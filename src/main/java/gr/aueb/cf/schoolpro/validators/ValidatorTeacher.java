package gr.aueb.cf.schoolpro.validators;

import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;

import java.util.HashMap;
import java.util.Map;

public class ValidatorTeacher {

    public static Map<String, String> checkTeacher(TeacherDTO teacher){
        Map<String, String> entries = new HashMap<>();

        if(teacher.getId() < 0) entries.put("ID", "Invalid Number");

        if(teacher.getFirstName().length() < 2) entries.put("First Name", "length");
        if (! teacher.getFirstName().matches("\\w+")) entries.put("First Name", "Invalid Characters");

        if(teacher.getLastName().length() < 2) entries.put("Last Name", "length");
        if (! teacher.getLastName().matches("\\w+")) entries.put("Last Name", "Invalid Characters");

        if( teacher.getSsn().length() != 9 ) entries.put("SSN", "must be 9 digits");
        if(!teacher.getSsn().matches("\\d+")) entries.put("SSN", "must contain only numbers");

        return entries;


    }

    public static Map<String, String> checkTeacher(TeacherInsertDTO teacher){
        Map<String, String> entries = new HashMap<>();
        if(teacher.getFirstName().length() < 2) entries.put("First Name", "length");
        if (! teacher.getFirstName().matches("[a-zA-Z]+")) entries.put("First Name", "Invalid Characters");

        if(teacher.getFirstName().length() < 2) entries.put("First Name", "length");
        if (! teacher.getFirstName().matches("[a-zA-Z]+")) entries.put("First Name", "Invalid Characters");

        if( teacher.getSsn().length() != 9 ) entries.put("SSN", "must be 9 digits");
        if(!teacher.getSsn().matches("\\d+")) entries.put("SSN", "must contain only numbers");

        return entries;

    }
}
