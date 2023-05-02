package Payment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
				
				if(statusPayment.equals("Belum Dibayar")){
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
		load_payment_data();
		load_table_payment();

		table_payment.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_payment.getSelectedRow();
				String name = table_payment.getValueAt(row, 0).toString();
				txt_name.setText(name);

				String service = table_payment.getValueAt(row, 2).toString();
				txt_keterangan.setText(service);
			}
			
		});
	}
	
	// public static void main(String[] args) {
	// 	new PaymentForm();
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btn_confirm)){
			int row = table_payment.getSelectedRow();
	        if(row == -1) {
	            JOptionPane.showMessageDialog(null, "Please select a payment record.");
	            return;
	        }

	        String name = txt_name.getText();
	        String keterangan = txt_keterangan.getText();
	        String statusPayment = "Sudah Dibayar"; // change payment status to "Sudah Dibayar"

	        Payment payment = payments.get(row);
	        payment.setKeterangan(keterangan);
	        payment.setStatus(statusPayment);

	        // update the payment record in the JTable
	        table_payment.setValueAt(keterangan, row, 2);
	        table_payment.setValueAt(statusPayment, row, 3);

	        // update the txt file with the updated payments ArrayList
	        try {
	            FileWriter writer = new FileWriter("src/database/payment.txt");
	            for (Payment p : payments) {
	                String line = p.getPatientName() + "#" + p.getTotal() + "#" + p.getKeterangan() + "#" + p.getStatus() + "\n";
	                writer.write(line);
	            }
	            writer.close();
	            JOptionPane.showMessageDialog(null, "Payment has been confirmed.");
	        } catch (IOException ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage());
	        }
		}
	}

}
