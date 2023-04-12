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

public class RegisterDoctorForm extends JFrame implements ActionListener{
	//declare tabel
	private JTable table_doctor;
	private JScrollPane scrollpane_table_doctor;
	private DefaultTableModel dtm_table_doctor;
	
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	//panel
	private JPanel panel_north = new JPanel();
	private JLabel header_title = new JLabel("REGISTER DOCTOR");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	//content 
	private JTextField txt_id = new JTextField();
	private JTextField txt_name = new JTextField();
	private JTextField txt_age = new JTextField();
	private JTextArea txt_address = new JTextArea();
	private JTextField txt_phone = new JTextField();
	private JRadioButton radio_male = new JRadioButton("Male");
	private JRadioButton radio_female = new JRadioButton("Female");
	private JComboBox<String> combo_specialization = new JComboBox<>();
	
	private JLabel id = new JLabel("ID");
	private JLabel name = new JLabel("Name");
	private JLabel age = new JLabel("Age");
	private JLabel address = new JLabel("Address");
	private JLabel phoneNumber = new JLabel("   Phone Number");
	private JLabel gender = new JLabel("   Gender");
	private JLabel specialization = new JLabel("   Specialization");
	
	//panel
	private JPanel panel_southFrame = new JPanel();
	private JPanel panel_south = new JPanel();
	private JPanel panel_space_south1 = new JPanel();
	private JPanel panel_space_south2 = new JPanel();
	private JPanel panel_space_south3 = new JPanel();
	private JPanel panel_space_south4 = new JPanel();
	private JButton btn_submit = new JButton("Submit");
	private JButton btn_clear = new JButton("Clear");
	private JButton btn_delete = new JButton("Delete");
	
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
			
			for(Doctor doctor : doctors) {
				System.out.println(doctor.getName());
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void write_doctor_data(){
		try {
			FileWriter fileWrite = new FileWriter("src/database/datadoctor.txt");
			String[] raw;
			String id;
			String name;
			int age;
			String address;
			String phoneNumber;
			String gender;
			String specialization;
			
//			raw = .split("#");
//			id = raw[0];
//			name = raw[1];
//			age = Integer.parseInt(raw[2]);
//			address = raw[3];
//			phoneNumber = raw[4];
//			gender = raw[5];
//			specialization = raw[6];
			
//			fileWrite.write(id);
			
			fileWrite.write("Files in Java might be tricky, but it is fun enough!");
		    fileWrite.close();
		    System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
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
//		panel_north.setBackground(Color.RED);
		header_title.setFont(font_title);
		header_title.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, "Center");
//		panel_space_north.setBackground(Color.BLUE);
		
		panel_north_table.setLayout(new BorderLayout());
		
		
		// Table
		table_doctor = new JTable();
		scrollpane_table_doctor = new JScrollPane(table_doctor);
//		scrollpane_table_doctor.setBackground(Color.GREEN);
		panel_north_table.add(scrollpane_table_doctor, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		
		//Content
		panel_center.setLayout(new GridLayout(1,2));
		
		panel_center_kiri.setLayout(new GridLayout(4,4));
//		panel_center_kiri.setBackground(Color.BLUE);
		panel_center_kiri.add(id);
		panel_center_kiri.add(txt_id);

		panel_center_kiri.add(name);
		panel_center_kiri.add(txt_name);
		
		panel_center_kiri.add(age);
		panel_center_kiri.add(txt_age);
		
		panel_center_kiri.add(address);
		panel_center_kiri.add(txt_address);
		
		panel_center.add(panel_center_kiri);
		
		panel_center_kanan.setLayout(new GridLayout(3, 3));
		panel_center_kanan.add(phoneNumber);
		panel_center_kanan.add(txt_phone);
		
		panel_center_kanan.add(gender);
		JPanel panel_gender = new JPanel();
		panel_gender.setLayout(new GridLayout(2,1));
		ButtonGroup bg_gender = new ButtonGroup();
		bg_gender.add(radio_male);
		bg_gender.add(radio_female);
		panel_gender.add(radio_male);
		panel_gender.add(radio_female);
		panel_center_kanan.add(panel_gender);
		
		panel_center_kanan.add(specialization);
		combo_specialization.addItem("General");
		combo_specialization.addItem("Surgeon");
		combo_specialization.addItem("Dentist");
		combo_specialization.addItem("Thorax");
		panel_center_kanan.add(combo_specialization);
		
		panel_center.add(panel_center_kanan);
		
		add(panel_center, "Center");
		
		//Footer
		panel_southFrame.setLayout(new BorderLayout());
		panel_southFrame.add(panel_space_south1, "North");
		panel_southFrame.add(panel_space_south2, "Center");
		
		panel_south.setLayout(new FlowLayout());
		panel_south.add(btn_submit);
		btn_submit.addActionListener(this);
		panel_south.add(btn_clear);
		btn_clear.addActionListener(this);
		panel_south.add(btn_delete);
		btn_delete.addActionListener(this);
		panel_southFrame.add(panel_south, "South");
		add(panel_southFrame, "South");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	public RegisterDoctorForm() {
		init_component();
		load_doctor_data();
		load_table_doctor();
	}
	
	public static void main(String[] args) {
		new RegisterDoctorForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_submit)) {
			String id = txt_id.getText();
			char d = id.charAt(0);
			
			String name = txt_name.getText();
			int age = Integer.parseInt(txt_age.getText());
			String address = txt_address.getText();
			String phoneNumber = txt_phone.getText();
			String gender = "";
			
			boolean flag = false;
			
			if(radio_male.isSelected()) {
				gender = "Male";
			}else if(radio_female.isSelected()) {
				gender = "Female";
			}
			
			String specialization = combo_specialization.getSelectedItem().toString();
			
			//VALIDATION
			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "ID must be filled!");
				flag = false;
			}else {
				flag = true;
			}
			if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "Name must be filled!");
				flag = false;
			}else {
				flag = true;
			}
			if(age < 20) {
				JOptionPane.showMessageDialog(null, "Age must be more than 20 years old!");
				flag = false;
			}else {
				flag = true;
			}
			
			//STORE DATA DI TABEL
			Object[] row = {id, name, age, address, phoneNumber, gender, specialization};
			
			if(flag == true) {
				dtm_table_doctor.addRow(row);
				doctors.add(new Doctor(id, name, age, address, phoneNumber, gender, specialization));
				table_doctor.invalidate();	
			}
			
			
			txt_id.setText("");
			txt_name.setText("");
			txt_age.setText("");
			txt_address.setText("");
			txt_phone.setText("");
	
		}else if(e.getSource().equals(btn_delete)) {
			int selectedRow = table_doctor.getSelectedRow();
			if(selectedRow != -1) {
				dtm_table_doctor.removeRow(selectedRow);
				doctors.remove(selectedRow);
				table_doctor.invalidate();
			}
		}else if(e.getSource().equals(btn_clear)) {
			dtm_table_doctor.setRowCount(0);
		}
		
	}

}
