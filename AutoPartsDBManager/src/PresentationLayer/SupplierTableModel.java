package PresentationLayer;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import BusinessLayer.Company;
import BusinessLayer.Supplier;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;

/**
 * Extends AbstartTableModel to build a table for sales item Frame.
 * Written by Michael Meesseman
 */
public class SupplierTableModel extends AbstractTableModel {

	//return list for some methods initialized
		    private List<Supplier> suppliers;
		    private List<Company> company;
		    private ReaderDAO readerDAO = DAOFactory.getReaderDAO();

		  //table column names
		    private final String[] COLUMN_NAMES = {"Supplier ID", "Last Name", 
		    		"First Name", "Contact Info ID", "Address ID", "Company ID", "Street Address",
		    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
		    		"Email Address", "Company Name"};
		    
		    /**
		     * Constructor to build the table.  Pulls into list from database.
		     * @exception SQLException	exception for database queries.
		     * @param invoiceNumberInput	brings in invoice number selected for query.
		     * Written by Michael Meesseman
		     */
		    public SupplierTableModel() throws SQLException{
		        suppliers = readerDAO.obtainSupplierList();
		
		    }
		    
		    /**
		     * Method returns number of rows for table.  REQUIRED.
		     * @return returns the number of rows
		     * Written by Michael Meesseman
		     */
		    @Override 
		    public int getRowCount() {
		        return suppliers.size();
		  
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
		                return suppliers.get(rowIndex).getSupplierID();
		            case 1:
		                return suppliers.get(rowIndex).getLastName();
		            case 2:
		                return suppliers.get(rowIndex).getFirstName();
		            case 3: 
		            	return suppliers.get(rowIndex).getContactInfoID();
		            case 4: 
		            	return suppliers.get(rowIndex).getAddressID();
		            case 5: 
		            	return suppliers.get(rowIndex).getCompanyID();
		            case 6:
		            	return suppliers.get(rowIndex).getStreetAddress();
		            case 7:
		            	return suppliers.get(rowIndex).getCity();
		            case 8:
		            	return suppliers.get(rowIndex).getState();
		            case 9:
		            	return suppliers.get(rowIndex).getZipCode();
		            case 10:
		            	return suppliers.get(rowIndex).getUnitNumber();
		            case 11:
		            	return suppliers.get(rowIndex).getPhoneNumber();
		            case 12:
		            	return suppliers.get(rowIndex).getCellPhoneNumber();
		            case 13: 
		            	return suppliers.get(rowIndex).getEmailAddress();
		            case 14: 
		            	return suppliers.get(rowIndex).getCompanyName();
		            default:
		                return null;
		        }
		    }
		    
		    /**
		     * Method returns selected row object. 
		     * @return supplier object of selected row
		     * Written by Michael Meesseman
		     */
		    Supplier getSuppliers(int rowIndex) throws SQLException {
		        return suppliers.get(rowIndex);
		    }
		    
		    /**
		     * Method updates the table with a fresh database query.
		     * @exception SQLException	exception for database queries.
		     * Written by Michael Meesseman
		     */
		    void databaseUpdated() throws SQLException{
		        suppliers = readerDAO.obtainSupplierList();
				fireTableDataChanged();
		    }
		    
		    /**
		     * Method updates the table with a filtered database query.
		     * @param column 	String of the column name to be searched
		     * @param search	String of text to search for.
		     * Written by Michael Meesseman
		     */
		    public void refresh(String column, String search){
		        suppliers = readerDAO.obtainSupplierFilter(column, search);
		        fireTableDataChanged();
		    }
		    
		    /**
		     * Method is used to see if the database query returned a result.
		     * @param column 	String of the column name to be searched
		     * @param search	String of text to search for.
		     * @return suppliers	returns list of suppliers to see if a search result was found.
		     * Written by Michael Meesseman
		     */
		    public List<Supplier> resultChecker(String column, String search) {
		    	
		    	List<Supplier> suppliers = readerDAO.obtainSupplierFilter(column, search);
		    	
		    	return suppliers;
		    }
		    
		    /**
		     * Method updates the table with a fresh database query.
		     * Written by Michael Meesseman
		     */
		    public void reset(){
		        suppliers = readerDAO.obtainSupplierList();
		        fireTableDataChanged();
		    }
		}




