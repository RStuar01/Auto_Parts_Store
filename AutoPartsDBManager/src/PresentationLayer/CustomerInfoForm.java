package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
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
import DatabaseLayer.DAOFactory;
import DatabaseLayer.WriterDAO;

/**
 * Class extends JDialog for company data entry
 * Written by Michael Meesseman
 */
public class CustomerInfoForm extends JDialog {
	   
	// regexfor email validation
	    private static final String EMAIL_REGEX = 
	    "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
	        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    private static final Pattern EMAIL_PATTERN = 
	                             Pattern.compile(EMAIL_REGEX);
	    
	    //field initialization
	    private JTextField customerIDField;
	    private JTextField lastNameField;
	    private JTextField firstNameField;
	    private JTextField contactInfoIDField;
	    private JTextField addressIDField;
	    private JTextField streetAddressField;
	    private JTextField cityField;
	    private JTextField stateField;
	    private JTextField zipCodeField;
	    private JTextField unitNumberField;
	    private JTextField homePhoneField;
	    private JTextField cellPhoneField;
	    private JTextField emailField;
	    private JButton confirmButton;
	    private JButton cancelButton;
	    
	    //Added by Rick
	    private boolean dataEntered = true;
	    private static WriterDAO writerDAO;
	    
	    private Customer customer = new Customer();
	    
	    /**
	     * Constructor to build dialog box for data entry for new add
	     * @param parent	this is the frame that called the form
	     * @param title		title of the form
	     * @param modal		boolean to block all other input on other windows until current one is closed.
	     * Written by Michael Meesseman
	     */
	    public CustomerInfoForm(java.awt.Frame parent, String title, boolean modal) {
	        super(parent, title, modal);
	        initComponents();
	        
	        // Added by Rick
	        writerDAO = DAOFactory.getWriterDAO();
	    }
	    
	    /**
	     * Constructor to build dialog box for data entry for new add
	     * @param parent	this is the frame that called the form
	     * @param title		title of the form
	     * @param modal		boolean to block all other input on other windows until current one is closed.
	     * @param customer  customer object to fill in fields
	     * Written by Michael Meesseman
	     */
	    public CustomerInfoForm(java.awt.Frame parent, String title, boolean modal, Customer customer) {
	        this(parent, title, modal);
	        this.customer = customer;
	        confirmButton.setText("Save");
	        customerIDField.setText(customer.getCustomerID());
	        lastNameField.setText(customer.getLastName());
	        firstNameField.setText(customer.getFirstName());
	        contactInfoIDField.setText(customer.getContactInfoID());
	        addressIDField.setText(customer.getAddressID());
	        streetAddressField.setText(customer.getStreetAddress());
	        cityField.setText(customer.getCity());
	        stateField.setText(customer.getState());
	        zipCodeField.setText(customer.getZipCode());
	        unitNumberField.setText(customer.getUnitNumber());
	        homePhoneField.setText(customer.getPhoneNumber());
	        cellPhoneField.setText(customer.getCellPhoneNumber());
	        emailField.setText(customer.getEmailAddress());
	        
	        //fields cannot be edited.
	        customerIDField.setEditable(false);
	        contactInfoIDField.setEditable(false);
	        addressIDField.setEditable(false);
	    }
	    
