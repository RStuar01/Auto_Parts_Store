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

import BusinessLayer.Supplier;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseWriter;
import DatabaseLayer.ReaderDAO;
import DatabaseLayer.WriterDAO;

/**
 * Class extends JDialog for supplier entry
 * Written by Michael Meesseman
 */
public class SupplierForm extends JDialog {
	  		
		//regex for email validation
		    private static final String EMAIL_REGEX = 
		    "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
		        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		    private static final Pattern EMAIL_PATTERN = 
		                             Pattern.compile(EMAIL_REGEX);
		 // text field initialization
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
		    
		    //Added by Rick
		    private boolean dataEntered = true;
		    private static WriterDAO writerDAO;
		    private static ReaderDAO readerDAO;
		    private String companyID;
		    
		    private Supplier supplier = new Supplier();
		    
		    /**
		     * Constructor to build dialog box for data entry for new add
		     * @param parent	this is the frame that called the form
		     * @param title		title of the form
		     * @param modal		boolean to block all other input on other windows until current one is closed.
		     * Written by Michael Meesseman
		     */
		    public SupplierForm(java.awt.Frame parent, String title, boolean modal) {
		        super(parent, title, modal);
		        initComponents();
		        
		     // Added by Rick
		        writerDAO = DAOFactory.getWriterDAO();
		        readerDAO = DAOFactory.getReaderDAO();
		    }
		    
		    /**
		     * Constructor to build dialog box for data entry for edit
		     * @param parent	this is the frame that called the form
		     * @param title		title of the form
		     * @param modal		boolean to block all other input on other windows until current one is closed.
		     * @param supplier	Supplier object to fill fields for edit.
		     * @param companyID	brings in company id for autofill of company name.
		     * Written by Michael Meesseman
		     */
		    public SupplierForm(java.awt.Frame parent, String title, boolean modal, Supplier supplier, String companyID) {
		        this(parent, title, modal);
		        this.supplier = supplier;
		        this.companyID = companyID;
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
		        
		        //auto fill of company name
		        companyNameField.setText(readerDAO.obtainCompanyName(companyID));
		        
		        //fields cannot be edited. 
		        supplierIDField.setEditable(false);
		        contactInfoIDField.setEditable(false);
		        addressIDField.setEditable(false);
		        companyNameField.setEditable(false);
		        
		    }
		    
		    
		    /**
		     * Method to initialize all components.
		     * Written by Michael Meesseman
		     */
		    private void initComponents() {
		    	
		    	//focus listeners to remove red text after validation
		    	supplierIDField = new JTextField();
		    	supplierIDField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(supplierIDField);
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
		        companyIDField = new JTextField();
		        companyIDField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(companyIDField);
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
		        companyNameField = new JTextField();
		        companyNameField.setEditable(false);
		        
		        companyNameField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(companyNameField);
					}
				});
		        cancelButton = new JButton();
		        confirmButton = new JButton();
		        
		        //fields cannot be edited.
		        supplierIDField.setEditable(false);
		        contactInfoIDField.setEditable(false);
		        addressIDField.setEditable(false);
		        
		        
		        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		       
		     // sets field size for window size changes.
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
		        
		        //cancel button
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
		    
		    // Added by Rick
		    /**
		     * Method processes data for the add and save buttons.  
			 * Method also validates data before adding to the database
		     * Written by Rick Stuart
		     */
		    private void processData() {
		    	
		    	boolean valid = false;
		    	
		    	//used when writing data to database
		    	String choice = "Supplier";
		    	
		    	//verifies fields not empty
		    	String lastName = verifyEntry(lastNameField);
		    	String firstName = verifyEntry(firstNameField);
		    	String companyID = verifyEntry(companyIDField);
		    	String streetAddress = verifyEntry(streetAddressField);
		    	String city = verifyEntry(cityField);
		    	String state = verifyEntry(stateField);
		    	String zipCode = verifyEntry(zipCodeField);
		    	String unitNumber = verifyEntry(unitNumberField);
		    	String homePhone = verifyEntry(homePhoneField);
		    	String cellPhone = verifyEntry(cellPhoneField);
		    	String email = verifyEntry(emailField);
		    	//String companyName = verifyEntry(companyNameField);
		    	
		    	//regex for phone number validation
		    	//displays dialog box when bad number is entered. 
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
		    	
		    	
		    	valid = writerDAO.checkCompanyExists(companyID);
		    	if(!valid)
		    	{
		    		System.out.println("Company for this ID number does not exist");
	    			JOptionPane.showMessageDialog(this, "Invalid Company ID Entered.",
		                    "Invalid Company ID", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    	else {
		    		System.out.println("ID: " + companyID);
		    		String companyName = readerDAO.obtainCompanyName(companyID);
		    		companyNameField.setText(companyName);
		    	}
		    	
		    	if(dataEntered && homePhoneCheck && cellPhoneCheck && valid) {
		    		
		    		System.out.println("Valid: " + valid);
		    		
		    			if (confirmButton.getText().equals("Add")) {
			    			writerDAO.manageNewPersonCreation(choice, lastName, firstName,
			    					streetAddress, city, state, zipCode, unitNumber, homePhone, cellPhone, 
			    					email, companyID);
			    			System.out.println("Adding a new supplier");
			    			dispose();
			    		}
			    		else {
			    			System.out.println("Editing a supplier");
			    			String employeeID = supplierIDField.getText();
			    			String contactID = contactInfoIDField.getText();
			    			String addressID = addressIDField.getText();
			    			((DatabaseWriter) writerDAO).manageEditingSupplier(employeeID, contactID, addressID,
			    					companyID, lastName,
			    					firstName, streetAddress, city, state, zipCode, unitNumber, 
			    					homePhone, cellPhone, email);
			    			dispose();
			    		}
			    				    	
		    		
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
		    			dataEntered = false;
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
