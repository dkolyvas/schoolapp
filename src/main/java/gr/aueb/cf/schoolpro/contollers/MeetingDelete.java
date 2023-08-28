package gr.aueb.cf.schoolpro.contollers;

import gr.aueb.cf.schoolpro.dao.IDAOMeetings;
import gr.aueb.cf.schoolpro.dao.MeetingsDAOImpl;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolpro.service.IMeetingsService;
import gr.aueb.cf.schoolpro.service.MeetingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/admin/meetingdelete")
public class MeetingDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int teacherId = -1;
        int studentId = -1;
        String searchType ="";
        String searchValue ="";
        searchType = request.getParameter("searchtype");
        searchValue = request.getParameter("searchvalue");
        try{
            if(request.getParameter("teacher") != null) teacherId = Integer.parseInt(request.getParameter("teacher"));
            if(request.getParameter("student") != null) studentId = Integer.parseInt(request.getParameter("student"));

            if(teacherId == -1 || studentId == -1){
                request.setAttribute("message", "Delete failed. Error in delete parameters");
                response.sendRedirect( request.getContextPath() + "+/admin/meetingsearch?" +searchType+ "=" + searchValue);
            }

            IDAOMeetings dao = new MeetingsDAOImpl();
            IMeetingsService service = new MeetingServiceImpl(dao);
            service.deleteMeeting(teacherId, studentId);
            response.sendRedirect(request.getContextPath() + "/admin/meetingsearch?" +searchType+ "=" + searchValue +"&message=Delete%20successful");
        }catch(DAOException| MeetingDAOException| SQLException  e){

            response.sendRedirect( request.getContextPath()+ "/admin/meetingsearch?" +searchType+ "=" + searchValue + "&message=delete%20error%20database%20error");
        }
    }
}
