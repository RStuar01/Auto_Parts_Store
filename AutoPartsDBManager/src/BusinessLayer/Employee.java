/**
 * Class Name:					Employee
 * Description:					This class inherits from People and provides fields specific
 * 								to this class, along with get/set accessors/mutators, and an 
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create an Employee object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class Employee extends People {

	// Fields
	private String employeeID;
	
	// Default Constructor
	public Employee() {
		super();
		employeeID = "";
	}
	
	// Overloaded Constructor
	public Employee(String employeeID, String lastName, String firstName,
			String contactInfoID, String addressID, String streetAddress,
			String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhoneNumber, String emailAddress) {
		
		super(lastName, firstName, contactInfoID, addressID, streetAddress,
			city, state, zipCode, unitNumber, phoneNumber, cellPhoneNumber, 
			emailAddress);
		this.employeeID = employeeID;
	}
	
	// Get and Set Accessors/Mutators
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
