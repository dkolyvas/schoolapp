package gr.aueb.cf.schoolpro.dao.exceptions;

public class NoRecordsFoundException extends Exception {
	public static final long serialVersionUID = 1;
	
	public NoRecordsFoundException() {
		super("No records found");
	}

}
