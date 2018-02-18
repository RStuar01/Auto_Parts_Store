 package PresentationLayer;

import java.awt.EventQueue;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.sql.SQLException;

>>>>>>> 700d98a6ee505bed56a8aa70635f8c6d17aa57da
import javax.swing.UnsupportedLookAndFeelException;


import DatabaseLayer.*;
import BusinessLayer.*;

/**
 * Class Name:					MainAutoPartsGUI
 * Description:					This class contains all the code and methods necessary to run
 * 								the main graphic user interface.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
public class AutoPartsStoreGui {

	// Variable declaration area for GUI components - List things like the buttons, text boxes, etc.
	
	// Data access object variables  (these were changed to static)
	// They may need to be changed back later
	private static WriterDAO writerDAO;
	private static ReaderDAO readerDAO;
	private static DeleterDAO deleterDAO;
	private static RFIDDAO rfidDAO;
	
	private static String choice = "";
	
	// Other local variables
	private static ArrayList<Product> compatibleProducts;
	private static ArrayList<Invoice> invoices;
	private static ArrayList<InvoiceLineItem> lineItems;
	private static ArrayList<Product> rfidProducts;
	
	/**
     * @param args the command line arguments
	 * @throws SQLException 
     */
    public static void main(String[] args) throws DBException, UnsupportedLookAndFeelException, SQLException {
        
    
    		
    		LoginFrame loginFrame = new LoginFrame();
    	
    
    	
    	
	
		
    	
    	// TODO Auto-generated method stub
		initialize();
		writerDAO = DAOFactory.getWriterDAO();
		readerDAO = DAOFactory.getReaderDAO();
		deleterDAO = DAOFactory.getDeleterDAO();
		rfidDAO = DAOFactory.getRFIDDAO();
		
		// read any incoming products
				System.out.println("Reading new products");
				rfidProducts = rfidDAO.ProductTextFile();
				
				if(rfidProducts != null) {
					System.out.println("Products from the arraylist");
					for(Product p: rfidProducts) {
						System.out.println(p.toString());
					}
					
					// this should return a string for accepted/rejected products
					writerDAO.writeIncomingProducts(rfidProducts);
				}
				else {
					System.out.println("Products is null");
				}
		
		// Temporarily call manageNewPersonCreation() from here
		choice = "Customer";
		String lastName = "TestLast";
		String firstName = "TestFirst";
		String stAddress = "TestAdd";
		String city = "TestCity";
		String state = "TestState";
		String zipCode = "TestZipcode";
		String unitNumber = "TestUnit";
		String phoneNumber = "TestPhone";
		String cellPhone = "TestCell";
		String emailAddress = "TestEmail";
		String companyID = "0";
		
		// Write a Customer record - WORKS
		/*
		writerDAO.manageNewPersonCreation(choice, lastName, firstName, stAddress, city, state,
				zipCode, unitNumber, phoneNumber, cellPhone, emailAddress, companyID);
		*/
		
		// Change choice to write an Employee record - same address and contact_info used
		// WORKS
		/*
		choice = "Employee";
		writerDAO.manageNewPersonCreation(choice, lastName, firstName, stAddress, city, state,
				zipCode, unitNumber, phoneNumber, cellPhone, emailAddress, companyID);
		*/
		
		// Need to create a company before the supplier
		// WORKS
		/*
		choice = "Company";
		String companyName = "TestCompany";
		writerDAO.createNewCompany(stAddress, city, state, zipCode, unitNumber,
				phoneNumber, cellPhone, emailAddress, companyName);
		*/
		
		// Change choice to write a Supplier record - same address and contact info used
		// WORKS - MUST SET company_id in DatabaseWriter.manageNewPersonCreation
		// in the last else if statement - need to write a method to get this from the gui
		/*
		choice = "Supplier";
		writerDAO.manageNewPersonCreation(choice, lastName, firstName, stAddress, city,
				state, zipCode, unitNumber, phoneNumber, cellPhone, emailAddress, companyID);
		*/
		
		// manually enter a product and update accounting_purchases
		// WORKS
		/*
		choice = "Product";	// this not needed here
		companyID = "8";	// This MUST match an entry in the DB
		String description = "TestDescription";
		String yearMin = "TestYear";
		String yearMax = "TestYear";
		String make = "TestMake";
		String model = "TestModel";
		String supplyPrice = "19.95";
		String sellPrice = "49.95";
		String coreCharge = "10";
		String compatNum = "001";
		String minStockQuantity = "30";
		String maxStockQuantity = "50";
		String location = "TestLocation";
		String quantityInStock = "50";
		
		writerDAO.manageEnteringNewProduct(description, yearMin, yearMax, make, model, supplyPrice, 
				sellPrice, coreCharge, compatNum, companyID, minStockQuantity,
				maxStockQuantity, location, quantityInStock);
		*/
		
		// Manage a Sale - NEED TO CALL FOR EACH LINE ITEM
		// maybe create invoice and each line item here.
		// or pass each line item to manageSale and add it there.
		// WORKS
		
		String date = "2018-02-03";
		String time = "20:00";
		String customerID = "1";
		String employeeID = "2";
		String quantityPurchased = "30";
		String productID = "6";
		
		// check to see if reorder is necessary from this method
		writerDAO.manageSale(date, time, customerID, employeeID, quantityPurchased, 
				productID);
		
		
		// AREA TO READ INFORMATION FROM DB**************************
		
		// Read and create a customer object
		// WORKS
		/*
		lastName = "Wayne";
		firstName = "Bruce";
		phoneNumber = "111-222-1234";
		
		Customer c = null;
		
		c = readerDAO.obtainCustomerInformation(lastName, firstName, phoneNumber);
		People p = (People) c;
		emailAddress = "";
		emailAddress = p.getEmailAddress();
		System.out.println(emailAddress);
		*/
		
		// Read and create an Employee object
		// WORKS
		/*
		lastName = "Quin";
		firstName = "Harley";
		phoneNumber = "111-223-4567";
		
		Employee e = null;
		
		e = readerDAO.obtainEmployeeInformation(lastName, firstName, phoneNumber);
		People p = (People) e;
		emailAddress = p.getEmailAddress();
		System.out.println(emailAddress);
		*/
		
		// Read and create a Supplier object
		lastName = "CobblePot";
		firstName = "Oswald";
		phoneNumber = "111-224-7890";
		
		Supplier supplier = null;
		
		//supplier = readerDAO.obtainSupplierInformation(lastName, firstName, phoneNumber);
		//People q = (People) supplier;
		//emailAddress = q.getEmailAddress();
		//System.out.println(emailAddress);
		
		//******************* NEW methods - 2/10/18*********************
		// Read and create a Company object
		//WORKS
				/*
				System.out.println("About to read a Company");
				String companyName = "Gotham Iron Works Ltd.";
				Company company = null;
				
				company = readerDAO.obtainCompanyInformation(companyName);
				emailAddress = company.getEmailAddress();
				System.out.println("Email Address: " + emailAddress);
				*/
				
				// lookup a product
				// WORKS
				/*
				String description = "starter";
				String year = "2013";
				String make = "Toyota";
				String model = "Corolla";
				Product currentProduct = null;
				
				currentProduct = readerDAO.lookupProduct(description, year, make, model);
				System.out.println("Product: " + currentProduct.toString());
				*/
				
				// lookup an alternate part - need an ArrayList to hold multiples
				//WORKS
				/*
				String productID = "Am123B";
				compatibleProducts = readerDAO.getCompatibleProducts(productID);
				
				System.out.println("In main: \n");
				for(Product product: compatibleProducts) {
					System.out.println("Product: " + product.toString());
				}
				*/
				
				// lookup a customers invoices
				//WORKS
				/*
				String customerID = "1";
				invoices = readerDAO.getInvoices(customerID);
				System.out.println("Main-Invoices ");
				for(Invoice i: invoices) {
					System.out.println("Invoice: " + i.toString());
				}
				*/
				
				// get the line items for an invoice
				// WORKS
				/*
				String invoiceNumber = "1";
				lineItems = readerDAO.getInvoiceLineItems(invoiceNumber);
				System.out.println("Main-line items ");
				for(InvoiceLineItem lineItems: lineItems) {
					System.out.println("Line Items: " + lineItems.toString());
				}
				*/
				
			}
		
	
	
	
	// Constructor for the main gui
	/**
	 * Create the application items.
	 */
	/**
	public MainAutoPartsGUI() {
		initialize();
		writerDAO = DAOFactory.getWriterDAO();
		readerDAO = DAOFactory.getReaderDAO();
		deleterDAO = DAOFactory.getDeleterDAO();
	}
	**/
	
	// Custom methods used in this class
	
	
	// ******** Keep all gui components below this line***************************
	/**
	 * Initialize the contents of the frame.
	 */
	// Changed to static
	private static void initialize() {
		
		System.out.println("In main - initialize");
	}
}
