package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dto.SpecialityDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.dto.UserShowDTO;
import gr.aueb.cf.schoolpro.service.ITeachersService;
import gr.aueb.cf.schoolpro.service.TeachersServiceImpl;
import gr.aueb.cf.schoolpro.validators.ValidatorTeacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/teacherinsert")
public class TeacherInsert extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if(message == null) message = "";
        request.setAttribute("message", message);

        try{
            IDAOTeachers dao = new TeachersDAOImplementation();
            TeachersServiceImpl service = new TeachersServiceImpl(dao);
            loadUsers(service, request);
            loadSpecialities(service, request);
            TeacherInsertDTO teacher = new TeacherInsertDTO();

            request.setAttribute("teacher", teacher);
            request.getRequestDispatcher("/templates/static/admin/insertteacher.jsp").forward(request, response);

        }catch(DAOException | SQLException e){
            message = "Πρόβλημα στην επικοινωνία με τη βάση δεδομένων";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/insertteacher.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        TeacherInsertDTO teacher = new TeacherInsertDTO();
        teacher.setFirstName(request.getParameter("firstname").trim());
        teacher.setLastName(request.getParameter("lastname").trim());
        teacher.setSsn(request.getParameter("ssn").trim());
        teacher.setUserName(request.getParameter("username"));
        teacher.setSpeciality(request.getParameter("speciality"));
        try{
            IDAOTeachers dao = new TeachersDAOImplementation();
            TeachersServiceImpl service = new TeachersServiceImpl(dao);
            Map<String, String> errors = ValidatorTeacher.checkTeacher(teacher);
            if(!errors.isEmpty()){
                message = "Errors: ";
                for(Map.Entry<String, String> entry : errors.entrySet()){
                    message = message + entry.getKey() + ": " + entry.getValue();
                }
                request.setAttribute("message", message);
                request.setAttribute("teacher", teacher);
                loadUsers(service, request);
                loadSpecialities(service, request);
                request.getRequestDispatcher("/templates/static/admin/insertteacher.jsp").forward(request, response);
            } else{
                service.InsertTeacher(teacher);
                response.sendRedirect( request.getContextPath()+ "/admin/adminmenu?insert=true");
            }
        }catch(InvalidEntryException e){
            message = e.getMessage();
            request.setAttribute("message", message);
            request.setAttribute("teacher", teacher);
            response.sendRedirect(request.getContextPath()+ "/admin/teacherinsert?message=" + message);
        }
        catch(DAOException |SQLException e){
            message = "Πρόβλημα στην επικοινωνία με τη βάση δεδομένων";
            request.setAttribute("message", message);
            request.setAttribute("teacher", teacher);
            request.getRequestDispatcher("/templates/static/admin/insertteacher.jsp").forward(request, response);
        }
    }

    private void loadUsers(TeachersServiceImpl service, HttpServletRequest request){
        List<String> users = service.userList();
        request.setAttribute("users", users);
    }

    private void loadSpecialities(TeachersServiceImpl service, HttpServletRequest request){
        List<String> specialities = service.specialityList();
        request.setAttribute("specialities", specialities);
    }



}
