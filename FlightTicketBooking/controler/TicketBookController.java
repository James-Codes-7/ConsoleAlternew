package controler;

import model.Flight;
import model.FlightTicketDatabase;
import model.Passenger;
import model.Ticket;
import view.Display;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TicketBookController {
    private static TicketBookController ticketBookController;
    private Display display;
    private static long FLIGHTIDDIGITS = 5;
    private FlightControler flightControler;

    enum Status {
        BOOKED, WAITING
    }

    private Scanner scanner = new Scanner(System.in);
    private long FLIGHTIDSTART = 12345;
    private long TICKETIDSTART = 10000;
    private Long BOOKFLIGHTID;
    private long TicketIdGenerater = 0;
    byte passengerCount = 0;
    private PassengerInformatiomController passengerInformatiomController;
    private FlightTicketDatabase flightTicketDatabase;

    public static TicketBookController getInstance() {
        if (ticketBookController == null) ticketBookController = new TicketBookController();
        return ticketBookController;
    }

    public TicketBookController() {
        flightTicketDatabase = FlightTicketDatabase.getInstance();
        passengerInformatiomController = PassengerInformatiomController.getInstance();
        flightControler = FlightControler.getInstance();
        display = Display.getInstance();
    }

    public void bookTicket() {
        TicketIdGenerater = TICKETIDSTART + flightTicketDatabase.getTicketList().size();
        byte option = 0;
        while (true) {
            try {
                display.alert("How many Ticket you want");
                passengerCount = scanner.nextByte();
                display.alert("Your ticket Count is: + passengerCount\nIf you want change Count Press 1 else 0");
                option = scanner.nextByte();
                if (option == 1) continue;
                break;
            } catch (InputMismatchException e) {
                display.alert("Input Mismatch");
                scanner.next();
            }
        }
        scanner.nextLine();
        display.alert("Enter your starting Place");
        String startPlace = scanner.nextLine();
        display.alert("Enter the landing Place");
        String landPlace = scanner.nextLine();
        int ticketIterate = 0;
        byte flightCheck = flightView(startPlace, landPlace);
        if (flightCheck == 0) {
            display.alert("No flight Available");
            return;
        }
        Ticket ticket = new Ticket();
        List<Passenger> passengerList = new ArrayList<>();
        while (ticketIterate < passengerCount) {
            Passenger passenger = new Passenger();
            String passengerName = passengerInformatiomController.nameFill();
            long passengerAadharNumber = passengerInformatiomController.aadharFill();
            String passengerDateOfBirth = passengerInformatiomController.dateOfBirthFill();
            long passengerPhoneNumber = passengerInformatiomController.mobileNumberFill();
            short passengerAge = passengerInformatiomController.ageFill();
            String passengerEmailId = passengerInformatiomController.emailIdFill();
            String passengerAddress = passengerInformatiomController.addressFill();
            long ticketId = TicketIdGenerater;
            short seatno = -1;
            String ticketStatus = "";
            if (flightControler.availableSeatCheck(BOOKFLIGHTID)) {
                ticketStatus = Status.BOOKED.toString();
                flightControler.seatControl(BOOKFLIGHTID);
                seatno = (short) flightTicketDatabase.getPassengerList().size();

            } else {
                ticketStatus = Status.WAITING.toString();
            }
            passenger.setPassengerName(passengerName);
            passenger.setPassengerAadharNumber(passengerAadharNumber);
            passenger.setPassengerDateOfBirth(passengerDateOfBirth);
            passenger.setPassengerPhoneNumber(passengerPhoneNumber);
            passenger.setPassengerAge(passengerAge);
            passenger.setPassengerEmailId(passengerEmailId);
            passenger.setPassengerAddress(passengerAddress);
            passenger.setTicketId(ticketId);
            passenger.setSeatno(seatno);
            passenger.setPassengerTicketStatus(ticketStatus);
            passenger.setTicketId(TicketIdGenerater);
            passengerList.add(passenger);
            flightTicketDatabase.setPassengerList(passenger);
            ticketIterate++;
        }
        ticket.setTicketId(TicketIdGenerater);
        ticket.setTicketBookedTime(System.currentTimeMillis());
        ticket.setTotalPassengers(passengerCount);
        ticket.setTotalTicketAmount(flightTicketDatabase.getFlightList().get((int) (BOOKFLIGHTID - FLIGHTIDSTART)).getTicketAmount() *
                passengerCount);
        ticket.setCancel(false);
        ticket.setFlightId(BOOKFLIGHTID);
        flightTicketDatabase.setTicketList(ticket);
        flightTicketDatabase.ticketDetailUpdate(ticket);
        flightTicketDatabase.passengerDetailUpdate(passengerList);
        display.alert("Ticket Booked Success Fully");
        display.alert("Your Ticket Id is:  " + TicketIdGenerater);
        TicketIdGenerater++;
    }

    private byte flightView(String startPlace, String landPlace) {
        List<Flight> flightList = flightTicketDatabase.getFlightList();
        byte check = 0;
        String flightIdCheck = "";
        for (Flight flight : flightList) {
            if (flight.getStartingPlace().equalsIgnoreCase(startPlace) && flight.getLandPlace().equalsIgnoreCase(landPlace)) {
                display.alert("\nFlight Id:             " + flight.getFlightId() +
                        "\nFlight Name:           " + flight.getFlightName() +
                        "\nTotal Seats:           " + flight.getAvailableSeats() +
                        "\nFlight Availablity:    " + flight.getIsavailable() +
                        "\nFlight Booked seats:   " + flight.getBookedSeats() + "\n");

                check = 1;
                flightIdCheck += flight.getFlightId();
            }
        }
        if (check == 1) {
            display.alert("Enter the flight Id");
            while (true) {
                try {
                    BOOKFLIGHTID = scanner.nextLong();
                    int size = flightIdCheck.indexOf(BOOKFLIGHTID.toString());
                    size = size != 0 ? size + 1 : size;
                    if (BOOKFLIGHTID.toString().length() == FLIGHTIDDIGITS && (size) % 5 == 0 &&
                            flightIdCheck.contains(BOOKFLIGHTID.toString())) {
                        break;
                    } else display.alert("Enter the Correct Flight Id");
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return check;
    }
}
