package schoolattendancealter.View;

import schoolattendancealter.connect.JDBCOperations;
import schoolattendancealter.model.LeaveApply;
import schoolattendancealter.model.SchoolAttendanceDatabase;
import schoolattendancealter.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManageStudent {

    private static ManageStudent manageStudent;
    private final int STUDENTIDSTART=10101;
    private SchoolAttendanceDatabase schoolAttendanceDatabase;
    private JDBCOperations jdbcOperations;
    private Scanner scanner=new Scanner(System.in);

    public static ManageStudent getInstance(){
        if(manageStudent==null)manageStudent=new ManageStudent();
        return manageStudent;
    }
    public  void studentLogin()
    {
        boolean bool =true;
        byte option=0;
        int studentId=0;
        String password="";
        f1:while(bool)
        {
             try {
                 System.out.println("Enter the student id");
                 studentId = scanner.nextInt();
                 while (schoolAttendanceDatabase.getStudentLogin().containsKey(studentId)) {
                     System.out.println("Enter the password");
                     password = scanner.next();
                     if (schoolAttendanceDatabase.getStudentLogin().get(studentId).equals(password)) {
                         System.out.println("Student Login successfully");
                         bool = false;
                         break f1;
                     } else System.out.println("Enter the corect password");

                 }
                 System.out.println("Enter the correct id");
             }catch (InputMismatchException e)
             {
                 System.out.println("Enter the number Integer format");scanner.next();
             }
        }
        bool=true;
        while(bool)
        {
            try {
                System.out.println();
                System.out.println("View Your Profile             Press 1");
                System.out.println("View attendance History       Press 2");
                System.out.println("Leave Apply                   Press 3");
                System.out.println("Exit                          Press 4");
                option = scanner.nextByte();
                if (option == 4) return;
                switch (option) {
                    case 1:
                        viewProfile(studentId);
                        break;
                    case 2:
                        viewAttendanceHistory(studentId);
                        break;
                    case 3:
                        leaveApply(studentId);
                        break;
                }
                System.out.println();
            }catch (InputMismatchException e){
                System.out.println("Wrong input format");scanner.next();}
        }
    }
    public void setAttendance(String date)
    {

            int size=schoolAttendanceDatabase.getStudentId().size();
            String attendanceStatus[]=new String[size];
            byte option=0;

            List<Integer> studentId =schoolAttendanceDatabase.getStudentId();
            for( int i=0;i<size;i++)
            {
                try {
                    System.out.println("The ID Number:" + studentId.get(i) + " if present Press 1 else 0");
                    option = scanner.nextByte();
                    if (option == 1) {
                        attendanceStatus[i] = "Present";
                    } else attendanceStatus[i] = "Absent";
                    int count = jdbcOperations.updateQuery("insert into schoolattendance(enrollid,atdate,Astatus)"
                            + "  values(" + studentId.get(i) + ",'" + date + "','" + attendanceStatus[i] + "')");
                }catch (InputMismatchException e){
                    System.out.println("Enter the input Byte Format");
                    i--;
                    scanner.next();

                }
            }

        System.out.println("Success Fully Completed");

    }
    private void leaveApply(int studentId)
    {
        while(true) {
            try {
                System.out.println("Enter how many days you want");
                int total = scanner.nextInt();

                LeaveApply checkLeave = schoolAttendanceDatabase.getLeaveApplyList().get(studentId - STUDENTIDSTART);

                if (checkLeave.getStudentid() != studentId) {
                    System.out.println("Enter the corect");
                    return;
                }

                if ((checkLeave.getTotalLeave() + total) <= checkLeave.getRemainingLeave()) {

                    int result = jdbcOperations.updateQuery("update schoolattendance set totalleaves=" + checkLeave.getTotalLeave() + total + " where enrollid=" + studentId);
                    checkLeave.setTotalLeave(checkLeave.getTotalLeave() + total);
                    System.out.println("Permission granted\n");
                } else {
                    System.out.println("Already your leave compleated");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch");
                scanner.next();
            }catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Array Index Out Of Bound");
                scanner.next();
            }
        }

    }
    public  void viewAttendanceHistory(int studentId)
    {
        try
        {
            System.out.println();
            ResultSet result= jdbcOperations.retriveQuery("select * from schoolattendance where enrollid="+studentId);
            while(result.next())
            {
                System.out.println("StudentId:"+result.getInt(1)
                        +" Date:"+result.getString(2)+" Status:"+result.getString(3)+" Total Leaves:" +
                        ""+result.getInt(4)
                        +" leave Permit:"+result.getInt(5)+"\n");
            }
        }
        catch(SQLException e)
        {
            System.out.println("Cannot retrieve");
        }

    }
    private  void viewProfile(int studentId)
    {

            List<Student> studentList=schoolAttendanceDatabase.getStudentList();

            for (Student student:studentList) {
                if(student.getStudentId()==studentId) {
                    System.out.println("Student Id:   " +student.getStudentId());
                    System.out.println("Student Name: " + student.getStudentName());
                    System.out.println("Student Dob:  " + student.getDateOfBirth()+"\n");
                    return;
                }
            }
        System.out.println("Enter the correct student Id");


    }
    public ManageStudent()
    {
        schoolAttendanceDatabase=SchoolAttendanceDatabase.getInstance();
        jdbcOperations=JDBCOperations.getInstance();
    }
}
