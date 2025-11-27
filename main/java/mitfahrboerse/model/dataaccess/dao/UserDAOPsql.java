package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.*;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;
import mitfahrboerse.model.dataaccess.exceptions.NoResultException;
import mitfahrboerse.model.dataaccess.util.DAOMapper;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The PostgreSQL-specific implementation of the {@link UserDAO} interface.
 * This class executes all SQL statements related to users, profiles, credentials,
 * and pictures against a PostgreSQL database within a transactional context.
 *
 * @author Jonathan Schilcher
 */
public final class UserDAOPsql extends AbstractDAO implements UserDAO {

    /**
     * Constructs a new UserDAOPsql with a database connection provided by a transaction.
     * This ensures all operations are part of the same atomic transaction.
     *
     * @param connection The database connection from the current transaction context.
     */
    public UserDAOPsql(Connection connection) {
        super(connection);
    }


    @Override
    public long createUser(RegistrationDTO registrationDTO) throws DataAccessException {
        String sql = "INSERT INTO t_user (first_name, last_name, email, password_hash, password_salt, phone_number, street, house_number, postal_code, city, country) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PasswordDTO password = registrationDTO.getPasswordDTO();
        AddressDTO address = registrationDTO.getAddress();
        return create(sql,
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getEmail(),
                password.getPasswordHash(),
                password.getSalt(),
                registrationDTO.getPhoneNumber(),
                address.getStreet(),
                address.getStreetNumber(),
                address.getPLZ(),
                address.getCity(),
                address.getCountry()).orElse(-1L);
    }

    @Override
    public void updateUser(UserDTO user) throws DataAccessException {
        String sql = "UPDATE t_user SET first_name = ?, last_name = ?, email = ?, phone_number = ?, street = ?, house_number = ?, postal_code = ?, city = ?, country = ? WHERE user_id = ?";
        AddressDTO address = user.getAddress();
        execute(sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                address.getStreet(),
                address.getStreetNumber(),
                address.getPLZ(),
                address.getCity(),
                address.getCountry(),
                user.getUserId()
        );
    }

    @Override
    public void deleteUser(long userId) throws DataAccessException {
        String sql = "DELETE FROM t_user WHERE user_id = ?";
        execute(sql, userId);
    }

    @Override
    public void findUserById(UserDTO userDTO) throws DataAccessException {
        String sql = "SELECT * FROM t_user WHERE user_id = ?";
        Optional<UserDTO> optUser = findOne(sql, DAOMapper::mapUser, userDTO.getUserId());
        if (optUser.isPresent()) {
            UserDTO user = optUser.get();
            fillUserAttrs(userDTO, user);
        } else {
            throw new NoResultException("User with id " + userDTO.getUserId() + " does not exist");
        }
    }

    @Override
    public void findUserByEmail(UserDTO userDTO) throws DataAccessException {
        String sql = "SELECT * FROM t_user WHERE email = ?";
        Optional<UserDTO> optUser = findOne(sql, DAOMapper::mapUser, userDTO.getEmail());
        if (optUser.isPresent()) {
            UserDTO user = optUser.get();
            fillUserAttrs(userDTO, user);
        } else  {
            throw new NoResultException("User with email " + userDTO.getEmail() + " does not exist");
        }
    }

    @Override
    public void saveProfilePicture(ImageDTO picture) throws DataAccessException {
        String sql = "INSERT INTO t_profile_picture (user_id, image_data, mimetype) VALUES (?, ?, ?) " +
                "ON CONFLICT (user_id) DO UPDATE SET image_data = EXCLUDED.image_data, mimetype = EXCLUDED.mimetype";
        execute(sql, picture.getUserId(), picture.getData(), picture.getMimetype());
    }

    @Override
    public void findProfilePictureByUserId(ImageDTO imageDTO) throws DataAccessException {
        String sql = "SELECT user_id, image_data, mimetype FROM t_profile_picture WHERE user_id = ?";
        Optional<ImageDTO> optImage = findOne(sql, DAOMapper::mapImage, imageDTO.getUserId());
        if (optImage.isPresent()) {
            ImageDTO image = optImage.get();
            imageDTO.setUserId(image.getUserId());
            imageDTO.setMimetype(image.getMimetype());
            imageDTO.setData(image.getData());
        } else  {
            throw new NoResultException("Picture for user with id " + imageDTO.getUserId() + " does not exist");
        }
    }

    @Override
    public void deleteProfilePicture(long userId) throws DataAccessException {
        String sql = "DELETE FROM t_profile_picture WHERE user_id = ?";
        execute(sql, userId);
    }

    @Override
    public PasswordDTO findPasswordInfoByEmail(String email) throws DataAccessException {
        String sql = "SELECT user_id, password_hash, password_salt FROM t_user WHERE email = ?";
        return findOne(sql, rs -> new PasswordDTO(
                rs.getLong("user_id"),
                rs.getString("password_salt"),
                rs.getString("password_hash")
        ), email).orElse(null);
    }

