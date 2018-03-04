/**
 * Interface Name:	DataWriter
 * Description:		This interface defines the methods which are employed by the DatabaseWriter class.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package DatabaseLayer;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import BusinessLayer.Product;

/**
 * This class holds the methods used to simulate reading RFID tags
 * Written by Rick Stuart
 */
public class RFIDTextReader implements RFIDDAO {

	private ArrayList<Product> products = null;
	private Path productsPath = null;
	private File productsFile = null;
	private static ReaderDAO readerDAO;
	
	private final String FIELD_SEP = ",";
		
	/**
	 * This method searches for the "products.txt" file and deletes it after it has
	 * 			been read
	 * Written by Rick Stuart
	 */
	public ArrayList<Product> ProductTextFile() {
		productsPath = Paths.get("products.txt");
		productsFile = productsPath.toFile();
		products = this.getProducts();
		
		// delete the RFID file after products are read
		if(productsFile.delete()) {
			System.out.println("RFID tags read!");
		}
		else {
			System.out.println("No incoming products!");
		}
		
		return products;
	}
	
	/**
	 * This method simulates reading RFID tags by reading the "products.txt" file
	 * Written by Rick Stuart
	 */
	public ArrayList<Product> getProducts() {
		
		// if the products file has already been read, don't read it again
		if(products != null) {
			return products;
		}
		
		// prevent the FileNotFounException
		if(Files.exists(productsPath)) {
			products = new ArrayList<Product>();
			
			try (BufferedReader in =
					new BufferedReader(
							new FileReader(productsFile))) {
				
				// read all products stored in the file into the array list
				String line = in.readLine();
				while(line != null) {
					String[] columns = line.split(FIELD_SEP);
					String productID = columns[0];
					String description = columns[1];
					String yearMin = columns[2];
					String yearMax = columns[3];
					String make = columns[4];
					String model = columns[5];
					String supplierPrice = columns[6];
					String sellPrice = columns[7];
					String coreCharge = columns[8];
					String compatibilityNumber = columns[9];
					String companyID = columns[10];
					String minQuantity = columns[11];
					String maxQuantity = columns[12];
					String warehouseLocation = columns[13];
					String quantityReceived = columns[14];
					
					Product p = new Product(productID, description, yearMin,
							yearMax, make, model, supplierPrice, sellPrice,
							coreCharge, compatibilityNumber, companyID, minQuantity,
							maxQuantity, warehouseLocation, quantityReceived);
					products.add(p);
					
					line = in.readLine();
				}
			}
			catch(IOException e) {
				System.out.println(e);
				return null;
			}
		}
		
		return products;
	}
	
	/**
	 * This method writes products, and their quantities that were rejected because 
	 * 		they exceed max intentory, or because the product does not yet exist 
	 * 		in the database to a text file called rejected.txt
	 * @param	reasonRejected		String to specify why the product was rejected
	 * @param	p					Product object that is being rejected
	 * @param	productID			String to identify the product object
	 * @param	quantityRejected	int to specify the number of items rejected
	 * Written by Rick Stuart
	 */
	public void writeQuantityRejected(String reasonRejected, Product p, String productID,
			int quantityRejected) {
		
		try {
		FileWriter fileWriter = new FileWriter("rejected.txt", true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);
		
		printWriter.print(reasonRejected);
		printWriter.print(p.getProductID() + ",");
		printWriter.print(p.getDescription() + ",");
		printWriter.print(p.getYearMinimum() + ",");
		printWriter.print(p.getYearMaximum() + ",");
		printWriter.print(p.getMake() + ",");
		printWriter.print(p.getModel() + ",");
		printWriter.print(p.getSupplierPrice() + ",");
		printWriter.print(p.getSellPrice() + ",");
		printWriter.print(p.getCoreCharge() + ",");
		printWriter.print(p.getCompatibilityNumber() + ",");
		printWriter.print(p.getCompanyID() + ",");
		printWriter.print(p.getMinQuantityInStock() + ",");
		printWriter.print(p.getMaxQuantityInStock() + ",");
		printWriter.print(p.getWarehouseLocation() + ",");
		printWriter.println(quantityRejected + "\n");
		printWriter.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method creates an order for products that have fallen below inventory
	 * 		minimums by writing the order to "products.txt".  
	 * @param	productID			String to identify the product being ordered
	 * @param	quantityToOrder		String to specify how many items to order
	 * Written by Rick Stuart
	 */
	public void writeProductOrder(String productID, String quantityToOrder) {
		
		Product p;
		
		readerDAO = DAOFactory.getReaderDAO();
		p = readerDAO.lookupProduct(productID);
		
		try {
			FileWriter fileWriter = new FileWriter("products.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);
			
			printWriter.print(p.getProductID() + ",");
			printWriter.print(p.getDescription() + ",");
			printWriter.print(p.getYearMinimum() + ",");
			printWriter.print(p.getYearMaximum() + ",");
			printWriter.print(p.getMake() + ",");
			printWriter.print(p.getModel() + ",");
			printWriter.print(p.getSupplierPrice() + ",");
			printWriter.print(p.getSellPrice() + ",");
			printWriter.print(p.getCoreCharge() + ",");
			printWriter.print(p.getCompatibilityNumber() + ",");
			printWriter.print(p.getCompanyID() + ",");
			printWriter.print(p.getMinQuantityInStock() + ",");
			printWriter.print(p.getMaxQuantityInStock() + ",");
			printWriter.print(p.getWarehouseLocation() + ",");
			printWriter.println(quantityToOrder);
			printWriter.close();
			}
			catch(IOException e) {
				System.out.println(e);
			}
	}
}
