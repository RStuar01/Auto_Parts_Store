package DatabaseLayer;

// List imports

/**
 * Interface Name:	DataWriter
 * Description:		This interface defines the methods which are employed by the DatabaseWriter class.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
public interface DataWriter {

	public void manageNewPersonCreation(String choice, String lastName, String firstName,
			String stAddress, String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhone, String emailAddress, String companyID);
	
	public void createNewCompany(String stAddress, String city, String state,
			String zipCode, String unitNumber, String phoneNumber, String cellPhone,
			String emailAddress, String companyName);
	
	public void manageEnteringNewProduct(String description, String yearMin, String yearMax,
			String make, String model, String supplyPrice, String sellPrice,
			String coreCharge, String compatNum, String companyID, String minStockQuantity,
			String maxStockQuantity, String location, String quantityInStock);
	
	public void manageSale(String date, String time, String customerID, String employeeID,
			String quantityPurchased, String productID);
	
}
