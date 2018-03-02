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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import BusinessLayer.Invoice;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.WriterDAO;

/**
 * Class extends JDialog for sales data entry
 * Written by Michael Meesseman
 */
public class SalesForm extends JDialog{

	// text field initialization
				    private JTextField invoiceNumberField;
				    private JTextField dateField;
				    private JTextField timeField;
				    private JTextField customerIDField;
				    private JTextField employeeIDField;
				    private JButton confirmButton;
				    private JButton cancelButton;
				    
				  //Added by Rick
				    private boolean dataEntered = true;
				    private static WriterDAO writerDAO;
				    
				    private Invoice invoice = new Invoice();
				    
				    /**
				     * Constructor to build dialog box for data entry for new add
				     * @param parent	this is the frame that called the form
				     * @param title		title of the form
				     * @param modal		boolean to block all other input on other windows until current one is closed.
				     * Written by Michael Meesseman
				     */
				    public SalesForm(java.awt.Frame parent, String title, boolean modal) {
				        super(parent, title, modal);
				        initComponents();
				        
				        // Added by Rick
				        writerDAO = DAOFactory.getWriterDAO();
				    }
				    
				    /**
				     * Constructor to build dialog box for data entry for edit
				     * @param parent	this is the frame that called the form
				     * @param title		title of the form
				     * @param modal		boolean to block all other input on other windows until current one is closed.
				     * @param invoice	Invoice object to fill fields for edit.
				     * Written by Michael Meesseman
				     */
				    public SalesForm(java.awt.Frame parent, String title, boolean modal, Invoice invoice) {
				        this(parent, title, modal);
				        this.invoice = invoice;
				        confirmButton.setText("Save");
				        invoiceNumberField.setText(invoice.getInvoiceNumber());
				        dateField.setText(invoice.getDate());
				        timeField.setText(invoice.getTime());
				        customerIDField.setText(invoice.getCustomerID());
				        employeeIDField.setText(invoice.getEmployeeID());
				        
				        //field cannot be edited. 
				        invoiceNumberField.setEditable(false);
				        }
				    
				    /**
				     * Method to initialize all components.
				     * Written by Michael Meesseman
				     */
				    private void initComponents() {
				    	
				    	//focus listeners to remove red text after validation
				    	invoiceNumberField = new JTextField();
				    	invoiceNumberField.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent arg0) {
								checkField(invoiceNumberField);
							}
						});
				        dateField = new JTextField();
				        
				        dateField.setText(LocalDate.now().toString());
				        timeField = new JTextField();
				        
				        //pull in date and time.
				        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
				        LocalTime time = LocalTime.now();
				        timeField.setText(formatter.format(time).toString());
				        
				        customerIDField = new JTextField();
				        customerIDField.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent arg0) {
								checkField(customerIDField);
							}
						});
				        employeeIDField = new JTextField();
				        employeeIDField.addFocusListener(new FocusAdapter() {
							@Override
							public void focusGained(FocusEvent arg0) {
								checkField(employeeIDField);
							}
						});
				        
				        cancelButton = new JButton();
				        confirmButton = new JButton();
				        
				        //field cannot be edited. 
				        invoiceNumberField.setEditable(false);
				        
				        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				       
				     // sets field size for window size changes.
				        Dimension longField = new Dimension(300, 20);
				        invoiceNumberField.setPreferredSize(longField);
				        invoiceNumberField.setMinimumSize(longField);
				        dateField.setPreferredSize(longField);
				        dateField.setMinimumSize(longField);
				        timeField.setPreferredSize(longField);
				        timeField.setMinimumSize(longField);
				        customerIDField.setPreferredSize(longField);
				        customerIDField.setMinimumSize(longField);
				        employeeIDField.setPreferredSize(longField);
				        employeeIDField.setMinimumSize(longField);
				        
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
				        JPanel salesPanel = new JPanel();
				        salesPanel.setLayout(new GridBagLayout());
				        salesPanel.add(new JLabel("Invoice Number:"), getConstraints(0, 0, GridBagConstraints.LINE_END));
				        salesPanel.add(invoiceNumberField, getConstraints(1, 0, GridBagConstraints.LINE_START));
				        salesPanel.add(new JLabel("Date:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
				        salesPanel.add(dateField, getConstraints(1, 1, GridBagConstraints.LINE_START));
				        salesPanel.add(new JLabel("Time:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
				        salesPanel.add(timeField, getConstraints(1, 2, GridBagConstraints.LINE_START));
				        salesPanel.add(new JLabel("Customer ID:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
				        salesPanel.add(customerIDField, getConstraints(1, 3, GridBagConstraints.LINE_START));
				        salesPanel.add(new JLabel("Employee ID:"), getConstraints(0, 4, GridBagConstraints.LINE_END));
				        salesPanel.add(employeeIDField, getConstraints(1, 4, GridBagConstraints.LINE_START));
				        
				        JPanel buttonPanel = new JPanel();
				        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				        buttonPanel.add(confirmButton);
				        buttonPanel.add(cancelButton);
				        
				        setLayout(new BorderLayout());
				        add(salesPanel, BorderLayout.CENTER);
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
				    	
				    	//verifies fields not empty.
				    	String date = verifyEntry(dateField);
				    	String time = verifyEntry(timeField);
				    	String customerID = verifyEntry(customerIDField);
				    	String employeeID = verifyEntry(employeeIDField);
				    	
				    	
				    	System.out.println("In SalesForm - processData");
				    	System.out.println("dataEntered: " + dataEntered);
				    	if(dataEntered) {
				    		
				    		// check that both customer and employee ID's exist
				    		boolean validCustomer = false;
				    		boolean validEmployee = false;
				    						    		
				    		validCustomer = writerDAO.checkCustomerExists(customerID);
				    		validEmployee = writerDAO.checkEmployeeExists(employeeID);
				    		

				    		if(!validCustomer)
				    			JOptionPane.showMessageDialog(this, "Invalid Customer ID Entered.  Please enter a valid customer ID.",
					                    "Invalid Customer ID", JOptionPane.INFORMATION_MESSAGE);
				    		
				    		if(!validEmployee)
				    			JOptionPane.showMessageDialog(this, "Invalid Employee ID Entered.  Please enter a valid employee ID.",
					                    "Invalid Employee ID", JOptionPane.INFORMATION_MESSAGE);
				    		
				    		if(validCustomer && validEmployee) {
				    			writerDAO.createInvoice(date, time, customerID, employeeID);
				    			
				    			// Notify user that addition was successful
				    			dispose();
				    		}
				    		else {
				    			
				    			//Notify user that was not successful
				    			System.out.println("Invoice not created");
				    			System.out.println("customer: " + validCustomer + 
				    					" ; Employee: " + validEmployee);
				    			
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
				    	
				    		
				    	if(dataItem.length() == 0) {
							name.setForeground(Color.RED);
							name.setText("Data Missing");
							dataEntered = false;
				    	}
				    	else if(dataItem.equals("Data Missing")) {
				    		dataEntered = false;
				    	}
					
				    	return dataItem;
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


	



