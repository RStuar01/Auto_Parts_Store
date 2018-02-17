package PresentationLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package autopartsstoregui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
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

/**
*
* @author Michael
*/
public class AccountingFrame  extends JFrame {
   private JTable customerTable;
   private TableModel customerTableModel;
   
   public AccountingFrame() throws UnsupportedLookAndFeelException, DBException {
       try {
           UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
       }
       catch (ClassNotFoundException | InstantiationException 
               | IllegalAccessException | UnsupportedLookAndFeelException e) {
           System.out.println(e);
       }
       setTitle("Accounting");
       setSize(768, 384);
       setLocationByPlatform(true);
       //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       add(buildButtonPanel(), BorderLayout.SOUTH);
       //customerTable = buildCustomerTable();
       add(new JScrollPane(customerTable), BorderLayout.CENTER);
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
           
       });
   
       panel.add(exitButton);
       
       return panel;
       
   }
   
   private void doAddButton() {
       
   }
   
   private void doEditButton() throws DBException {
       
   }
   
   private void doDeleteButton() throws DBException {
       
   }
   
   private void doHelpButton()
   {
       
   }
      
  // private JTable buildCustomerTable() throws DBException {
  //     customerTableModel = new TableModel();
  //     JTable table = new JTable((javax.swing.table.TableModel) customerTableModel);
  //     table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  //     table.setBorder(null);
  //     return table;
  // }
}

