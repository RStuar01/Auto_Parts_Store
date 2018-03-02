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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import BusinessLayer.InvoiceLineItem;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.DatabaseWriter;
import DatabaseLayer.ReaderDAO;
import DatabaseLayer.WriterDAO;


/**
 * Class extends JDialog for sales items entry
 * Written by Michael Meesseman
 */
public class SalesItemForm extends JDialog{
	
				
	// text field initialization
			    private JTextField invoiceLineItemNumberField;
			    private JTextField invoiceNumberField;
			    private JTextField purchasedQtyField;
			    private JTextField productIDField;
			    private JButton confirmButton;
			    private JButton cancelButton;
			    
			    //Added by Rick
			    private String invoiceNumber = "";
			    private String date = "";
			    private String time = "";
			    private String customerID = "";
			    private String employeeID = "";
			    private boolean dataEntered = true;
			    private static WriterDAO writerDAO;
			    private ReaderDAO readerDAO;
			    
			    private InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
			    
			    /**
			     * Constructor to build dialog box for data entry for new add
			     * @param parent	this is the frame that called the form
			     * @param title		title of the form
			     * @param modal		boolean to block all other input on other windows until current one is closed.
			     * @param invoiceNumberInput  brings in the invoice number of the selected invoice.
			     * Written by Michael Meesseman
			     */
			    public SalesItemForm(java.awt.Frame parent, String title, boolean modal, String invoiceNumberInput) {
			        super(parent, title, modal);
			        invoiceNumber = invoiceNumberInput;
			        //this.date = date;
			        //this.time = time;
			        //this.customerID = customerID;
			        //this.employeeID = employeeID;
			        initComponents(invoiceNumberInput);
			        
			     // Added by Rick
			        writerDAO = DAOFactory.getWriterDAO();
			        readerDAO = DAOFactory.getReaderDAO();
			    }
			    
			    /**
			     * Constructor to build dialog box for data entry for edit
			     * @param parent	this is the frame that called the form
			     * @param title		title of the form
			     * @param modal		boolean to block all other input on other windows until current one is closed.
			     * @param invoiceLineItem	InvoiceLineItem object to fill fields for edit.
			     * Written by Michael Meesseman
			     */
			    public SalesItemForm(java.awt.Frame parent, String title, boolean modal, InvoiceLineItem invoiceLineItem) {
			        this(parent, title, modal, title);
			        this.invoiceLineItem = invoiceLineItem;
			        confirmButton.setText("Save");
			        invoiceLineItemNumberField.setText(invoiceLineItem.getInvoiceLineNumber());
			        invoiceNumberField.setText(invoiceLineItem.getInvoiceNumber());
			        purchasedQtyField.setText(invoiceLineItem.getQuantityPurchased());
			        productIDField.setText(invoiceLineItem.getProductID());
			        
			        //fields cannot be edited. 
			        invoiceLineItemNumberField.setEditable(false);
			        invoiceNumberField.setEditable(false);
			        
			        }
			    
			    /**
			     * Method to initialize all components.
			     * Written by Michael Meesseman
			     */
			    private void initComponents(String invoiceNumberInput) {
			    	
			    	//focus listeners to remove red text after validation
			    	invoiceLineItemNumberField = new JTextField();
			    	invoiceLineItemNumberField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(invoiceLineItemNumberField);
						}
					});
			        invoiceNumberField = new JTextField();
			        invoiceNumberField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(invoiceNumberField);
						}
					});
			        invoiceNumberField.setText(invoiceNumberInput);
			        purchasedQtyField = new JTextField();
			        purchasedQtyField.setName("Purchased Quantity");
			        purchasedQtyField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(purchasedQtyField);
						}
					});
			        productIDField = new JTextField();
			        productIDField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(productIDField);
						}
					});
			        cancelButton = new JButton();
			        confirmButton = new JButton();
			        
			        //fields cannot be edited. 
			        invoiceLineItemNumberField.setEditable(false);
			        invoiceNumberField.setEditable(false);
			        
			        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			       
			        // sets field size for window size changes.
			        Dimension longField = new Dimension(300, 20);
			        invoiceLineItemNumberField.setPreferredSize(longField);
			        invoiceLineItemNumberField.setMinimumSize(longField);
			        invoiceNumberField.setPreferredSize(longField);
			        invoiceNumberField.setMinimumSize(longField);
			        purchasedQtyField.setPreferredSize(longField);
			        purchasedQtyField.setMinimumSize(longField);
			        productIDField.setPreferredSize(longField);
			        productIDField.setMinimumSize(longField);
			        
			        
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
			        JPanel salesItemPanel = new JPanel();
			        salesItemPanel.setLayout(new GridBagLayout());
			        salesItemPanel.add(new JLabel("Invoice Line Item Number:"), getConstraints(0, 0, GridBagConstraints.LINE_END));
			        salesItemPanel.add(invoiceLineItemNumberField, getConstraints(1, 0, GridBagConstraints.LINE_START));
			        salesItemPanel.add(new JLabel("Invoice Number:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
			        salesItemPanel.add(invoiceNumberField, getConstraints(1, 1, GridBagConstraints.LINE_START));
			        salesItemPanel.add(new JLabel("Purchased Quantity:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
			        salesItemPanel.add(purchasedQtyField, getConstraints(1, 2, GridBagConstraints.LINE_START));
			        salesItemPanel.add(new JLabel("Product ID:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
			        salesItemPanel.add(productIDField, getConstraints(1, 3, GridBagConstraints.LINE_START));
			        
			        JPanel buttonPanel = new JPanel();
			        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			        buttonPanel.add(confirmButton);
			        buttonPanel.add(cancelButton);
			        
			        setLayout(new BorderLayout());
			        add(salesItemPanel, BorderLayout.CENTER);
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
			    
			    //Added by Rick
			    /**
			     * Method processes data for the add and save buttons.  
				 * Method also validates data before adding to the database
			     * Written by Rick Stuart
			     */
			    private void processData() {
			    	
			    	//verifies fields not empty
			    	String purchasedQuantity = verifyEntry(purchasedQtyField);
			    	String productID = verifyEntry(productIDField);
			    	

			    	//verifies qty is in stock before sale.
			    	String qtyInStockString = readerDAO.getQtyInStock(Integer.parseInt(productIDField.getText()));
			    	Integer qtyInStock = Integer.parseInt(qtyInStockString);
			    	
			    	boolean inStock = false;
			    	
			    	if (Integer.parseInt(purchasedQtyField.getText()) <= qtyInStock && ValidateInteger.validateInteger(purchasedQtyField, this))
			    		inStock = true;
			    	else
			    	{
			    		inStock = false;
			    		JOptionPane.showMessageDialog(this, "Quantity Listed for that Item is not in Stock.");
			    	}
			    	
			    	
			    	if(dataEntered  && ValidateInteger.validateInteger(purchasedQtyField, this) && inStock) {

			    	System.out.println("In SalesItemForm - processData");
			    	System.out.println("dataEntered: " + dataEntered);
			    	writerDAO.manageEnteringToAccountingSales(invoiceNumber, purchasedQuantity, productID);
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
