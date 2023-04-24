package doctor;

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

import appointment.Appointment;
import patient.Patient;

public class BillForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("PEMBAYARAN");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_bill;
	private JScrollPane scrollpane_table_bill;
	private DefaultTableModel dtm_table_bill;
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	private JLabel lbl_id = new JLabel("ID");
	private JLabel lbl_name = new JLabel("Name");
	private JLabel lbl_complaints = new JLabel("Complaints");
	private JLabel lbl_date = new JLabel("Date");
	private JLabel lbl_time = new JLabel("Time");
	private JLabel lbl_bill_id = new JLabel("   Bill ID");
	private JLabel lbl_bill_date = new JLabel("   Bill Date");
	private JLabel lbl_bill_vacc = new JLabel("   Virtual Account");
	private JLabel lbl_bill_prove = new JLabel("   Bill Prove");
	
	private JTextField txt_id = new JTextField();
	private JTextField txt_name = new JTextField();
	private JTextField txt_complaints = new JTextField();
	private JTextField txt_date = new JTextField();
	private JTextField txt_time = new JTextField();
	private JTextField txt_bill_id = new JTextField();
	private JTextField txt_bill_date = new JTextField();
	private JTextField txt_bill_vacc = new JTextField();
	private JTextField txt_bill_prove = new JTextField();
	
	private JPanel panel_southFrame = new JPanel();
	private JPanel panel_south = new JPanel();
	private JPanel panel_space_south1 = new JPanel();
	private JPanel panel_space_south2 = new JPanel();
	private JPanel panel_space_south3 = new JPanel();
	private JPanel panel_space_south4 = new JPanel();
	private JButton btn_submit = new JButton("Submit");
	private JButton btn_clear = new JButton("Clear");
	private JButton btn_delete = new JButton("Delete");
	
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	
	public void load_appointment_data() {
		File file = new File("src/database/appointment.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String id;
			String name;
			String complaints;
			String date;
			String time;
			String doctorName;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				id = raw[0];
				name = raw[1];
				complaints = raw[2];
				date = raw[3];
				time = raw[3];
				doctorName = raw[4];
				
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	}
	
	public void load_table_bill() {
		String[] column = {"ID", "Name", "Complaints", "Date", "Time"};
		dtm_table_bill = new DefaultTableModel(column, 0);
		
		for(Appointment app: appointments) {
			String id = app.getId();
			String name = app.getPatientName();
			String complaints = app.getComplaint();
			String date = app.getDate();
			String time = app.getTime();
			
			Object[] row = {id, name, complaints, date, time};
			dtm_table_bill.addRow(row);
		}
		table_bill.setModel(dtm_table_bill);
	}
	
	public void init_components() {
		setLayout(new BorderLayout());
		
		//Header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(lbl_register, "North");
		lbl_register.setFont(font_title);
		lbl_register.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, "Center");
		
		panel_north_table.setLayout(new BorderLayout());
		
		
		// Table
		table_bill = new JTable();
		scrollpane_table_bill = new JScrollPane(table_bill);
		panel_north_table.add(scrollpane_table_bill, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		
		//Content
		panel_center.setLayout(new GridLayout(1,2));
		
		panel_center_kiri.setLayout(new GridLayout(5,5));
		panel_center_kiri.add(lbl_id);
		panel_center_kiri.add(txt_id);

		panel_center_kiri.add(lbl_name);
		panel_center_kiri.add(txt_name);
		
		panel_center_kiri.add(lbl_complaints);
		panel_center_kiri.add(txt_complaints);
		
		panel_center_kiri.add(lbl_date);
		panel_center_kiri.add(txt_date);
		
		panel_center_kiri.add(lbl_time);
		panel_center_kiri.add(txt_time);
		
		panel_center.add(panel_center_kiri);
		
		panel_center_kanan.setLayout(new GridLayout(4, 4));
		panel_center_kanan.add(lbl_bill_id);
		panel_center_kanan.add(txt_bill_id);
		
		panel_center_kanan.add(lbl_bill_date);
		panel_center_kanan.add(txt_bill_date);
		
		panel_center_kanan.add(lbl_bill_vacc);
		panel_center_kanan.add(txt_bill_vacc);
		
		panel_center_kanan.add(lbl_bill_prove);
		panel_center_kanan.add(txt_bill_prove);
		
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,680);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public BillForm() {
		init_components();
		load_table_bill();
	}
	
	public static void main(String[] args) {
		new BillForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
