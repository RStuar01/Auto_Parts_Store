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

import BusinessLayer.Invoice;
import BusinessLayer.Product;

/**
 *
 * @author Michael
 */
public class SalesFrame extends JFrame {
    private JTable salesTable;
    private SalesTableModel salesTableModel;
    
    public SalesFrame() throws UnsupportedLookAndFeelException, DBException, SQLException {
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
        setVisible(true);
                
    }
    
    private JPanel buildButtonPanel() throws DBException {
        JPanel panel = new JPanel();
        
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent) -> {
            try {
				doSelectButton();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(selectButton);
        
    
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
    
    private void doSelectButton() throws UnsupportedLookAndFeelException, DBException, SQLException {
    	SalesItemFrame salesItemFrame = new SalesItemFrame(salesTable.getSelectedRow());
        salesItemFrame.setLocationRelativeTo(this);
        salesItemFrame.setVisible(true);
    }
    
    private void doAddButton() {
    	SalesForm salesForm = new SalesForm(this, "Add Invoice", true);
        salesForm.setLocationRelativeTo(this);
        salesForm.setVisible(true);
    }
    
    private void doEditButton() throws DBException, SQLException {
    	int selectedRow = salesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No invoice is currently "
                    + "selected.", "No Invoice selected", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Invoice invoice = SalesTableModel.getInvoices(selectedRow);
            SalesForm salesForm = new SalesForm(this, "Edit Invoice", true, invoice);
            salesForm.setLocationRelativeTo(this);
            salesForm.setVisible(true);
        }
    }
    
   
    
    private void doHelpButton()
    {
    	JOptionPane.showMessageDialog(this, "Press the 'Select' button after selecting an invoice to see invoice details. \n"
    			+ "Press the 'Add' button to add a invoice. \n"
                + "Press the 'Edit' button after selecting a invoice to edit their name. \n"
                + "Press the 'Exit' button to exit the program.", 
                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void fireDatabaseUpdatedEvent() throws SQLException
    {
        ((SalesTableModel) salesTableModel).databaseUpdated();
    }
       
    private JTable buildSalesTable() throws DBException, SQLException {
        salesTableModel = new SalesTableModel();
        JTable table = new JTable((javax.swing.table.TableModel) salesTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
        return table;
    }
}
