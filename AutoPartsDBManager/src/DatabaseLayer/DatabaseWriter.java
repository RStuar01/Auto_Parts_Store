/**
 * Class Name:		DatabaseWriter
 * Description:		This class contains the methods called using data access objects to Write information
 * 					to the database, and also methods which call these methods directly.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BusinessLayer.Product;

/**
 * This class holds the methods called from other classes to write data to the database
 * Written by Rick Stuart
 */
public class DatabaseWriter implements WriterDAO {
	private Connection connObj = null;	
	private WriteHelper writerHelper = null;
	private ReaderDAO readerDAO;
	private RFIDDAO rfidDAO;
	
	/**
	 * This is the Constructor that is called from the DAOFactory class.
	 * This class also calls WriterHelper to obtain an instance of that class.
	 * Written by Rick Stuart
	 */
	public DatabaseWriter() {
		
		writerHelper = new WriteHelper();
		readerDAO = DAOFactory.getReaderDAO();
		rfidDAO = DAOFactory.getRFIDDAO();
	}
	
	/**
	 * This method closes the database connection.
	 * @param connObj				The connection object.
	 * Written by Rick Stuart
	 */
	public static void closeConnection(Connection connObj) {
		
		if(connObj != null) {
			try {
				connObj.close();
			}
			catch (SQLException ignore) {
			}
		}
	}
	
