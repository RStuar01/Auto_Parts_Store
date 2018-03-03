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
 * This interface holds the signatures of the methods used in the RFIDTextReader class
 * Written by Rick Stuart
 */
public interface RFIDReader {

	// List method signatures
	public ArrayList<Product> ProductTextFile();
	public ArrayList<Product> getProducts();
	public void writeQuantityRejected(String reasonRejected, Product p, String productID,
			int quantityRejected);
	public void writeProductOrder(String productID, String quantityToOrder);
	
}
