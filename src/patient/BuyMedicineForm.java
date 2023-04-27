package patient;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import medicine.Medicine;

public class BuyMedicineForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("BUY MEDICINE");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_medicine;
	private JScrollPane scrollpane_table_medicine;
	private DefaultTableModel dtm_table_medicine;
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	private JLabel lbl_name = new JLabel("Name");
	private JLabel lbl_qty = new JLabel("Quantity");
	private JLabel lbl_total = new JLabel("Total");

	private JTextField txt_name = new JTextField();
	private JTextField txt_quantity = new JTextField();
	private JTextField txt_total = new JTextField();
	
	private JPanel panel_southFrame = new JPanel();
	private JPanel panel_south = new JPanel();
	private JPanel panel_space_south1 = new JPanel();
	private JPanel panel_space_south2 = new JPanel();
	private JPanel panel_space_south3 = new JPanel();
	private JPanel panel_space_south4 = new JPanel();
	private JButton btn_continue = new JButton("Continue Payment");
	private JButton btn_cancel = new JButton("Cancel");
	
	private ArrayList<Medicine> medicines = new ArrayList<>();
	
	public void load_medicine_data() {
		File file = new File("src/database/medicine.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String id;
			String name;
			String function;
			double price;
			int stock;
			String type;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				id = raw[0];
				name = raw[1];
				function = raw[2];
				price = Double.parseDouble(raw[3]);
				stock = Integer.parseInt(raw[4]);
				type = raw[5];
				
				medicines.add(new Medicine(id, name, function, price, stock, type));
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	}
	
	public void load_table_medicine() {
		String[] column = {"ID", "Name", "Function", "Price", "Stock", "Type"};
		dtm_table_medicine = new DefaultTableModel(column, 0);
		
		for(Medicine medicine: medicines) {
			String id = medicine.getId();
			String name = medicine.getName();
			String function = medicine.getFunction();
			double price = medicine.getPrice();
			int stock = medicine.getStock();
			String type = medicine.getType();
			
			Object[] row = {id, name, function, price, stock, type};
			dtm_table_medicine.addRow(row);
		}
		
		table_medicine.setModel(dtm_table_medicine);;
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
		table_medicine = new JTable();
		scrollpane_table_medicine = new JScrollPane(table_medicine);
		panel_north_table.add(scrollpane_table_medicine, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		//Content
		panel_center.setLayout(new GridLayout(3,3));
		panel_center.add(lbl_name);
		panel_center.add(txt_name);
		panel_center.add(lbl_qty);
		panel_center.add(txt_quantity);
		panel_center.add(lbl_total);
		panel_center.add(txt_total);
		panel_center.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 0));
		add(panel_center, "Center");
		
		//Footer
		panel_southFrame.setLayout(new BorderLayout());
		panel_southFrame.add(panel_space_south1, "North");
		panel_southFrame.add(panel_space_south2, "Center");
		
		panel_south.setLayout(new FlowLayout());
		panel_south.add(btn_continue);
		btn_continue.addActionListener(this);
		panel_south.add(btn_cancel);
		btn_cancel.addActionListener(this);
		panel_southFrame.add(panel_south, "South");
		add(panel_southFrame, "South");
		
		setTitle("Medicine");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public BuyMedicineForm() {
		init_components();
		load_medicine_data();
		load_table_medicine();
		
		table_medicine.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_medicine.getSelectedRow();
				String name = table_medicine.getValueAt(row, 1).toString();
				txt_name.setText(name);
				
				double price = (double) dtm_table_medicine.getValueAt(row, 3);
				int quantity = Integer.parseInt(txt_quantity.getText());
				double total = price * quantity;
				txt_total.setText(String.valueOf(total));
			}
		});
	}
//
//	public static void main(String[] args) {
//		new BuyMedicineForm();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(btn_continue)) {
			
		}
		
		else if(obj.equals(btn_cancel)) {
			txt_name.setText("");
			txt_quantity.setText("");
		}
	}

}