	/**
	 * This method obtains a connection to the database.
	 * @return connection			The database connection object.
	 * Written by Rick Stuart
	 */
	public static Connection getDBConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		String url = "jdbc:mysql://localhost:3306/auto_parts_schema";
		String username = "autouser";
		String password = "autouser";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return connection;
	}
	
	/**
	 * This method manages entering the data and creating an object for a new
	 * 			customer, employee or supplier
	 * @param	choice		String variable to identify the new person type
	 * @param	lastName	String to hold last name 
	 * @param	firstName	String to hold first name
	 * @param	stAddress	String to hold the street address
	 * @param	city		String to hold the city name
	 * @param	state		String to hold the state name
	 * @param	zipCode		String to hold the zip code
	 * @param	unitNumber	String to hold the unit number
	 * @param	phoneNumber	String to hold the phone number
	 * @param	cellPhone	String to hold the cell phone number
	 * @param	emailAddress	String to hold the email address
	 * @param	companyID		String to hold the company ID
	 * Written by Rick Stuart
	 */
	public void manageNewPersonCreation(String choice, String lastName, String firstName,
			String stAddress, String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhone, String emailAddress, String companyID){
		
		String addressID = null;
		String contactInfoID = null;
		
		// write new address and contact records, obtain the id's
		writerHelper.writeAddressInformation(stAddress, city, state, zipCode, unitNumber);
		addressID = readerDAO.obtainNewAddressID(stAddress, city, state, zipCode, unitNumber);
		writerHelper.writeContactInformation(phoneNumber, cellPhone, emailAddress);
		contactInfoID = readerDAO.obtainNewContactInformationID(phoneNumber, cellPhone, emailAddress);
		
		// determine type of person to create
		if(choice == "Customer") {
			writerHelper.writeCustomerInformation(addressID, contactInfoID, lastName, firstName);
		}
		else if (choice == "Employee") {
			writerHelper.writeEmployeeInformation(addressID, contactInfoID, lastName, firstName);
		}
		else if (choice == "Supplier") {
			writerHelper.writeSupplierInformation(addressID, contactInfoID, lastName, firstName,
					companyID);
		}
	}
	
	/**
	 * This method manages entering a new company along with it's
	 * 		address and contact information
	 * @param	stAddress	String to hold the street address
	 * @param	city		String to hold the city name
	 * @param	state		String to hold the state name
	 * @param	zipCode		String to hold the zip code
	 * @param	unitNumber	String to hold the unit number
	 * @param	phoneNumber	String to hold the phone number
	 * @param	cellPhone	String to hold the cell phone number
	 * @param	emailAddress	String to hold the email address
	 * @param	companyName		String to hold the company name
	 * Written by Rick Stuart
	 */
	public void createNewCompany(String stAddress, String city, String state,
			String zipCode, String unitNumber, String phoneNumber, String cellPhone,
			String emailAddress, String companyName) {
		
		String addressID = null;
		String contactInfoID = null;
		
		// write new address and contact information and obtain their ID's
		writerHelper.writeAddressInformation(stAddress, city, state, zipCode, unitNumber);
		addressID = readerDAO.obtainNewAddressID(stAddress, city, state, zipCode, unitNumber);
		writerHelper.writeContactInformation(phoneNumber, cellPhone, emailAddress);
		contactInfoID = readerDAO.obtainNewContactInformationID(phoneNumber, cellPhone, emailAddress);
		
		// write the new company information.
		writerHelper.writeCompanyInformation(addressID, contactInfoID, companyName);
	}
	
	/**
	 * This method is used to enter a new product to the database, one that does 
	 * 		not yet exist in the database.
	 * @param	description				String to describe product
	 * @param	yearMin					String to specify year range product works for
	 * @param	yearMax					String to specify year range product works for
	 * @param	make					String to specify the make product works for
	 * @param	model					String to specify the model product works for
	 * @param	supplyPrice				String to specify product cost from supplier
	 * @param	sellPrice				String to specify product's selling price
	 * @param	coreCharge				String to specify cost for core charge
	 * @param	compatNum				String to identify what products are compatible
	 * @param	companyID				String to specify company ID product is obtained from
	 * @param	minStockQuantity		String to specify when to reorder product
	 * @param	maxStockQuantity		String to specify how much to reorder
	 * @param	location				String to specify location in warehouse
	 * @param	quantityInStock			String to identify how many items to add to database
	 * Written by Rick Stuart
	 */
	public void manageEnteringNewProduct(String description, String yearMin, String yearMax,
			String make, String model, String supplyPrice, String sellPrice,
			String coreCharge, String compatNum, String companyID, String minStockQuantity,
			String maxStockQuantity, String location, String quantityInStock) {
		
		String productID = "";
		String dollarValue = "0";
		
		// write the product to the database
		writerHelper.enterNewProduct(description, yearMin, yearMax, make, model,
				supplyPrice, sellPrice, coreCharge, compatNum, companyID,
				minStockQuantity, maxStockQuantity, location, quantityInStock);
		
		// obtain the ID for the product
		productID = readerDAO.obtainProductID(description, yearMin, yearMax, make, model,
				supplyPrice, sellPrice, coreCharge, compatNum, companyID,
				minStockQuantity, maxStockQuantity, location, quantityInStock);
		
		// obtain the amount spent on product for accounting
		dollarValue = readerDAO.obtainDollarValue(quantityInStock, supplyPrice);
	
		// Enter the purchase to accounting purchases
		writerHelper.enterToAccountingPurchases (quantityInStock, dollarValue, productID);
		
	}
	
	/**
	 * this method manages a sale to a customer
	 * @param	date				String to write date of sale
	 * @param	time				String to record time of sale
	 * @param	customerID			String to specify customer that made the purchase
	 * @param	employeeID			String to record the employee that made the sale
	 * @param	productID			string to record the item sold and update inventory
	 * Written by Rick Stuart
	 */
	public void manageSale(String date, String time, String customerID, String employeeID,
			String quantityPurchased, String productID) {
		
		String invoiceID = "";
		String lineID = "";
		String sellPrice = "";
		String dollarValue = "";
		String salesTax = "";
		Boolean reorderProduct = false;
		
		// create a new invoice and obtain it's id
		writerHelper.createInvoice(date, time, customerID, employeeID);
		invoiceID = readerDAO.obtainNewInvoiceNumber(date, time, customerID, employeeID);
		
		// create a new line item 
		writerHelper.createInvoiceLineItem(invoiceID, quantityPurchased, productID);
		
		// obtain auto-incremented invoice ID, sale price, total cost and tax collected
		lineID = readerDAO.obtainLineItemID(invoiceID, quantityPurchased, productID);
		sellPrice = readerDAO.obtainSellPrice(productID);
		dollarValue = readerDAO.obtainDollarValue(quantityPurchased, sellPrice);
		salesTax = readerDAO.obtainSalesTax(dollarValue);
		
		// Enter the sale to accounting records
		writerHelper.enterAccountingSales(lineID, quantityPurchased, productID, dollarValue,
				salesTax);
		
		// subtract the number of items sold from inventory
		writerHelper.updateQuantityInStock(productID, quantityPurchased);
		
		// check if reorder necessary
		reorderProduct = writerHelper.checkReorderNecessity(productID);
		
		// re-order product to manage inventory levels if necessary
		if(reorderProduct) {
			writerHelper.createOrderForProduct(productID);
		}
	}
	
	/**
	 * This method is used by the RFID reader to record products entering 
	 * 			the warehouse automatically
	 * @param	rfidProducts			ArrayList of incoming products
	 * Written by Rick Stuart
	 */
	public void writeIncomingProducts(ArrayList<Product> rfidProducts) {
		
		boolean exists = false;
		
		for(Product p: rfidProducts) {
			
			// determine if product is previously entered to the database
			exists = writerHelper.verifyProductInDatabase(p);
			String productID = p.getProductID();
			
			// if the product is not new - record the product
			if(exists) {
				writerHelper.writeIncomingProduct(p, productID);
				exists = false;
			}
			else {	// If product is new - must enter manually
				int quantityRejected = Integer.parseInt(p.getQuantityInStock());
				String reasonRejected = "Product not in database: ";
				rfidDAO.writeQuantityRejected(reasonRejected, p, productID, quantityRejected);
			}
		}
	}
	
	/**
	 * This method manages manually entering a new purchase to accounting records
	 * @param	productID			String to identify the new product
	 * @param	quantityPurchased	String to record number of items
	 * @param	dollarValue			String to record total cost for accounting
	 * Written by Rick Stuart
	 */
	public void manuallyEnterNewAccountingPurchase(String productID, 
			String quantityPurchased, String dollarValue) {
		
		String newPurchaseUpdate = null;
		
		newPurchaseUpdate = "insert into accounting_purchases " +
				"(accounting_purchases_record_id, purchases_quantity, dollar_value, "
				+ "product_product) " +
				"values (DEFAULT, '" + quantityPurchased + "', -'" + dollarValue + "', '" +
				productID + "');";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newPurchaseUpdate);
			writerHelper.updateQuantityInStockPurchase(productID, quantityPurchased);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
	}
	
	/**
	 * This method manages manually entering a new product to the database records
	 * @param	description				String to describe product
	 * @param	yearMin					String to specify year range product works for
	 * @param	yearMax					String to specify year range product works for
	 * @param	make					String to specify the make product works for
	 * @param	model					String to specify the model product works for
	 * @param	supplyPrice				String to specify product cost from supplier
	 * @param	sellPrice				String to specify product's selling price
	 * @param	coreCharge				String to specify cost for core charge
	 * @param	compatNum				String to identify what products are compatible
	 * @param	companyID				String to specify company ID product is obtained from
	 * @param	minStockQuantity		String to specify when to reorder product
	 * @param	maxStockQuantity		String to specify how much to reorder
	 * @param	location				String to specify location in warehouse
	 * @param	quantityInStock			String to identify how many items to add to database
	 * Written by Rick Stuart
	 */
	public void manuallyEnterNewPart(String description, String minYear, String maxYear,
			String make, String model, String supplierPrice, String sellPrice, 
			String coreCharge, String compatibilityNumber, String companyID, 
			String minStockQuantity, String maxStockQuantity,
			String warehouseLocation, String quantityInStock) {
		
		String newPartUpdate = null;
		
		newPartUpdate = "insert into product "
				+ "values (DEFAULT, '" + description + "', '" + minYear + "', '"
				+ maxYear + "', '" + make + "', '" + model + "', '" + supplierPrice
				+ "', '" + sellPrice + "', '" + coreCharge + "', '"
				+ compatibilityNumber + "', '" + companyID + "', '"
				+ minStockQuantity + "', '" + maxStockQuantity + "', '"
				+ warehouseLocation + "', '" + quantityInStock + "');";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newPartUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
	}
	
	/**
	 * This method creates a single invoice manually
	 * @param	date				String to write date of sale
	 * @param	time				String to record time of sale
	 * @param	customerID			String to specify customer that made the purchase
	 * @param	employeeID			String to record the employee that made the sale
	 * Written by Rick Stuart
	 */
	public void createInvoice(String date, String time, String customerID, String employeeID) {
		
		String newInvoiceUpdate = null;
		
		newInvoiceUpdate = "insert into invoice " +
				"(invoice_number, date, time, customer_customer_id, employee_employee_id) " +
				"values (DEFAULT, '" + date + "', '" + time + "', '" + customerID + "', '" +
				employeeID + "');";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
	}
	
	/**
	 * This method creates a single invoice line item to add to an existing invoice
	 * @param	invoiceNum				String to hold invoice number the line item belongs too
	 * @param	quantityPurchased		String to record how many of a single item were sold
	 * @param	productID				String to identify the product sold
	 * Written by Rick Stuart
	 */
	public void createInvoiceLineItem(String invoiceNum, String quantityPurchased,
			String productID) {
		
		String newInvoiceLineItemUpdate = null;
		
		newInvoiceLineItemUpdate = "insert into invoice_line_item " +
				"(invoice_line_number, invoice_invoice_number, quantity_purchased, " +
				"product_product) " +
				"values (DEFAULT, '" + invoiceNum + "', '" + quantityPurchased +
				"', '" + productID + "');";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceLineItemUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
	}
	
	/**
	 * This method is used to verify the manual entry of a company id is valid
	 * @param 	companyID			String to hold the company ID 
	 * Written by Rick Stuart
	 */
	public boolean checkCompanyExists(String companyID) {
		
		boolean valid = false;
		ResultSet rs = null;
		String companyName = "";
		String companyIDQuery = null;
		
		companyIDQuery = "select company_name " +
				"from company " +
				"where company_id = '" + companyID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(companyIDQuery);			
			while(rs.next()) {
				companyName = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		if(companyName.length() != 0) {
			valid = true;
		}
		return valid;
	}
	
	/**
	 * This method is used to verify that a manually entered product ID is valid
	 * @param	productID		String to hold the product ID
	 * Written by Rick Stuart
	 */
	public boolean checkProductExists(String productID) {
		
		boolean valid = false;
		ResultSet rs = null;
		String productName = "";
		String productIDQuery = null;
		
		productIDQuery = "select description " +
				"from product " +
				"where product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(productIDQuery);			
			while(rs.next()) {
				productName = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		if(productName.length() != 0) {
			valid = true;
		}
		return valid;
		
	}
	
	/**
	 * This method is used to verify the manually entere customer ID is valid
	 * @param	customerID			String to hold the customer ID
	 * Written by Rick Stuart
	 */
	public boolean checkCustomerExists(String customerID) {
		
		boolean valid = false;
		ResultSet rs = null;
		String customerName = "";
		String customerIDQuery = null;
		
		customerIDQuery = "select last_name " +
				"from customer " +
				"where customer_id = '" + customerID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(customerIDQuery);			
			while(rs.next()) {
				customerName = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		if(customerName.length() != 0) {
			valid = true;
		}
		return valid;
	}
	
	/**
	 * This method is used to verify the manually entere employee ID is valid
	 * @param	employeeID			String to hold the employee ID
	 * Written by Rick Stuart
	 */
	public boolean checkEmployeeExists(String employeeID) {
		
		boolean valid = false;
		ResultSet rs = null;
		String employeeName = "";
		String employeeIDQuery = null;
		
		employeeIDQuery = "select last_name " +
				"from employee " +
				"where employee_id = '" + employeeID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(employeeIDQuery);			
			while(rs.next()) {
				employeeName = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		if(employeeName.length() != 0) {
			valid = true;
		}
		return valid;
	}

	/**
	 * This method is used to manage a sale
	 * @param	invoiceNumber		String to hold the invoice number for the sale
	 * @param	purchasedQuantity	String to record number of items on line item
	 * @param	productID			String to identify the product sold
	 * Written by Rick Stuart
	 */
	public void manageEnteringToAccountingSales(String invoiceNumber, 
		String purchasedQuantity, String productID) {
		
		String lineID = "";
		String sellPrice = "";
		String dollarValue = "";
		String salesTax = "";
		boolean reorderProduct = false;
		
		// Create a new invoice line item and obtain it's line number ID
		createInvoiceLineItem(invoiceNumber, purchasedQuantity,
				productID);
		lineID = readerDAO.obtainInvoiceLineID(invoiceNumber, purchasedQuantity,
				productID);
		
		// obtain price, total value and sales tax for this line item
		sellPrice = readerDAO.obtainSellPrice(productID);
		dollarValue = readerDAO.obtainDollarValue(purchasedQuantity, sellPrice);
		salesTax = readerDAO.obtainSalesTax(dollarValue);
		
		// write the sale to accounting records and update quantity in warehouse
		writerHelper.enterAccountingSales(lineID, purchasedQuantity, productID, dollarValue,
				salesTax);
		writerHelper.updateQuantityInStock(productID, purchasedQuantity);
		
		// check if reorder necessary
		reorderProduct = writerHelper.checkReorderNecessity(productID);
		
		// create an order for the product if necessary
		if(reorderProduct) {
			writerHelper.createOrderForProduct(productID);
		}
	}
	
	/**
	 * This method is used to manage editing a customer record
	 * @param	customerID	String to hold the customer ID
	 * @param	contactID	String to hold the contactInfo ID
	 * @param	addressID	String to hold the address ID
	 * @param	lastName	String to hold last name 
	 * @param	firstName	String to hold first name
	 * @param	stAddress	String to hold the street address
	 * @param	city		String to hold the city name
	 * @param	state		String to hold the state name
	 * @param	zipCode		String to hold the zip code
	 * @param	unitNumber	String to hold the unit number
	 * @param	phoneNumber	String to hold the phone number
	 * @param	cellPhone	String to hold the cell phone number
	 * @param	email		String to hold the email address
	 * Written by Rick Stuart
	 */
	public void manageEditingCustomer(String customerID, String contactID, String addressID, 
			String lastName, String firstName, String streetAddress, 
			String city, String state, String zipCode, String unitNumber, 
			String homePhone, String cellPhone, String email) {
		
		// write over the existing records to record alterations such as spelling errors
		writerHelper.editAddress(addressID, streetAddress, city, state, zipCode, unitNumber);
		writerHelper.editContactInfo(contactID, homePhone, cellPhone, email);
		writerHelper.editCustomer(customerID, lastName, firstName, contactID, addressID);
	}

	/**
	 * This method is used to manage editing an employee record
	 * @param	employeeID	String to hold the customer ID
	 * @param	contactID	String to hold the contactInfo ID
	 * @param	addressID	String to hold the address ID
	 * @param	lastName	String to hold last name 
	 * @param	firstName	String to hold first name
	 * @param	stAddress	String to hold the street address
	 * @param	city		String to hold the city name
	 * @param	state		String to hold the state name
	 * @param	zipCode		String to hold the zip code
	 * @param	unitNumber	String to hold the unit number
	 * @param	phoneNumber	String to hold the phone number
	 * @param	cellPhone	String to hold the cell phone number
	 * @param	email		String to hold the email address
	 * Written by Rick Stuart
	 */
	public void manageEditingEmployee(String employeeID, String contactID, String addressID,
			String lastName, String firstName, String streetAddress, String city,
			String state, String zipCode, String unitNumber, String homePhone,
			String cellPhone, String email) {

		// write over the existing records to record alterations such as spelling errors
		writerHelper.editAddress(addressID, streetAddress, city, state, zipCode, unitNumber);
		writerHelper.editContactInfo(contactID, homePhone, cellPhone, email);
		writerHelper.editEmployee(employeeID, lastName, firstName, contactID, addressID);
	}
	
	/**
	 * This method is used to edit product information due to data entry errors
	 * @param	productID				String to identify the product being edited
	 * @param	description				String to describe product
	 * @param	minYear					String to specify year range product works for
	 * @param	maxYear					String to specify year range product works for
	 * @param	make					String to specify the make product works for
	 * @param	model					String to specify the model product works for
	 * @param	supplierPrice				String to specify product cost from supplier
	 * @param	sellPrice				String to specify product's selling price
	 * @param	coreCharge				String to specify cost for core charge
	 * @param	compatibilityNumber		String to identify what products are compatible
	 * @param	companyID				String to specify company ID product is obtained from
	 * @param	minStockQuantity		String to specify when to reorder product
	 * @param	maxStockQuantity		String to specify how much to reorder
	 * @param	warehouseLocation		String to specify location in warehouse
	 * @param	quantityInStock			String to identify how many items to add to database
	 * Written by Rick Stuart
	 */
	public void editProduct(String productID, String description, String minYear,
			String maxYear, String make, String model, String supplierPrice,
			String sellPrice, String coreCharge, String compatibilityNumber,
			String companyID, String minStockQuantity, String maxStockQuantity,
			String warehouseLocation, String quantityInStock) {
		
		String update = null;
		Statement stmt = null;
		
		// write over the existing record to fix data entry errors
		update = "UPDATE product "
				+ "SET description = '" + description + "', year_minimum = '"
				+ minYear + "', year_maximum = '" + maxYear + "', make = '" 
				+ make + "', model = '" + model + "', supplier_price = '" 
				+ supplierPrice + "', sell_price = '" + sellPrice + "', core_charge = '"
				+ coreCharge + "', compatibility_number = '" + compatibilityNumber
				+ "', company_company_id = '" + companyID
				+ "', min_quantity_in_stock = '" + minStockQuantity 
				+ "', max_quantity_in_stock = '" + maxStockQuantity
				+ "', warehouse_location = '" + warehouseLocation
				+ "', quantity_in_stock = '" + quantityInStock
				+ "' WHERE product = " + productID + ";";
				
		connObj = getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		closeConnection(connObj);
	}
	
	/**
	 * This method is used to edit company information due to data entry errors
	 * @param	companyID	String to identify the company
	 * @param	addressID	String to identify the address record number
	 * @param	contactID	String to identify the contact info record number
	 * @param	streetAddress	String to hold the street address
	 * @param	city		String to hold the city name
	 * @param	state		String to hold the state name
	 * @param	zipCode		String to hold the zip code
	 * @param	unitNumber	String to hold the unit number
	 * @param	homePhone	String to hold the phone number
	 * @param	cellPhone	String to hold the cell phone number
	 * @param	email		String to hold the email address
	 * @param	companyName		String to hold the company name
	 * Written by Rick Stuart
	 */
	public void manageEditingCompany(String companyID, String addressID, 
			String contactID, String streetAddress, String city, String state, 
			String zipCode, String unitNumber, String homePhone, String cellPhone,
			String email, String companyName) {
		
		// overwrite the existing records to fix data entry errors
		writerHelper.editAddress(addressID, streetAddress, city, state, zipCode, unitNumber);
		writerHelper.editContactInfo(contactID, homePhone, cellPhone, email);
		writerHelper.editCompany(companyID, companyName);
		
	}
	
	/**
	 * This method is used to edit supplier information to fix data entry errors
	 * @param supplierID		String to hold the supplier record ID number
	 * @param contactID			String to hold the contact info record ID number
	 * @param addressID			String to hold the address record ID number
	 * @param companyID			String to identify the company ID number
	 * @param lastName			String to hold the last name of supplier
	 * @param firstName			String to hold the supplier's first name
	 * @param streetAddress		String to hold the street address
	 * @param city				String to hold the city name
	 * @param state				String to hold the state name
	 * @param zipCode			String to hold the zip code number
	 * @param unitNumber		String to hold the unit number
	 * @param homePhone			String to hold the home telephone number
	 * @param cellPhone			String to hold the cell phone number
	 * @param email				String to hold the email address
	 * Written by Rick Stuart
	 */
	public void manageEditingSupplier(String supplierID, String contactID, 
			String addressID, String companyID, String lastName, String firstName, 
			String streetAddress, String city, String state, String zipCode, 
			String unitNumber, String homePhone, String cellPhone, String email) {

		// overwrite the existing records to fix data entry errors
		writerHelper.editAddress(addressID, streetAddress, city, state, zipCode, unitNumber);
		writerHelper.editContactInfo(contactID, homePhone, cellPhone, email);
		writerHelper.editSupplier(supplierID, lastName, firstName, contactID, addressID, companyID);
	}
}
