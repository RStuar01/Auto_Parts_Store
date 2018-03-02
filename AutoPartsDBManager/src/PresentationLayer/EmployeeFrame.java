package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import BusinessLayer.Employee;

/**
 * Extends JFrame to build a frame for employee tracking.
 * Calls several methods to build the frame.
 * Written by Michael Meesseman
 */
public class EmployeeFrame extends JFrame {

		//table initalization
	    private JTable employeeTable;
	    private EmployeeTableModel employeeTableModel;
	    
	    //search field initialized.
	    private JTextField searchField;
	    private JComboBox searchCombo;
	    
	    /**
	     * Constructor to build the frame.
	     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    public EmployeeFrame() throws UnsupportedLookAndFeelException, SQLException {
	        try {
	            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
	        }
	        catch (ClassNotFoundException | InstantiationException 
	                | IllegalAccessException | UnsupportedLookAndFeelException e) {
	            System.out.println(e);
	        }
	        setTitle("Employee Information");
	        setSize(768, 384);
	        setLocationByPlatform(true);
	        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        add(buildButtonPanel(), BorderLayout.SOUTH);
	        employeeTable = buildEmployeeTable();
	        add(new JScrollPane(employeeTable), BorderLayout.CENTER);
	        add(buildSearchPanel(), BorderLayout.NORTH);
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
	    
	        //add button
	        JButton addButton = new JButton("Add");
	        addButton.addActionListener((ActionEvent) -> {
	            doAddButton();
	            try {
					fireDatabaseUpdatedEvent();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	    
	        panel.add(addButton);
	        
	        //edit button
	        JButton editButton = new JButton("Edit");
	        editButton.setToolTipText("Edit selected employee");
	        editButton.addActionListener((ActionEvent) -> {
	            try {
	                doEditButton();
	                fireDatabaseUpdatedEvent();
	            } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
	        panel.add(editButton);

	        //help button
	        JButton helpButton = new JButton("Help");
	        helpButton.addActionListener((ActionEvent) -> {
	            doHelpButton();
	        });
	    
	        panel.add(helpButton);
	         
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
	     * Written by Michael Meesseman
	     */
	    private void doAddButton() {
	    	EmployeeForm employeeForm = new EmployeeForm(this, "Add Employee", true);
	        employeeForm.setLocationRelativeTo(this);
	        employeeForm.setVisible(true);
	    }
	    
	    /**
	     * Method executes when edit button is pressed
	     * * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */ 
	    private void doEditButton() throws SQLException {
	    	int selectedRow = employeeTable.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(this, "No Employee is currently "
	                    + "selected.", "No Employee selected", JOptionPane.ERROR_MESSAGE);
	        }
	        else
	        {
	            Employee employee = employeeTableModel.getEmployee(selectedRow);
	            EmployeeForm employeeForm = new EmployeeForm(this, "Edit Customer", true, employee);
	            employeeForm.setLocationRelativeTo(this);
	            employeeForm.setVisible(true);
	        }
	    }
	    
	   
	    /**
	     * Method executes when help button is pressed.
	     * Written by Michael Meesseman
	     */
	    private void doHelpButton()
	    {
	    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a employee. \n"
	                + "Press the 'Edit' button after selecting a employee to edit their name. \n"
	                + "Press the 'Exit' button to exit the program.", 
	                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    /**
	     * Method to refresh the table from the database.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    public void fireDatabaseUpdatedEvent() throws SQLException {
	    	employeeTableModel.databaseUpdated();
	    }
	       
	    
	    /**
	     * Method to build the frame table that goes in center.
	     * @return table	JTable to populate database results
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	   private JTable buildEmployeeTable() throws SQLException {
	        employeeTableModel = new EmployeeTableModel();
	        JTable table = new JTable((javax.swing.table.TableModel) employeeTableModel);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setBorder(null);
	        return table;
	   }
	   
	   
	   /**
	    * Method to build the search panel.
	    * @return panel	panel which populates the NORTH end of frame.
	    * Written by Michael Meesseman
	    */
	   private JPanel buildSearchPanel() {
		   
		// drop down box fields
		   String[] fields = {"Employee ID", "Last Name", 
		    		"First Name", "Contact Info ID", "Address ID", "Street Address", 
		    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
		    		"Email Address"};
		   
		   JPanel panel = new JPanel();
		   
		 //text field initialize
		   searchField = new JTextField();
		   Dimension longField = new Dimension(300, 20);
	       searchField.setPreferredSize(longField);
	       searchField.setMinimumSize(longField);
	       
	       panel.add(searchField);
	       
	    // combo box initialize
	       searchCombo = new JComboBox(fields);
	       panel.add(searchCombo);
	       
	       
	     //search button
	       JButton searchButton = new JButton("Search");
	       searchButton.addActionListener((ActionEvent) -> {
	           try {
				doSearchButton();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       });
	   
	       panel.add(searchButton);
		   
		   return panel;
	   }
	   
	   /**
	    * Method executes when search button is pressed
	    * @exception SQLException	exception for database queries.
	    * Written by Michael Meesseman
	    */
	   private void doSearchButton() throws SQLException {
		   
		   String column;
		   
		// switch to set search parameter to database field name.
		   switch(searchCombo.getSelectedIndex())
		   {
		   case 0:
			   column = "employee_id";
			   break;
		   case 1:
			   column = "last_name";
			   break;
		   case 2:
			   column = "first_name";
			   break;
		   case 3:
			   column = "contact_info_contact_info_id";
			   break;
		   case 4:
			   column = "Address_address_id";
			   break;
		   case 5:
			   column = "street_address";
			   break;
		   case 6:
			   column = "city";
			   break;
		   case 7:
			   column = "state";
			   break;
		   case 8:
			   column = "zip_code";
			   break;
		   case 9: 
			   column = "unit_number";
			   break;
		   case 10:
			   column = "phone_number";
			   break;
		   case 11:
			   column = "cell_phone_number";
			   break;
		   case 12:
			   column = "email_address";
			   break;
		   default:
			   column = "";
			   break;
			   
		   }
		   
		// empty search field refreshes table to all entries.
	 	   // otherwise database is filled with query results from database.
	 	   // if search result does not return a result a dialog box notifies the user. 
		   if(searchField.getText().equals(""))
			   employeeTableModel.reset();
		   else
			   employeeTableModel.refresh(column, searchField.getText());
		   if(employeeTableModel.resultChecker(column, searchField.getText()).isEmpty()) {
	 	   		JOptionPane.showMessageDialog(this, "Employee does not exist.  Please try another search.", 
	 	                    "Search Input does not Exist.", JOptionPane.INFORMATION_MESSAGE);
	 	   		employeeTableModel.reset();
	 	   		}
	   }
	


	
	
}
