package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.AccountingPurchases;
import BusinessLayer.Customer;
import BusinessLayer.Invoice;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;

/**
 * Extends AbstartTableModel to build a table for Accounting Frame.
 * Written by Michael Meesseman
 */
public class PurchasesTableModel extends AbstractTableModel{
	
						
						  
	//return list for some methods initialized
					    private List<AccountingPurchases> purchases;
					    private ReaderDAO readerDAO = DAOFactory.getReaderDAO();
   
					  //table column names

					    private final String[] COLUMN_NAMES = {"Purchase Number", "Purchase Quantity", 
					    		"Purchase Price", "Product ID"};
					    

					    /**
					     * Constructor to build the table.  Pulls into list from database.
					     * @exception SQLException	exception for database queries.
					     * Written by Michael Meesseman
					     */
					    public PurchasesTableModel() throws SQLException{
					        purchases = readerDAO.obtainPurchaseList();
					    }
					    
					    /**
					     * Method returns number of rows for table.  REQUIRED.
					     * @return returns the number of rows
					     * Written by Michael Meesseman
					     */
					    @Override 
					    public int getRowCount() {
					        return purchases.size();
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
					                return purchases.get(rowIndex).getAccountingPurchasesRecordID();
					            case 1:
					                return purchases.get(rowIndex).getPurchasesQuantity();
					            case 2:
					                return purchases.get(rowIndex).getDollarValue();
					            case 3: 
					            	return purchases.get(rowIndex).getProductID();
					            default:
					                return null;
					        }
					    }
					    
					    /**
					     * Method returns selected row object. 
					     * @return purchases object of selected row
					     * Written by Michael Meesseman
					     */
					    AccountingPurchases getPurchases(int rowIndex) throws SQLException {
					        return purchases.get(rowIndex);
					    }
					    
					    /**
					     * Method updates the table with a fresh database query.
					     * @exception SQLException	exception for database queries.
					     * Written by Michael Meesseman
					     */
					    void databaseUpdated() throws SQLException{
					        purchases = readerDAO.obtainPurchaseList();
							fireTableDataChanged();
					    }
					    
					    /**
					     * Method updates the table with a filtered database query.
					     * @param column 	String of the column name to be searched
					     * @param search	String of text to search for.
					     * Written by Michael Meesseman
					     */
					    public void refresh(String column, String search){
					        purchases = readerDAO.obtainPurchaseFilter(column, search);
					        fireTableDataChanged();
					    }
					    
					    
					    /**
					     * Method is used to see if the database query returned a result.
					     * @param column 	String of the column name to be searched
					     * @param search	String of text to search for.
					     * @return purchases	returns list of purchases to see if a search result was found.
					     * Written by Michael Meesseman
					     */
					    public List<AccountingPurchases> resultChecker(String column, String search) {
					    	
					    	List<AccountingPurchases> purchases = readerDAO.obtainPurchaseFilter(column, search);
					    	
					    	return purchases;
					    }
					    
					    /**
					     * Method updates the table with a fresh database query.
					     * Written by Michael Meesseman
					     */
					    public void reset(){
					        purchases = readerDAO.obtainPurchaseList();
					        fireTableDataChanged();
					    }
					}













