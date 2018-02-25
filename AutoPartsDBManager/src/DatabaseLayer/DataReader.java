package DatabaseLayer;

import java.util.ArrayList;

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

	// List methods used to read the database.
	
	// obtainCustomerInformation
	public Customer obtainCustomerInformation(String lastName, String firstName,
			String phoneNumber);
	
	// obtainEmployeeInformation
	public Employee obtainEmployeeInformation(String lastName, String firstName,
			String phoneNumber);
	
	// obtainSupplierInformation
	public Supplier obtainSupplierInformation(String lastName, String firstName,
			String phoneNumber);
	
	// obtainCompanyInformtion
	public Company obtainCompanyInformation(String companyName);
	public static String obtainCompanyName(String companyID) {
		return null;
	}
	
	// obtainInvoiceInformation
	public ArrayList<Invoice> getInvoices(String customerID);
	
	// obtainInvoiceLineItemInformation
	public ArrayList<InvoiceLineItem> getInvoiceLineItems(String invoiceNumber);
	
	// obtainProductInformation
	public ArrayList<Product> getCompatibleProducts(String compatibilityNumber);
	public Product lookupProduct(String productID);
	public Product lookupProduct(String description, String year, String make,
			String model);
	public String getQuantityInStock(String productID);
	// obtainAccountingPurchasesInformation
	// obtainAccountingSalesInformation
	
}
