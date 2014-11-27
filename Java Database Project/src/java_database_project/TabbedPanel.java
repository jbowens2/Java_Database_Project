package java_database_project;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;

public class TabbedPanel extends JTabbedPane{
	
	private static final long serialVersionUID = 1L;
	private String[] states = { "MD","DC" };
	private JTextField new_troop_name;
	private JTextField new_troop_city;
	private JTextField new_troop_address;
	private JTextField new_troop_state_1;
	private JTextField new_scout_firstname;
	private JTextField new_scout_lastname;
	private JTextField new_scout_address;
	private JTextField new_scout_city;
	private JTextField new_scout_zipcode;
	private JTextField new_product_name;
	private JTextField new_product_price;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private JTable ProductTable;
	private JTable ScoutTable;
	private JTextArea new_product_description;
	private JTextField new_volunteer_firstname;
	private JTextField new_volunteer_lastname;
	private JTextField new_volunteer_phone;
	private final Action action_2 = new SwingAction_2();

	public TabbedPanel (){	
		setSize(800, 800);
		
		JPanel dashboard = new JPanel();
		dashboard.setBackground(Color.LIGHT_GRAY);
		addTab("Dashboard", null, dashboard, null);
		dashboard.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 30, 800, 200);
		dashboard.add(scrollPane);
		
		ProductTable = new JTable();
		scrollPane.setViewportView(ProductTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 230, 800, 200);
		dashboard.add(scrollPane_1);
		
		ScoutTable = new JTable();
		scrollPane_1.setViewportView(ScoutTable);
		
		JPanel new_scout = new JPanel();
		new_scout.setBackground(Color.LIGHT_GRAY);
		new_scout.setForeground(Color.GRAY);
		addTab("New Scout", null, new_scout, null);
		new_scout.setLayout(null);
		
		JLabel lblN = new JLabel("New Scout Information");
		lblN.setHorizontalAlignment(SwingConstants.LEFT);
		lblN.setBounds(29, 25, 334, 16);
		new_scout.add(lblN);
		
		JComboBox new_scout_troop = new JComboBox();
		new_scout_troop.setBounds(126, 77, 193, 27);
		new_scout.add(new_scout_troop);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setBounds(29, 123, 85, 16);
		new_scout.add(lblFirstname);
		
		new_scout_firstname = new JTextField();
		new_scout_firstname.setBounds(126, 116, 237, 30);
		new_scout.add(new_scout_firstname);
		new_scout_firstname.setColumns(10);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setBounds(364, 123, 80, 16);
		new_scout.add(lblLastname);
		
		new_scout_lastname = new JTextField();
		new_scout_lastname.setBounds(456, 117, 237, 30);
		new_scout.add(new_scout_lastname);
		new_scout_lastname.setColumns(10);
		
		JLabel lblTroop = new JLabel("Troop:");
		lblTroop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTroop.setBounds(53, 81, 61, 16);
		new_scout.add(lblTroop);
		
		JLabel lblAddress_1 = new JLabel("Address:");
		lblAddress_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress_1.setBounds(53, 165, 61, 16);
		new_scout.add(lblAddress_1);
		
		new_scout_address = new JTextField();
		new_scout_address.setBounds(126, 158, 567, 30);
		new_scout.add(new_scout_address);
		new_scout_address.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCity.setBounds(53, 207, 61, 16);
		new_scout.add(lblCity);
		
		new_scout_city = new JTextField();
		new_scout_city.setBounds(126, 200, 181, 30);
		new_scout.add(new_scout_city);
		new_scout_city.setColumns(10);
		
		JLabel lblState_1 = new JLabel("State:");
		lblState_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState_1.setBounds(316, 207, 61, 16);
		new_scout.add(lblState_1);
		
		JComboBox new_scout_state = new JComboBox(states);
		new_scout_state.setBounds(389, 200, 68, 30);
		new_scout.add(new_scout_state);
		
		JLabel lblZipcode_1 = new JLabel("Zipcode:");
		lblZipcode_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZipcode_1.setBounds(488, 207, 61, 16);
		new_scout.add(lblZipcode_1);
		
		new_scout_zipcode = new JTextField();
		new_scout_zipcode.setBounds(562, 200, 131, 30);
		new_scout.add(new_scout_zipcode);
		new_scout_zipcode.setColumns(10);
		
		JButton cancel_new_scout = new JButton("Cancel");
		cancel_new_scout.setBounds(126, 259, 117, 29);
		new_scout.add(cancel_new_scout);
		
		JButton save_new_scout = new JButton("Save");
		save_new_scout.setAction(action);
		save_new_scout.setBounds(576, 259, 117, 29);
		new_scout.add(save_new_scout);
		
