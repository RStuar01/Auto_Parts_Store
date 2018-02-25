package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableModel;

import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;

public class SalesItemFrame extends JFrame{
	
	
	    private JTable salesItemTable;
	    private SalesItemTableModel salesItemTableModel;
	    private String invoiceNumberInput;
	    private String date;
	    private String time;
	    private String customerID;
	    private String employeeID;
	    
	    private JTextField searchField;
	    private JComboBox searchCombo;
	    
	    public SalesItemFrame(String invoiceNumberInput, String date, String time, String customerID, String employeeID) throws UnsupportedLookAndFeelException, DBException, SQLException {
	        try {
	            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
	        }
	        catch (ClassNotFoundException | InstantiationException 
	                | IllegalAccessException | UnsupportedLookAndFeelException e) {
	            System.out.println(e);
	        }
	        this.invoiceNumberInput = invoiceNumberInput;
	        this.date = date;
	        this.time = time;
	        this.customerID = customerID;
	        this.employeeID = employeeID;
	        setTitle("Invoice Detail");
	        setSize(768, 384);
	        setLocationByPlatform(true);
	        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        add(buildButtonPanel(), BorderLayout.SOUTH);
	        salesItemTable = buildSalesTable();
	        add(new JScrollPane(salesItemTable), BorderLayout.CENTER);
	        add(buildSearchPanel(), BorderLayout.NORTH);
	        setVisible(true);
	                
	    }
	    
	    private JPanel buildButtonPanel() throws DBException {
	        JPanel panel = new JPanel();
	        
	        JButton addButton = new JButton("Add");
	        addButton.addActionListener((ActionEvent) -> {
	            doAddButton();
	            try {
					fireDatabaseUpdatedEvent();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	    
	        panel.add(addButton);
	       /* 
	        JButton editButton = new JButton("Edit");
	        editButton.setToolTipText("Edit selected Sales Line");
	        editButton.addActionListener((ActionEvent) -> {
	            try {
	                doEditButton();
	            } catch (DBException e) {
	                System.out.println(e);
	            } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	        panel.add(editButton);
	        
	        */
	        
	        JButton helpButton = new JButton("Help");
	        helpButton.addActionListener((ActionEvent) -> {
	            doHelpButton();
	        });
	    
	        panel.add(helpButton);
	         
	        JButton exitButton = new JButton("Exit");
	        exitButton.addActionListener((ActionEvent) -> {
	            dispose();
	        });
	    
	        panel.add(exitButton);
	        
	        return panel;
	        
	    }
	    
	    private void doAddButton() {
	    	SalesItemForm salesItemForm = new SalesItemForm(this, "Add Sales Item", true, invoiceNumberInput, date, time, customerID, employeeID);
	        salesItemForm.setLocationRelativeTo(this);
	        salesItemForm.setVisible(true);
	    }
	    
	    /*
	    private void doEditButton() throws DBException, SQLException {
	    	int selectedRow = salesItemTable.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(this, "No Sales Item is currently "
	                    + "selected.", "No Sales Item selected", JOptionPane.ERROR_MESSAGE);
	        }
	        else
	        {
	            InvoiceLineItem invoiceLineItems = salesItemTableModel.getInvoiceLineItem(selectedRow);
	            SalesItemForm salesItemForm = new SalesItemForm(this, "Edit Product", true, invoiceLineItems);
	            salesItemForm.setLocationRelativeTo(this);
	            salesItemForm.setVisible(true);
	        }
	    }
	    
	    */
	    
	    private void doHelpButton()
	    {
	    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a sales item. \n"
	                
	                + "Press the 'Exit' button to exit the program.", 
	                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	   
	    
	    public void fireDatabaseUpdatedEvent() throws SQLException
	    {
	        ((SalesItemTableModel) salesItemTableModel).databaseUpdated();
	    }
	       
	    private JTable buildSalesTable() throws DBException, SQLException {
	        salesItemTableModel = new SalesItemTableModel(invoiceNumberInput);
	        JTable table = new JTable((javax.swing.table.TableModel) salesItemTableModel);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setBorder(null);
	        return table;
	    }
	
	    private JPanel buildSearchPanel() {
	 	   
	 	   String[] fields = {"Invoice Line Number", "Invoice Number", 
				  	"Quantity Purchased", "Product ID"};
	 	   
	 	   JPanel panel = new JPanel();
	 	   
	 	   searchField = new JTextField();
	 	   Dimension longField = new Dimension(300, 20);
	        searchField.setPreferredSize(longField);
	        searchField.setMinimumSize(longField);
	        
	        panel.add(searchField);
	        
	        searchCombo = new JComboBox(fields);
	        panel.add(searchCombo);
	        
	        JButton searchButton = new JButton("Search");
	        searchButton.addActionListener((ActionEvent) -> {
	            try {
	 			doSearchButton();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	        });
	    
	        panel.add(searchButton);
	 	   
	 	   return panel;
	    }
	    
	    private void doSearchButton() throws SQLException {
	 	   
	 	   String column;
	 	   
	 	   switch(searchCombo.getSelectedIndex())
	 	   {
	 	   case 0:
	 		   column = "invoice_line_number";
	 		   break;
	 	   case 1:
	 		   column = "invoice_invoice_number";
	 		   break;
	 	   case 2:
	 		   column = "quantity_purchased";
	 		   break;
	 	   case 3:
	 		   column = "product_product";
	 		   break;
	 	   default:
	 		   column = "";
	 		   break;
	 		   
	 	   }
	 	   
	 	   if(searchField.getText().equals(""))
	 		  salesItemTableModel.reset();
	 	   else
	 		  salesItemTableModel.refresh(column, searchField.getText());
	 	  if(salesItemTableModel.resultChecker(column, searchField.getText()).isEmpty()) {
	 	   		JOptionPane.showMessageDialog(this, "Invoice Line does not exist.  Please try another search.", 
	 	                    "Search Input does not Exist.", JOptionPane.INFORMATION_MESSAGE);
	 	   		salesItemTableModel.reset();
	 	   		}
	    }

}
