package java_database_project;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class MenuBar extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();
	private final Action action_5 = new SwingAction_5();
	private final Action action_6 = new SwingAction_6();
	
	public MenuBar(){
		JMenu file = new JMenu("File");
		//JMenu edit = new JMenu("Edit");
		JMenu query = new JMenu("Query");
		JMenu report = new JMenu("Report");
		JMenu statistics = new JMenu("Statistics");
		
		//new menu
		JMenu New = new JMenu("New");
		JMenu Delete = new JMenu("Delete");
		JMenu Alter = new JMenu("Alter");
		
		//new sub category
		JMenuItem newScout = new JMenuItem("Scout");
		newScout.setAction(action);
		JMenuItem newTroop = new JMenuItem("Troop");
		newTroop.setAction(action_2);
		JMenuItem newProduct = new JMenuItem("Product");
		newProduct.setAction(action_4);
		JMenuItem newVolunteer = new JMenuItem("Volunteer");
		newVolunteer.setAction(action_1);
		JMenuItem newCustomer = new JMenuItem("Customer");
		newCustomer.setAction(action_5);
		JMenuItem newShipment = new JMenuItem("Shipment");
		JMenuItem newOrder = new JMenuItem("Order");
		newOrder.setAction(action_6);
		New.add(newScout);
		New.add(newTroop);
		New.add(newProduct);
		New.add(newVolunteer);
		New.add(newCustomer);
		New.add(newShipment);
		New.add(newOrder);
		file.add(New);
		
		//
		
		JMenuItem loadQuery = new JMenuItem("New Query");
		loadQuery.setAction(action_3);
		query.add(loadQuery);
		this.add(file);
		//this.add(edit);
		this.add(query);
		this.add(report);
		this.add(statistics);
		
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Scout");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new NewScout();
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_1() {
			putValue(NAME, "Volunteer");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new NewVolunteer();
		}
	}
	private class SwingAction_2 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_2() {
			putValue(NAME, "Troop");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new NewTroop();
		}
	}
	
	private class SwingAction_3 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_3() {
			putValue(NAME, "New Query");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new QueryWindow();
		}
	}
	private class SwingAction_4 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_4() {
			putValue(NAME, "Product");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new NewProduct();
		}
	}
	private class SwingAction_5 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_5() {
			putValue(NAME, "Customer");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new NewCustomer();
		}
	}
	private class SwingAction_6 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction_6() {
			putValue(NAME, "Order");
			//putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			new NewOrder();
		}
	}
}
