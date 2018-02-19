package PresentationLayer;


import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.Customer;
import BusinessLayer.Employee;
import DatabaseLayer.DatabaseReader;

public class EmployeeTableModel extends AbstractTableModel{

			   
		    private List<Employee> employees;
		    private final String[] COLUMN_NAMES = {"Employee ID", "Last Name", 
		    		"First Name", "Contact Info ID", "Address ID", "Street Address", 
		    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
		    		"Email Address"};
		    
		    public EmployeeTableModel() throws SQLException{
		        employees = DatabaseReader.obtainEmployeeList();
		    }
		    
		    @Override 
		    public int getRowCount() {
		        return employees.size();
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
		                return employees.get(rowIndex).getEmployeeID();
		            case 1:
		                return employees.get(rowIndex).getLastName();
		            case 2:
		                return employees.get(rowIndex).getFirstName();
		            case 3: 
		            	return employees.get(rowIndex).getContactInfoID();
		            case 4: 
		            	return employees.get(rowIndex).getAddressID();
		            case 5: 
		            	return employees.get(rowIndex).getStreetAddress();
		            case 6: 
		            	return employees.get(rowIndex).getCity();
		            case 7:
		            	return employees.get(rowIndex).getState();
		            case 8:
		            	return employees.get(rowIndex).getZipCode();
		            case 9:
		            	return employees.get(rowIndex).getUnitNumber();
		            case 10:
		            	return employees.get(rowIndex).getPhoneNumber();
		            case 11:
		            	return employees.get(rowIndex).getCellPhoneNumber();
		            case 12:
		            	return employees.get(rowIndex).getEmailAddress();
		            default:
		                return null;
		        }
		    }
		    
		    Employee getEmployee(int rowIndex) throws SQLException {
		        return employees.get(rowIndex);
		    }
		    
		    void databaseUpdated() throws SQLException{
		    	employees = DatabaseReader.obtainEmployeeList();
				fireTableDataChanged();
		    }
		    
		    public void refresh(String column, String search){
		    	employees = DatabaseReader.obtainEmployeeFilter(column, search);
		        fireTableDataChanged();
		    }
		    
		    public void reset(){
		    	employees = DatabaseReader.obtainEmployeeList();
		        fireTableDataChanged();
		    }
		


	
	
}
