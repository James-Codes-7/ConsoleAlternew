package hotalmanagementalter.view;



import hotalmanagementalter.model.Bill;
import hotalmanagementalter.model.FoodOrder;
import hotalmanagementalter.model.HotalDatabase;

import java.util.List;


public class BillView {

    private static BillView billView;
    public static int conform=0;

    private HotalDatabase hotalDatabase;

    public static BillView getInstance()
    {
        if(billView==null)billView=new BillView();
        return billView;
    }
    public void showBill(int i,int billId)
    {
        if(i==1)
        {
            Bill bill= hotalDatabase.getBill(billId-1);
            System.out.println("Your Bill Id :"+bill.getBillId());
            System.out.println("Your Table   :"+bill.getTableName());
            System.out.println("Date And Time:"+bill.getDate());
            System.out.println();
            foodLineView(bill.getFoodOrders());
            System.out.println("Your Total bill Amount:"+bill.getTotalBillAmount());
            conform=0;
        }
        else
        {
            System.out.println();
            List<Bill> bills=hotalDatabase.getBills();
            for(Bill bill:bills)
            {
                System.out.println("Your Bill Id :"+bill.getBillId());
                System.out.println("Your Table   :"+bill.getTableName());
                System.out.println("Date And Time:"+bill.getDate());
                System.out.println();
                foodLineView(bill.getFoodOrders());
                System.out.println("Your Total bill Amount:"+bill.getTotalBillAmount());
            }
        }
    }
    public void foodLineView(List<FoodOrder> foodorders)
    {
        System.out.println("Food Id         Food Name       Food Rate       foodQuentity       amount");
        for(FoodOrder foods:foodorders)
        {
            System.out.printf(foods.getFoodId()+"\t\t\t"+foods.getFoodName()+
                    "\t\t\t"+foods.getFoodrate()+"\t\t\t\t"+foods.getFoodQuentity()+"\t\t\t\t\t"+foods.getTotalRate()+"\n");
        }
    }

    public BillView()
    {
      hotalDatabase=HotalDatabase.getInstance();
    }
}
