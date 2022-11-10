package view;

import controler.FlightTicketSetup;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FlightTicket {
    private Scanner scanner = new Scanner(System.in);
    private ManagePassenger managePassenger;
    private FlightTicketSetup flightTicketSetup;
    private ManageAdmin manageAdmin;
    public static void main(String[] args) {
        FlightTicket flightTicket = new FlightTicket();
        flightTicket.ticketPage();
    }
    private void ticketPage() {
        System.out.println("-->Welcome to Flight Ticket Booking<--");
        flightTicketSetup.flightTicketSetup();
        boolean bool = true;
        byte option = 0;
        while (bool) {
            try {
                System.out.println("\nAdmin            Press 1");
                System.out.println("Passenger        Press 2");
                System.out.println("Exit             Press 3");
                System.out.println("Enter the option");
                option = scanner.nextByte();
                if (option == 3) return;
                switch (option) {
                    case 1:
                        manageAdmin.adminView();
                        break;
                    case 2:
                        managePassenger.passengerView();
                        break;
                    default:
                        System.out.println("Enter the valid one");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input Mismatch");
                scanner.next();
            }
        }
    }
    public FlightTicket() {
        managePassenger = ManagePassenger.getInstance();
        manageAdmin = ManageAdmin.getInstance();
        flightTicketSetup = FlightTicketSetup.getInstance();
    }
}
