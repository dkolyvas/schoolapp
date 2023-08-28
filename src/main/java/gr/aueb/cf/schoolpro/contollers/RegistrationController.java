package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOUsers;
import gr.aueb.cf.schoolpro.dao.UserDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.service.IUserService;
import gr.aueb.cf.schoolpro.service.UserServiceImpl;
import gr.aueb.cf.schoolpro.validators.ValidatorUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/templates/static/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String message ="Errors: ";
        if(!password1.equals(password2)){
            request.setAttribute("isError", "true");
            request.setAttribute("message", "Δεν έχετε καταχωρίσει το ίδιο password στο πεδίο επαλήθευσης");
            request.getRequestDispatcher("/templates/static/registration.jsp").forward(request, response);
        }
        UserInsertDTO user = new UserInsertDTO();
        user.setUsername(username);
        user.setPassword(password1);
        try{
            IDAOUsers dao = new UserDAOImplementation();
            IUserService service = new UserServiceImpl(dao);
            Map<String, String> errors = ValidatorUser.checkUser(user, service);
            if(!errors.isEmpty()){
                if(errors.get("Username") != null) message += "Username: " + errors.get("Username");
                if(errors.get("Password") != null) message += "Password: " + errors.get("Password");
                request.setAttribute("isError", true);
                request.setAttribute("message", message);
                System.out.println(message);
                request.getRequestDispatcher("/templates/static/registration.jsp").forward(request, response);
            }else{
                service.insertUser(user);
                response.sendRedirect(request.getContextPath()+ "/login?newUser=true");
            }

        }catch(SQLException | DAOException e){
            e.printStackTrace();
            request.setAttribute("isError", true);
            request.setAttribute("message", "Πρόβλημα στην επικοινωνία με τη βάση δεδομένων");
        }
    }
}
