package patient;

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

public class ViewPatient extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("VIEW PATIENT DATA");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_patient;
	private JScrollPane scrollpane_table_patient;
	private DefaultTableModel dtm_table_patient;
	
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	
	public void load_patient_data() {
		File file = new File("src/database/patient.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String id;
			String name;
			int age;
			String address;
			String phoneNumber;
			String gender;
			String blood;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				id = raw[0];
				name = raw[1];
				age = Integer.parseInt(raw[2]);
				address = raw[3];
				phoneNumber = raw[4];
				gender = raw[5];
				blood = raw[6];
				
				patients.add(new Patient(id, name, age, address, phoneNumber, gender, blood));
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void load_table_patient() {
		String[] column = {"ID", "Name", "Age", "Address", "Phone Number", "Gender", "Blood Group"};
		dtm_table_patient = new DefaultTableModel(column, 0);
		
		for(Patient patient: patients) {
			String id = patient.getId();
			String name = patient.getName();
			int age = patient.getAge();
			String address = patient.getAddress();
			String phoneNumber = patient.getPhoneNum();
			String gender = patient.getGender();
			String blood = patient.getBlood();
			
			Object[] row = {id, name, age, address, phoneNumber, gender, blood};
			dtm_table_patient.addRow(row);
		}
		
		table_patient.setModel(dtm_table_patient);;
	}
	
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
		table_patient = new JTable();
		scrollpane_table_patient = new JScrollPane(table_patient);
		panel_north_table.add(scrollpane_table_patient, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		setTitle("View Patient Data");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public ViewPatient() {
		initComponent();
		load_patient_data();
		load_table_patient();
	}
	
	public static void main(String[] args) {
		new ViewPatient();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		
	}

}
