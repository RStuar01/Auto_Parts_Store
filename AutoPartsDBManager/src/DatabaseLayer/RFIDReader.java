package DatabaseLayer;

import java.util.ArrayList;

import BusinessLayer.Product;

public interface RFIDReader {

	// List method signatures
	public ArrayList<Product> ProductTextFile();
	public ArrayList<Product> getProducts();
	
}