		JPanel new_troop = new JPanel();
		new_troop.setBackground(Color.LIGHT_GRAY);
		addTab("New Troop", null, new_troop, null);
		new_troop.setLayout(null);
		
		JLabel new_troop_information = new JLabel("New Troop Information");
		new_troop_information.setVerticalAlignment(SwingConstants.TOP);
		new_troop_information.setBounds(29, 25, 336, 20);
		new_troop.add(new_troop_information);
		
		JLabel lblTroopName = new JLabel("Troop Name:");
		lblTroopName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTroopName.setBounds(30, 119, 84, 25);
		new_troop.add(lblTroopName);
		
		new_troop_name = new JTextField();
		new_troop_name.setBounds(126, 119, 602, 25);
		new_troop.add(new_troop_name);
		new_troop_name.setColumns(10);
		
		JLabel lblTroopCity = new JLabel("City:");
		lblTroopCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTroopCity.setBounds(37, 198, 77, 14);
		new_troop.add(lblTroopCity);
		
		new_troop_city = new JTextField();
		new_troop_city.setBounds(126, 192, 242, 25);
		new_troop.add(new_troop_city);
		new_troop_city.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState.setBounds(374, 198, 46, 14);
		new_troop.add(lblState);
		
		
		JComboBox new_troop_state = new JComboBox(states);
		new_troop_state.setBounds(432, 196, 92, 20);
		new_troop_state.setSelectedIndex(0);
		new_troop.add(new_troop_state);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(53, 156, 61, 16);
		new_troop.add(lblAddress);
		
		new_troop_address = new JTextField();
		new_troop_address.setBounds(126, 155, 602, 25);
		new_troop.add(new_troop_address);
		new_troop_address.setColumns(10);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZipcode.setBounds(536, 197, 61, 16);
		new_troop.add(lblZipcode);
		
		new_troop_state_1 = new JTextField();
		new_troop_state_1.setBounds(609, 192, 119, 25);
		new_troop.add(new_troop_state_1);
		new_troop_state_1.setColumns(10);
		
		JLabel lblManager = new JLabel("Manager:");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManager.setBounds(53, 81, 61, 16);
		new_troop.add(lblManager);
		
		String[]  managers = {"Please select a manager"};
		JComboBox new_troop_manager_combo = new JComboBox(managers);
		new_troop_manager_combo.setSelectedIndex(0);
		new_troop_manager_combo.setBounds(126, 77, 224, 27);
		new_troop.add(new_troop_manager_combo);
		
		populateManager(new_troop_manager_combo);
		
		JButton save_new_troop = new JButton("Save");
		save_new_troop.setBounds(611, 258, 117, 29);
		new_troop.add(save_new_troop);
		
		JButton cancel_new_troop = new JButton("Cancel");
		cancel_new_troop.setBounds(126, 258, 117, 29);
		new_troop.add(cancel_new_troop);
		
		JPanel add_product = new JPanel();
		add_product.setBackground(Color.LIGHT_GRAY);
		addTab("Add Product", null, add_product, null);
		add_product.setLayout(null);
		
		JLabel label = new JLabel("New Product Information");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBounds(29, 25, 336, 20);
		add_product.add(label);
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName.setBounds(29, 72, 92, 16);
		add_product.add(lblProductName);
		
		new_product_name = new JTextField();
		new_product_name.setBounds(133, 66, 583, 28);
		add_product.add(new_product_name);
		new_product_name.setColumns(10);
		
		JLabel lblRetailPrice = new JLabel("Retail Price:");
		lblRetailPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRetailPrice.setBounds(29, 112, 92, 16);
		add_product.add(lblRetailPrice);
		
		new_product_price = new JTextField();
		new_product_price.setBounds(133, 106, 232, 28);
		add_product.add(new_product_price);
		new_product_price.setColumns(10);
		
		JLabel lblEnterADescription = new JLabel("Enter a description for the new product below");
		lblEnterADescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterADescription.setBounds(133, 182, 583, 16);
		add_product.add(lblEnterADescription);
		
		JButton cancel_new_product = new JButton("Cancel");
		cancel_new_product.setBounds(133, 616, 117, 29);
		add_product.add(cancel_new_product);
		
		JButton save_new_product = new JButton("Save");
		save_new_product.setAction(action_1);
		save_new_product.setBounds(599, 616, 117, 29);
		add_product.add(save_new_product);
		
		new_product_description = new JTextArea();
		new_product_description.setBounds(133, 210, 583, 153);
		add_product.add(new_product_description);
		new_product_description.setColumns(10);
		
		JPanel new_Volunteer = new JPanel();
		addTab("New Volunteer", null, new_Volunteer, null);
		new_Volunteer.setLayout(null);
		
