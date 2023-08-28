package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOStudents;
import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.StudentsDAOImpl;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
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

@WebServlet("/admin/studentinsert")
public class StudentInsert extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if(message == null) message = "";
        request.setAttribute("message", message);

        try{
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);
            loadUsers(service, request);
            loadCities(service, request);
            StudentInsertDTO student = new StudentInsertDTO();

            request.setAttribute("student", student);
            request.getRequestDispatcher("/templates/static/admin/insertstudent.jsp").forward(request, response);

        }catch( SQLException e){
            message = "Πρόβλημα στην επικοινωνία με τη βάση δεδομένων";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/insertstudent.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        StudentInsertDTO student = new StudentInsertDTO();
        student.setFirstName(request.getParameter("firstname").trim());
        student.setLastName(request.getParameter("lastname").trim());
        student.setGender(request.getParameter("gender"));
        student.setUsername(request.getParameter("username"));
        student.setCity(request.getParameter("city"));
        student.setBirthDate(request.getParameter("birthdate"));
        try{
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);
            Map<String, String> errors = ValidatorStudent.validateStudent(student);
            if(!errors.isEmpty()){
                message = "Errors: ";
                for(Map.Entry<String, String> entry : errors.entrySet()){
                    message = message + entry.getKey() + ": " + entry.getValue();
                }
                request.setAttribute("message", message);
                request.setAttribute("student", student);
                loadUsers(service, request);
                loadCities(service, request);
                request.getRequestDispatcher("/templates/static/admin/insertstudent.jsp").forward(request, response);
            } else{
                service.insertStudent(student);
                response.sendRedirect(request.getContextPath()+ "/admin/adminmenu?insert=true");
            }
        }catch(InvalidEntryException| DAOException |SQLException| ParseException e){
            message = e.getMessage();
            request.setAttribute("message", message);
            request.setAttribute("student", student);
            request.getRequestDispatcher("/templates/static/admin/insertteacher.jsp").forward(request, response);
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