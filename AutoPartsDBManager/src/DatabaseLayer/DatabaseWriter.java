package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BusinessLayer.Product;

// List imports

/**
 * Class Name:		DatabaseWriter
 * Description:		This class contains the methods called using data access objects to Write information
 * 					to the database, and also methods which call these methods directly.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
public class DatabaseWriter implements WriterDAO {
	private static Connection connObj = null;	// Changed to static for testing
	private WriteHelper writerHelper = null;
	
	/**
	 * This is the Constructor that is called from the DAOFactory class.
	 * This method also calls the WriteHelper Constructor to provide an instance of that class.
	 */
	public DatabaseWriter() {
		
		writerHelper = new WriteHelper();
		connObj = getDBConnection();
		//call the RFID reader from here***********
		closeConnection(connObj);
	}
	
	/**
	 * This method closes the database connection.
	 * @param connObj				The connection object.
	 */
	static void closeConnection(Connection connObj) {
		
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
	 */
	static Connection getDBConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		//String url = "jdbc:mysql://localhost:3306/mydb";
		String url = "jdbc:mysql://localhost:3306/auto_parts_schema";
		String username = "autouser";
		String password = "autouser";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		/*
		System.out.println("DatabaseWriter - Connection Established!");
		
		// check a simple read - REMOVE THIS LATER
		String query = "SELECT last_name FROM customer WHERE customer_id = 1";
		Statement stmt = null;
		String lastName = null;
		try {	
			//stmt = connObj.createStatement();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				lastName = rs.getString(1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
					
			System.out.println("The value read from the database is:");
			System.out.println(lastName);
		*/
		return connection;
	}
	
	// Enters a new Customer/Employee/ or Supplier along with their address info and
	// contact info
	public void manageNewPersonCreation(String choice, String lastName, String firstName,
			String stAddress, String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhone, String emailAddress, String companyID){
		
		String addressID = null;
		String contactInfoID = null;
		
		writerHelper.writeAddressInformation(stAddress, city, state, zipCode, unitNumber);
		addressID = writerHelper.obtainNewAddressID(stAddress, city, state, zipCode, unitNumber);
		writerHelper.writeContactInformation(phoneNumber, cellPhone, emailAddress);
		contactInfoID = writerHelper.obtainNewContactInformationID(phoneNumber, cellPhone, emailAddress);
		
		if(choice == "Customer") {
			writerHelper.writeCustomerInformation(addressID, contactInfoID, lastName, firstName);
		}
		else if (choice == "Employee") {
			writerHelper.writeEmployeeInformation(addressID, contactInfoID, lastName, firstName);
		}
		else if (choice == "Supplier") {
			//addressID = "38";
			//contactInfoID = "38";
			//companyID = "5";
			writerHelper.writeSupplierInformation(addressID, contactInfoID, lastName, firstName,
					companyID);
		}
	}
	
	// Enters a new Company along with address and contact info
	public void createNewCompany(String stAddress, String city, String state,
			String zipCode, String unitNumber, String phoneNumber, String cellPhone,
			String emailAddress, String companyName) {
		
		String addressID = null;
		String contactInfoID = null;
		
		writerHelper.writeAddressInformation(stAddress, city, state, zipCode, unitNumber);
		addressID = writerHelper.obtainNewAddressID(stAddress, city, state, zipCode, unitNumber);
		writerHelper.writeContactInformation(phoneNumber, cellPhone, emailAddress);
		contactInfoID = writerHelper.obtainNewContactInformationID(phoneNumber, cellPhone, emailAddress);
		writerHelper.writeCompanyInformation(addressID, contactInfoID, companyName);
	}
	
	//NOTE:  this is to enter a new product - the first time it is put in stock
	// Enters the product, updates accounting_purchases
	public void manageEnteringNewProduct(String description, String yearMin, String yearMax,
			String make, String model, String supplyPrice, String sellPrice,
			String coreCharge, String compatNum, String companyID, String minStockQuantity,
			String maxStockQuantity, String location, String quantityInStock) {
		
		String productID = "";
		String dollarValue = "0";
		
		writerHelper.enterNewProduct(description, yearMin, yearMax, make, model,
				supplyPrice, sellPrice, coreCharge, compatNum, companyID,
				minStockQuantity, maxStockQuantity, location, quantityInStock);
		
		productID = writerHelper.obtainProductID(description, yearMin, yearMax, make, model,
				supplyPrice, sellPrice, coreCharge, compatNum, companyID,
				minStockQuantity, maxStockQuantity, location, quantityInStock);
		
		dollarValue = writerHelper.obtainDollarValue(quantityInStock, supplyPrice);
		
		System.out.println("$" + dollarValue);
		
		writerHelper.enterToAccountingPurchases (quantityInStock, dollarValue, productID);
		
	}
	
	// Manages a sale - creates the invoice, 1 line item, updates accounting_sales
	//NEED TO ADAPT FOR MULTIPLE LINE ITEMS
	public void manageSale(String date, String time, String customerID, String employeeID,
			String quantityPurchased, String productID) {
		
		String invoiceID = "";
		String lineID = "";
		String sellPrice = "";
		String dollarValue = "";
		String salesTax = "";
		Boolean reorderProduct = false;
		
		writerHelper.createInvoice(date, time, customerID, employeeID);
		invoiceID = writerHelper.obtainNewInvoiceNumber(date, time, customerID, employeeID);
		
		System.out.println("Invoice Number: " + invoiceID);
		writerHelper.createInvoiceLineItem(invoiceID, quantityPurchased, productID);
		lineID = writerHelper.obtainLineItemID(invoiceID, quantityPurchased, productID);
		sellPrice = writerHelper.obtainSellPrice(productID);
		dollarValue = writerHelper.obtainDollarValue(quantityPurchased, sellPrice);
		salesTax = writerHelper.obtainSalesTax(dollarValue);
		
		System.out.println("Line ID: " + lineID);
		System.out.println("Sell Price: $" + sellPrice);
		System.out.println("Dollar Value: " + dollarValue);
		System.out.println("Sales Tax: " + salesTax);
		writerHelper.enterAccountingSales(lineID, quantityPurchased, productID, dollarValue,
				salesTax);
		writerHelper.updateQuantityInStock(productID, quantityPurchased);
		
		// check if reorder necessary
		reorderProduct = writerHelper.checkReorderNecessity(productID);
		System.out.println("Need to reorder: " + reorderProduct);
		
		if(reorderProduct) {
			writerHelper.createOrderForProduct(productID);
		}
		
	}
	
	public void writeIncomingProducts(ArrayList<Product> rfidProducts) {
		
		boolean exists = false;
		
		System.out.println("In the write method");
		
		for(Product p: rfidProducts) {
			exists = writerHelper.verifyProductInDatabase(p);
			String productID = p.getProductID();
			if(exists) {
				writerHelper.writeIncomingProduct(p, productID);
				
				
				
				
				
				exists = false;
			}
			else {
				System.out.println("Product does not exist in database - enter product!");
			}
		}
		
	}
	
	public void manuallyEnterNewAccountingPurchase(String productID, 
			String quantityPurchased, String dollarValue) {
		
		String newPurchaseUpdate = null;
		
		newPurchaseUpdate = "insert into accounting_purchases " +
				"(accounting_purchases_record_id, purchases_quantity, dollar_value, "
				+ "product_product) " +
				"values (DEFAULT, '" + quantityPurchased + "', -'" + dollarValue + "', '" +
				productID + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newPurchaseUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
	}
	
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
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newPartUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
	}
	
	// copy from writeHelper - due to changes in gui
	public void createInvoice(String date, String time, String customerID, String employeeID) {
		
		String newInvoiceUpdate = null;
		
		newInvoiceUpdate = "insert into invoice " +
				"(invoice_number, date, time, customer_customer_id, employee_employee_id) " +
				"values (DEFAULT, '" + date + "', '" + time + "', '" + customerID + "', '" +
				employeeID + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
	}
	
	public void createInvoiceLineItem(String invoiceNum, String quantityPurchased,
			String productID) {
		
		String newInvoiceLineItemUpdate = null;
		
		newInvoiceLineItemUpdate = "insert into invoice_line_item " +
				"(invoice_line_number, invoice_invoice_number, quantity_purchased, " +
				"product_product) " +
				"values (DEFAULT, '" + invoiceNum + "', '" + quantityPurchased +
				"', '" + productID + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceLineItemUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
	}
}
