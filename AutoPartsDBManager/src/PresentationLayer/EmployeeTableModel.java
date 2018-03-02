package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.Employee;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;
import DatabaseLayer.ReaderDAO;

/**
 * Extends AbstartTableModel to build a table for employees Frame.
 * Written by Michael Meesseman
 */
public class EmployeeTableModel extends AbstractTableModel{
	
			//static private ReaderDAO readerDAO;

	//return list for some methods initialized
		    private List<Employee> employees;

		    private ReaderDAO readerDAO = DAOFactory.getReaderDAO();

		  //table column names

		    private final String[] COLUMN_NAMES = {"Employee ID", "Last Name", 
		    		"First Name", "Contact Info ID", "Address ID", "Street Address", 
		    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
		    		"Email Address"};
		    
		    /**
		     * Constructor to build the table.  Pulls into list from database.
		     * @exception SQLException	exception for database queries.
		     * Written by Michael Meesseman
		     */
		    public EmployeeTableModel() throws SQLException{
		        employees = readerDAO.obtainEmployeeList();
		    }
		    
		    /**
		     * Method returns number of rows for table.  REQUIRED.
		     * @return returns the number of rows
		     * Written by Michael Meesseman
		     */
		    @Override 
		    public int getRowCount() {
		        return employees.size();
		    }
		    
		    
		    /**
		     * Method returns number of columns for table.  REQUIRED.
		     * @return returns the number of columns
		     * Written by Michael Meesseman
		     */
		    @Override
		    public int getColumnCount() {
		        return COLUMN_NAMES.length;
		    }
		    
		    /**
		     * Method returns the column names for the table.  REQUIRED.
		     * @return returns the column names
		     * Written by Michael Meesseman
		     */
		    @Override
		    public String getColumnName(int columnIndex) {
		        return COLUMN_NAMES[columnIndex];
		    }
		    
		    /**
		     * Method returns data at specifc row and column.  REQUIRED.
		     * @param rowIndex		row number from table
		     * @param columnIndex 	column number from table
		     * @return returns the object being queried.
		     * Written by Michael Meesseman
		     */
		    @Override
		    public Object getValueAt(int rowIndex, int columnIndex) {
		        switch (columnIndex) {
		            case 0:
		                return employees.get(rowIndex).getEmployeeID();
		            case 1:
		                return employees.get(rowIndex).getLastName();
		            case 2:
		                return employees.get(rowIndex).getFirstName();
		            case 3: 
		            	return employees.get(rowIndex).getContactInfoID();
		            case 4: 
		            	return employees.get(rowIndex).getAddressID();
		            case 5: 
		            	return employees.get(rowIndex).getStreetAddress();
		            case 6: 
		            	return employees.get(rowIndex).getCity();
		            case 7:
		            	return employees.get(rowIndex).getState();
		            case 8:
		            	return employees.get(rowIndex).getZipCode();
		            case 9:
		            	return employees.get(rowIndex).getUnitNumber();
		            case 10:
		            	return employees.get(rowIndex).getPhoneNumber();
		            case 11:
		            	return employees.get(rowIndex).getCellPhoneNumber();
		            case 12:
		            	return employees.get(rowIndex).getEmailAddress();
		            default:
		                return null;
		        }
		    }
		    
		    /**
		     * Method returns selected row object. 
		     * @return employee object of selected row
		     * Written by Michael Meesseman
		     */
		    Employee getEmployee(int rowIndex) throws SQLException {
		        return employees.get(rowIndex);
		    }
		    
		    /**
		     * Method updates the table with a fresh database query.
		     * @exception SQLException	exception for database queries.
		     * Written by Michael Meesseman
		     */
		    void databaseUpdated() throws SQLException{
		    	employees = readerDAO.obtainEmployeeList();
				fireTableDataChanged();
		    }
		    
		    /**
		     * Method updates the table with a filtered database query.
		     * @param column 	String of the column name to be searched
		     * @param search	String of text to search for.
		     * Written by Michael Meesseman
		     */
		    public void refresh(String column, String search){
		    	employees = readerDAO.obtainEmployeeFilter(column, search);
		        fireTableDataChanged();
		    }
		    
		    /**
		     * Method is used to see if the database query returned a result.
		     * @param column 	String of the column name to be searched
		     * @param search	String of text to search for.
		     * @return employees	returns list of employees to see if a search result was found.
		     * Written by Michael Meesseman
		     */
		    public List<Employee> resultChecker(String column, String search) {
		    	
		    	List<Employee> employeess = readerDAO.obtainEmployeeFilter(column, search);
		    	
		    	return employeess;
		    }
		    
		    /**
		     * Method updates the table with a fresh database query.
		     * Written by Michael Meesseman
		     */
		    public void reset(){
		    	employees = readerDAO.obtainEmployeeList();
		        fireTableDataChanged();
		    }
		


	
	
}
