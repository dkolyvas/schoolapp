package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.model.Teacher;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface IDAOTeachers {
	public List<Teacher> getTeachersByName(String lastNameLike) throws DAOException, NoRecordsFoundException;
	public Teacher getTeacherById(int id) throws DAOException, TeacherNotFoundException;
	public Teacher getTeacherByUsername(String username) throws DAOException;
	public boolean DeleteTeacher(int id) throws DAOException, TeacherNotFoundException;
	public Teacher UpdateTeacher(Teacher teacher) throws DAOException, TeacherNotFoundException;
	public Teacher getTeacherByUserId(int userid) throws DAOException;

	public void Insert(Teacher teacher) throws DAOException;
	

}
