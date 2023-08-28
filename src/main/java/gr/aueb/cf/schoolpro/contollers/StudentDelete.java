package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOStudents;
import gr.aueb.cf.schoolpro.dao.IDAOTeachers;
import gr.aueb.cf.schoolpro.dao.StudentsDAOImpl;
import gr.aueb.cf.schoolpro.dao.TeachersDAOImplementation;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
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

@WebServlet("/admin/studentdelete")
public class StudentDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchCriteria = request.getParameter("criteria");
        int id = Integer.parseInt(request.getParameter("id"));
        try{
            IDAOStudents dao = new StudentsDAOImpl();
            IStudentService service = new StudentServiceImpl(dao);
            service.deleteStudent(id);
            response.sendRedirect(request.getContextPath()+"/admin/studentsearch?studentname=" + searchCriteria + "&message=deleted");
        }catch(DAOException | SQLException | StudentNotFoundException | IOException e ){
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/admin/studentsearch?studentname=" + searchCriteria + "&message=deleterror");
        }

    }
}
