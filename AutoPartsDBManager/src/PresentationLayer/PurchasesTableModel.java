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

public class PurchasesTableModel extends AbstractTableModel{
						  
					    private List<AccountingPurchases> purchases;
					    private ReaderDAO readerDAO = DAOFactory.getReaderDAO();
					    private final String[] COLUMN_NAMES = {"Purchase Number", "Purchase Quantity", 
					    		"Purchase Price", "Product ID"};
					    
					    public PurchasesTableModel() throws SQLException{
					        purchases = readerDAO.obtainPurchaseList();
					    }
					    
					    @Override 
					    public int getRowCount() {
					        return purchases.size();
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
					    
					    AccountingPurchases getPurchases(int rowIndex) throws SQLException {
					        return purchases.get(rowIndex);
					    }
					    
					    void databaseUpdated() throws SQLException{
					        purchases = readerDAO.obtainPurchaseList();
							fireTableDataChanged();
					    }
					    
					    public void refresh(String column, String search){
					        purchases = readerDAO.obtainPurchaseFilter(column, search);
					        fireTableDataChanged();
					    }
					    
					    public List<AccountingPurchases> resultChecker(String column, String search) {
					    	
					    	List<AccountingPurchases> purchases = readerDAO.obtainPurchaseFilter(column, search);
					    	
					    	return purchases;
					    }
					    
					    public void reset(){
					        purchases = readerDAO.obtainPurchaseList();
					        fireTableDataChanged();
					    }
					}













