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
import java.sql.SQLException;

import javax.swing.Action;

import net.proteanit.sql.DbUtils;

public class NewCustomer extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField phone;
	private JTextField email;
	@SuppressWarnings("rawtypes")
	private JComboBox Scout_Combo;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	@SuppressWarnings("rawtypes")
	public NewCustomer(){
		//custom variables
		this.setTitle("New Customer Information");
		this.setSize(new Dimension(500, 400));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel new_scout_panel = new JPanel();
		new_scout_panel.setBounds(0, 0, 500, 378);
		getContentPane().add(new_scout_panel);
		new_scout_panel.setLayout(null);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setBounds(16, 78, 75, 16);
		new_scout_panel.add(lblFirstname);
		
		firstname = new JTextField();
		firstname.setBounds(103, 72, 134, 28);
		new_scout_panel.add(firstname);
		firstname.setColumns(10);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setBounds(249, 72, 75, 16);
		new_scout_panel.add(lblLastname);
		
		lastname = new JTextField();
		lastname.setBounds(336, 66, 134, 28);
		new_scout_panel.add(lastname);
		lastname.setColumns(10);
		
		JLabel lblTroop = new JLabel("Scout:");
		lblTroop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTroop.setBounds(30, 37, 61, 16);
		new_scout_panel.add(lblTroop);
		
		Scout_Combo = new JComboBox();
		Scout_Combo.setBounds(103, 33, 134, 27);
		new_scout_panel.add(Scout_Combo);
		
		populateTroop(Scout_Combo);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(30, 118, 61, 16);
		new_scout_panel.add(lblPhone);
		
		phone = new JTextField();
		phone.setBounds(103, 112, 134, 28);
		new_scout_panel.add(phone);
		phone.setColumns(10);
		
		email = new JTextField();
		email.setBounds(336, 106, 134, 28);
		new_scout_panel.add(email);
		email.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(263, 112, 61, 16);
		new_scout_panel.add(lblEmail);
		
		JButton cancel = new JButton("Cancel");
		cancel.setAction(action_1);
		cancel.setBounds(103, 152, 117, 29);
		new_scout_panel.add(cancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setAction(action);
		btnSave.setBounds(353, 152, 117, 29);
		new_scout_panel.add(btnSave);
		this.setVisible(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populateTroop(JComboBox combo){
		try{
			Main.makeConnection();
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM SCOUT");
			Main.result = Main.preparedStatement.executeQuery();
			while (Main.result.next()){
				combo.addItem(Main.result.getString("SCOUT_ID"));
			}
			Main.closeConnection();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void loadData(){
		Main.makeConnection();
		try {
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM CUSTOMER");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.customer_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
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
				Main.preparedStatement = Main.connection.prepareStatement("INSERT INTO CUSTOMER (`FIRSTNAME`, `LASTNAME`,`PHONE`,`SCOUT_ID`) values(?,?,?,?)");
				Main.preparedStatement.setString(1, firstname.getText());
				Main.preparedStatement.setString(2, lastname.getText());
				Main.preparedStatement.setString(3, phone.getText());
				Main.preparedStatement.setInt(4, Integer.valueOf(Scout_Combo.getSelectedItem().toString()));
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
