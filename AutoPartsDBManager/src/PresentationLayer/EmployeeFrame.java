package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

import BusinessLayer.Customer;
import BusinessLayer.Employee;
import DatabaseLayer.DatabaseReader;


public class EmployeeFrame extends JFrame {


	    private JTable employeeTable;
	    private EmployeeTableModel employeeTableModel;
	    
	    private JTextField searchField;
	    private JComboBox searchCombo;
	    
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
	    
	    private JPanel buildButtonPanel() throws SQLException {
	        JPanel panel = new JPanel();
	    
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

	        
	        JButton helpButton = new JButton("Help");
	        helpButton.addActionListener((ActionEvent) -> {
	            doHelpButton();
	        });
	    
	        panel.add(helpButton);
	         
	        JButton exitButton = new JButton("Exit");
	        exitButton.addActionListener((ActionEvent) -> {
	            dispose();
	        });
	    
	        panel.add(exitButton);
	        
	        return panel;
	        
	    }
	    
	    private void doAddButton() {
	    	EmployeeForm employeeForm = new EmployeeForm(this, "Add Employee", true);
	        employeeForm.setLocationRelativeTo(this);
	        employeeForm.setVisible(true);
	    }
	    
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
	    
	   
	    
	    private void doHelpButton()
	    {
	    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a employee. \n"
	                + "Press the 'Edit' button after selecting a employee to edit their name. \n"
	                + "Press the 'Exit' button to exit the program.", 
	                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    public void fireDatabaseUpdatedEvent() throws SQLException {
	    	employeeTableModel.databaseUpdated();
	    }
	       
	   private JTable buildEmployeeTable() throws SQLException {
	        employeeTableModel = new EmployeeTableModel();
	        JTable table = new JTable((javax.swing.table.TableModel) employeeTableModel);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setBorder(null);
	        return table;
	   }
	   
	   private JPanel buildSearchPanel() {
		   
		   String[] fields = {"Employee ID", "Last Name", 
		    		"First Name", "Contact Info ID", "Address ID", "Street Address", 
		    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
		    		"Email Address"};
		   
		   JPanel panel = new JPanel();
		   
		   searchField = new JTextField();
		   Dimension longField = new Dimension(300, 20);
	       searchField.setPreferredSize(longField);
	       searchField.setMinimumSize(longField);
	       
	       panel.add(searchField);
	       
	       searchCombo = new JComboBox(fields);
	       panel.add(searchCombo);
	       
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
	   
	   private void doSearchButton() throws SQLException {
		   
		   String column;
		   
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
