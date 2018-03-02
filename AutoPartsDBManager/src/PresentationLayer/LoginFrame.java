package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import BusinessLayer.InvoiceLineItem;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;

/**
 * Extends JFrame to build a frame for login.
 * Calls several methods to build the frame.
 * Written by Michael Meesseman
 */
public class LoginFrame extends JFrame {
	
	//field initialization
	 private JTextField usernameField;
	 private JPasswordField passwordField;
	 private JButton loginButton;
	 private JButton cancelButton;
	 private ReaderDAO readerDAO = DAOFactory.getReaderDAO();

	
	 
	 /**
	     * Constructor to build the frame.
	     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	public LoginFrame() throws UnsupportedLookAndFeelException, SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        
        setTitle("Auto Parts Store Login");
        setSize(748, 465);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.SOUTH);
        add(addLoginFields(), BorderLayout.CENTER);
        setVisible(true);
                
    }
    
	/**
     * Method to build the Button Panel.
     * @return panel	this is the button panel that goes to the SOUTH of the frame.
     * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private JPanel buildButtonPanel() throws SQLException {
        JPanel panel = new JPanel();
        
        //login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent) -> {
            try {
				doLoginButton();
			} catch (UnsupportedLookAndFeelException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(loginButton);
        
        //exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent) -> {
            dispose();
        });
    
        panel.add(exitButton);
        
        return panel;
        
    }
    
    /**
     * Method executes when add button is pressed
     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
	 * @exception SQLException	exception for database queries.
     * Written by Michael Meesseman
     */
    private void doLoginButton() throws UnsupportedLookAndFeelException, SQLException {
    	// Test login data
    	
     	
    	String password;
    	
    	
    
    		if (usernameField.getText().equals("") || passwordField.getText().equals(""))
    		{
    			JOptionPane.showMessageDialog(this, "Username or password was empty. Please enter values",
                        "Invalid Login", JOptionPane.INFORMATION_MESSAGE);
    		}
    		else
    		{
    		password = passwordField.getText();
			if (password.equals(readerDAO.obtainPassword(usernameField.getText())))
			{
				dispose();
				AutoPartsStoreFrame gui = new AutoPartsStoreFrame();
			}
				else
				JOptionPane.showMessageDialog(this, "Username or password was incorrect.",
                    "Invalid Login", JOptionPane.INFORMATION_MESSAGE);
    		}
    	
    	
    	
    	
    }
    
    /**
     * Method initailizes components and adds them to the frame.  
     * Written by Michael Meesseman
     */
   private JPanel addLoginFields() {
	   
	   // text fields
	   usernameField = new JTextField();
       passwordField = new JPasswordField();
       
       //buttons
       cancelButton = new JButton();
       loginButton = new JButton();
       
       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      
       //field sizes 
       Dimension longField = new Dimension(300, 20);
       usernameField.setPreferredSize(longField);
       usernameField.setMinimumSize(longField);
       passwordField.setPreferredSize(longField);
       passwordField.setMinimumSize(longField);
       
       //grid of labels and textfields 
       JPanel loginPanel = new JPanel();
       loginPanel.setLayout(new GridBagLayout());
       loginPanel.add(new JLabel("Username:"), getConstraints(0, 0, GridBagConstraints.LINE_END));
       loginPanel.add(usernameField, getConstraints(1, 0, GridBagConstraints.LINE_START));
       loginPanel.add(new JLabel("Password:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
       loginPanel.add(passwordField, getConstraints(1, 1, GridBagConstraints.LINE_START));
       
       passwordField.addActionListener(new ActionListener() {
    	   	public void actionPerformed(ActionEvent e) {
    	   		try {
					doLoginButton();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	   	}
       });
       
       return loginPanel;
       
   }

   /**
    * Method for setting grid of labels and fields.
    * @param x			x axis
    * @param y			y axis
    * @param anchor	where the field sits in the grid space ex. LINE_START or LINE_END.
    * @return c	GridBagConstraints variable for constraints on the grid.
    * Written by Michael Meesseman
    */
   private GridBagConstraints getConstraints(int x, int y, int anchor) {
       GridBagConstraints c = new GridBagConstraints();
       c.insets = new Insets(5,5,0,5);
       c.gridx = x;
       c.gridy = y;
       c.anchor = anchor;
       return c;
   }
   

   
  

}
