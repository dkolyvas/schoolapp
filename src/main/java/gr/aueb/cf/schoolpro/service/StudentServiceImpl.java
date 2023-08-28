package gr.aueb.cf.schoolpro.service;



import gr.aueb.cf.schoolpro.dao.*;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolpro.dto.StudentDTO;
import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.model.Student;
import gr.aueb.cf.schoolpro.util.DateUtil;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements IStudentService {
    private Map<Integer, String> users;
    private Map<Integer, String> cities;

    private IDAOStudents dao;

    public StudentServiceImpl(IDAOStudents dao) throws SQLException {
        this.dao = dao;
        IDAOCities daoCities = new CitiesDAOImpl();
        IDAOUsers daoUsers = new UserDAOImplementation();
        users = daoUsers.returnUserList();
        cities = daoCities.getCities();

    }

    @Override
    public List<StudentDTO> getStudents(String name) throws DAOException, NoRecordsFoundException {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        List<Student> students = dao.getStudentByName(name);
        students.forEach(student -> {
            studentDTOList.add(mapToDTO(student));
        });
        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentById(int id) throws DAOException, StudentNotFoundException {

        return mapToDTO(dao.getStudentById(id));
    }

    @Override
    public StudentDTO getStudentByUsername(String username) throws DAOException {
        Student student = dao.getStudentByUserName(username);
        if(student != null) return mapToDTO(student);
        else return null;
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws DAOException, StudentNotFoundException, InvalidEntryException, ParseException{
        boolean result = false;
        Student studentDAo = mapToDao(student);
        Student checkStudent = dao.getStudentByUserId(studentDAo.getUserId());
        if(checkStudent != null && checkStudent.getId() != student.getId()) throw new InvalidEntryException("Το username αντιστοιχεί σε άλλο χρήστη");
        Student updatedStudent = dao.updateStudent(studentDAo);
        if(updatedStudent != null) result = true;
        return result;
    }

    @Override
    public boolean deleteStudent(int id) throws DAOException, StudentNotFoundException {
        boolean result = false;
        result = dao.deleteStudent(id);
        return result;
    }

    @Override
    public boolean insertStudent(StudentInsertDTO student) throws DAOException, InvalidEntryException, ParseException {
        Student studentDao = mapToDao(student);
        boolean result = false;
        if(dao.getStudentByUserId(studentDao.getUserId()) != null) throw new InvalidEntryException("Το username αντιστοιχεί σε άλλο χρήστη");
        result = dao.insertStudent(studentDao);
        return  result;
    }

    @Override
    public List<String> getCities() {
        List<String> cityList = new ArrayList<>();
        for(Map.Entry<Integer, String> entry : cities.entrySet()){
            cityList.add(entry.getValue());
        }
        return cityList;
    }

    @Override
    public List<String> getUsers() {
        List<String> userList = new ArrayList<>();
        for(Map.Entry<Integer, String> entry : users.entrySet()){
            userList.add(entry.getValue());
        }
        return userList;
    }

    private int findUserId(String username) throws InvalidEntryException {
        int userId = -1;
        for(Map.Entry<Integer, String>  entry: users.entrySet()){
            if(entry.getValue().equals(username)) userId =  entry.getKey();
        }
        if(userId == -1) throw new InvalidEntryException("Username: " + username);
        return userId;
    }

    private int findCityId(String city) throws InvalidEntryException{
        int cityId = -1;
        for(Map.Entry<Integer, String> entry: cities.entrySet()){
            if(entry.getValue().equals(city)) cityId = entry.getKey();
        }
        if (cityId == -1) throw new InvalidEntryException("City: " + city);
        return cityId;
    }

    private Student mapToDao(StudentDTO dto) throws InvalidEntryException, ParseException {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        student.setBirthDate(DateUtil.toDate(dto.getBirthDate()));
        student.setCityId(findCityId(dto.getCity()));
        student.setUserId(findUserId(dto.getUsername()));
        return student;
    }
    private Student mapToDao(StudentInsertDTO dto) throws InvalidEntryException, ParseException{
        Student student = new Student();
        student.setId(-1);
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        student.setBirthDate(DateUtil.toDate(dto.getBirthDate()));
        student.setCityId(findCityId(dto.getCity()));
        student.setUserId(findUserId(dto.getUsername()));
        return student;
    }

    private StudentDTO mapToDTO(Student student){
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setGender(student.getGender());
        dto.setBirthDate(DateUtil.toString(student.getBirthDate()));
        dto.setUsername(users.get(student.getUserId()));
        dto.setCity(cities.get(student.getCityId()));
        return dto;
    }
}
