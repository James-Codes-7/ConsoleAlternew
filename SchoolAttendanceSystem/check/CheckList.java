package schoolattendancealter.check;

import java.util.regex.Pattern;

public class CheckList {

    private static CheckList checkList;
    public static CheckList getInstance()
    {
        if(checkList==null)checkList=new CheckList();
        return checkList;
    }
    public boolean dateCheck(String date)
    {
        return Pattern.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}", date);
    }
}
