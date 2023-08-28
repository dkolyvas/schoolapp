package gr.aueb.cf.schoolpro.dto;

import java.util.Date;

public class MeetingDTO {
    protected int teacherId;
    protected int studentId;
    protected String classroom;
    protected String date;

    public MeetingDTO(int teacherId, int studentId, String classroom, String date) {
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.classroom = classroom;
        this.date = date;
    }

    public MeetingDTO(){}

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
