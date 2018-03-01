package PresentationLayer;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Class for integer validation
 * Written by Michael Meesseman
 */
public class ValidateInteger {
	
	/**
	 * method validates textfield and displays dialog box when error
	 * @param textField		input textfield to be validated
	 * @param dailog		input dialog box to display message into.
	 * @return boolean		returns whether a field is a integer or not. 
	 * Written by Michael Meesseman
	 */
	static boolean validateInteger(JTextField textField, JDialog dialog) {
		
		try
		{
			Integer.parseInt(textField.getText());
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(dialog, "Invalid input entered in " 
		+ textField.getName() + " .  Please enter a valid interger.",
                    "Invalid Input", JOptionPane.INFORMATION_MESSAGE);	
			return false;
		}
		
		return true;
		
	}

}
