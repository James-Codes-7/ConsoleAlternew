package schoolattendancealter.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SchoolAttendanceDatabase {

    private static SchoolAttendanceDatabase schoolAttendanceDatabase;

    private HashMap<Integer,String> studentLogin;
    private HashMap<Integer,String> techerLogin;
    private List<LeaveApply> leaveApplyList;
    private List<Student> studentList;
    private List<Teacher> teacherList;

    List<Integer> studentId ;

    public List<Integer> getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId.add(studentId);
    }

    public HashMap<Integer, String> getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(Integer studentId, String studentPassword) {
        this.studentLogin.putIfAbsent(studentId,studentPassword);
    }

    public HashMap<Integer, String> getTecherLogin() {
        return techerLogin;
    }

    public void setTecherLogin(Integer teacherId, String  teacherPassword) {
        this.techerLogin.putIfAbsent(teacherId,teacherPassword);
    }



    public List<LeaveApply> getLeaveApplyList() {
        return leaveApplyList;
    }

    public void setLeaveApplyList(List<LeaveApply> leaveApplyList) {
        this.leaveApplyList.addAll(leaveApplyList);
    }
    public void setLeaveApplyList(LeaveApply leaveApply)
    {
        this.leaveApplyList.add(leaveApply);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList.addAll(studentList);
    }
    public void setStudentList(Student student)
    {
        this.studentList.add(student);
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList.addAll(teacherList);
    }
    public void setTeacherList(Teacher teacher)
    {
        this.teacherList.add(teacher);
    }

    public static SchoolAttendanceDatabase getInstance()
   {
       if(schoolAttendanceDatabase==null)schoolAttendanceDatabase=new SchoolAttendanceDatabase();
       return  schoolAttendanceDatabase;
   }
   public SchoolAttendanceDatabase()
   {
       leaveApplyList=new ArrayList<>();
       studentList=new ArrayList<>();
       teacherList=new ArrayList<>();
       studentLogin=new HashMap<>();
       techerLogin=new HashMap<>();
       studentId=new ArrayList<>();
   }

}
