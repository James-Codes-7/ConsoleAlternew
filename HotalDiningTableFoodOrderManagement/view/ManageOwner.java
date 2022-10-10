package hotalmanagementalter.view;

import hotalmanagementalter.model.Food;
import hotalmanagementalter.model.HotalDatabase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageOwner {

    private static ManageOwner manageOwner;
    private HotalDatabase hotalDatabase;
    private TableView tableView;
    private String ownerPassword;
    private Scanner scanner=new Scanner(System.in);
    private int foodIdStart=1001;
    private BillView billView;

    public  static ManageOwner getInstance()
    {
        if(manageOwner==null)manageOwner=new ManageOwner();
        return manageOwner;
    }
    public void OwnerView() {


        ownersSetup();
        System.out.println("Enter the Password");
        boolean bool=true;
        while(bool)
        {
            String password=scanner.next();
            if(password.equals(ownerPassword))break;
            else System.out.println("Enter the correct password");
        }

        bool=true;
        byte option=0;
        while(bool)
        {
            try {

                System.out.println("\nView Bills            Press 1");
                System.out.println("View Food Details     Press 2");
                System.out.println("Add Food              Press 3");
                System.out.println("Exit                  Press 4");
                option = scanner.nextByte();
                if (option == 4) {
                    bool = false;
                    break;
                }
                switch (option) {
                    case 1:
                        billView.showBill(2, 1);
                        break;
                    case 2:
                        tableView.viewFoodDetails();
                        break;
                    case 3:
                        foodAdd();
                        break;
                    default:
                        System.out.println("Enter the coorect one");
                }
            }catch (InputMismatchException e)
            {
                System.out.println("Input Mismatch ");
                scanner.next();
            }

        }

    }
    private void foodAdd()
    {
        System.out.println("Enter the food Name");
        scanner.nextLine();
        String foodName=scanner.nextLine();
        int foodQuentity=0;
        int foodRate=0;
        while(true) {
            try {
                System.out.println("Enter the food Quentity");
                foodQuentity = scanner.nextInt();
                System.out.println("Enter the Food Rate");
                foodRate = scanner.nextInt();
                break;
            }catch (InputMismatchException e)
            {
                System.out.println("Input Mismatch");
                scanner.next();
            }
        }
            Food food = new Food();
            food.setFoodName(foodName);
            food.setFoodId(foodIdStart + hotalDatabase.getFoodList().size());
            food.setQuantity(foodQuentity);
            food.setRate(foodRate);
            hotalDatabase.setFoodList(food);
            System.out.println("Food Added Successfully");

    }
    public ManageOwner()
    {
        billView=BillView.getInstance();
        tableView=TableView.getInstance();
        hotalDatabase=HotalDatabase.getInstance();

    }
    public void ownersSetup()
    {
        ownerPassword="hentry123";
    }

}
