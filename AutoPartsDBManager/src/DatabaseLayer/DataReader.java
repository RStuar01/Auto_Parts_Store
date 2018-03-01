package DatabaseLayer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import BusinessLayer.AccountingPurchases;
import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Employee;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;
import BusinessLayer.Supplier;

// List imports

/**
 * Interface Name:			DataReader
 * Description:				This interface defines the methods which are employed by the DatabaseReader class.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
public interface DataReader {

	//public void closeConnection(Connection connObj);
	//public static Connection getDBConnection();
	//public static String getQtyInStock(Integer productID);
	public Product lookupProduct(String productID);
	//public static ArrayList<Company> obtainCompanyList();
	//public static ArrayList<Customer> obtainCustomerList();
	//public static ArrayList<Employee> obtainEmployeeList();
	//public static ArrayList<Invoice> obtainInvoiceList();
	//public static ArrayList<InvoiceLineItem> obtainInvoiceLineItemList(String invoiceNumberInput);
	//public static String obtainPassword(String username);
	//public static ArrayList<Customer> obtainCustomerFilter(String column, String search);
	//public static ArrayList<Employee> obtainEmployeeFilter(String column, String search);
	//public static ArrayList<InvoiceLineItem> obtainInvoiceLineItemFilter(String invoiceNumberInput,
	//String column, String search);
	//public static ArrayList<Invoice> obtainInvoiceFilter(String column, String search);
	//public static ArrayList<Company> obtainCompanyFilter(String column, String search);
	//public static ArrayList<Supplier> obtainSupplierFilter(String column, String search);
	
	//public static String obtainSupplierPrice(String productID);
	//public static ArrayList<Product> obtainProductList();
	//public static ArrayList<AccountingPurchases> obtainPurchaseList();
	//public static ArrayList<AccountingPurchases> obtainPurchaseFilter(String column, String search);
	//public static ArrayList<Product> obtainProductFilter(String column, String search);
	//public static ArrayList<Supplier> obtainSupplierList();
	public String obtainQuantityInStock(String productID);
	
	public String obtainCompanyName(String companyID);

	public String obtainSupplierPrice(String text);

	public List<Company> obtainCompanyList();

	public List<Company> obtainCompanyFilter(String column, String search);

	public List<Customer> obtainCustomerList();

	public List<Customer> obtainCustomerFilter(String column, String search);

	public List<Employee> obtainEmployeeList();

	public List<Employee> obtainEmployeeFilter(String column, String search);

	public Object obtainPassword(String text);

	public List<Product> obtainProductList();

	public List<Product> obtainProductFilter(String column, String search);

	public List<AccountingPurchases> obtainPurchaseList();

	public List<AccountingPurchases> obtainPurchaseFilter(String column, String search);

	public String getQtyInStock(int parseInt);

	public List<InvoiceLineItem> obtainInvoiceLineItemList(String invoiceNumberInput);

	public List<InvoiceLineItem> obtainInvoiceLineItemFilter(String invoiceNumberInput, String column, String search);

	public List<Invoice> obtainInvoiceList();

	public List<Invoice> obtainInvoiceFilter(String column, String search);

	public List<Supplier> obtainSupplierList();

	public List<Supplier> obtainSupplierFilter(String column, String search);
	
}
