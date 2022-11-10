package controler;

import model.FlightTicketDatabase;
import model.Passenger;
import model.Ticket;
import view.Display;

import java.text.SimpleDateFormat;
import java.util.*;

public class TicketControler {
    private long FLIGHTIDSTART = 12345;
    private List<Short> seatNumberAssign = new ArrayList<>();
    private Display display;
    private static TicketControler ticketControler;
    private PassengerInformatiomController passengerInformatiomController;
    private FlightControler flightControler;
    private Scanner scanner = new Scanner(System.in);
    private long TICKETIDSTART = 10000;
    private FlightTicketDatabase flightTicketDatabase;

    public static TicketControler getInstance() {
        if (ticketControler == null) ticketControler = new TicketControler();
        return ticketControler;
    }

    public TicketControler() {
        flightTicketDatabase = FlightTicketDatabase.getInstance();
        flightControler = FlightControler.getInstance();
        passengerInformatiomController = PassengerInformatiomController.getInstance();
        display = Display.getInstance();
    }
    public void viewTicket(boolean bool) {
        List<Ticket> ticket = flightTicketDatabase.getTicketList();
        if (bool == true) {
            long ticketId = ticketIdCheckOrRemove(true, 0);
            for (Ticket ticketIterate : ticket) {
                if (ticketId == ticketIterate.getTicketId()) {
                    display.alert("---------------------------------------------------------------------------\n" +
                            "\nTicket Id:             " + ticketIterate.getTicketId() +
                            "\nTicket Booked Time:    " + dateFormat(ticketIterate.getTicketBookedTime()) +
                            "\nTotal  Passengers:     " + ticketIterate.getTotalPassengers() + "\n\n-->Flight Details<--\n");

                    flightControler.flightView(ticketIterate.getFlightId(), true);
                    passengerInformatiomController.passengerView(ticketId, true);
                    display.alert("\nTotal Ticket Amount     :" + ticketIterate.getTotalTicketAmount() +
                            "\n---------------------------------------------------------------------------\n");
                    return;
                }
            }
        } else {
            for (Ticket ticketIterate : ticket) {
                display.alert("---------------------------------------------------------------------------"
                        + "\nTicket Id:             " + ticketIterate.getTicketId() +
                        "\nTicket Booked Time:    " + dateFormat(ticketIterate.getTicketBookedTime()) +
                        "\nTotal  Passengers:     " + ticketIterate.getTotalPassengers() +
                        "\n-->Flight Details<--");
                flightControler.flightView(0, false);
                passengerInformatiomController.passengerView(0, false);
                display.alert("Total Ticket Amount     :" + ticketIterate.getTotalTicketAmount() +
                        "\n---------------------------------------------------------------------------\n");
            }
        }
    }

    public long ticketIdCheckOrRemove(boolean bool, long ticketId) {
        List<Ticket> ticketList = flightTicketDatabase.getTicketList();
        if (bool == true) {
            while (true) {
                try {
                    System.out.println("Enter Your Ticket ID");
                    ticketId = scanner.nextLong();
                    for (Ticket ticket : ticketList) {
                        if (ticket.getTicketId() == ticketId) return ticketId;
                    }
                    System.out.println("Enter the correct Id");

                } catch (InputMismatchException e) {
                    System.out.println("Input Mismatch");
                }
            }
        } else {
            for (Ticket ticket : ticketList) {
                if (ticket.getTicketId() == ticketId) {
                    ticketList.remove(ticket);
                }
            }
            return 2;
        }
    }

