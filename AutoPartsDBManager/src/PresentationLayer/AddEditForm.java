package PresentationLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package autopartsstoregui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
/**
*
* @author Michael
*/
public class AddEditForm extends JDialog{
   
   private static final String EMAIL_REGEX = 
   "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
       "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
   private static final Pattern EMAIL_PATTERN = 
                            Pattern.compile(EMAIL_REGEX);
   
   private JTextField firstNameField;
   private JTextField lastNameField;
   private JTextField emailField;
   private JButton confirmButton;
   private JButton cancelButton;
   
   //private Customer customer = new Customer();
   
   public AddEditForm(java.awt.Frame parent, String title, boolean modal) {
       super(parent, title, modal);
       initComponents();
   }
   
   //public AddEditForm(java.awt.Frame parent, String title, boolean modal, Customer customer) {
   //    this(parent, title, modal);
   //    this.customer = customer;
   //    confirmButton.setText("Save");
   //    firstNameField.setText(customer.getFirstName());
   //    lastNameField.setText(customer.getLastName());
   //    emailField.setText(customer.getEmail());
   //    emailField.setEditable(false);
   //}
   
   private void initComponents() {
       firstNameField = new JTextField();
       lastNameField = new JTextField();
       emailField = new JTextField();
       cancelButton = new JButton();
       confirmButton = new JButton();
       
       setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      
       Dimension longField = new Dimension(300, 20);
       firstNameField.setPreferredSize(longField);
       firstNameField.setMinimumSize(longField);
       lastNameField.setPreferredSize(longField);
       lastNameField.setMinimumSize(longField);
       emailField.setPreferredSize(longField);
       emailField.setMinimumSize(longField);
       
       cancelButton.setText("Cancel");
       cancelButton.addActionListener((ActionEvent) -> {
           cancelButtonActionPerformed();
       });
       
       confirmButton.setText("Add");
       confirmButton.addActionListener((ActionEvent) -> {
           confirmButtonActionPerformed();
       });
       
       JPanel customerPanel = new JPanel();
       customerPanel.setLayout(new GridBagLayout());
       customerPanel.add(new JLabel("First Name:"), getConstraints(0, 0, GridBagConstraints.LINE_END));
       customerPanel.add(firstNameField, getConstraints(1, 0, GridBagConstraints.LINE_START));
       customerPanel.add(new JLabel("Last Name:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
       customerPanel.add(lastNameField, getConstraints(1, 1, GridBagConstraints.LINE_START));
       customerPanel.add(new JLabel("Email:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
       customerPanel.add(emailField, getConstraints(1, 2, GridBagConstraints.LINE_START));
       
       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
       buttonPanel.add(confirmButton);
       buttonPanel.add(cancelButton);
       
       setLayout(new BorderLayout());
       add(customerPanel, BorderLayout.CENTER);
       add(buttonPanel, BorderLayout.SOUTH);
       pack();
   }
   
   private GridBagConstraints getConstraints(int x, int y, int anchor) {
       GridBagConstraints c = new GridBagConstraints();
       c.insets = new Insets(5,5,0,5);
       c.gridx = x;
       c.gridy = y;
       c.anchor = anchor;
       return c;
   }
   
   private void cancelButtonActionPerformed() {
       dispose();
   }
   
   private void confirmButtonActionPerformed() {
       if (validateData()) {
           setData();
           if (confirmButton.getText().equals("Add")) {
               doAdd();
           }
           else 
           {
               doEdit();
           }
       }
   }
   
   private boolean isEmpty()
   {
       String firstName = firstNameField.getText();
       String lastName = lastNameField.getText();
       String email = emailField.getText();
       
       if (firstName.equals("") || lastName.equals("") || email.equals("") 
               || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) 
       {
           JOptionPane.showMessageDialog(this, "Please fill in all fields.",
                   "Missing Fields", JOptionPane.INFORMATION_MESSAGE);
           return false;
       }
       else
           return true;
   }
   
   private boolean validateData() {
       
       boolean valid = false;
       String email = emailField.getText();
       
       if (confirmButton.getText().equals("Add")) 
       {
           if(isEmpty())
               if(emailValidator(email)) 
                   if(customerNotExists(email))
                       valid = true;
       }
       else 
       {
           if(isEmpty())
              valid = true;
       }
       
       return valid;
   }

   private boolean customerNotExists(String email)
   {
       
   boolean valid = false;
   
   //List<Customer> customers;
   //try 
   //{
   //    customers = CustomerDB.getCustomers();
   //    
   //    if (customers.isEmpty())
   //        return true;
   //    
   //    for (Customer c : customers)
   //        {
   //            if (c.getEmail().equalsIgnoreCase(emailField.getText()))
   //            {
   //                JOptionPane.showMessageDialog(this, "A customer already has that email address. \nPlease"
   //                           + " enter a different email address.",
   //                 "Invalid Email", JOptionPane.ERROR_MESSAGE);
   //                emailField.grabFocus();
   //                valid = false;
   //            }
   //            else
   //                valid = true;
   //        }
   //}
   //catch (DBException e)
   //{
   //    System.out.println(e);
   //}


       return valid;
   }
   
   private void setData() {
       String firstName = firstNameField.getText();
       String lastName = lastNameField.getText();
       String email = emailField.getText();
       //customer.setFirstName(firstName);
       //customer.setLastName(lastName);
       //customer.setEmail(email);
   }
   
   private void doEdit() {
       //try {
           //CustomerDB.updateCustomer(customer);
           dispose();
           fireDatabaseUpdatedEvent();
       //}
       //catch (DBException e)
       //{
           //System.out.println(e);
       //}
   }
    
   private void doAdd() {
       //try {
       //    CustomerDB.addCustomer(customer);
       //    dispose();
       //    fireDatabaseUpdatedEvent();
       //}
       //catch (DBException e)
       //{
       //    System.out.println(e);
       //}
   }
   
   private void fireDatabaseUpdatedEvent() {
       //CustomerManagerFrame mainWindow = (CustomerManagerFrame) getOwner();
       //mainWindow.fireDatabaseUpdatedEvent();
   }
      
   
   private boolean emailValidator(String email)
   {
       if (email == null) 
           return false;        

       Matcher matcher = EMAIL_PATTERN.matcher(email);
       if (matcher.matches())
           return true;
       else
       {
           JOptionPane.showMessageDialog(this, "Invalid email address entered. \nPlease"
                       + " enter an email address in the format of xxxxxxxxxx@xxxxxx.xxx",
                   "Invalid Email", JOptionPane.ERROR_MESSAGE);
           emailField.grabFocus();
           return false;
       
       }
   }
}
   
