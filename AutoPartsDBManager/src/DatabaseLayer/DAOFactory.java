package DatabaseLayer;

/**
 * Class Name:			DAOFactory
 * Description:			This class provides the methods that return data access objects 
 * 						which implement the DAO interfaces.
 * @author Craig Mathes, Michael Meesseman, Richard Stuart
 * @created Saturday, 1,20,2018
 */

public class DAOFactory {

	/**
	 * Uses the WriterDAO interface to return an instance of the DatabaseWriter class.
	 * @return  wDAO	a writer data access object.
	 * Written by Rick Stuart
	 */
	public static WriterDAO getWriterDAO() {
		
		WriterDAO wDAO = new DatabaseWriter();
		return wDAO;
	}
	
	/**
	 * Uses the ReaderDAO interface to return an instance of the DatabaseReader class.
	 * @return  rDAO	a reader data access object.
	 * Written by Rick Stuart
	 */
	public static ReaderDAO getReaderDAO() {
		
		DatabaseReader rDAO = new DatabaseReader();
		return rDAO;
	}
	
	/**
	 * Uses the RFIDDAO interface to return an instance of the RFIDReader class
	 * @return	rfidTextDAO		an RFID reader object
	 * Written by Rick Stuart
	 */
	public static RFIDDAO getRFIDDAO() {
		
		RFIDTextReader rfidTextDAO = new RFIDTextReader();
		return rfidTextDAO;
	}
}
