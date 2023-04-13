package room;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RoomForm extends JFrame implements ActionListener{
	//declare tabel 
	private JTable table_room;
	private JScrollPane scrollpane_table_room;
	private DefaultTableModel dtm_table_room;
	
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	//header
	private JPanel panel_north = new JPanel();
	private JLabel header_title = new JLabel("ROOM REGISTRATION");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	//content
	private JTextField txt_number = new JTextField(20);
	private JComboBox<String> combo_type = new JComboBox<>();
	private JTextField txt_duration = new JTextField();
	private JTextField txt_charge = new JTextField();
	
	private JLabel number = new JLabel("Room No : ");
	private JLabel type = new JLabel("Room Type : ");
	private JLabel duration = new JLabel("     Duration : ");
	private JLabel charge = new JLabel("     Room Charge : ");
	private JLabel space = new JLabel(" ");
	
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
	
	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	void load_room_data() {
		File file = new File("src/database/dataroom.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String number;
			String type;
			int duration;
			double charge;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				number = raw[0];
				type = raw[1];
				duration = Integer.parseInt(raw[2]);
				charge = Double.parseDouble(raw[3]);
				
				rooms.add(new Room(number, type, duration, charge));
			}
			for(Room room : rooms) {
				System.out.println(room.getNumber());
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	void load_table_room() {
		String[] column = {"Room Number", "Room Type", "Duration : day(s)", "Room Charge"};
		dtm_table_room = new DefaultTableModel(column, 0);
		
		for(Room room : rooms) {
			String number = room.getNumber();
			String type = room.getType();
			int duration = room.getDuration();
			double charge = room.getCharge();
			
			Object[] row = {number, type, duration, charge};
			dtm_table_room.addRow(row);
		}
		table_room.setModel(dtm_table_room);
	}
	
	void init_component() {
		setLayout(new BorderLayout());
		//header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(header_title, "North");
		
		// panel_north.setBackground(Color.RED);
		
		header_title.setFont(font_title);
		header_title.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, "Center");
		// panel_space_north.setBackground(Color.BLUE);
		
		panel_north_table.setLayout(new BorderLayout());
		// panel_north_table.setBackground(Color.CYAN);
		
		// Table
		table_room = new JTable();
		scrollpane_table_room = new JScrollPane(table_room);
		// scrollpane_table_room.setBackground(Color.GREEN);
		panel_north_table.add(scrollpane_table_room, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		// panel_north_table.setBackground(Color.CYAN);
			
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		//content
		panel_center.setLayout(new GridLayout(1,2));
		
		panel_center_kiri.setLayout(new GridLayout(2,2));
//		panel_center_kiri.setBackground(Color.CYAN);
		
		panel_center_kiri.add(number);
		panel_center_kiri.add(txt_number);

		
		panel_center_kiri.add(type);
		combo_type.addItem("Regular");
		combo_type.addItem("VIP");
		combo_type.addItem("VVIP");
		panel_center_kiri.add(combo_type);


		panel_center.add(panel_center_kiri);
		
		panel_center_kanan.setLayout(new GridLayout(2,2));
		panel_center_kanan.add(duration);
		panel_center_kanan.add(txt_duration);

		
		panel_center_kanan.add(charge);
		panel_center_kanan.add(txt_charge);
	
		
		panel_center.add(panel_center_kanan);
		
		add(panel_center, "Center");
		
		//footer
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
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public RoomForm() {
		init_component();
		load_room_data();
		load_table_room();
	}

	public static void main(String[] args) {
		new RoomForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btn_submit)) {
			boolean flag = false;
			String number = txt_number.getText();
			String type = combo_type.getSelectedItem().toString();
			Integer duration = Integer.parseInt(txt_duration.getText());
			Double charge = Double.parseDouble(txt_charge.getText());
			
			//VALIDATION
			if(number.length()!=3) {
				JOptionPane.showMessageDialog(null, "Room Number must between 001 until 999");
				flag = false;
			}else{
				JOptionPane.showMessageDialog(null, "Room Registration Success!");
				flag = true;
			}

			//STORE DATA DI TABEL
			Object[] row = {number, type, duration, charge};

			if(flag == true){
				dtm_table_room.addRow(row);
				rooms.add(new Room(number, type, duration, charge));
				table_room.invalidate();
			}

		}else if(obj.equals(btn_delete)){
			int selectedRow = table_room.getSelectedRow();
			if(selectedRow != -1){
				dtm_table_room.removeRow(selectedRow);
				rooms.remove(selectedRow);
				table_room.invalidate();
			}
		}else if(obj.equals(btn_clear)){
			dtm_table_room.setRowCount(0);
		}
	}

}
