package schoolattendancealter.View;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SchoolAttendanceView {

    private Scanner scanner=new Scanner(System.in);
    private ManageStudent manageStudent;
    private  ManageTeacher manageTeacher;

    public static void main(String[] args) {
        SchoolAttendanceView schoolAttendanceView=new SchoolAttendanceView();
        schoolAttendanceView.attendancePage();
    }
    private  void attendancePage()
    {
        System.out.println("Welcome To School Attendance System");
        System.out.println();
        boolean bool=true;
        byte option=0;
        while(bool)
        {
            try {
                System.out.println("Teacher     Press 1");
                System.out.println("Student     Press 2");
                System.out.println("Exit        Press 3");
                System.out.println("Enter the option");
                option = scanner.nextByte();
                if (option >= 3) {
                    bool = false;
                    break;
                }
                switch (option) {

                    case 1:
                        manageTeacher.teacherLogin();
                        break;
                    case 2:
                        manageStudent.studentLogin();
                        break;
                    default:
                        System.out.println("Enter the correct one");
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong input");
                scanner.next();}

        }

    }
    public  SchoolAttendanceView()
    {
        manageStudent=ManageStudent.getInstance();
        manageTeacher=ManageTeacher.getInstance();
        AttendanceSetup.getInstance().studentStart();
        AttendanceSetup.getInstance().teacherStart();
    }
}
