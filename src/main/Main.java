package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import medicine.MedicineForm;
import patient.PatientForm;

public class Main extends JFrame implements ActionListener{
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenu menuOption = new JMenu("Guide");
	private JMenuItem menuItemFileRegister = new JMenuItem("Login");
	private JMenuItem menuItemFileLogin = new JMenuItem("Register");
	
	private MedicineForm medicineForm;
	private PatientForm patientForm;
	private JDesktopPane jdPane = new JDesktopPane();
	
	public Main() {
		add(jdPane);
		
		menuBar.add(menuFile);
		menuFile.add(menuItemFileRegister);
		menuFile.add(new JSeparator());
		menuFile.add(menuItemFileLogin);
		
		menuBar.add(menuOption);
		
		setJMenuBar(menuBar);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
