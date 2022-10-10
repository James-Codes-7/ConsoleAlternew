package bankingalter.controller;

import java.util.HashMap;

public class UserLoginControl {

         private static  UserLoginControl userLoginControl;
    public HashMap<Long, String> getLoginCheck() {
        return loginCheck;
    }

    public void setLoginCheck(HashMap<Long, String> loginCheck) {
        this.loginCheck.putAll(loginCheck);
    }
    public void setLoginCheck(long accountNo,String password)
    {
        this.loginCheck.put(accountNo,password);
    }

    public HashMap<Long, String> getPasswordChange() {
        return passwordChange;
    }

    public void setPasswordChange(HashMap<Long, String> passwordChange) {
        this.passwordChange.putAll(passwordChange);
    }

    private HashMap<Long,String> loginCheck=new HashMap<>();
    private  HashMap<Long,String> passwordChange=new HashMap<>();

    public static  UserLoginControl getInstance()
    {
        if(userLoginControl==null)userLoginControl=new UserLoginControl();
        return userLoginControl;
    }
}
