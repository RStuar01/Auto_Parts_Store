package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import BusinessLayer.Customer;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import DatabaseLayer.DatabaseReader;

public class SalesItemTableModel extends AbstractTableModel {

	
				  
				private List<InvoiceLineItem> invoiceLineItems;
				private final String[] COLUMN_NAMES = {"Invoice Line Number", "Invoice Number", 
					  	"Quantity Purchased", "Product ID"};
				int invoiceNumberInput;
					    
				public SalesItemTableModel(int invoiceNumberInput) throws SQLException{
					 this.invoiceNumberInput = invoiceNumberInput;
				     invoiceLineItems = DatabaseReader.obtainInvoiceLineItemList(invoiceNumberInput);
				     
				}
					    
					    @Override 
					    public int getRowCount() {
					        return invoiceLineItems.size();
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
					    
					    InvoiceLineItem getInvoiceLineItem(int rowIndex) throws SQLException {
					        return invoiceLineItems.get(rowIndex);
					    }
					    
					    void databaseUpdated() throws SQLException{
					        invoiceLineItems = DatabaseReader.obtainInvoiceLineItemList(invoiceNumberInput);
							fireTableDataChanged();
					    }
					}











	

