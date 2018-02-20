package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BusinessLayer.AccountingPurchases;
import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Employee;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;
import BusinessLayer.People;
import BusinessLayer.Product;
import BusinessLayer.Supplier;
import PresentationLayer.SalesTableModel;

// List imports here

/**
 * Class Name:		DatabaseReader
 * Description:		This class contains the methods called using data access objects to Read information
 * 					from the database, and also methods which call these methods directly.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */

public class DatabaseReader implements ReaderDAO {
	
	// Declare variables
	private static Connection connObj = null;	// Changed to static
	private ReadHelper readHelper = null;
	private ArrayList<Product> compatibleProducts = null;
	private ArrayList<Invoice> invoices = null;
	private ArrayList<InvoiceLineItem> lineItems = null;
	
	// Define methods
	/**
	 * This is the Constructor which is called from the DAOFactory class.
	 * This method also calls the ReadHelper Constructor to provide an instance of that class.
	 */
	public DatabaseReader() {
		readHelper = new ReadHelper();
		connObj = getDBConnection();
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
		
		System.out.println("DatabaseReader - Connection Established!");			
		DatabaseWriter.closeConnection(connObj);
			
		return connection;
	}
	
	public Customer obtainCustomerInformation(String lastName, String firstName,
			String phoneNumber) {
		
		Customer currentCustomer = null;
		String query = null;
		
		String customerID = "0";
		String addressID = "0";
		String contactInfoID = "0";
		//String lastName = "";	//receive as an argument
		//String firstName = "";
		String streetAddress = "";
		String city = "";
		String state = "";
		String zipCode = "";
		String unitNumber = "";
		//String phoneNumber = "";
		String cellPhone = "";
		String emailAddress = "";
		
		query = "select c.customer_id, c.contact_info_contact_info_id, "
				+ "c.Address_address_id, street_address, city, state, zip_code, unit_number, "
				+ "cell_phone_number, email_address " +
				"from customer c join address a " +
				"on c.Address_address_id = a.address_id " +
				"join contact_info ci " +
				"on c.contact_info_contact_info_id = ci.contact_info_id " +
				"where last_name = '" + lastName + "' and first_name = '" + firstName +
				"' and phone_number = '" + phoneNumber + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				customerID = rs.getString(1);
				contactInfoID = rs.getString(2);
				addressID = rs.getString(3);
				streetAddress = rs.getString(4);
				city = rs.getString(5);
				state = rs.getString(6);
				zipCode = rs.getString(7);
				unitNumber = rs.getString(8);
				cellPhone = rs.getString(9);
				emailAddress = rs.getString(10);
				
				currentCustomer = new Customer(customerID, lastName, firstName,
						contactInfoID, addressID, streetAddress, city, state, 
						zipCode, unitNumber, phoneNumber, cellPhone, emailAddress);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
				
		return currentCustomer;
	}
	
	public Employee obtainEmployeeInformation(String lastName, String firstName,
			String phoneNumber) {
		
		Employee currentEmployee = null;
		String query = null;
		
		String employeeID = "0";
		String addressID = "0";
		String contactInfoID = "0";
		String streetAddress = "";
		String city = "";
		String state = "";
		String zipCode = "";
		String unitNumber = "";
		String cellPhone = "";
		String emailAddress = "";
		
		query = "select e.employee_ID, e.contact_info_contact_info_ID, e.Address_address_id, " +
				"street_address, city, state, zip_code, unit_number, "
				+ "cell_phone_number, email_address " +
				"from employee e join address a " +
				"on e.Address_address_id = a.address_id " +
				"join contact_info ci " +
				"on e.contact_info_contact_info_id = ci.contact_info_id " +
				"where last_name = '" + lastName + "' and first_name = '" + firstName +
				"' and phone_number = '" + phoneNumber + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				employeeID = rs.getString(1);
				contactInfoID = rs.getString(2);
				addressID = rs.getString(3);
				streetAddress = rs.getString(4);
				city = rs.getString(5);
				state = rs.getString(6);
				zipCode = rs.getString(7);
				unitNumber = rs.getString(8);
				cellPhone = rs.getString(9);
				emailAddress = rs.getString(10);
		
				currentEmployee = new Employee(employeeID, lastName, firstName,
						contactInfoID, addressID, streetAddress, city, state, 
						zipCode, unitNumber, phoneNumber, cellPhone, emailAddress);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
		
		return currentEmployee;
	}
	
