package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.*;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;
import gr.aueb.cf.schoolpro.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/meetingsearch")
public class MeetingSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            IDAOMeetings daoMeetings = new MeetingsDAOImpl();
            IMeetingsService serviceMeetings = new MeetingServiceImpl(daoMeetings);
            IDAOTeachers daoTeachers = new TeachersDAOImplementation();
            ITeachersService serviceTeachers = new TeachersServiceImpl(daoTeachers);
            IDAOStudents daoStudents = new StudentsDAOImpl();
            IStudentService serviceStudents = new StudentServiceImpl(daoStudents);
            List<MeetingShowDTO> meetings = new ArrayList<>();
            String message = request.getParameter("message");
            if (request.getParameter("teacherid") != null) {
                int teacherId = Integer.parseInt(request.getParameter("teacherid"));
                request.setAttribute("searchtype", "teacherid");
                request.setAttribute("searchvalue", teacherId);
                meetings = serviceMeetings.teacherMeetings(teacherId, serviceStudents, serviceTeachers);
            } else if (request.getParameter("studentid") != null) {
                int studentId = Integer.parseInt(request.getParameter("studentid"));
                request.setAttribute("searchtype", "studentid");
                request.setAttribute("searchvalue", studentId);
                meetings = serviceMeetings.studentMeetings(studentId, serviceStudents, serviceTeachers);
            }else{
                message = message + " wrong url parameter";
            }
            if (meetings.isEmpty()) {
                 message = message +  " no results found";
                request.setAttribute("message", message);
            } else {
                request.setAttribute("meetings", meetings);
            }
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/searchmeeting.jsp").forward(request, response);

        } catch (SQLException | DAOException | StudentNotFoundException | TeacherNotFoundException e) {
            String message = "Error: " + e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/searchmeeting.jsp").forward(request, response);
        }
    }
}

