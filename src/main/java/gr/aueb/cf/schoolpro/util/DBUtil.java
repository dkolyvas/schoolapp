package gr.aueb.cf.schoolpro.util;





import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class DBUtil {

	private static BasicDataSource ds = new BasicDataSource();
	private static Connection connection;
	
	private DBUtil() {
		
	}
	
	static {

		ds.setUrl("jdbc:mysql://localhost:3306/schooldbpro?serverTimezone=UTC");
		ds.setUsername("schoolpro");
		ds.setPassword(System.getenv("SCHOOL_DB_USER_PASSWD"));
		ds.setInitialSize(8);
		ds.setMaxTotal(32);
		ds.setMinIdle(8);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);
	}
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = ds.getConnection();
			return connection;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			throw new SQLException();
		}
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