	public Supplier obtainSupplierInformation(String lastName, String firstName,
			String phoneNumber) {
		
		Supplier currentSupplier = null;
		String query = null;
		
		String supplierID = "0";
		String addressID = "0";
		String contactInfoID = "0";
		String companyID = "0";
		String streetAddress = "";
		String city = "";
		String state = "";
		String zipCode = "";
		String unitNumber = "";
		String cellPhone = "";
		String emailAddress = "";
		
		query = "select s.supplier_id, s.contact_info_contact_info_id, " +
				"s.Address_address_id, s.company_company_id, " +
				"street_address, city, state, zip_code, unit_number, " +
				"cell_phone_number, email_address " +
				"from supplier s join address a " +
				"on s.Address_address_id = a.address_id " +
				"join contact_info ci " +
				"on s.contact_info_contact_info_id = ci.contact_info_id " +
				"where last_name = '" + lastName + "' and first_name = '" + firstName +
				"' and phone_number = '" + phoneNumber + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				supplierID = rs.getString(1);
				contactInfoID = rs.getString(2);
				addressID = rs.getString(3);
				companyID = rs.getString(4);
				streetAddress = rs.getString(5);
				city = rs.getString(6);
				state = rs.getString(7);
				zipCode = rs.getString(8);
				unitNumber = rs.getString(9);
				cellPhone = rs.getString(10);
				emailAddress = rs.getString(11);
				
				currentSupplier = new Supplier(supplierID, companyID, lastName, firstName,
						contactInfoID, addressID, streetAddress, city, state, zipCode,
						unitNumber, phoneNumber, cellPhone, emailAddress);
				
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
		
		return currentSupplier;
	}
	
public static ArrayList<Customer> obtainCustomerList() {
		
		String query = "SELECT  * FROM customer, address, contact_info "
				+ "where customer.contact_info_contact_info_id = contact_info.contact_info_id "
				+ "and customer.Address_address_id = address.address_id";
		ArrayList<Customer> customers = new ArrayList<>();
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String customerID = rs.getString(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String contactInfoID = rs.getString(4);
				String addressID = rs.getString(5);
				String streetAddress = rs.getString(7);
				String city = rs.getString(8);
				String state = rs.getString(9);
				String zipCode = rs.getString(10);
				String unitNumber = rs.getString(11);
				String homePhone = rs.getString(13);
				String cellPhone = rs.getString(14);
				String emailAddress = rs.getString(15);
				
				Customer c = new Customer();
				c.setCustomerID(customerID);
				c.setLastName(lastName);
				c.setFirstName(firstName);
				c.setContactInfoID(contactInfoID);
				c.setAddressID(addressID);
				c.setStreetAddress(streetAddress);
				c.setCity(city);
				c.setState(state);
				c.setZipCode(zipCode);
				c.setUnitNumber(unitNumber);
				c.setPhoneNumber(homePhone);
				c.setCellPhoneNumber(cellPhone);
				c.setEmailAddress(emailAddress);
				
				customers.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
				
		return customers;
	}

public static ArrayList<Employee> obtainEmployeeList() {
	
	String query = "SELECT  * FROM employee, address, contact_info "
			+ "where employee.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and employee.Address_address_id = address.address_id";
	ArrayList<Employee> employees = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String employeeID = rs.getString(1);
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			String contactInfoID = rs.getString(4);
			String addressID = rs.getString(5);
			String streetAddress = rs.getString(7);
			String city = rs.getString(8);
			String state = rs.getString(9);
			String zipCode = rs.getString(10);
			String unitNumber = rs.getString(11);
			String homePhone = rs.getString(13);
			String cellPhone = rs.getString(14);
			String emailAddress = rs.getString(15);
			
			Employee e = new Employee();
			e.setEmployeeID(employeeID);
			e.setLastName(lastName);
			e.setFirstName(firstName);
			e.setContactInfoID(contactInfoID);
			e.setAddressID(addressID);
			e.setStreetAddress(streetAddress);
			e.setCity(city);
			e.setState(state);
			e.setZipCode(zipCode);
			e.setUnitNumber(unitNumber);
			e.setPhoneNumber(homePhone);
			e.setCellPhoneNumber(cellPhone);
			e.setEmailAddress(emailAddress);
			
			employees.add(e);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return employees;
}




public static ArrayList<Product> obtainProductList() {
	
	String query = "SELECT * FROM product";
	ArrayList<Product> products = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String productID = rs.getString(1);
			String description = rs.getString(2);
			String minYear = rs.getString(3);
			String maxYear = rs.getString(4);
			String make = rs.getString(5);
			String model = rs.getString(6);
			String supplierPrice = rs.getString(7);
			String sellPrice = rs.getString(8);
			String coreCharge = rs.getString(9);
			String compNumber = rs.getString(10);
			String companyID = rs.getString(11);
			String minStockQty = rs.getString(12);
			String maxStockQty = rs.getString(13);
			String warehouseLocation = rs.getString(14);
			String stockQty = rs.getString(15);
			
			Product p = new Product();
			p.setProductID(productID);
			p.setDescription(description);
			p.setYearMinimum(minYear);
			p.setYearMaximum(maxYear);
			p.setMake(make);
			p.setModel(model);
			p.setSupplierPrice(supplierPrice);
			p.setSellPrice(sellPrice);
			p.setCoreCharge(coreCharge);
			p.setCompatibilityNumber(compNumber);
			p.setCompanyID(companyID);
			p.setMinQuantityInStock(minStockQty);
			p.setMaxQuantityInStock(maxStockQty);
			p.setWarehouseLocation(warehouseLocation);
			p.setQuantityInStock(stockQty);
			
			products.add(p);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return products;
}

public static ArrayList<AccountingPurchases> obtainPurchaseList() {
	
	String query = "SELECT * FROM accounting_purchases";
	ArrayList<AccountingPurchases> purchases = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String purchaseID = rs.getString(1);
			String purchaseQty = rs.getString(2);
			String dollarValue = rs.getString(3);
			String productID = rs.getString(4);
			
			AccountingPurchases p = new AccountingPurchases();
			p.setAccountingPurchasesRecordID(purchaseID);
			p.setPurchasesQuantity(purchaseQty);
			p.setDollarValue(dollarValue);
			p.setProductID(productID);
			purchases.add(p);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return purchases;
}

public static ArrayList<Invoice> obtainInvoiceList() {
	
	String query = "SELECT * FROM invoice";
	ArrayList<Invoice> invoices = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String invoiceNumber = rs.getString(1);
			String date = rs.getString(2);
			String time = rs.getString(3);
			String customerID = rs.getString(4);
			String employeeID = rs.getString(5);
		
			
			Invoice i = new Invoice();
			i.setInvoiceNumber(invoiceNumber);
			i.setDate(date);
			i.setTime(time);
			i.setCustomerID(customerID);
			i.setEmployeeID(employeeID);
			
			
			invoices.add(i);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return invoices;
}



public static ArrayList<InvoiceLineItem> obtainInvoiceLineItemList(String invoiceNumberInput) {
	
		
	String query = "SELECT * FROM invoice_line_item where invoice_invoice_number = '" + invoiceNumberInput + "'";
	ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String invoiceLineNumber = rs.getString(1);
			String invoiceNumber = rs.getString(2);
			String quantityPurchased = rs.getString(3);
			String productID = rs.getString(4);
			
			InvoiceLineItem i = new InvoiceLineItem();
			i.setInvoiceLineNumber(invoiceLineNumber);
			i.setInvoiceNumber(invoiceNumber);
			i.setQuantityPurchased(quantityPurchased);
			i.setProductID(productID);
			
			
			invoiceLineItems.add(i);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return invoiceLineItems;
}

public static ArrayList<Supplier> obtainSupplierList() {

	
	String query = "SELECT * FROM supplier, address, contact_info, company "
			+ "where supplier.Address_address_id = address.address_id "
			+ "and supplier.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and supplier.company_company_id = company.company_id";
	
	ArrayList<Supplier> supplier = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String supplierID = rs.getString(1);
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			String contactInfoID = rs.getString(4);
			String addressID = rs.getString(5);
			String companyID = rs.getString(6);
			String streetAddress = rs.getString(8);
			String city = rs.getString(9);
			String state = rs.getString(10);
			String zipCode = rs.getString(11);
			String unitNumber = rs.getString(12);
			String homePhone = rs.getString(14);
			String cellPhone = rs.getString(15);
			String emailAddress = rs.getString(16);
			String companyName = rs.getString(20);
			
			Supplier s = new Supplier();
			
			s.setSupplierID(supplierID);
			s.setLastName(lastName);
			s.setFirstName(firstName);
			s.setContactInfoID(contactInfoID);
			s.setAddressID(addressID);
			s.setCompanyID(companyID);
			s.setStreetAddress(streetAddress);
			s.setCity(city);
			s.setState(state);
			s.setZipCode(zipCode);
			s.setUnitNumber(unitNumber);
			s.setPhoneNumber(homePhone);
			s.setCellPhoneNumber(cellPhone);
			s.setEmailAddress(emailAddress);
			s.setCompanyName(companyName);
			
			supplier.add(s);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return supplier;
}

	
	public Company obtainCompanyInformation(String companyName) {
		
		Company currentCompany = null;
		String query = null;
		
		String companyID = "0";
		String addressID = "0";
		String contactInfoID = "0";
		String streetAddress = "";
		String city = "";
		String state = "";
		String zipCode = "";
		String unitNumber = "";
		String phoneNumber = "";
		String cellPhone = "";
		String emailAddress = "";
		
		//NOTE:  this does NOT use REGEXP so name must be exact
		query = "SELECT c.company_id, c.Address_address_id, "
				+ "c.contact_info_contact_info_id, c.company_name, a.street_address, "
				+ "a.city, a.state, a.zip_code, "
				+ "a.unit_number, ci.phone_number, ci.cell_phone_number, " 
				+ "ci.email_address "
				+ "FROM company c JOIN address a "
				+ "ON c.Address_address_id = a.address_id "
				+ "JOIN contact_info ci "
				+ "ON c.contact_info_contact_info_id = ci.contact_info_id "
				+ "WHERE company_name = '" + companyName + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			System.out.println("In the try statement");
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In the While statement");
				companyID = rs.getString(1);
				addressID = rs.getString(2);
				contactInfoID = rs.getString(3);
				companyName = rs.getString(4);
				streetAddress = rs.getString(5);
				city = rs.getString(6);
				state = rs.getString(7);
				zipCode = rs.getString(8);
				unitNumber = rs.getString(9);
				phoneNumber = rs.getString(10);
				cellPhone = rs.getString(11);
				emailAddress = rs.getString(12);
				
				System.out.println("About to create Company Object");
				currentCompany = new Company(companyID, companyName,
						streetAddress, city, state, zipCode, unitNumber,
						phoneNumber, cellPhone, emailAddress);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
		
		return currentCompany;
	}
	
	public Product lookupProduct(String productID) {
		
		Product existingProduct = null;
		String query = null;
		//String productID = "";
		String description = "";
		String yearMin = "";
		String yearMax = "";
		String make = "";
		String model = "";
		String supplierPrice = "";
		String sellPrice = "";
		String coreCharge = "";
		String compatibilityNumber  = "";
		String companyID = "";
		String minStockQuantity = "";
		String maxStockQuantity = "";
		String location = "";
		String stockQuantity = "";
		
		query = "SELECT description, year_minimum, year_maximum, make, "
				+ "model, supplier_price, sell_price, core_charge, "
				+ "compatibility_number, company_company_id, "
				+ "min_quantity_in_stock, max_quantity_in_stock, "
				+ "warehouse_location, quantity_in_stock "
				+ "FROM product "
				+ "WHERE product = " + productID + ";";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			System.out.println("In the try statement");
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In the While statement");
				//productID = rs.getString(1);
				description = rs.getString(1);
				yearMin = rs.getString(2);
				yearMax = rs.getString(3);
				make = rs.getString(4);
				model = rs.getString(5);
				supplierPrice = rs.getString(6);
				sellPrice = rs.getString(7);
				coreCharge = rs.getString(8);
				compatibilityNumber = rs.getString(9);
				companyID = rs.getString(10);
				minStockQuantity = rs.getString(11);
				maxStockQuantity = rs.getString(12);
				location = rs.getString(13);
				stockQuantity = rs.getString(14);
		
				existingProduct = new Product(productID, description, yearMin,
						yearMax, make, model, supplierPrice, sellPrice, coreCharge,
						compatibilityNumber, companyID, minStockQuantity,
						maxStockQuantity, location, stockQuantity);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
		
		return existingProduct;
	}
	
	public Product lookupProduct(String description, String year, String make,
			String model) {
		
		String query = null;
		String productID = "";
		Product currentProduct = null;
		//String description = "";
		String yearMin = "";
		String yearMax = "";
		//make = "";
		//model = "";
		String supplierPrice = "";
		String sellPrice = "";
		String coreCharge = "";
		String compatibilityNumber  = "";
		String companyID = "";
		String minStockQuantity = "";
		String maxStockQuantity = "";
		String location = "";
		String stockQuantity = "";
		
		query = "SELECT product, year_minimum, year_maximum, supplier_price, "
				+ "sell_price, core_charge, compatibility_number, "
				+ "company_company_id, min_quantity_in_stock, "
				+ "max_quantity_in_stock, warehouse_location, quantity_in_stock "
				+ "FROM product "
				+ "WHERE description = '" + description + "' "
				+ "AND year_minimum <= '" + year + "' "
				+ "AND year_maximum >= '" + year + "' "
				+ "AND make = '" + make + "' "
				+ "AND model = '" + model + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			System.out.println("In the try statement");
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In the While statement");
				productID = rs.getString(1);
				yearMin = rs.getString(2);
				yearMax = rs.getString(3);
				supplierPrice = rs.getString(4);
				sellPrice = rs.getString(5);
				coreCharge = rs.getString(6);
				compatibilityNumber = rs.getString(7);
				companyID = rs.getString(8);
				minStockQuantity = rs.getString(9);
				maxStockQuantity = rs.getString(10);
				location = rs.getString(11);
				stockQuantity = rs.getString(12);
				
				currentProduct = new Product(productID, description, yearMin,
						yearMax, make, model, supplierPrice, sellPrice, coreCharge,
						compatibilityNumber, companyID, minStockQuantity,
						maxStockQuantity, location, stockQuantity);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
				
		
		return currentProduct;
	}
	
	public ArrayList<Product> getCompatibleProducts(String compatibilityNumber) {
		
		compatibleProducts = new ArrayList<Product>();
		String query = null;
		
		String productID = "";
		String description = "";
		String yearMin = "";
		String yearMax = "";
		String make = "";
		String model = "";
		String supplierPrice = "";
		String sellPrice = "";
		String coreCharge = "";
		String companyID = "";
		String minStockQuantity = "";
		String maxStockQuantity = "";
		String location = "";
		String stockQuantity = "";
		
		query = "SELECT product, description, year_minimum, year_maximum, "
				+ "make, model, supplier_price, sell_price, core_charge, "
				+ "compatibility_number, company_company_id, "
				+ "min_quantity_in_stock, max_quantity_in_stock, "
				+ "warehouse_location, quantity_in_stock "
				+ "FROM product "
				+ "WHERE compatibility_number = '" + compatibilityNumber + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			System.out.println("In the try statement");
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In the While statement");
				productID = rs.getString(1);
				description = rs.getString(2);
				yearMin = rs.getString(3);
				yearMax = rs.getString(4);
				make = rs.getString(5);
				model = rs.getString(6);
				supplierPrice = rs.getString(7);
				sellPrice = rs.getString(8);
				coreCharge = rs.getString(9);
				compatibilityNumber = rs.getString(10);
				companyID = rs.getString(11);
				minStockQuantity = rs.getString(12);
				maxStockQuantity = rs.getString(13);
				location = rs.getString(14);
				stockQuantity = rs.getString(15);
				
				Product nextProduct = new Product(productID, description,
						yearMin, yearMax, make, model, supplierPrice,
						sellPrice, coreCharge, compatibilityNumber,
						companyID, minStockQuantity, maxStockQuantity,
						location, stockQuantity);
				
				compatibleProducts.add(nextProduct);
				
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
		
		return compatibleProducts;
	}
	
	public ArrayList<Invoice> getInvoices(String customerID) {
		
		invoices = new ArrayList<Invoice>();
		String query = null;
		String invoiceNumber = "";
		String date = "";
		String time = "";
		String employeeID = "";
		
		query = "SELECT invoice_number, date, time, employee_employee_id "
				+ "FROM invoice "
				+ "WHERE customer_customer_id = '" + customerID + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			System.out.println("In the invoices try statement");
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In the While statement");
				invoiceNumber = rs.getString(1);
				date = rs.getString(2);
				time = rs.getString(3);
				employeeID = rs.getString(4);
				
				Invoice nextInvoice = new Invoice(invoiceNumber, date, time,
						customerID, employeeID);
		
				invoices.add(nextInvoice);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
		
		return invoices;
	}
	
	public ArrayList<InvoiceLineItem> getInvoiceLineItems(String invoiceNumber) {
		
		lineItems = new ArrayList<InvoiceLineItem>();
		String query = null;
		String lineNumber = "";
		String quantityPurchased = "";
		String productID = "";
		
		query = "SELECT invoice_line_number, quantity_purchased, product_product "
				+ "FROM invoice_line_item "
				+ "WHERE invoice_invoice_number = " + invoiceNumber + ";";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
						
		try {	
			System.out.println("In the invoices try statement");
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In the While statement");
				lineNumber = rs.getString(1);
				quantityPurchased = rs.getString(2);
				productID = rs.getString(3);
				
				InvoiceLineItem nextLineItem = new InvoiceLineItem(lineNumber,
						invoiceNumber, quantityPurchased, productID);
				
				lineItems.add(nextLineItem);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
				
				
		return lineItems;
	}
	
	public String getQuantityInStock(String productID) {
		
		String quantity = "";
		String query = null;
		
		query = "SELECT quantity_in_stock "
				+ "FROM product "
				+ "WHERE product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj =  DatabaseWriter.getDBConnection();
		System.out.println("Product ID: " + productID);
		
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("In the quantity while statement");
				quantity = rs.getString(1);
			}
			System.out.println("quantity: " + quantity);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
			
		System.out.println("Quantity: " + quantity);
		
		return quantity;
	}


public static String obtainPassword(String username) {
	
	String query = "SELECT password FROM login WHERE username = '" + username + "'";
	String password = "";
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			password = rs.getString(1);
		}
		
	}
	
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
	
