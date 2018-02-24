package PresentationLayer;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ValidateDouble {
	
		
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
