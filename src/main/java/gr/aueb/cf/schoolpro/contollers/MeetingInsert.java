package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.*;
import gr.aueb.cf.schoolpro.dao.exceptions.*;
import gr.aueb.cf.schoolpro.dto.MeetingDTO;
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

@WebServlet("/admin/meetinginsert")
public class MeetingInsert extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("meetingteachername", "-");
        session.setAttribute("meetingstudentname", "-");
        request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action){
            case "searchteacher":
                searchTeacher(request, response);
                break;
            case "setteacher":
                setTeacher(request, response);
                break;
            case "searchstudent":
                searchStudent(request, response);
                break;
            case "setstudent":
                setStudent(request, response);
                break;
            case "insert":
                insertMeeting(request, response);
                break;
            default:
                String message = "Invalid url argument";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
        }

    }

    private void searchTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("strteachersearch");
        try {
            IDAOTeachers dao = new TeachersDAOImplementation();
            ITeachersService service = new TeachersServiceImpl(dao);
            List<TeacherDTO> teachers = service.getTeacherList(name);
            request.getSession(true).setAttribute("teacherlist", teachers);
            //request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
        } catch (SQLException | NoRecordsFoundException | DAOException e) {
            String message = "Error: " + e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
        }
    }

    private void setTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try{
        int teacherId = Integer.parseInt(request.getParameter("teacherid"));
//            IDAOTeachers dao = new TeachersDAOImplementation();
//            ITeachersService service = new TeachersServiceImpl(dao);
        TeacherDTO teacher = new TeacherDTO();
        HttpSession session = request.getSession(false);
        List<TeacherDTO> teachers = (List<TeacherDTO>) session.getAttribute("teacherlist");
        for (TeacherDTO currTeacher : teachers) {
            if (currTeacher.getId() == teacherId) {
                teacher = currTeacher;
                break;
            }
        }
        session.setAttribute("meetingteacherid", teacherId);
        session.setAttribute("meetingteachername", teacher.getFirstName() + " " + teacher.getLastName());
        request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);

//        }catch(SQLException  | DAOException | TeacherNotFoundException e){
//            String message ="Error: " +  e.getMessage();
//            request.setAttribute("message", message);
//            request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);

    }

    private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("strstudentsearch");
        try {
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);
            List<StudentDTO> students = service.getStudents(name);
            request.getSession(false).setAttribute("studentlist", students);
            //request.setAttribute("students", students);
            request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
        } catch (SQLException | NoRecordsFoundException | DAOException e) {
            String message = "Error: " + e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
        }
    }

    private void setStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //     try{
        int studentId = Integer.parseInt(request.getParameter("studentid"));
//            IDAOStudents dao = new StudentsDAOImpl();
//            IStudentService service = new StudentServiceImpl(dao);
        StudentDTO student = new StudentDTO();
        HttpSession session = request.getSession(false);
        List<StudentDTO> students = (List<StudentDTO>) session.getAttribute("studentlist");
        for (StudentDTO currStudent : students) {
            if (currStudent.getId() == studentId) {
                student = currStudent;
                break;
            }
        }
        String studentName = student.getFirstName() + " "+ student.getLastName();
        session.setAttribute("meetingstudentid", studentId);
        session.setAttribute("meetingstudentname", studentName);
        //request.setAttribute("name", studentName);
        request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);

//        }catch(SQLException | DAOException | StudentNotFoundException e){
//            String message ="Error: " +  e.getMessage();
//            request.setAttribute("message", message);
//            request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
//        }
    }

    private void insertMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        MeetingDTO meeting = new MeetingDTO();
        if(session.getAttribute("meetingteacherid") != null){
            meeting.setTeacherId((Integer) session.getAttribute("meetingteacherid"));
        }else{
            meeting.setTeacherId(-1);
        }
        if(session.getAttribute("meetingstudentid") != null) {
            meeting.setStudentId((Integer) session.getAttribute("meetingstudentid"));
        }else{
            meeting.setStudentId(-1);
        }
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
            request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
        } else {
            try {
                IDAOMeetings dao = new MeetingsDAOImpl();
                IMeetingsService service = new MeetingServiceImpl(dao);
                service.insertMeeting(meeting);
                message = "Επιτυχής Εισαγωγή";
                session.removeAttribute("teacherlist");
                session.removeAttribute("studentlist");
                session.removeAttribute("meetingstudentid");
                session.removeAttribute("meetingstudentname");
                session.removeAttribute("meetingteacherid");
                session.removeAttribute("meetingteachername");

                response.sendRedirect(request.getContextPath()  + "/admin/adminmenu?insert=true");

            } catch (SQLException | MeetingDAOException | DAOException | ParseException e) {
                message = e.getMessage();
                request.setAttribute("meeting", meeting);
                request.getRequestDispatcher("/templates/static/admin/insertmeeting.jsp").forward(request, response);
            }
        }
    }
}
