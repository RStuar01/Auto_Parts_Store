package PresentationLayer;


import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * Class for main screen in program.  Extends JFrame
 * Written by Michael Meesseman
 */
public class AutoPartsStoreFrame extends JFrame{
 
     
	   /**
     * Constructor to build the frame when user logs in. 
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    public AutoPartsStoreFrame() throws UnsupportedLookAndFeelException, SQLException {
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
    
    /**
     * Method to build the Button Panel.
     * @return panel	this is the button panel that goes to the SOUTH of the frame.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private JPanel buildButtonPanel() throws SQLException {
        JPanel panel = new JPanel();
    
        // Customer information button
        JButton customerInfoButton = new JButton("Customer Information");
        customerInfoButton.addActionListener((ActionEvent) -> {
            try {
                doCustomerInfoButton();
            } catch (SQLException e) {
                System.out.println(e);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
        });
    
        panel.add(customerInfoButton);
        
        // employee information button
        JButton employeeButton = new JButton("Employee");
        employeeButton.addActionListener((ActionEvent) -> {
            try {
                doEmployeeButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
        });
    
        panel.add(employeeButton);
        
        // accounting purchases button
        JButton accountingButton = new JButton("Purchases");
        accountingButton.addActionListener((ActionEvent) -> {
            try {
                doAccountingButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        panel.add(accountingButton);
        
        // products button
        JButton partsButton = new JButton("Parts");
        partsButton.addActionListener((ActionEvent) -> {
            try {
                doPartsButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        panel.add(partsButton);
        
        // supplier information button
        JButton supplierButton = new JButton("Suppliers");
        supplierButton.addActionListener((ActionEvent) -> {
            try {
                doSupplierButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(supplierButton);
        
        // company information button
        JButton companyButton = new JButton("Companies");
        companyButton.addActionListener((ActionEvent) -> {
            try {
                doCompanyButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(companyButton);
         
        // sales invoices button
        JButton salesButton = new JButton("Sales");
        salesButton.addActionListener((ActionEvent) -> {
            try {
                doSalesButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(AutoPartsStoreFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(salesButton);
        
        // exit button. closes program.
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent) -> {
            System.exit(0);
        });
    
        panel.add(exitButton);
    
        return panel;
        
    }
    
    /**
     * Method executes and opens a customer information window.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doCustomerInfoButton() throws UnsupportedLookAndFeelException, SQLException {
        CustomerInformationFrame custInfoFrame = new CustomerInformationFrame();
    }
    
    /**
     * Method executes and opens a employee information window.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doEmployeeButton() throws UnsupportedLookAndFeelException, SQLException {
        EmployeeFrame employeeFrame = new EmployeeFrame();
    }
    
    /**
     * Method executes and opens a accounting purchases window.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doAccountingButton() throws UnsupportedLookAndFeelException, SQLException {
        AccountingFrame accountFrame = new AccountingFrame();
    }
    
    /**
     * Method executes and opens a supplier information window.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doSupplierButton() throws UnsupportedLookAndFeelException, SQLException {
         SupplierFrame supplierFrame = new SupplierFrame();
    }
    
    /**
     * Method executes and opens a company information window.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doCompanyButton() throws UnsupportedLookAndFeelException, SQLException {
       CompanyFrame companyFrame = new CompanyFrame();
   }
    
    /**
     * Method executes and opens a product information window.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doPartsButton() throws UnsupportedLookAndFeelException, SQLException
    {
        PartsFrame partsFrame = new PartsFrame();
    }
    
    /**
     * Method executes and opens a sales invoice window.
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doSalesButton() throws UnsupportedLookAndFeelException, SQLException
    {
        SalesFrame salesFrame = new SalesFrame();
    }
    
   
}