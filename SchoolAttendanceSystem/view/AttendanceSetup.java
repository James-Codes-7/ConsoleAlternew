package schoolattendancealter.View;


import schoolattendancealter.connect.JDBCOperations;
import schoolattendancealter.model.LeaveApply;
import schoolattendancealter.model.SchoolAttendanceDatabase;
import schoolattendancealter.model.Student;
import schoolattendancealter.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;



public class AttendanceSetup {

    private SchoolAttendanceDatabase schoolAttendanceDatabase;
    private static AttendanceSetup attendanceSetup;
    public static AttendanceSetup getInstance()
    {
        if(attendanceSetup==null)attendanceSetup=new AttendanceSetup();
        return attendanceSetup;
    }
    private JDBCOperations jdbcOperations;
    public  void studentStart()
    {
        try
        {
            ResultSet result=jdbcOperations.retriveQuery("select * from schoolstudent");
            while(result.next())
            {
                schoolAttendanceDatabase.setStudentLogin(result.getInt(1),result.getString(4));
                schoolAttendanceDatabase.setStudentId(result.getInt(1));
                Student student =new Student();
                student.setStudentId(result.getInt(1));
                student.setStudentName(result.getString(2));
                student.setDateOfBirth(result.getString(3));
                student.setStudentLoginPassword(result.getString(4));
                schoolAttendanceDatabase.setStudentList(student);
            }

            result=jdbcOperations.retriveQuery("select * from schoolattendance");
            while(result.next())
            {
                LeaveApply leaveApply=new LeaveApply();
                leaveApply.setStudentid(result.getInt(1));
                leaveApply.setTotalLeave(result.getInt(4));
                leaveApply.setRemainingLeave(result.getInt(5));
                schoolAttendanceDatabase.setLeaveApplyList(leaveApply);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void teacherStart()
    {


        ResultSet result=null;
        try {
            result = jdbcOperations.retriveQuery("select * from teacherDetails");
            while (result.next()) {
                schoolAttendanceDatabase.setTecherLogin(result.getInt(1),result.getString(5));
                Teacher teacher=new Teacher();
                teacher.setTeacherId(result.getInt(1));
                teacher.setTeacherName(result.getString(2));
                teacher.setTeacherSection(result.getString(3));
                teacher.setTeacherDateOfBirth(result.getString(4));
                schoolAttendanceDatabase.setTeacherList(teacher);
            }
            result.close();
        }catch (SQLException e)
        {
            System.out.println("Cannot setup ");
        }

    }
    public  AttendanceSetup()
    {
       jdbcOperations=JDBCOperations.getInstance();
       schoolAttendanceDatabase=SchoolAttendanceDatabase.getInstance();
    }
}
