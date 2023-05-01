package pharmacist;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
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

import medicine.Medicine;

public class PharmacistForm extends JFrame implements ActionListener{
	
	//declare tabel
	private JTable table_pharmacist;
	private JScrollPane scrollpane_table_pharmacist;
	private DefaultTableModel dtm_table_pharmacist;
		
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
		
	//panel
	private JPanel panel_north = new JPanel();
	private JLabel header_title = new JLabel("REGISTER PHARMACIST");
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
	private ButtonGroup bg_gender;
	private JTextField txt_experience = new JTextField();
		
	private JLabel id = new JLabel("ID");
	private JLabel name = new JLabel("Name");
	private JLabel age = new JLabel("Age");
	private JLabel address = new JLabel("Address");
	private JLabel phoneNumber = new JLabel("   Phone Number");
	private JLabel gender = new JLabel("   Gender");
	private JLabel experience = new JLabel("   Experience (years)");
		
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
	private JButton btn_update = new JButton("Update");
	private ArrayList<Pharmacist> pharmacists = new ArrayList<Pharmacist>();
		
	private int size = 0;

	public void load_pharmacist_data(){
		File file = new File("src/database/datapharmacist.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String id;
			String name;
			int age;
			String address;
			String phoneNumber;
			String gender;
			int experience;
			
			//while not end of file
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				id = raw[0];
				name = raw[1];
				age = Integer.parseInt(raw[2]);
				address = raw[3];
				phoneNumber = raw[4];
				gender = raw[5];
				experience = Integer.parseInt(raw[6]);
				// space = raw[7];
				pharmacists.add(new Pharmacist(id, name, age, address, phoneNumber, gender, experience));
				size++;
			}
			
			for(Pharmacist pharmacist : pharmacists) {
				System.out.println(pharmacist.getName());
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void load_table_pharmacist() {
		String[] column = {"ID", "Name", "Age", "Address", "Phone Number", "Gender", "Experience (years))"};
		dtm_table_pharmacist = new DefaultTableModel(column, 0);
		
		for(Pharmacist pharmacist : pharmacists) {
			String id = pharmacist.getId();
			String name = pharmacist.getName();
			int age = pharmacist.getAge();
			String address = pharmacist.getAddress();
			String phoneNumber = pharmacist.getPhoneNumber();
			String gender = pharmacist.getGender();
			int experience = pharmacist.getExperience();
			
			Object[] row = {id, name, age, address, phoneNumber, gender, experience};
			dtm_table_pharmacist.addRow(row);
		}
		table_pharmacist.setModel(dtm_table_pharmacist);
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
		table_pharmacist = new JTable();
		scrollpane_table_pharmacist = new JScrollPane(table_pharmacist);
//		scrollpane_table_pharmacist.setBackground(Color.GREEN);
		panel_north_table.add(scrollpane_table_pharmacist, "North");
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
		bg_gender = new ButtonGroup();
		bg_gender.add(radio_male);
		bg_gender.add(radio_female);
		panel_gender.add(radio_male);
		panel_gender.add(radio_female);
		panel_center_kanan.add(panel_gender);
		
		panel_center_kanan.add(experience);
		panel_center_kanan.add(txt_experience);
		
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
		panel_south.add(btn_update);
		btn_update.addActionListener(this);
		panel_southFrame.add(panel_south, "South");
		add(panel_southFrame, "South");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public PharmacistForm() {
		init_component();
		load_pharmacist_data();
		load_table_pharmacist();

		table_pharmacist.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_pharmacist.getSelectedRow();

				String id = table_pharmacist.getValueAt(row,  0).toString();
				txt_id.setText(id);
				
				String name = table_pharmacist.getValueAt(row, 1).toString();
				txt_name.setText(name);
				
				String age = table_pharmacist.getValueAt(row, 2).toString();
				txt_age.setText(age);
				
				String address = table_pharmacist.getValueAt(row, 3).toString();
				txt_address.setText(address);
				
				String phoneNum = table_pharmacist.getValueAt(row, 4).toString();
				txt_phone.setText(phoneNum);

				String gender = table_pharmacist.getValueAt(row, 5).toString();

				if(gender.equals("Female")){
					radio_female.setSelected(true);
				}else if(gender.equals("Male")){
					radio_male.setSelected(true);
				}

				String experience = table_pharmacist.getValueAt(row, 6).toString();
				txt_experience.setText(experience);
			}
		});
	}

