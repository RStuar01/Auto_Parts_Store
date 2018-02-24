package PresentationLayer;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.Customer;
import DatabaseLayer.DatabaseReader;


public class CustomerTableModel extends AbstractTableModel {

	   
	    private List<Customer> customers;
	    private final String[] COLUMN_NAMES = {"Customer ID", "Last Name", 
	    		"First Name", "Contact Info ID", "Address ID", "Street Address", 
	    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
	    		"Email Address"};
	    
	    public CustomerTableModel() throws SQLException{
	        customers = DatabaseReader.obtainCustomerList();
	    }
	    
	    @Override 
	    public int getRowCount() {
	        return customers.size();
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
	    
	    Customer getCustomer(int rowIndex) throws SQLException {
	        return customers.get(rowIndex);
	    }
	    
	    void databaseUpdated() throws SQLException{
	        customers = DatabaseReader.obtainCustomerList();
			fireTableDataChanged();
	    }
	    
	    public void refresh(String column, String search){
	        customers = DatabaseReader.obtainCustomerFilter(column, search);
	       
	        fireTableDataChanged();
	    }
	    
	    public void reset(){
	        customers = DatabaseReader.obtainCustomerList();
	        fireTableDataChanged();
	    }
	}

