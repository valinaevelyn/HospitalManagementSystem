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

public class PaymentDoneBayar extends JFrame{
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
	private JButton btn_confirm = new JButton("Confirm Payment");
	
	private ArrayList<Payment> payments = new ArrayList<Payment>();
	
	public void load_payment_data() {
		File file = new File("src/database/payment.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String name;
			int total;
			String keterangan;
			String statusPayment;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				name = raw[0];
				total = Integer.parseInt(raw[1]);
				keterangan = raw[2];
				statusPayment = raw[3];
				
				if(statusPayment.equals("Sudah Dibayar")){
					payments.add(new Payment(name, total, keterangan, statusPayment));
				}
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	}
	
	public void load_table_payment() {
		String[] column = {"Patient Name", "Total", "Service/Product Name", "Payment Status"};
		dtm_table_payment = new DefaultTableModel(column, 0);
		
		for(Payment payment: payments) {
			String name = payment.getPatientName();
			int total = payment.getTotal();
			String keterangan = payment.getKeterangan();
			String statusPayment = payment.getStatus();
			
			Object[] row = {name, total, keterangan, statusPayment};
			dtm_table_payment.addRow(row);
		}
		
		table_payment.setModel(dtm_table_payment);
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
		
		
		setTitle("View Resolved Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public PaymentDoneBayar() {
		init_components();
		load_payment_data();
		load_table_payment();
	}

	// public static void main(String[] args) {
	// 	new PaymentDoneBayar();
	// }

}
