/**
 * Class Name:					AccountingPurchases
 * Description:					This class provides fields specific
 * 								to this class, along with get/set accessors/mutators, and an 
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,27,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create an AccountingPurchases object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class AccountingPurchases {
	
	// Fields
	private String accountingPurchasesRecordID;
	private String purchasesQuantity;
	private String dollarValue;
	private String productID;
	
	// Default Constructor
	public AccountingPurchases() {
		this("", "", "", "");
	}
	
	// Overloaded Constructor
	AccountingPurchases(String accountingPurchasesRecordID, 
			String purchasesQuantity, String dollarValue, String productID) {
		this.accountingPurchasesRecordID = accountingPurchasesRecordID;
		this.purchasesQuantity = purchasesQuantity;
		this.dollarValue = dollarValue;
		this.productID = productID;
	}
	
	// Get and Set Accessors/Mutators
	public void setAccountingPurchasesRecordID(String accountingPurchasesRecordID) {
		this.accountingPurchasesRecordID = accountingPurchasesRecordID;
	}
	
	public String getAccountingPurchasesRecordID() {
		return accountingPurchasesRecordID;
	}
	
	public void setPurchasesQuantity(String purchasesQuantity) {
		this.purchasesQuantity = purchasesQuantity;
	}
	
	public String getPurchasesQuantity() {
		return purchasesQuantity;
	}
	
	public void setDollarValue(String dollarValue) {
		this.dollarValue = dollarValue;
	}
	
	public String getDollarValue() {
		return dollarValue;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getProductID() {
		return productID;
	}
	
	@Override
	public String toString() {
		return accountingPurchasesRecordID;
	}
}
