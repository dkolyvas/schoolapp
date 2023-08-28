package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TeachersDAOImplementation implements IDAOTeachers {
	private Connection connection;
	
	public TeachersDAOImplementation() throws DAOException {
		try {
			connection = DBUtil.getConnection();
		}catch (SQLException e){
			e.printStackTrace();
			throw new DAOException("Error establishing connection with the database");
		}

	}

	@Override
	public List<Teacher> getTeachersByName(String lastNameLike) throws DAOException, NoRecordsFoundException{
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?")) {
			ResultSet rs;

			ps.setString(1, lastNameLike + "%");
			rs = ps.executeQuery();

			List<Teacher> list = new ArrayList<>();
			while (rs.next()) {
				Teacher currTeacher = new Teacher();
				currTeacher.setId(rs.getInt("ID"));
				currTeacher.setFirstName(rs.getString("FIRSTNAME"));
				currTeacher.setLastName(rs.getString("LASTNAME"));
				currTeacher.setSSN(rs.getInt("SSN"));
				currTeacher.setSpecialtyId(rs.getInt("SPECIALITY_ID"));
				currTeacher.setUserId(rs.getInt("USER_ID"));
				list.add(currTeacher);
			}
			if (list.isEmpty()) throw new NoRecordsFoundException();
			return list;
		}catch (SQLException e){
			e.printStackTrace();
			throw new DAOException("Error with database communication. Unable to process your request");
		}
	}

	@Override
	public Teacher getTeacherById(int id) throws DAOException, TeacherNotFoundException {
		Teacher currTeacher;


		try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM TEACHERS WHERE ID = ?");){
			ps.setInt(1,id);
			ResultSet rs;
			rs = ps.executeQuery();
			if(!rs.next()) throw new TeacherNotFoundException(id);
			currTeacher = new Teacher();
			currTeacher.setId(rs.getInt("ID"));
			currTeacher.setFirstName(rs.getString("FIRSTNAME"));
			currTeacher.setLastName(rs.getString("LASTNAME"));
			currTeacher.setSSN(rs.getInt("SSN"));
			currTeacher.setSpecialtyId(rs.getInt("SPECIALITY_ID"));
			currTeacher.setUserId(rs.getInt("USER_ID"));
			return currTeacher;
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error in database communication. Unable process your request");
		}
	}

	@Override
	public Teacher getTeacherByUsername(String username) throws DAOException {
		Teacher currTeacher;


		try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM TEACHERS INNER JOIN USERS ON TEACHERS.USER_ID = USERS.ID WHERE USERNAME = ?");){
			ps.setString(1,username);
			ResultSet rs;
			rs = ps.executeQuery();
			if(!rs.next()) return null;
			currTeacher = new Teacher();
			currTeacher.setId(rs.getInt("ID"));
			currTeacher.setFirstName(rs.getString("FIRSTNAME"));
			currTeacher.setLastName(rs.getString("LASTNAME"));
			currTeacher.setSSN(rs.getInt("SSN"));
			currTeacher.setSpecialtyId(rs.getInt("SPECIALITY_ID"));
			currTeacher.setUserId(rs.getInt("USER_ID"));
			return currTeacher;
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error in database communication. Unable process your request");
		}
	}

	@Override
	public boolean DeleteTeacher(int id) throws DAOException, TeacherNotFoundException {
		try(PreparedStatement ps = connection.prepareStatement("DELETE FROM TEACHERS WHERE ID = ?")) {

			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if(i==0) throw new TeacherNotFoundException(id);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException("Error in database communication. Unable to process your request");
		}
		
	}

	@Override
	public Teacher UpdateTeacher(Teacher teacher) throws DAOException, TeacherNotFoundException{
		String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ?,  SSN = ?, SPECIALITY_ID =?, USER_ID = ? WHERE ID = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, teacher.getFirstName());
			ps.setString(2, teacher.getLastName());
			ps.setInt(3, teacher.getSSN());
			ps.setInt(4, teacher.getSpecialtyId());
			ps.setInt(5, teacher.getUserId());
			ps.setInt(6, teacher.getId());
			int i = ps.executeUpdate();
			if (i == 0) throw new TeacherNotFoundException(teacher);
			return teacher;
					
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException("Database communication error.Unable to procaess your request" );
		}
		
	}

	@Override
	public Teacher getTeacherByUserId(int userid) throws DAOException {
		Teacher currTeacher;


		try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM TEACHERS WHERE USER_ID = ?");){
			ps.setInt(1,userid);
			ResultSet rs;
			rs = ps.executeQuery();
			if(!rs.next()) return null;
			currTeacher = new Teacher();
			currTeacher.setId(rs.getInt("ID"));
			currTeacher.setFirstName(rs.getString("FIRSTNAME"));
			currTeacher.setLastName(rs.getString("LASTNAME"));
			currTeacher.setSSN(rs.getInt("SSN"));
			currTeacher.setSpecialtyId(rs.getInt("SPECIALITY_ID"));
			currTeacher.setUserId(rs.getInt("USER_ID"));
			return currTeacher;
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error in database communication. Unable process your request");
		}
	}


	@Override
	public void Insert(Teacher teacher) throws DAOException {
		String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME, SSN, SPECIALITY_ID , USER_ID ) VALUES(?,?,?,?,?)";
		try(PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, teacher.getFirstName());
			ps.setString(2, teacher.getLastName());
			ps.setInt(3, teacher.getSSN());
			ps.setInt(4, teacher.getSpecialtyId());
			ps.setInt(5, teacher.getUserId());
			int i = ps.executeUpdate();


	}catch(SQLException e) {
		e.printStackTrace();
		throw new DAOException("Database error. Unable to process your request");
	}
		
	}

}
