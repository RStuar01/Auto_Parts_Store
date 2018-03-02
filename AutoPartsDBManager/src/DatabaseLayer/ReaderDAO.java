/**
 * Interface Name:		ReaderDAO
 * Description:			This interface provides the data access object used to implement
 * 						the DataReader methods used to access the SQL database.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package DatabaseLayer;

import java.util.List;

import BusinessLayer.AccountingPurchases;
import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Employee;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;
import BusinessLayer.Product;
import BusinessLayer.Supplier;

/**
 * This interface maps the DataReader interface to the DAOFactory class
 * Written by Rick Stuart
 */
public interface ReaderDAO extends DataReader {
}
