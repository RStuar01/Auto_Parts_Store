/**
 * Class Name:						People
 * Description:						This is the SuperClass for Customer, Supplier, and Employee classes,
 * 									and provides data fields for the basic elements of each of these along
 * 									with get/set accessors/mutators, and the toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create a People object for use by the sub-classes that inherit from it.
 * Written by Rick Stuart
 */
public class People {

	// Declare Fields
	private String lastName;
	private String firstName;
	private String contactInfoID;
	private String addressID;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String unitNumber;
	private String phoneNumber;
	private String cellPhoneNumber;
	private String emailAddress;
	
	// Default Constructor
	public People() {
		this("", "", "", "", "", "", "", "", "", "", "", "");
	}
	
	// Overloaded Constructor
	public People(String lastName, String firstName, String contactInfoID, 
			String addressID, String streetAddress, String city, 
			String state, String zipCode, String unitNumber, String phoneNumber,
			String cellPhoneNumber, String emailAddress) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.contactInfoID = contactInfoID;
		this.addressID = addressID;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.unitNumber = unitNumber;
		this.phoneNumber = phoneNumber;
		this.cellPhoneNumber = cellPhoneNumber;
		this.emailAddress = emailAddress;
	}
	
	// Get and Set Accessors/Mutators
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setContactInfoID(String contactInfoID) {
		this.contactInfoID = contactInfoID;
	}
	
	public String getContactInfoID() {
		return contactInfoID;
	}
	
	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}
	
	public String getAddressID() {
		return addressID;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	
	public String getUnitNumber() {
		return unitNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}
}
