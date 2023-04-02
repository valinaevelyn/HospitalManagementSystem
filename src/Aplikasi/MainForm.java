package Aplikasi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("REGISTRASI PASIEN");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_patient;
	private JScrollPane scrollpane_table_patient;
	private DefaultTableModel dtm_table_patient;
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	private JLabel lbl_id = new JLabel("ID");
	private JLabel lbl_name = new JLabel("Name");
	private JLabel lbl_age = new JLabel("Age");
	private JLabel lbl_address = new JLabel("Address");
	private JLabel lbl_phonenum = new JLabel("   Phone Number");
	private JLabel lbl_gender = new JLabel("   Gender");
	private JLabel lbl_blood = new JLabel("   Blood Type");
	
	private JTextField txt_id = new JTextField();
	private JTextField txt_name = new JTextField();
	private JTextField txt_age = new JTextField();
	private JTextArea txt_address = new JTextArea();
	private JTextField txt_phonenum = new JTextField();
	private JRadioButton radio_male = new JRadioButton("Male");
	private JRadioButton radio_female = new JRadioButton("Female");
	private JComboBox<String> combo_blood = new JComboBox<>();
	
	private JPanel panel_southFrame = new JPanel();
	private JPanel panel_south = new JPanel();
	private JPanel panel_space_south1 = new JPanel();
	private JPanel panel_space_south2 = new JPanel();
	private JPanel panel_space_south3 = new JPanel();
	private JPanel panel_space_south4 = new JPanel();
	private JButton btn_submit = new JButton("Submit");
	private JButton btn_clear = new JButton("Clear");
	private JButton btn_delete = new JButton("Delete");
	
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
		
		
		//Content
		panel_center.setLayout(new GridLayout(1,2));
		
		panel_center_kiri.setLayout(new GridLayout(4,4));
		panel_center_kiri.add(lbl_id);
		panel_center_kiri.add(txt_id);

		panel_center_kiri.add(lbl_name);
		panel_center_kiri.add(txt_name);
		
		panel_center_kiri.add(lbl_age);
		panel_center_kiri.add(txt_age);
		
		panel_center_kiri.add(lbl_address);
		panel_center_kiri.add(txt_address);
		
		panel_center.add(panel_center_kiri);
		
		panel_center_kanan.setLayout(new GridLayout(3, 3));
		panel_center_kanan.add(lbl_phonenum);
		panel_center_kanan.add(txt_phonenum);
		
		panel_center_kanan.add(lbl_gender);
		JPanel panel_gender = new JPanel();
		panel_gender.setLayout(new GridLayout(2,1));
		ButtonGroup bg_gender = new ButtonGroup();
		bg_gender.add(radio_male);
		bg_gender.add(radio_female);
		panel_gender.add(radio_male);
		panel_gender.add(radio_female);
		panel_center_kanan.add(panel_gender);
		
		panel_center_kanan.add(lbl_blood);
		combo_blood.addItem("A");
		combo_blood.addItem("B");
		combo_blood.addItem("AB");
		combo_blood.addItem("O");
		panel_center_kanan.add(combo_blood);
		
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
		
		setTitle("Registration Patient!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public MainForm() {
		initComponent();
		load_patient_data();
		load_table_patient();
	}
	
	public static void main(String[] args) {
		new MainForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btn_submit)) {
			String id = txt_id.getText();
			String name = txt_name.getText();
			int age = Integer.parseInt(txt_age.getText());
			String address = txt_address.getText();
			String phoneNumber = txt_phonenum.getText();
			String gender = "";
			
			if(radio_male.isSelected()) {
				gender = "Male";
			}else if(radio_female.isSelected()) {
				gender = "Female";
			}
			
			String blood = combo_blood.getSelectedItem().toString();
			
			Object[] row = {id, name, age, address, phoneNumber, gender, blood};
			dtm_table_patient.addRow(row);
			patients.add(new Patient(id, name, age, address, phoneNumber, gender, blood));
			table_patient.invalidate();
		}
		
		else if(obj.equals(btn_clear)) {
			
		}
		
		else if (obj.equals(btn_delete)) {
			int selectedRow = table_patient.getSelectedRow();
			if(selectedRow != -1) {
				dtm_table_patient.removeRow(selectedRow);
				patients.remove(selectedRow);
				table_patient.invalidate();
			}
		}
	}

}
