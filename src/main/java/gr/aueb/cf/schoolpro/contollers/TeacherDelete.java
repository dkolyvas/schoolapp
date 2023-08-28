package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.service.ITeachersService;
import gr.aueb.cf.schoolpro.service.TeachersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/teacherdelete")
public class TeacherDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchCriteria = request.getParameter("criteria");
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            IDAOTeachers dao = new TeachersDAOImplementation();
            ITeachersService service = new TeachersServiceImpl(dao);
            service.DeleteTeacher(id);
            response.sendRedirect(request.getContextPath()+"/admin/teachersearch?teachername=" + searchCriteria + "&message=deleted");
        }catch(DAOException|SQLException | TeacherNotFoundException | IOException e ){
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/admin/teachersearch?teachername=" + searchCriteria + "&message=deleterror");
        }

    }
}
