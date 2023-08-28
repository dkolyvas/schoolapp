package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOStudents;
import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.StudentsDAOImpl;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.service.IStudentService;
import gr.aueb.cf.schoolpro.service.StudentServiceImpl;
import gr.aueb.cf.schoolpro.service.TeachersServiceImpl;
import gr.aueb.cf.schoolpro.validators.ValidatorStudent;
import gr.aueb.cf.schoolpro.validators.ValidatorTeacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/studentupdate")
public class StudentUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchCriteria = request.getParameter("criteria");
        int id = Integer.parseInt(request.getParameter("id"));
        String message = "";
        StudentDTO student = new StudentDTO();
        try{
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);
            student = service.getStudentById(id);
            loadUsers(service, request);
            loadCities(service, request);
            request.setAttribute("student", student);
            request.setAttribute("criteria", searchCriteria);
            request.getRequestDispatcher("/templates/static/admin/updatestudent.jsp").forward(request, response);

        }catch(SQLException | DAOException | StudentNotFoundException e){
            message = e.getMessage();
            request.setAttribute("student", student);
            request.setAttribute("message", message);
            request.setAttribute("criteria", searchCriteria);
            request.getRequestDispatcher("/templates/static/admin/updatestudent.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchCriteria = request.getParameter("criteria");
        int id = Integer.parseInt(request.getParameter("id"));
        String message = "";
        StudentDTO student = new StudentDTO();
        student.setFirstName(request.getParameter("firstname"));
        student.setId(id);
        student.setLastName(request.getParameter("lastname"));
        student.setBirthDate(request.getParameter("birthdate"));
        student.setGender(request.getParameter("gender"));
        student.setUsername(request.getParameter("username"));
        student.setCity(request.getParameter("city"));
        try {
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);

            List<String> users = service.getUsers();
            List<String> cities = service.getCities();
            request.setAttribute("users", users);
            request.setAttribute("cities", cities);
            request.setAttribute("student", student);
            request.setAttribute("criteria", searchCriteria);

            Map<String, String> errors = ValidatorStudent.validateStudent(student);
            if (!errors.isEmpty()) {
                for (Map.Entry<String, String> entry : errors.entrySet()) {
                    message = "Errors: " + entry.getKey() + " : " + entry.getValue() + "  ";
                }
                request.setAttribute("message", message);

                request.getRequestDispatcher("/templates/static/admin/updatestudent.jsp").forward(request, response);
            } else {
                service.updateStudent(student);
                message = "updated";
                response.sendRedirect(request.getContextPath() + "/admin/studentsearch?studentname=" + searchCriteria + "&message=" + message);
            }
        }catch(InvalidEntryException|SQLException | DAOException | StudentNotFoundException | ParseException e){
            message = e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/updatestudent.jsp").forward(request, response);
        }

    }
    private void loadUsers(IStudentService service, HttpServletRequest request) {
        List<String> users = service.getUsers();
        request.setAttribute("users", users);
    }

    private void loadCities(IStudentService service, HttpServletRequest request) {
        List<String> cities = service.getCities();
        request.setAttribute("cities", cities);
    }
}
