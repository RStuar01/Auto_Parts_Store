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
        setSize(768, 100);
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
            } catch (SQLException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
        });
    
        panel.add(customerInfoButton);
        
        JButton employeeButton = new JButton("Employee");
        employeeButton.addActionListener((ActionEvent) -> {
            try {
                doEmployeeButton();
            } catch (DBException e) {
                System.out.println(e);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
        });
    
        panel.add(employeeButton);
        
        JButton accountingButton = new JButton("Purchases");
        accountingButton.addActionListener((ActionEvent) -> {
            try {
                doAccountingButton();
            } catch (DBException e) {
                System.out.println(e);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(supplierButton);
        
        JButton companyButton = new JButton("Companies");
        companyButton.addActionListener((ActionEvent) -> {
            try {
                doCompanyButton();
            } catch (DBException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(companyButton);
         
        JButton salesButton = new JButton("Sales");
        salesButton.addActionListener((ActionEvent) -> {
            try {
                doSalesButton();
            } catch (DBException e) {
                System.out.println(e);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
    
    private void doCustomerInfoButton() throws UnsupportedLookAndFeelException, DBException, SQLException {
        CustomerInformationFrame custInfoFrame = new CustomerInformationFrame();
    }
    
    private void doEmployeeButton() throws UnsupportedLookAndFeelException, DBException, SQLException {
        EmployeeFrame employeeFrame = new EmployeeFrame();
    }
    
    private void doAccountingButton() throws DBException, UnsupportedLookAndFeelException, SQLException {
        AccountingFrame accountFrame = new AccountingFrame();
    }
    
    private void doSupplierButton() throws DBException, UnsupportedLookAndFeelException, SQLException {
         SupplierFrame supplierFrame = new SupplierFrame();
    }
    
    private void doCompanyButton() throws DBException, UnsupportedLookAndFeelException, SQLException {
       CompanyFrame companyFrame = new CompanyFrame();
   }
    
    private void doPartsButton() throws UnsupportedLookAndFeelException, DBException, SQLException
    {
        PartsFrame partsFrame = new PartsFrame();
    }
    
    private void doSalesButton() throws UnsupportedLookAndFeelException, DBException, SQLException
    {
        SalesFrame salesFrame = new SalesFrame();
    }
    
   
}