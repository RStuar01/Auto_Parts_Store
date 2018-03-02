/**
 * Class Name:						Product 
 * Description:						
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package BusinessLayer;

/**
 * This class contains the fields, constructors, get accessors and set mutators necessary
 * 			to create a Product object for use by the Database and 
 * 			Presentation layers
 * Written by Rick Stuart
 */
public class Product {

	// Fields
	private String productID;
	private String description;
	private String yearMinimum;
	private String yearMaximum;
	private String make;
	private String model;
	private String supplierPrice;
	private String sellPrice;
	private String coreCharge;
	private String compatibilityNumber;
	private String companyID;
	private String minQuantityInStock;
	private String maxQuantityInStock;
	private String warehouseLocation;
	private String quantityInStock;
	
	// Default Constructor
	public Product() {
		this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
	}
	
	// Overloaded Constructor
	public Product(String productID, String description, String yearMinimum,
			String yearMaximum, String make, String model, String supplierPrice,
			String sellPrice, String coreCharge, String compatibilityNumber,
			String companyID, String minQuantityInStock, String maxQuantityInStock,
			String warehouseLocation, String quantityInStock) {
		this.productID = productID;
		this.description = description;
		this.yearMinimum = yearMinimum;
		this.yearMaximum = yearMaximum;
		this.make = make;
		this.model = model;
		this.supplierPrice = supplierPrice;
		this.sellPrice = sellPrice;
		this.coreCharge = coreCharge;
		this.compatibilityNumber = compatibilityNumber;
		this.companyID = companyID;
		this.minQuantityInStock = minQuantityInStock;
		this.maxQuantityInStock = maxQuantityInStock;
		this.warehouseLocation = warehouseLocation;
		this.quantityInStock = quantityInStock;
	}
	
	// Get and Set Accessors/Mutators
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getProductID() {
		return productID;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setYearMinimum(String yearMinimum) {
		this.yearMinimum = yearMinimum;
	}
	
	public String getYearMinimum() {
		return yearMinimum;
	}
	
	public void setYearMaximum(String yearMaximum) {
		this.yearMaximum = yearMaximum;
	}
	
	public String getYearMaximum() {
		return yearMaximum;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setSupplierPrice(String supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	
	public String getSupplierPrice() {
		return supplierPrice;
	}
	
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public String getSellPrice() {
		return sellPrice;
	}
	
	public void setCoreCharge(String coreCharge) {
		this.coreCharge = coreCharge;
	}
	
	public String getCoreCharge() {
		return coreCharge;
	}
	
	public void setCompatibilityNumber(String compatibilityNumber) {
		this.compatibilityNumber = compatibilityNumber;
	}
	
	public String getCompatibilityNumber() {
		return compatibilityNumber;
	}
	
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	
	public String getCompanyID() {
		return companyID;
	}
	
	public void setMinQuantityInStock(String minQuantityInStock) {
		this.minQuantityInStock = minQuantityInStock;
	}
	
	public String getMinQuantityInStock() {
		return minQuantityInStock;
	}
	
	public void setMaxQuantityInStock(String maxQuantityInStock) {
		this.maxQuantityInStock = maxQuantityInStock;
	}
	
	public String getMaxQuantityInStock() {
		return maxQuantityInStock;
	}
	
	public void setWarehouseLocation(String warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}
	
	public String getWarehouseLocation() {
		return warehouseLocation;
	}
	
	public void setQuantityInStock(String quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	
	public String getQuantityInStock() {
		return quantityInStock;
	}
	
	@Override
	public String toString() {
		return productID + ", " + description;
	}
}
