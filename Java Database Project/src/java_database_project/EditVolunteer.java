package java_database_project;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
//import java.sql.Date;

import java.sql.SQLException;

import javax.swing.Action;

import net.proteanit.sql.DbUtils;

public class EditVolunteer extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField phone;
	//private String[] states = { "MD","DC" };
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	public EditVolunteer(){
		//custom variables
		this.setTitle("Update Volunteer Information");
		this.setSize(new Dimension(500, 400));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel new_volunteer_panel = new JPanel();
		new_volunteer_panel.setBounds(0, 0, 500, 378);
		getContentPane().add(new_volunteer_panel);
		new_volunteer_panel.setLayout(null);
		
		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setBounds(30, 44, 75, 16);
		new_volunteer_panel.add(lblFirstname);
		
		firstname = new JTextField();
		firstname.setBounds(117, 38, 353, 28);
		new_volunteer_panel.add(firstname);
		firstname.setColumns(10);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setBounds(30, 84, 75, 16);
		new_volunteer_panel.add(lblLastname);
		
		lastname = new JTextField();
		lastname.setBounds(117, 78, 353, 28);
		new_volunteer_panel.add(lastname);
		lastname.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(44, 124, 61, 16);
		new_volunteer_panel.add(lblPhone);
		
		phone = new JTextField();
		phone.setBounds(117, 118, 353, 28);
		new_volunteer_panel.add(phone);
		phone.setColumns(10);
		
		JButton cancel = new JButton("Cancel");
		cancel.setAction(action_1);
		cancel.setBounds(117, 173, 117, 29);
		new_volunteer_panel.add(cancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setAction(action);
		btnSave.setBounds(353, 173, 117, 29);
		new_volunteer_panel.add(btnSave);
		this.setVisible(true);
		
		setData();
	}
	
	public void setData(){
		firstname.setText(TabbedPanel.temp_firstname);
		lastname.setText(TabbedPanel.temp_lastname);
		phone.setText(TabbedPanel.temp_phone);
	}
	
	public void loadData(){
		Main.makeConnection();
		try {
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM VOLUNTEER");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.volunteer_table.setModel(DbUtils.resultSetToTableModel(Main.result));
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
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Save");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			Main.makeConnection();
			try{
				Main.preparedStatement = Main.connection.prepareStatement("UPDATE VOLUNTEER SET FIRSTNAME = ?, LASTNAME = ?, PHONE = ? WHERE VOLUNTEER_ID = ?");
				Main.preparedStatement.setString(1, firstname.getText());
				Main.preparedStatement.setString(2, lastname.getText());
				Main.preparedStatement.setString(3, phone.getText());
				Main.preparedStatement.setInt(4, Integer.valueOf(TabbedPanel.temp_volunteer_id));
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
