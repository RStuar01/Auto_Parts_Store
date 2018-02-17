package PresentationLayer;

import java.awt.EventQueue;

import DatabaseLayer.*;
import BusinessLayer.*;

/**
 * Class Name:					MainAutoPartsGUI
 * Description:					This class contains all the code and methods necessary to run
 * 								the main graphic user interface.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */
public class MainAutoPartsGUI {

	// Variable declaration area for GUI components - List things like the buttons, text boxes, etc.
	
	// Data access object variables  (these were changed to static)
	// They may need to be changed back later
	private static WriterDAO writerDAO;
	private static ReaderDAO readerDAO;
	private static DeleterDAO deleterDAO;
	
	// Other local variables
	
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello!");
		
		//Remove next line
		MainAutoPartsGUI();
		
		// ********** REMOVE THIS AND REPLACE with code for this program
		// This is an example of how it may be written from another program I wrote
		/**
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeopleManager window = new PeopleManager();	
					window.peopleManagerFrame.setVisible(true);
					window.peopleManagerFrame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		**/
		
		
	}
	
	//Remove later and replace with version below
	private static void MainAutoPartsGUI() {
		// TODO Auto-generated method stub
		initialize();
		writerDAO = DAOFactory.getWriterDAO();
		readerDAO = DAOFactory.getReaderDAO();
		deleterDAO = DAOFactory.getDeleterDAO();
	}
	
	
	// Constructor for the main gui
	/**
	 * Create the application items.
	 */
	/**
	public MainAutoPartsGUI() {
		initialize();
		writerDAO = DAOFactory.getWriterDAO();
		readerDAO = DAOFactory.getReaderDAO();
		deleterDAO = DAOFactory.getDeleterDAO();
	}
	**/
	
	// Custom methods used in this class
	
	
	// ******** Keep all gui components below this line***************************
	/**
	 * Initialize the contents of the frame.
	 */
	// Changed to static
	private static void initialize() {
		
		System.out.println("In main - initialize");
	}
}
