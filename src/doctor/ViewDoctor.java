package doctor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ViewDoctor extends JFrame{
	//declare tabel
	private JTable table_doctor;
	private JScrollPane scrollpane_table_doctor;
	private DefaultTableModel dtm_table_doctor;
	
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	//panel
	private JPanel panel_north = new JPanel();
	private JLabel header_title = new JLabel("VIEW DOCTOR DATA");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	
	public void load_doctor_data(){
		File file = new File("src/database/datadoctor.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String id;
			String name;
			int age;
			String address;
			String phoneNumber;
			String gender;
			String specialization;
			//while not end of file
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				id = raw[0];
				name = raw[1];
				age = Integer.parseInt(raw[2]);
				address = raw[3];
				phoneNumber = raw[4];
				gender = raw[5];
				specialization = raw[6];
				
				doctors.add(new Doctor(id, name, age, address, phoneNumber, gender, specialization));
			}
			
			// for(Doctor doctor : doctors) {
			// 	System.out.println(doctor.getName());
			// }
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void load_table_doctor() {
		String[] column = {"ID", "Name", "Age", "Address", "Phone Number", "Gender", "Specialization"};
		dtm_table_doctor = new DefaultTableModel(column, 0);
		
		for(Doctor doctor : doctors) {
			String id = doctor.getId();
			String name = doctor.getName();
			int age = doctor.getAge();
			String address = doctor.getAddress();
			String phoneNumber = doctor.getPhoneNumber();
			String gender = doctor.getGender();
			String specialization = doctor.getSpecialization();
			
			Object[] row = {id, name, age, address, phoneNumber, gender, specialization};
			dtm_table_doctor.addRow(row);
		}
		table_doctor.setModel(dtm_table_doctor);
	}
	
	public void init_component() {
		setLayout(new BorderLayout());
		//Header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(header_title, "North");
		header_title.setFont(font_title);
		header_title.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, "Center");
		
		panel_north_table.setLayout(new BorderLayout());
		
		// Table
		table_doctor = new JTable();
		scrollpane_table_doctor = new JScrollPane(table_doctor);
		panel_north_table.add(scrollpane_table_doctor, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
        setTitle("View Doctor Data");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public ViewDoctor() {
		init_component();
		load_doctor_data();
		load_table_doctor();
	}
	
	public static void main(String[] args) {
		new ViewDoctor();
	}

}
