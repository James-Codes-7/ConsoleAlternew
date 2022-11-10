package model;

public class Ticket {
    private long ticketId;
    private long ticketBookedTime;
    private byte totalPassengers;
    private boolean isCancel;
    private long flightId;
    private double totalTicketAmount;
    public long getFlightId() {
        return flightId;
    }
    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }
    public boolean isCancel() {
        return isCancel;
    }
    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }
    public long getTicketId() {
        return ticketId;
    }
    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
    public long getTicketBookedTime() {
        return ticketBookedTime;
    }
    public void setTicketBookedTime(long ticketBookedTime) {
        this.ticketBookedTime = ticketBookedTime;
    }
    public byte getTotalPassengers() {
        return totalPassengers;
    }
    public void setTotalPassengers(byte totalPassengers) {
        this.totalPassengers = totalPassengers;
    }
    public double getTotalTicketAmount() {
        return totalTicketAmount;
    }
    public void setTotalTicketAmount(double totalTicketAmount) {
        this.totalTicketAmount = totalTicketAmount;
    }
}