	    /**
	     * Method to initialize all components.
	     * Written by Michael Meesseman
	     */
	    private void initComponents() {
	    	
	    	//focus listeners to remove red text after validation
	    	
	    	customerIDField = new JTextField();
	    	customerIDField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(customerIDField);
				}
			});
	        lastNameField = new JTextField();
	        lastNameField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(lastNameField);
				}
			});
	        firstNameField = new JTextField();
	        firstNameField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(firstNameField);
				}
			});
	        contactInfoIDField = new JTextField();
	        contactInfoIDField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(contactInfoIDField);
				}
			});
	        addressIDField = new JTextField();
	        addressIDField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(addressIDField);
				}
			});
	        streetAddressField = new JTextField();
	        streetAddressField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(streetAddressField);
				}
			});
	        cityField = new JTextField();
	        cityField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(cityField);
				}
			});
	        stateField = new JTextField();
	        stateField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(stateField);
				}
			});
	        zipCodeField = new JTextField();
	        zipCodeField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(zipCodeField);
				}
			});
	        unitNumberField = new JTextField();
	        unitNumberField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(unitNumberField);
				}
			});
	        homePhoneField = new JTextField();
	        homePhoneField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(homePhoneField);
				}
			});
	        cellPhoneField = new JTextField();
	        cellPhoneField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(cellPhoneField);
				}
			});
	        emailField = new JTextField();
	        emailField.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					checkField(emailField);
				}
			});
	        cancelButton = new JButton();
	        confirmButton = new JButton();
	        
	     // fields cannot be edited.
	        customerIDField.setEditable(false);
	        contactInfoIDField.setEditable(false);
	        addressIDField.setEditable(false);
	        
	        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	       
	     // sets field size for window size changes.
	        Dimension longField = new Dimension(300, 20);
	        customerIDField.setPreferredSize(longField);
	        customerIDField.setMinimumSize(longField);
	        firstNameField.setPreferredSize(longField);
	        firstNameField.setMinimumSize(longField);
	        lastNameField.setPreferredSize(longField);
	        lastNameField.setMinimumSize(longField);
	        contactInfoIDField.setPreferredSize(longField);
	        contactInfoIDField.setMinimumSize(longField);
	        addressIDField.setPreferredSize(longField);
	        addressIDField.setMinimumSize(longField);
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
	        
	        // cancel button
	        cancelButton.setText("Cancel");
	        cancelButton.addActionListener((ActionEvent) -> {
	            cancelButtonActionPerformed();
	        });
	        
	        //add button
	        confirmButton.setText("Add");
	        confirmButton.addActionListener((ActionEvent) -> {
	            try {
					confirmButtonActionPerformed();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	        
	     // grid layout for labels and fields
	        JPanel customerPanel = new JPanel();
	        customerPanel.setLayout(new GridBagLayout());
	        customerPanel.add(new JLabel("Customer ID"), getConstraints(0, 0, GridBagConstraints.LINE_END));
	        customerPanel.add(customerIDField, getConstraints(1, 0, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Last Name:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
	        customerPanel.add(lastNameField, getConstraints(1, 1, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("First Name:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
	        customerPanel.add(firstNameField, getConstraints(1, 2, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Contact Info ID:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
	        customerPanel.add(contactInfoIDField, getConstraints(1, 3, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Address ID:"), getConstraints(0, 4, GridBagConstraints.LINE_END));
	        customerPanel.add(addressIDField, getConstraints(1, 4, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Street Address:"), getConstraints(0, 5, GridBagConstraints.LINE_END));
	        customerPanel.add(streetAddressField, getConstraints(1, 5, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("City:"), getConstraints(0, 6, GridBagConstraints.LINE_END));
	        customerPanel.add(cityField, getConstraints(1, 6, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("State:"), getConstraints(0, 7, GridBagConstraints.LINE_END));
	        customerPanel.add(stateField, getConstraints(1, 7, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Zip Code:"), getConstraints(0, 8, GridBagConstraints.LINE_END));
	        customerPanel.add(zipCodeField, getConstraints(1, 8, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Unit Number:"), getConstraints(0, 9, GridBagConstraints.LINE_END));
	        customerPanel.add(unitNumberField, getConstraints(1, 9, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Home Phone:"), getConstraints(0, 10, GridBagConstraints.LINE_END));
	        customerPanel.add(homePhoneField, getConstraints(1, 10, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Cell Phone:"), getConstraints(0, 11, GridBagConstraints.LINE_END));
	        customerPanel.add(cellPhoneField, getConstraints(1, 11, GridBagConstraints.LINE_START));
	        customerPanel.add(new JLabel("Email:"), getConstraints(0, 12, GridBagConstraints.LINE_END));
	        customerPanel.add(emailField, getConstraints(1, 12, GridBagConstraints.LINE_START));
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        buttonPanel.add(confirmButton);
	        buttonPanel.add(cancelButton);
	        
	        setLayout(new BorderLayout());
	        add(customerPanel, BorderLayout.CENTER);
	        add(buttonPanel, BorderLayout.SOUTH);
	        pack();
	    }
	    
	    /**
	     * Method for setting grid of labels and fields.
	     * @param x			x axis
	     * @param y			y axis
	     * @param anchor	where the field sits in the grid space ex. LINE_START or LINE_END.
	     * @return c	GridBagConstraints variable for constraints on the grid.
	     * Written by Michael Meesseman
	     */
	    private GridBagConstraints getConstraints(int x, int y, int anchor) {
	        GridBagConstraints c = new GridBagConstraints();
	        c.insets = new Insets(5,5,0,5);
	        c.gridx = x;
	        c.gridy = y;
	        c.anchor = anchor;
	        return c;
	    }
	    
	    /**
	     * Method executes when cancel button is pressed.
	     * Written by Michael Meesseman
	     */
	    private void cancelButtonActionPerformed() {
	        dispose();
	    }
	    
	    /**
	     * Method executes when add or save button is pressed
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    
	    private void confirmButtonActionPerformed() throws SQLException {
	    	
	    	// Added by Rick
	    	processData();
	    	
	    	if(dataEntered) {
	    		
	    		//Write to the DB
	    		System.out.println("All data entered!");
	    		
	    	}
	    	
	    	dataEntered = true;
	
	    }
	    

	    /**
	     * Method processes data for the add and save buttons.  
		 * Method also validates data before adding to the database
	     * Written by Rick Stuart
	     */
	    private void processData() {
	    	
	    	//used when writing to database.
	    	String choice = "Customer";
	    	
	    	String companyID = "";
	    	
	    	// verifies fields not empty.
	    	String lastName = verifyEntry(lastNameField);
	    	String firstName = verifyEntry(firstNameField);
	    	String streetAddress = verifyEntry(streetAddressField);
	    	String city = verifyEntry(cityField);
	    	String state = verifyEntry(stateField);
	    	String zipCode = verifyEntry(zipCodeField);
	    	String unitNumber = verifyEntry(unitNumberField);
	    	String homePhone = verifyEntry(homePhoneField);
	    	String cellPhone = verifyEntry(cellPhoneField);
	    	String email = verifyEntry(emailField);
	    	
	    	
	    	// regex for phone number validation
	    	// phone number validation and displays dialogs when invalid. 
	    	String phoneNumRegexStr =  "^\\(*\\+*[1-9]{0,3}\\)*-*[1-9]{0,3}[-. /]*\\(*[2-9]\\d{2}\\)*[-. /]*\\d{3}[-. /]*\\d{4} *e*x*t*\\.* *\\d{0,4}$";
	    	boolean homePhoneCheck = homePhone.matches(phoneNumRegexStr);
	    	boolean cellPhoneCheck = cellPhone.matches(phoneNumRegexStr);
	    	
	    	if(!homePhone.matches(phoneNumRegexStr))
	    		JOptionPane.showMessageDialog(this, "Invalid Home Phone Entered.  Please enter phone number"
	    				+ "in ###-###-#### x### format.",
	                    "Invalid Phone Number.", JOptionPane.INFORMATION_MESSAGE);
	    	
	    	if(!cellPhone.matches(phoneNumRegexStr))
	    		JOptionPane.showMessageDialog(this, "Invalid Cell Phone Entered.  Please enter phone number"
	    				+ "in ###-###-#### x### format.",
	                    "Invalid Phone Number.", JOptionPane.INFORMATION_MESSAGE);

	    	//validates fields before adding to database
	    	if(dataEntered && homePhoneCheck && cellPhoneCheck) {
	    		
	    		if (confirmButton.getText().equals("Add")) {
	    			writerDAO.manageNewPersonCreation(choice, lastName, firstName,
	    					streetAddress, city, state, zipCode, unitNumber, homePhone, cellPhone, 
	    					email, companyID);
	    			System.out.println("Adding a new customer");
	    			dispose();
	    		}
	    		else {
	    			//updates during edit
	    			System.out.println("Editing a cusotmer");
	    			String customerID = customerIDField.getText();
	    			String contactID = contactInfoIDField.getText();
	    			String addressID = addressIDField.getText();
	    			writerDAO.manageEditingCustomer(customerID, contactID, addressID, lastName,
	    					firstName, streetAddress, city, state, zipCode, unitNumber, 
	    					homePhone, cellPhone, email);
	    			dispose();
	    		}
	    		
	    		dispose();
	   
	    	}
	    }


	    // Added by Rick
	    /**
	     * Method validate field is not empty. 
	     * turns box red and enters text "Data Missing" when a field is empty.
	     * @param name		Textfield being validated.
	     * Written by Rick Stuart
	     */
	    private String verifyEntry(JTextField name) {
	    	String dataItem = "";
	    	boolean valid = true;
	    		
	    	dataItem = name.getText();
	    	
	    	if(name == unitNumberField) {
	    		//dataItem = name.getText();
	    		if(dataItem.length() == 0 || dataItem == "DataMissing") {
	    			dataItem = "N.A.";
	    			unitNumberField.setText(dataItem);
	    		}
	    	}
	    	
	    	if(name == emailField) {
	    		valid = emailValidator(emailField.getText());
	    		if(!valid) {
	    			emailField.setText("Invalid Email");
	    			name.setForeground(Color.RED);
	    		}
	    	}
	    	
	    	//dataItem = name.getText();
	    	if(dataItem.length() == 0) {
				name.setForeground(Color.RED);
				name.setText("Data Missing");
				dataEntered = false;
	    	}
	    	else if(dataItem.equals("Data Missing") || dataItem.equals("Invalid Email")) {
	    		dataEntered = false;
	    	}
		
	    	return dataItem;
	    }
	    
	    /**
	     * Method validates email field has valid address. 
	     * displays message when invalid email is entered. 
	     * @param email		String of email being validated.
	     * @return boolean	boolean value on whether email is good or not.
	     * Written by Michael Meesseman
	     */
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
	
	    /**
		 * Checks that the Text Field held the Data Missing message before resetting the color.
		 * @param name					JTextField name to be checked.
		 */
		private void checkField(JTextField name) {			
			if(name.getText().equals("Data Missing")) {  
				name.setText("");
				name.setForeground(Color.BLACK);
			}
		}


}