		JLabel lblNewVolunteerInformation = new JLabel("New Volunteer Information");
		lblNewVolunteerInformation.setBounds(29, 25, 334, 16);;
		new_Volunteer.add(lblNewVolunteerInformation);
		
		JLabel lbltroop = new JLabel("Phone:");
		lbltroop.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltroop.setBounds(53, 81, 61, 16);
		new_Volunteer.add(lbltroop);;
		
		JLabel lblFirstname_1 = new JLabel("Firstname:");
		lblFirstname_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname_1.setBounds(29, 123, 85, 16);
		new_Volunteer.add(lblFirstname_1);
		
		new_volunteer_firstname = new JTextField();
		new_volunteer_firstname.setBounds(126, 116, 237, 30);
		new_Volunteer.add(new_volunteer_firstname);
		new_volunteer_firstname.setColumns(10);
		
		JLabel lblLastname_1 = new JLabel("Lastname:");
		lblLastname_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname_1.setBounds(364, 123, 80, 16);
		new_Volunteer.add(lblLastname_1);
		
		new_volunteer_lastname = new JTextField();
		new_volunteer_lastname.setBounds(456, 117, 237, 30);
		new_Volunteer.add(new_volunteer_lastname);
		new_volunteer_lastname.setColumns(10);
		
		new_volunteer_phone = new JTextField();
		new_volunteer_phone.setBounds(126, 75, 237, 28);
		new_Volunteer.add(new_volunteer_phone);
		new_volunteer_phone.setColumns(10);
		
		JButton cancel_new_volunteer = new JButton("Cancel");
		cancel_new_volunteer.setBounds(126, 195, 117, 29);
		new_Volunteer.add(cancel_new_volunteer);
		
		JButton save_new_volunteer = new JButton("Save");
		save_new_volunteer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		save_new_volunteer.setAction(action_2);
		save_new_volunteer.setBounds(576, 195, 117, 29);
		new_Volunteer.add(save_new_volunteer);
		
		loadData();	
	}
	
	public void populateManager(JComboBox combo){
		try{
			Main.makeConnection();
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM Volunteer");
			Main.result = Main.preparedStatement.executeQuery();
			while (Main.result.next()){
				combo.addItem(Main.result.getString("FIRSTNAME")+ " "+ Main.result.getString("LASTNAME"));
			}
			Main.closeConnection();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void loadData(){
		Main.makeConnection();
		try {
			Main.preparedStatement = Main.connection.prepareStatement("SELECT PRODUCT_NAME, PRODUCT_DESCRIPTION, RETAIL_PRICE FROM product");
			Main.result = Main.preparedStatement.executeQuery();
			ProductTable.setModel(DbUtils.resultSetToTableModel(Main.result));
			Main.preparedStatement = Main.connection.prepareStatement("SELECT FIRSTNAME, LASTNAME, PHONE, EMAIL FROM scout");
			Main.result = Main.preparedStatement.executeQuery();
			ScoutTable.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.closeConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e ,"Please make sure you have a connection to your database!",JOptionPane.WARNING_MESSAGE);
		}catch(Exception general){
			JOptionPane.showMessageDialog(null, general ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	public void resetTabs(){
		//int selectedTabIndex = this.getSelectedIndex();
		//Component selectedTabName = this.getComponentAt(selectedTabIndex);
		//this.removeTabAt(selectedTabIndex);
		//this.addTab(null, null, selectedTabName, null);
		this.setSelectedIndex(0);
		
	}
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Save Scout");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "Save Product");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			Main.makeConnection();
			try {
				Main.preparedStatement = Main.connection.prepareStatement("INSERT INTO product (`product_name`,`product_description`,`retail_price`) VALUES (?,?,?)");
				Main.preparedStatement.setString(1, new_product_name.getText());
				Main.preparedStatement.setString(2, new_product_description.getText());
				Main.preparedStatement.setFloat(3, Float.parseFloat(new_product_price.getText()));
				Main.preparedStatement.executeUpdate();
				loadData();
				resetTabs();
				Main.closeConnection();
				
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1 ,"Error!",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "Save Volunteer");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			Main.makeConnection();
			try{
				Main.preparedStatement = Main.connection.prepareStatement("INSERT INTO volunteer (`FIRSTNAME`, `LASTNAME`,`PHONE`) values(?,?,?)");
				Main.preparedStatement.setString(1, new_volunteer_firstname.getText());
				Main.preparedStatement.setString(2, new_volunteer_lastname.getText());
				Main.preparedStatement.setString(3, new_volunteer_phone.getText());
				Main.preparedStatement.execute();
				loadData();
				resetTabs();
				Main.closeConnection();
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2 ,"Error!",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
