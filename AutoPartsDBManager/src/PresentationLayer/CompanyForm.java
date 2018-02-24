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
import java.awt.event.FocusListener;
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

import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Supplier;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseWriter;
import DatabaseLayer.WriterDAO;

public class CompanyForm extends JDialog {

		  
			    private static final String EMAIL_REGEX = 
			    "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
			        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			    private static final Pattern EMAIL_PATTERN = 
			                             Pattern.compile(EMAIL_REGEX);
			    
			    private JTextField companyIDField;
			    private JTextField addressIDField;
			    private JTextField contactIDField;
			    private JTextField companyNameField;
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
			    
			    private Company company = new Company();
			    
			    public CompanyForm(java.awt.Frame parent, String title, boolean modal) {
			        super(parent, title, modal);
			        initComponents();
			        
			     // Added by Rick
			        writerDAO = DAOFactory.getWriterDAO();
			    }
			    
			    public CompanyForm(java.awt.Frame parent, String title, boolean modal, Company company) {
			        this(parent, title, modal);
			        this.company = company;
			        confirmButton.setText("Save");
			        companyIDField.setText(company.getCompanyID());
			        addressIDField.setText(company.getAddressID());
			        contactIDField.setText(company.getContactInfoID());
			        companyNameField.setText(company.getCompanyName());			       
			        streetAddressField.setText(company.getStreetAddress());
			        cityField.setText(company.getCity());
			        stateField.setText(company.getState());
			        zipCodeField.setText(company.getZipCode());
			        unitNumberField.setText(company.getUnitNumber());
			        homePhoneField.setText(company.getPhoneNumber());
			        cellPhoneField.setText(company.getCellPhoneNumber());
			        emailField.setText(company.getEmailAddress());
			        companyIDField.setEditable(false);
			        addressIDField.setEditable(false);
			        contactIDField.setEditable(false);
			        }
			    
			    private void initComponents() {
			    	companyIDField = new JTextField();
			    	companyIDField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(companyIDField);
						}
					});
			        addressIDField = new JTextField();
			        addressIDField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(addressIDField);
						}
					});
			        contactIDField = new JTextField();
			        contactIDField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(contactIDField);
						}
					});
			        companyNameField = new JTextField();
			        companyNameField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(companyNameField);
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
			        companyIDField.setEditable(false);
			        addressIDField.setEditable(false);
			        contactIDField.setEditable(false);
			        
			        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			       
			        Dimension longField = new Dimension(300, 20);
			        companyIDField.setPreferredSize(longField);
			        companyIDField.setMinimumSize(longField);
			        addressIDField.setPreferredSize(longField);
			        addressIDField.setMinimumSize(longField);
			        contactIDField.setPreferredSize(longField);
			        contactIDField.setMinimumSize(longField);
			        companyNameField.setPreferredSize(longField);
			        companyNameField.setMinimumSize(longField);
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
			        supplierPanel.add(new JLabel("Company ID"), getConstraints(0, 0, GridBagConstraints.LINE_END));
			        supplierPanel.add(companyIDField, getConstraints(1, 0, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Address ID:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
			        supplierPanel.add(addressIDField, getConstraints(1, 1, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Contact Info ID:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
			        supplierPanel.add(contactIDField, getConstraints(1, 2, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Company Name:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
			        supplierPanel.add(companyNameField, getConstraints(1, 3, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Street Address:"), getConstraints(0, 4, GridBagConstraints.LINE_END));
			        supplierPanel.add(streetAddressField, getConstraints(1, 4, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("City:"), getConstraints(0, 5, GridBagConstraints.LINE_END));
			        supplierPanel.add(cityField, getConstraints(1, 5, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("State:"), getConstraints(0, 6, GridBagConstraints.LINE_END));
			        supplierPanel.add(stateField, getConstraints(1, 6, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Zip Code:"), getConstraints(0, 7, GridBagConstraints.LINE_END));
			        supplierPanel.add(zipCodeField, getConstraints(1, 7, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Unit Number:"), getConstraints(0, 8, GridBagConstraints.LINE_END));
			        supplierPanel.add(unitNumberField, getConstraints(1, 8, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Home Phone:"), getConstraints(0, 9, GridBagConstraints.LINE_END));
			        supplierPanel.add(homePhoneField, getConstraints(1, 9, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Cell Phone:"), getConstraints(0, 10, GridBagConstraints.LINE_END));
			        supplierPanel.add(cellPhoneField, getConstraints(1, 10, GridBagConstraints.LINE_START));
			        supplierPanel.add(new JLabel("Email:"), getConstraints(0, 11, GridBagConstraints.LINE_END));
			        supplierPanel.add(emailField, getConstraints(1, 11, GridBagConstraints.LINE_START));
			        
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
			        
			    	// Added by Rick
			    	processData();
			    	
			    	if(dataEntered) {
			    		
			    		//Write to the DB
			    		System.out.println("All data entered!");
			    		
			    	}
			    	
			    	dataEntered = true;
			    	
			    	// Modified by Rick
			    	/*
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
			        */
			    }
			    
			    // Added by Rick
			    private void processData() {
			    	
			    	String choice = "Company";
			    	
			    	String companyName = verifyEntry(companyNameField);
			    	String streetAddress = verifyEntry(streetAddressField);
			    	String city = verifyEntry(cityField);
			    	String state = verifyEntry(stateField);
			    	String zipCode = verifyEntry(zipCodeField);
			    	String unitNumber = verifyEntry(unitNumberField);
			    	String homePhone = verifyEntry(homePhoneField);
			    	String cellPhone = verifyEntry(cellPhoneField);
			    	String email = verifyEntry(emailField);
			    	
			    	
			    	if(dataEntered) {
			    		writerDAO.createNewCompany(streetAddress, city, state, zipCode, 
			    				unitNumber, homePhone, cellPhone, email, 
			    				companyName);
			    		dispose();
		    				
			    	}
			    }
			    
			 // Added by Rick
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
			    
			    /*
			    private boolean isEmpty()
			    {
			        
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
			        String email = emailField.getText();
			        
			        if (confirmButton.getText().equals("Add")) 
			        {
			            //if(isEmpty())
			                if(emailValidator(email)) 
			                    //if(customerNotExists(email))
			                        valid = true;
			        }
			        else 
			        {
			            //if(isEmpty())
			               //valid = true;
			        }
			        
			        return valid;
			    }

			
			    
			    private void setData() {
			    	
			    	String companyID = companyIDField.getText();
			        String addressID = addressIDField.getText();
			        String contactID = contactIDField.getText();
			        String companyName = companyNameField.getText();
			        String streetAddress = streetAddressField.getText();
			        String city = cityField.getText();
			        String state = stateField.getText();
			        String zipCode = zipCodeField.getText();
			        String unitNumber = unitNumberField.getText();
			        String homePhone = homePhoneField.getText();
			        String cellPhone = cellPhoneField.getText();
			        String email = emailField.getText();
			        company.setCompanyID(companyID);
			        company.setAddressID(addressID);
			        company.setContactInfoID(contactID);
			        company.setCompanyName(companyName);
			        company.setStreetAddres(streetAddress);
			        company.setCity(city);
			        company.setState(state);
			        company.setZipCode(zipCode);
			        company.setUnitNumber(unitNumber);
			        company.setPhoneNumber(homePhone);
			        company.setCellPhoneNumber(cellPhone);
			        company.setEmailAddress(email);
			       
			        
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
