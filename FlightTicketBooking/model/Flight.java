package model;

public class Flight {
    private String flightName;
    private long flightId;
    private String startingPlace;
    private String landPlace;
    private String filghtStartTime;
    private String flightLandTime;
    private int totalSeats;
    private short bookedSeats;
    private long ticketPrice;
    private boolean isavailable;
    private double ticketAmount;
    private int availableSeats;
    public long getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public short getBookedSeats() {
        return bookedSeats;
    }
    public void setBookedSeats(short bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
    public boolean getIsavailable() {
        return isavailable;
    }
    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }
    public double getTicketAmount() {
        return ticketAmount;
    }
    public void setTicketAmount(double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }
    public String getFlightName() {
        return flightName;
    }
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }
    public long getFlightId() {
        return flightId;
    }
    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }
    public String getStartingPlace() {
        return startingPlace;
    }
    public void setStartingPlace(String startingPlace) {
        this.startingPlace = startingPlace;
    }
    public String getLandPlace() {
        return landPlace;
    }
    public void setLandPlace(String landPlace) {
        this.landPlace = landPlace;
    }
    public String getFilghtStartTime() {
        return filghtStartTime;
    }
    public void setFilghtStartTime(String filghtStartTime) {
        this.filghtStartTime = filghtStartTime;
    }
    public String getFlightLandTime() {
        return flightLandTime;
    }
    public void setFlightLandTime(String flightLandTime) {
        this.flightLandTime = flightLandTime;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
