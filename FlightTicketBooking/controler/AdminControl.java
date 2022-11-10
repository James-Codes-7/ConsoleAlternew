package controler;

import java.util.HashMap;

public class AdminControl {
    private HashMap<Long, String> adminCheck;
    private static AdminControl adminControl;

    public static AdminControl getInstance() {
        if (adminControl == null) adminControl = new AdminControl();
        return adminControl;
    }

    public void adminSetup() {
        adminCheck.put(12345l, "admin");
    }

    public boolean adminIdCheck(long adminId) {
        return adminCheck.containsKey(adminId);
    }

    public boolean adminPasswordCheck(long adminId, String password) {
        return adminCheck.get(adminId).equals(password);
    }

    public AdminControl() {
        adminCheck = new HashMap<>();
    }
}
