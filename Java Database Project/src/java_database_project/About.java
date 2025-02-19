package java_database_project;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class About extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public About(){
		this.setTitle("About");
		this.setSize(new Dimension(450,250));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 191);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setBounds(6, 6, 376, 16);
		panel.add(lblCredits);
		
		JLabel lblJavaDeveloper = new JLabel("Java & MySQL Developer : Jimmy Bowens");
		lblJavaDeveloper.setHorizontalAlignment(SwingConstants.CENTER);
		lblJavaDeveloper.setBounds(6, 34, 426, 16);
		panel.add(lblJavaDeveloper);
		
		JLabel lblDatabaseDeveloperPhil = new JLabel("MySQL Database Developer: Phil Tucker");
		lblDatabaseDeveloperPhil.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseDeveloperPhil.setBounds(6, 62, 426, 16);
		panel.add(lblDatabaseDeveloperPhil);
		
		JLabel lblProjectOrganizerReports = new JLabel("Project Organizer, Reports, Graphics, and ERD: Jessica Rudolph");
		lblProjectOrganizerReports.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectOrganizerReports.setBounds(6, 90, 426, 16);
		panel.add(lblProjectOrganizerReports);
		
		JLabel lblNewLabel = new JLabel("\u00A9 All Rights Reserved! Towson University, Fall 2014.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 146, 426, 16);
		panel.add(lblNewLabel);
		
		JLabel lblProfessorSungchulHong = new JLabel("Professor Sung-Chul Hong - Department of Computer Science.");
		lblProfessorSungchulHong.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessorSungchulHong.setBounds(6, 118, 426, 16);
		panel.add(lblProfessorSungchulHong);
	}
}
