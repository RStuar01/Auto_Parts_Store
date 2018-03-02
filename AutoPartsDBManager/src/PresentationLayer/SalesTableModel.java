package PresentationLayer;


import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.Invoice;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;

/**
 * Extends AbstartTableModel to build a table for sales invoice Frame.
 * Written by Michael Meesseman
 */
public class SalesTableModel extends AbstractTableModel {
	
					
	//return list for some methods initialized
				    private static List<Invoice> invoices;
				    private ReaderDAO readerDAO = DAOFactory.getReaderDAO();

				  //table column names
				    private final String[] COLUMN_NAMES = {"Invoice Number", "Date", 
				    		"Time", "Customer ID", "Employee ID"};
				    
				    /**
				     * Constructor to build the table.  Pulls into list from database.
				     * @exception SQLException	exception for database queries.
				     * Written by Michael Meesseman
				     */
				    public SalesTableModel() throws SQLException{
				        invoices = readerDAO.obtainInvoiceList();
				    }
				    
				    /**
				     * Method returns number of rows for table.  REQUIRED.
				     * @return returns the number of rows
				     * Written by Michael Meesseman
				     */
				    @Override 
				    public int getRowCount() {
				        return invoices.size();
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
				                return invoices.get(rowIndex).getInvoiceNumber();
				            case 1:
				                return invoices.get(rowIndex).getDate();
				            case 2:
				                return invoices.get(rowIndex).getTime();
				            case 3: 
				            	return invoices.get(rowIndex).getCustomerID();
				            case 4: 
				            	return invoices.get(rowIndex).getEmployeeID();
				            default:
				                return null;
				        }
				    }
				    
				    /**
				     * Method returns selected row object. 
				     * @return invoice object of selected row
				     * Written by Michael Meesseman
				     */
				    static Invoice getInvoices(int rowIndex) throws SQLException {
				        return invoices.get(rowIndex);
				    }
				    
				    /**
				     * Method updates the table with a fresh database query.
				     * @exception SQLException	exception for database queries.
				     * Written by Michael Meesseman
				     */
				    void databaseUpdated() throws SQLException{
				        invoices = readerDAO.obtainInvoiceList();
						fireTableDataChanged();
				    }
				    
				    /**
				     * Method updates the table with a filtered database query.
				     * @param column 	String of the column name to be searched
				     * @param search	String of text to search for.
				     * Written by Michael Meesseman
				     */
				    public void refresh(String column, String search){
				        invoices = readerDAO.obtainInvoiceFilter(column, search);
				        fireTableDataChanged();
				    }
				    
				    /**
				     * Method is used to see if the database query returned a result.
				     * @param column 	String of the column name to be searched
				     * @param search	String of text to search for.
				     * @return invoices	returns list of invoices to see if a search result was found.
				     * Written by Michael Meesseman
				     */
				    public List<Invoice> resultChecker(String column, String search) {
				    	
				    	List<Invoice> invoices = readerDAO.obtainInvoiceFilter(column, search);
				    	
				    	return invoices;
				    }
				    
				    /**
				     * Method updates the table with a fresh database query.
				     * Written by Michael Meesseman
				     */
				    public void reset(){
				        invoices = readerDAO.obtainInvoiceList();
				        fireTableDataChanged();
				    }
				    
}










