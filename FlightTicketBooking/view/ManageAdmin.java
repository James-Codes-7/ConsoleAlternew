package view;

import controler.AdminControl;
import controler.FlightControler;
import controler.PassengerInformatiomController;
import controler.TicketControler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageAdmin {
    private static ManageAdmin manageAdmin;
    private AdminControl adminControl;
    private FlightControler flightControler;
    private PassengerInformatiomController passengerInformatiomController;
    private TicketControler ticketControler;
    private Scanner scanner = new Scanner(System.in);
    public static ManageAdmin getInstance() {
        if (manageAdmin == null) manageAdmin = new ManageAdmin();
        return manageAdmin;
    }
    public ManageAdmin() {
        flightControler = FlightControler.getInstance();
        passengerInformatiomController = PassengerInformatiomController.getInstance();
        ticketControler = TicketControler.getInstance();
        adminControl = AdminControl.getInstance();
    }
    public void adminView() {
        adminControl.adminSetup();
        String password = "";
        System.out.println("Enter the admin Id");
        label:
        while (true) {
            long adminId = scanner.nextLong();
            while (adminControl.adminIdCheck(adminId)) {
                System.out.println("Enter the Password");
                password = scanner.next();
                if (adminControl.adminPasswordCheck(adminId, password)) {
                    System.out.println("-->Welcome Admin<--");
                    break label;
                } else System.out.println("Enter the correct Password");
            }
            System.out.println("Enter the correct Admin Id");
        }
        boolean bool = true;
        byte option = 0;
        while (bool) {
            try {
                System.out.println("View Flight Details        Press 1");
                System.out.println("View Passenger Details     Press 2");
                System.out.println("View Ticket Detail         Press 3");
                System.out.println("Exit                       Press 4");
                System.out.println("Enter the option");
                option = scanner.nextByte();
                if (option == 4) return;
                switch (option) {
                    case 1:
                        flightControler.flightView(0, false);
                        break;
                    case 2:
                        passengerInformatiomController.passengerView(0, false);
                        break;
                    case 3:
                        ticketControler.viewTicket(false);
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
}
