package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import DatabaseLayer.DatabaseWriter;
import BusinessLayer.AccountingPurchases;
import BusinessLayer.Customer;

public class AccountingPurchaseForm extends JDialog{
	 
		    private static final String EMAIL_REGEX = 
		    "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
		        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		    private static final Pattern EMAIL_PATTERN = 
		                             Pattern.compile(EMAIL_REGEX);
		    
		    private JTextField purchaseIDField;
		    private JTextField productIDField;
		    private JTextField purchaseQtyField;
		    private JTextField dollarValueField;
		    private JButton confirmButton;
		    private JButton cancelButton;
		    
		    private AccountingPurchases purchase = new AccountingPurchases();
		    
		    public AccountingPurchaseForm(java.awt.Frame parent, String title, boolean modal) {
		        super(parent, title, modal);
		        initComponents();
		    }
		    
		    public AccountingPurchaseForm(java.awt.Frame parent, String title, boolean modal, AccountingPurchases purchase) {
		        this(parent, title, modal);
		        this.purchase = purchase;
		        confirmButton.setText("Save");
		        purchaseIDField.setText(purchase.getAccountingPurchasesRecordID());
		        productIDField.setText(purchase.getProductID());
		        purchaseQtyField.setText(purchase.getPurchasesQuantity());
		        dollarValueField.setText(purchase.getDollarValue());
		        purchaseIDField.setEditable(false);
		        dollarValueField.setEditable(false);
		     }
		    
