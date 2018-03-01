package PresentationLayer;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Class for double validation
 * Written by Michael Meesseman
 */
public class ValidateDouble {
	
	/**
	 * method validates textfield and displays dialog box when error
	 * @param textField		input textfield to be validated
	 * @param dailog		input dialog box to display message into.
	 * @return boolean		returns whether a field is a double or not. 
	 * Written by Michael Meesseman
	 */
	static boolean validateDouble(JTextField textField, JDialog dialog) {
		
		try
		{
			Double.parseDouble(textField.getText());
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(dialog, "Invalid input entered in " 
		+ textField.getName() + " .  Please enter a valid double.",
                    "Invalid Input", JOptionPane.INFORMATION_MESSAGE);	
			return false;
		}
		
		return true;
		
	}

}
