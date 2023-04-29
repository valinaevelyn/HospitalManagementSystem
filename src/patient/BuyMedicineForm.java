package patient;

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
import java.util.concurrent.CompletableFuture;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Payment.Payment;
import medicine.Medicine;
import profile.User;

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
	
	private JLabel lbl_name = new JLabel("Name");
	private JLabel lbl_qty = new JLabel("Quantity");
	private JLabel lbl_price = new JLabel("Price");
	private JLabel lbl_max_quan = new JLabel("Maximal Quantity");

	private JTextField txt_name = new JTextField();
	private JTextField txt_quantity = new JTextField();
	private JTextField txt_price = new JTextField();
	private JTextField txt_max_quan = new JTextField();
	
	private JPanel panel_southFrame = new JPanel();
	private JPanel panel_south = new JPanel();
	private JPanel panel_space_south1 = new JPanel();
	private JPanel panel_space_south2 = new JPanel();
	private JButton btn_continue = new JButton("Continue Payment");
	private JButton btn_cancel = new JButton("Cancel");
	
	private ArrayList<Medicine> medicines = new ArrayList<>();
	private ArrayList<Payment> payments = new ArrayList<>();
	private ArrayList<User> users = new ArrayList<>();

	private static int index;

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		BuyMedicineForm.index = index;
	}

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
		
		File file2 = new File("src/database/payment.txt");
		try{
			Scanner scan = new Scanner(file2);
			String[] raw;
			String name;
			int total;
			String service;
			String status;

			while(scan.hasNextLine()){
				raw = scan.nextLine().split("#");
				name = raw[0];
				total = Integer.parseInt(raw[1]);
				service = raw[2];
				status = raw[3];

				payments.add(new Payment(name, total, service, status));
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		File file3 = new File("src/database/user.txt");
        try{
            Scanner scan = new Scanner(file3);
            String[] raw;
            String username;
            String role;
            String password;
            String name;

            while(scan.hasNextLine()){
                raw = scan.nextLine().split("#");
                username = raw[0];
                role = raw[1];
                password = raw[2];
                name = raw[3];

                users.add(new User(username, role, password, name));
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
		panel_center.setLayout(new GridLayout(4,4));
		panel_center.add(lbl_name);
		panel_center.add(txt_name);
		panel_center.add(lbl_price);
		panel_center.add(txt_price);
		panel_center.add(lbl_max_quan);
		panel_center.add(txt_max_quan);
		panel_center.add(lbl_qty);
		panel_center.add(txt_quantity);
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
		txt_max_quan.setEditable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,680);
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
				
				String max_quan = table_medicine.getValueAt(row, 4).toString();
				txt_max_quan.setText(max_quan);

				double price = (double) dtm_table_medicine.getValueAt(row, 3);
				String price_txt = table_medicine.getValueAt(row, 3).toString();
				txt_price.setText(price_txt);
			}
		});
	}

	public static void main(String[] args) {
		new BuyMedicineForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(btn_continue)) {
			int check = 1;
			String name = txt_name.getText();
			String quantity = txt_quantity.getText();
			double price = Double.parseDouble(txt_price.getText());
			int max_quan = Integer.parseInt(txt_max_quan.getText());
			int qtyCheck = Integer.parseInt(quantity);

			if(name.equals("")){
				JOptionPane.showMessageDialog(null, "Name field can not be empty!");
				check *= 0;
                return;
			} else{
				check *= 1;
			}

			check = 0;
			for(Medicine m: medicines){
				if(name.equals(m.getName())){
					check = 1;
				} 
			}
			
			if(check == 0){
				JOptionPane.showMessageDialog(null, "The product you entered is not available or invalid!");
                return;
			}

			if(quantity.equals("")){
				JOptionPane.showMessageDialog(null, "Quantity field can not be empty!");
				check = 0;
                return;
			} else{
				check = 1;
			}
			
			if(quantity.equals("0")){
				JOptionPane.showMessageDialog(null, "Quantity can not be 0!");
				check = 0;
                return;
			} else{
				check = 1;
			}

			for (int i = 0; i < quantity.length(); i++) {
				if (!Character.isDigit(quantity.charAt(i))) {
					JOptionPane.showMessageDialog(null, "Quantity field must be field with numbers only!");
					check = 0;
					return;
				} else{
					check = 1;
				}
			}

			if(qtyCheck > max_quan){
				JOptionPane.showMessageDialog(null, "Quantity must be less than Maximal Quantity!");
				check = 0;
				txt_quantity.setText("");
                return;
			}

			int price2 = (int) price;
			int total = qtyCheck * price2;
			JOptionPane.showMessageDialog(null, "Your total is Rp" + total +  ". Please report to receptionist to continue payment.");

			File file = new File("src/database/payment.txt");
			try {
				FileWriter writer = new FileWriter(file, true);
                writer.write(users.get(index).getName()+"#"+total+"#"+name+"#"+"Belum Dibayar"+"\n");
                // payments.add(new Payment(name, ));
                writer.close();
			} catch (IOException a) {
				System.out.println("File not found!");
			}

			txt_max_quan.setText("");
			txt_name.setText("");
			txt_price.setText("");
			txt_quantity.setText("");
		}
		
		else if(obj.equals(btn_cancel)) {
			txt_name.setText("");
			txt_quantity.setText("");
		}
	}

}
