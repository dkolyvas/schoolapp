package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
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

@WebServlet("/admin/teacherupdate")
public class TeacherUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchCriteria = request.getParameter("criteria");
        int id = Integer.parseInt(request.getParameter("id"));
        String message = "";
        TeacherDTO teacher = new TeacherDTO();
        try{
            IDAOTeachers dao = new TeachersDAOImplementation();
            TeachersServiceImpl service = new TeachersServiceImpl(dao);
            teacher = service.getTeacherById(id);
            loadUsers(service, request);
            loadSpecialities(service, request);
            request.setAttribute("teacher", teacher);
            request.setAttribute("criteria", searchCriteria);
            request.getRequestDispatcher("/templates/static/admin/updateteacher.jsp").forward(request, response);

        }catch(SQLException | DAOException | TeacherNotFoundException e){
            message = e.getMessage();
            request.setAttribute("teacher", teacher);
            request.setAttribute("message", message);
            request.setAttribute("criteria", searchCriteria);
            request.getRequestDispatcher("/templates/static/admin/updateteacher.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchCriteria = request.getParameter("criteria");
        int id = Integer.parseInt(request.getParameter("id"));
        String message = "";
        TeacherDTO teacher = new TeacherDTO();
        teacher.setFirstName(request.getParameter("firstname"));
        teacher.setId(id);
        teacher.setLastName(request.getParameter("lastname"));
        teacher.setSsn(request.getParameter("ssn"));
        teacher.setUserName(request.getParameter("username"));
        teacher.setSpeciality(request.getParameter("speciality"));
        try {
            IDAOTeachers dao = new TeachersDAOImplementation();
            TeachersServiceImpl service = new TeachersServiceImpl(dao);

            List<String> users = service.userList();
            List<String> specialities = service.specialityList();
            request.setAttribute("users", users);
            request.setAttribute("specialities", specialities);
            request.setAttribute("teacher", teacher);
            request.setAttribute("criteria", searchCriteria);

            Map<String, String> errors = ValidatorTeacher.checkTeacher(teacher);
            if (!errors.isEmpty()) {
                for (Map.Entry<String, String> entry : errors.entrySet()) {
                    message = "Errors: " + entry.getKey() + " : " + entry.getValue() + "  ";
                }
                request.setAttribute("message", message);

                request.getRequestDispatcher("/templates/static/admin/updateteacher.jsp").forward(request, response);
            } else {
                service.UpdateTeacher(teacher);
                message = "updated";
                response.sendRedirect(request.getContextPath() + "/admin/teachersearch?teachername=" + searchCriteria + "&message=" + message);
            }
        }catch(InvalidEntryException e){
            message = e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/templates/static/admin/updateteacher.jsp").forward(request, response);
        }
        catch(SQLException | DAOException | TeacherNotFoundException e){
            e.printStackTrace();
            message = "updaterror";
            response.sendRedirect(request.getContextPath() + "/admin/teachersearch?teachername=" + searchCriteria + "&message=" + message);
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
