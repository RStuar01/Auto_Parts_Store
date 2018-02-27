package DatabaseLayer;

// List imports
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BusinessLayer.Product;

/**
 * Class Name:		WriteHelper
 * Description:		This class contains the methods called from the DatabaseWriter class
 * 					to support the methods used there.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
public class WriteHelper {
	
	private Connection connObj = null;
	private static ReaderDAO readerDAO;
	
	/**
	 * Constructor called from the DatabaseWriter class to create an instance of this class.
	 */
	public WriteHelper() {
	}

	/**
	 * This method writes the information for a new address record to the database.
	 * @param streetAddress			String variable for the street address.
	 * @param city					String variable for the city.
	 * @param state					String variable for the state.
	 * @param zipCode				String variable for the zip code.
	 * @param unitNumber			String variable for the unit number.
	 */
	public void writeAddressInformation(String streetAddress, String city, String state, String zipCode,
			String unitNumber) {
	System.out.println();
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
	 * This method uses supplied address information to obtain the ID number for an address which was just
	 * written to the database.
	 * @param stAddress				String variable for the street address.
	 * @param city					String variable for the city.
	 * @param state					String variable for the state.
	 * @param zipCode				String variable for the zip code.
	 * @param unitNumber			String variable for the unit number.
	 * @return addressID			String variable for the address ID number.
	 */
	public String obtainNewAddressID(String stAddress, String city, String state, String zipCode, String unitNumber) {
		
		String addressID = null;
		String query = null;
		ResultSet rs = null;
		
		if(unitNumber.equals("NULL")) {
			query = "select address_id " +
					"from address " +
					"where street_address = '" + stAddress + "' and city = '" + city + "' and state = '" + state +
					"' and zip_code = '" + zipCode + "' and unit_number  is null';";
		}
		else {
			query = "select address_id from address where street_address = '" + stAddress +
					"' and city = '" + city + "' and state = '" + state +
					"' and zip_code = '" + zipCode +
					"' and unit_number = '" + unitNumber + "';";
		}
		
		Statement stmt = null;
								
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				addressID = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);		
				
		return addressID;
	}
	
	/**
	 * This method writes a new contact information record to the database.
	 * @param phoneNumber			String variable for the phone number.
	 * @param cellPhone				String variable for the cell phone number.
	 * @param emailAddress			String variable for the email address.
	 */
	public void writeContactInformation(String phoneNumber, String cellPhone, String emailAddress) {
		
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
	 * This method uses supplied information to locate and obtain the contact information ID number
	 * which corresponds to the information just written to the database.
	 * @param phoneNumber			String variable for the phone number.
	 * @param cellPhone				String variable for the cell phone number.
	 * @param emailAddress			String variable for the email address.
	 * @return contactInfoID		String variable for the contact info ID number.
	 */
	public String obtainNewContactInformationID(String phoneNumber, String cellPhone, String emailAddress) {
		
		String contactInfoID = null;
		String query = null;
		ResultSet rs = null;
		
		query = "select contact_info_id " +
				"from contact_info " +
				"where phone_number = '" + phoneNumber + "' and cell_phone_number = '" + cellPhone +
				"' and email_address = '" + emailAddress + "';";
		
		Statement stmt = null;
										
		connObj = DatabaseWriter.getDBConnection();
										
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				contactInfoID = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
						
		DatabaseWriter.closeConnection(connObj);		
		
		return contactInfoID;
	}
	
	/**
	 * This method write a new Customer record to the database.
	 * @param addressID				String variable for the address ID number.
	 * @param contactInfoID			String variable for the contact information ID number.
	 * @param lastName				String variable for the Customers last name.
	 * @param firstName				String variable for the Customers first name.
	 */
	public void writeCustomerInformation(String addressID, String contactInfoID, String lastName, String firstName) {
		
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
	 * This method writes the information for a new Employee record to the database.
	 * @param addressID				String variable for the address ID number.
	 * @param contactInfoID			String variable for the contact information ID number.
	 * @param lastName				String variable for the Employees' last name.
	 * @param firstName				String variable for the Employees' first name.
	 */
	public void writeEmployeeInformation(String addressID, String contactInfoID, String lastName, String firstName) {
		
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
	
	public void writeSupplierInformation(String addressID, String contactInfoID, String lastName,
			String firstName, String companyID) {
		
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
	
	public void writeCompanyInformation(String addressID, String contactInfoID, String companyName) {
		
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
	
	public String obtainProductID(String description, String yearMin, String yearMax,
			String make, String model, String supplyPrice, String sellPrice,
			String coreCharge, String compatNum, String companyID, String minStockQuantity,
			String maxStockQuantity, String location, String quantityInStock) {
		
		String productID = null;
		String query = null;
		ResultSet rs = null;
		
		query = "select product " +
				"from product " +
				"where description = '" + description + "' and year_minimum = '" + yearMin +
				"' and year_maximum = '" + yearMax + "' and make = '" + make + "' and model = '" +
				model + "' and supplier_price = '" + supplyPrice + "' and sell_price = '" +
				sellPrice + "' and core_charge = '" + coreCharge + "' and compatibility_number = '" +
				compatNum + "' and company_company_id = '" + companyID + 
				"' and min_quantity_in_stock = '" + minStockQuantity + "' and max_quantity_in_stock = '" +
				maxStockQuantity + "' and warehouse_location = '" + location + "' and quantity_in_stock = '" +
				quantityInStock + "';";
		
		Statement stmt = null;
								
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				productID = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		//System.out.println("product ID: " + productID);
		
		return productID;
	}
	
	public String obtainDollarValue(String quantityInStock, String supplyPrice) {
		
		double total = 0;
		String dollarValue = "";
		
		double purchasePrice = Double.parseDouble(supplyPrice);
		int purchasedQuantity = Integer.parseInt(quantityInStock);
		
		total = purchasePrice * purchasedQuantity;
		
		dollarValue = String.valueOf(total);
		
		return dollarValue;
	}
	
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
	
	public String obtainNewInvoiceNumber(String date, String time, String customerID,
			String employeeID) {
		
		String invoiceNum = "";
		String query = null;
		ResultSet rs = null;
		
		query = "select invoice_number " +
				"from invoice " +
				"where date = '" + date + "' and time = '" + time +
				"' and customer_customer_id = '" + customerID + "' and employee_employee_id = '" +
				employeeID + "';";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				invoiceNum = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		return invoiceNum;
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
	
	public String obtainLineItemID(String invoiceID, String quantityPurchased, String productID) {
		
		String lineID = "";
		String query = null;
		ResultSet rs = null;
		
		query = "select invoice_line_number " +
				"from invoice_line_item " +
				"where invoice_invoice_number = '" + invoiceID + "' and quantity_purchased = '" +
				quantityPurchased + "' and product_product = '" + productID + "';";
		
		Statement stmt = null;  
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				lineID = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		
		return lineID;
	}
	
	public String obtainSellPrice(String productID) {
		
		String price = "";
		String query = null;
		ResultSet rs = null;
		
		query = "select sell_price " +
				"from product " +
				"where product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				price = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		return price;
	}
	
	public String obtainSalesTax(String dollarValue) {
		
		String tax = "";
		Double salesTax = 0.0;
		
		double cost = Double.parseDouble(dollarValue);
		salesTax = cost * 0.075;
		//System.out.println("Unrounded: " + salesTax);
		salesTax = (double) Math.round((salesTax * 100) + 0.5) / 100;
		//System.out.println("Rounded up: " + salesTax);
		tax = String.valueOf(salesTax);
		
		return tax;
	}
	
	public void enterAccountingSales(String lineID, String quantityPurchased,
			String productID, String dollarValue, String salesTax) {
		
		String newAccountingSaleUpdate = null;
		
		//System.out.println("In accounting sales");
		
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
	
	public boolean verifyProductInDatabase(Product p) {
		
		boolean exists = false;
		
		String productID = p.getProductID();
		
		//System.out.println("In verifyProducts");
		readerDAO = DAOFactory.getReaderDAO();
		Product existingProduct = readerDAO.lookupProduct(productID);
		
		
		
		if(existingProduct != null) {
			if(p.getProductID().equalsIgnoreCase(existingProduct.getProductID())) {
				if(p.getDescription().equalsIgnoreCase(existingProduct.getDescription())) {
					if(p.getYearMinimum().equalsIgnoreCase(existingProduct.getYearMinimum())) {
						if(p.getYearMaximum().equalsIgnoreCase(existingProduct.getYearMaximum())) {
							if(p.getMake().equalsIgnoreCase(existingProduct.getMake())) {
								if(p.getModel().equalsIgnoreCase(existingProduct.getModel())) {
									exists = true;
									//System.out.println("Boolean set");
								}
							}
						}
					}
				}
			}
		}
		else {
			//System.out.println("writeHelper.VerifyProductInDatabase");
			//System.out.println("Product is null - does not match existing product!");
		}
		
		return exists;
	}
	
	public void writeIncomingProduct(Product product, String productID) {
		
		String quantityArriving = "";
		String oldQuantity = "";
		String maxQuantityInStock = "";
		String supplyPrice = "";
		String dollarValue = "";
		int newQuantity = 0;
		int maxQuantity = 0;
		int quantityAccepted = 0;
		int quantityRejected = 0;
		
		//System.out.println("Writing product");
		String update = null;
		 
		//System.out.println("Quantity arriving: " + product.getQuantityInStock());
		oldQuantity = readerDAO.obtainQuantityInStock(productID);
		//System.out.println("oldQuantity = " + oldQuantity);
		
		quantityArriving = product.getQuantityInStock();
		productID = product.getProductID();
		newQuantity = Integer.parseInt(oldQuantity) + Integer.parseInt(quantityArriving);
		
		// Need to verify that does not exceed maxQuantity
		maxQuantityInStock = getMaxQuantityInStock(productID);
		maxQuantity = Integer.parseInt(maxQuantityInStock);
		
		if(newQuantity > maxQuantity) {
			//System.out.println("Quantity exceeds max quantity!");
			//System.out.println("Maximum quantity allowed: " + maxQuantity);
			quantityAccepted = maxQuantity - Integer.parseInt(oldQuantity);
			newQuantity = quantityAccepted + Integer.parseInt(oldQuantity);
			quantityRejected = Integer.parseInt(quantityArriving) - quantityAccepted;
			
			//System.out.println("quantity accepted: " + quantityAccepted);
			//System.out.println("quantity rejected: " + quantityRejected);
			
			
		}
		
			update = "UPDATE product "
					+ "SET quantity_in_stock = " + newQuantity
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
			
			// copied from line 167 DatabaseWriter // strings
			String quantityReceived = Integer.toString(quantityAccepted);
			supplyPrice = product.getSupplierPrice();
			//System.out.println("SupplierPrice: " + supplyPrice);
			dollarValue = obtainDollarValue(quantityReceived, supplyPrice);
			//System.out.println("Dollar Value: " + dollarValue);
			enterToAccountingPurchases (quantityReceived, dollarValue, productID);
				
		DatabaseWriter.closeConnection(connObj);
	}
	
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
				//System.out.println("In Stock: " + quantityInStock);
				//System.out.println("Max: " + minQuantity);
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
	
	public void updateQuantityInStock(String productID, String quantitySold) {
		
		String update = null;
		
		String currentQtyInStock = DatabaseReader.getQtyInStock(Integer.parseInt(productID));
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
	
	public void updateQuantityInStockPurchase(String productID, String quantityPurchased) {
		
		String update = null;
		
		String currentQtyInStock = DatabaseReader.getQtyInStock(Integer.parseInt(productID));
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
		
		dollarValue = obtainDollarValue(quantityToOrder, supplyPrice);
		
		// write the order to accounting_purchases
		enterToAccountingPurchases(quantityToOrder, dollarValue, productID);
		System.out.println("Purchase made!");
		
	}
	
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
	
	public String obtainInvoiceLineID(String invoiceNumber, String purchasedQuantity,
			String productID) {
		
		String lineNumber = "";
		
		String query = null;
		ResultSet rs = null;
		
		query = "SELECT invoice_line_number "
				+ "FROM invoice_line_item "
				+ "WHERE invoice_invoice_number = '" + invoiceNumber 
				+ "' AND quantity_purchased = '" + purchasedQuantity 
				+ "' AND product_product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				lineNumber = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
		
		return lineNumber;
	}
	
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
	
	public void editCustomer(String customerID, String lastName, String firstName,
			String contactID, String addressID) {
		
		String update = null;
		Statement stmt = null;
		
		
		//System.out.println("CustomerID " + customerID);
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
	
	public void editEmployee(String employeeID, String lastName, String firstName,
			String contactID, String addressID) {
		
		String update = null;
		Statement stmt = null;
		
		
		//System.out.println("EmployeeID " + employeeID);
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
	
	public void editSupplier(String supplierID, String lastName, String firstName,
			String contactID, String addressID, String companyID) {
		
		String update = null;
		Statement stmt = null;
		
		
		//System.out.println("SupplierID " + supplierID);
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