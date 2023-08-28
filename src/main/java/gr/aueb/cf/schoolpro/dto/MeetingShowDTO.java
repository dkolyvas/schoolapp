package gr.aueb.cf.schoolpro.dto;

public class MeetingShowDTO extends MeetingDTO{
    private String teacherName;
    private String studentName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

     public MeetingShowDTO(){
        super();
    }
}
