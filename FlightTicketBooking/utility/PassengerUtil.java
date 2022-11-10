package util;

import java.util.regex.Pattern;

public class PassengerUtil {
    private static PassengerUtil passengerUtil;
    public static PassengerUtil getInstance() {
        if (passengerUtil == null) passengerUtil = new PassengerUtil();
        return passengerUtil;
    }
    public boolean nameCheck(String passengerName) {
        return Pattern.matches("[A-Z][a-zA-z/ ]{2,}", passengerName);
    }
    public boolean dateOfBirthCheck(String DOB) {
        return Pattern.matches("[0-9]{4}[/-][0-1][0-9][/-][0-3][0-9]", DOB);
    }
    public boolean emailIdCheck(String emailId) {
        return Pattern.matches("^(.+)@(.+)$", emailId);
    }
    public boolean aadharNumberCheck(Long aadharNum) {
        return Pattern.matches("[0-9]{12}", aadharNum.toString());
    }
    public boolean mobileNumberCheck(Long mobilenum) {
        return Pattern.matches("[0-9]{10}", mobilenum.toString());
    }
}
