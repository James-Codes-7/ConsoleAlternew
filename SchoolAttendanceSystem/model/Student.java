package schoolattendancealter.model;

public class Student {

    private int studentId;
    private String studentName;
    private String dateOfBirth;
    private String studentLoginPassword;

    public String getStudentLoginPassword() {
        return studentLoginPassword;
    }

    public void setStudentLoginPassword(String studentLoginPassword) {
        this.studentLoginPassword = studentLoginPassword;
    }



    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



}
