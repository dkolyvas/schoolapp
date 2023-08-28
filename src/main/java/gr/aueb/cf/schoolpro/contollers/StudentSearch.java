package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOStudents;
import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.StudentsDAOImpl;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.service.IStudentService;
import gr.aueb.cf.schoolpro.service.ITeachersService;
import gr.aueb.cf.schoolpro.service.StudentServiceImpl;
import gr.aueb.cf.schoolpro.service.TeachersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/studentsearch")
public class StudentSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("studentname");
        String message = request.getParameter("message");
        if(message == null) message = "";
        if(message.equals("deleted")) message = "Επιτιχής διαγραφή";
        if(message.equals("deleterror")) message = "Απέτυχε η διαγραφη";
        if(message.equals("updated")) message = "Επιτυχής ενημέρωση";
        if(message.equals("updaterror")) message = "Απτέτυχε η ενημέρωση";
        List<StudentDTO> students = new ArrayList<>();
        try {
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);

            students = service.getStudents(studentName);
            request.setAttribute("students", students);
            request.setAttribute("message", message);
            request.setAttribute("criteria", studentName);
            request.getRequestDispatcher("/templates/static/admin/searchstudent.jsp").forward(request, response);


        } catch (NoRecordsFoundException | SQLException | DAOException e) {
            request.setAttribute("message",message + " "+ e.getMessage());
            request.setAttribute("students", students);
            request.getRequestDispatcher("/templates/static/admin/searchstudent.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("studentname");
        String message = request.getParameter("message");
        if(message == null) message = "";

        List<StudentDTO> students = new ArrayList<>();
        try {
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);

            students = service.getStudents(studentName);
            request.setAttribute("students", students);
            request.setAttribute("message", message);
            request.setAttribute("criteria", studentName);
            request.getRequestDispatcher("/templates/static/admin/searchstudent.jsp").forward(request, response);


        } catch (NoRecordsFoundException | SQLException | DAOException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("students", students);
            request.getRequestDispatcher("/templates/static/admin/searchstudent.jsp").forward(request, response);
        }
    }
}

