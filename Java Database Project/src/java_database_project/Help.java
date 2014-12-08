package java_database_project;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class Help extends JFrame{
	public Help() {
		this.setTitle("Help");
		getContentPane().setLayout(null);
		
		//set up tree
		 DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tutorial");
		 //first level
		 final DefaultMutableTreeNode aboutMenu = new DefaultMutableTreeNode("About");
	     DefaultMutableTreeNode fileMenu = new DefaultMutableTreeNode("File");
	     DefaultMutableTreeNode queryMenu = new DefaultMutableTreeNode("Query");
	     //DefaultMutableTreeNode reportMenu = new DefaultMutableTreeNode("Report");
	     DefaultMutableTreeNode statisticsMenu = new DefaultMutableTreeNode("Statistics");
	     final DefaultMutableTreeNode fullDownload = new DefaultMutableTreeNode("Complete Full Tutorial");
	     
	     //second level
	     DefaultMutableTreeNode newMenu = new DefaultMutableTreeNode("New");
	     final DefaultMutableTreeNode newDDLQuery = new DefaultMutableTreeNode("DDL Query");
	     final DefaultMutableTreeNode newDMLQuery = new DefaultMutableTreeNode("DML Query");
	     final DefaultMutableTreeNode allStats = new DefaultMutableTreeNode("All Statistics");
	     fileMenu.add(newMenu);
	     queryMenu.add(newDDLQuery);
	     queryMenu.add(newDMLQuery);
	     statisticsMenu.add(allStats);
	    
	     //third level
	     final DefaultMutableTreeNode newVolunteer = new DefaultMutableTreeNode("Volunteer");
	     final DefaultMutableTreeNode newTroop = new DefaultMutableTreeNode("Troop");
	     final DefaultMutableTreeNode newScout = new DefaultMutableTreeNode("Scout");
	     final DefaultMutableTreeNode newCustomer = new DefaultMutableTreeNode("Customer");
	     final DefaultMutableTreeNode newProduct = new DefaultMutableTreeNode("Product");
	     final DefaultMutableTreeNode newOrder = new DefaultMutableTreeNode("Order");
	     
	     newMenu.add(newVolunteer);
	     newMenu.add(newTroop);
	     newMenu.add(newScout);
	     newMenu.add(newCustomer);
	     newMenu.add(newProduct);
	     newMenu.add(newOrder);
	     
	     //add to root
	     root.add(aboutMenu);
	     root.add(fileMenu);
	     root.add(queryMenu);
	     //root.add(reportMenu);
	     root.add(statisticsMenu);
	     root.add(fullDownload);
	     
	     //add everything to tree
		final JTree tree = new JTree(root);
		
		
		//set up action listener
		tree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode.equals(aboutMenu)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/about.pdf");
				}
				else if(selectedNode.equals(newVolunteer)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/new/volunteer.pdf");
				}
				else if(selectedNode.equals(newTroop)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/new/troop.pdf");
				}
				else if(selectedNode.equals(newScout)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/new/scout.pdf");
				}
				else if(selectedNode.equals(newCustomer)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/new/customer.pdf");
				}
				else if(selectedNode.equals(newProduct)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/new/product.pdf");
				}
				else if(selectedNode.equals(newOrder)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/new/order.pdf");
				}
				else if(selectedNode.equals(newDDLQuery)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/Query/DDLQuery.pdf");
				}
				else if(selectedNode.equals(newDMLQuery)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/Query/DMLQuery.pdf");
				}
				else if(selectedNode.equals(allStats)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/statistics/allStatistics.pdf");
				}
				else if(selectedNode.equals(fullDownload)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/tutorial/GSCookieTutorial.pdf");
				}
			}
			
		});
		
		tree.setBounds(10, 11, 374, 350);
		getContentPane().add(tree);
		this.setResizable(false);
		this.setSize(new Dimension(400,400));
		this.setVisible(true);
	}
	private static final long serialVersionUID = 1L;
	
	public void makeConnection(String url){
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI(url));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
}
