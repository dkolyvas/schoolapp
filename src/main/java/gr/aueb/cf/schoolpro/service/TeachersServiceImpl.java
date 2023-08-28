package gr.aueb.cf.schoolpro.service;


import gr.aueb.cf.schoolpro.dao.*;
import gr.aueb.cf.schoolpro.dao.exceptions.DAOException;
import gr.aueb.cf.schoolpro.dao.exceptions.InvalidEntryException;
import gr.aueb.cf.schoolpro.dao.exceptions.NoRecordsFoundException;
import gr.aueb.cf.schoolpro.dao.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolpro.dto.TeacherDTO;
import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.model.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeachersServiceImpl implements ITeachersService {
	private IDAOTeachers dao;
	private List<Teacher> list;
	private int index;
	private Map<Integer, String> specialities;

	public Map<Integer, String> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Map<Integer, String> specialities) {
		this.specialities = specialities;
	}

	public Map<Integer, String> getUsers() {
		return users;
	}

	public void setUsers(Map<Integer, String> users) {
		this.users = users;
	}
	public List<String> userList(){
		List<String> userList = new ArrayList<>();
		String currentUser;
		for(Map.Entry<Integer, String> entry : users.entrySet()){

			currentUser = entry.getValue();
			userList.add(currentUser);
		}
		return userList;
	}

	public List<String> specialityList(){
		List<String> specList = new ArrayList<>();
		String currSpec;
		for(Map.Entry<Integer, String> entry : specialities.entrySet()){
			currSpec = entry.getValue();
			specList.add(currSpec);
		}
		return specList;
	}

	private Map<Integer, String> users;
	
	public TeachersServiceImpl(IDAOTeachers dao) throws SQLException {
		this.dao = dao;
		index = 0;
		list = new ArrayList<>();
		IDAOSpecialities daoSpecialities = new SpecilaitiesDAOImpl();
		IDAOUsers daoUsers = new UserDAOImplementation();
		users = daoUsers.returnUserList();
		specialities = daoSpecialities.getSpecialities();

		
	}

	@Override
	public void GetTeachers(String lastName) throws DAOException, NoRecordsFoundException {

		
	}

	@Override
	public TeacherDTO getTeacherById(int id) throws DAOException, TeacherNotFoundException {
		Teacher teacher = dao.getTeacherById(id);
		return maptoDTO(teacher);
	}

	@Override
	public TeacherDTO getTeacherByUsername(String username) throws DAOException {
		Teacher teacher = dao.getTeacherByUsername(username);
		if(teacher != null)	return maptoDTO(teacher);
		else return null;
	}

	@Override
	public List<TeacherDTO> getTeacherList(String lastName) throws DAOException, NoRecordsFoundException {
		List<TeacherDTO> teachers = new ArrayList<>();
		List<Teacher> list = dao.getTeachersByName(lastName);
		for(Teacher teacher: list) {
			teachers.add(maptoDTO(teacher));
		}
		return teachers;

	}

	@Override
	public void UpdateTeacher(TeacherDTO teacher) throws DAOException, TeacherNotFoundException, InvalidEntryException {
		Teacher teacherDAO = maptoDAO(teacher);
		Teacher checkTeacher = dao.getTeacherByUserId(teacherDAO.getUserId());
		if( checkTeacher != null && checkTeacher.getId() != teacher.getId()) throw new InvalidEntryException("To userid αντιστοιεί σε άλλο χρήστη");
		dao.UpdateTeacher(teacherDAO);
		
	}

	@Override
	public void DeleteTeacher(int id) throws DAOException, TeacherNotFoundException {
		dao.DeleteTeacher(id);
		
	}

	@Override
	public void InsertTeacher(TeacherInsertDTO teacher) throws DAOException, InvalidEntryException {
		Teacher teacherDAO = maptoDAO(teacher);
		int userId = teacherDAO.getUserId();
		if(dao.getTeacherByUserId(userId) != null) throw new InvalidEntryException("To userid αντιστοιεί σε άλλο χρήστη");
		dao.Insert(teacherDAO);
		
	}

	@Override
	public TeacherDTO getFirst() throws NoRecordsFoundException {
		if( list.isEmpty()) throw new NoRecordsFoundException();
		index = 0;
		return maptoDTO(list.get(index));
		
	}

	@Override
	public TeacherDTO getPrevious() throws NoRecordsFoundException {
		if( list.isEmpty()) throw new NoRecordsFoundException();
		if(index >0) index--;
		return maptoDTO(list.get(index));
	}

	@Override
	public TeacherDTO getNext() throws NoRecordsFoundException {
		if( list.isEmpty()) throw new NoRecordsFoundException();
		if(index < list.size()-1) index++;
		return maptoDTO(list.get(index));
		
	}

	@Override
	public TeacherDTO getLast() throws NoRecordsFoundException {
		if( list.isEmpty()) throw new NoRecordsFoundException();
		index = list.size()-1;
		return maptoDTO(list.get(index));
	}
	
	private int findIndexOfSpeciality(String speciality) throws InvalidEntryException {
		int specialityId = -1;
		for(Map.Entry<Integer, String> entry: specialities.entrySet()) {
			if (entry.getValue().equals(speciality)) {
				specialityId = entry.getKey();
			}
		}
		if(specialityId == -1) throw new InvalidEntryException("Speciality : " + speciality +" does not exist");
		return specialityId;
			
		
	}
	
	private int findIndexOfUser(String username) throws InvalidEntryException {
		int userId = -1;
		for(Map.Entry<Integer, String> entry : users.entrySet()) {
			if(entry.getValue().equals(username)) userId = entry.getKey();
		}
		if(userId == -1) throw new InvalidEntryException("Username :" + username + "does not exist");
		return userId;
	}

	
	private Teacher maptoDAO(TeacherDTO teacher) throws InvalidEntryException {

		Teacher toDAO = new Teacher();
		toDAO.setId(teacher.getId());
		toDAO.setFirstName(teacher.getFirstName());
		toDAO.setLastName(teacher.getLastName());
		toDAO.setSSN(Integer.parseInt(teacher.getSsn()));

		toDAO.setSpecialtyId(findIndexOfSpeciality(teacher.getSpeciality()));
		toDAO.setUserId(findIndexOfUser(teacher.getUserName()));
		return toDAO;
		
	}
	
	private Teacher maptoDAO(TeacherInsertDTO teacher) throws InvalidEntryException {
		Teacher toDAO = new Teacher();
		
		toDAO.setFirstName(teacher.getFirstName());
		toDAO.setLastName(teacher.getLastName());
		toDAO.setSSN(Integer.parseInt(teacher.getSsn()));
		toDAO.setUserId(findIndexOfUser(teacher.getUserName()));
		toDAO.setSpecialtyId(findIndexOfSpeciality(teacher.getSpeciality()));
		
		return toDAO;
		
	}
	
	private TeacherDTO maptoDTO(Teacher teacher) {
		TeacherDTO dto = new TeacherDTO();
		dto.setId(teacher.getId());
		dto.setFirstName(teacher.getFirstName());
		dto.setLastName(teacher.getLastName());
		dto.setSsn(String.valueOf(teacher.getSSN()));
		dto.setSpeciality(specialities.get(teacher.getSpecialtyId()));
		dto.setUserName(users.get(teacher.getUserId()));
		
		return dto;
		
	}

}
