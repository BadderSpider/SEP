package mitfahrboerse.model.presentation.beans;

import mitfahrboerse.global.dto.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mitfahrboerse.model.business.exception.BusinessException;
import mitfahrboerse.model.business.service.RideService;

/**
 * Backing Bean for the rides.xhtml.
 * Inherits from AbstractPagedTableBean.
 * To manage the search criteria and display the resulting list
 * of available rides in a paginated table.
 *
 * @author Matthias Schmitt
 */
@Named("ridesBean")
@ViewScoped
public class RidesBean extends AbstractPagedTableBean<RideDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * DTO to hold the search criteria from the filter fields in the table header.
     */
    private SearchCriteriaDTO searchCriteria;

    /**
     * Initializes the bean.
     * Creates a new SearchCriteriaDTO and loads the initial data.
     */
    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.searchCriteria = new SearchCriteriaDTO();

        filter();

        //TEST DATEN ENTWICKLUNG
        if (getPagedItems() == null || getPagedItems().isEmpty() ||true) {
            createTestData();
        }
    }

    /**
     * toDO del
     */
    private void createTestData() {
        List<RideDTO> testRides = new ArrayList<>();


        List<StopDTO> stops1 = new ArrayList<>();

        stops1.add(new StopDTO(
                1L, 1L, StopType.START, "München Hbf",
                3, 2, OffsetDateTime.now().plusDays(1).withHour(8).withMinute(0).withOffsetSameInstant(ZoneOffset.UTC), 0
        ));


        stops1.add(new StopDTO(
                2L, 1L, StopType.FINISH, "Berlin ZOB",
                0, 0, OffsetDateTime.now().plusDays(1).withHour(14).withMinute(0).withOffsetSameInstant(ZoneOffset.UTC), 1
        ));

        RideDTO ride1 = new RideDTO(
                100L,                   // driverId
                1L,                     // rideId
                TransportationType.CAR, // transportationType
                25.50,                  // pricePerSeat
                3,                      // maxSeats
                2,                      // maxBaggage
                "Fahre entspannt nach Berlin, Nichtraucher.", // description
                stops1,                 // stops
                LocalDate.now().plusDays(1), // date
                LocalDate.now(),        // publicationDate
                RideStatus.PLANNED      // rideStatus
        );
        testRides.add(ride1);

        // --- Datensatz 2: Hamburg -> Köln (Bus) ---
        List<StopDTO> stops2 = new ArrayList<>();

        stops2.add(new StopDTO(
                3L, 2L, StopType.START, "Hamburg ZOB",
                40, 40, OffsetDateTime.now().plusDays(2).withHour(10).withMinute(30).withOffsetSameInstant(ZoneOffset.UTC), 0
        ));

        stops2.add(new StopDTO(
                4L, 2L, StopType.FINISH, "Köln Hbf",
                40, 40, OffsetDateTime.now().plusDays(2).withHour(16).withMinute(45).withOffsetSameInstant(ZoneOffset.UTC), 1
        ));

        RideDTO ride2 = new RideDTO(
                101L,
                2L,
                TransportationType.IC_BUS, // oder BUS je nach Enum
                15.00,
                40,
                40,
                "Schnellbus Direktverbindung.",
                stops2,
                LocalDate.now().plusDays(2),
                LocalDate.now(),
                RideStatus.PLANNED
        );
        testRides.add(ride2);


        setPagedItems(testRides);


        setTotalElements(testRides.size());
    }

    /**
     * Action method for the 'cb_search' button.
     */
    @Override
    public void filter() {
        try {
            this.setPagedItems(RideService.searchRides(searchCriteria));
        } catch (BusinessException e) {
            // toDo
        }
    }


    public SearchCriteriaDTO getSearchCriteria() {
        return this.searchCriteria;
    }

    public void setSearchCriteria(SearchCriteriaDTO searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    protected void load() {
        try {
            filter();
        } catch (Exception e) {
           //toDo
        }
    }

    public List<TransportationType> getTransportationTypes() {
        return Arrays.asList(TransportationType.values());
    }
}