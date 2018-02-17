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
import java.util.function.Supplier;
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


public class SupplierFrame  extends JFrame {
    private JTable supplierTable;
    private SupplierTableModel supplierTableModel;
    
    public SupplierFrame() throws UnsupportedLookAndFeelException, DBException, SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Supplier Information");
        setSize(768, 384);
        setLocationByPlatform(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.SOUTH);
        supplierTable = buildSupplierTable();
        add(new JScrollPane(supplierTable), BorderLayout.CENTER);
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
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Delete selected customer");
        deleteButton.addActionListener((ActionEvent) -> {
            try {
                doDeleteButton();
            } catch (DBException e) {
                System.out.println(e);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        panel.add(deleteButton);
        
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
    	SupplierForm supplierForm = new SupplierForm(this, "Add Supplier", true);
        supplierForm.setLocationRelativeTo(this);
        supplierForm.setVisible(true);
    }
    
    private void doEditButton() throws DBException, SQLException {
    	int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No Supplier is currently "
                    + "selected.", "No supplier selected", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            BusinessLayer.Supplier supplier = supplierTableModel.getSuppliers(selectedRow);
            SupplierForm supplierForm = new SupplierForm(this, "Edit Supplier", true, supplier);
            supplierForm.setLocationRelativeTo(this);
            supplierForm.setVisible(true);
        }
    }
    
    private void doDeleteButton() throws DBException, SQLException {
    	int selectedRow = supplierTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No supplier is currently selected", 
                    "No supplier selected.", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            BusinessLayer.Supplier supplier = supplierTableModel.getSuppliers(selectedRow);
            int ask = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " 
                    + supplier.getCompanyName() + " from the database?", "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (ask == JOptionPane.YES_OPTION) {
                try {
                    // Delete method from DatabaseDeleter
                    fireDatabaseUpdatedEvent();
                }
                catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }
    
    private void doHelpButton()
    {
    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a supplier. \n"
                + "Press the 'Edit' button after selecting a supplier to edit their name. \n"
                + "Press the 'Delete' button after selecting a supplier to delete that customer. \n"
                + "Press the 'Exit' button to exit the program.", 
                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void fireDatabaseUpdatedEvent() throws SQLException {
    	supplierTableModel.databaseUpdated();
    }
       
   private JTable buildSupplierTable() throws SQLException {
        supplierTableModel = new SupplierTableModel();
        JTable table = new JTable((javax.swing.table.TableModel) supplierTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
        return table;
   }
}


