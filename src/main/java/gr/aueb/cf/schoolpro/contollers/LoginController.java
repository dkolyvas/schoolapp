package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOUsers;
import gr.aueb.cf.schoolpro.dao.StudentsDAOImpl;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.UserDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")

public class LoginController extends HttpServlet {
    private IDAOUsers dao;
    private IUserService service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInsertDTO user = new UserInsertDTO();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        try{
            dao = new UserDAOImplementation();
            service = new UserServiceImpl(dao);
            HttpSession session = request.getSession(false);
            if(!service.authenticate(user)){
                request.setAttribute("isError", true);
                String errors = "Λανθασμένο username/password";
                request.setAttribute("errors", errors);
                response.sendRedirect(request.getContextPath()+ "/login?isError=true&errors=login");

            }else if(user.getUsername().equals("admin")){
                session.setAttribute("admin", true);
                response.sendRedirect(request.getContextPath() + "/admin/adminmenu");
            }else{
                IStudentService studentService = new StudentServiceImpl(new StudentsDAOImpl());
                ITeachersService teacherService = new TeachersServiceImpl(new TeachersDAOImplementation());
                TeacherDTO teacher = teacherService.getTeacherByUsername(user.getUsername());
                StudentDTO student = studentService.getStudentByUsername(user.getUsername());
                session.setAttribute("username", user.getUsername());
                if(teacher != null){
                    session.setAttribute("usertype", "teacher");
                    session.setAttribute("userdetails", teacher);
                }else if(student != null){
                    session.setAttribute("usertype", "student");
                    session.setAttribute("userdetails", student);
                } else{
                    session.setAttribute("usertype", "unregistered");
                }
                response.sendRedirect(request.getContextPath() +"/user/usermenu");
            }
        }catch(SQLException | DAOException e){
            response.sendRedirect(request.getContextPath() + "/login?isError=true&errors=db");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String errors ;


           String isError = request.getParameter("isError");
           errors=  request.getParameter("errors");
           String newUser = request.getParameter("newUser");
           if(isError != null && isError.equals("true")){
               request.setAttribute("isError", "true");
               if(errors.equals("login")){ errors = "Λάθος στοιχεία σύνδεσης. Παρακαλώ επαναλάβετε";}
               else if(errors.equals("db")) {errors = "Πρόβλημα στην επικοινωνία με τη βάση δεδομένων";}
               else errors = "Παρουσιάστηκε κάποιο πρόβλημα";
               request.setAttribute("errors", errors);
           }else{
               request.setAttribute("isError", "false");
           }
           if(newUser != null && newUser.equals("true")){
               request.setAttribute("newUser", "true");
           }else{
               request.setAttribute("newUser", "false");
           }


           request.getRequestDispatcher("/templates/static/login.jsp").forward(request, response);

    }
}
