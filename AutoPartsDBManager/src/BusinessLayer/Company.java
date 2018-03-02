/**
 * Class Name:					Company
 * Description:					This class inherits from People and provides fields specific
 * 								to this class, along with get/set accessors/mutators, and the
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,27,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create a Company object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class Company {
	
	// Fields
	private String companyID;
	private String addressID;
	private String contactInfoID;
	private String companyName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String unitNumber;
	private String phoneNumber;	
	private String cellPhoneNumber;
	private String emailAddress;
	
	// Default Constructor
	public Company() {
		this("", "", "", "", "", "", "", "", "", "");
	}
	
	// Overloaded Constructor
	public Company(String companyID, String companyName, String streetAddress,
			String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhoneNumber, String emailAddress) {
		this.companyID = companyID;
		this.companyName = companyName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.unitNumber = unitNumber;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	// Get and Set Accessors/Mutators
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	
	public String getCompanyID() {
		return companyID;
	}
	
	
	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}
	
	public String getAddressID() {
		return addressID;
	}
	
	public void setContactInfoID(String contactInfoID) {
		this.contactInfoID = contactInfoID;
	}
	
	public String getContactInfoID() {
		return contactInfoID;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setStreetAddres(String streetAddress) {
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
		return companyName;
	}
}