package BusinessLayer;

/**
 * Class Name:					Company
 * Description:					This class inherits from People and provides fields specific
 * 								to this class, along with get/set accessors/mutators, and the
 * 								overridden toString method.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,27,2018
 */
public class Company {
	
	// Fields
	private String companyID;
	private String addressID;
	private String contactInfoID;
	private String companyName;
	
	// Default Constructor
	public Company() {
		this("", "", "", "");
	}
	
	// Overloaded Constructor
	public Company(String companyID, String addressID, String contactInfoID,
			String companyName) {
		this.companyID = companyID;
		this.addressID = addressID;
		this.contactInfoID = contactInfoID;
		this.companyName = companyName;
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
	
	@Override
	public String toString() {
		return companyName;
	}

}
