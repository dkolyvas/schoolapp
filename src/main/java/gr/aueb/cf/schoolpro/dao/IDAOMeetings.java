package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolpro.model.Meeting;

import java.util.List;

public interface IDAOMeetings {

    public List<Meeting> getMeetingsForStudent(int studentId) throws DAOException;
    public List<Meeting> getMeetingsForTeacher(int teacherId) throws DAOException;

    public void insertMeeting(Meeting meeting) throws DAOException, MeetingDAOException;

    public Meeting updateMeeting(Meeting meeting) throws DAOException, MeetingDAOException;

    public void deleteMeeting(int teacherId, int studentId) throws DAOException, MeetingDAOException;
}
