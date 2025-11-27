package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.OfferDTO;
import mitfahrboerse.global.dto.OfferStatus;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;
import mitfahrboerse.model.dataaccess.exceptions.NoResultException;
import mitfahrboerse.model.dataaccess.util.DAOMapper;

import java.sql.Connection;
import java.util.Optional;

/**
 * The PostgreSQL-specific implementation of the {@link OfferDAO} interface.
 * This class is responsible for all SQL operations related to offer entities.
 * Instances are created within a transaction to ensure data consistency.
 *
 * @author Jonathan Schilcher
 */
public final class OfferDAOPsql extends AbstractDAO implements OfferDAO {

    /**
     * Constructs a new OfferDAOPsql with a database connection provided by a transaction.
     * This ensures all operations are part of the same atomic transaction.
     *
     * @param connection The database connection from the current transaction context.
     */
    public OfferDAOPsql(Connection connection) {
        super(connection);
    }


    @Override
    public long createOffer(OfferDTO offer) throws DataAccessException {
        String sql = "INSERT INTO t_offer (request_id, drive_id, status) VALUES (?, ?, ?)";
        return create(sql,
                offer.getRequest(),
                offer.getRide(),
                offer.getStatus()
        ).orElse(-1L);
    }

    @Override
    public void updateOfferStatus(long offerId, OfferStatus status) throws DataAccessException {
        String sql = "UPDATE t_offer SET status = ? WHERE offer_id = ?";
        execute(sql, status, offerId);
    }

    @Override
    public void deleteOffer(long offerId) throws DataAccessException {
        String sql = "DELETE FROM t_offer WHERE offer_id = ?";
        execute(sql, offerId);
    }

    @Override
    public void findOfferById(OfferDTO offerDTO) throws DataAccessException {
        String sql = "SELECT * FROM t_offer WHERE offer_id = ?";
        Optional<OfferDTO> optOffer = findOne(sql, DAOMapper::mapOffer, offerDTO.getOfferId());
        if (optOffer.isPresent()) {
            OfferDTO offer = optOffer.get();
            offerDTO.setOfferId(offer.getOfferId());
            offerDTO.setRide(offer.getRide());
            offerDTO.setRequest(offer.getRequest());
            offerDTO.setStatus(offer.getStatus());
        } else {
            throw new NoResultException("Offer with id " + offerDTO.getOfferId() + " not found");
        }
    }

    @Override
    public boolean hasDriverAlreadyOffered(long driverId, long requestId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM v_offer_details WHERE driver_id = ? AND request_id = ?";
        long count = findScalar(sql, Long.class, driverId, requestId).orElse(0L);
        return count > 0;
    }
}