	return password;
}

public static ArrayList<Customer> obtainCustomerFilter(String column, String search)
{
	String query = "SELECT  * FROM customer, address, contact_info "
			+ "where customer.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and customer.Address_address_id = address.address_id and " + column + " = '" + search + "'";
	
	ArrayList<Customer> customers = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String customerID = rs.getString(1);
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			String contactInfoID = rs.getString(4);
			String addressID = rs.getString(5);
			String streetAddress = rs.getString(7);
			String city = rs.getString(8);
			String state = rs.getString(9);
			String zipCode = rs.getString(10);
			String unitNumber = rs.getString(11);
			String homePhone = rs.getString(13);
			String cellPhone = rs.getString(14);
			String emailAddress = rs.getString(15);
			
			Customer c = new Customer();
			c.setCustomerID(customerID);
			c.setLastName(lastName);
			c.setFirstName(firstName);
			c.setContactInfoID(contactInfoID);
			c.setAddressID(addressID);
			c.setStreetAddress(streetAddress);
			c.setCity(city);
			c.setState(state);
			c.setZipCode(zipCode);
			c.setUnitNumber(unitNumber);
			c.setPhoneNumber(homePhone);
			c.setCellPhoneNumber(cellPhone);
			c.setEmailAddress(emailAddress);
			
			customers.add(c);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return customers;
	
}

