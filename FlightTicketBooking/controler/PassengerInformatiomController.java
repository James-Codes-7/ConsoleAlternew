package controler;

import model.FlightTicketDatabase;
import model.Passenger;
import util.PassengerUtil;
import view.Display;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PassengerInformatiomController {
    private Display display;
    private static PassengerInformatiomController ticketBookController;
    private FlightTicketDatabase flightTicketDatabase;
    private PassengerUtil passengerUtil;
    private boolean bool = true;
    private byte option = 0;
    private Scanner scanner = new Scanner(System.in);

    public static PassengerInformatiomController getInstance() {
        if (ticketBookController == null) ticketBookController = new PassengerInformatiomController();
        return ticketBookController;
    }

    public PassengerInformatiomController() {
        passengerUtil = PassengerUtil.getInstance();
        flightTicketDatabase = FlightTicketDatabase.getInstance();
        display = Display.getInstance();
    }

    public String nameFill() {
        String name = "";
        display.alert("\nEnter the Passenger Name\n" +
                "The name must be the first letter is capital and only use alphabet");
        while (bool) {
            try {
                name = scanner.nextLine();
                if (passengerUtil.nameCheck(name)) {
                    display.alert("Your Name:" + name +
                            "\nIf you want change name  Press 1 else 0");
                    option = scanner.nextByte();
                    if (option == 1) {
                        display.alert("Re -Enter name");
                        continue;
                    }
                    break;
                } else display.alert("Enter the correct Format");
            } catch (InputMismatchException e) {
                display.alert("Input mismatch");
                scanner.next();
            }
        }
        return name;
    }

    public String dateOfBirthFill() {
        String dateOfBirth = "";
        display.alert("\nEnter the Passenger DOB" +
                "\nThe dateOfBirth must be this Format  YYYY-MM-DD");
        while (bool) {
            try {
                dateOfBirth = scanner.next();
                if (passengerUtil.dateOfBirthCheck(dateOfBirth)) {
                    display.alert("Your DOB:" + dateOfBirth +
                            "\nIf you want change dateOfBirth  Press 1 else 0");
                    option = scanner.nextByte();
                    if (option == 1) {
                        display.alert("Re -Enter dateOfBirth");
                        continue;
                    }
                    break;
                } else display.alert("Enter the correct Format");
            } catch (InputMismatchException e) {
                display.alert("Input mismatch");
                scanner.next();
            }
        }
        return dateOfBirth;
    }

    public String emailIdFill() {
        String emailIDCheck = "";
        display.alert("\nEnter the Passenger EmailId" +
                "\nThe emailIDCheck must be this Format ajjsh288@mail.com");
        while (bool) {
            try {
                emailIDCheck = scanner.next();
                if (passengerUtil.emailIdCheck(emailIDCheck)) {
                    display.alert("Your emailId:" + emailIDCheck +
                            "\nIf you want change emailIDCheck  Press 1 else 0");
                    option = scanner.nextByte();
                    if (option == 1) {
                        display.alert("Re -Enter emailIDCheck");
                        continue;
                    }
                    break;
                } else display.alert("Enter the correct Format");
            } catch (InputMismatchException e) {
                display.alert("Input mismatch");
                scanner.next();
            }
        }
        return emailIDCheck;
    }

    public String addressFill() {
        String address = "";
        display.alert("\nEnter the Passenger Address");
        scanner.nextLine();
        while (bool) {
            try {
                address = scanner.nextLine();
                display.alert("Your address:" + address +
                        "\nIf you want change address  Press 1 else 0");
                option = scanner.nextByte();
                if (option == 1) {
                    display.alert("Re -Enter address");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                display.alert("Input mismatch");
                scanner.next();
            }
        }
        return address;
    }

    public long aadharFill() {
        long aadharNumber = 0;
        display.alert("\nEnter the Passenger aadhar" +
                "\nThe aadharNumber must be 12 digits");
        while (bool) {
            try {
                aadharNumber = scanner.nextLong();
                if (passengerUtil.aadharNumberCheck(aadharNumber)) {
                    display.alert("Your aadharNumber:" + aadharNumber +
                            "\nIf you want change aadharNumber  Press 1 else 0");
                    option = scanner.nextByte();
                    if (option == 1) {
                        display.alert("Re -Enter aadharNumber");
                        continue;
                    }
                    break;
                } else display.alert("Enter the correct format");
            } catch (InputMismatchException e) {
                display.alert("Input mismatch");
                scanner.next();
            }
        }
        return aadharNumber;
    }

    public long mobileNumberFill() {
        long mobileNumber = 0;
        display.alert("\nEnter the Passenger Mobile number" +
                "\nThe mobileNumber must be 10 digits");
        while (bool) {
            try {
                mobileNumber = scanner.nextLong();
                if (passengerUtil.mobileNumberCheck(mobileNumber)) {
                    display.alert("Your mobileNumber:" + mobileNumber +
                            "\nIf you want change mobileNumber  Press 1 else 0");
                    option = scanner.nextByte();
                    if (option == 1) {
                        display.alert("Re -Enter mobileNumber");
                        continue;
                    }
                    break;
                } else display.alert("Enter the correct format");
            } catch (InputMismatchException e) {
                display.alert("Input mismatch");
                scanner.next();
            }
        }
        return mobileNumber;
    }

    public short ageFill() {
        short age = 0;
        display.alert("\nEnter the Passenger age" +
                "\nThe age must be 10 digits");
        while (bool) {
            try {
                age = scanner.nextShort();
                display.alert("Your age:" + age +
                        "\nIf you want change age  Press 1 else 0");
                option = scanner.nextByte();
                if (option == 1) {
                    display.alert("Re -Enter age");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                display.alert("Input mismatch");
                scanner.next();
            }
        }
        return age;
    }

    public void passengerView(long ticketId, boolean bool) {
        List<Passenger> passengerList = flightTicketDatabase.getPassengerList();
        if (bool == true) {
            for (Passenger passenger : passengerList) {
                if (passenger.getTicketId() == ticketId) {
                    display.alert("\nPassenger Name:               " + passenger.getPassengerName() +
                            "\nPassenger Aadhar:             " + passenger.getPassengerAadharNumber() +
                            "\nPassenger MobileNo:           " + passenger.getPassengerPhoneNumber() +
                            "\nPassenger DOB:                " + passenger.getPassengerDateOfBirth() +
                            "\nPassenger Age:                " + passenger.getPassengerAge() +
                            "\nPassenger Email Id:           " + passenger.getPassengerEmailId() +
                            "\nPassenger Ticket Status:      " + passenger.getPassengerTicketStatus() +
                            "\nPassenger Address             " + passenger.getPassengerAddress() +
                            "\nPassenger Ticketno:           " + passenger.getSeatno());
                }
            }
        } else {
            for (Passenger passenger : passengerList) {
                display.alert("\nPassenger Name:               " + passenger.getPassengerName() +
                        "\nPassenger Aadhar:             " + passenger.getPassengerAadharNumber() +
                        "\nPassenger MobileNo:           " + passenger.getPassengerPhoneNumber() +
                        "\nPassenger DOB:                " + passenger.getPassengerDateOfBirth() +
                        "\nPassenger Age:                " + passenger.getPassengerAge() +
                        "\nPassenger Email Id:           " + passenger.getPassengerEmailId() +
                        "\nPassenger Ticket Status:      " + passenger.getPassengerTicketStatus() +
                        "\nPassenger Address             " + passenger.getPassengerAddress() +
                        "Passenger Ticketno:           " + passenger.getSeatno());

            }
        }
    }

    public void passengerRemove(long ticketId) {
        List<Passenger> passengerList = flightTicketDatabase.getPassengerList();
        for (int i = 0; i < passengerList.size(); i++) {
            if (passengerList.get(i).getTicketId() == ticketId) {
                passengerList.remove(i);
            }
        }
    }
}
