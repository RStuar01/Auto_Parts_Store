package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Supplier;
import DatabaseLayer.DatabaseReader;

public class SupplierTableModel extends AbstractTableModel {
	
	  
		    private List<Supplier> suppliers;
		    private List<Company> company;
		    private final String[] COLUMN_NAMES = {"Supplier ID", "Last Name", 
		    		"First Name", "Contact Info ID", "Address ID", "Company ID", "Street Address",
		    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
		    		"Email Address", "Company Name"};
		    
		    public SupplierTableModel() throws SQLException{
		        suppliers = DatabaseReader.obtainSupplierList();
		
		    }
		    
		    @Override 
		    public int getRowCount() {
		        return suppliers.size();
		  
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
		    
		    Supplier getSuppliers(int rowIndex) throws SQLException {
		        return suppliers.get(rowIndex);
		    }
		    
		    
		    void databaseUpdated() throws SQLException{
		        suppliers = DatabaseReader.obtainSupplierList();
				fireTableDataChanged();
		    }
		    
		    public void refresh(String column, String search){
		        suppliers = DatabaseReader.obtainSupplierFilter(column, search);
		        fireTableDataChanged();
		    }
		    
		    public void reset(){
		        suppliers = DatabaseReader.obtainSupplierList();
		        fireTableDataChanged();
		    }
		}




