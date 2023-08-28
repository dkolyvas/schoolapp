package gr.aueb.cf.schoolpro.dto;

public class TeacherDTO {
	private int id;
	private String firstName;
	private String lastName;
	private String ssn;
	private String speciality;
	private String userName;

	public TeacherDTO(int id, String firstName, String lastName, String ssn, String speciality, String username) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.speciality = speciality;
		this.userName = username;
	}

	public TeacherDTO() {
		
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
