package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.*;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dto.MeetingDTO;
import gr.aueb.cf.schoolpro.dto.MeetingShowDTO;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.service.*;
import gr.aueb.cf.schoolpro.validators.ValidatorMeeting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/meetingupdate")
public class MeetingUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int teacherId =-1;
       String teacherName = "";
       int studentId = -1;
       String studentName ="";
       String date ="";
       String room ="";
       String searchType ="";
       if(request.getParameter("teacherid") != null) teacherId = Integer.parseInt(request.getParameter("teacherid"));
       if(request.getParameter("teachername") != null) teacherName = request.getParameter("teachername");
        if(request.getParameter("studentid") != null) studentId = Integer.parseInt(request.getParameter("studentid"));
        if(request.getParameter("studentname") != null) studentName = request.getParameter("studentname");
        if(request.getParameter("searchtype") != null) searchType = request.getParameter("searchtype");

        HttpSession session = request.getSession(false);
        MeetingShowDTO meeting = new MeetingShowDTO();
        meeting.setTeacherId(teacherId);
        meeting.setTeacherName(teacherName);
        meeting.setStudentId(studentId);
        meeting.setStudentName(studentName);
        meeting.setClassroom(room);
        meeting.setDate(date);
        session.setAttribute("meeting", meeting);
        session.setAttribute("searchtype", searchType);
        request.getRequestDispatcher("/templates/static/admin/updatemeeting.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        MeetingDTO meeting = (MeetingDTO) session.getAttribute("meeting");

        meeting.setClassroom(request.getParameter("room"));
        meeting.setDate(request.getParameter("date"));
        String message = "";
        Map<String, String> errors = ValidatorMeeting.checkMeeting(meeting);
        if (!errors.isEmpty()) {
            message = "Errors: ";
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                message = message + " " + entry.getKey() + ": " + entry.getValue();
            }
            request.setAttribute("message", message);
            request.setAttribute("meeting", meeting);
            request.getRequestDispatcher("/templates/static/admin/updatemeeting.jsp").forward(request, response);
        } else {
            try {
                IDAOMeetings dao = new MeetingsDAOImpl();
                IMeetingsService service = new MeetingServiceImpl(dao);
                service.updateMeeting(meeting);
                message = "Succesfull update";
                request.setAttribute("message", message);
                session.removeAttribute("teacherlist");
                session.removeAttribute("studentlist");
                session.removeAttribute("meeting");
                String searchType = (String) session.getAttribute("searchtype");
                session.removeAttribute("searchtype");
                int searchValue = 1;

                if(searchType.equals("teacherid")) searchValue = meeting.getTeacherId();
                if(searchType.equals("studentid")) searchValue = meeting.getStudentId();
                response.sendRedirect( request.getContextPath() + "/admin/meetingsearch?" + searchType + "=" + searchValue+"&message=successful%20update");

            } catch (SQLException | MeetingDAOException | DAOException | ParseException e) {
                message = e.getMessage();
                request.setAttribute("message", message);
                request.getRequestDispatcher("/templates/static/admin/updatemeeting.jsp").forward(request, response);
            }
        }
    }

}
