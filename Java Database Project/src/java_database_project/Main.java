package java_database_project;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	public static Connection connection;
	public static Statement statement;
	public static boolean isValidated = false;
	public static java.sql.PreparedStatement preparedStatement;
	public static ResultSet result;
	public Main (){
		setupFrame(this);
		makeConnection();
	}
	
	public void setupFrame(JFrame frame){
		TabbedPanel tabbedPanel = new TabbedPanel();
		tabbedPanel.setBounds(0, 0, 794, 772);
		frame.getContentPane().add(tabbedPanel);
		frame.setTitle("Girl's Scout Cookie Tracking System");
		frame.setSize(new Dimension(800,800));
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		if(System.getProperty("os.name").startsWith("Mac OS X")){
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("apple.awt.graphics.UseQuartz", "true");
			frame.setJMenuBar(new MenuBar());
		}
		else{
			frame.setJMenuBar(new MenuBar());
		}
		
		this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent event){
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit? This will close all associated windows.", "Confirm", JOptionPane.YES_NO_OPTION);
                if(confirmed == JOptionPane.YES_OPTION){
                    System.exit(1);
                }
            }
        });
		
		frame.setVisible(true);
	}
	
	public static void makeConnection(){
		connection = null;
		statement = null;
		result = null;
		preparedStatement = null;
		Properties databaseCredential = new Properties();
		FileInputStream in = null;
		String url, user, password;
		
		try{
			in = new FileInputStream(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"login.properties");
			databaseCredential.load(in);
			url = databaseCredential.getProperty("url");
			user = databaseCredential.getProperty("user");
			password = databaseCredential.getProperty("password");
			connection = DriverManager.getConnection(url, user, password);

		}catch(SQLException e){
            JOptionPane.showMessageDialog(null, e + " Please make sure you have a working internect connection.","Connection Failure!",JOptionPane.WARNING_MESSAGE);

		}
		catch(IOException i){
			System.out.println(i);
		}
		
	}
	
	public static void closeConnection(){
		try{
			if(result != null){
				result.close();
			}
			if(statement != null){
				statement.close();
			}
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(connection!= null){
				connection.close();
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e ,"Error!",JOptionPane.WARNING_MESSAGE);
		}
	}

}
