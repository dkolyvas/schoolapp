package gr.aueb.cf.schoolpro.dao.exceptions;

import gr.aueb.cf.schoolpro.model.Teacher;

public class TeacherNotFoundException extends Exception{
	private static final long serialVersionUID = 1;
	public TeacherNotFoundException(Teacher teacher) {
		super("No teacher with id " + teacher.getId()+ " was found");
	}
	
	public TeacherNotFoundException(int id) {
		super("No teacher with id " + id + " was found");
	}

}
