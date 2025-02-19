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
import java.util.Calendar;

import javax.swing.Action;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;

public class NewOrder extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	@SuppressWarnings("rawtypes")
	private JComboBox scout_combo, customer_combo, product_combo;
	private JTextField quantity;

	@SuppressWarnings("rawtypes")
	public NewOrder(){
		//custom variables
		this.setTitle("New Order Information");
		this.setSize(new Dimension(600, 400));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel new_volunteer_panel = new JPanel();
		new_volunteer_panel.setBounds(0, 0, 600, 378);
		getContentPane().add(new_volunteer_panel);
		new_volunteer_panel.setLayout(null);
		
		JLabel lblFirstname = new JLabel("Scout:");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setBounds(30, 44, 75, 16);
		new_volunteer_panel.add(lblFirstname);
		
		JButton cancel = new JButton("Cancel");
		cancel.setAction(action_1);
		cancel.setBounds(63, 176, 117, 29);
		new_volunteer_panel.add(cancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setAction(action);
		btnSave.setBounds(416, 176, 117, 29);
		new_volunteer_panel.add(btnSave);
		
		scout_combo = new JComboBox();
		scout_combo.setBounds(117, 40, 80, 27);
		new_volunteer_panel.add(scout_combo);
		
		populateScout(scout_combo);
		
		customer_combo = new JComboBox();
		customer_combo.setBounds(288, 40, 80, 27);
		new_volunteer_panel.add(customer_combo);
		
		populateCustomer(customer_combo);
		
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomer.setBounds(201, 44, 75, 16);
		new_volunteer_panel.add(lblCustomer);
		
		product_combo = new JComboBox();
		product_combo.setBounds(453, 40, 80, 27);
		new_volunteer_panel.add(product_combo);
		
		populateProduct(product_combo);
		
		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProduct.setBounds(380, 44, 61, 16);
		new_volunteer_panel.add(lblProduct);
		
		JLabel scout_name = new JLabel("Scout Name");
		scout_name.setHorizontalAlignment(SwingConstants.CENTER);
		scout_name.setBounds(63, 89, 134, 16);
		new_volunteer_panel.add(scout_name);
		
		JLabel customer_name = new JLabel("Customer Name");
		customer_name.setHorizontalAlignment(SwingConstants.CENTER);
		customer_name.setBounds(209, 89, 159, 16);
		new_volunteer_panel.add(customer_name);
		
		JLabel product_name = new JLabel("Product Name");
		product_name.setHorizontalAlignment(SwingConstants.CENTER);
		product_name.setBounds(380, 89, 153, 16);
		new_volunteer_panel.add(product_name);
		
		quantity = new JTextField();
		quantity.setBounds(288, 117, 134, 28);
		new_volunteer_panel.add(quantity);
		quantity.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantity.setBounds(201, 123, 61, 16);
		new_volunteer_panel.add(lblQuantity);
		this.setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populateScout(JComboBox combo){
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void populateCustomer(JComboBox combo){
		try{
			Main.makeConnection();
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM CUSTOMER");
			Main.result = Main.preparedStatement.executeQuery();
			while (Main.result.next()){
				combo.addItem(Main.result.getString("CUSTOMER_ID"));
			}
			Main.closeConnection();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populateProduct(JComboBox combo){
		try{
			Main.makeConnection();
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM PRODUCT");
			Main.result = Main.preparedStatement.executeQuery();
			while (Main.result.next()){
				combo.addItem(Main.result.getString("PRODUCT_ID"));
			}
			Main.closeConnection();
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void loadData(){
		Main.makeConnection();
		try {
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM PRODUCT_ORDER");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.order_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM MONEY");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.money_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM SHIPMENT");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.shipment_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM INVENTORY");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.inventory_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
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
				Main.preparedStatement = Main.connection.prepareStatement("INSERT INTO PRODUCT_ORDER (`DATE`, `SCOUT_ID`,`CUSTOMER_ID`, `PRODUCT_ID`, `QUANTITY`, `STATUS`) values(?,?,?,?,?,?)");
				Calendar calendar = Calendar.getInstance();
				java.util.Date currentDate = calendar.getTime();
				java.sql.Date date = new java.sql.Date(currentDate.getTime());
				Main.preparedStatement.setDate(1, date);
				Main.preparedStatement.setInt(2, Integer.valueOf(scout_combo.getSelectedItem().toString()));
				Main.preparedStatement.setInt(3, Integer.valueOf(customer_combo.getSelectedItem().toString()));
				Main.preparedStatement.setInt(4, Integer.valueOf(product_combo.getSelectedItem().toString()));
				Main.preparedStatement.setInt(5, Integer.valueOf(quantity.getText()));
				Main.preparedStatement.setString(6, "Order Placed");
				Main.preparedStatement.execute();
				loadData();
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
