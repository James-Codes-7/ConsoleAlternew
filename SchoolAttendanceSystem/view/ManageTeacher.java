package schoolattendancealter.View;

import schoolattendancealter.check.CheckList;
import schoolattendancealter.connect.JDBCOperations;
import schoolattendancealter.model.SchoolAttendanceDatabase;
import schoolattendancealter.model.Teacher;

import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ManageTeacher {

    private Scanner scanner =new Scanner(System.in);
    private static ManageTeacher manageTeacher;
    private ManageStudent manageStudent;
    private JDBCOperations jdbcOperations;
    private static  int STUDENTIDSTART=10101;
    private CheckList checkList;
    private SchoolAttendanceDatabase schoolAttendanceDatabase;

    public  void teacherLogin() {
        boolean bool = true;

        //12345
        //hentry123
        int teacherId = 0;
        String teacherPassword = "";
        byte option = 0;
        System.out.println("Enter the Teacher Id");
        f1:
        while (bool) {
            try {
                teacherId = scanner.nextInt();
                while (schoolAttendanceDatabase.getTecherLogin().containsKey(teacherId)) {
                    System.out.println("Enter the Password");
                    teacherPassword = scanner.next();
                    if (schoolAttendanceDatabase.getTecherLogin().get(teacherId).equals(teacherPassword)) {
                        bool = false;
                        break f1;
                    } else System.out.println("Re-Enter Password");
                }
                System.out.println("Enter the Correct teacher Id");
            }catch (InputMismatchException e){
                System.out.println("Enter the correct input format");
                scanner.next();}
        }
        bool=true;
        while(bool)
        {
            try {
                System.out.println("\nView Your Profile           Press 1");
                System.out.println("View Student Attendence     Press 2");
                System.out.println("Put Attendance to Students  Press 3");
                System.out.println("Exit                        Press 4");
                System.out.println("Enter the option");
                option = scanner.nextByte();
                if (option == 4) return;
                switch (option) {
                    case 1:
                        viewTeacherProfile(teacherId);
                        break;
                    case 2:
                        viewStudentAttendance();
                        break;
                    case 3:
                        putAttendance();
                        break;
                    default:
                        System.out.println("Enter the valid one");
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong Input Format");
                scanner.next();}
        }
    }
        private void viewTeacherProfile (int teacherId)
        {
                List<Teacher> teacherList= schoolAttendanceDatabase.getTeacherList();
                for (Teacher teacher:teacherList)
                {
                    if(teacher.getTeacherId()==teacherId)
                    {
                        System.out.println("Teacher Id:    "+teacher.getTeacherId());
                        System.out.println("Teacher Name:  "+teacher.getTeacherName());
                        System.out.println("Your class:    "+teacher.getTeacherSection());
                        System.out.println("Your Dob:      "+teacher.getTeacherDateOfBirth());
                    }
                }


        }
    private void viewStudentAttendance()
    {
        boolean bool=true;
        byte option=0;
        while(bool)
        {
            try {
                System.out.println("View whole attendance                              Press 1");
                System.out.println("View Single Student Attendance                     Press 2");
                System.out.println("View Attendance use Date                           Press 3");
                System.out.println("View attendance use Date and Student ID            Press 4");
                System.out.println("Exit                                               Press 5");
                option = scanner.nextByte();
                if (option == 5) return;
                switch (option) {
                    case 1:
                        wholeAttendance();
                        break;
                    case 2:
                        singleStudentAttendance();
                        break;
                    case 3:
                        viewAttendanceUseDate();
                        break;
                    case 4:viewAttendanceUseDateAndStudentId();
                    default:
                        System.out.println("Enter the correct one");

                }
            }catch (InputMismatchException e)
            {
                System.out.println("Input Mismatch");
                scanner.next();
            }
        }
    }
    private  void viewAttendanceUseDateAndStudentId()
    {
        int studentId=0;
        while(true) {
            try {


                System.out.println("Enter the student Id");
                 studentId = scanner.nextInt();
                if (studentId < schoolAttendanceDatabase.getStudentId().size() + STUDENTIDSTART || studentId
                        > schoolAttendanceDatabase.getStudentId().size() + STUDENTIDSTART) {
                    System.out.println("Wrong stdeunt Id");
                    continue;
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("Input Mismatch");scanner.next();
            }
        }
        System.out.println("Enter the date  this format YYYY-MM-DD");
        boolean bool=true;
        String date="";
        while(bool)
        {
            date= scanner.next();
            if(checkList.dateCheck(date))break;
            else System.out.println("Wrong date Format");
        }
        try {
            ResultSet result = jdbcOperations.retriveQuery("select * from schoolattendance where atdate='" + date + "'");
            System.out.println("\nFull Attendance Sheet\n");
            while (result.next()) {
                if(result.getInt(1)==studentId)
                System.out.println("StudentId:" + result.getInt(1)
                        + " Date:" + result.getString(2) + " Status:" + result.getString(3) +
                        " Total Leaves:" + result.getInt(4)
                        + "  leave Permit:" + result.getInt(5) + "\n");
            }
        }
        catch(Exception e){
            System.out.println("Cannot retireve");}
    }
    private void wholeAttendance()
    {
        try
        {

            ResultSet result=jdbcOperations.retriveQuery("select * from schoolattendance");
            while(result.next())
            {
                System.out.println("StudentId:"+result.getInt(1)
                        +" Date:"+result.getString(2)+" Status:"+result.getString(3)+" " +
                        "Total Leaves:"+result.getInt(4)
                        +"  leave Permit:"+result.getInt(5)+"\n");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void singleStudentAttendance()
    {
        try{
            System.out.println("Enter the student Id");
            int studentId= scanner.nextInt();
            if(studentId<schoolAttendanceDatabase.getStudentId().size()+STUDENTIDSTART||studentId
            >schoolAttendanceDatabase.getStudentId().size()+STUDENTIDSTART)
                System.out.println("Wrong stdeunt Id");
            manageStudent.viewAttendanceHistory(studentId);
        }catch(InputMismatchException e)
        {
            System.out.println("Wrong Input Format");
            scanner.next();
        }
    }
    private void viewAttendanceUseDate()
    {
        System.out.println("Enter the date  this format YYYY-MM-DD");
        boolean bool=true;
        String date="";
        while(bool)
        {
            date= scanner.next();
            if(checkList.dateCheck(date))break;
            else System.out.println("Wrong date Format");
        }
        try {
            ResultSet result = jdbcOperations.retriveQuery("select * from schoolattendance where atdate='" + date + "'");
            System.out.println("\nFull Attendance Sheet\n");
            while (result.next()) {
                System.out.println("StudentId:" + result.getInt(1)
                        + " Date:" + result.getString(2) + " Status:" + result.getString(3) +
                        " Total Leaves:" + result.getInt(4)
                        + "  leave Permit:" + result.getInt(5) + "\n");
            }
        }
        catch(Exception e){
            System.out.println("Cannot retireve");}
    }
    private void putAttendance()
    {
        System.out.println("Enter the date  this format YYYY-MM-DD");
        boolean bool=true;
        while(bool)
        {
            String date= scanner.next();
            if(checkList.dateCheck(date))
            {
                manageStudent.setAttendance(date);
                break;
            }
            else System.out.println("Wrong Format");
        }
    }
    public static ManageTeacher getInstance()
    {
        if(manageTeacher==null)manageTeacher=new ManageTeacher();
        return manageTeacher;
    }

    public ManageTeacher()
    {
         jdbcOperations=JDBCOperations.getInstance();
        checkList=CheckList.getInstance();
        schoolAttendanceDatabase=SchoolAttendanceDatabase.getInstance();
        manageStudent=ManageStudent.getInstance();
    }

}