public static ArrayList<Employee> obtainEmployeeFilter(String column, String search)
{
	String query = "SELECT  * FROM employee, address, contact_info "
			+ "where employee.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and employee.Address_address_id = address.address_id and " + column + " = '" + search + "'";
	
	ArrayList<Employee> employees = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String employeeID = rs.getString(1);
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			String contactInfoID = rs.getString(4);
			String addressID = rs.getString(5);
			String streetAddress = rs.getString(7);
			String city = rs.getString(8);
			String state = rs.getString(9);
			String zipCode = rs.getString(10);
			String unitNumber = rs.getString(11);
			String homePhone = rs.getString(13);
			String cellPhone = rs.getString(14);
			String emailAddress = rs.getString(15);
			
			Employee e = new Employee();
			e.setEmployeeID(employeeID);
			e.setLastName(lastName);
			e.setFirstName(firstName);
			e.setContactInfoID(contactInfoID);
			e.setAddressID(addressID);
			e.setStreetAddress(streetAddress);
			e.setCity(city);
			e.setState(state);
			e.setZipCode(zipCode);
			e.setUnitNumber(unitNumber);
			e.setPhoneNumber(homePhone);
			e.setCellPhoneNumber(cellPhone);
			e.setEmailAddress(emailAddress);
			
			employees.add(e);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return employees;
	
}

