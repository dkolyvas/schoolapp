package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.IDAOMeetings;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.MeetingDTO;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.model.Meeting;
import gr.aueb.cf.schoolpro.util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MeetingServiceImpl implements IMeetingsService{
    private IDAOMeetings dao;

    public MeetingServiceImpl(IDAOMeetings dao){
        this.dao = dao;
    }
    @Override
    public List<MeetingShowDTO> studentMeetings(int studentId, IStudentService studentService, ITeachersService teachersService) throws DAOException, TeacherNotFoundException, StudentNotFoundException {
        List<Meeting> rawData = dao.getMeetingsForStudent(studentId);
        List<MeetingShowDTO> meetings = new ArrayList<>();

        for(Meeting meeting: rawData){
            meetings.add(mapToDTO(meeting, studentService, teachersService));

        }
        return meetings;
    }

    @Override
    public List<MeetingShowDTO> teacherMeetings(int teacherId, IStudentService studentService, ITeachersService teachersService) throws DAOException, TeacherNotFoundException, StudentNotFoundException {
        List<Meeting> rawData = dao.getMeetingsForTeacher(teacherId);
        List<MeetingShowDTO> meetings = new ArrayList<>();

        for(Meeting meeting: rawData){
            meetings.add(mapToDTO(meeting, studentService, teachersService));

        }
        return meetings;
    }

    @Override
    public void insertMeeting(MeetingDTO meeting) throws DAOException, MeetingDAOException, ParseException {
        dao.insertMeeting(mapToDao(meeting));
    }

    @Override
    public void updateMeeting(MeetingDTO meeting) throws DAOException, MeetingDAOException, ParseException {
        dao.updateMeeting(mapToDao(meeting));

    }

    @Override
    public void deleteMeeting(int teacherId, int studentId) throws DAOException, MeetingDAOException {
        dao.deleteMeeting(teacherId, studentId);
    }

    private Meeting mapToDao(MeetingDTO dto) throws ParseException {
        Meeting meeting = new Meeting();
        meeting.setTeacher(dto.getTeacherId());
        meeting.setStudent(dto.getStudentId());
        meeting.setDate(DateUtil.toDate(dto.getDate()));
        meeting.setRoom(dto.getClassroom());

        return meeting;

    }

    private MeetingDTO mapToDTO(Meeting meeting){
        MeetingDTO dto = new MeetingDTO();
        dto.setTeacherId(meeting.getTeacher());
        dto.setStudentId(meeting.getStudent());
        dto.setClassroom(meeting.getRoom());
        dto.setDate(DateUtil.toString(meeting.getDate()));

        return dto;

    }

    private MeetingShowDTO mapToDTO(Meeting meeting, IStudentService studentService, ITeachersService teachersService) throws DAOException, TeacherNotFoundException, StudentNotFoundException {
        MeetingShowDTO dto = new MeetingShowDTO();
        TeacherDTO teacher = teachersService.getTeacherById(meeting.getTeacher());
        StudentDTO student = studentService.getStudentById(meeting.getStudent());

        dto.setTeacherId(meeting.getTeacher());
        dto.setStudentId(meeting.getStudent());
        dto.setClassroom(meeting.getRoom());
        dto.setDate(DateUtil.toString(meeting.getDate()));
        dto.setTeacherName(teacher.getFirstName() + " " + teacher.getLastName());
        dto.setStudentName(student.getFirstName() + " " + student.getLastName());

        return dto;
    }
}
