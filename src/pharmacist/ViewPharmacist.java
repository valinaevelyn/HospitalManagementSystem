package pharmacist;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
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


public class ViewPharmacist extends JFrame implements ActionListener{
	
	//declare tabel
	private JTable table_pharmacist;
	private JScrollPane scrollpane_table_pharmacist;
	private DefaultTableModel dtm_table_pharmacist;
		
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
		
	//panel
	private JPanel panel_north = new JPanel();
	private JLabel header_title = new JLabel("VIEW PHARMACIST");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
		
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
		
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
		
	
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
		
		panel_center.add(panel_center_kiri);
		
		panel_center_kanan.setLayout(new GridLayout(3, 3));
		
		JPanel panel_gender = new JPanel();
		panel_gender.setLayout(new GridLayout(2,1));
		panel_center_kanan.add(panel_gender);
		
		
		panel_center.add(panel_center_kanan);
		
		add(panel_center, "Center");
		
		//Footer
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,500);
        setTitle("View Pharmacist");
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public ViewPharmacist() {
		init_component();
		load_pharmacist_data();
		load_table_pharmacist();
	}

	// public static void main(String[] args) {
	// 	new ViewPharmacist();
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
