package PresentationLayer;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class FocusListner implements FocusListener{
	
	
		  private final JTextField textField;
		  
		  FocusListner(JTextField textField){
		    this.textField = textField;
		    textField.addFocusListener(this);
		  }
		  public void focusGained(FocusEvent e) 
		  {
		    textField.setText("");
		    textField.setForeground(Color.BLACK);
		  }

		  public void focusLost(FocusEvent e)
		  {
		    // nothing
		  }
		

}
