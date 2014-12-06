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
	     DefaultMutableTreeNode reportMenu = new DefaultMutableTreeNode("Report");
	     DefaultMutableTreeNode statisticsMenu = new DefaultMutableTreeNode("Statistics");
	     DefaultMutableTreeNode helpMenu = new DefaultMutableTreeNode("Help");
	     
	     //second level
	     DefaultMutableTreeNode newMenu = new DefaultMutableTreeNode("New");
	     fileMenu.add(newMenu);
	    
	     //third level
	     final DefaultMutableTreeNode newVolunteer = new DefaultMutableTreeNode("Volunteer");
	     DefaultMutableTreeNode newTroop = new DefaultMutableTreeNode("Troop");
	     DefaultMutableTreeNode newScout = new DefaultMutableTreeNode("Scout");
	     DefaultMutableTreeNode newCustomer = new DefaultMutableTreeNode("Customer");
	     DefaultMutableTreeNode newProduct = new DefaultMutableTreeNode("Product");
	     DefaultMutableTreeNode newOrder = new DefaultMutableTreeNode("Order");
	     DefaultMutableTreeNode newMoney = new DefaultMutableTreeNode("Money");
	     
	     newMenu.add(newVolunteer);
	     newMenu.add(newTroop);
	     newMenu.add(newScout);
	     newMenu.add(newCustomer);
	     newMenu.add(newProduct);
	     newMenu.add(newOrder);
	     newMenu.add(newMoney);
	     
	     //add to root
	     root.add(aboutMenu);
	     root.add(fileMenu);
	     root.add(queryMenu);
	     root.add(reportMenu);
	     root.add(statisticsMenu);
	     root.add(helpMenu);
	     
	     //add everything to tree
		final JTree tree = new JTree(root);
		
		
		//set up action listener
		tree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode.equals(aboutMenu)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/about.pdf");
				}
				else if(selectedNode.equals(newVolunteer)){
					makeConnection("http://girlsscoutcookie.jimmybowens.com/volunteer.pdf");
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
