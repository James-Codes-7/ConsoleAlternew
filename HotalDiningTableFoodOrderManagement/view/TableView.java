package hotalmanagementalter.view;


import hotalmanagementalter.check.CheckList;
import hotalmanagementalter.model.Bill;
import hotalmanagementalter.model.Food;
import hotalmanagementalter.model.FoodOrder;
import hotalmanagementalter.model.HotalDatabase;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TableView {
    int conform=0;
    private BillView billView;
    private  static TableView tableView;
    private int BillId=1;
    private int foodIdStart=1001;
    private HotalDatabase hotalDatabase;
    private CheckList checkList;
    private  static int totalBillAmount=0;
    public void tableSelect( String tables)
    {
        tableOrderDetails(tables);

    }
    private void tableOrderDetails(String table)
    {
        System.out.println("View  Food  Details");
        boolean bool=true;
        int option=0;

        Scanner scan=new Scanner(System.in);
        List<FoodOrder> foodOrder=null;;
        while(bool)
        {
            try {
                System.out.println();
                System.out.println("Food Order    Press 1");
                System.out.println("Pay Bill      Press 2");
                System.out.println("Exit          Press 3");
                option = scan.nextByte();
                switch (option) {
                    case 1:
                        foodOrder = foodOrder();
                        break;
                    case 2:
                        if (conform > 0) {
                            payBill(table, foodOrder);
                            conform = 0;
                            System.out.println();
                        } else System.out.println("Order First");
                        break;
                    case 3:
                        if (conform != 0)
                            System.out.println("Please Pay the Bill");else return;

                }
            }catch (InputMismatchException e){
                System.out.println("Input Mismatch");scan.next();}
        }
    }
    private void payBill(String table,List<FoodOrder> foodOrder)
    {
        Bill bill=new Bill();
        bill.setBillId(BillId);bill.setTableName(table);
        bill.setTotalBillAmount(totalBillAmount);
        bill.setDate(java.time.LocalDateTime.now());
        bill.setFoodOrders(foodOrder);
        hotalDatabase.setBills(bill);
        totalBillAmount=0;
        System.out.println("Your Bill");
       billView.showBill(1,BillId++);
        conform=0;

    }
    private List<FoodOrder> foodOrder()
    {
        Scanner scan=new Scanner(System.in);
        int foodId=0,foodQuanity=0;
        boolean  bool=true;
        System.out.println();

        viewFoodDetails();
        byte option=1;
        ArrayList<FoodOrder> foodOrders=new ArrayList<>();

        while(bool)
        {
            try {
                System.out.println("Enter the food Id");
                foodId = scan.nextInt();
                List<Food> foodList = hotalDatabase.getFoodList();

                if (foodId < foodIdStart || foodId > foodList.size() + (foodIdStart - 1)) {
                    System.out.println("Wrong "
                            + "food id");
                    continue;
                }
                System.out.println("Enter the Food Quantity");
                foodQuanity = scan.nextInt();
                if (!checkList.foodQuentityCheck(foodQuanity, foodId)) {
                    System.out.println("Actual food quantity Less than"
                            + "Your order quantity Sorry");
                    continue;
                }
                Food order = hotalDatabase.getFood(foodId - foodIdStart);
                FoodOrder foodOrder = new FoodOrder();
                foodOrder.setFoodId(order.getFoodId());foodOrder.setFoodName(order.getFoodName());
                foodOrder.setFoodrate(order.getRate()); foodOrder.setFoodQuentity(foodQuanity);
                foodOrder.setTotalRate(foodQuanity * order.getRate());
                foodOrders.add(foodOrder);
                order.setQuantity(order.getQuantity() - foodQuanity);
                totalBillAmount += foodQuanity * order.getRate();

                System.out.println("Continue Order Press 1");
                option = scan.nextByte();
                if (option == 1) continue;
                else break;
            }catch (InputMismatchException e){
                System.out.println("Input Mismatch");scan.next();}
        }
        System.out.println("Order SuccessFull");

        System.out.println("Get Your Food");
        conform++;
        return foodOrders;

    }
    public TableView()
    {
        hotalDatabase=HotalDatabase.getInstance();
        checkList=CheckList.getInstance();
        billView=BillView.getInstance();
    }
    public static TableView getInstance()
    {
        if(tableView==null)tableView=new TableView();
        return tableView;
    }
    public void viewFoodDetails()
    {
        System.out.println();
        System.out.println("Food Id         Food Name       Food Rate       foodQuentity");
        List<Food> foodList=hotalDatabase.getFoodList();
        for(Food foods:foodList)
        {
            if(foods.getQuantity()!=0)
            System.out.printf(foods.getFoodId()+"\t\t\t"+foods.getFoodName()+
                    "\t\t\t"+foods.getRate()+"\t\t\t\t"+foods.getQuantity()+"\n");
        }
    }
}
