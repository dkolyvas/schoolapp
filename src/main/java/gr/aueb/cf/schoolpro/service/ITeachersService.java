package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.model.Teacher;

import java.util.List;

public interface ITeachersService {
	public void GetTeachers(String lastName) throws DAOException, NoRecordsFoundException;
	public TeacherDTO getTeacherById(int id) throws DAOException, TeacherNotFoundException;
	public TeacherDTO getTeacherByUsername(String username) throws DAOException;
	public List<TeacherDTO> getTeacherList(String lastName) throws DAOException, NoRecordsFoundException;

	public void UpdateTeacher(TeacherDTO teacher) throws DAOException, TeacherNotFoundException, InvalidEntryException;
	public void DeleteTeacher(int id) throws DAOException, TeacherNotFoundException;
	public void InsertTeacher(TeacherInsertDTO teacher) throws DAOException, InvalidEntryException;
	public TeacherDTO getFirst() throws NoRecordsFoundException;
	public TeacherDTO getPrevious() throws NoRecordsFoundException;
	public TeacherDTO getNext() throws NoRecordsFoundException;
	public TeacherDTO getLast() throws NoRecordsFoundException;

}
