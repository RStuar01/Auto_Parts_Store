package PresentationLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package autopartsstoregui;

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

import BusinessLayer.Product;

/**
 * Extends JFrame to build a frame for parts tracking.
 * Calls several methods to build the frame.
 * Written by Michael Meesseman
 */
public class PartsFrame  extends JFrame {
	
	//table initalization
    private JTable partTable;
    private PartsTableModel productTableModel;
    
  //search field initialized.
    private JTextField searchField;
    private JComboBox searchCombo;
    
    /**
     * Constructor to build the frame.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    public PartsFrame() throws UnsupportedLookAndFeelException, SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Parts Information");
        setSize(768, 384);
        setLocationByPlatform(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.SOUTH);
        partTable = buildProductTable();
        add(new JScrollPane(partTable), BorderLayout.CENTER);
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
        
        
        //edit button
        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit selected customer");
        editButton.addActionListener((ActionEvent) -> {
            try {
                doEditButton();
                fireDatabaseUpdatedEvent();
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        panel.add(editButton);
        
        
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
    	PartsForm partsForm = new PartsForm(this, "Add Part", true);
        partsForm.setLocationRelativeTo(this);
        partsForm.setVisible(true);
    }
    
    /**
     * Method executes when edit button is pressed
     * * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */ 
    private void doEditButton() throws SQLException {
    	int selectedRow = partTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No Part is currently "
                    + "selected.", "No Part selected", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Product product = PartsTableModel.getProducts(selectedRow);
            PartsForm partsForm = new PartsForm(this, "Edit Product", true, product);
            partsForm.setLocationRelativeTo(this);
            partsForm.setVisible(true);
        }
    }
    
  
    /**
     * Method executes when help button is pressed.
     * Written by Michael Meesseman
     */
    private void doHelpButton()
    {
    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a part. \n"
                + "Press the 'Edit' button after selecting a part to edit their name. \n"
                + "Press the 'Exit' button to exit the program.", 
                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Method to refresh the table from the database.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    public void fireDatabaseUpdatedEvent() throws SQLException {
    	productTableModel.databaseUpdated();
    }
       
    /**
     * Method to build the frame table that goes in center.
     * @return table	JTable to populate database results
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private JTable buildProductTable() throws SQLException {
        productTableModel = new PartsTableModel();
        JTable table = new JTable((javax.swing.table.TableModel) productTableModel);
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
 	   String[] fields = {"Product ID", "Description", 
	    		"Minimum Year", "Maximum Year", "Make", "Model", "Supplier Price",
	    		"Sell Price", "Core Charge", "Compatibility Number", "Company ID",
	    		"Minimum Quantity in Stock", "Maximum Quantity in Stock", "Warehouse Location", 
	    		"Quantity in Stock"};
 	   
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
 		   column = "product";
 		   break;
 	   case 1:
 		   column = "description";
 		   break;
 	   case 2:
 		   column = "year_minimum";
 		   break;
 	   case 3:
 		   column = "year_maximum";
 		   break;
 	   case 4:
 		   column = "make";
 		   break;
 	   case 5:
 		   column = "model";
 		   break;
 	   case 6:
 		   column = "supplier_price";
 		   break;
 	   case 7:
 		   column = "sell_price";
 		   break;
 	   case 8:
 		   column = "core_charge";
 		   break;
 	   case 9: 
 		   column = "compatibility_number";
 		   break;
 	   case 10:
 		   column = "company_company_id";
 		   break;
 	   case 11:
 		   column = "min_quantity_in_stock";
 		   break;
 	   case 12:
 		   column = "max_quantity_in_stock";
 		   break;
 	   case 13:
 		   column = "warehouse_location";
 		   break;
 	   case 14:
 		   column = "quantity_in_stock";
 		   break;
 	   default:
 		   column = "";
 		   break;
 		   
 	   }
 	   
 	// empty search field refreshes table to all entries.
 	   // otherwise database is filled with query results from database.
 	   // if search result does not return a result a dialog box notifies the user. 
 	   if(searchField.getText().equals(""))
 		   productTableModel.reset();
 	   else
 		   productTableModel.refresh(column, searchField.getText());
 	  if(productTableModel.resultChecker(column, searchField.getText()).isEmpty()) {
	   		JOptionPane.showMessageDialog(this, "Product does not exist.  Please try another search.", 
	                    "Search Input does not Exist.", JOptionPane.INFORMATION_MESSAGE);
	   		productTableModel.reset();
	   		}
    }
}