    public void ticketCancel() {
        long ticketId = ticketIdCheckOrRemove(true, 0);
        long flightId = flightIdCheck();
        byte totalPassenger = 0;
        System.out.println("Enter the total Passenger Count");
        totalPassenger = scanner.nextByte();
        if (flightTicketDatabase.getTicketList().get((int) (ticketId - TICKETIDSTART)).getFlightId() == flightId) {

            if (flightTicketDatabase.getTicketList().get((int) (ticketId - TICKETIDSTART)).getTotalPassengers() == totalPassenger) {

                if (ticketTatkalCheck(totalPassenger, flightId)) {
                    ticketIdCheckOrRemove(false, ticketId);
                    flightTicketDatabase.ticketRemoveUpdate(ticketId);
                    System.out.println("Ticket Assign Tatkal Success Fully");
                } else {
                    removeTicket(flightId, ticketId, totalPassenger);
                }
                System.out.println("Ticket Cancel SuccessFully");
                return;
            }
        }
        System.out.println("Ticket not cancel");
    }

    private void removeTicket(long flightId, long ticketId, byte totalPassenger) {
        flightTicketDatabase.getFlightList().get((int) (flightId - FLIGHTIDSTART)).setBookedSeats((short)
                (flightTicketDatabase.getFlightList().get((int) (flightId - FLIGHTIDSTART)).getBookedSeats() - totalPassenger));

        flightTicketDatabase.getFlightList().get((int) (flightId - FLIGHTIDSTART)).setAvailableSeats(
                flightTicketDatabase.getFlightList().get((int) (flightId - FLIGHTIDSTART)).getAvailableSeats() + totalPassenger);

        flightTicketDatabase.getFlightList().get((int) (flightId - FLIGHTIDSTART)).setIsavailable(true);
        ticketIdCheckOrRemove(false, ticketId);
        passengerInformatiomController.passengerRemove(ticketId);
        flightTicketDatabase.ticketRemoveUpdate(ticketId);
    }

    private boolean ticketTatkalCheck(byte totalPassenger, long flightId) {
        List<Ticket> ticketList = flightTicketDatabase.getTicketList();
        for (Ticket ticket : ticketList) {
            if (flightId == ticket.getFlightId() && totalPassenger == ticket.getTotalPassengers()) {
                if (passengerTicketStatusCheck(ticket.getTicketId(), totalPassenger, true)) {
                    return passengerTicketStatusCheck(ticket.getTicketId(), totalPassenger, false);
                }
            }
        }
        return false;
    }

    private boolean passengerTicketStatusCheck(long ticketId, byte totalPasseger, boolean bool) {
        byte count = 0;
        long checkTicketId = 0;
        List<Passenger> passengerList = flightTicketDatabase.getPassengerList();
        if (bool == true) {
            for (Passenger passenger : passengerList) {
                checkTicketId = passenger.getTicketId();
                String checkTicketStatus = passenger.getPassengerTicketStatus();
                while (checkTicketId == ticketId && checkTicketStatus.equalsIgnoreCase("WAITING")) {
                    count++;
                    seatNumberAssign.add(passenger.getSeatno());
                }
                if (count == totalPasseger) break;
                count = 0;
                seatNumberAssign.clear();
            }
            return count == totalPasseger;
        } else {
            count = 0;
            int i = 0;
            for (Passenger passenger : passengerList) {
                checkTicketId = passenger.getTicketId();
                while (checkTicketId == ticketId && totalPasseger != count) {
                    passenger.setPassengerTicketStatus("BOOKED");
                    passenger.setSeatno(seatNumberAssign.get(i++));
                    count++;
                }
            }
            seatNumberAssign.clear();
            return true;
        }
    }

    public long flightIdCheck() {
        long flightId = 0;
        while (true) {
            try {
                System.out.println("Enter Your Fight ID");
                flightId = scanner.nextLong();
                if (flightId >= FLIGHTIDSTART && flightId < flightTicketDatabase.getFlightList().size() + FLIGHTIDSTART) {
                    return flightId;
                } else System.out.println("Enter the Correct Id");
            } catch (InputMismatchException e) {
                System.out.println("Input Mismatch");
            }
        }
    }

    private String dateFormat(long millis) {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(millis));
    }
}
