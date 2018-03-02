/**
 * Class Name:					InvoiceLineItem
 * Description:					This class  provides fields specific
 * 								to this class, along with get/set accessors/mutators, and an 
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create an InvoiceLineItem object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class InvoiceLineItem {
	
	// Fields
	private String invoiceLineNumber;
	private String invoiceNumber;
	private String quantityPurchased;
	private String productID;
	
	// Default Constructor
	public InvoiceLineItem() {
		this("", "", "", "");
	}
	
	// Overloaded Constructor
	public InvoiceLineItem(String invoiceLineNumber, String invoiceNumber,
			String quantityPurchased, String productID) {
		this.invoiceLineNumber = invoiceLineNumber;
		this.invoiceNumber = invoiceNumber;
		this.quantityPurchased = quantityPurchased;
		this.productID = productID;
	}
	
	// Get and Set Accessors/Mutators
	public void setInvoiceLineNumber(String invoiceLineNumber) {
		this.invoiceLineNumber = invoiceLineNumber;
	}
	
	public String getInvoiceLineNumber() {
		return invoiceLineNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setQuantityPurchased(String quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	
	public String getQuantityPurchased() {
		return quantityPurchased;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getProductID() {
		return productID;
	}
	
	@Override
	public String toString() {
		return productID + ", " + invoiceNumber + ", " + invoiceLineNumber + ", " +
				quantityPurchased;
	}
}