		    private void initComponents() {
		    	purchaseIDField = new JTextField();
		        productIDField = new JTextField();
		        purchaseQtyField = new JTextField();
		        dollarValueField = new JTextField();
		        cancelButton = new JButton();
		        confirmButton = new JButton();
		        purchaseIDField.setEditable(false);
		        
		        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		       
		        Dimension longField = new Dimension(300, 20);
		        purchaseIDField.setPreferredSize(longField);
		        purchaseIDField.setMinimumSize(longField);
		        productIDField.setPreferredSize(longField);
		        productIDField.setMinimumSize(longField);
		        purchaseQtyField.setPreferredSize(longField);
		        purchaseQtyField.setMinimumSize(longField);
		        dollarValueField.setPreferredSize(longField);
		        dollarValueField.setMinimumSize(longField);
		        
		        cancelButton.setText("Cancel");
		        cancelButton.addActionListener((ActionEvent) -> {
		            cancelButtonActionPerformed();
		        });
		        
		        confirmButton.setText("Add");
		        confirmButton.addActionListener((ActionEvent) -> {
		            try {
						confirmButtonActionPerformed();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        });
		        
		        JPanel purchasePanel = new JPanel();
		        purchasePanel.setLayout(new GridBagLayout());
		        purchasePanel.add(new JLabel("Purchase ID:"), getConstraints(0, 0, GridBagConstraints.LINE_END));
		        purchasePanel.add(purchaseIDField, getConstraints(1, 0, GridBagConstraints.LINE_START));
		        purchasePanel.add(new JLabel("Product ID:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
		        purchasePanel.add(productIDField, getConstraints(1, 1, GridBagConstraints.LINE_START));
		        purchasePanel.add(new JLabel("Purchase Quantity:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
		        purchasePanel.add(purchaseQtyField, getConstraints(1, 2, GridBagConstraints.LINE_START));
		        purchasePanel.add(new JLabel("Dollar Value:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
		        purchasePanel.add(dollarValueField, getConstraints(1, 3, GridBagConstraints.LINE_START));
		        
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		        buttonPanel.add(confirmButton);
		        buttonPanel.add(cancelButton);
		        
		        setLayout(new BorderLayout());
		        add(purchasePanel, BorderLayout.CENTER);
		        add(buttonPanel, BorderLayout.SOUTH);
		        pack();
		    }
		    
		    private GridBagConstraints getConstraints(int x, int y, int anchor) {
		        GridBagConstraints c = new GridBagConstraints();
		        c.insets = new Insets(5,5,0,5);
		        c.gridx = x;
		        c.gridy = y;
		        c.anchor = anchor;
		        return c;
		    }
		    
		    private void cancelButtonActionPerformed() {
		        dispose();
		    }
		    
		    private void confirmButtonActionPerformed() throws SQLException {
		        if (validateData()) {
		            setData();
		            if (confirmButton.getText().equals("Add")) {
		                doAdd();
		            }
		            else 
		            {
		                doEdit();
		            }
		        }
		    }
		    
		    /*
		    private boolean isEmpty()
		    {
		        String e = firstNameField.getText();
		        String lastName = lastNameField.getText();
		        String email = emailField.getText();
		        
		        if (firstName.equals("") || lastName.equals("") || email.equals("") 
		                || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) 
		        {
		            JOptionPane.showMessageDialog(this, "Please fill in all fields.",
		                    "Missing Fields", JOptionPane.INFORMATION_MESSAGE);
		            return false;
		        }
		        else
		            return true;
		    }
		    
		    */
		    
		    private boolean validateData() {
		        
		        boolean valid = false;
		        //String email = emailField.getText();
		        
		        if (confirmButton.getText().equals("Add")) 
		        {
		            //if(isEmpty())
		              //  if(emailValidator(email)) 
		                    //if(customerNotExists(email))
		                        valid = true;
		        }
		        else 
		        {
		           // if(isEmpty())
		               valid = true;
		        }
		        
		        return valid;
		    }

		    /*
		    private boolean customerNotExists(String email)
		    {
		        
		    boolean valid = false;
		    
		    List<Customer> customers;
		    try 
		    {
		        customers = CustomerDB.getCustomers();
		        
		        if (customers.isEmpty())
		            return true;
		        
		        for (Customer c : customers)
		            {
		                if (c.getEmailAddress().equalsIgnoreCase(emailField.getText()))
		                {
		                    JOptionPane.showMessageDialog(this, "A customer already has that email address. \nPlease"
		                               + " enter a different email address.",
		                     "Invalid Email", JOptionPane.ERROR_MESSAGE);
		                    emailField.grabFocus();
		                    valid = false;
		                }
		                else
		                    valid = true;
		            }
		    }
		    catch (DBException e)
		    {
		        System.out.println(e);
		    }
		 
		        return valid;
		    }
		    
		    */
		    
		    private void setData() {
		    	
		    	String purchaseID = purchaseIDField.getText();
		        String productID = productIDField.getText();
		        String purchaseQty = purchaseQtyField.getText();
		        String dollarValue = dollarValueField.getText();
		        purchase.setAccountingPurchasesRecordID(purchaseID);
		        purchase.setProductID(productID);
		        purchase.setPurchasesQuantity(purchaseQty);
		        purchase.setDollarValue(dollarValue);
		    }
		    
		    private void doEdit() {
		        try {
		            //update customer method
		            dispose();
		            fireDatabaseUpdatedEvent();
		        }
		        catch (SQLException e)
		        {
		           System.out.println(e);
		        }
		    }
		     
		    private void doAdd() throws SQLException {
		        try {
		            //add customer method from DatabaseWriter
		            dispose();
		            fireDatabaseUpdatedEvent();
		        }
		        catch (SQLException e)
		        {
		            System.out.println(e);
		        }
		    }
		    
		    private void fireDatabaseUpdatedEvent() throws SQLException {
		        AccountingFrame mainWindow = (AccountingFrame) getOwner();
		        mainWindow.fireDatabaseUpdatedEvent();
		    }
		       
		    
		    private boolean emailValidator(String email)
		    {
		        if (email == null) 
		            return false;        
		 
		        Matcher matcher = EMAIL_PATTERN.matcher(email);
		        if (matcher.matches())
		            return true;
		        else
		        {
		            JOptionPane.showMessageDialog(this, "Invalid email address entered. \nPlease"
		                        + " enter an email address in the format of xxxxxxxxxx@xxxxxx.xxx",
		                    "Invalid Email", JOptionPane.ERROR_MESSAGE);
		            //emailField.grabFocus();
		            return false;
		        
		        }
		    }
		
		   


	


}
