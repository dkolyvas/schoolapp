package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.*;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/usermenu")
public class UserMenu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String usertype = (String) session.getAttribute("usertype");
        List<MeetingShowDTO> meetings;
        try{
            IDAOMeetings dao = new MeetingsDAOImpl();
            IMeetingsService service = new MeetingServiceImpl(dao);
            IDAOTeachers teachersDAO = new TeachersDAOImplementation();
            ITeachersService teachersService = new TeachersServiceImpl(teachersDAO);
            IDAOStudents studentsDAO = new StudentsDAOImpl();
            IStudentService studentService = new StudentServiceImpl(studentsDAO);
            String name = "";

            switch(usertype){
                case "teacher":
                    TeacherDTO teacher = (TeacherDTO) session.getAttribute("userdetails");
                    meetings = service.teacherMeetings(teacher.getId(), studentService, teachersService);
                    name = teacher.getFirstName() + " " + teacher.getLastName();
                    if(meetings.isEmpty()) request.setAttribute("message", "No meetings have been scheduled for you yet");
                    break;
                case "student":
                    StudentDTO student = (StudentDTO) session.getAttribute("userdetails");
                    meetings = service.studentMeetings(student.getId(), studentService, teachersService);
                    name = student.getFirstName() + " " + student.getLastName();
                    if(meetings.isEmpty()) request.setAttribute("message", "No meetings have been scheduled for you yet");
                    break;
                default:
                    meetings = null;
                    request.setAttribute("message", "The administrator has not completed your inscription");
                  }
            request.setAttribute("name", name);
            request.setAttribute("meetings", meetings);

            request.getRequestDispatcher("/templates/static/user/usermenu.jsp").forward(request, response);

        }catch(SQLException | DAOException | TeacherNotFoundException | StudentNotFoundException e){
            request.setAttribute("message", "Error: " + e.getMessage());
            request.getRequestDispatcher("/templates/static/user/usermenu.jsp").forward(request, response);
        }

    }
}
