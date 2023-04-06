package room;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.table.DefaultTableModel;

import patient.Patient;

public class RoomForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("REGISTRASI RUANGAN");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_room;
	private JScrollPane scrollpane_table_room;
	private DefaultTableModel dtm_table_room;
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	private JLabel lbl_number = new JLabel("Room Number");
	private JLabel lbl_status = new JLabel("Room Status");
	private JLabel lbl_capacity = new JLabel("Room Capacity");
	
	private JTextField txt_number = new JTextField();
	private JTextField txt_status = new JTextField();
	private JTextField txt_capacity = new JTextField();
	
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
	
	public void load_room_data() {
		File file = new File("src/database/room.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			int number;
			int capacity;
			String status;
			boolean checkStatus;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				number = Integer.parseInt(raw[0]);
				capacity = Integer.parseInt(raw[1]);
				status = raw[2];
				
				if (status.equals("Non-Available")) {
					checkStatus = false;
				}else {
					checkStatus = true;
				}
				
				rooms.add(new Room(number, checkStatus, capacity));
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void load_table_room() {
		String[] column = {"Room Number", "Room Capacity", "Room Status"};
		dtm_table_room = new DefaultTableModel(column, 0);
		
		for(Room room: rooms) {
			int number = room.getNumber();
			int capacity = room.getCapacity();
			String status = room.checkRoomStatus();
			
			Object[] row = {number, capacity, status};
			dtm_table_room.addRow(row);
		}
		table_room.setModel(dtm_table_room);
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
		table_room = new JTable();
		scrollpane_table_room = new JScrollPane(table_room);
		panel_north_table.add(scrollpane_table_room, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		// Content
		panel_center.setLayout(new GridLayout(1,1));
		
		panel_center_kiri.setLayout(new GridLayout(3,3));
		panel_center_kiri.add(lbl_number);
		panel_center_kiri.add(txt_number);

		panel_center_kiri.add(lbl_capacity);
		panel_center_kiri.add(txt_capacity);
		
		panel_center_kiri.add(lbl_status);
		panel_center_kiri.add(txt_status);
	
		panel_center.add(panel_center_kiri);
		
		add(panel_center, "Center");
		
		// Footer
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
		
		setTitle("Registration Room");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
		
	public RoomForm() {
		initComponent();
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
			int number = Integer.parseInt(txt_number.getText());
			int capacity = Integer.parseInt(txt_capacity.getText());
			String status = txt_status.getText();
			
			boolean checkStatus;
			
			if(status == "Non-Available") {
				checkStatus = false;
			}else {
				checkStatus = true;
			}
			
			Object[] row = {number, capacity, status};
			dtm_table_room.addRow(row);
			rooms.add(new Room(number, checkStatus, capacity));
			table_room.invalidate();
		}
		
		else if(obj.equals(btn_clear)) {
			
		}
		
		else if(obj.equals(btn_delete)) {
			int selectedRow = table_room.getSelectedRow();
			if(selectedRow != -1) {
				dtm_table_room.removeRow(selectedRow);
				rooms.remove(selectedRow);
				table_room.invalidate();
			}
		}
	}

}
