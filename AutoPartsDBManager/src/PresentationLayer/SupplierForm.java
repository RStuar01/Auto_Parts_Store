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

import BusinessLayer.Customer;
import BusinessLayer.Supplier;
import DatabaseLayer.DatabaseWriter;

public class SupplierForm extends JDialog {
	  
		    private static final String EMAIL_REGEX = 
		    "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
		        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		    private static final Pattern EMAIL_PATTERN = 
		                             Pattern.compile(EMAIL_REGEX);
		    
		    private JTextField supplierIDField;
		    private JTextField lastNameField;
		    private JTextField firstNameField;
		    private JTextField contactInfoIDField;
		    private JTextField addressIDField;
		    private JTextField companyIDField;
		    private JTextField streetAddressField;
		    private JTextField cityField;
		    private JTextField stateField;
		    private JTextField zipCodeField;
		    private JTextField unitNumberField;
		    private JTextField homePhoneField;
		    private JTextField cellPhoneField;
		    private JTextField emailField;
		    private JTextField companyNameField;
		    private JButton confirmButton;
		    private JButton cancelButton;
		    
		    private Supplier supplier = new Supplier();
		    
		    public SupplierForm(java.awt.Frame parent, String title, boolean modal) {
		        super(parent, title, modal);
		        initComponents();
		    }
		    
		    public SupplierForm(java.awt.Frame parent, String title, boolean modal, Supplier supplier) {
		        this(parent, title, modal);
		        this.supplier = supplier;
		        confirmButton.setText("Save");
		        supplierIDField.setText(supplier.getSupplierID());
		        lastNameField.setText(supplier.getLastName());
		        firstNameField.setText(supplier.getFirstName());
		        contactInfoIDField.setText(supplier.getContactInfoID());
		        addressIDField.setText(supplier.getAddressID());
		        companyIDField.setText(supplier.getCompanyID());
		        streetAddressField.setText(supplier.getStreetAddress());
		        cityField.setText(supplier.getCity());
		        stateField.setText(supplier.getState());
		        zipCodeField.setText(supplier.getZipCode());
		        unitNumberField.setText(supplier.getUnitNumber());
		        homePhoneField.setText(supplier.getPhoneNumber());
		        cellPhoneField.setText(supplier.getCellPhoneNumber());
		        emailField.setText(supplier.getEmailAddress());
		        companyNameField.setText(supplier.getCompanyName());
		        supplierIDField.setEditable(false);
		        firstNameField.setEditable(false);
		    }
		    
