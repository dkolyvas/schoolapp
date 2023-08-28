package gr.aueb.cf.schoolpro.dao.exceptions;

import gr.aueb.cf.schoolpro.model.Student;

public class StudentNotFoundException extends Exception {
    private static final long serialversionUID = 1;

    public StudentNotFoundException(int id){
        super("No student with id " + id + " was found");
    }

    public StudentNotFoundException(Student student){
        super("No student with id " + student.getId() + " was found");
    }
}
