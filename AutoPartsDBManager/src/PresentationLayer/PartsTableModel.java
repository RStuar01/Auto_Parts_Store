package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;

/**
 * Extends AbstartTableModel to build a table for parts Frame.
 * Written by Michael Meesseman
 */
public class PartsTableModel extends AbstractTableModel{
	
				
	//return list for some methods initialized
			    private static List<Product> products;
			    private ReaderDAO readerDAO = DAOFactory.getReaderDAO();

			    //table column names

			    private final String[] COLUMN_NAMES = {"Product ID", "Description", 
			    		"Minimum Year", "Maximum Year", "Make", "Model", "Supplier Price",
			    		"Sell Price", "Core Charge", "Compatibility Number", "Company ID",
			    		"Minimum Quantity in Stock", "Maximum Quantity in Stock", "Warehouse Location", 
			    		"Quantity in Stock"};
			    
			    
			    /**
			     * Constructor to build the table.  Pulls into list from database.
			     * @exception SQLException	exception for database queries.
			     * Written by Michael Meesseman
			     */
			    public PartsTableModel() throws SQLException{
			        products = readerDAO.obtainProductList();
			    }
			    
			    /**
			     * Method returns number of rows for table.  REQUIRED.
			     * @return returns the number of rows
			     * Written by Michael Meesseman
			     */
			    @Override 
			    public int getRowCount() {
			        return products.size();
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
			                return products.get(rowIndex).getProductID();
			            case 1:
			                return products.get(rowIndex).getDescription();
			            case 2:
			                return products.get(rowIndex).getYearMinimum();
			            case 3: 
			            	return products.get(rowIndex).getYearMaximum();
			            case 4: 
			            	return products.get(rowIndex).getMake();
			            case 5: 
			            	return products.get(rowIndex).getModel();
			            case 6: 
			            	return products.get(rowIndex).getSupplierPrice();
			            case 7:
			            	return products.get(rowIndex).getSellPrice();
			            case 8:
			            	return products.get(rowIndex).getCoreCharge();
			            case 9:
			            	return products.get(rowIndex).getCompatibilityNumber();
			            case 10:
			            	return products.get(rowIndex).getCompanyID();
			            case 11:
			            	return products.get(rowIndex).getMinQuantityInStock();
			            case 12:
			            	return products.get(rowIndex).getMaxQuantityInStock();
			            case 13:
			            	return products.get(rowIndex).getWarehouseLocation();
			            case 14:
			            	return products.get(rowIndex).getQuantityInStock();
			            default:
			                return null;
			        }
			    }
			    
			    /**
			     * Method returns selected row object. 
			     * @return customer object of selected row
			     * Written by Michael Meesseman
			     */
			    static Product getProducts(int rowIndex) throws SQLException {
			        return products.get(rowIndex);
			    }
			    
			    /**
			     * Method updates the table with a fresh database query.
			     * @exception SQLException	exception for database queries.
			     * Written by Michael Meesseman
			     */
			    void databaseUpdated() throws SQLException{
			        products = readerDAO.obtainProductList();
					fireTableDataChanged();
			    }
			    
			    /**
			     * Method updates the table with a filtered database query.
			     * @param column 	String of the column name to be searched
			     * @param search	String of text to search for.
			     * Written by Michael Meesseman
			     */
			    public void refresh(String column, String search){
			        products = readerDAO.obtainProductFilter(column, search);
			        fireTableDataChanged();
			    }
			    
			    /**
			     * Method is used to see if the database query returned a result.
			     * @param column 	String of the column name to be searched
			     * @param search	String of text to search for.
			     * @return company	returns list of customers to see if a search result was found.
			     * Written by Michael Meesseman
			     */
			    public List<Product> resultChecker(String column, String search) {
			    	
			    	List<Product> products = readerDAO.obtainProductFilter(column, search);
			    	
			    	return products;
			    }
			    
			    /**
			     * Method updates the table with a fresh database query.
			     * Written by Michael Meesseman
			     */
			    public void reset(){
			        products = readerDAO.obtainProductList();
			        fireTableDataChanged();
			    }
			}







