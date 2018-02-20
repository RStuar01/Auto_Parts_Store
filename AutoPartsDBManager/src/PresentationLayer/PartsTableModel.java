package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.Customer;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DatabaseReader;

public class PartsTableModel extends AbstractTableModel{
	
			  
			    private static List<Product> products;
			    private final String[] COLUMN_NAMES = {"Product ID", "Description", 
			    		"Minimum Year", "Maximum Year", "Make", "Model", "Supplier Price",
			    		"Sell Price", "Core Charge", "Compatibility Number", "Company ID",
			    		"Minimum Quantity in Stock", "Maximum Quantity in Stock", "Warehouse Location", 
			    		"Quantity in Stock"};
			    
			    public PartsTableModel() throws SQLException{
			        products = DatabaseReader.obtainProductList();
			    }
			    
			    @Override 
			    public int getRowCount() {
			        return products.size();
			    }
			    
			    @Override
			    public int getColumnCount() {
			        return COLUMN_NAMES.length;
			    }
			    
			    @Override
			    public String getColumnName(int columnIndex) {
			        return COLUMN_NAMES[columnIndex];
			    }
			    
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
			    
			    static Product getProducts(int rowIndex) throws SQLException {
			        return products.get(rowIndex);
			    }
			    
			    void databaseUpdated() throws SQLException{
			        products = DatabaseReader.obtainProductList();
					fireTableDataChanged();
			    }
			    
			    public void refresh(String column, String search){
			        products = DatabaseReader.obtainProductFilter(column, search);
			        fireTableDataChanged();
			    }
			    
			    public void reset(){
			        products = DatabaseReader.obtainProductList();
			        fireTableDataChanged();
			    }
			}







