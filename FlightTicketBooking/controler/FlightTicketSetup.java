package controler;

import connection.JDBCOperation;
import model.Flight;
import model.FlightTicketDatabase;
import model.Passenger;
import model.Ticket;
import view.Display;


import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightTicketSetup {
    private static FlightTicketSetup flightTicketSetup;
    private Display display;
    public static FlightTicketSetup getInstance() {
        if (flightTicketSetup == null) flightTicketSetup = new FlightTicketSetup();
        return flightTicketSetup;
    }
    private FlightTicketDatabase flightTicketDatabase;

    private JDBCOperation jdbcOperation;
    public void flightTicketSetup() {
        try {
            ResultSet result = jdbcOperation.retriveQuery("select * from flightDetail");
            while (result.next()) {
                Flight flight = new Flight();
                flight.setFlightId(result.getLong(1));
                flight.setFlightName(result.getString(2));
                flight.setStartingPlace(result.getString(3));
                flight.setLandPlace(result.getString(4));
                flight.setTotalSeats(result.getInt(7));
                flight.setAvailableSeats(result.getInt(7));
                flight.setIsavailable(result.getBoolean(8));
                flight.setBookedSeats(result.getShort(9));
                flight.setTicketAmount(result.getLong(11));
                flightTicketDatabase.setFlightList(flight);
            }
            result = jdbcOperation.retriveQuery("select * from passengers");
            while (result.next()) {
                Passenger passenger = new Passenger();
                passenger.setPassengerName(result.getString(1));
                passenger.setPassengerAadharNumber(result.getLong(2));
                passenger.setPassengerDateOfBirth(result.getString(3));
                passenger.setPassengerPhoneNumber(result.getLong(4));
                passenger.setPassengerAge(result.getShort(5));
                passenger.setPassengerEmailId(result.getString(6));
                passenger.setPassengerAddress(result.getString(7));
                passenger.setPassengerTicketStatus(result.getString(8));
                passenger.setSeatno(result.getShort(9));
                passenger.setTicketId(result.getLong(10));
                flightTicketDatabase.setPassengerList(passenger);
            }
            result = jdbcOperation.retriveQuery("select * from ticket");
            while (result.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(result.getLong(1));
                ticket.setTicketBookedTime(result.getLong(2));
                ticket.setTotalPassengers(result.getByte(3));
                ticket.setCancel(result.getBoolean(4));
                ticket.setFlightId(result.getLong(5));
                ticket.setTotalTicketAmount(result.getLong(6));
                flightTicketDatabase.setTicketList(ticket);
            }
            display.alert("Flight ticket Setup Success Fully");
        } catch (SQLException s) {
            display.alert("Cannot Iterate");
        }
    }
    FlightTicketSetup() {
        flightTicketDatabase = FlightTicketDatabase.getInstance();
        jdbcOperation = JDBCOperation.getInstance();
        display=Display.getInstance();
    }
}
