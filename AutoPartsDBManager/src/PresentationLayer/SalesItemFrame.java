package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableModel;

import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;

public class SalesItemFrame extends JFrame{
	
	
	    private JTable salesItemTable;
	    private SalesItemTableModel salesItemTableModel;
	    private int invoiceNumberInput;
	    
	    public SalesItemFrame(int invoiceNumberInput) throws UnsupportedLookAndFeelException, DBException, SQLException {
	        try {
	            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
	        }
	        catch (ClassNotFoundException | InstantiationException 
	                | IllegalAccessException | UnsupportedLookAndFeelException e) {
	            System.out.println(e);
	        }
	        this.invoiceNumberInput = invoiceNumberInput;
	        setTitle("Invoice Detail");
	        setSize(768, 384);
	        setLocationByPlatform(true);
	        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        add(buildButtonPanel(), BorderLayout.SOUTH);
	        salesItemTable = buildSalesTable();
	        add(new JScrollPane(salesItemTable), BorderLayout.CENTER);
	        setVisible(true);
	                
	    }
	    
	    private JPanel buildButtonPanel() throws DBException {
	        JPanel panel = new JPanel();
	        
	        JButton addButton = new JButton("Add");
	        addButton.addActionListener((ActionEvent) -> {
	            doAddButton();
	        });
	    
	        panel.add(addButton);
	        
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
	    	SalesItemForm salesItemForm = new SalesItemForm(this, "Add Sales Item", true);
	        salesItemForm.setLocationRelativeTo(this);
	        salesItemForm.setVisible(true);
	    }
	    
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
	    
	    private void doHelpButton()
	    {
	    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a sales item. \n"
	                + "Press the 'Edit' button after selecting a sales item to edit their name. \n" 
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
	


}
