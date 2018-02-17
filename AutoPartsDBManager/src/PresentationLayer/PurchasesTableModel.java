package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.AccountingPurchases;
import BusinessLayer.Customer;
import BusinessLayer.Invoice;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DatabaseReader;

public class PurchasesTableModel extends AbstractTableModel{
						  
					    private List<AccountingPurchases> purchases;
					    private final String[] COLUMN_NAMES = {"Purchase Number", "Purchase Quantity", 
					    		"Purchase Price", "Product ID"};
					    
					    public PurchasesTableModel() throws SQLException{
					        purchases = DatabaseReader.obtainPurchaseList();
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
					        purchases = DatabaseReader.obtainPurchaseList();
							fireTableDataChanged();
					    }
					}