public static ArrayList<AccountingPurchases> obtainPurchaseFilter(String column, String search)
{
	String query = "SELECT * FROM accounting_purchases where " + column + " = '" + search + "'";
	
	ArrayList<AccountingPurchases> purchases = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String purchaseID = rs.getString(1);
			String purchaseQty = rs.getString(2);
			String dollarValue = rs.getString(3);
			String productID = rs.getString(4);
			
			AccountingPurchases p = new AccountingPurchases();
			p.setAccountingPurchasesRecordID(purchaseID);
			p.setPurchasesQuantity(purchaseQty);
			p.setDollarValue(dollarValue);
			p.setProductID(productID);
			purchases.add(p);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return purchases;
	
}


public static ArrayList<Product> obtainProductFilter(String column, String search) {
	
	String query = "SELECT * FROM product where " + column + " = '" + search + "'";
	ArrayList<Product> products = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String productID = rs.getString(1);
			String description = rs.getString(2);
			String minYear = rs.getString(3);
			String maxYear = rs.getString(4);
			String make = rs.getString(5);
			String model = rs.getString(6);
			String supplierPrice = rs.getString(7);
			String sellPrice = rs.getString(8);
			String coreCharge = rs.getString(9);
			String compNumber = rs.getString(10);
			String companyID = rs.getString(11);
			String minStockQty = rs.getString(12);
			String maxStockQty = rs.getString(13);
			String warehouseLocation = rs.getString(14);
			String stockQty = rs.getString(15);
			
			Product p = new Product();
			p.setProductID(productID);
			p.setDescription(description);
			p.setYearMinimum(minYear);
			p.setYearMaximum(maxYear);
			p.setMake(make);
			p.setModel(model);
			p.setSupplierPrice(supplierPrice);
			p.setSellPrice(sellPrice);
			p.setCoreCharge(coreCharge);
			p.setCompatibilityNumber(compNumber);
			p.setCompanyID(companyID);
			p.setMinQuantityInStock(minStockQty);
			p.setMaxQuantityInStock(maxStockQty);
			p.setWarehouseLocation(warehouseLocation);
			p.setQuantityInStock(stockQty);
			
			products.add(p);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return products;
}

