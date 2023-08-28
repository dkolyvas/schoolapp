package gr.aueb.cf.schoolpro.dao;

import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.model.Student;

import java.util.List;

public interface IDAOStudents {
    public List<Student> getStudentByName(String lastName) throws DAOException, NoRecordsFoundException;

    public Student getStudentById(int id ) throws DAOException, StudentNotFoundException;

    public Student getStudentByUserName(String username) throws DAOException;

    public Student updateStudent(Student student) throws DAOException, StudentNotFoundException;

    public boolean deleteStudent(int id) throws DAOException, StudentNotFoundException;

    public boolean insertStudent(Student student) throws DAOException;

    public Student getStudentByUserId(int userid) throws DAOException;


}
