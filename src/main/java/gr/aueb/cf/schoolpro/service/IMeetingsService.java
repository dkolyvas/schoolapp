package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.MeetingDTO;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;

import java.text.ParseException;
import java.util.List;

public interface IMeetingsService {
    List<MeetingShowDTO> studentMeetings(int studentId, IStudentService studentService, ITeachersService teachersService) throws DAOException, TeacherNotFoundException, StudentNotFoundException;
    List<MeetingShowDTO> teacherMeetings(int teacherId, IStudentService studentService, ITeachersService teachersService) throws DAOException, TeacherNotFoundException, StudentNotFoundException;
    void insertMeeting(MeetingDTO meeting) throws DAOException, MeetingDAOException, ParseException;
    void updateMeeting(MeetingDTO meeting) throws DAOException, MeetingDAOException, ParseException;

    void deleteMeeting(int teacheId, int meetingId) throws DAOException, MeetingDAOException;
}
