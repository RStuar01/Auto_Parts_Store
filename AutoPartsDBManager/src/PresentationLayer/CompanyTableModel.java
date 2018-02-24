package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.AccountingPurchases;
import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Supplier;
import DatabaseLayer.DatabaseReader;

public class CompanyTableModel extends AbstractTableModel{
	
	
	
			    private List<Company> companies;
			    
			    private final String[] COLUMN_NAMES = {"Company ID", "Address ID", "Contact Info ID", 
			    		"Company Name", "Street Address", "City", "State", "Zip Code", 
			    		"Unit Number", "Home Phone", "Cell Phone", "Email Address"};
			    
			    public CompanyTableModel() throws SQLException{
			        companies = DatabaseReader.obtainCompanyList();
			
			    }
			    
			    @Override 
			    public int getRowCount() {
			        return companies.size();
			  
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
			    
			    Company getCompanies(int rowIndex) throws SQLException {
			        return companies.get(rowIndex);
			    }
			    
			    
			    void databaseUpdated() throws SQLException{
			        companies = DatabaseReader.obtainCompanyList();
					fireTableDataChanged();
			    }
			    
			    public void refresh(String column, String search){
			        companies = DatabaseReader.obtainCompanyFilter(column, search);
			        fireTableDataChanged();
			    }
			    
			    public List<Company> resultChecker(String column, String search) {
			    	
			    	List<Company> company = DatabaseReader.obtainCompanyFilter(column, search);
			    	
			    	return company;
			    }
			    
			    public void reset(){
			        companies = DatabaseReader.obtainCompanyList();
			        fireTableDataChanged();
			    }
			






}
