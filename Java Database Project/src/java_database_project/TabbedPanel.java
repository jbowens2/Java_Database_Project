package java_database_project;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabbedPanel extends JTabbedPane{
	
	private static final long serialVersionUID = 1L;
	//private String[] states = { "MD","DC" };
	public static JTable scout_table;
	public static JTable volunteer_table;
	public static JTable troop_table;
	public static JTable customer_table;
	public static JTable shipment_table;
	public static JTable product_table;
	public static JTable order_table;
	public static JTable money_table;
	public static JTable inventory_table;
	
	//volunteer temp variables
	public static String temp_volunteer_id, temp_firstname, temp_lastname, temp_phone;
	//end volunteer temp variables
	
	//troop temp variables
	public static String temp_troop_id,temp_troop_name, temp_troop_address, temp_troop_city, temp_troop_state, temp_troop_zipcode, temp_troop_manager;
	//end troop temp variables
	
	//scout temp variables
	public static String temp_scout_id, temp_scout_firstname, temp_scout_lastname, temp_scout_phone, temp_scout_email, temp_scout_birthdate, temp_scout_startdate, temp_scout_parent, temp_scout_address, temp_scout_city, temp_scout_state, temp_scout_zipcode, temp_scout_troop_id;
	public static int stateColumn;
	//end scout temp variables

	public TabbedPanel (){
		addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				loadData();
			}
		});	
		setSize(800, 800);
		
		JPanel volunteer_tab = new JPanel();
		addTab("Volunteer", null, volunteer_tab, null);
		volunteer_tab.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 800, 800);
		volunteer_tab.add(scrollPane_1);
		
		volunteer_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		volunteer_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					//System.out.println(volunteer_table.getValueAt(volunteer_table.getSelectedRow(), volunteer_table.getSelectedColumn()));
					temp_volunteer_id = volunteer_table.getValueAt(volunteer_table.getSelectedRow(), 0).toString();
					temp_firstname = volunteer_table.getValueAt(volunteer_table.getSelectedRow(), 1).toString();
					temp_lastname = volunteer_table.getValueAt(volunteer_table.getSelectedRow(), 2).toString();
					temp_phone = volunteer_table.getValueAt(volunteer_table.getSelectedRow(), 3).toString();
					new EditVolunteer();
				}
			}
		});
		
	    
		volunteer_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		volunteer_table.setRowSelectionAllowed(true);
		scrollPane_1.setViewportView(volunteer_table);
		
		JPanel troop_tab = new JPanel();
		addTab("Troop", null, troop_tab, null);
		troop_tab.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 800, 800);
		troop_tab.add(scrollPane_2);
		
		troop_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		troop_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					TabbedPanel.temp_troop_id = troop_table.getValueAt(troop_table.getSelectedRow(), 0).toString();
					TabbedPanel.temp_troop_name = troop_table.getValueAt(troop_table.getSelectedRow(), 1).toString();
					TabbedPanel.temp_troop_address = troop_table.getValueAt(troop_table.getSelectedRow(), 2).toString();
					TabbedPanel.temp_troop_city = troop_table.getValueAt(troop_table.getSelectedRow(), 3).toString();
					TabbedPanel.temp_troop_state = troop_table.getValueAt(troop_table.getSelectedRow(), 4).toString();
					TabbedPanel.temp_troop_zipcode = troop_table.getValueAt(troop_table.getSelectedRow(), 5).toString();
					new EditTroop();
				}
				
			}
		});
	    
		troop_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane_2.setViewportView(troop_table);
		
		JPanel scout_tab = new JPanel();
		scout_tab.setBackground(Color.LIGHT_GRAY);
		addTab("Scout", null, scout_tab, null);
		scout_tab.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 800);
		scout_tab.add(scrollPane);
		
		scout_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		scout_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					TabbedPanel.temp_scout_id = scout_table.getValueAt(scout_table.getSelectedRow(), 0).toString();
					TabbedPanel.temp_scout_firstname = scout_table.getValueAt(scout_table.getSelectedRow(), 1).toString();
					TabbedPanel.temp_scout_lastname = scout_table.getValueAt(scout_table.getSelectedRow(), 2).toString();
					TabbedPanel.temp_scout_phone = scout_table.getValueAt(scout_table.getSelectedRow(), 3).toString();
					TabbedPanel.temp_scout_email = scout_table.getValueAt(scout_table.getSelectedRow(), 4).toString();
					TabbedPanel.temp_scout_birthdate = scout_table.getValueAt(scout_table.getSelectedRow(), 5).toString();
					TabbedPanel.temp_scout_startdate = scout_table.getValueAt(scout_table.getSelectedRow(), 6).toString();
					TabbedPanel.temp_scout_parent = scout_table.getValueAt(scout_table.getSelectedRow(), 7).toString();
					TabbedPanel.temp_scout_address = scout_table.getValueAt(scout_table.getSelectedRow(), 8).toString();
					TabbedPanel.temp_scout_city = scout_table.getValueAt(scout_table.getSelectedRow(), 9).toString();
					TabbedPanel.temp_scout_state = scout_table.getValueAt(scout_table.getSelectedRow(), 10).toString();
					TabbedPanel.temp_scout_zipcode = scout_table.getValueAt(scout_table.getSelectedRow(), 11).toString();
					TabbedPanel.temp_scout_troop_id = scout_table.getValueAt(scout_table.getSelectedRow(), 12).toString();
					new EditScout();
				}
			}
		});
	    
		scout_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setViewportView(scout_table);
		
		JPanel customer_tab = new JPanel();
		addTab("Customer", null, customer_tab, null);
		customer_tab.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 800, 800);
		customer_tab.add(scrollPane_3);
		
		customer_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    
		customer_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane_3.setViewportView(customer_table);
		
		JPanel product_tab = new JPanel();
		addTab("Product", null, product_tab, null);
		product_tab.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(0, 0, 800, 800);
		product_tab.add(scrollPane_5);
		
		product_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    
		scrollPane_5.setViewportView(product_table);
		
		JPanel order_tab = new JPanel();
		addTab("Orders", null, order_tab, null);
		order_tab.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(0, 0, 800, 800);
		order_tab.add(scrollPane_6);
		
		order_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    
		scrollPane_6.setViewportView(order_table);
		
		JPanel money_tab = new JPanel();
		addTab("Money", null, money_tab, null);
		money_tab.setLayout(null);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(0, 0, 800, 800);
		money_tab.add(scrollPane_7);
		
		money_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    
		scrollPane_7.setViewportView(money_table);
		
		JPanel shipment_tab = new JPanel();
		addTab("Shipment", null, shipment_tab, null);
		shipment_tab.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 0, 800, 800);
		shipment_tab.add(scrollPane_4);
		
		shipment_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		
		shipment_table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane_4.setViewportView(shipment_table);
		
		JPanel inventory_tab = new JPanel();
		addTab("Inventory", null, inventory_tab, null);
		inventory_tab.setLayout(null);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(0, 0, 800, 800);
		inventory_tab.add(scrollPane_8);
		
		inventory_table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
	    
		scrollPane_8.setViewportView(inventory_table);
		
		
		loadData();	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			Main.preparedStatement = Main.connection.prepareStatement("SELECT NAME, DESCRIPTION, PRICE FROM PRODUCT");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.product_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM SCOUT");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.scout_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM VOLUNTEER");
			Main.result = Main.preparedStatement.executeQuery();
			TabbedPanel.volunteer_table.setModel(DbUtils.resultSetToTableModel(Main.result));
			
			Main.preparedStatement = Main.connection.prepareStatement("SELECT * FROM TROOP");
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
			//JOptionPane.showMessageDialog(null, general ,"Error!",JOptionPane.WARNING_MESSAGE);
			
			//System.out.println(general);
		}
		
	}
}
