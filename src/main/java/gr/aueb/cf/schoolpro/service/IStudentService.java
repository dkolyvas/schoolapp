package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;

import java.text.ParseException;
import java.util.List;

public interface IStudentService {
    List<StudentDTO> getStudents(String name) throws DAOException, NoRecordsFoundException;
    StudentDTO getStudentById(int id) throws DAOException, StudentNotFoundException;
    StudentDTO getStudentByUsername(String username) throws DAOException;
    boolean updateStudent(StudentDTO student) throws DAOException, StudentNotFoundException, InvalidEntryException, ParseException;
    boolean deleteStudent(int id) throws DAOException, StudentNotFoundException;
    boolean insertStudent(StudentInsertDTO student) throws DAOException, InvalidEntryException, ParseException;
    List<String> getCities();
    List<String> getUsers();


}
