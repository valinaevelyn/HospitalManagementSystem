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
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private JButton btn_update = new JButton("Update");
	
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
		panel_south.add(btn_update);
		btn_update.addActionListener(this);
		panel_southFrame.add(panel_south, "South");
		add(panel_southFrame, "South");
		
		setTitle("Register Room");
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

		table_room.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e){
				int row = table_room.getSelectedRow();

				String number = table_room.getValueAt(row, 0).toString();
				txt_number.setText(number);

				String type = table_room.getValueAt(row, 1).toString();
				combo_type.setSelectedItem(type);

				String duration = table_room.getValueAt(row, 2).toString();
				txt_duration.setText(duration);

				String charge = table_room.getValueAt(row, 3).toString();
				txt_charge.setText(charge);
			}
		});
	}

	// public static void main(String[] args) {
	// 	new RoomForm();
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btn_submit)) {
			int check = 1;
			String number = txt_number.getText();
			String type = combo_type.getSelectedItem().toString();
			String durationTemp = txt_duration.getText();
			String chargeTemp = txt_charge.getText();
			
			//VALIDATION
			String number_validation = "[0-9]+[0-9]+[0-9]";
			
			if(!number.matches(number_validation)) {
				JOptionPane.showMessageDialog(null, "Number must be XXX");
				check*=0;
				txt_number.setText("");
				return;
			}else if(durationTemp.equals("")){
				JOptionPane.showMessageDialog(null, "Duration field must be filled!");
				txt_duration.setText("");
				check*=0;
				return;	
			}else if(chargeTemp.equals("")){
				JOptionPane.showMessageDialog(null, "Charge field must be filled!");
				txt_charge.setText("");
				check*=0;
				return;
			}
				
	
			Double charge = Double.parseDouble(txt_charge.getText());
			int duration = Integer.parseInt(txt_duration.getText());
			
			//STORE DATA DI TABEL
			Object[] row = {number, type, duration, charge};

			if(check == 1){
				dtm_table_room.addRow(row);
				rooms.add(new Room(number, type, duration, charge));
				table_room.invalidate();
			}

			File file = new File("src/database/dataroom.txt");
       		try{
				FileWriter writer = new FileWriter(file, true);
				writer.write(number+"#"+type+"#"+duration+"#"+charge+"\n");
				rooms.add(new Room(number, type, duration, charge));
				writer.close();
        	}catch (IOException a){
				System.out.println("File not found!");
			}

			txt_number.setText("");
			combo_type.setSelectedItem("");
			txt_duration.setText("");
			txt_charge.setText("");

		}else if(obj.equals(btn_delete)){
			int selectedRow = table_room.getSelectedRow();
			if(selectedRow != -1){
				File file = new File("src/database/dataroom.txt");
				ArrayList<Room> tempRooms = new ArrayList<Room>();
				
				try {
					Scanner scan = new Scanner(file);
					String[] raw;
					String currNumber;
					String type;
					int duration;
					double charge;
					String number = (String) dtm_table_room.getValueAt(selectedRow, 0);
					
					while(scan.hasNextLine()) {
						raw = scan.nextLine().split("#");
						currNumber = raw[0];
						type = raw[1];
						duration = Integer.parseInt(raw[2]);
						charge = Double.parseDouble(raw[3]);
						
						if(!currNumber.equals(number)) {
							tempRooms.add(new Room(currNumber, type, duration, charge));
						}
					}
				} catch (FileNotFoundException a) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
				
				try {
					FileWriter writer = new FileWriter(file);
					for(Room room : tempRooms) {
						String line = room.getNumber() + "#" +  room.getType() + "#" + room.getDuration() + "#" + room.getCharge();
						writer.write(line + "\n");
					}
					writer.close();
				} catch (IOException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
				dtm_table_room.removeRow(rooms.size()-1);
				rooms.clear();
				load_room_data();
				load_table_room();
				table_room.invalidate();

				txt_number.setText("");
				combo_type.setSelectedItem("");
				txt_duration.setText("");
				txt_charge.setText("");
			}
		}else if(obj.equals(btn_clear)){
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear?", "Select an option", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
				txt_number.setText("");
				combo_type.setSelectedItem("");
				txt_duration.setText("");
				txt_charge.setText("");
            }
		}else if(obj.equals(btn_update)){
			int selectedUpdate = table_room.getSelectedRow();
			if(selectedUpdate >= 0){
				String number = txt_number.getText();
				String type = combo_type.getSelectedItem().toString();
				Integer duration = Integer.parseInt(txt_duration.getText());
				Double charge = Double.parseDouble(txt_charge.getText());
				
				rooms.get(selectedUpdate).setNumber(number);
				rooms.get(selectedUpdate).setType(type);
				rooms.get(selectedUpdate).setDuration(duration);
				rooms.get(selectedUpdate).setCharge(charge);

				dtm_table_room.setValueAt(number, selectedUpdate, 0);
				dtm_table_room.setValueAt(combo_type.getSelectedItem(), selectedUpdate, 1);
				dtm_table_room.setValueAt(duration, selectedUpdate, 2);
				dtm_table_room.setValueAt(charge, selectedUpdate, 3);

				try{
					File file = new File("src/database/dataroom.txt");
					FileWriter writer = new FileWriter(file);
					
					for(Room r : rooms){
						String line = r.getNumber() + "#" + r.getType() + "#" + r.getDuration() + "#" + r.getCharge() +"\n";
						writer.write(line);
					}
					writer.close();
					JOptionPane.showMessageDialog(null, "Data has been updated!");
				}catch (IOException a){
					System.out.println("File not found!");
				}
				
				txt_number.setText("");
				combo_type.setSelectedItem("");
				txt_duration.setText("");
				txt_charge.setText("");
			}
		}
	}
}