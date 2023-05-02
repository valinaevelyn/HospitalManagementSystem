package medicine;

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

import patient.Patient;

public class MedicineForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("MEDICINE");
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
	
	private JLabel lbl_id = new JLabel("ID");
	private JLabel lbl_name = new JLabel("Name");
	private JLabel lbl_function = new JLabel("Function");
	private JLabel lbl_price = new JLabel("   Price");
	private JLabel lbl_stock = new JLabel("   Stock");
	private JLabel lbl_type = new JLabel("   Type");
	
	private JTextField txt_id = new JTextField();
	private JTextField txt_name = new JTextField();
	private JTextArea txt_function = new JTextArea();
	private JTextField txt_price = new JTextField();
	private JTextField txt_stock = new JTextField();
	private JComboBox<String> combo_type = new JComboBox<>();
	
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
		
		
		//Content
		panel_center.setLayout(new GridLayout(1,2));
		
		panel_center_kiri.setLayout(new GridLayout(3,3));
		panel_center_kiri.add(lbl_id);
		panel_center_kiri.add(txt_id);

		panel_center_kiri.add(lbl_name);
		panel_center_kiri.add(txt_name);
		
		panel_center_kiri.add(lbl_function);
		panel_center_kiri.add(txt_function);
		
		panel_center.add(panel_center_kiri);
		
		panel_center_kanan.setLayout(new GridLayout(3, 3));
		panel_center_kanan.add(lbl_price);
		panel_center_kanan.add(txt_price);
		
		panel_center_kanan.add(lbl_stock);
		panel_center_kanan.add(txt_stock);
		
		panel_center_kanan.add(lbl_type);
		combo_type.addItem("Capsul");
		combo_type.addItem("Syrup");
		combo_type.addItem("Bubuk");
		combo_type.addItem("Cream");
		panel_center_kanan.add(combo_type);
		
		panel_center.add(panel_center_kanan);
		
		add(panel_center, "Center");
		
		//Footer
		panel_southFrame.setLayout(new BorderLayout());
		panel_southFrame.add(panel_space_south1, "North");
		panel_southFrame.add(panel_space_south2, "Center");
		
		panel_south.setLayout(new FlowLayout());
		panel_south.add(btn_submit);
		btn_submit.addActionListener(this);
		panel_south.add(btn_update);
		btn_update.addActionListener(this);
		panel_south.add(btn_clear);
		btn_clear.addActionListener(this);
		panel_south.add(btn_delete);
		btn_delete.addActionListener(this);
		panel_southFrame.add(panel_south, "South");
		add(panel_southFrame, "South");
		
		setTitle("Medicine!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public MedicineForm() {
		initComponent();
		load_medicine_data();
		load_table_medicine();
		
		table_medicine.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_medicine.getSelectedRow();
				String id = table_medicine.getValueAt(row,  0).toString();
				txt_id.setText(id);
				
				String name = table_medicine.getValueAt(row, 1).toString();
				txt_name.setText(name);
				
				String function = table_medicine.getValueAt(row, 2).toString();
				txt_function.setText(function);
				
				String price = table_medicine.getValueAt(row, 3).toString();
				txt_price.setText(price);
				
				String stock = table_medicine.getValueAt(row, 4).toString();
				txt_stock.setText(stock);
				
				String type = table_medicine.getValueAt(row, 5).toString();
				combo_type.setSelectedItem(type);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btn_submit)) {
			int check = 1;
			String id = txt_id.getText();
			String name = txt_name.getText();
			String function = txt_function.getText();
			double price = Double.parseDouble(txt_price.getText());
			int stock = Integer.parseInt(txt_stock.getText());
			String type = combo_type.getSelectedItem().toString();

			//VALIDATION
			String id_validation = "M+[0-9]+[0-9]+[0-9]";
			if(id.matches(id_validation)){
				check *=1;
			}else{
				JOptionPane.showMessageDialog(null, "ID must start with 'M'");
				check *=0;
				return;
			}

			if(name.length()>10){
				check *=1;
			}else{
				check *=0;
				JOptionPane.showMessageDialog(null, "Name must be more than 10 characters");
				return;
			}

			if(price >= 500){
				check *=1;
			}else{
				check *=0;
				JOptionPane.showMessageDialog(null, "Price must be more than 500.00!");
				return;
			}

			if(stock >= 0){
				check *=1;
			}else{
				check *=0;
				JOptionPane.showMessageDialog(null, "Stock must be more than 0");
				return;
			}
			
			//STORE DATA DI DALAM TABEL
			Object[] row = {id, name, function, price, stock, type};
			if(check == 1){
				dtm_table_medicine.addRow(row);
				medicines.add(new Medicine(id, name, function, price, stock, type));
				table_medicine.invalidate();
			}
			
			File file = new File("src/database/medicine.txt");
			try {
				FileWriter writer = new FileWriter(file, true);
				writer.write(id+"#"+name+"#"+function+"#"+price+"#"+stock+"#"+type+"\n");
				medicines.add(new Medicine(id, name, function, price, stock, type));
				writer.close();
			}catch(IOException a) {
				System.out.println("File Not Found!");
			}
			
			txt_id.setText("");
			txt_name.setText("");
			txt_function.setText("");
			txt_price.setText("");
			txt_stock.setText("");
			combo_type.setSelectedItem("");
		}
		
		else if(obj.equals(btn_clear)) {
			dtm_table_medicine.setRowCount(0);
			try {
				FileWriter writer = new FileWriter("src/database/medicine.txt");
				writer.write("");
				writer.close();
				System.out.println("File cleared succesfully!");
			}catch(IOException a){
				System.out.println("File Not Found!");
			}
		}
		else if(obj.equals(btn_delete)) {
			int selectedRow = table_medicine.getSelectedRow();
			if(selectedRow != -1) {
			    File file = new File("src/database/medicine.txt");
			    ArrayList<Medicine> tempMedicines = new ArrayList<Medicine>();

				try {
			        Scanner scan = new Scanner(file);
			        String[] raw;
			        String currId;
			        String name;
			        String function;
			        double price;
			        int stock;
			        String type;
			        String id = (String) dtm_table_medicine.getValueAt(selectedRow, 0);

			        while(scan.hasNextLine()) {
			            raw = scan.nextLine().split("#");
			            currId = raw[0];
			            name = raw[1];
			            function = raw[2];
			            price = Double.parseDouble(raw[3]);
			            stock = Integer.parseInt(raw[4]);
			            type = raw[5];
			            
			            if (!currId.equals(id)) {
			                tempMedicines.add(new Medicine(currId, name, function, price, stock, type));
			            }
			        }
			    } catch (FileNotFoundException a) {
			        JOptionPane.showMessageDialog(null, a.getMessage());
			    }
			    

			    try {
			        FileWriter writer = new FileWriter(file);

			        for (Medicine m : tempMedicines) {
			            String medicineData = m.getId() + "#" + m.getName() + "#" + m.getFunction() + "#" + m.getPrice() + "#" + m.getStock() + "#" + m.getType() + "\n";
			            writer.write(medicineData);
			        }

			        writer.close();
			    } catch (IOException a) {
			        JOptionPane.showMessageDialog(null, a.getMessage());
			    }

			    // Refresh the data in the ArrayList and the JTable
				dtm_table_medicine.removeRow(tempMedicines.size()-1);
			    medicines.clear();
			    load_medicine_data();
			    load_table_medicine();
				table_medicine.invalidate();

				txt_id.setText("");
				txt_name.setText("");
				txt_function.setText("");
				txt_price.setText("");
				txt_stock.setText("");
				combo_type.setSelectedItem("");
			}
		}else if(obj.equals(btn_update)) {
			int selectedUpdate = table_medicine.getSelectedRow();
			if(selectedUpdate >= 0){
				String id = txt_id.getText();
				String name = txt_name.getText();
				String function = txt_function.getText();
				double price = Double.parseDouble(txt_price.getText());
				int stock = Integer.parseInt(txt_stock.getText());
				String type = combo_type.getSelectedItem().toString();

				dtm_table_medicine.setValueAt(id, selectedUpdate, 0);
				dtm_table_medicine.setValueAt(name, selectedUpdate, 1);
				dtm_table_medicine.setValueAt(function, selectedUpdate, 2);
				dtm_table_medicine.setValueAt(price, selectedUpdate, 3);
				dtm_table_medicine.setValueAt(stock, selectedUpdate, 4);
				dtm_table_medicine.setValueAt(combo_type.getSelectedItem(), selectedUpdate, 5);

				medicines.get(selectedUpdate).setId(id);
				medicines.get(selectedUpdate).setName(name);
				medicines.get(selectedUpdate).setFunction(function);
				medicines.get(selectedUpdate).setPrice(price);
				medicines.get(selectedUpdate).setStock(stock);
				medicines.get(selectedUpdate).setType(type);

				//write file
				try{
					File file = new File("src/database/medicine.txt");
					FileWriter writer = new FileWriter(file);
					
					for (Medicine m : medicines) {
			            String medicineData = m.getId() + "#" + m.getName() + "#" + m.getFunction() + "#" + m.getPrice() + "#" + m.getStock() + "#" + m.getType() + "\n";
			            writer.write(medicineData);
			        }
					writer.close();
					JOptionPane.showMessageDialog(null, "Data has been updated!");
				}catch (IOException a){
					System.out.println("File not found!");
				}
				//biar kehapus
				txt_id.setText("");
				txt_name.setText("");
				txt_function.setText("");
				txt_price.setText("");
				txt_stock.setText("");
				combo_type.setSelectedItem("");
			}
		}
	}
}
