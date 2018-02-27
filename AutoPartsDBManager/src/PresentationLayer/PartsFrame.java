package PresentationLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package autopartsstoregui;

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

import BusinessLayer.Customer;
import BusinessLayer.Product;


public class PartsFrame  extends JFrame {
    private JTable partTable;
    private PartsTableModel productTableModel;
    
    private JTextField searchField;
    private JComboBox searchCombo;
    
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
    
    private JPanel buildButtonPanel() throws SQLException {
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
    	PartsForm partsForm = new PartsForm(this, "Add Part", true);
        partsForm.setLocationRelativeTo(this);
        partsForm.setVisible(true);
    }
    
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
    
  
    
    private void doHelpButton()
    {
    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a part. \n"
                + "Press the 'Edit' button after selecting a part to edit their name. \n"
                + "Press the 'Exit' button to exit the program.", 
                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void fireDatabaseUpdatedEvent() throws SQLException {
    	productTableModel.databaseUpdated();
    }
       
    private JTable buildProductTable() throws SQLException {
        productTableModel = new PartsTableModel();
        JTable table = new JTable((javax.swing.table.TableModel) productTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
        return table;
    }
    
    private JPanel buildSearchPanel() {
 	   
 	   String[] fields = {"Product ID", "Description", 
	    		"Minimum Year", "Maximum Year", "Make", "Model", "Supplier Price",
	    		"Sell Price", "Core Charge", "Compatibility Number", "Company ID",
	    		"Minimum Quantity in Stock", "Maximum Quantity in Stock", "Warehouse Location", 
	    		"Quantity in Stock"};
 	   
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

