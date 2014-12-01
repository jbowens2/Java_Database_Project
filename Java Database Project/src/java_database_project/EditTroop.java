package java_database_project;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
//import java.sql.Date;

import java.sql.SQLException;


import javax.swing.Action;



import net.proteanit.sql.DbUtils;

public class EditTroop extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField address;
	private JTextField city;
	private JTextField zipcode;
	
	private static JComboBox state_combo;
	private static JComboBox manager;
	private String[] states = { "MD","DC" };
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private JTextField name;

	public EditTroop(){
		//custom variables
		this.setTitle("Edit Troop Information");
		this.setSize(new Dimension(500, 400));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel new_troop_panel = new JPanel();
		new_troop_panel.setBounds(0, 0, 500, 378);
		getContentPane().add(new_troop_panel);
		new_troop_panel.setLayout(null);
		
		JLabel lblTroop = new JLabel("Manager:");
		lblTroop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTroop.setBounds(30, 25, 61, 16);
		new_troop_panel.add(lblTroop);
		
		manager = new JComboBox();
		manager.setBounds(103, 21, 134, 27);
		new_troop_panel.add(manager);
		
		populateManager(manager);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(30, 106, 61, 16);
		new_troop_panel.add(lblAddress);
		
		address = new JTextField();
		address.setBounds(103, 100, 367, 28);
		new_troop_panel.add(address);
		address.setColumns(10);
		
		city = new JTextField();
		city.setBounds(103, 140, 97, 28);
		new_troop_panel.add(city);
		city.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCity.setBounds(30, 146, 61, 16);
		new_troop_panel.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setHorizontalAlignment(SwingConstants.RIGHT);
		lblState.setBounds(202, 146, 35, 16);
		new_troop_panel.add(lblState);
		
		zipcode = new JTextField();
		zipcode.setBounds(400, 140, 70, 28);
		new_troop_panel.add(zipcode);
		zipcode.setColumns(10);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZipcode.setBounds(327, 146, 61, 16);
		new_troop_panel.add(lblZipcode);
		
		JButton cancel = new JButton("Cancel");
		cancel.setAction(action_1);
		cancel.setBounds(103, 190, 117, 29);
		new_troop_panel.add(cancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setAction(action);
		btnSave.setBounds(353, 190, 117, 29);
		new_troop_panel.add(btnSave);
		
		state_combo = new JComboBox(states);
		state_combo.setBounds(249, 140, 75, 27);
		new_troop_panel.add(state_combo);
		
		name = new JTextField();
		name.setBounds(103, 60, 367, 28);
		new_troop_panel.add(name);
		name.setColumns(10);
		
		JLabel lblTroopName = new JLabel("Troop Name:");
		lblTroopName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTroopName.setBounds(6, 66, 85, 16);
		new_troop_panel.add(lblTroopName);
		this.setVisible(true);
		
		setData();
	}
	
	public void populateManager(JComboBox combo){
		try{
			Main.makeConnection();
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM VOLUNTEER");
			Main.result = Main.preparedStatement.executeQuery();
			while (Main.result.next()){
				combo.addItem(Main.result.getString("VOLUNTEER_ID"));
			}
			Main.closeConnection();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void setData(){
		manager.setSelectedItem(TabbedPanel.temp_troop_manager);
		name.setText(TabbedPanel.temp_troop_name);
		address.setText(TabbedPanel.temp_troop_address);
		city.setText(TabbedPanel.temp_troop_city);
		state_combo.setSelectedItem(TabbedPanel.temp_troop_state);
		zipcode.setText(TabbedPanel.temp_troop_zipcode);
	}
	
	public void loadData(){
		Main.makeConnection();
		try {
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM TROOP");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.troop_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.closeConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e ,"Please make sure you have a connection to your database!",JOptionPane.WARNING_MESSAGE);
		}catch(Exception general){
			JOptionPane.showMessageDialog(null, general ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public JFrame getFrame(){
		return this;
	}
	
	
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Save");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			Main.makeConnection();
			try{
				Main.preparedStatement = Main.connection.prepareStatement("UPDATE TROOP SET NAME = ?, ADDRESS = ?, CITY = ?, STATE = ?, ZIPCODE = ?, MANAGER = ? WHERE TROOP_ID = ?");
				Main.preparedStatement.setString(1, name.getText());
				
				Main.preparedStatement.setString(2, address.getText());
				
				Main.preparedStatement.setString(3, city.getText());
				
				String stateValue = state_combo.getSelectedItem().toString();
				Main.preparedStatement.setString(4, stateValue);
				
				Main.preparedStatement.setString(5, zipcode.getText());
				
				String managerValue = manager.getSelectedItem().toString();
				Main.preparedStatement.setInt(6, Integer.valueOf(managerValue));
				
				Main.preparedStatement.setString(7, TabbedPanel.temp_troop_id);
				
				Main.preparedStatement.execute();
				
				loadData();
				//resetTabs();
				Main.closeConnection();
				getFrame().dispose();	
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null, e2 ,"Error!",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "Cancel");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			getFrame().dispose();
		}
	}
}
