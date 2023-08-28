package gr.aueb.cf.schoolpro.dto;

public class TeacherInsertDTO {
	private String firstName;
	private String lastName;

	private String ssn;
	private String speciality;
	private String userName;

	
	public TeacherInsertDTO() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public TeacherInsertDTO(String firstName, String lastName, String ssn, String speciality, String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.speciality = speciality;
		this.userName = userName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
