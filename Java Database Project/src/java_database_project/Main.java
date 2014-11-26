package java_database_project;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame{
	public Main (){
		setupFrame(this);
	}
	
	
	public void setupFrame(JFrame frame){
		frame.setTitle("Girl's Scout Cookie Tracking System");
		frame.setSize(new Dimension(800,800));
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();

	}

}
