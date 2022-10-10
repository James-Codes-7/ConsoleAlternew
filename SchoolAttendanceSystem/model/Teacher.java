package schoolattendancealter.model;

public class Teacher {

    private int teacherId;
    private String teacherName;
    private String teacherSection;
    private String teacherDateOfBirth;
    private  String teacherLoginPassword;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSection() {
        return teacherSection;
    }

    public void setTeacherSection(String teacherSection) {
        this.teacherSection = teacherSection;
    }

    public String getTeacherDateOfBirth() {
        return teacherDateOfBirth;
    }

    public void setTeacherDateOfBirth(String teacherDateOfBirth) {
        this.teacherDateOfBirth = teacherDateOfBirth;
    }

    public String getTeacherLoginPassword() {
        return teacherLoginPassword;
    }

    public void setTeacherLoginPassword(String teacherLoginPassword) {
        this.teacherLoginPassword = teacherLoginPassword;
    }
}
