package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.function.Supplier;
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
import javax.swing.table.TableModel;

import BusinessLayer.Customer;


public class CompanyFrame extends JFrame{
	
	
	
	    private JTable companyTable;
	    private CompanyTableModel companyTableModel;
	    
	    private JTextField searchField;
	    private JComboBox searchCombo;
	    
	    public CompanyFrame() throws UnsupportedLookAndFeelException, DBException, SQLException {
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
	        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        add(buildButtonPanel(), BorderLayout.SOUTH);
	        companyTable = buildCompanyTable();
	        add(new JScrollPane(companyTable), BorderLayout.CENTER);
	        add(buildSearchPanel(), BorderLayout.NORTH);
	        setVisible(true);
	                
	    }
	    
	    private JPanel buildButtonPanel() throws DBException {
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
	        editButton.setToolTipText("Edit selected company");
	        editButton.addActionListener((ActionEvent) -> {
	            try {
	                doEditButton();
	                fireDatabaseUpdatedEvent();
	            } catch (DBException e) {
	                System.out.println(e);
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
	    	CompanyForm companyForm = new CompanyForm(this, "Add company", true);
	        companyForm.setLocationRelativeTo(this);
	        companyForm.setVisible(true);
	    }
	    
	    private void doEditButton() throws DBException, SQLException {
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
	    
	    
	    
	    private void doHelpButton()
	    {
	    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a company. \n"
	                + "Press the 'Edit' button after selecting a company to edit their name. \n"
	                + "Press the 'Exit' button to exit the program.", 
	                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    public void fireDatabaseUpdatedEvent() throws SQLException {
	    	companyTableModel.databaseUpdated();
	    }
	       
	   private JTable buildCompanyTable() throws SQLException {
	        companyTableModel = new CompanyTableModel();
	        JTable table = new JTable((javax.swing.table.TableModel) companyTableModel);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setBorder(null);
	        return table;
	   }
	   
	 private JPanel buildSearchPanel() {
		   
		   String[] fields = {"Company ID", "Address ID", "Contact Info ID", 
		    		"Company Name", "Street Address", "City", "State", "Zip Code", 
		    		"Unit Number", "Home Phone", "Cell Phone", "Email Address"};
		   
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
