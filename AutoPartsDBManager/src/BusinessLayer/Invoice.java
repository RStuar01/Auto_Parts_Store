/**
 * Class Name:					Invoice
 * Description:					This class contains fields specific
 * 								to this class, along with get/set accessors/mutators, and an 
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,27,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create an Invoice object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class Invoice {

	// Fields
	private String invoiceNumber;
	private String date;
	private String time;
	private String customerID;
	private String employeeID;
	
	// Default Constructor
	public Invoice() {
		this("", "", "", "", "");
	}
	
	// Overloaded Constructor
	public Invoice(String invoiceNumber, String date, String time, 
			String customerID, String employeeID) {
		this.invoiceNumber = invoiceNumber;
		this.date = date;
		this.time = time;
		this.customerID = customerID;
		this.employeeID = employeeID;
	}
	
	// Get and Set Accessors/Mutators
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}
	
	@Override
	public String toString() {
		return invoiceNumber;
	}
}
