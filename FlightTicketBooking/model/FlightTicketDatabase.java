package model;

import connection.JDBCOperation;

import java.util.ArrayList;
import java.util.List;

public class FlightTicketDatabase {
    private static FlightTicketDatabase flightTicketDatabase;
    private JDBCOperation jdbcOperation;
    List<TicketCancel> ticketCancels;
    private List<Flight> flightList;
    private List<Ticket> ticketList;
    private List<Passenger> passengerList;
    public List<Passenger> getPassengerList() {
        return passengerList;
    }
    public void setPassengerList(Passenger passengerList) {
        this.passengerList.add(passengerList);
    }
    public List<Flight> getFlightList() {
        return flightList;
    }
    public void setFlightList(Flight flightList) {
        this.flightList.add(flightList);
    }
    public List<Ticket> getTicketList() {
        return ticketList;
    }
    public void setTicketList(Ticket ticketList) {
        this.ticketList.add(ticketList);
    }
    public static FlightTicketDatabase getInstance() {
        if (flightTicketDatabase == null) flightTicketDatabase = new FlightTicketDatabase();
        return flightTicketDatabase;
    }
    public FlightTicketDatabase() {
        flightList = new ArrayList<>();
        ticketList = new ArrayList<>();
        ticketCancels = new ArrayList<>();
        passengerList = new ArrayList<>();
        jdbcOperation = JDBCOperation.getInstance();
    }
    public void flightDetailUpdate(long flightId) {
        List<Flight> flightList = flightTicketDatabase.getFlightList();
        for (Flight flight : flightList) {
            if (flight.getFlightId() == flightId) {
                jdbcOperation.updateQuery("update flightdetail set totalseats=" + flight.getTotalSeats() + ",bookedseats=" + flight.getBookedSeats() + "," +
                        "flightavailable=" + flight.getIsavailable() + " where flightid=" + flight.getFlightId());
            }
        }
    }
    public void passengerDetailUpdate(List<Passenger> passengerList) {
        for (Passenger passenger : passengerList) {
            jdbcOperation.updateQuery("insert into passengers values('" + passenger.getPassengerName() + "'," + passenger.getPassengerAadharNumber()
                    + ",'" + passenger.getPassengerDateOfBirth() + "'," + passenger.getPassengerPhoneNumber() + "," + passenger.getPassengerAge() + ","
                    + "'" + passenger.getPassengerEmailId() + "','" + passenger.getPassengerAddress() + "','" + passenger.getPassengerTicketStatus() + "'," +
                    "" + passenger.getSeatno() + "," + passenger.getTicketId() + " )");
        }
    }
    public void ticketDetailUpdate(Ticket ticket) {

        jdbcOperation.updateQuery("insert into ticket values(" + ticket.getTicketId() + "," + ticket.getTicketBookedTime() + "," +
                "" + ticket.getTotalPassengers() + "," + ticket.isCancel() + "," + ticket.getFlightId() + "," + ticket.getTotalTicketAmount() + ")");
    }
    public void ticketRemoveUpdate(long ticketId) {
        jdbcOperation.updateQuery("delete from ticket where ticketid=" + ticketId);
    }
}
