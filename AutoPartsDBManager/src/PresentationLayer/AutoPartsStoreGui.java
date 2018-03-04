/**
 * Class Name:					MainAutoPartsGUI
 * Description:					This class contains all the code and methods necessary to run
 * 								the main graphic user interface.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
package PresentationLayer;

import java.util.ArrayList;
import java.sql.SQLException;

import javax.swing.UnsupportedLookAndFeelException;

import DatabaseLayer.*;
import BusinessLayer.*;


public class AutoPartsStoreGui {

	private static WriterDAO writerDAO;
	private static RFIDDAO rfidDAO;
	private static ArrayList<Product> rfidProducts;
	
	/**
     * @param args the command line arguments
	 * @throws SQLException 
     */
    @SuppressWarnings("unused")
	public static void main(String[] args) throws UnsupportedLookAndFeelException, SQLException {
        	
    	LoginFrame loginFrame = new LoginFrame();
    	initialize();
		writerDAO = DAOFactory.getWriterDAO();
		rfidDAO = DAOFactory.getRFIDDAO();
		
		// read any incoming products
		rfidProducts = rfidDAO.ProductTextFile();
				
		if(rfidProducts != null) {		
			writerDAO.writeIncomingProducts(rfidProducts);
		}
    }
	
	private static void initialize() {
	}
}
