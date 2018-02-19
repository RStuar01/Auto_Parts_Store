package PresentationLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package autopartsstoregui;

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

import BusinessLayer.Customer;
import BusinessLayer.Product;


public class PartsFrame  extends JFrame {
    private JTable partTable;
    private PartsTableModel productTableModel;
    
    public PartsFrame() throws UnsupportedLookAndFeelException, DBException, SQLException {
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
        editButton.setToolTipText("Edit selected customer");
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
    	PartsForm partsForm = new PartsForm(this, "Add Part", true);
        partsForm.setLocationRelativeTo(this);
        partsForm.setVisible(true);
    }
    
    private void doEditButton() throws DBException, SQLException {
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
       
    private JTable buildProductTable() throws DBException, SQLException {
        productTableModel = new PartsTableModel();
        JTable table = new JTable((javax.swing.table.TableModel) productTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
        return table;
    }
}

