package Payment;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import medicine.Medicine;

public class PaymentForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_confirm = new JLabel("CONFIRM PAYMENT");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_payment;
	private JScrollPane scrollpane_table_payment;
	private DefaultTableModel dtm_table_payment;
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	private JLabel lbl_name = new JLabel("Name");
	private JLabel lbl_keterangan = new JLabel("Keterangan");

	private JTextField txt_name = new JTextField();
	private JTextField txt_keterangan = new JTextField();
	
	private JPanel panel_southFrame = new JPanel();
	private JPanel panel_south = new JPanel();
	private JPanel panel_space_south1 = new JPanel();
	private JPanel panel_space_south2 = new JPanel();
	private JPanel panel_space_south3 = new JPanel();
	private JPanel panel_space_south4 = new JPanel();
	private JButton btn_confirm = new JButton("Confirm");
	
	private ArrayList<Payment> payments = new ArrayList<Payment>();
	
	public void load_payment_data() {
		File file = new File("src/database/payment.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String name;
			String keterangan;
			int total;
			String statusPayment;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				name = raw[0];
				keterangan = raw[1];
				total = Integer.parseInt(raw[2]);
				statusPayment = raw[3];
				
				boolean status = false;
				
				if(statusPayment.equals("Belum Dibayar")) {
					status = false;
				}else if(statusPayment.equals("Sudah Lunas")) {
					status = true;
				}
				
//				payments.add(new Payment(name, keterangan, total, status));
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	}
	
	public void load_table_payment() {
		String[] column = {"Name", "Keterangan", "Total", "Status Payment"};
		dtm_table_payment = new DefaultTableModel(column, 0);
		
		for(Payment payment: payments) {
			String name = payment.getPatient().getName();
			String keterangan = payment.getKeterangan();
			int total = payment.getTotal();
			String statusPayment = payment.checkStatusPayment();
			
			Object[] row = {name, keterangan, total, statusPayment};
			dtm_table_payment.addRow(row);
		}
		
		table_payment.setModel(dtm_table_payment);;
	}
	
	public void init_components() {
		
		setLayout(new BorderLayout());
		
		//Header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(lbl_confirm, "North");
		lbl_confirm.setFont(font_title);
		lbl_confirm.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, "Center");
		
		panel_north_table.setLayout(new BorderLayout());
		
		
		// Table
		table_payment = new JTable();
		scrollpane_table_payment = new JScrollPane(table_payment);
		panel_north_table.add(scrollpane_table_payment, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		//Content
		panel_center.setLayout(new GridLayout(2,2));
		panel_center.add(lbl_name);
		panel_center.add(txt_name);
		panel_center.add(lbl_keterangan);
		panel_center.add(txt_keterangan);
		panel_center.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 0));
		add(panel_center, "Center");
		
		//Footer
		panel_southFrame.setLayout(new BorderLayout());
		panel_southFrame.add(panel_space_south1, "North");
		panel_southFrame.add(panel_space_south2, "Center");
		
		panel_south.setLayout(new FlowLayout());
		panel_south.add(btn_confirm);
		btn_confirm.addActionListener(this);
		panel_southFrame.add(panel_south, "South");
		add(panel_southFrame, "South");
		
		setTitle("Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public PaymentForm() {
		init_components();
//		load_payment_data();
		load_table_payment();
	}
	
	public static void main(String[] args) {
		new PaymentForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
