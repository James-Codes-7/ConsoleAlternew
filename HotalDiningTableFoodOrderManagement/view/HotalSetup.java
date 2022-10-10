package hotalmanagementalter.view;;
import hotalmanagementalter.model.Food;
import hotalmanagementalter.model.HotalDatabase;

import java.util.ArrayList;
import java.util.List;

public class HotalSetup {

    private static HotalSetup hotalSetup;
    private HotalDatabase hotalDatabase;

    public void hotalSetup()
    {
        List<Food> foods=new ArrayList<>();
        Food food =new Food();

        food.setFoodId(1001);food.setQuantity(50);food.setFoodName("Idly");food.setRate(10);
        foods.add(food);

        Food food1 =new Food();
        food1.setFoodId(1002);food1.setQuantity(50);food1.setFoodName("Dhosa");food1.setRate(15);
        foods.add(food1);

        Food food2 =new Food();
        food2.setFoodId(1003);food2.setQuantity(50);food2.setFoodName("Poori");food2.setRate(5);
        foods.add(food2);

        Food food3 =new Food();
        food3.setFoodId(1004);food3.setQuantity(50);food3.setFoodName("Sapathi");food3.setRate(15);
        foods.add(food3);

        Food food4 =new Food();
        food4.setFoodId(1005);food4.setQuantity(50);food4.setFoodName("Briyani");food4.setRate(120);
        foods.add(food4);
        hotalDatabase.setFoodList(foods);
        System.out.println("Hotel SetUp SuccessFully");
    }
    public static HotalSetup getInstance()
    {
        if(hotalSetup==null)hotalSetup=new HotalSetup();
        return hotalSetup;
    }
    public HotalSetup()
    {
        hotalDatabase=HotalDatabase.getInstance();
    }
}