	public static void main(String[] args) {
		new PharmacistForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_submit)) {
			int check = 1;
			String id = txt_id.getText();
			String name = txt_name.getText();
			int age = Integer.parseInt(txt_age.getText());
			String address = txt_address.getText();
			String phoneNumber = txt_phone.getText();
			String gender = "";
			
			if(radio_male.isSelected()) {
				gender = "Male";
			}else if(radio_female.isSelected()) {
				gender = "Female";
			}
			
			int experience = Integer.parseInt(txt_experience.getText());

			
			//VALIDATION
			String id_validation = "P+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+";
			if(id.matches(id_validation)) {
				check *=1;
			}else {
				JOptionPane.showMessageDialog(null, "ID must be true!");
				check *=0;
			}
			if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "Name must be filled!");
				check *=0;
			}else {
				check *=1;
			}
			if(age < 20) {
				JOptionPane.showMessageDialog(null, "Age must be more than 20 years old!");
				check *=0;
			}else {
				check *=1;
			}
			if(experience < 1) {
				JOptionPane.showMessageDialog(null, "Must be more than 1 year experience");
				check *=0;
			}else {
				check *=1;
			}
			
			//STORE DATA DI TABEL
			Object[] row = {id, name, age, address, phoneNumber, gender, experience};
			if(check == 1) {
				dtm_table_pharmacist.addRow(row);
				pharmacists.add(new Pharmacist(id, name, age, address, phoneNumber, gender, experience));
				table_pharmacist.invalidate();
			}

			File file = new File("src/database/datapharmacist.txt");
       		try{
				FileWriter writer = new FileWriter(file, true);
				writer.write(id+"#"+name+"#"+age+"#"+address+"#"+phoneNumber+"#"+gender+"#"+experience+"\n");
				pharmacists.add(new Pharmacist(id, name, age, address, phoneNumber, gender, experience));
				writer.close();
        	}catch (IOException a){
				System.out.println("File not found!");
			}

			txt_id.setText("");
			txt_name.setText("");
			txt_age.setText("");
			txt_address.setText("");
			txt_phone.setText("");
			bg_gender.clearSelection();
			txt_experience.setText("");

		}else if(e.getSource().equals(btn_delete)) {
			File file = new File("src/database/datapharmacist.txt");
			int selectedRow = table_pharmacist.getSelectedRow();
			if(selectedRow != -1) {
				dtm_table_pharmacist.removeRow(selectedRow);
				pharmacists.remove(selectedRow);
				table_pharmacist.invalidate();
			}
			
			try {
				FileWriter writer = new FileWriter("src/database/medicine.txt");
				// for (Pharmacist p : pharmacists) {
				// 	writer.write(p.getId() + "#" + p.getName() + "#" + p.getAge() + "#" + p.getAddress() + "#" + p.getPhoneNumber() + "#" + p.getGender() + "#" + p.getExperience() + "\n");

				
				writer.write(id+"#"+name+"#"+age+"#"+address+"#"+phoneNumber+"#"+gender+"#"+experience+"\n");
				// pharmacists.remove(new Pharmacist(id, name, age, address, phoneNumber, gender, experience));
				writer.close();
			}catch(IOException a){
				System.out.println("File Not Found!");
			}
		}else if(e.getSource().equals(btn_clear)) {
			dtm_table_pharmacist.setRowCount(0);
			try {
				FileWriter writer = new FileWriter("src/database/datapharmacist.txt");
				writer.write("");
				writer.close();
				System.out.println("File cleared succesfully!");
			}catch(IOException a){
				System.out.println("File Not Found!");
			}
		}else if(e.getSource().equals(btn_update)){
			int selectedUpdate = table_pharmacist.getSelectedRow();
			if(selectedUpdate >= 0){
				String id = txt_id.getText();
				String name = txt_name.getText();
				int age = Integer.parseInt(txt_age.getText());
				String address = txt_address.getText();
				String phoneNumber = txt_phone.getText();
				String gender = "";
				if(radio_male.isSelected()) {
					gender = "Male";
				}else if(radio_female.isSelected()) {
					gender = "Female";
				}
				int experience = Integer.parseInt(txt_experience.getText());

				dtm_table_pharmacist.setValueAt(id, selectedUpdate, 0);
				dtm_table_pharmacist.setValueAt(name, selectedUpdate, 1);
				dtm_table_pharmacist.setValueAt(age, selectedUpdate, 2);
				dtm_table_pharmacist.setValueAt(address, selectedUpdate, 3);
				dtm_table_pharmacist.setValueAt(phoneNumber, selectedUpdate, 4);
				dtm_table_pharmacist.setValueAt(gender, selectedUpdate, 5);
				dtm_table_pharmacist.setValueAt(experience, selectedUpdate, 6);

				//set datanya
				pharmacists.get(selectedUpdate).setId(id);
				pharmacists.get(selectedUpdate).setName(name);
				pharmacists.get(selectedUpdate).setAge(age);
				pharmacists.get(selectedUpdate).setAddress(address);
				pharmacists.get(selectedUpdate).setPhoneNumber(phoneNumber);
				if(pharmacists.get(selectedUpdate).getGender() == "Male"){
					pharmacists.get(selectedUpdate).setGender("Male");
				}else if(pharmacists.get(selectedUpdate).getGender() == "Female"){
					pharmacists.get(selectedUpdate).setGender("Female");
				}
				pharmacists.get(selectedUpdate).setGender(gender);
				pharmacists.get(selectedUpdate).setExperience(experience);

				//write update file (INI MASIH BINGUNG TAPI INSYATUHAN OKE OKE OKE OKE TINGGAL CARI DIKIT)
				try{
					File file = new File("src/database/datapharmacist.txt");
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String line = ""; 
					String oldText = "";

					String oldId = id;
					String oldName = name;
					int oldAge = age;
					String oldAddress = address;
					String oldPhoneNumber = phoneNumber;
					String oldGender = gender;
					int oldExperience = experience;

					while((line = reader.readLine())!= null){
						oldText+=line + "#";
						oldId += line + "#";
						oldName += line + "#";
						// oldAge += line + "#";
						oldAddress += line + "#";
						oldPhoneNumber += line + "#";
						oldGender += line + "#";
						// oldExperience += line + "#";
					}
					reader.close();

					String newText = oldText.replaceAll("old text", "new text");
					String newId = oldId.replaceAll(oldId, id);
					String newName = oldName.replaceAll(oldName, name);

					FileWriter writer = new FileWriter(file);
					writer.write(id+"#"+name+"#"+age+"#"+address+"#"+phoneNumber+"#"+gender+"#"+experience+"\n");
					writer.close();
					}catch (IOException a){
					System.out.println("File not found!");
					}

				//biar kehapus
				txt_id.setText("");
				txt_name.setText("");
				txt_age.setText("");
				txt_address.setText("");
				txt_phone.setText("");
				bg_gender.clearSelection();
				txt_experience.setText("");
			}
		}
		
	}
}