public static ArrayList<Supplier> obtainSupplierFilter(String column, String search) {

	
	String query = "SELECT * FROM supplier, address, contact_info, company "
			+ "where supplier.Address_address_id = address.address_id "
			+ "and supplier.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and supplier.company_company_id = company.company_id and " + column + " = '" + search + "'";
		
	
	ArrayList<Supplier> supplier = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String supplierID = rs.getString(1);
			String lastName = rs.getString(2);
			String firstName = rs.getString(3);
			String contactInfoID = rs.getString(4);
			String addressID = rs.getString(5);
			String companyID = rs.getString(6);
			String streetAddress = rs.getString(8);
			String city = rs.getString(9);
			String state = rs.getString(10);
			String zipCode = rs.getString(11);
			String unitNumber = rs.getString(12);
			String homePhone = rs.getString(14);
			String cellPhone = rs.getString(15);
			String emailAddress = rs.getString(16);
			String companyName = rs.getString(20);
			
			Supplier s = new Supplier();
			
			s.setSupplierID(supplierID);
			s.setLastName(lastName);
			s.setFirstName(firstName);
			s.setContactInfoID(contactInfoID);
			s.setAddressID(addressID);
			s.setCompanyID(companyID);
			s.setStreetAddress(streetAddress);
			s.setCity(city);
			s.setState(state);
			s.setZipCode(zipCode);
			s.setUnitNumber(unitNumber);
			s.setPhoneNumber(homePhone);
			s.setCellPhoneNumber(cellPhone);
			s.setEmailAddress(emailAddress);
			s.setCompanyName(companyName);
			
			supplier.add(s);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return supplier;
}

