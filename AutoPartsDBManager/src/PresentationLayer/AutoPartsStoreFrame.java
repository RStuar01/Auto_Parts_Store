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
/**
*
* @author Michael
*/
public class AutoPartsStoreFrame extends JFrame{

    
   
   public AutoPartsStoreFrame() throws UnsupportedLookAndFeelException, DBException {
       try {
           UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
       }
       catch (ClassNotFoundException | InstantiationException 
               | IllegalAccessException | UnsupportedLookAndFeelException e) {
           System.out.println(e);
       }
       setTitle("Auto Parts Store");
       setSize(768, 384);
       setLocationByPlatform(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       add(buildButtonPanel(), BorderLayout.CENTER);
       setVisible(true);
               
   }
   
   private JPanel buildButtonPanel() throws DBException {
       JPanel panel = new JPanel();
   
       JButton customerInfoButton = new JButton("Customer Information");
       customerInfoButton.addActionListener((ActionEvent) -> {
           try {
               doCustomerInfoButton();
           } catch (DBException e) {
               System.out.println(e);
           } catch (UnsupportedLookAndFeelException ex) {
               Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
   
       panel.add(customerInfoButton);
       
       JButton accountingButton = new JButton("Accounting");
       accountingButton.addActionListener((ActionEvent) -> {
           try {
               doAccountingButton();
           } catch (DBException e) {
               System.out.println(e);
           } catch (UnsupportedLookAndFeelException ex) {
               Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       panel.add(accountingButton);
       
       JButton partsButton = new JButton("Parts");
       partsButton.addActionListener((ActionEvent) -> {
           try {
               doPartsButton();
           } catch (DBException e) {
               System.out.println(e);
           } catch (UnsupportedLookAndFeelException ex) {
               Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       panel.add(partsButton);
       
       JButton supplierButton = new JButton("Suppliers");
       supplierButton.addActionListener((ActionEvent) -> {
           try {
               doSupplierButton();
           } catch (DBException ex) {
               Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
           } catch (UnsupportedLookAndFeelException ex) {
               Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
   
       panel.add(supplierButton);
        
       JButton salesButton = new JButton("Sales");
       salesButton.addActionListener((ActionEvent) -> {
           try {
               doSalesButton();
           } catch (DBException e) {
               System.out.println(e);
           } catch (UnsupportedLookAndFeelException ex) {
               Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
   
       panel.add(salesButton);
       
       
       JButton exitButton = new JButton("Exit");
       exitButton.addActionListener((ActionEvent) -> {
           System.exit(0);
       });
   
       panel.add(exitButton);
   
       return panel;
       
   }
   
   private void doCustomerInfoButton() throws UnsupportedLookAndFeelException, DBException {
       CustomerInformationFrame custInfoFrame = new CustomerInformationFrame();
   }
   
   private void doAccountingButton() throws DBException, UnsupportedLookAndFeelException {
       AccountingFrame accountFrame = new AccountingFrame();
   }
   
   private void doSupplierButton() throws DBException, UnsupportedLookAndFeelException {
        SupplierFrame supplierFrame = new SupplierFrame();
   }
   
   private void doPartsButton() throws UnsupportedLookAndFeelException, DBException
   {
       PartsFrame partsFrame = new PartsFrame();
   }
   
   private void doSalesButton() throws UnsupportedLookAndFeelException, DBException
   {
       SalesFrame salesFrame = new SalesFrame();
   }
   
    
   
}