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

/**
 * Extends JFrame to build a frame for Companies supplying products.
 * Calls several methods to build the frame.
 * Written by Michael Meesseman
 */
public class CompanyFrame extends JFrame{
	
		// table variables initialized
	    private JTable companyTable;
	    private CompanyTableModel companyTableModel;
	    
	  //Search field initialized
	    private JTextField searchField;
	    private JComboBox searchCombo;
	    
	    /**
	     * Constructor to build the frame.
	     * @exception UnsupportedLookAndFeelException	Handles multiple operating system configs.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    public CompanyFrame() throws UnsupportedLookAndFeelException, SQLException {
	        try {
	            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
	        }
	        catch (ClassNotFoundException | InstantiationException 
	                | IllegalAccessException | UnsupportedLookAndFeelException e) {
	            System.out.println(e);
	        }
	        setTitle("Company Information");
	        setSize(768, 384);
	        setLocationByPlatform(true);
	        
	        
	        add(buildButtonPanel(), BorderLayout.SOUTH);
	        companyTable = buildCompanyTable();
	        add(new JScrollPane(companyTable), BorderLayout.CENTER);
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
	    
	        // add button
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
	        editButton.setToolTipText("Edit selected company");
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
	        
	       
	        // help button
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
	    	CompanyForm companyForm = new CompanyForm(this, "Add company", true);
	        companyForm.setLocationRelativeTo(this);
	        companyForm.setVisible(true);
	    }
	    
	    /**
	     * Method executes when edit button is pressed
	     * * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */ 
	    private void doEditButton() throws SQLException {
	    	int selectedRow = companyTable.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(this, "No Company is currently "
	                    + "selected.", "No company selected", JOptionPane.ERROR_MESSAGE);
	        }
	        else
	        {
	            BusinessLayer.Company company = companyTableModel.getCompanies(selectedRow);
	            CompanyForm companyForm = new CompanyForm(this, "Edit Company", true, company);
	            companyForm.setLocationRelativeTo(this);
	            companyForm.setVisible(true);
	        }
	    }
	    
	    
	    /**
	     * Method executes when help button is pressed.
	     * Written by Michael Meesseman
	     */
	    private void doHelpButton()
	    {
	    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a company. \n"
	                + "Press the 'Edit' button after selecting a company to edit their name. \n"
	                + "Press the 'Exit' button to exit the program.", 
	                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    /**
	     * Method to refresh the table from the database.
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	    public void fireDatabaseUpdatedEvent() throws SQLException {
	    	companyTableModel.databaseUpdated();
	    }
	       
	    /**
	     * Method to build the frame table that goes in center.
	     * @return table	JTable to populate database results
	     * @exception SQLException	exception for database queries.
	     * Written by Michael Meesseman
	     */
	   private JTable buildCompanyTable() throws SQLException {
	        companyTableModel = new CompanyTableModel();
	        JTable table = new JTable((javax.swing.table.TableModel) companyTableModel);
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
		   String[] fields = {"Company ID", "Address ID", "Contact Info ID", 
		    		"Company Name", "Street Address", "City", "State", "Zip Code", 
		    		"Unit Number", "Home Phone", "Cell Phone", "Email Address"};
		   
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
			   column = "company_id";
			   break;
		   case 1:
			   column = "Address_address_id";
			   break;
		   case 2:
			   column = "contact_info_contact_info_id";
			   break;
		   case 3:
			   column = "company_name";
			   break;
		   case 4:
			   column = "street_address";
			   break;
		   case 5:
			   column = "city";
			   break;
		   case 6:
			   column = "state";
			   break;
		   case 7:
			   column = "zip_code";
			   break;
		   case 8:
			   column = "unit_number";
			   break;
		   case 9:
			   column = "phone_number";
			   break;
		   case 10: 
			   column = "cell_phone_number";
			   break;
		   case 11:
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
			   companyTableModel.reset();
		   else
			   companyTableModel.refresh(column, searchField.getText());
		   	if(companyTableModel.resultChecker(column, searchField.getText()).isEmpty()) {
	 	   		JOptionPane.showMessageDialog(this, "Company does not exist.  Please try another search.", 
	 	                    "Search Input does not Exist.", JOptionPane.INFORMATION_MESSAGE);
	 	   		companyTableModel.reset();
	 	   		}
	   }
	




}
