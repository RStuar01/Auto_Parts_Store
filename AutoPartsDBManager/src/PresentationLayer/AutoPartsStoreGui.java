package PresentationLayer;

import java.awt.EventQueue;
import java.sql.SQLException;

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
	
	private static String choice = "";
	
	// Other local variables
	/*
	 
	
	  //Launch the application
	  //@param args
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello!");
		
		//Remove next line
		MainAutoPartsGUI();
		
		// ********** REMOVE THIS AND REPLACE with code for this program
		// This is an example of how it may be written from another program I wrote
		/**
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeopleManager window = new PeopleManager();	
					window.peopleManagerFrame.setVisible(true);
					window.peopleManagerFrame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		
		
	}
	*/
	
	//Remove later and replace with version below
	//private static void MainAutoPartsGUI() { // changed to version below
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
		
		// enter a product and update accounting_purchases
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
		
		// Manage a Sale
		// WORKS
		/*
		String date = "2018-02-03";
		String time = "20:00";
		String customerID = "1";
		String employeeID = "2";
		String quantityPurchased = "3";
		String productID = "6";
		
		writerDAO.manageSale(date, time, customerID, employeeID, quantityPurchased, 
				productID);
		*/
		
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
		
		// Read and create a Company object
		
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
