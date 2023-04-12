package bill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.foreign.Addressable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BillForm extends JFrame implements ActionListener{
	//declare tabel
	private JTable table_bill;
	private JScrollPane scrollpane_table_bill;
	private DefaultTableModel dtm_table_bill;
	
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	//panel
	private JPanel panel_north = new JPanel();
	private JLabel header_title = new JLabel("BILL FORM");
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
	private JTextField txt_date_start = new JTextField();
	private JTextField txt_date_end = new JTextField();
	private JTextField txt_proof = new JTextField();
	
	private JLabel id = new JLabel("ID : ");
	private JLabel date_start = new JLabel("Date Start : ");
	private JLabel date_end = new JLabel("Date End : ");
	private JLabel virtualAcc = new JLabel("     VA : 1234567890");
	private JLabel proof = new JLabel("     Proof : ");
	private JLabel space = new JLabel();
	
	//footer
	private JPanel panel_southFrame = new JPanel();
	private JPanel panel_south = new JPanel();
	private JPanel panel_space_south1 = new JPanel();
	private JPanel panel_space_south2 = new JPanel();
	private JPanel panel_space_south3 = new JPanel();
	private JPanel panel_space_south4 = new JPanel();
	private JButton btn_submit = new JButton("Submit");
	private JButton btn_clear = new JButton("Clear");
	private JButton btn_delete = new JButton("Delete");
	
	private ArrayList<Bill> bills = new ArrayList<Bill>();
	
	public void load_bill_data() {
		
	}
	
	public void load_table_bill() {
		String[] column = {"ID", "Date Start", "Date End", "Proof"};
		dtm_table_bill = new DefaultTableModel(column, 0);
		
		for(Bill bill : bills) {
			String id = bill.getId();
			String date_start = bill.getDate_start();
			String date_end = bill.getDate_end();
			String proof = bill.getProof();
			
			Object[] row = {id, date_start, date_end, proof};
			dtm_table_bill.addRow(row);
		}
		table_bill.setModel(dtm_table_bill);
	}
	
	void init_component(){
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
		
		// Table
		table_bill = new JTable();
		scrollpane_table_bill = new JScrollPane(table_bill);
		// scrollpane_table_bill.setBackground(Color.GREEN);
		panel_north_table.add(scrollpane_table_bill, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
				
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		//content
		panel_center.setLayout(new GridLayout(1, 2));
		
		panel_center_kiri.setLayout(new GridLayout(4,4));
		
		panel_center_kiri.add(id);
		panel_center_kiri.add(txt_id);

		panel_center_kiri.add(date_start);
		panel_center_kiri.add(txt_date_start);
		
		panel_center_kiri.add(date_end);
		panel_center_kiri.add(txt_date_end);
		
		panel_center.add(panel_center_kiri);
		
		panel_center_kanan.setLayout(new GridLayout(3, 4));
		
		panel_center_kanan.add(virtualAcc);
		panel_center_kanan.add(space);
		
		panel_center_kanan.add(proof);
		panel_center_kanan.add(txt_proof);
		
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
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	public BillForm() {
		init_component();
		load_bill_data();
		load_table_bill();
	}

	public static void main(String[] args) {
		new BillForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btn_submit)) {
			boolean flag = false;
			String id = txt_id.getText();
			String date_start = txt_date_start.getText();
			String date_end = txt_date_end.getText();
			String proof = txt_proof.getText();

			//VALIDATION
			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "ID must be filled!");
				flag = false;
			}else{
				JOptionPane.showMessageDialog(null, "Bill has generated!!");
				flag = true;
			}

			//STORE DATA DI TABLE
			Object[] row = {id, date_start, date_end, proof};

			if(flag == true){
				dtm_table_bill.addRow(row);
				bills.add(new Bill(id, date_start, date_end, date_end, proof));
				table_bill.invalidate();
			}

			txt_id.setText("");
			txt_date_start.setText("");
			txt_date_end.setText("");
			txt_proof.setText("");

		}else if(obj.equals(btn_delete)){
			int selectedRow = table_bill.getSelectedRow();
			if(selectedRow != -1){
				dtm_table_bill.removeRow(selectedRow);
				bills.remove(selectedRow);
				table_bill.invalidate();
			}
		}else if(obj.equals(btn_clear)){
			dtm_table_bill.setRowCount(0);
		}
	}

}
