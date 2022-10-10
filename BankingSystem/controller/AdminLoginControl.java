package bankingalter.controller;

import java.util.HashMap;

public class AdminLoginControl {
    private static AdminLoginControl adminLoginControl;
    private HashMap<Long,String> adminCheck=new HashMap<>();

    public HashMap<Long, String> getAdminCheck() {
        return adminCheck;
    }

    public void setAdminCheck(HashMap<Long, String> adminCheck) {
        this.adminCheck.putAll(adminCheck);
    }

    public static AdminLoginControl getInstance()
    {
        if(adminLoginControl==null)adminLoginControl=new AdminLoginControl();
        return adminLoginControl;
    }
    public void setAdminCheck(Long adminId, String adminPassword)
    {
        this.adminCheck.putIfAbsent(adminId,adminPassword);
    }
    public  void adminSet()
    {
        this.adminCheck.put(12345l,"james");
    }
}