public static ArrayList<InvoiceLineItem> obtainInvoiceLineItemFilter(String invoiceNumberInput,
		String column, String search) {
	
	
	String query = "SELECT * FROM invoice_line_item where invoice_invoice_number "
			+ "= '" + invoiceNumberInput  + "' and " + column + " = '" + search + "'";
	ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String invoiceLineNumber = rs.getString(1);
			String invoiceNumber = rs.getString(2);
			String quantityPurchased = rs.getString(3);
			String productID = rs.getString(4);
			
			InvoiceLineItem i = new InvoiceLineItem();
			i.setInvoiceLineNumber(invoiceLineNumber);
			i.setInvoiceNumber(invoiceNumber);
			i.setQuantityPurchased(quantityPurchased);
			i.setProductID(productID);
			
			
			invoiceLineItems.add(i);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return invoiceLineItems;
}

public static ArrayList<Invoice> obtainInvoiceFilter(String column, String search) {
	
	String query = "SELECT * FROM invoice where " + column + " = '" + search + "'";
	ArrayList<Invoice> invoices = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String invoiceNumber = rs.getString(1);
			String date = rs.getString(2);
			String time = rs.getString(3);
			String customerID = rs.getString(4);
			String employeeID = rs.getString(5);
		
			
			Invoice i = new Invoice();
			i.setInvoiceNumber(invoiceNumber);
			i.setDate(date);
			i.setTime(time);
			i.setCustomerID(customerID);
			i.setEmployeeID(employeeID);
			
			
			invoices.add(i);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return invoices;
}


public String obtainInvoiceNumber(String input) {
	
	
	String query = "SELECT invoice_number FROM invoice where invoic_number  = '" + input +"'";
			
	String invoiceNumber = "";
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			invoiceNumber = rs.getString(1);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return invoiceNumber;
}

}
