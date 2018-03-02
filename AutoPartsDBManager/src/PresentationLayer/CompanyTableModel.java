package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.Company;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;


/**
 * Extends AbstartTableModel to build a table for companies Frame.
 * Written by Michael Meesseman
 */
public class CompanyTableModel extends AbstractTableModel{
	
				private static ReaderDAO readerDAO = DAOFactory.getReaderDAO();;
	
				//readerDAO = DAOFactory.getReaderDAO();
	
				//return list for some methods initialized
			    private List<Company> companies;
			    
			    //table column names
			    private final String[] COLUMN_NAMES = {"Company ID", "Address ID", "Contact Info ID", 
			    		"Company Name", "Street Address", "City", "State", "Zip Code", 
			    		"Unit Number", "Home Phone", "Cell Phone", "Email Address"};
			    
			    /**
			     * Constructor to build the table.  Pulls into list from database.
			     * @exception SQLException	exception for database queries.
			     * Written by Michael Meesseman
			     */
			    public CompanyTableModel() throws SQLException{

			        companies = readerDAO.obtainCompanyList();
			    }
			    
			    @Override 
			    /**
			     * Method returns number of rows for table.  REQUIRED.
			     * @return returns the number of rows
			     * Written by Michael Meesseman
			     */
			    public int getRowCount() {
			        return companies.size();
			  
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
			                return companies.get(rowIndex).getCompanyID();
			            case 1: 
			            	return companies.get(rowIndex).getAddressID();
			            case 2: 
			            	return companies.get(rowIndex).getContactInfoID();
			            case 3: 
			            	return companies.get(rowIndex).getCompanyName();
			            case 4:
			            	return companies.get(rowIndex).getStreetAddress();
			            case 5:
			            	return companies.get(rowIndex).getCity();
			            case 6:
			            	return companies.get(rowIndex).getState();
			            case 7:
			            	return companies.get(rowIndex).getZipCode();
			            case 8:
			            	return companies.get(rowIndex).getUnitNumber();
			            case 9:
			            	return companies.get(rowIndex).getPhoneNumber();
			            case 10:
			            	return companies.get(rowIndex).getCellPhoneNumber();
			            case 11: 
			            	return companies.get(rowIndex).getEmailAddress();
			            default:
			                return null;
			        }
			    }
			    
			    /**
			     * Method returns selected row object. 
			     * @return company object of selected row
			     * Written by Michael Meesseman
			     */
			    Company getCompanies(int rowIndex) throws SQLException {
			        return companies.get(rowIndex);
			    }
			    
			    /**
			     * Method updates the table with a fresh database query.
			     * @exception SQLException	exception for database queries.
			     * Written by Michael Meesseman
			     */
			    void databaseUpdated() throws SQLException{
			        companies = readerDAO.obtainCompanyList();
					fireTableDataChanged();
			    }
			    
			    /**
			     * Method updates the table with a filtered database query.
			     * @param column 	String of the column name to be searched
			     * @param search	String of text to search for.
			     * Written by Michael Meesseman
			     */
			    public void refresh(String column, String search){
			        companies = readerDAO.obtainCompanyFilter(column, search);
			        fireTableDataChanged();
			    }
			    
			    /**
			     * Method is used to see if the database query returned a result.
			     * @param column 	String of the column name to be searched
			     * @param search	String of text to search for.
			     * @return company	returns list of companies to see if a search result was found.
			     * Written by Michael Meesseman
			     */
			    public List<Company> resultChecker(String column, String search) {
			    	
			    	List<Company> company = readerDAO.obtainCompanyFilter(column, search);
			    	
			    	return company;
			    }
			    
			    /**
			     * Method updates the table with a fresh database query.
			     * Written by Michael Meesseman
			     */
			    public void reset(){
			        companies = readerDAO.obtainCompanyList();
			        fireTableDataChanged();
			    }
			


}
