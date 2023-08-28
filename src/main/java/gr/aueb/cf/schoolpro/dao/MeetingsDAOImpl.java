package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolpro.model.Meeting;
import gr.aueb.cf.schoolpro.util.DBUtil;
import gr.aueb.cf.schoolpro.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingsDAOImpl implements IDAOMeetings {

    private Connection connection;

    public MeetingsDAOImpl() throws SQLException {
        connection = DBUtil.getConnection();
    }

    @Override
    public List<Meeting> getMeetingsForStudent(int studentId) throws DAOException {
        List<Meeting> meetings = new ArrayList<>();
        Meeting curMeeting;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MEETINGS WHERE STUDENT_ID = ? ");
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                curMeeting = new Meeting();
                curMeeting.setStudent(rs.getInt("STUDENT_ID"));
                curMeeting.setTeacher(rs.getInt("TEACHER_ID"));
                curMeeting.setDate(DateUtil.fromSQLDate(rs.getDate("MEETING_DATE")));
                curMeeting.setRoom(rs.getString("MEETING_ROOM"));
                meetings.add(curMeeting);
            }
            return meetings;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Database error. Unable to handle your request");
        }
    }

    @Override
    public List<Meeting> getMeetingsForTeacher(int teacherId) throws DAOException {
        List<Meeting> meetings = new ArrayList<>();
        Meeting curMeeting;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM MEETINGS WHERE TEACHER_ID = ? ");
            ps.setInt(1, teacherId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                curMeeting = new Meeting();
                curMeeting.setStudent(rs.getInt("STUDENT_ID"));
                curMeeting.setTeacher(rs.getInt("TEACHER_ID"));
                curMeeting.setDate(DateUtil.fromSQLDate(rs.getDate("MEETING_DATE")));
                curMeeting.setRoom(rs.getString("MEETING_ROOM"));
                meetings.add(curMeeting);
            }
            return meetings;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Database error. Unable to handle your request");
        }
    }

    @Override
    public void insertMeeting(Meeting meeting) throws DAOException, MeetingDAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO MEETINGS VALUES(?, ?, ?, ?)");
            ps.setInt(1, meeting.getTeacher());
            ps.setInt(2, meeting.getStudent());
            ps.setString(3, meeting.getRoom());
            ps.setDate(4, DateUtil.toSQLDate(meeting.getDate()));
            int i = ps.executeUpdate();
            if (i == 0) throw new MeetingDAOException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Database error. Unable to handle your request");
        }

    }

    @Override
    public Meeting updateMeeting(Meeting meeting) throws DAOException, MeetingDAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE MEETINGS SET MEETING_ROOM = ?, MEETING_DATE = ? " +
                    "WHERE TEACHER_ID = ? AND STUDENT_ID = ?");
            ps.setString(1, meeting.getRoom());
            ps.setDate(2, DateUtil.toSQLDate(meeting.getDate()));
            ps.setInt(3, meeting.getTeacher());
            ps.setInt(4, meeting.getStudent());
            int i = ps.executeUpdate();
            if (i == 0) throw new MeetingDAOException();
            return meeting;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Database error. Unable to handle your request");
        }
    }

    @Override
    public void deleteMeeting(int teacherId, int studentId) throws DAOException, MeetingDAOException {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE  FROM MEETINGS WHERE TEACHER_ID = ? AND STUDENT_ID = ?");
            ps.setInt(1,teacherId);
            ps.setInt(2, studentId);
            int i = ps.executeUpdate();
            if (i == 0) throw new MeetingDAOException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Database error. Unable to handle your request");
        }
    }
}
