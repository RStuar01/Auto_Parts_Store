package PresentationLayer;


import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.Customer;
import BusinessLayer.Invoice;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DatabaseReader;


public class SalesTableModel extends AbstractTableModel {
			
				  
				    private static List<Invoice> invoices;
				    private final String[] COLUMN_NAMES = {"Invoice Number", "Date", 
				    		"Time", "Customer ID", "Employee ID"};
				    
				    public SalesTableModel() throws SQLException{
				        invoices = DatabaseReader.obtainInvoiceList();
				    }
				    
				    @Override 
				    public int getRowCount() {
				        return invoices.size();
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
				    
				    static Invoice getInvoices(int rowIndex) throws SQLException {
				        return invoices.get(rowIndex);
				    }
				    
				    void databaseUpdated() throws SQLException{
				        invoices = DatabaseReader.obtainInvoiceList();
						fireTableDataChanged();
				    }
				}










