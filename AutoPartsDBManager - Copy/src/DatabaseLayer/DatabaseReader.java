package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// List imports here

/**
 * Class Name:		DatabaseReader
 * Description:		This class contains the methods called using data access objects to Read information
 * 					from the database, and also methods which call these methods directly.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */

public class DatabaseReader implements ReaderDAO {
	
	// Declare variables
	private static Connection connObj = null;	// Changed to static
	private ReadHelper readHelper = null;
	
	
	// Define methods
	/**
	 * This is the Constructor which is called from the DAOFactory class.
	 * This method also calls the ReadHelper Constructor to provide an instance of that class.
	 */
	public DatabaseReader() {
		readHelper = new ReadHelper();
		connObj = getDBConnection();
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
		
		System.out.println("DatabaseReader - Connection Established!");
		
		
	
					
		DatabaseWriter.closeConnection(connObj);
			
		
		return connection;
	}
}
