package model;

public class Passenger {

    private String passengerName;
    private long passengerAadharNumber;
    private String passengerDateOfBirth;
    private long passengerPhoneNumber;
    private short passengerAge;
    private String passengerEmailId;
    private String passengerAddress;
    private long ticketId;
    private short seatno;
    private String passengerTicketStatus;
    public long getTicketId() {
        return ticketId;
    }
    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
    public short getSeatno() {
        return seatno;
    }
    public void setSeatno(short seatno) {
        this.seatno = seatno;
    }
    public String getPassengerTicketStatus() {
        return passengerTicketStatus;
    }
    public void setPassengerTicketStatus(String passengerTicketStatus) {
        this.passengerTicketStatus = passengerTicketStatus;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public long getPassengerAadharNumber() {
        return passengerAadharNumber;
    }
    public void setPassengerAadharNumber(long passengerAadharNumber) {
        this.passengerAadharNumber = passengerAadharNumber;
    }
    public String getPassengerDateOfBirth() {
        return passengerDateOfBirth;
    }
    public void setPassengerDateOfBirth(String passengerDateOfBirth) {
        this.passengerDateOfBirth = passengerDateOfBirth;
    }
    public long getPassengerPhoneNumber() {
        return passengerPhoneNumber;
    }
    public void setPassengerPhoneNumber(long passengerPhoneNumber) {
        this.passengerPhoneNumber = passengerPhoneNumber;
    }
    public short getPassengerAge() {
        return passengerAge;
    }
    public void setPassengerAge(short passengerAge) {
        this.passengerAge = passengerAge;
    }
    public String getPassengerEmailId() {
        return passengerEmailId;
    }
    public void setPassengerEmailId(String passengerEmailId) {
        this.passengerEmailId = passengerEmailId;
    }
    public String getPassengerAddress() {
        return passengerAddress;
    }
    public void setPassengerAddress(String passengerAddress) {
        this.passengerAddress = passengerAddress;
    }
}
