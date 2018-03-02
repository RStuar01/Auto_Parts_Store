/**
 * Class Name:					Supplier
 * Description:					This class inherits from People and provides fields specific
 * 								to this class, along with get/set accessors/mutators, and the
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create a Supplier object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class Supplier extends People {

	// Fields
	private String supplierID;
	private String companyID;
	private String companyName;
	
	// Default Constructor
	public Supplier() {
		super();
		this.supplierID = "";
		this.companyID = "";
	}
	
	//Overloaded Constructor
	public Supplier(String supplierID, String companyID, String lastName, String firstName,
			String contactInfoID, String addressID, String streetAddress,
			String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhoneNumber, String emailAddress) {
		
		super(lastName, firstName, contactInfoID, addressID, streetAddress,
			city, state, zipCode, unitNumber, phoneNumber, cellPhoneNumber, 
			emailAddress);
		this.supplierID = supplierID;
	}
	
	// Get and Set Accessors/Mutators
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	
	public String getSupplierID() {
		return supplierID;
	}
	
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	
	public String getCompanyID() {
		return companyID;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
