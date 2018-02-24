package PresentationLayer;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ValidateInteger {
	
	
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
