package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.Customer;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;

/**
 * Extends AbstartTableModel to build a table for customer Frame.
 * Written by Michael Meesseman
 */
public class CustomerTableModel extends AbstractTableModel {

		//static private ReaderDAO readerDAO;
		
	//return list for some methods initialized
	    private List<Customer> customers;
	    private ReaderDAO readerDAO = DAOFactory.getReaderDAO();
	    
	  //table column names
	    private final String[] COLUMN_NAMES = {"Customer ID", "Last Name", 
	    		"First Name", "Contact Info ID", "Address ID", "Street Address", 
	    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
	    		"Email Address"};
	    
	    /**
	     * Constructor to build the table.  Pulls into list from database.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    public CustomerTableModel() throws SQLException{
	        customers = readerDAO.obtainCustomerList();
	    }
	    
	    /**
	     * Method returns number of rows for table.  REQUIRED.
	     * @return returns the number of rows
	     * Written by Michael Meesseman
	     */
	    @Override 
	    public int getRowCount() {
	        return customers.size();
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
	                return customers.get(rowIndex).getCustomerID();
	            case 1:
	                return customers.get(rowIndex).getLastName();
	            case 2:
	                return customers.get(rowIndex).getFirstName();
	            case 3: 
	            	return customers.get(rowIndex).getContactInfoID();
	            case 4: 
	            	return customers.get(rowIndex).getAddressID();
	            case 5: 
	            	return customers.get(rowIndex).getStreetAddress();
	            case 6: 
	            	return customers.get(rowIndex).getCity();
	            case 7:
	            	return customers.get(rowIndex).getState();
	            case 8:
	            	return customers.get(rowIndex).getZipCode();
	            case 9:
	            	return customers.get(rowIndex).getUnitNumber();
	            case 10:
	            	return customers.get(rowIndex).getPhoneNumber();
	            case 11:
	            	return customers.get(rowIndex).getCellPhoneNumber();
	            case 12:
	            	return customers.get(rowIndex).getEmailAddress();
	            default:
	                return null;
	        }
	    }
	    
	    /**
	     * Method returns selected row object. 
	     * @return customer object of selected row
	     * Written by Michael Meesseman
	     */
	    Customer getCustomer(int rowIndex) throws SQLException {
	        return customers.get(rowIndex);
	    }
	    
	    /**
	     * Method updates the table with a fresh database query.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    void databaseUpdated() throws SQLException{
	        customers = readerDAO.obtainCustomerList();
			fireTableDataChanged();
	    }
	    
	    /**
	     * Method updates the table with a filtered database query.
	     * @param column 	String of the column name to be searched
	     * @param search	String of text to search for.
	     * Written by Michael Meesseman
	     */
	    public void refresh(String column, String search){
	        customers = readerDAO.obtainCustomerFilter(column, search);
	       
	        fireTableDataChanged();
	    }
	    
	    /**
	     * Method is used to see if the database query returned a result.
	     * @param column 	String of the column name to be searched
	     * @param search	String of text to search for.
	     * @return customer	returns list of customers to see if a search result was found.
	     * Written by Michael Meesseman
	     */
	    public List<Customer> resultChecker(String column, String search) {
	    	
	    	List<Customer> customer = readerDAO.obtainCustomerFilter(column, search);
	    	
	    	return customer;
	    }
	    
	    /**
	     * Method updates the table with a fresh database query.
	     * Written by Michael Meesseman
	     */
	    public void reset(){
	        customers = readerDAO.obtainCustomerList();
	        fireTableDataChanged();
	    }
	}