    @Override
    public void saveVerificationToken(VerificationTokenDTO token) throws DataAccessException {
        String sql = "INSERT INTO t_verification_tokens (user_id, token_type, token_hash, new_email, expires_at) VALUES (?, ?, ?, ?, ?)";
        execute(sql,
                token.getUserId(),
                token.getTokenType(),
                token.getTokenHash(),
                token.getEmail(), // For email changes, this holds the new email
                Timestamp.from(token.getExpiresAt().toInstant()));
    }

    @Override
    public PasswordResetDTO getPasswordResetInfoByHashedToken(String hashedToken) throws DataAccessException {
        String sql = "SELECT t.user_id, u.password_salt, u.password_hash, t.expires_at, t.token_hash " +
                "FROM t_verification_tokens t JOIN t_user u ON t.user_id = u.user_id " +
                "WHERE t.token_hash = ? AND t.token_type = 'PASSWORD'";
        return findOne(sql, rs -> new PasswordResetDTO(
                rs.getLong("user_id"),
                rs.getString("password_salt"),
                rs.getString("password_hash"),
                rs.getTimestamp("expires_at").toInstant().atOffset(java.time.ZoneOffset.UTC),
                rs.getString("token_hash"),
                null // newPasswordHash is not stored here
        ), hashedToken).orElseThrow(() -> new NoResultException("No password reset info found for the provided token."));
    }

    @Override
    public MailResetDTO getMailResetInfoByHashedToken(String hashedToken) throws DataAccessException {
        String sql = "SELECT user_id, new_email, expires_at, token_hash FROM t_verification_tokens WHERE token_hash = ? AND token_type = 'EMAIL'";
        return findOne(sql, rs -> new MailResetDTO(
                rs.getLong("user_id"),
                rs.getString("new_email"),
                rs.getTimestamp("expires_at").toInstant().atOffset(java.time.ZoneOffset.UTC),
                rs.getString("token_hash")
        ), hashedToken).orElseThrow(() -> new NoResultException("No mail reset info found for the provided token."));
    }

    @Override
    public void resetPassword(PasswordDTO newPassword) throws DataAccessException {
        String sql = "UPDATE t_user SET password_hash = ?, password_salt = ? WHERE user_id = ?";
        execute(sql, newPassword.getPasswordHash(), newPassword.getSalt(), newPassword.getUserId());
    }

    @Override
    public void resetMail(long userId, String newEmail) throws DataAccessException {
        String sql = "UPDATE t_user SET email = ? WHERE user_id = ?";
        execute(sql, newEmail, userId);
    }

    @Override
    public void deleteVerificationTokenByHashedToken(String hashedToken) throws DataAccessException {
        String sql = "DELETE FROM t_verification_tokens WHERE token_hash = ?";
        execute(sql, hashedToken);
    }


    @Override
    public List<RideDTO> findRidesByDriverId(long driverId) throws DataAccessException {
        String sql = "SELECT * FROM v_concrete_drive_details WHERE driver_id = ?";
        return findAll(sql, DAOMapper::mapRide, driverId);
    }

    @Override
    public List<BookingDTO> findBookingsByPassengerId(long passengerId) throws DataAccessException {
        String sql = "SELECT * FROM t_booking WHERE passenger_id = ?";
        return findAll(sql, DAOMapper::mapBooking, passengerId);
    }

    @Override
    public List<RequestDTO> findRequestsByUserId(long requesterId) throws DataAccessException {
        String sql = "SELECT * FROM t_request WHERE requester_id = ?";
        return findAll(sql, DAOMapper::mapRequest, requesterId);
    }

    @Override
    public List<OfferDTO> findOffersByDriverId(long driverId) throws DataAccessException {
        String sql = "SELECT * FROM v_offer_details WHERE driver_id = ?";
        return findAll(sql, DAOMapper::mapOffer, driverId);
    }

    @Override
    public List<BookingDTO> findPendingBookingsForDriver(long driverId) throws DataAccessException {
        String sql = "SELECT * FROM v_booking_details WHERE driver_id = ? AND status = 'REQUESTED'";
        return findAll(sql, DAOMapper::mapBooking, driverId);
    }

    @Override
    public List<OfferDTO> findPendingOffersByDriverId(long driverId) throws DataAccessException {
        String sql = "SELECT * FROM v_offer_details WHERE driver_id = ? AND status = 'OPEN'";
        return findAll(sql, DAOMapper::mapOffer, driverId);
    }

    @Override
    public List<UserDTO> findUsersByCriteria(SearchCriteriaDTO criteria) throws DataAccessException {
        // Not implemented
        return List.of();
    }

    @Override
    public long countUsersByCriteria(SearchCriteriaDTO criteria) throws DataAccessException {
        // Not implemented
        return 0;
    }

    private void fillUserAttrs(UserDTO fill, UserDTO values) {
        fill.setUserId(values.getUserId());
        fill.setFirstName(values.getFirstName());
        fill.setLastName(values.getLastName());
        fill.setEmail(values.getEmail());
        fill.setPhone(values.getPhone());
        fill.setAddress(values.getAddress());
        fill.setUserRole(values.getUserRole());
        fill.setRatingCount(values.getRatingCount());
        fill.setAverageRating(values.getAverageRating());
        fill.setMailVerified(values.isMailVerified());
    }
}