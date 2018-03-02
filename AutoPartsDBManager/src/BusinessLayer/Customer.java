/**
 * Class Name:					Customer
 * Description:					This class inherits from People and provides fields specific
 * 								to this class, along with get/set accessors/mutators and an 
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create a Customer object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class Customer extends People {

	// Declare the fields
	private String customerID;
	
	// Default Constructor
	public Customer() {
		super();
		customerID = "";
	}
	
	// Overloaded Constructor
	public Customer(String customerID, String lastName, String firstName,
			String contactInfoID, String addressID, String streetAddress,
			String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhoneNumber, String emailAddress) {
		
		super(lastName, firstName, contactInfoID, addressID, streetAddress,
			city, state, zipCode, unitNumber, phoneNumber, cellPhoneNumber, 
			emailAddress);
		this.customerID = customerID;
	}
	
	// Get and Set Accessors/Mutators
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
