package DatabaseLayer;

import BusinessLayer.Customer;
import BusinessLayer.Employee;
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
	
	// obtainInvoiceInformation
	// obtainInvoiceLineItemInformation
	
	// obtainProductInformation
	
	// obtainAccountingPurchasesInformation
	// obtainAccountingSalesInformation
	
}
