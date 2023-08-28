package gr.aueb.cf.schoolpro.validators;

import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.util.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ValidatorStudent {
    public static Map<String, String> validateStudent(StudentDTO student){
        Map<String, String> entries = new HashMap<>();

        if(student.getId() <0 ) entries.put("ID", "Invalid Number");

        if(student.getFirstName().length() < 2) entries.put("First Name", "Length");
        if(!student.getFirstName().matches("[a-zA-Z]+")) entries.put("First Name", "Invalid Characters");

        if(student.getLastName().length() < 2) entries.put("Last Name", "Length");
        if(!student.getLastName().matches("[a-zA-Z]+")) entries.put("Last Name", "Invalid Characters");
        if(student.getGender() == null) {entries.put("Gender", "Not selected");}
        else if(!student.getGender().matches("[MF]")) {entries.put("Gender", "Invalid Value");}


        try{
            Date date = DateUtil.toDate(student.getBirthDate());

        }
        catch(ParseException e){
            entries.put("Birthdate", "Invalid Date");
        }
        return entries;


    }

    public static Map<String, String> validateStudent(StudentInsertDTO student){
        Map<String, String> entries = new HashMap<>();

        if(student.getFirstName().length() < 2) entries.put("First Name", "Length");
        if(!student.getFirstName().matches("[a-zA-Z]+")) entries.put("First Name", "Invalid Characters");

        if(student.getLastName().length() < 2) entries.put("Last Name", "Length");
        if(!student.getLastName().matches("[a-zA-Z]+")) entries.put("Last Name", "Invalid Characters");
        if(student.getGender() == null) {entries.put("Gender", "Not selected");}
        else if(!student.getGender().matches("[MF]")) entries.put("Gender", "Invalid Value");

        try{
            Date date = DateUtil.toDate(student.getBirthDate());

        }
        catch(ParseException e){
            entries.put("Birthdate", "Invalid Date");
        }
        return entries;


    }


}
