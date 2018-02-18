package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import BusinessLayer.InvoiceLineItem;
import DatabaseLayer.DatabaseReader;

public class LoginFrame extends JFrame {
	
	 private JTextField usernameField;
	 private JTextField passwordField;
	 private JButton loginButton;
	 private JButton cancelButton;
	
	public LoginFrame() throws UnsupportedLookAndFeelException, DBException, SQLException {
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
    
    private JPanel buildButtonPanel() throws DBException {
        JPanel panel = new JPanel();
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent) -> {
            try {
				doLoginButton();
			} catch (UnsupportedLookAndFeelException | DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(loginButton);
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent) -> {
            dispose();
        });
    
        panel.add(exitButton);
        
        return panel;
        
    }
    
    private void doLoginButton() throws UnsupportedLookAndFeelException, DBException {
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
			if (password.equals(DatabaseReader.obtainPassword(usernameField.getText())))
			{
				
				AutoPartsStoreFrame gui = new AutoPartsStoreFrame();
			}
				else
				JOptionPane.showMessageDialog(this, "Username or password was incorrect.",
                    "Invalid Login", JOptionPane.INFORMATION_MESSAGE);
    		}
    	
    	
    	
    	
    }
    
   private JPanel addLoginFields() {
	   
	   usernameField = new JTextField();
       passwordField = new JTextField();
       cancelButton = new JButton();
       loginButton = new JButton();
       
       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      
       Dimension longField = new Dimension(300, 20);
       usernameField.setPreferredSize(longField);
       usernameField.setMinimumSize(longField);
       passwordField.setPreferredSize(longField);
       passwordField.setMinimumSize(longField);
       
       JPanel loginPanel = new JPanel();
       loginPanel.setLayout(new GridBagLayout());
       loginPanel.add(new JLabel("Username:"), getConstraints(0, 0, GridBagConstraints.LINE_END));
       loginPanel.add(usernameField, getConstraints(1, 0, GridBagConstraints.LINE_START));
       loginPanel.add(new JLabel("Password:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
       loginPanel.add(passwordField, getConstraints(1, 1, GridBagConstraints.LINE_START));
       
       //setLayout(new BorderLayout());
       //add(loginPanel, BorderLayout.CENTER);
       //pack();
       
       return loginPanel;
   }
   
   private GridBagConstraints getConstraints(int x, int y, int anchor) {
       GridBagConstraints c = new GridBagConstraints();
       c.insets = new Insets(5,5,0,5);
       c.gridx = x;
       c.gridy = y;
       c.anchor = anchor;
       return c;
   }
   

   
  

}
