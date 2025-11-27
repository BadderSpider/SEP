package mitfahrboerse.global.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for the possible recurrence of a ride. Encapsules information that is necessary for recurrence such as start time and end time.
 *
 * @author Kerstin Arnoczky
 */
public class CycleDTO extends AbstractRideDTO {

	private CycleType rule;
	private LocalDate start;
	private LocalDate end;
	private List<LocalDate> exceptions;

    public CycleDTO(CycleType rule, LocalDate start, LocalDate end, List<LocalDate> exceptions) {
        this.rule = rule;
        this.start = start;
        this.end = end;
        this.exceptions = exceptions;
    }

    public CycleDTO(long driverId, long rideId, TransportationType transportationType, double pricePerSeat, int maxSeats, int maxBaggage, String description, List<StopDTO> stops, CycleType rule, LocalDate start, LocalDate end, List<LocalDate> exceptions) {
        super(driverId, rideId, transportationType, pricePerSeat, maxSeats, maxBaggage, description, stops);
        this.rule = rule;
        this.start = start;
        this.end = end;
        this.exceptions = exceptions;
    }

    public CycleType getRule() {
        return rule;
    }

    /**
     * Sets the rule through which the cycle is repeated.
     * 
     * @param rule The cycle type.
     */
    public void setRule(CycleType rule) {
        this.rule = rule;
    }

    public LocalDate getStart() {
        return start;
    }

    /**
     * Sets the first date where the cycle begins.
     * 
     * @param start The starting date of the cycle.
     */
    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    /**
     * Sets the last date where the cycle ends.
     * 
     * @param end The end date of the cycle.
     */
    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<LocalDate> getExceptions() {
        return exceptions;
    }

    /**
     * Sets the dates on which no ride of the cycle will be executed.
     * 
     * @param exceptions The list of exception dates.
     */
    public void setExceptions(List<LocalDate> exceptions) {
        this.exceptions = exceptions;
    }
}
