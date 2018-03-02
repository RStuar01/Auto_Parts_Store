package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;

/**
 * Extends AbstartTableModel to build a table for sales item Frame.
 * Written by Michael Meesseman
 */
public class SalesItemTableModel extends AbstractTableModel {
	
				
	//return list for some methods initialized
				private List<InvoiceLineItem> invoiceLineItems;

				private ReaderDAO readerDAO = DAOFactory.getReaderDAO();
				
				//table column names
				private final String[] COLUMN_NAMES = {"Invoice Line Number", "Invoice Number", 
					  	"Quantity Purchased", "Product ID"};
				String invoiceNumberInput;
					    
				/**
			     * Constructor to build the table.  Pulls into list from database.
			     * @exception SQLException	exception for database queries.
			     * @param invoiceNumberInput	brings in invoice number selected for query.
			     * Written by Michael Meesseman
			     */
				public SalesItemTableModel(String invoiceNumberInput) throws SQLException{
					 this.invoiceNumberInput = invoiceNumberInput;
				     invoiceLineItems = readerDAO.obtainInvoiceLineItemList(invoiceNumberInput);
				     
				}
					    
				/**
			     * Method returns number of rows for table.  REQUIRED.
			     * @return returns the number of rows
			     * Written by Michael Meesseman
			     */
					    @Override 
					    public int getRowCount() {
					        return invoiceLineItems.size();
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
					                return invoiceLineItems.get(rowIndex).getInvoiceLineNumber();
					            case 1:
					                return invoiceLineItems.get(rowIndex).getInvoiceNumber();
					            case 2:
					                return invoiceLineItems.get(rowIndex).getQuantityPurchased();
					            case 3: 
					            	return invoiceLineItems.get(rowIndex).getProductID();
					            default:
					                return null;
					        }
					    }
					    
					    /**
					     * Method returns selected row object. 
					     * @return invoicelineitem object of selected row
					     * Written by Michael Meesseman
					     */
					    InvoiceLineItem getInvoiceLineItem(int rowIndex) throws SQLException {
					        return invoiceLineItems.get(rowIndex);
					    }
					    
					    /**
					     * Method updates the table with a fresh database query.
					     * @exception SQLException	exception for database queries.
					     * Written by Michael Meesseman
					     */
					    void databaseUpdated() throws SQLException{
					        invoiceLineItems = readerDAO.obtainInvoiceLineItemList(invoiceNumberInput);
							fireTableDataChanged();
					    }
					    
					    /**
					     * Method updates the table with a filtered database query.
					     * @param column 	String of the column name to be searched
					     * @param search	String of text to search for.
					     * Written by Michael Meesseman
					     */
					    public void refresh(String column, String search){
					        invoiceLineItems = readerDAO.obtainInvoiceLineItemFilter(invoiceNumberInput, column, search);
					        fireTableDataChanged();
					    }
					    
					    /**
					     * Method is used to see if the database query returned a result.
					     * @param column 	String of the column name to be searched
					     * @param search	String of text to search for.
					     * @return lineItem	returns list of lineitems to see if a search result was found.
					     * Written by Michael Meesseman
					     */
					    public List<InvoiceLineItem> resultChecker(String column, String search) {
					    	
					    	List<InvoiceLineItem> lineItem = readerDAO.obtainInvoiceLineItemFilter(invoiceNumberInput, column, search);
					    	
					    	return lineItem;
					    }
					    
					    /**
					     * Method updates the table with a fresh database query.
					     * Written by Michael Meesseman
					     */
					    public void reset(){
					    	 invoiceLineItems = readerDAO.obtainInvoiceLineItemList(invoiceNumberInput);
					        fireTableDataChanged();
					    }
					}











	

