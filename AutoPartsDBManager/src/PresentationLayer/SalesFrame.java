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

import BusinessLayer.Invoice;

/**
 * Extends JFrame to build a frame for sales tracking.
 * Calls several methods to build the frame.
 * Written by Michael Meesseman
 */
public class SalesFrame extends JFrame {
	
	//table initalization
    private JTable salesTable;
    private SalesTableModel salesTableModel;
    
  //search field initialized.
    private JTextField searchField;
    private JComboBox searchCombo;
    
    /**
     * Constructor to build the frame.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    public SalesFrame() throws UnsupportedLookAndFeelException, SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Sales");
        setSize(768, 384);
        setLocationByPlatform(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.SOUTH);
        salesTable = buildSalesTable();
        add(new JScrollPane(salesTable), BorderLayout.CENTER);
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
        
        //select button
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent) -> {
            try {
				doSelectButton();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(selectButton);
        
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
     * Method executes when select button is pressed
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doSelectButton() throws UnsupportedLookAndFeelException, SQLException {
    	
        	int selectedRow = salesTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "No invoice is currently "
                        + "selected.", "No Invoice selected", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
    	
    	String invoiceNumber = (String) salesTable.getValueAt(salesTable.getSelectedRow(), 0);
    	String date = (String) salesTable.getValueAt(salesTable.getSelectedRow(), 1);
    	String time = (String) salesTable.getValueAt(salesTable.getSelectedRow(), 2);
    	String customerID = (String) salesTable.getValueAt(salesTable.getSelectedRow(), 3);
    	String employeeID = (String) salesTable.getValueAt(salesTable.getSelectedRow(), 4);
    	SalesItemFrame salesItemFrame = new SalesItemFrame(invoiceNumber);
        salesItemFrame.setLocationRelativeTo(this);
        salesItemFrame.setVisible(true);
            }
    }
    
    /**
     * Method executes when add button is pressed
     * Written by Michael Meesseman
     */
    private void doAddButton() {
    	SalesForm salesForm = new SalesForm(this, "Add Invoice", true);
        salesForm.setLocationRelativeTo(this);
        salesForm.setVisible(true);
    }
    
    /**
     * Method executes when help button is pressed.
     * Written by Michael Meesseman
     */
    private void doHelpButton()
    {
    	JOptionPane.showMessageDialog(this, "Press the 'Select' button after selecting an invoice to see invoice details. \n"
    			+ "Press the 'Add' button to add a invoice. \n"
                
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
        ((SalesTableModel) salesTableModel).databaseUpdated();
    }
       
    
    /**
     * Method to build the frame table that goes in center.
     * @return table	JTable to populate database results
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private JTable buildSalesTable() throws SQLException {
        salesTableModel = new SalesTableModel();
        JTable table = new JTable((javax.swing.table.TableModel) salesTableModel);
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
 	   String[] fields = {"Invoice Number", "Date", 
	    		"Time", "Customer ID", "Employee ID"};
 	   
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
 		   column = "invoice_number";
 		   break;
 	   case 1:
 		   column = "date";
 		   break;
 	   case 2:
 		   column = "time";
 		   break;
 	   case 3:
 		   column = "customer_customer_id";
 		   break;
 	   case 4:
 		   column = "employee_employee_id";
 		   break;
 	   default:
 		   column = "";
 		   break;
 		   
 	   }
 	   
 	// empty search field refreshes table to all entries.
 	   // otherwise database is filled with query results from database.
 	   // if search result does not return a result a dialog box notifies the user. 
 	   if(searchField.getText().equals(""))
 		   salesTableModel.reset();
 	   else
 		   salesTableModel.refresh(column, searchField.getText());
 	  if(salesTableModel.resultChecker(column, searchField.getText()).isEmpty()) {
	   		JOptionPane.showMessageDialog(this, "Invoice does not exist.  Please try another search.", 
	                    "Search Input does not Exist.", JOptionPane.INFORMATION_MESSAGE);
	   		salesTableModel.reset();
	   		}
    }
}
