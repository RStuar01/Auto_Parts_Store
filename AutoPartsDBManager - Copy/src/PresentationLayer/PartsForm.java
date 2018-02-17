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
import BusinessLayer.Product;
import DatabaseLayer.DatabaseWriter;

public class PartsForm extends JDialog {
			   
		    private static final String EMAIL_REGEX = 
		    "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
		        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		    private static final Pattern EMAIL_PATTERN = 
		                             Pattern.compile(EMAIL_REGEX);
		    
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
		    
		    private Product product = new Product();
		    
		    public PartsForm(java.awt.Frame parent, String title, boolean modal) {
		        super(parent, title, modal);
		        initComponents();
		    }
		    
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
			    productIDField.setEditable(false);
		        }
		    
		    private void initComponents() {
		    	
		    	productIDField = new JTextField();
			    descriptionField = new JTextField();
			    minYearField = new JTextField();
			    maxYearField = new JTextField();
			    makeField = new JTextField();
			    modelField = new JTextField();
			    supplierPriceField = new JTextField();
			    sellPriceField = new JTextField();
			    coreChargeField = new JTextField();
			    compatibilityNumberField = new JTextField();
			    companyIDField = new JTextField();
			    minStockQtyField = new JTextField();
			    maxStockQtyField = new JTextField();
			    warehouseLocationField = new JTextField();
			    qtyInStockField = new JTextField();
			    cancelButton = new JButton();
		        confirmButton = new JButton();
		        
		        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		       
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
		    */
		    
		    private boolean validateData() {
		        
		        boolean valid = false;
		        //String email = emailField.getText();
		        
		        if (confirmButton.getText().equals("Add")) 
		        {
		          //  if(isEmpty())
		            //    if(emailValidator(email)) 
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
		    	
		    	String productID = productIDField.getText();
		        String description = descriptionField.getText();
		        String minYear = minYearField.getText();
		        String maxYear = maxYearField.getText();
		        String make = makeField.getText();
		        String model = modelField.getText();
		        String supplierPrice = supplierPriceField.getText();
		        String sellPrice = sellPriceField.getText();
		        String coreCharge = coreChargeField.getText();
		        String compatibilityNumber = compatibilityNumberField.getText();
		        String companyID = companyIDField.getText();
		        String minStockQty = minStockQtyField.getText();
		        String maxStockQty = maxStockQtyField.getText();
		        String warehouseLocation = warehouseLocationField.getText();
		        String qtyInStock = qtyInStockField.getText();
		        
		        product.setProductID(productID);
		        product.setDescription(description);
		        product.setYearMinimum(minYear);
		        product.setYearMaximum(maxYear);
		        product.setMake(make);
		        product.setModel(model);
		        product.setSupplierPrice(supplierPrice);
		        product.setSellPrice(sellPrice);
		        product.setCoreCharge(coreCharge);
		        product.setCompatibilityNumber(compatibilityNumber);
		        product.setCompanyID(companyID);
		        product.setMinQuantityInStock(minStockQty);
		        product.setMaxQuantityInStock(maxStockQty);
		        product.setWarehouseLocation(warehouseLocation);
		        product.setQuantityInStock(qtyInStock);

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
		        PartsFrame mainWindow = (PartsFrame) getOwner();
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
		    //        emailField.grabFocus();
		            return false;
		        
		        }
		    }
		
		   


	


}
