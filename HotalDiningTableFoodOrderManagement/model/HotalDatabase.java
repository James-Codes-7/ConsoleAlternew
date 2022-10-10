package hotalmanagementalter.model;

import java.util.ArrayList;
import java.util.List;

public class HotalDatabase {

   private static HotalDatabase hotalDatabase;

    private List<Bill>  bills;
    private List<Food>  foodList;

    public List<Bill> getBills() {
        return bills;
    }
    public Bill getBill(int billId){return this.bills.get(billId);}

    public void setBills(List<Bill> bills) {
        this.bills.addAll(bills);
    }
    public void setBills(Bill bill)
    {
        this.bills.add(bill);
    }

    public List<Food> getFoodList() {
        return foodList;
    }
    public Food getFood(int foodId)
    {
        return this.foodList.get(foodId);
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList.addAll(foodList);
    }
    public void setFoodList(Food food)
    {
        this.foodList.add(food);
    }

    public static HotalDatabase getInstance()
    {
        if(hotalDatabase==null)hotalDatabase=new HotalDatabase();
        return hotalDatabase;
    }
    public HotalDatabase()
    {
        bills=new ArrayList<>();
        foodList=new ArrayList<>();
    }
}
