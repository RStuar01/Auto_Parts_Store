package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

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

/**
 * Extends JFrame to build a frame for sales invoice items tracking.
 * Calls several methods to build the frame.
 * Written by Michael Meesseman
 */
public class SalesItemFrame extends JFrame{
	
	//table initalization
	    private JTable salesItemTable;
	    private SalesItemTableModel salesItemTableModel;
	    
	    // invoice number selection
	    private String invoiceNumberInput;

	  //search field initialized.
	    private JTextField searchField;
	    private JComboBox searchCombo;
	    
	    /**
	     * Constructor to build the frame.
	     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    public SalesItemFrame(String invoiceNumberInput) throws UnsupportedLookAndFeelException, SQLException {
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
	        add(buildSearchPanel(), BorderLayout.NORTH);
	        setVisible(true);
	                
	    }
	    
	    /**
	     * Method to build the Button Panel.
	     * @return panel	this is the button panel that goes to the SOUTH of the frame.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    private JPanel buildButtonPanel() throws SQLException {
	        JPanel panel = new JPanel();
	        
	        //add button
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
	      
	        //help button
	        JButton helpButton = new JButton("Help");
	        helpButton.addActionListener((ActionEvent) -> {
	            doHelpButton();
	        });
	    
	        panel.add(helpButton);
	         
	        //exit button
	        JButton exitButton = new JButton("Exit");
	        exitButton.addActionListener((ActionEvent) -> {
	            dispose();
	        });
	    
	        panel.add(exitButton);
	        
	        return panel;
	        
	    }
	    
	    /**
	     * Method executes when add button is pressed
	     * Written by Michael Meesseman
	     */
	    private void doAddButton() {
	    	SalesItemForm salesItemForm = new SalesItemForm(this, "Add Sales Item", true, invoiceNumberInput);
	        salesItemForm.setLocationRelativeTo(this);
	        salesItemForm.setVisible(true);
	    }
	    
	    /**
	     * Method executes when help button is pressed.
	     * Written by Michael Meesseman
	     */
	    private void doHelpButton()
	    {
	    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a sales item. \n"
	                
	                + "Press the 'Exit' button to exit the program.", 
	                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	   
	    /**
	     * Method to refresh the table from the database.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    public void fireDatabaseUpdatedEvent() throws SQLException
	    {
	        ((SalesItemTableModel) salesItemTableModel).databaseUpdated();
	    }
	       
	    /**
	     * Method to build the frame table that goes in center.
	     * @return table	JTable to populate database results
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    private JTable buildSalesTable() throws SQLException {
	        salesItemTableModel = new SalesItemTableModel(invoiceNumberInput);
	        JTable table = new JTable((javax.swing.table.TableModel) salesItemTableModel);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setBorder(null);
	        return table;
	    }
	
	    /**
		    * Method to build the search panel.
		    * @return panel	panel which populates the NORTH end of frame.
		    * Written by Michael Meesseman
		    */
	    private JPanel buildSearchPanel() {
	 	   
	    	// drop down box fields
	 	   String[] fields = {"Invoice Line Number", "Invoice Number", 
				  	"Quantity Purchased", "Product ID"};
	 	   
	 	   JPanel panel = new JPanel();
	 	   
	 	//text field initialize
	 	   searchField = new JTextField();
	 	   Dimension longField = new Dimension(300, 20);
	        searchField.setPreferredSize(longField);
	        searchField.setMinimumSize(longField);
	        
	        panel.add(searchField);
	        
	        // combo box initialize
	        searchCombo = new JComboBox(fields);
	        panel.add(searchCombo);
	        
	        //search button
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
	    
	    /**
		    * Method executes when search button is pressed
		    * @exception SQLException	exception for database queries.
		    * Written by Michael Meesseman
		    */
	    private void doSearchButton() throws SQLException {
	 	   
	 	   String column;
	 	   
	 	// switch to set search parameter to database field name.
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
	 	   
	 	// empty search field refreshes table to all entries.
	 	   // otherwise database is filled with query results from database.
	 	   // if search result does not return a result a dialog box notifies the user. 
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
