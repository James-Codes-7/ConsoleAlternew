package hotalmanagementalter.check;


import hotalmanagementalter.model.HotalDatabase;

import java.util.ArrayList;

public class CheckList {

    private static CheckList checkList;
    private static int FOODIDSTART=1001;
    private HotalDatabase hotalDatabase;
    public   static  CheckList getInstance()
    {
        if(checkList==null)checkList=new CheckList();
        return checkList;
    }
    public boolean   foodQuentityCheck( int foodQuanity, int foodId)
    {
        return hotalDatabase.getFood(foodId-FOODIDSTART).getQuantity()>=foodQuanity;

    }
    public CheckList()
    {
     hotalDatabase=HotalDatabase.getInstance();
    }
}
