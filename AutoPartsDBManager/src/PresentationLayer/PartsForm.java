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

import BusinessLayer.Product;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.WriterDAO;

/**
 * Class extends JDialog for parts data entry
 * Written by Michael Meesseman
 */
public class PartsForm extends JDialog {
			   
			// text field initialization
		    private JTextField productIDField;
		    private JTextField descriptionField;
		    private JTextField minYearField;
		    private JTextField maxYearField;
		    private JTextField makeField;
		    private JTextField modelField;
		    private JTextField supplierPriceField;
		    private JTextField sellPriceField;
		    private JTextField coreChargeField;
		    private JTextField compatibilityNumberField;
		    private JTextField companyIDField;
		    private JTextField minStockQtyField;
		    private JTextField maxStockQtyField;
		    private JTextField warehouseLocationField;
		    private JTextField qtyInStockField;
		    private JButton confirmButton;
		    private JButton cancelButton;
		    
		  //Added by Rick
		    private boolean dataEntered = true;
		    private static WriterDAO writerDAO;
		    
		    private Product product = new Product();
		    
		    /**
		     * Constructor to build dialog box for data entry for new add
		     * @param parent	this is the frame that called the form
		     * @param title		title of the form
		     * @param modal		boolean to block all other input on other windows until current one is closed.
		     * Written by Michael Meesseman
		     */
		    public PartsForm(java.awt.Frame parent, String title, boolean modal) {
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
		     * @param product	Product object to fill fields for edit.
		     * Written by Michael Meesseman
		     */
		    public PartsForm(java.awt.Frame parent, String title, boolean modal, Product product) {
		        this(parent, title, modal);
		        this.product = product;
		        confirmButton.setText("Save");
		        
		        productIDField.setText(product.getProductID());
			    descriptionField.setText(product.getDescription());
			    minYearField.setText(product.getYearMinimum());
			    maxYearField.setText(product.getYearMaximum());
			    makeField.setText(product.getMake());
			    modelField.setText(product.getModel());
			    supplierPriceField.setText(product.getSupplierPrice());
			    sellPriceField.setText(product.getSellPrice());
			    coreChargeField.setText(product.getCoreCharge());
			    compatibilityNumberField.setText(product.getCompatibilityNumber());
			    companyIDField.setText(product.getCompanyID());
			    minStockQtyField.setText(product.getMinQuantityInStock());
			    maxStockQtyField.setText(product.getMaxQuantityInStock());
			    warehouseLocationField.setText(product.getWarehouseLocation());
			    qtyInStockField.setText(product.getQuantityInStock());
			    
			    //fields cannot be edited. 
			    productIDField.setEditable(false);
		        }
		    
		    /**
		     * Method to initialize all components.
		     * Written by Michael Meesseman
		     */
		    private void initComponents() {
		    	
		    	//focus listeners to remove red text after validation
		    	productIDField = new JTextField();
		    	productIDField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(productIDField);
					}
				});
			    descriptionField = new JTextField();
			    descriptionField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(descriptionField);
					}
				});
			    minYearField = new JTextField();
			    minYearField.setName("Minimum Year");
			    minYearField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(minYearField);
					}
				});
			    maxYearField = new JTextField();
			    maxYearField.setName("Maximum Year");
			    maxYearField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField( maxYearField);
					}
				});
			    makeField = new JTextField();
			    makeField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(makeField);
					}
				});
			    modelField = new JTextField();
			    modelField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(modelField);
					}
				});
			    supplierPriceField = new JTextField();
			    supplierPriceField.setName("Supplier Price");
			    supplierPriceField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(supplierPriceField);
					}
				});
			    sellPriceField = new JTextField();
			    sellPriceField.setName("Sell Price");
			    sellPriceField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(sellPriceField);
					}
				});
			    coreChargeField = new JTextField();
			    coreChargeField.setName("Core Charge");
			    coreChargeField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(coreChargeField);
					}
				});
			    compatibilityNumberField = new JTextField();
			    compatibilityNumberField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(compatibilityNumberField);
					}
				});
			    companyIDField = new JTextField();
			    companyIDField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(companyIDField);
					}
				});
			    minStockQtyField = new JTextField();
			    minStockQtyField.setName("Minimum Quantity Stock");
			    minStockQtyField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(minStockQtyField);
					}
				});
			    maxStockQtyField = new JTextField();
			    maxStockQtyField.setName("Maximum Quantity Stock");
			    maxStockQtyField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(maxStockQtyField);
					}
				});
			    warehouseLocationField = new JTextField();
			    warehouseLocationField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(warehouseLocationField);
					}
				});
			    qtyInStockField = new JTextField();
			    qtyInStockField.setName("Quantity in Stock");
			    qtyInStockField.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						checkField(qtyInStockField);
					}
				});
			    cancelButton = new JButton();
		        confirmButton = new JButton();
		        
		        //fields cannot be edited.
		        productIDField.setEditable(false);
		        
		        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		       
		     // sets field size for window size changes.
		        Dimension longField = new Dimension(300, 20);
		    
		        productIDField.setPreferredSize(longField);
		        productIDField.setMinimumSize(longField);
		        descriptionField.setPreferredSize(longField);
		        descriptionField.setMinimumSize(longField);
		        minYearField.setPreferredSize(longField);
		        minYearField.setMinimumSize(longField);
		        maxYearField.setPreferredSize(longField);
		        maxYearField.setMinimumSize(longField);
		        makeField.setPreferredSize(longField);
		        makeField.setMinimumSize(longField);
		        modelField.setPreferredSize(longField);
		        modelField.setMinimumSize(longField);
		        supplierPriceField.setPreferredSize(longField);
		        supplierPriceField.setMinimumSize(longField);
		        sellPriceField.setPreferredSize(longField);
		        sellPriceField.setMinimumSize(longField);
		        coreChargeField.setPreferredSize(longField);
		        coreChargeField.setMinimumSize(longField);
		        compatibilityNumberField.setPreferredSize(longField);
		        compatibilityNumberField.setMinimumSize(longField);
		        companyIDField.setPreferredSize(longField);
		        companyIDField.setMinimumSize(longField);
		        minStockQtyField.setPreferredSize(longField);
		        minStockQtyField.setMinimumSize(longField);
		        maxStockQtyField.setPreferredSize(longField);
		        maxStockQtyField.setMinimumSize(longField);
		        warehouseLocationField.setPreferredSize(longField);
		        warehouseLocationField.setMinimumSize(longField);
		        qtyInStockField.setPreferredSize(longField);
		        qtyInStockField.setMinimumSize(longField);
		        
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
		        JPanel partsPanel = new JPanel();
		        partsPanel.setLayout(new GridBagLayout());
		        partsPanel.add(new JLabel("Product ID"), getConstraints(0, 0, GridBagConstraints.LINE_END));
		        partsPanel.add(productIDField, getConstraints(1, 0, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Description:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
		        partsPanel.add(descriptionField, getConstraints(1, 1, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Minimum Year:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
		        partsPanel.add(minYearField, getConstraints(1, 2, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Maximum Year:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
		        partsPanel.add(maxYearField, getConstraints(1, 3, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Make:"), getConstraints(0, 4, GridBagConstraints.LINE_END));
		        partsPanel.add(makeField, getConstraints(1, 4, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Model:"), getConstraints(0, 5, GridBagConstraints.LINE_END));
		        partsPanel.add(modelField, getConstraints(1, 5, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Supplier Price:"), getConstraints(0, 6, GridBagConstraints.LINE_END));
		        partsPanel.add(supplierPriceField, getConstraints(1, 6, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Sell Price:"), getConstraints(0, 7, GridBagConstraints.LINE_END));
		        partsPanel.add(sellPriceField, getConstraints(1, 7, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Core Charge:"), getConstraints(0, 8, GridBagConstraints.LINE_END));
		        partsPanel.add(coreChargeField, getConstraints(1, 8, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Compatibility Number:"), getConstraints(0, 9, GridBagConstraints.LINE_END));
		        partsPanel.add(compatibilityNumberField, getConstraints(1, 9, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Company ID:"), getConstraints(0, 10, GridBagConstraints.LINE_END));
		        partsPanel.add(companyIDField, getConstraints(1, 10, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Minimum Stock Quantity:"), getConstraints(0, 11, GridBagConstraints.LINE_END));
		        partsPanel.add(minStockQtyField, getConstraints(1, 11, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Maximum Stock Quantity:"), getConstraints(0, 12, GridBagConstraints.LINE_END));
		        partsPanel.add(maxStockQtyField, getConstraints(1, 12, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Warehouse Location:"), getConstraints(0, 13, GridBagConstraints.LINE_END));
		        partsPanel.add(warehouseLocationField, getConstraints(1, 13, GridBagConstraints.LINE_START));
		        partsPanel.add(new JLabel("Quantity In Stock:"), getConstraints(0, 14, GridBagConstraints.LINE_END));
		        partsPanel.add(qtyInStockField, getConstraints(1, 14, GridBagConstraints.LINE_START));
		        
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		        buttonPanel.add(confirmButton);
		        buttonPanel.add(cancelButton);
		        
		        setLayout(new BorderLayout());
		        add(partsPanel, BorderLayout.CENTER);
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
		    	
		    	//verifies fields not empty.
		    	String description = verifyEntry(descriptionField);
		    	String minYear = verifyEntry(minYearField);
		    	String maxYear = verifyEntry(maxYearField);
		    	String make = verifyEntry(makeField);
		    	String model = verifyEntry(modelField);
		    	String supplierPrice = verifyEntry(supplierPriceField);
		    	String sellPrice = verifyEntry(sellPriceField);
		    	String coreCharge = verifyEntry(coreChargeField);
		    	String compatibilityNumber = verifyEntry(compatibilityNumberField);
		    	String companyID = verifyEntry(companyIDField);
		    	String minStockQuantity = verifyEntry(minStockQtyField);
		    	String maxStockQuantity = verifyEntry(maxStockQtyField);
		    	String warehouseLocation = verifyEntry(warehouseLocationField);
		    	String quantityInStock = verifyEntry(qtyInStockField);
		    	
		    	boolean qtyRange = false;
		    	boolean yearRange= false;
		    	
		    	//validates min is less than or equal to max.
		    	try {
		    	Integer minYearInt = Integer.parseInt(minYear);
		    	Integer maxYearInt = Integer.parseInt(maxYear);
		    	yearRange = minYearInt <= maxYearInt;
		    	if (!yearRange)
		    		JOptionPane.showMessageDialog(this, "Minimum Year is not less than or equal to Maximum Year.", 
		     	                    "Year Mismatch", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    	catch (NumberFormatException ex)
		    	{
		    		
		    	}

		    	
		    	//validates min is less than or equal to max.
		    	try {
		    	Integer minStockQtyInt = Integer.parseInt(minStockQuantity);
		    	Integer maxStockQtyInt = Integer.parseInt(maxStockQuantity);
		    	
		    	qtyRange = minStockQtyInt <= maxStockQtyInt;
		    	
		    	if (!qtyRange)
		    		JOptionPane.showMessageDialog(this, "Minimum Stock Quantity is not less "
		    				+ "than or equal to Maximum Stock Quantity.", 
		     	                    "Year Mismatch", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    	catch (NumberFormatException ex)
		    	{
		    		
		    	}
		    	boolean valid = false;
	   	   		
	    		
	    		valid = writerDAO.checkCompanyExists(companyID);
	    		if(!valid)
	    		{
	    			System.out.println("Company does not exist");
	    			JOptionPane.showMessageDialog(this, "Company ID does not exist.", 
		     	                    "Invalid Company ID", JOptionPane.INFORMATION_MESSAGE);
	    		}
	    		
		    	
		    	if(dataEntered  && ValidateInteger.validateInteger(minYearField, this) 
		    			&& ValidateInteger.validateInteger(maxYearField, this) && 
		    			ValidateDouble.validateDouble(supplierPriceField, this) &&
		    			ValidateDouble.validateDouble(sellPriceField, this) && 
		    			ValidateDouble.validateDouble(coreChargeField, this) && 
		    			ValidateInteger.validateInteger(minStockQtyField, this) &&
		    			ValidateInteger.validateInteger(maxStockQtyField, this) &&
		    			ValidateInteger.validateInteger(qtyInStockField, this) 
		    			&& yearRange && qtyRange && valid) {
		    		
		    			if (confirmButton.getText().equals("Add")) {
		    				writerDAO.manageEnteringNewProduct(description, minYear, maxYear,
		    						make, model, supplierPrice, sellPrice, coreCharge,
		    						compatibilityNumber, companyID, minStockQuantity,
		    						maxStockQuantity, warehouseLocation, quantityInStock);
		    			dispose();
		    			}
		    			else {
		    				//updates during edit.
		    				String productID = productIDField.getText();
		    				writerDAO.editProduct(productID, description, minYear, maxYear,
		    						make, model, supplierPrice, sellPrice, coreCharge,
		    						compatibilityNumber, companyID, minStockQuantity,
		    						maxStockQuantity, warehouseLocation, quantityInStock);
		    				dispose();
		    			}
		    			
		    			
		    			//Notify user that add was successful
		    			dispose();
		    		}
		    		
		    			
		    			//Notify user that add was NOT successful
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
		    	
		    	//dataItem = name.getText();
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
