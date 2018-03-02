/**
 * Class Name:					AccountingSales
 * Description:					This class provides fields specific
 * 								to this class, along with get/set accessors/mutators, and an 
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,27,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create an AccountingSales object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class AccountingSales {
	
	// Fields
	private String accountingSalesRecordID;
	private String soldQuantity;
	private String productID;
	private String dollarValue;
	private String salesTaxAcquired;
	private String invoiceLineNumber;
	
	// Default Constructor
	AccountingSales() {
		this("", "", "", "", "", "");
	}
	
	// Overloaded Constructor
	AccountingSales(String accountingSalesRecordID, String soldQuantity,
			String productID, String dollarValue, String salesTaxAcquired,
			String invoiceLineNumber) {
		this.accountingSalesRecordID = accountingSalesRecordID;
		this.soldQuantity = soldQuantity;
		this.productID = productID;
		this.dollarValue = dollarValue;
		this.salesTaxAcquired = salesTaxAcquired;
		this.invoiceLineNumber = invoiceLineNumber;
	}
	
	// Get and Set Accessors/Mutators
	public void setAccountingSalesRecordID(String accountingSalesRecordID) {
		this.accountingSalesRecordID = accountingSalesRecordID;
	}
	
	public String getAccountingSalesRecordID() {
		return accountingSalesRecordID;
	}
	
	public void setSoldQuantity(String soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
	
	public String getSoldQuantity() {
		return soldQuantity;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getProductID() {
		return productID;
	}
	
	public void setDollarValue(String dollarValue) {
		this.dollarValue = dollarValue;
	}
	
	public String getDollarValue() {
		return dollarValue;
	}
	
	public void setSalesTaxAcquired(String salesTaxAcquired) {
		this.salesTaxAcquired = salesTaxAcquired;
	}
	
	public String getSalesTaxAcquired() {
		return salesTaxAcquired;
	}
	
	public void setInvoiceLineNumber(String invoiceLineNumber) {
		this.invoiceLineNumber = invoiceLineNumber;
	}
	
	public String getInvoiceLineNumber() {
		return invoiceLineNumber;
	}
	
	@Override
	public String toString() {
		return accountingSalesRecordID;
	}
}