		    private void initComponents() {
		    	supplierIDField = new JTextField();
		        lastNameField = new JTextField();
		        firstNameField = new JTextField();
		        contactInfoIDField = new JTextField();
		        addressIDField = new JTextField();
		        companyIDField = new JTextField();
		        streetAddressField = new JTextField();
		        cityField = new JTextField();
		        stateField = new JTextField();
		        zipCodeField = new JTextField();
		        unitNumberField = new JTextField();
		        homePhoneField = new JTextField();
		        cellPhoneField = new JTextField();
		        emailField = new JTextField();
		        companyNameField = new JTextField();
		        cancelButton = new JButton();
		        confirmButton = new JButton();
		        
		        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		       
		        Dimension longField = new Dimension(300, 20);
		        supplierIDField.setPreferredSize(longField);
		        supplierIDField.setMinimumSize(longField);
		        firstNameField.setPreferredSize(longField);
		        firstNameField.setMinimumSize(longField);
		        lastNameField.setPreferredSize(longField);
		        lastNameField.setMinimumSize(longField);
		        contactInfoIDField.setPreferredSize(longField);
		        contactInfoIDField.setMinimumSize(longField);
		        addressIDField.setPreferredSize(longField);
		        addressIDField.setMinimumSize(longField);
		        companyIDField.setPreferredSize(longField);
		        companyIDField.setMinimumSize(longField);
		        streetAddressField.setPreferredSize(longField);
		        streetAddressField.setMinimumSize(longField);
		        cityField.setPreferredSize(longField);
		        cityField.setMinimumSize(longField);
		        stateField.setPreferredSize(longField);
		        stateField.setMinimumSize(longField);
		        zipCodeField.setPreferredSize(longField);
		        zipCodeField.setMinimumSize(longField);
		        unitNumberField.setPreferredSize(longField);
		        unitNumberField.setMinimumSize(longField);
		        homePhoneField.setPreferredSize(longField);
		        homePhoneField.setMinimumSize(longField);
		        cellPhoneField.setPreferredSize(longField);
		        cellPhoneField.setMinimumSize(longField);
		        emailField.setPreferredSize(longField);
		        emailField.setMinimumSize(longField);
		        companyNameField.setPreferredSize(longField);
		        companyNameField.setMinimumSize(longField);
		        
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
		        
		        JPanel supplierPanel = new JPanel();
		        supplierPanel.setLayout(new GridBagLayout());
		        supplierPanel.add(new JLabel("Supplier ID"), getConstraints(0, 0, GridBagConstraints.LINE_END));
		        supplierPanel.add(supplierIDField, getConstraints(1, 0, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Last Name:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
		        supplierPanel.add(lastNameField, getConstraints(1, 1, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("First Name:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
		        supplierPanel.add(firstNameField, getConstraints(1, 2, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Contact Info ID:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
		        supplierPanel.add(contactInfoIDField, getConstraints(1, 3, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Address ID:"), getConstraints(0, 4, GridBagConstraints.LINE_END));
		        supplierPanel.add(addressIDField, getConstraints(1, 4, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Company ID:"), getConstraints(0, 5, GridBagConstraints.LINE_END));
		        supplierPanel.add(companyIDField, getConstraints(1, 5, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Street Address:"), getConstraints(0, 6, GridBagConstraints.LINE_END));
		        supplierPanel.add(streetAddressField, getConstraints(1, 6, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("City:"), getConstraints(0, 7, GridBagConstraints.LINE_END));
		        supplierPanel.add(cityField, getConstraints(1, 7, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("State:"), getConstraints(0, 8, GridBagConstraints.LINE_END));
		        supplierPanel.add(stateField, getConstraints(1, 8, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Zip Code:"), getConstraints(0, 9, GridBagConstraints.LINE_END));
		        supplierPanel.add(zipCodeField, getConstraints(1, 9, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Unit Number:"), getConstraints(0, 10, GridBagConstraints.LINE_END));
		        supplierPanel.add(unitNumberField, getConstraints(1, 10, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Home Phone:"), getConstraints(0, 11, GridBagConstraints.LINE_END));
		        supplierPanel.add(homePhoneField, getConstraints(1, 11, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Cell Phone:"), getConstraints(0, 12, GridBagConstraints.LINE_END));
		        supplierPanel.add(cellPhoneField, getConstraints(1, 12, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Email:"), getConstraints(0, 13, GridBagConstraints.LINE_END));
		        supplierPanel.add(emailField, getConstraints(1, 13, GridBagConstraints.LINE_START));
		        supplierPanel.add(new JLabel("Company Name:"), getConstraints(0, 14, GridBagConstraints.LINE_END));
		        supplierPanel.add(companyNameField, getConstraints(1, 14, GridBagConstraints.LINE_START));
		        
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		        buttonPanel.add(confirmButton);
		        buttonPanel.add(cancelButton);
		        
		        setLayout(new BorderLayout());
		        add(supplierPanel, BorderLayout.CENTER);
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
		    
		    
		    private boolean isEmpty()
		    {
		        String firstName = firstNameField.getText();
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
		    
		    private boolean validateData() {
		        
		        boolean valid = false;
		        String email = emailField.getText();
		        
		        if (confirmButton.getText().equals("Add")) 
		        {
		            if(isEmpty())
		                if(emailValidator(email)) 
		                    //if(customerNotExists(email))
		                        valid = true;
		        }
		        else 
		        {
		            if(isEmpty())
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
		    	
		    	String supplierID = supplierIDField.getText();
		        String firstName = firstNameField.getText();
		        String lastName = lastNameField.getText();
		        String contactInfoID = contactInfoIDField.getText();
		        String addressID = addressIDField.getText();
		        String companyID = companyIDField.getText();
		        String streetAddress = streetAddressField.getText();
		        String city = cityField.getText();
		        String state = stateField.getText();
		        String zipCode = zipCodeField.getText();
		        String unitNumber = unitNumberField.getText();
		        String homePhone = homePhoneField.getText();
		        String cellPhone = cellPhoneField.getText();
		        String email = emailField.getText();
		        String companyName = companyNameField.getText();
		        supplier.setSupplierID(supplierID);
		        supplier.setFirstName(firstName);
		        supplier.setLastName(lastName);
		        supplier.setContactInfoID(contactInfoID);
		        supplier.setAddressID(addressID);
		        supplier.setCompanyID(companyID);
		        supplier.setStreetAddress(streetAddress);
		        supplier.setCity(city);
		        supplier.setState(state);
		        supplier.setZipCode(zipCode);
		        supplier.setUnitNumber(unitNumber);
		        supplier.setPhoneNumber(homePhone);
		        supplier.setCellPhoneNumber(cellPhone);
		        supplier.setEmailAddress(email);
		        supplier.setCompanyName(companyName);
		        
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
		        SupplierFrame mainWindow = (SupplierFrame) getOwner();
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
		            emailField.grabFocus();
		            return false;
		        
		        }
		    }
		
		   


	


}
