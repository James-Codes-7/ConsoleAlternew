package model;

public class TicketCancel {
    private long flightId;
    private long ticketId;
    private long passengerEmailId;
    private long passengerPhoneNumber;
    public long getFlightId() {
        return flightId;
    }
    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }
    public long getTicketId() {
        return ticketId;
    }
    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
    public long getPassengerEmailId() {
        return passengerEmailId;
    }
    public void setPassengerEmailId(long passengerEmailId) {
        this.passengerEmailId = passengerEmailId;
    }
    public long getPassengerPhoneNumber() {
        return passengerPhoneNumber;
    }
    public void setPassengerPhoneNumber(long passengerPhoneNumber) {
        this.passengerPhoneNumber = passengerPhoneNumber;
    }
}
