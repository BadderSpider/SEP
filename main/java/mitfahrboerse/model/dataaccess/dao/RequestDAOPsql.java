package mitfahrboerse.model.dataaccess.dao;

import mitfahrboerse.global.dto.OfferDTO;
import mitfahrboerse.global.dto.RequestDTO;
import mitfahrboerse.global.dto.SearchCriteriaDTO;
import mitfahrboerse.model.dataaccess.exceptions.DataAccessException;
import mitfahrboerse.model.dataaccess.exceptions.NoResultException;
import mitfahrboerse.model.dataaccess.util.DAOMapper;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The PostgreSQL-specific implementation of the {@link RequestDAO} interface.
 * This class handles all database interactions for ride request entities.
 * Instances are created within a transaction to ensure data integrity.
 *
 * @author Jonathan Schilcher
 */
public final class RequestDAOPsql extends AbstractDAO implements RequestDAO {

    /**
     * Constructs a new RequestDAOPsql with a database connection provided by a transaction.
     * This ensures all operations are part of the same atomic transaction.
     *
     * @param connection The database connection from the current transaction context.
     */
    public RequestDAOPsql(Connection connection) {
        super(connection);
    }


    @Override
    public long createRequest(RequestDTO request) throws DataAccessException {
        String sql = "INSERT INTO t_request (requester_id, start_location, destination_location, desired_timestamp, price, baggage, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return create(sql,
                request.getRequesterId(),
                request.getStart(),
                request.getDestination(),
                Timestamp.from(request.getDesiredTime().toInstant()),
                request.getPrice(),
                request.getBaggage(),
                request.getDescription()
        ).orElse(-1L);
    }

    @Override
    public void updateRequest(RequestDTO request) throws DataAccessException {
        String sql = "UPDATE t_request SET start_location = ?, destination_location = ?, desired_timestamp = ?, price = ?, baggage = ?, description = ? WHERE request_id = ?";
        execute(sql,
                request.getStart(),
                request.getDestination(),
                Timestamp.from(request.getDesiredTime().toInstant()),
                request.getPrice(),
                request.getBaggage(),
                request.getDescription(),
                request.getRequestId()
        );
    }

    @Override
    public void deleteRequest(long requestId) throws DataAccessException {
        String sql = "DELETE FROM t_request WHERE request_id = ?";
        execute(sql, requestId);
    }

    @Override
    public void findRequestById(RequestDTO requestDTO) throws DataAccessException {
        String sql = "SELECT * FROM t_request WHERE request_id = ?";
        Optional<RequestDTO> optRequest = findOne(sql, DAOMapper::mapRequest, requestDTO.getRequestId());
        if (optRequest.isPresent()) {
            RequestDTO request = optRequest.get();
            requestDTO.setRequestId(request.getRequestId());
            requestDTO.setRequesterId(request.getRequesterId());
            requestDTO.setStart(request.getStart());
            requestDTO.setDestination(request.getDestination());
            requestDTO.setPrice(request.getPrice());
            requestDTO.setBaggage(request.getBaggage());
            requestDTO.setDescription(request.getDescription());
            requestDTO.setDesiredTime(request.getDesiredTime());
        } else {
            throw new NoResultException("Request with id " + requestDTO.getRequestId() + " not found");
        }
    }

    @Override
    public List<RequestDTO> findRequestsByCriteria(SearchCriteriaDTO criteria) throws DataAccessException {
        // Not implemented
        return List.of();
    }

    @Override
    public List<OfferDTO> findOffersByRequestId(long requestId) throws DataAccessException {
        String sql = "SELECT * FROM t_offer WHERE request_id = ?";
        return findAll(sql, DAOMapper::mapOffer, requestId);
    }

    @Override
    public long countRequestsByCriteria(SearchCriteriaDTO criteria) throws DataAccessException {
        // Not implemented
        return 0;
    }
}