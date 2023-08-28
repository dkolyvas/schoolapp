package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.model.Student;
import gr.aueb.cf.schoolpro.util.DBUtil;
import gr.aueb.cf.schoolpro.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAOImpl implements IDAOStudents {
    private Connection connection;

    public StudentsDAOImpl() throws SQLException{
        connection = DBUtil.getConnection();
    }
    @Override
    public List<Student> getStudentByName(String lastName) throws DAOException, NoRecordsFoundException {
        List<Student> students = new ArrayList<>();
        Student currStudent;
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?")){
            ResultSet rs;
            ps.setString(1, lastName + "%");
            rs = ps.executeQuery();
            if(!rs.next()) throw new NoRecordsFoundException();
            do{
                currStudent = new Student();
                currStudent.setId(rs.getInt("ID"));
                currStudent.setFirstName(rs.getString("FIRSTNAME"));
                currStudent.setLastName(rs.getString("LASTNAME"));
                currStudent.setGender(rs.getString("GENDER"));
                currStudent.setBirthDate(DateUtil.fromSQLDate(rs.getDate("BIRTH_DATE")));
                currStudent.setUserId(rs.getInt("USER_ID"));
                currStudent.setCityId(rs.getInt("CITY_ID"));
                students.add(currStudent);
            }while(rs.next());
            return students;

        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to process your request");
        }
    }

    @Override
    public Student getStudentById(int id) throws DAOException, StudentNotFoundException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM STUDENTS WHERE ID = ?")){
            ResultSet rs;
            Student currStudent;
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(!rs.next()) throw new StudentNotFoundException(id);
            currStudent = new Student();
            currStudent.setId(rs.getInt("ID"));
            currStudent.setFirstName(rs.getString("FIRSTNAME"));
            currStudent.setLastName(rs.getString("LASTNAME"));
            currStudent.setGender(rs.getString("GENDER"));
            currStudent.setBirthDate(DateUtil.fromSQLDate(rs.getDate("BIRTH_DATE")));
            currStudent.setUserId(rs.getInt("USER_ID"));
            currStudent.setCityId(rs.getInt("CITY_ID"));
            return currStudent;

        }catch  (SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to process your request");
        }
    }

    @Override
    public Student getStudentByUserName(String username) throws DAOException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM STUDENTS INNER JOIN USERS ON STUDENTS.USER_ID = USERS.ID WHERE USERNAME = ?")){
            ResultSet rs;
            Student currStudent;
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(!rs.next()) return null;
            currStudent = new Student();
            currStudent.setId(rs.getInt("ID"));
            currStudent.setFirstName(rs.getString("FIRSTNAME"));
            currStudent.setLastName(rs.getString("LASTNAME"));
            currStudent.setGender(rs.getString("GENDER"));
            currStudent.setBirthDate(DateUtil.fromSQLDate(rs.getDate("BIRTH_DATE")));
            currStudent.setUserId(rs.getInt("USER_ID"));
            currStudent.setCityId(rs.getInt("CITY_ID"));
            return currStudent;

        }catch  (SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to process your request");
        }

    }

    @Override
    public Student updateStudent(Student student) throws DAOException, StudentNotFoundException {
        try(PreparedStatement ps = connection.prepareStatement("UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ?, GENDER = ?, " +
                "BIRTH_DATE = ?, CITY_ID=?, USER_ID = ?  WHERE ID = ?")){
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getGender());
            ps.setDate(4, DateUtil.toSQLDate(student.getBirthDate()));
            ps.setInt(5, student.getCityId());
            ps.setInt(6, student.getUserId());
            ps.setInt(7, student.getId());
            int i = ps.executeUpdate();
            if(i ==0) throw new StudentNotFoundException(student.getId());
            return student;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to process your request");
        }
    }

    @Override
    public boolean deleteStudent(int id) throws DAOException, StudentNotFoundException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE  FROM STUDENTS WHERE ID = ?")){
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i==0) throw new StudentNotFoundException(id);
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to process your request");
        }
    }

    @Override
    public boolean insertStudent(Student student) throws DAOException {
        boolean isSuccessfull = false;
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO STUDENTS(FIRSTNAME, LASTNAME, " +
                "GENDER, BIRTH_DATE, CITY_ID, USER_ID) VALUES (?, ?, ?, ?, ?, ?)")){

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getGender());
            ps.setDate(4, DateUtil.toSQLDate(student.getBirthDate()));
            ps.setInt(5, student.getCityId());
            ps.setInt(6, student.getUserId());
            int i = ps.executeUpdate();
            if(i > 0) isSuccessfull = true;
            return isSuccessfull;
        }catch(SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to process your request");
        }

    }

    @Override
    public Student getStudentByUserId(int userid) throws DAOException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM STUDENTS WHERE USER_ID = ?")){
            ResultSet rs;
            Student currStudent;
            ps.setInt(1,userid);
            rs = ps.executeQuery();
            if(!rs.next()) return null;
            currStudent = new Student();
            currStudent.setId(rs.getInt("ID"));
            currStudent.setFirstName(rs.getString("FIRSTNAME"));
            currStudent.setLastName(rs.getString("LASTNAME"));
            currStudent.setGender(rs.getString("GENDER"));
            currStudent.setBirthDate(DateUtil.fromSQLDate(rs.getDate("BIRTH_DATE")));
            currStudent.setUserId(rs.getInt("USER_ID"));
            currStudent.setCityId(rs.getInt("CITY_ID"));
            return currStudent;

        }catch  (SQLException e){
            e.printStackTrace();
            throw new DAOException("Database error. Unable to process your request");
        }
    }
}
