package hotalmanagementalter.model;

public class FoodOrder {
   private int foodId;
    private String foodName;
    private  int foodrate;
    private  int foodQuentity;
    private  int totalRate;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodrate() {
        return foodrate;
    }

    public void setFoodrate(int foodrate) {
        this.foodrate = foodrate;
    }

    public int getFoodQuentity() {
        return foodQuentity;
    }

    public void setFoodQuentity(int foodQuentity) {
        this.foodQuentity = foodQuentity;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }
}
