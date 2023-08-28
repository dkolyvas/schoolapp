package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.service.ITeachersService;
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

@WebServlet("/admin/teachersearch")
public class TeacherSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherName = request.getParameter("teachername");
        String message = request.getParameter("message");
        if(message == null) message = "";
        if(message.equals("deleted")) message = "Επιτιχής διαγραφή";
        if(message.equals("deleterror")) message = "Απέτυχε η διαγραφη";
        if(message.equals("updated")) message = "Επιτυχής ενημέρωση";
        if(message.equals("updaterror")) message = "Απτέτυχε η ενημέρωση";
        List<TeacherDTO> teachers = new ArrayList<>();
        try {
            IDAOTeachers dao = new TeachersDAOImplementation();
            ITeachersService service = new TeachersServiceImpl(dao);

            teachers = service.getTeacherList(teacherName);
            request.setAttribute("teachers", teachers);
            request.setAttribute("message", message);
            request.setAttribute("criteria", teacherName);
            request.getRequestDispatcher("/templates/static/admin/searchteacher.jsp").forward(request, response);


        } catch (NoRecordsFoundException | SQLException | DAOException e) {
            request.setAttribute("message",message + " "+ e.getMessage());
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/templates/static/admin/searchteacher.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherName = request.getParameter("teachername");
        String message = request.getParameter("message");
        if(message == null) message = "";

        List<TeacherDTO> teachers = new ArrayList<>();
        try {
            IDAOTeachers dao = new TeachersDAOImplementation();
            ITeachersService service = new TeachersServiceImpl(dao);

            teachers = service.getTeacherList(teacherName);
            request.setAttribute("teachers", teachers);
            request.setAttribute("message", message);
            request.setAttribute("criteria", teacherName);
            request.getRequestDispatcher("/templates/static/admin/searchteacher.jsp").forward(request, response);


        } catch (NoRecordsFoundException | SQLException | DAOException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/templates/static/admin/searchteacher.jsp").forward(request, response);
        }
    }
}
