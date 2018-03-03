/**
 * Interface Name:	DataWriter
 * Description:		This interface defines the methods which are employed by the DatabaseWriter class.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package DatabaseLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BusinessLayer.Product;

/**
 * This class holds methods called by the DatabaseWriter class to assist with writing
 * 			to the database
 * Written by Rick Stuart
 */
public class WriteHelper {
	
	private Connection connObj = null;
	private static ReaderDAO readerDAO;
	private static RFIDDAO rfidDAO;
	
	/**
	 * Constructor called from the DatabaseWriter class to create an instance of this class.
	 */
	public WriteHelper() {
		readerDAO = DAOFactory.getReaderDAO();
		rfidDAO = DAOFactory.getRFIDDAO();
	}

	/**
	 * This helper method writes the information for a new address record to the database.
	 * @param streetAddress			String variable for the street address.
	 * @param city					String variable for the city.
	 * @param state					String variable for the state.
	 * @param zipCode				String variable for the zip code.
	 * @param unitNumber			String variable for the unit number.
	 * Written by Rick Stuart
	 */
	public void writeAddressInformation(String streetAddress, String city, String state, 
			String zipCode, String unitNumber) {
	
		String newAddressUpdate = null;
	
		newAddressUpdate = "insert into address " +
				"(address_id, street_address, city, state, zip_code, unit_number) " +
				"values (DEFAULT, '" + streetAddress + "', '" + city + "', '" + state + "', '" + zipCode +
				"', '" + unitNumber + "');";
		
		Statement stmt = null;
							
		connObj = DatabaseWriter.getDBConnection();
							
		try {	
			stmt = connObj.createStatement();
			stmt.executeUpdate(newAddressUpdate);			
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
			
		DatabaseWriter.closeConnection(connObj);		
	}
	
	/**
	 * This helper method writes a new contact information record to the database.
	 * @param phoneNumber			String variable for the phone number.
	 * @param cellPhone				String variable for the cell phone number.
	 * @param emailAddress			String variable for the email address.
	 * Written by Rick Stuart
	 */
	public void writeContactInformation(String phoneNumber, String cellPhone, 
			String emailAddress) {
		
		String newContactInfoUpdate = null;
		
		newContactInfoUpdate = "insert into contact_info " +
				"(contact_info_id, phone_number, cell_phone_number, email_address) " +
				"values (DEFAULT, '" + phoneNumber + "', '" + cellPhone +
				"', '" + emailAddress + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newContactInfoUpdate);
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method writes a new Customer record to the database.
	 * @param addressID				String variable for the address ID number.
	 * @param contactInfoID			String variable for the contact information ID number.
	 * @param lastName				String variable for the Customers last name.
	 * @param firstName				String variable for the Customers first name.
	 * Written by Rick Stuart
	 */
	public void writeCustomerInformation(String addressID, String contactInfoID, 
			String lastName, String firstName) {
		
		String newCustomerUpdate = null;
		
		newCustomerUpdate = "insert into customer " +
				"(customer_id, Address_address_id, contact_info_contact_info_id, last_name, first_name) " +
				"values (DEFAULT, '" + addressID + "', '" + contactInfoID + "', '" +
				lastName + "', '" + firstName + "');";
		
		Statement stmt = null;
										
		connObj = DatabaseWriter.getDBConnection();
										
		try {	
			stmt = connObj.createStatement();
			stmt.executeUpdate(newCustomerUpdate);			
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
						
		DatabaseWriter.closeConnection(connObj);		
	}
	
	/**
	 * This helper method writes the information for a new Employee record 
	 * 			to the database.
	 * @param addressID				String variable for the address ID number.
	 * @param contactInfoID			String variable for the contact information ID number.
	 * @param lastName				String variable for the Employees' last name.
	 * @param firstName				String variable for the Employees' first name.
	 * Written by Rick Stuart
	 */
	public void writeEmployeeInformation(String addressID, String contactInfoID, 
			String lastName, String firstName) {
		
		String newEmployeeUpdate = null;
		
		newEmployeeUpdate = "insert into employee " +
				"(employee_id, Address_address_id, contact_info_contact_info_id, last_name, first_name) " +
				"values (DEFAULT, '" + addressID + "', '" + contactInfoID + "', '" +
				lastName + "', '" + firstName + "');";
		
		Statement stmt = null;
										
		connObj = DatabaseWriter.getDBConnection();
										
		try {	
			stmt = connObj.createStatement();
			stmt.executeUpdate(newEmployeeUpdate);			
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
						
		DatabaseWriter.closeConnection(connObj);		
	}
	
	/**
	 * This helper method writes a supplier record to the database.
	 * @param addressID				String to hold address record ID number
	 * @param contactInfoID			String to hold contact info record ID number
	 * @param lastName				String to hold last name
	 * @param firstName				String to hold first name
	 * @param companyID				String to hold company ID number
	 * Written by Rick Stuart
	 */
	public void writeSupplierInformation(String addressID, String contactInfoID, 
			String lastName, String firstName, String companyID) {
		
		String newSupplierUpdate = null;
		
		newSupplierUpdate = "insert into supplier " +
				"(supplier_id, Address_address_id, contact_info_contact_info_id, last_name, first_name,"
				+ " company_company_id) " +
				"values (DEFAULT, '" + addressID + "', '" + contactInfoID + "', '" +
				lastName + "', '" + firstName + "', '" + companyID + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newSupplierUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method writes a company record to the database.
	 * @param addressID				String to hold address record ID number
	 * @param contactInfoID			String to hold contact info record ID number
	 * @param companyName			String to hold company name
	 * Written by Rick Stuart
	 */
	public void writeCompanyInformation(String addressID, String contactInfoID, 
			String companyName) {
		
		String newCompanyUpdate = null;
		
		newCompanyUpdate = "insert into company " +
				"(company_id, Address_address_id, contact_info_contact_info_id, company_name) " +
				"values (DEFAULT, '" + addressID + "', '" + contactInfoID + "', '" +
				companyName + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newCompanyUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method writes a new product record to the database.
	 * @param description			String to hold product description
	 * @param yearMin				String to define minimum of year range
	 * @param yearMax				String to define maximum of year range
	 * @param make					String to define vehicle make
	 * @param model					String to define vehicle model
	 * @param supplyPrice			String to specify supplier price of product
	 * @param sellPrice				String to specify selling price of product
	 * @param coreCharge			String to define core charge cost
	 * @param compatNum				String to define part compatibility with other parts
	 * @param companyID				String to hold company record ID number
	 * @param minStockQuantity		String to specify ordering quantities
	 * @param maxStockQuantity		String to specify ordering quantities
	 * @param location				String to define location in warehouse
	 * @param quantityInStock		String to set quantity entering building
	 * Written by Rick Stuart
	 */
	public void enterNewProduct(String description, String yearMin, String yearMax,
			String make, String model, String supplyPrice, String sellPrice,
			String coreCharge, String compatNum, String companyID,
			String minStockQuantity, String maxStockQuantity, String location, 
			String quantityInStock) {
		
		String newProductUpdate = null;
		
		newProductUpdate = "insert into product " +
				"(product, description, year_minimum, year_maximum, make, model, " +
				"supplier_price, sell_price, core_charge, compatibility_number, " +
				"company_company_id, min_quantity_in_stock, max_quantity_in_stock, " +
				"warehouse_location, quantity_in_stock) " +
				"values (DEFAULT, '" + description + "', '" + yearMin + "', '" +
				yearMax + "', '" + make + "', '" + model + "', '" + supplyPrice +
				"', '" + sellPrice + "', '" + coreCharge + "', '" + compatNum +
				"', '" + companyID + "', '" + minStockQuantity + "', '" +
				maxStockQuantity + "', '" + location + "', '" + quantityInStock + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newProductUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method writes a record to accounting purchases
	 * @param numPurchased		String to specify number of items purchased
	 * @param dollarValue		String to specify total cost to purchase inventory items
	 * @param productID			String to specify product ID number
	 * Written by Rick Stuart
	 */
	public void enterToAccountingPurchases(String numPurchased, String dollarValue,
			String productID) {
		
		String newPurchaseUpdate = null;
		//ResultSet rs = null;
		
		newPurchaseUpdate = "insert into accounting_purchases " +
				"(accounting_purchases_record_id, purchases_quantity, dollar_value, "
				+ "product_product) " +
				"values (DEFAULT, '" + numPurchased + "', -'" + dollarValue + "', '" +
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
	
	/**
	 * This helper method creates a new invoice record in the database.
	 * @param date				String to hold the date the invoice was created
	 * @param time				String to hold the time the invoice was created
	 * @param customerID		String to hold the customer record ID number
	 * @param employeeID		String to hold the employee record ID number that created
	 * 									the sale
	 * Written by Rick Stuart
	 */
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
	
	/**
	 * This helper method creates a new invoice line item
	 * @param invoiceNum			String to hold the invoice number
	 * @param quantityPurchased		String to specify number of items sold
	 * @param productID				String to specify product ID number
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
	
	/**
	 * This helper method writes a new sale to accounting records.
	 * @param lineID				String to specify the line item record ID number
	 * @param quantityPurchased		String to specify number of items sold
	 * @param productID				String to specify the product ID
	 * @param dollarValue			String to hold the total cost of product sold
	 * @param salesTax				String to hold the sales tax collected
	 * Written by Rick Stuart
	 */
	public void enterAccountingSales(String lineID, String quantityPurchased,
			String productID, String dollarValue, String salesTax) {
		
		String newAccountingSaleUpdate = null;
		
		newAccountingSaleUpdate = "insert into accounting_sales " +
				"(accounting_sales_record_id, sold_quantity, product_product, " +
				"dollar_value, sales_tax_acquired, invoice_line_item_invoice_line_number) " +
				"values (DEFAULT, '" + quantityPurchased + "', '" + productID + "', '" +
				dollarValue + "', '" + salesTax + "', '" + lineID + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			//System.out.println("In acc sales try");
			stmt = connObj.createStatement();
			stmt.executeUpdate(newAccountingSaleUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method verifies that a user entere product ID is valid
	 * @param p				Product object being searched
	 * @return	exists		boolean to verify if product exists in the database
	 * Written by Rick Stuart
	 */
	public boolean verifyProductInDatabase(Product p) {
		
		boolean exists = false;
		
		String productID = p.getProductID();
		
		//readerDAO = DAOFactory.getReaderDAO();
		Product existingProduct = readerDAO.lookupProduct(productID);
		
		if(existingProduct != null) {
			if(p.getProductID().equalsIgnoreCase(existingProduct.getProductID())) {
				if(p.getDescription().equalsIgnoreCase(existingProduct.getDescription())) {
					if(p.getYearMinimum().equalsIgnoreCase(existingProduct.getYearMinimum())) {
						if(p.getYearMaximum().equalsIgnoreCase(existingProduct.getYearMaximum())) {
							if(p.getMake().equalsIgnoreCase(existingProduct.getMake())) {
								if(p.getModel().equalsIgnoreCase(existingProduct.getModel())) {
									exists = true;
								}
							}
						}
					}
				}
			}
		}
		
		return exists;
	}
	
	/**
	 * This method is used with the RFID reader to write incoming products to the database
	 * @param product		Product object
	 * @param productID		String to specify product ID
	 * Written by Rick Stuart
	 */
	public void writeIncomingProduct(Product product, String productID) {
		
		int newQuantity = 0;
		
		String update = null;
		
		newQuantity = determineQuantityToAccept(product, productID);
		
		update = "UPDATE product "
				+ "SET quantity_in_stock = quantity_in_stock + " + newQuantity
				+ " WHERE product = " + productID + ";";
			
		Statement stmt = null;
			
		connObj = DatabaseWriter.getDBConnection();
									
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method determines how many items read by RFID reader to accept
	 * 		to inventory and calls associated methods to update database
	 * @param p					Product object entering inventory
	 * @param productID			String to hold product ID number
	 * @return	quantityToAccept	Int to specify how many of a product to accept to inventory
	 * Written by Rick Stuart
	 */
	public int determineQuantityToAccept(Product p, String productID) {
		
		int quantityToAccept = 0;
		int sum = 0;
		int quantityRejected = 0;	
		String quantityInStock = "";
		String quantityArriving = "";
		String maxQuantityInStock = "";
		boolean exceedsMax = false;
		
		//get old quantity in stock
		quantityInStock = readerDAO.obtainQuantityInStock(productID);
		
		//determine quantity arriving
		quantityArriving = p.getQuantityInStock();
		
		//determine sum
		sum = Integer.parseInt(quantityInStock) + Integer.parseInt(quantityArriving);
		
		//determine max to have
		maxQuantityInStock = getMaxQuantityInStock(productID);
		
		//determine if sum > maxQauntity
		if(sum > Integer.parseInt(maxQuantityInStock)) {
			exceedsMax = true;
		}
		
		//if yes, find quantity to accept
		if(exceedsMax) {
			
			// quantity to accept = max - in stock
			quantityToAccept = Integer.parseInt(maxQuantityInStock) - Integer.parseInt(quantityInStock);
			
			// quantity rejected = arriving - accepted
			quantityRejected = Integer.parseInt(quantityArriving) - quantityToAccept;
			
			// write rejected quantity to a file
			String reasonRejected = "Exceeds Max Quantity: ";
			rfidDAO.writeQuantityRejected(reasonRejected, p, productID, quantityRejected);
		}
		else {
			quantityToAccept = Integer.parseInt(quantityArriving);
			
		}
				
		
		
		//update purchases	
		if(quantityToAccept > 0) {
			String supplyPrice = "";
			String dollarValue = "";
			String quantityAccepted = Integer.toString(quantityToAccept);
			supplyPrice = p.getSupplierPrice();
			dollarValue = readerDAO.obtainDollarValue(quantityAccepted, supplyPrice);
			enterToAccountingPurchases(quantityAccepted, dollarValue, productID);
		}
		
		return quantityToAccept;
	}
	
	/**
	 * This helper method obtains the max number of items of that type that type 
	 * 			allowed in inventory
	 * @param productID			String to specify the product ID number
	 * @return	maxQuantityInStock	String to specify the max number allowed
	 * Written by Rick Stuart
	 */
	public String getMaxQuantityInStock(String productID) {
		
		String maxQuantityInStock = "";
		
		String query = null;
		ResultSet rs = null;
		
		query = "SELECT max_quantity_in_stock "
				+ "FROM product "
				+ "WHERE product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				maxQuantityInStock = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		return maxQuantityInStock;
	}
	
	/**
	 * This helper method checks inventory levels to see if re-ordering is necessary
	 * @param productID				String to identify the product ID being checked
	 * @return	reorder				boolean to specify if a re-order is required
	 * Written by Rick Stuart
	 */
	public Boolean checkReorderNecessity(String productID) {
		
		Boolean reorder = false;
		int quantityInStock = 0;
		int minQuantity = 0;
		String query = null;
		ResultSet rs = null;
		
		query = "SELECT quantity_in_stock, min_quantity_in_stock "
				+ "FROM product "
				+ "WHERE product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				quantityInStock = rs.getInt(1);
				minQuantity = rs.getInt(2);
				if(minQuantity >= quantityInStock) {
					reorder = true;
				}
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		return reorder;
	}
	
	/**
	 * This helper method updates the quantity in stock in product when a sale is made
	 * 			to maintain inventory levels
	 * @param productID			String to specify the product record ID number
	 * @param quantitySold		String to specify number of items sold
	 * Written by Rick Stuart
	 */
	public void updateQuantityInStock(String productID, String quantitySold) {
		
		String update = null;
		
		String currentQtyInStock = readerDAO.getQtyInStock(Integer.parseInt(productID));
		Integer newQtyInStock = Integer.parseInt(currentQtyInStock) - Integer.parseInt(quantitySold);
		
		update = "UPDATE product "
				+ "SET quantity_in_stock = " + newQtyInStock.toString()
				+ " WHERE product = " + productID + ";";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * This helper method updates quantity levels when new products arrive
	 * @param productID					String to specify product
	 * @param quantityPurchased			String to specify number of items to add to inventory
	 * Written by Rick Stuart
	 */
	public void updateQuantityInStockPurchase(String productID, String quantityPurchased) {
		
		String update = null;
		
		String currentQtyInStock = readerDAO.getQtyInStock(Integer.parseInt(productID));
		Integer newQtyInStock = Integer.parseInt(currentQtyInStock) + Integer.parseInt(quantityPurchased);
		
		update = "UPDATE product "
				+ "SET quantity_in_stock = " + newQtyInStock.toString()
				+ " WHERE product = " + productID + ";";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * This helper method creates a re-order when inventory level reach minimum
	 * @param productID				String to specify product to order
	 * Written by Rick Stuart
	 */
	public void createOrderForProduct(String productID) {
		
		String quantityToOrder = "";
		String quantityInStock = "";
		String maxQuantity = "";
		String dollarValue = "";
		String supplyPrice = "";
		int quantity = 0;
		
		maxQuantity = getMaxQuantityInStock(productID);
		quantityInStock = getQuantityInStock(productID);
		quantity = Integer.parseInt(maxQuantity) - Integer.parseInt(quantityInStock);
		quantityToOrder = Integer.toString(quantity);
		supplyPrice = obtainSupplyPrice(productID);
		
		dollarValue = readerDAO.obtainDollarValue(quantityToOrder, supplyPrice);
		
		// write the order to accounting_purchases
		enterToAccountingPurchases(quantityToOrder, dollarValue, productID);
		
		// write the order to products.txt
		System.out.println("Purchase made!");	
		rfidDAO.writeProductOrder(productID, quantityToOrder);
	}
	
	/**
	 * This helper method obtains the inventory levels of a product based on product ID
	 * @param productID				String to specify the product record ID number
	 * @return	quantityInStock		String to hold the stock level
	 * Written by Rick Stuart
	 */
	public String getQuantityInStock(String productID) {
		
		String quantityInStock = "";
		
		String query = null;
		ResultSet rs = null;
		
		query = "SELECT quantity_in_stock "
				+ "FROM product "
				+ "WHERE product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				quantityInStock = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		return quantityInStock;
	}
	
	/**
	 * This helper method obtains the cost to purchase a product from the supplier
	 * 			in order to write records to accounting purchases
	 * @param productID			String to identify the product queried
	 * @return	supplyPrice		String to specify the cost to obtain the product for resale
	 * Written by Rick Stuart
	 */
	public String obtainSupplyPrice(String productID) {
		
		String supplyPrice = "";
		
		String query = null;
		ResultSet rs = null;
		
		query = "SELECT supplier_price "
				+ "FROM product "
				+ "WHERE product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				supplyPrice = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		return supplyPrice;
	}
	
	/**
	 * This helper method updates an address record due to data entry errors or when
	 * 			an address changes for any object with an address
	 * @param addressID				String to specify the address record number to update
	 * @param streetAddress			String to hold the street address
	 * @param city					String to hold the city name
	 * @param state					String to hold the state name
	 * @param zipCode				String to hold the zip code
	 * @param unitNumber			String to hold the unit number
	 * Written by Rick Stuart
	 */
	public void editAddress(String addressID, String streetAddress, String city,
			String state, String zipCode, String unitNumber) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE address "
				+ "SET street_address = '" + streetAddress + "', "
				+ "city = '" + city + "', state = '" + state + "', zip_code = '"
				+ zipCode + "', unit_number = '" + unitNumber
				+ "' WHERE address_id = " + addressID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method updates a contact information record 
	 * 				due to changes or data entry errors
	 * @param contactID			String to hold the record ID number
	 * @param homePhone			String to hold the phone number
	 * @param cellPhone			String to hold the cell phone number
	 * @param email				String to hold the email address
	 * Written by Rick Stuart
	 */
	public void editContactInfo(String contactID, String homePhone, String cellPhone,
			String email) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE contact_info "
				+ "SET phone_number = '" + homePhone + "', "
				+ "cell_phone_number = '" + cellPhone + "', "
				+ "email_address = '" + email
				+ "' WHERE contact_info_id = " + contactID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method updates customer records due to data entry errors
	 * @param customerID		String to specify the record to update
	 * @param lastName			String to hold the customer's last name
	 * @param firstName			String to hold the customer's first name
	 * @param contactID			String to hold the contact info record ID number
	 * @param addressID			String to hold the address record ID number
	 * Written by Rick Stuart
	 */
	public void editCustomer(String customerID, String lastName, String firstName,
			String contactID, String addressID) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE customer "
				+ "SET last_name = '" + lastName + "', "
				+ "first_name = '" + firstName 
				+ "' WHERE customer_id = " + customerID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method updates an employee's record when changes occur or 
	 * 				due to data entry errors
	 * @param employeeID		String to specify the record to update
	 * @param lastName			String to hold employee's last name
	 * @param firstName			String to hold employee's first name
	 * @param contactID			String to specify the contact info record ID number
	 * @param addressID			String to specify the address record ID number
	 * Written by Rick Stuart
	 */
	public void editEmployee(String employeeID, String lastName, String firstName,
			String contactID, String addressID) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE employee "
				+ "SET last_name = '" + lastName + "', "
				+ "first_name = '" + firstName 
				+ "' WHERE employee_id = " + employeeID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method update a company record due to changes or data entry errors
	 * @param companyID			String to specify the company record to update
	 * @param companyName		String to hold the company name
	 * Written by Rick Stuart
	 */
	public void editCompany(String companyID, String companyName) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE company "
				+ "SET company_name = '" + companyName 
				+ "' WHERE company_id = " + companyID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	/**
	 * This helper method updates a supplier's information due to changes or data
	 * 				entry errors
	 * @param supplierID		String to specify the supplier record to update
	 * @param lastName			String to hold the supplier's last name
	 * @param firstName			String to hold the supplier's first name
	 * @param contactID			String to specify the contact info record ID number
	 * @param addressID			String to specify the address record ID number
	 * @param companyID			String to specify the company record ID number
	 * Written by Rick Stuart
	 */
	public void editSupplier(String supplierID, String lastName, String firstName,
			String contactID, String addressID, String companyID) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE supplier "
				+ "SET last_name = '" + lastName + "', "
				+ "first_name = '" + firstName 
				+ "', company_company_id = '" + companyID
				+ "' WHERE supplier_id = " + supplierID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
}