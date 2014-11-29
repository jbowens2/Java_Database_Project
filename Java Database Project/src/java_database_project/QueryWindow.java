package java_database_project;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import net.proteanit.sql.DbUtils;

public class QueryWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField Query;
	private final Action action = new SwingAction();
	private JLabel output;
	
	public QueryWindow(){
		this.setTitle("Query Database");
		this.setSize(new Dimension(400,400));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblEnterQueryBelow = new JLabel("Enter Query Below");
		lblEnterQueryBelow.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterQueryBelow.setBounds(0, 6, 388, 16);
		getContentPane().add(lblEnterQueryBelow);
		
		Query = new JTextField();
		Query.setBounds(10, 34, 378, 28);
		getContentPane().add(Query);
		Query.setColumns(10);
		
		JButton done = new JButton("Done");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		done.setBounds(20, 74, 117, 29);
		getContentPane().add(done);
		
		JButton run = new JButton("Run Query");
		run.setAction(action);
		run.setBounds(271, 74, 117, 29);
		getContentPane().add(run);
		
		output = new JLabel("");
		output.setBounds(10, 115, 378, 257);
		getContentPane().add(output);
		this.setVisible(true);
	}
	
	public void loadData(){
		Main.makeConnection();
		try {
			Main.preparedStatement = Main.connection.prepareStatement("SELECT NAME, DESCRIPTION, PRICE FROM PRODUCT");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.product_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM SCOUT");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.scout_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM VOLUNTEER");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.volunteer_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT NAME, ADDRESS, CITY, STATE, ZIPCODE, MANAGER, FIRSTNAME, LASTNAME FROM TROOP, VOLUNTEER WHERE TROOP.MANAGER = VOLUNTEER.VOLUNTEER_ID");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.troop_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM CUSTOMER");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.customer_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM SHIPMENT");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.shipment_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM PRODUCT_ORDER");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.order_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM MONEY");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.money_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
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
	
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Run Query");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			Main.makeConnection();
			try{
				Main.preparedStatement = Main.connection.prepareStatement(Query.getText());
				Main.preparedStatement.executeUpdate();
				int affectedRows = Main.preparedStatement.executeUpdate();
				output.setText(Integer.toString(affectedRows));
				Main.closeConnection();
				loadData();
			}catch(Exception e2){
				 output.setText(e2.toString());
			}
		}
	}
}
