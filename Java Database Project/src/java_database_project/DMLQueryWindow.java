package java_database_project;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DMLQueryWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private JTextArea Query;
	public static JTable queryResultTable;
	
	public DMLQueryWindow(){
		this.setTitle("Query Database");
		this.setSize(new Dimension(1000,640));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblEnterQueryBelow = new JLabel("Enter Query Below");
		lblEnterQueryBelow.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterQueryBelow.setBounds(0, 6, 388, 16);
		getContentPane().add(lblEnterQueryBelow);
		
		JButton done = new JButton("Done");
		done.setAction(action_1);
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		done.setBounds(0, 557, 117, 29);
		getContentPane().add(done);
		
		JButton run = new JButton("Run Query");
		run.setAction(action);
		run.setBounds(883, 557, 117, 29);
		getContentPane().add(run);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 34, 1000, 256);
		getContentPane().add(scrollPane);
		
		Query = new JTextArea();
		scrollPane.setViewportView(Query);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 289, 1000, 256);
		getContentPane().add(scrollPane_1);
		
		queryResultTable = new JTable();
		scrollPane_1.setViewportView(queryResultTable);
		this.setVisible(true);
		
		//loadData();
	}
	
	public JFrame getFrame(){
		return this;
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
			//DMLQueryWindow.queryResultTable.setModel(DbUtils.resultSetToTableModel(Main.result));//test
			
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
				Main.result = Main.preparedStatement.executeQuery();
				DMLQueryWindow.queryResultTable.setModel(DbUtils.resultSetToTableModel(Main.result));
				Main.closeConnection();
				loadData();
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
			putValue(NAME, "Done");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			getFrame().dispose();
		}
	}
}
