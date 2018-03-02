/**
 * Interface Name:	DataWriter
 * Description:		This interface defines the methods which are employed by the DatabaseWriter class.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package DatabaseLayer;

import java.util.ArrayList;
import BusinessLayer.Product;

/**
 * This interface holds the method signatures for the methods used in the DatabaseWriter class
 * Written by Rick Stuart
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
	
	public void writeIncomingProducts(ArrayList<Product> rfidProducts);
	
	public void manuallyEnterNewAccountingPurchase(String productID, 
			String quantityPurchased, String dollarValue);
	
	public void manuallyEnterNewPart(String description, String minYear, String maxYear,
			String make, String model, String supplierPrice, String sellPrice, 
			String coreCharge, String compatibilityNumber, String companyID, 
			String minStockQuantity, String maxStockQuantity,
			String warehouseLocation, String quantityInStock);
	
	// copy from writeHelper due to gui changes
	public void createInvoice(String date, String time, String customerID,
			String employeeID);
	
	// copy from writeHelper due to gui changes
	public void createInvoiceLineItem(String invoiceNumber, String purchasedQuantity,
			String productID);
	
	//New for different gui
	public boolean checkCompanyExists(String companyID);
	
	//New for different gui
	public boolean checkProductExists(String productID);
	
	//New for different gui
	public boolean checkCustomerExists(String customerID);
	
	//New for different gui
	public boolean checkEmployeeExists(String employeeID);
		
	//New for different gui
	public void manageEnteringToAccountingSales(String invoiceNumber, 
			String purchasedQuantity, String productID);
	
	public void manageEditingCustomer(String customerID, String contactID, String addressID, 
			String lastName, String firstName, String streetAddress, String city,
			String state, String zipCode, String unitNumber, String homePhone,
			String cellPhone, String email);
	
	public void manageEditingEmployee(String employeeID, String contactID, String addressID,
			String lastName, String firstName, String streetAddress, String city,
			String state, String zipCode, String unitNumber, String homePhone,
			String cellPhone, String email);
	
	public void editProduct(String productID, String description, String minYear,
			String maxYear, String make, String model, String supplierPrice,
			String sellPrice, String coreCharge, String compatibilityNumber,
			String companyID, String minStockQuantity, String maxStockQuantity,
			String warehouseLocation, String quantityInStock);
	
	public void manageEditingCompany(String companyID, String addressID, 
			String contactID, String streetAddress, String city, String state, 
			String zipCode, String unitNumber, String homePhone, String cellPhone,
			String email, String companyName);
	
	public void manageEditingSupplier(String supplierID, String contactID, 
			String addressID, String companyID, String lastName, String firstName, 
			String streetAddress, String city, String state, String zipCode, 
			String unitNumber, String homePhone, String cellPhone, String email);
}
