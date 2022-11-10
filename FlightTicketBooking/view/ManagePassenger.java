package view;


import controler.TicketBookController;
import controler.TicketControler;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ManagePassenger {
    private static ManagePassenger managePassenger;
    private TicketControler ticketControler;
    private TicketBookController ticketBookController;
    private Scanner scanner = new Scanner(System.in);
    public void passengerView() {
        boolean bool = true;
        byte option = 0;
        int check = 1;
        while (bool) {
            try {
                System.out.println("Ticket Booking                Press 1");
                System.out.println("View Your Ticket              Press 2");
                System.out.println("Cancel Ticket                 Press 3");
                System.out.println("Exit                          Press 4");
                System.out.println("Enter the option ");
                option = scanner.nextByte();
                if (option == 4) return;
                switch (option) {
                    case 1:
                        ticketBookController.bookTicket();
                        check++;
                        break;
                    case 2:
                        if (check != 0)
                            ticketControler.viewTicket(true);
                        break;
                    case 3:
                        if (check != 0) {
                            ticketControler.ticketCancel();
                            check--;
                        }
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
    public ManagePassenger() {
        ticketBookController = TicketBookController.getInstance();
        ticketControler = TicketControler.getInstance();
    }
    public static ManagePassenger getInstance() {
        if (managePassenger == null) managePassenger = new ManagePassenger();
        return managePassenger;
    }
}
