package gr.aueb.cf.schoolpro.validators;

import gr.aueb.cf.schoolpro.dto.MeetingDTO;
import gr.aueb.cf.schoolpro.util.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ValidatorMeeting {
    public static Map<String, String> checkMeeting(MeetingDTO meeting){
        Map<String, String> entries = new HashMap<>();

        if(meeting.getStudentId() < 0) entries.put("Student", "StudentId not set");

        if(meeting.getTeacherId() <0 ) entries.put("Teacher", "TeacherId not set");

        if(meeting.getClassroom().length() < 2) entries.put("Classroom", "Invalid Classroom");

        try{
            Date date = DateUtil.toDate(meeting.getDate());

        }catch(ParseException e){
            e.printStackTrace();
            entries.put("Meeting date", "Invalid Date");
        }
        return entries;
    }
}
