package hospitalFrame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import patient.Patient;

public class Guide extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 22);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("Guide to Hospital Management System");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_medicine;
	private JScrollPane scrollpane_table_medicine;
	private DefaultTableModel dtm_table_medicine;
	
	public void initComponent() {
		
		setLayout(new BorderLayout());
		
		//Header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(lbl_register, "North");
		lbl_register.setFont(font_title);
		lbl_register.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, "Center");
		
		panel_north_table.setLayout(new BorderLayout());
		
		// Table
		table_medicine = new JTable();
		scrollpane_table_medicine = new JScrollPane(table_medicine);
		panel_north_table.add(scrollpane_table_medicine, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		setTitle("Medicine Stocklist");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public Guide() {
		initComponent();
	}
	
	public static void main(String[] args) {
		new Guide();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	}

}

