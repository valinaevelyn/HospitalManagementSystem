package medicine;

import java.awt.BorderLayout;
import java.awt.Font;
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

import patient.Patient;

public class ViewMedicine extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("MEDICINE STOCK LIST");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JTable table_medicine;
	private JScrollPane scrollpane_table_medicine;
	private DefaultTableModel dtm_table_medicine;
	
	private ArrayList<Medicine> medicines = new ArrayList<Medicine>();
	
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
		table_medicine = new JTable();
		scrollpane_table_medicine = new JScrollPane(table_medicine);
		panel_north_table.add(scrollpane_table_medicine, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
		
		setTitle("Medicine Stock List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public ViewMedicine() {
		initComponent();
		load_medicine_data();
		load_table_medicine();
	}
	
	public static void main(String[] args) {
		new ViewMedicine();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	}

}

