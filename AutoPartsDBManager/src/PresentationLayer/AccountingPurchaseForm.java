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
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import BusinessLayer.AccountingPurchases;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;
import DatabaseLayer.WriterDAO;


/**
 * Class extends JDialog for data entry
 * Written by Michael Meesseman
 */
public class AccountingPurchaseForm extends JDialog{
	 
		    // initialize fields and buttons		    
		    private JTextField purchaseIDField;
		    private JTextField productIDField;
		    private JTextField purchaseQtyField;
		    private JTextField dollarValueField;
		    private JButton confirmButton;
		    private JButton cancelButton;
		    
		  //Added by Rick
		    private boolean dataEntered = true;
		    private WriterDAO writerDAO;
		    private ReaderDAO readerDAO;
		   
		    private AccountingPurchases purchase = new AccountingPurchases();
		    
		    
		    /**
		     * Constructor to build dialog box for data entry for new add
		     * @param parent	this is the frame that called the form
		     * @param title		title of the form
		     * @param modal		boolean to block all other input on other windows until current one is closed.
		     * Written by Michael Meesseman
		     */
		    public AccountingPurchaseForm(java.awt.Frame parent, String title, boolean modal) {
		        super(parent, title, modal);
		        
		        //initializes all componets of the form.
		        initComponents();
		        
		        //Added by Rick
		        writerDAO = DAOFactory.getWriterDAO();
		        readerDAO = DAOFactory.getReaderDAO();
		    }
		    
		    /**
		     * Constructor to build dialog box for edits.
			 * @param parent	this is the frame that called the form
		     * @param title		title of the form
		     * @param modal		boolean to block all other input on other windows until current one is closed.
		     * @param purchase	brings in the object of the selected Accounting Purchase
		     * Written by Michael Meesseman
		     */
		    public AccountingPurchaseForm(java.awt.Frame parent, String title, boolean modal, AccountingPurchases purchase) {
		        this(parent, title, modal);
		        this.purchase = purchase;
		        confirmButton.setText("Save");
		        purchaseIDField.setText(purchase.getAccountingPurchasesRecordID());
		        productIDField.setText(purchase.getProductID());
		        purchaseQtyField.setText(purchase.getPurchasesQuantity());
		        dollarValueField.setText(purchase.getDollarValue());
		        
		        //fields cannot be edited.
		        purchaseIDField.setEditable(false);
		        dollarValueField.setEditable(false);
		     }
		    
		    /**
		     * Method to initialize all components.
		     * Written by Michael Meesseman
		     */
		    private void initComponents() {
		    	purchaseIDField = new JTextField();
		        productIDField = new JTextField();
		        purchaseQtyField = new JTextField();
		        dollarValueField = new JTextField();
		        cancelButton = new JButton();
		        confirmButton = new JButton();
		        
		        // fields cannot be edited.
		        purchaseIDField.setEditable(false);
		        dollarValueField.setEditable(false);
		        
		        // sets name for telling user which field is wrong during validation. 
		        purchaseQtyField.setName("Purchase Quantity");
		        dollarValueField.setName("Dollar Value");
		        
		        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		       
		        // sets field lengths so window size can be adjusted without changing field size. 
		        Dimension longField = new Dimension(300, 20);
		        purchaseIDField.setPreferredSize(longField);
		        purchaseIDField.setMinimumSize(longField);
		        productIDField.setPreferredSize(longField);
		        productIDField.setMinimumSize(longField);
		        purchaseQtyField.setPreferredSize(longField);
		        purchaseQtyField.setMinimumSize(longField);
		        dollarValueField.setPreferredSize(longField);
		        dollarValueField.setMinimumSize(longField);
		        
		        // cancel button
		        cancelButton.setText("Cancel");
		        cancelButton.addActionListener((ActionEvent) -> {
		            cancelButtonActionPerformed();
		        });
		        
		        // add button
		        confirmButton.setText("Add");
		        confirmButton.addActionListener((ActionEvent) -> {
		            try {
						confirmButtonActionPerformed();
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        });
		        
		        // focus listeners to reset error fields.
		        purchaseIDField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(purchaseIDField);
					}
				});
			
		        productIDField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(productIDField);
					}
				});
		        
		        purchaseQtyField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(purchaseQtyField);
					}
				});
		        
		        dollarValueField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(dollarValueField);
					}
				});
		        
		       
		        
		        // builds grid of labels and text fields for data entry.      
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
		        
		        // builds panel
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		        buttonPanel.add(confirmButton);
		        buttonPanel.add(cancelButton);
		        
		        setLayout(new BorderLayout());
		        add(purchasePanel, BorderLayout.CENTER);
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
		    	String productID = verifyEntry(productIDField);
		    	String quantityPurchased = verifyEntry(purchaseQtyField);
		    	
		    	//Moved outside of if statement by Michael Meesseman
		    	//Needs to be outside so the dialog box does not close before user corrects data errors.
		    	//Check that product exists
		    	boolean valid = writerDAO.checkProductExists(productID);
		    	
		    	if(!valid)
		    	{
		    		System.out.println("Product does not exist");
	    			
	    			//Notify user that add was NOT successful
	    			JOptionPane.showMessageDialog(this, "Invalid Product ID Entered.",
		                    "This Product ID does not exist!", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    		
		    	
		    	//Validated number fields are integers, product ID exists and that fields are not empty.
		    	if(dataEntered && ValidateInteger.validateInteger(purchaseQtyField, this) && valid) {
		    		
		    			// calculates total price of purchase
		    			double supplierPrice = Double.parseDouble(readerDAO.obtainSupplierPrice(productIDField.getText()));
				    	int qty = Integer.parseInt(quantityPurchased);
				    	double total = supplierPrice * qty;
				    	
				    	// formats total to 2 decimal places.
				    	DecimalFormat format = new DecimalFormat(".##");
				    	String totalString = format.format(total);
				    	
				    	dollarValueField.setText(totalString);
		    			
		    			// adds fields to database. 
		    			writerDAO.manuallyEnterNewAccountingPurchase(productID, 
		    				quantityPurchased, dollarValueField.getText());
		    			dispose();
		    			
		    			//Notify user that add was successful
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
