package DatabaseLayer;

//List imports here
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Class Name:		DatabaseDeleter
 * Description:		This class contains the methods called using data access objects to Delete information
 * 					from the database, and also methods which call these methods directly.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */

public class DatabaseDeleter implements DeleterDAO {
	
	// Declare variables
	private static Connection connObj = null;		// Changed to static
	private DeleteHelper deleteHelper;
	
	// Define methods
	public DatabaseDeleter() {	
		deleteHelper = new DeleteHelper();
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
		String url = "jdbc:mysql://127.0.0.1:3306/auto_parts_schema";
		String username = "autouser";
		String password = "autouser";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		//System.out.println("DatabaseDeleter - Connection Established!");
		
		
	
					
		DatabaseWriter.closeConnection(connObj);
			
		
		return connection;
	}
	
	// Define methods to delete data
}
