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
		String url = "jdbc:mysql://127.0.0.1:3306/auto_parts_schema";
		String username = "autouser";
		String password = "autouser";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		System.out.println("DatabaseReader - Connection Established!");			
		
			
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
		String homePhone = "";
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

public static ArrayList<Company> obtainCompanyList() {
	
	String query = "select company_name from company, "
			+ "supplier where supplier.company_company_id = company.company_id";
	ArrayList<Company> company = new ArrayList<>();
	
	Statement stmt = null;
	
	connObj =  DatabaseWriter.getDBConnection();
					
	try {	
		stmt = connObj.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String companyName = rs.getString(1);
			
			Company c = new Company();
			c.setCompanyName(companyName);
			company.add(c);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return company;
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

public static ArrayList<Invoice> obtainInvoiceList() {
	
	String query = "SELECT * FROM invoice, invoice_line_item";
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
			String productID = rs.getString(9);
			String quantityPurchased = rs.getString(8);
			
			Invoice i = new Invoice();
			InvoiceLineItem invoiceLine = new InvoiceLineItem();
			i.setInvoiceNumber(invoiceNumber);
			i.setDate(date);
			i.setTime(time);
			i.setCustomerID(customerID);
			i.setEmployeeID(employeeID);
			invoiceLine.setProductID(productID);
			invoiceLine.setQuantityPurchased(quantityPurchased);
			
			invoices.add(i);
		}
	}
	catch (SQLException e) {
		System.out.println(e.toString());
	}
	
	DatabaseWriter.closeConnection(connObj);
			
	return invoices;
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

}

