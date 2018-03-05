/**
 * Interface Name:			DataReader
 * Description:				This interface defines the methods which are employed by the DatabaseReader class.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package DatabaseLayer;


import java.util.ArrayList;
import BusinessLayer.AccountingPurchases;
import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Employee;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;
import BusinessLayer.Supplier;

/**
 * This interface contains the signatures of the methods used in the DatabaseReader class.
 * Written by Rick Stuart
 */
public interface DataReader {

	public String getQtyInStock(Integer productID);
	public Product lookupProduct(String productID);
	public ArrayList<Company> obtainCompanyList();
	public ArrayList<Customer> obtainCustomerList();
	public ArrayList<Employee> obtainEmployeeList();
	public ArrayList<Invoice> obtainInvoiceList();
	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemList(String invoiceNumberInput);
	public String obtainPassword(String username);
	public ArrayList<Customer> obtainCustomerFilter(String column, String search);
	public ArrayList<Employee> obtainEmployeeFilter(String column, String search);
	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemFilter(String invoiceNumberInput,
			String column, String search);
	public ArrayList<Invoice> obtainInvoiceFilter(String column, String search);
	public ArrayList<Company> obtainCompanyFilter(String column, String search);
	public ArrayList<Supplier> obtainSupplierFilter(String column, String search);
	public String obtainCompanyName(String companyID);
	public String obtainSupplierPrice(String productID);
	public ArrayList<Product> obtainProductList();
	public ArrayList<AccountingPurchases> obtainPurchaseList();
	public ArrayList<AccountingPurchases> obtainPurchaseFilter(String column, String search);
	public ArrayList<Product> obtainProductFilter(String column, String search);
	public ArrayList<Supplier> obtainSupplierList();
	public String obtainQuantityInStock(String productID);
	public String obtainNewAddressID(String stAddress, String city, String state,
			String zipCode, String unitNumber);
	public String obtainNewContactInformationID(String phoneNumber, String cellPhone,
			String emailAddress);
	public String obtainProductID(String description, String yearMin, String yearMax,
			String make, String model, String supplyPrice, String sellPrice,
			String coreCharge, String compatNum, String companyID, String minStockQuantity,
			String maxStockQuantity, String location, String quantityInStock);
	public String obtainDollarValue(String quantityInStock, String supplyPrice);
	public String obtainNewInvoiceNumber(String date, String time, String customerID,
			String employeeID);
	public String obtainLineItemID(String invoiceID, String quantityPurchased, 
			String productID);
	public String obtainSellPrice(String productID);
	public String obtainSalesTax(String dollarValue);
	public String obtainInvoiceLineID(String invoiceNumber, String purchasedQuantity,
			String productID);
}
