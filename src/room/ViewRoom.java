package room;
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

public class ViewRoom extends JFrame implements ActionListener{
	//declare tabel 
	private JTable table_room;
	private JScrollPane scrollpane_table_room;
	private DefaultTableModel dtm_table_room;
	
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	//header
	private JPanel panel_north = new JPanel();
	private JLabel header_title = new JLabel("VIEW ROOM DATA");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
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
		
		panel_center.add(panel_center_kanan);
		
		add(panel_center, "Center");
		
		setTitle("View Room Data");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public ViewRoom() {
		init_component();
		load_room_data();
		load_table_room();
	}

	// public static void main(String[] args) {
	// 	new ViewRoom();
	// }

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
