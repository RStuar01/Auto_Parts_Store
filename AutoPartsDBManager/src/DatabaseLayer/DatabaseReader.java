/**
 * Class Name:		DatabaseReader
 * Description:		This class contains the methods called using data access objects to Read information
 * 					from the database.
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

import BusinessLayer.AccountingPurchases;
import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Employee;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;
import BusinessLayer.Supplier;

/**
 * This class holds the methods to read data from the database.
 * Written by Rick Stuart
 */
public class DatabaseReader implements ReaderDAO {
	
	// Declare variables
	private static Connection connObj = null;	
	
	// Define methods
	/**
	 * This is the Constructor which is called from the DAOFactory class.
	 * Written by Rick Stuart
	 */
	public DatabaseReader() {
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
		
		closeConnection(connObj);
		return connection;
	}
	
	/**
	 * This method obtains quantity of a product in stock based on the product ID
	 * @param productID			String to specify the product
	 * @return qtyInStock		String to indicate the number of items in stock
	 * Written by Rick Stuart
	 */
	public String getQtyInStock(Integer productID) {
	
		String query = "SELECT quantity_in_stock from product where product = " + productID;
	
		String qtyInStock = "";
	
		Statement stmt = null;
	
		connObj = getDBConnection();
	
		try {
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				qtyInStock = rs.getString(1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
	
		return qtyInStock;
	}
	
	/**
	 * This method obtains information for an existing product in the database
	 * 			based on the productID
	 * @param	productID			String to identify the product
	 * @return	existingProduct		a product object
	 * Written by Rick Stuart
	 */
	public Product lookupProduct(String productID) {
		
		Product existingProduct = null;
		String query = null;
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
		
		connObj =  getDBConnection();
						
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
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
		
		closeConnection(connObj);
		
		return existingProduct;
	}
	
	/**
	 * This method obtains an arrayList of company objects
	 * @return	company		ArrayList of company objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Company> obtainCompanyList() {
	
		String query = "select * from company, address, contact_info "
			+ "where Address_address_id = address.address_id "
			+ "and contact_info_contact_info_id = contact_info.contact_info_id";
	
		ArrayList<Company> company = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String companyID = rs.getString(1);
				String addressID = rs.getString(2);
				String contactID = rs.getString(3);
				String companyName = rs.getString(4);
				String streetAddress = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				String zipCode = rs.getString(9);
				String unitNumber = rs.getString(10);
				String homePhone = rs.getString(12);
				String cellPhone = rs.getString(13);
				String emailAddress = rs.getString(14);
			
				Company c = new Company();
				c.setCompanyID(companyID);
				c.setAddressID(addressID);
				c.setContactInfoID(contactID);
				c.setCompanyName(companyName);
				c.setStreetAddres(streetAddress);
				c.setCity(city);
				c.setState(state);
				c.setZipCode(zipCode);
				c.setUnitNumber(unitNumber);
				c.setPhoneNumber(homePhone);
				c.setCellPhoneNumber(cellPhone);
				c.setEmailAddress(emailAddress);
				
				company.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
		
		return company;
	}
	
	/**
	 * This method obtains an arrayList of customer objects
	 * @return customers	ArrayList of customer objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Customer> obtainCustomerList() {
		
		String query = "SELECT  * FROM customer, address, contact_info "
				+ "where customer.contact_info_contact_info_id = contact_info.contact_info_id "
				+ "and customer.Address_address_id = address.address_id";
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		Statement stmt = null;
		
		connObj =  getDBConnection();
						
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
		
		closeConnection(connObj);
				
		return customers;
	}
	
	/**
	 * This method obtains an arrayList of employee objects
	 * @return	employees	ArrayList of employee objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Employee> obtainEmployeeList() {
	
		String query = "SELECT  * FROM employee, address, contact_info "
			+ "where employee.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and employee.Address_address_id = address.address_id";
		ArrayList<Employee> employees = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return employees;
	}
	
	/**
	 * This method obtains an arrayList of invoice objects
	 * @return invoices		ArrayList of invoice objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Invoice> obtainInvoiceList() {
	
		String query = "SELECT * FROM invoice";
		ArrayList<Invoice> invoices = new ArrayList<>();
		
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return invoices;
	}

	/**
	 * This method obtains an arrayList of lineItem objects
	 * @return	invoiceLineItems	ArrayList of lineItem objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemList(String invoiceNumberInput) {
	
		
		String query = "SELECT * FROM invoice_line_item where invoice_invoice_number = '" + invoiceNumberInput + "'";
		ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return invoiceLineItems;
	}

	/**
	 * This method obtains a password based on username supplied
	 * @param	username		String to specify the user
	 * @return	password		String holding the password for login validation
	 * Written by Michael Meesseman
	 */
	public String obtainPassword(String username) {
	
		String query = "SELECT password FROM login WHERE username = '" + username + "'";
		String password = "";
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
	
		return password;
	}

	/**
	 * This method obtains an arrayList of customer objects
	 * @param	column		String to identify the column
	 * @param	search		String 
	 * @return	customers	ArrayList of customer objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Customer> obtainCustomerFilter(String column, String search)
	{
		String query = "SELECT  * FROM customer, address, contact_info "
			+ "where customer.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and customer.Address_address_id = address.address_id and " + column + " = '" + search + "'";
	
		ArrayList<Customer> customers = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return customers;
	}

	/**
	 * This method obtains an arrayList of employee objects
	 * @param	column			String to identify the column
	 * @param	search			String
	 * @return	employees		ArrayList of employee objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Employee> obtainEmployeeFilter(String column, String search)
	{
		String query = "SELECT  * FROM employee, address, contact_info "
			+ "where employee.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and employee.Address_address_id = address.address_id and " + column + " = '" + search + "'";
	
		ArrayList<Employee> employees = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return employees;
	}

	/**
	 * This method obtains an arrayList of lineItem objects
	 * @param	invoiceNumberInput	String to identify the invoice
	 * @param	column			String to identify the column
	 * @param	search			String
	 * @return	invoiceLineItems		ArrayList of lineItem objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemFilter(String invoiceNumberInput,
		String column, String search) {
	
	
		String query = "SELECT * FROM invoice_line_item where invoice_invoice_number "
			+ "= '" + invoiceNumberInput  + "' and " + column + " = '" + search + "'";
		ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return invoiceLineItems;
	}

	/**
	 * This method obtains an arrayList of invoice objects
	 * @param	column			String to identify the column
	 * @param	search			String
	 * @return	invoices		ArrayList of invoice objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Invoice> obtainInvoiceFilter(String column, String search) {
	
		String query = "SELECT * FROM invoice where " + column + " = '" + search + "'";
		ArrayList<Invoice> invoices = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return invoices;
	}

	/**
	 * This method obtains an arrayList of product objects
	 * @param	column			String to identify the column
	 * @param	search			String
	 * @return	company			ArrayList of company objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Company> obtainCompanyFilter(String column, String search) {
	
		String query = "select * from company, address, contact_info "
			+ "where Address_address_id = address.address_id "
			+ "and contact_info_contact_info_id = contact_info.contact_info_id and " + column + " = '" + search + "'";
	
		ArrayList<Company> company = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String companyID = rs.getString(1);
				String addressID = rs.getString(2);
				String contactID = rs.getString(3);
				String companyName = rs.getString(4);
				String streetAddress = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				String zipCode = rs.getString(9);
				String unitNumber = rs.getString(10);
				String homePhone = rs.getString(12);
				String cellPhone = rs.getString(13);
				String emailAddress = rs.getString(14);
			
				Company c = new Company();
			
				c.setCompanyID(companyID);
				c.setAddressID(addressID);
				c.setContactInfoID(contactID);
				c.setCompanyName(companyName);
				c.setStreetAddres(streetAddress);
				c.setCity(city);
				c.setState(state);
				c.setZipCode(zipCode);
				c.setUnitNumber(unitNumber);
				c.setPhoneNumber(homePhone);
				c.setCellPhoneNumber(cellPhone);
				c.setEmailAddress(emailAddress);
			
				company.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return company;
	}
	
	/**
	 * This method obtains an arrayList of supplier objects
	 * @param	column			String to identify the column
	 * @param	search			String
	 * @return	supplier		ArrayList of supplier objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Supplier> obtainSupplierFilter(String column, String search) {

		String query = "SELECT * FROM supplier, address, contact_info, company "
			+ "where supplier.Address_address_id = address.address_id "
			+ "and supplier.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and supplier.company_company_id = company.company_id and " + column + " = '" + search + "'";
		
		ArrayList<Supplier> supplier = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return supplier;
	}
	
	/**
	 * This method obtains a company name based on the company ID
	 * @param companyID			String to specify the company ID
	 * @return companyName		String holding the companies name
	 * Written by Rick Stuart
	 */
	public String obtainCompanyName(String companyID) {
	
		String query = "SELECT company_name FROM company where company_id  = '" 
			+ companyID +"'";
	
		String companyName = "";
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				companyName = rs.getString(1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return companyName;
	}

	/**
	 * This method obtains the price paid for an object from the supplier
	 * @param productID			String to specify the product
	 * @return supplierPrice	String holding the suppliers price
	 * Written by Rick Stuart
	 */
	public String obtainSupplierPrice(String productID) {
	
		Integer productIDInt = Integer.parseInt(productID);
	
		String query = "SELECT supplier_price FROM product where product  = '" 
			+ productIDInt +"'";
	
		String supplierPrice = "";
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				supplierPrice = rs.getString(1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return supplierPrice;
	}
	
	/**
	 * This method obtains an arrayList of product objects
	 * @return	products	ArrayList of product objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Product> obtainProductList() {
	
		String query = "SELECT * FROM product";
		ArrayList<Product> products = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return products;
	}

	/**
	 * This method obtains an arrayList of purchase objects
	 * @return	purchases	ArrayList of purchase objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<AccountingPurchases> obtainPurchaseList() {
	
		String query = "SELECT * FROM accounting_purchases";
		ArrayList<AccountingPurchases> purchases = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return purchases;
	}
	
	/**
	 * This method obtains an arrayList of purchse objects
	 * @param	column			String to identify the column
	 * @param	search			String
	 * @return	purchases		ArrayList of purchase objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<AccountingPurchases> obtainPurchaseFilter(String column, String search)
	{
		String query = "SELECT * FROM accounting_purchases where " + column + " = '" + search + "'";
	
		ArrayList<AccountingPurchases> purchases = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return purchases;
	}

	/**
	 * This method obtains an arrayList of product objects
	 * @param	column			String to identify the column
	 * @param	search			String
	 * @return	products		ArrayList of product objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Product> obtainProductFilter(String column, String search) {
	
		String query = "SELECT * FROM product where " + column + " = '" + search + "'";
		ArrayList<Product> products = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
		
		return products;
	}
	
	/**
	 * This method obtains an arrayList of supplier objects
	 * @return	supplier		ArrayList of supplier objects
	 * Written by Michael Meesseman
	 */
	public ArrayList<Supplier> obtainSupplierList() {
	
		String query = "SELECT * FROM supplier, address, contact_info, company "
			+ "where supplier.Address_address_id = address.address_id "
			+ "and supplier.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and supplier.company_company_id = company.company_id";
	
		ArrayList<Supplier> supplier = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
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
	
		closeConnection(connObj);
			
		return supplier;
	}
	
	/**
	 * This method obtains the number of a specific item in stock
	 * @param	productID		String to identify the product
	 * @return	quantity		String holding the number of items in stock
	 * Written by Rick Stuart
	 */
	public String obtainQuantityInStock(String productID) {
		
		String quantity = "";
		String query = null;
		
		query = "SELECT quantity_in_stock "
				+ "FROM product "
				+ "WHERE product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
		
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				quantity = rs.getString(1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		closeConnection(connObj);
			
		return quantity;
	}
	
	/**
	 * This helper method uses supplied address information to obtain the ID number 
	 * 		for an address which was just written to the database.
	 * @param stAddress				String variable for the street address.
	 * @param city					String variable for the city.
	 * @param state					String variable for the state.
	 * @param zipCode				String variable for the zip code.
	 * @param unitNumber			String variable for the unit number.
	 * @return addressID			String variable for the address ID number.
	 * Written by Rick Stuart
	 */
	public String obtainNewAddressID(String stAddress, String city, String state, 
			String zipCode, String unitNumber) {
		
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
								
		connObj = getDBConnection();
								
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
				
		closeConnection(connObj);		
				
		return addressID;
	}
	
	/**
	 * This helper method uses supplied information to locate and obtain the 
	 * 		contact information ID number which corresponds to the information 
	 * 		just written to the database.
	 * @param phoneNumber			String variable for the phone number.
	 * @param cellPhone				String variable for the cell phone number.
	 * @param emailAddress			String variable for the email address.
	 * @return contactInfoID		String variable for the contact info ID number.
	 * Written by Rick Stuart
	 */
	public String obtainNewContactInformationID(String phoneNumber, String cellPhone, 
			String emailAddress) {
		
		String contactInfoID = null;
		String query = null;
		ResultSet rs = null;
		
		query = "select contact_info_id " +
				"from contact_info " +
				"where phone_number = '" + phoneNumber + "' and cell_phone_number = '" + cellPhone +
				"' and email_address = '" + emailAddress + "';";
		
		Statement stmt = null;
										
		connObj = getDBConnection();
										
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
						
		closeConnection(connObj);		
		
		return contactInfoID;
	}
	
	/**
	 * This helper method obtains the record ID number of a product just written
	 * 			to the database
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
	 * @return productID			String to define the record ID number for product
	 * Written by Rick Stuart
	 */
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
								
		connObj = getDBConnection();
								
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
				
		closeConnection(connObj);
		
		return productID;
	}
	
	/**
	 * This helper method obtains the dollar value based on product purchased 
	 * 			and number of items purchased.
	 * @param quantityInStock			String to update inventory
	 * @param supplyPrice				String to define cost to purchase product
	 * 									to update accounting purchases records
	 * @return	dollarValue				String to specify total reorder cost
	 * Written by Rick Stuart
	 */
	public String obtainDollarValue(String quantityInStock, String supplyPrice) {
		
		double total = 0;
		String dollarValue = "";
		
		double purchasePrice = Double.parseDouble(supplyPrice);
		int purchasedQuantity = Integer.parseInt(quantityInStock);
		
		total = purchasePrice * purchasedQuantity;
		
		dollarValue = String.valueOf(total);
		
		return dollarValue;
	}
	
	/**
	 * This helper method obtains the invoice record ID number for an invoice 
	 * 			just written to the database.
	 * @param date				String to hold the date the invoice was created
	 * @param time				String to hold the time the invoice was created
	 * @param customerID		String to hold the customer record ID number
	 * @param employeeID		String to hold the employee record ID number
	 * @return	invoiceNum		String to specify the invoice record ID number
	 * Written by Rick Stuart
	 */
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
		
		connObj = getDBConnection();
								
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
				
		closeConnection(connObj);
		
		return invoiceNum;
	}
	
	/**
	 * This helper method obtains the line item record ID of a line item just 
	 * 			written to the database.
	 * @param invoiceID				String to hold the invoice record ID number
	 * @param quantityPurchased		String to specify number of items purchased/sold
	 * @param productID				String to specify product ID number
	 * @return	lineID				String to hold the line item record ID number
	 * Written by Rick Stuart
	 */
	public String obtainLineItemID(String invoiceID, String quantityPurchased, String productID) {
		
		String lineID = "";
		String query = null;
		ResultSet rs = null;
		
		query = "select invoice_line_number " +
				"from invoice_line_item " +
				"where invoice_invoice_number = '" + invoiceID + "' and quantity_purchased = '" +
				quantityPurchased + "' and product_product = '" + productID + "';";
		
		Statement stmt = null;  
		
		connObj = getDBConnection();
								
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
				
		closeConnection(connObj);
		
		return lineID;
	}
	
	/**
	 * This helper method obtains the customer's cost to purchase the item
	 * @param productID			String to specify the product ID number
	 * @return	price			String to specify the selling price of the product
	 * Written by Rick Stuart
	 */
	public String obtainSellPrice(String productID) {
		
		String price = "";
		String query = null;
		ResultSet rs = null;
		
		query = "select sell_price " +
				"from product " +
				"where product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
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
				
		closeConnection(connObj);
		
		return price;
	}
	
	/**
	 * This helper method obtains the sales tax to be charged for the line item transaction
	 * @param dollarValue		String to specify total cost for the line item
	 * @return	tax				String to specify the sales tax to be charged
	 * Written by Rick Stuart
	 */
	public String obtainSalesTax(String dollarValue) {
		
		String tax = "";
		Double salesTax = 0.0;
		
		double cost = Double.parseDouble(dollarValue);
		salesTax = cost * 0.075;
		salesTax = (double) Math.round((salesTax * 100) + 0.5) / 100;
		tax = String.valueOf(salesTax);
		
		return tax;
	}
	
	/**
	 * The helper method obtains the invoice line item record ID number for a newly
	 * 			created line item
	 * @param invoiceNumber			String to hold the invoice ID number for the line item
	 * @param purchasedQuantity		String to specify the number of items purchased
	 * @param productID				String to specify the product purchased
	 * @return	lineNumber			String to hold the line item ID number
	 * Written by Rick Stuart
	 */
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
		
		connObj = getDBConnection();
								
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
				
		closeConnection(connObj);
		
		return lineNumber;
	}
}
