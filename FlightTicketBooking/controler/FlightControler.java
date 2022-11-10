package controler;

import model.Flight;
import model.FlightTicketDatabase;
import view.Display;

import java.util.List;

public class FlightControler {
    private long FLIGHTIDSTART = 12345;
    private static FlightControler flightControler;
    private FlightTicketDatabase flightTicketDatabase;
    private Display display;

    public boolean availableSeatCheck(long flightId) {
        int index = (int) (flightId - FLIGHTIDSTART);
        return flightTicketDatabase.getFlightList().get(index).getIsavailable();
    }

    public void flightView(long flightId, boolean bool) {
        List<Flight> flightList = flightTicketDatabase.getFlightList();
        if (bool == true) {
            for (Flight flight : flightList) {
                if (flight.getFlightId() == flightId) {
                    display.alert("\nFlight Id:                     " + flight.getFlightId() +
                            "\nFlight Name:                   " + flight.getFlightName() +
                            "\nFlight Starting Place:         " + flight.getStartingPlace() +
                            "\nFlight Land Place:             " + flight.getLandPlace() +
                            "\nFlight total Seats:            " + flight.getTotalSeats() +
                            "\nFlight Booked Seats:           " + flight.getBookedSeats() +
                            "\nFlight Ticket Amount:          " + flight.getTicketAmount() +
                            "Flight Available Seats:        " + flight.getAvailableSeats());
                }
            }
        } else {
            for (Flight flight : flightList) {
                display.alert("\nFlight Id:                     " + flight.getFlightId() +
                        "\nFlight Name:                   " + flight.getFlightName() +
                        "\nFlight Starting Place:         " + flight.getStartingPlace() +
                        "\nFlight Land Place:             " + flight.getLandPlace() +
                        "\nFlight total Seats:            " + flight.getTotalSeats() +
                        "\nFlight Booked Seats:           " + flight.getBookedSeats() +
                        "\nFlight Ticket Amount:          " + flight.getTicketAmount() +
                        "Flight Available Seats:        " + flight.getAvailableSeats());
            }
        }
    }

    public void seatControl(long BOOKFLIGHTID) {
        flightTicketDatabase.getFlightList().get((int) (BOOKFLIGHTID - FLIGHTIDSTART)).setAvailableSeats(
                flightTicketDatabase.getFlightList().get((int) (BOOKFLIGHTID - FLIGHTIDSTART)).getAvailableSeats() - 1);

        flightTicketDatabase.getFlightList().get((int) (BOOKFLIGHTID - FLIGHTIDSTART)).setBookedSeats((short)
                (flightTicketDatabase.getFlightList().get((int) (BOOKFLIGHTID - FLIGHTIDSTART)).getBookedSeats() + 1));

        if (flightTicketDatabase.getFlightList().get((int) (BOOKFLIGHTID - FLIGHTIDSTART)).getAvailableSeats() == 0)
            flightTicketDatabase.getFlightList().get((int) (BOOKFLIGHTID - FLIGHTIDSTART)).setIsavailable(false);
        flightTicketDatabase.flightDetailUpdate(BOOKFLIGHTID);
    }

    public static FlightControler getInstance() {
        if (flightControler == null) flightControler = new FlightControler();
        return flightControler;
    }

    public FlightControler() {
        flightTicketDatabase = FlightTicketDatabase.getInstance();
        display = Display.getInstance();
    }
}
