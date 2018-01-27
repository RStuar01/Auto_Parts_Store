package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// List imports

/**
 * Class Name:		DatabaseWriter
 * Description:		This class contains the methods called using data access objects to Write information
 * 					to the database, and also methods which call these methods directly.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
public class DatabaseWriter implements WriterDAO {
	private static Connection connObj = null;	// Changed to static for testing
	private WriteHelper writerHelper = null;
	
	/**
	 * This is the Constructor that is called from the DAOFactory class.
	 * This method also calls the WriteHelper Constructor to provide an instance of that class.
	 */
	public DatabaseWriter() {
		
		writerHelper = new WriteHelper();
		connObj = getDBConnection();
		//getNumAppts10Days(connObj);
		closeConnection(connObj);
	}
	
	/**
	 * This method closes the database connection.
	 * @param connObj				The connection object.
	 */
	static void closeConnection(Connection connObj) {
		
		if(connObj != null) {
			try {
				connObj.close();
			}
			catch (SQLException ignore) {
			}
		}
	}
	
	/**
	 * This method obtains a connection to the database.
	 * @return connection			The database connection object.
	 */
	static Connection getDBConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		//String url = "jdbc:mysql://localhost:3306/mydb";
		String url = "jdbc:mysql://localhost:3306/auto_parts_schema";
		String username = "root";
		String password = "rick6022";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("DatabaseWriter - Connection Established!");
		
		// check a simple read - REMOVE THIS LATER
		String query = "SELECT last_name FROM customer WHERE customer_id = 1";
		Statement stmt = null;
		String lastName = null;
		try {	
			//stmt = connObj.createStatement();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				lastName = rs.getString(1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
					
			System.out.println("The value read from the database is:");
			System.out.println(lastName);
		
		return connection;
	}
}
