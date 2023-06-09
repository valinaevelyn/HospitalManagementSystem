package bill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import patient.Patient;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private JLabel virtualAcc = new JLabel("     Rek. 1234567890 (BCA a/n RS. AAA)");
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
	private JButton btn_update = new JButton("Update");
	
	private ArrayList<Bill> bills = new ArrayList<Bill>();
	// Date & Time
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd MMMM yyyy");
	
	public void load_bill_data() {
		File file = new File("src/database/bill.txt");
		try{
			Scanner scan = new Scanner(file);
			String[] raw;
			String id;
			String date_start;
			String date_end;
			String proof;

			while(scan.hasNextLine()){
				raw = scan.nextLine().split("#");
				id = raw[0];
				date_start = raw[1];
				date_end = raw[2];
				proof = raw[3];

				bills.add(new Bill(id, date_start, date_end, proof));
			}

			for(Bill bill : bills){
				System.out.println(bill.getId());
			}
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "File is not found!");
		}
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
		panel_south.add(btn_update);
		btn_update.addActionListener(this);
		panel_southFrame.add(panel_south, "South");
		add(panel_southFrame, "South");
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,650);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	public BillForm() {
		init_component();
		load_bill_data();
		load_table_bill();

		table_bill.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_bill.getSelectedRow();

				String id = table_bill.getValueAt(row,  0).toString();
				txt_id.setText(id);
				
				String date_start = table_bill.getValueAt(row, 1).toString();
				txt_date_start.setText(date_start);
				
				String date_end = table_bill.getValueAt(row, 2).toString();
				txt_date_end.setText(date_end);
				
				String proof = table_bill.getValueAt(row, 3).toString();
				txt_proof.setText(proof);
			}
		});
	}

	// public static void main(String[] args) {
	// 	new BillForm();
	// }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btn_submit)) {
			Date dateChecker = null;
			int check = 1;
			String id = txt_id.getText();
			String date_start = txt_date_start.getText();
			String date_end = txt_date_end.getText();
			String proof = txt_proof.getText();

			//VALIDATION
			String id_validation = "B+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]";
			if(id.matches(id_validation)) {
				check *=1;
			}else {
				JOptionPane.showMessageDialog(null, "ID format must be ''BXXXXXX'' (XXXXXX = 6 digit numbers)");
				check *=0;
				return;
			}

			if(id.length() == 7) check *= 1;
            else{
                JOptionPane.showMessageDialog(null, "ID format must be ''BXXXXXX'' (XXXXXX = 6 digit numbers)");
                check *= 0;
                return;
            } 

			dateFormat.setLenient(false);
			try{
				Date datechecker2 = dateFormat.parse(date_start);
			} catch (ParseException ee) {
				JOptionPane.showMessageDialog(null, "Invalid start date format!");
                check *= 0;
                txt_date_start.setText("");
                return;
            }
			
			dateFormat.setLenient(false);
			try{
				Date datechecker2 = dateFormat.parse(date_end);
			} catch (ParseException ee) {
				JOptionPane.showMessageDialog(null, "Invalid end date format!");
                check *= 0;
                txt_date_end.setText("");
                return;
            }
			
			try {
                dateChecker = dateFormat.parse(date_start);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateChecker);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
            
                if (year < 1900 || year > 2100 || month < 0 || month > 11 || day < 1 || day > 31) {
                    JOptionPane.showMessageDialog(null, "Invalid date start format, please use dd MMMM yyyy format. (ex : 1 April 1900)");
                    check *= 0;
                    txt_date_start.setText("");
                    return;
                }
                check *= 1;
            } catch (ParseException ee) {
                JOptionPane.showMessageDialog(null, "Invalid date start format, please use dd MMMM yyyy format. (ex : 1 April 1900)");
                check *= 0;
                txt_date_start.setText("");
                return;
            }
			
			try {
                dateChecker = dateFormat.parse(date_end);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateChecker);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
            
                if (year < 1900 || year > 2100 || month < 0 || month > 11 || day < 1 || day > 31) {
                    JOptionPane.showMessageDialog(null, "Invalid date end format, please use dd MMMM yyyy format. (ex : 1 April 1900)");
                    check *= 0;
                    txt_date_end.setText("");
                    return;
                }
            
                check *= 1;
            } catch (ParseException ee) {
                JOptionPane.showMessageDialog(null, "Invalid date end format, please use dd MMMM yyyy format. (ex : 1 April 1900)");
                check *= 0;
                txt_date_end.setText("");
                return;
            }

			Date userInputDate = null;
            try {
                userInputDate = dateFormat.parse(date_start);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

			Date userInputDate1 = null;
            try {
                userInputDate1 = dateFormat.parse(date_end);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

			if(userInputDate != null && userInputDate1 != null){
				if(userInputDate1.before(userInputDate)){
					JOptionPane.showMessageDialog(null, "End Date has to be after Start Date!");
                    check *= 0;
					txt_date_end.setText("");
                    return;
				}
			} else {
                System.out.println("Invalid date input");
                txt_date_start.setText("");
                txt_date_end.setText("");
                check *= 0;
                return;
            }

			if(proof.equals("")){
				JOptionPane.showMessageDialog(null, "Proof field can not be empty!");
                txt_proof.setText("");
				check *=0;
                return;
			} else{
				check *= 1;
			}

			//STORE DATA DI TABLE
			Object[] row = {id, date_start, date_end, proof};

			if(check == 1){
				dtm_table_bill.addRow(row);
				bills.add(new Bill(id, date_start, date_end, proof));
				table_bill.invalidate();
			}

			File file = new File("src/database/bill.txt");
			try{
				FileWriter writer = new FileWriter(file, true);
				writer.write(id+"#"+date_start+"#"+date_end+"#"+proof+"\n");
				bills.add(new Bill(id, date_start, date_end, proof));
				writer.close();
			}catch(IOException a){
				System.out.println("File not found!");
			}

			txt_id.setText("");
			txt_date_start.setText("");
			txt_date_end.setText("");
			txt_proof.setText("");
			JOptionPane.showMessageDialog(null, "Bill has been submited!");
		}else if(obj.equals(btn_delete)){
			int selectedRow = table_bill.getSelectedRow();
			if(selectedRow != -1) {
				File file = new File("src/database/bill.txt");
				ArrayList<Bill> tempBills = new ArrayList<Bill>();
				
				try {
					Scanner scan = new Scanner(file);
					String[] raw;
					String currId;
					String date_start;
					String date_end;
					String proof;
					
					String id = (String) dtm_table_bill.getValueAt(selectedRow, 0);
					
					while(scan.hasNextLine()) {
						raw = scan.nextLine().split("#");
						currId = raw[0];
						date_start = raw[1];
						date_end = raw[2];
						proof = raw[3];
						
						if(!currId.equals(id)) {
							tempBills.add(new Bill(currId, date_start, date_end, proof));
						}
					}
					
				} catch (FileNotFoundException a) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
				
				try {
					FileWriter writer = new FileWriter(file);
					for(Bill bill: tempBills) {
						String billData = bill.getId() + "#" + bill.getDate_start() + "#" + bill.getDate_end() + "#" + bill.getProof()  + "\n";
						writer.write(billData);
					}
					writer.close();
				} catch (IOException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
				dtm_table_bill.removeRow(tempBills.size()-1);
				bills.clear();
				load_bill_data();
				load_table_bill();
				table_bill.invalidate();

				txt_id.setText("");
				txt_date_start.setText("");
				txt_date_end.setText("");
				txt_proof.setText("");	
				JOptionPane.showMessageDialog(null, "Bill has been deleted!");
			}
		}else if(obj.equals(btn_clear)){
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear text fields?", "Select an option", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
				txt_id.setText("");
				txt_date_start.setText("");
				txt_date_end.setText("");
				txt_proof.setText("");
            }
		}else if(obj.equals(btn_update)){
			int selectedUpdate = table_bill.getSelectedRow();
			if(selectedUpdate >= 0){
				String id = txt_id.getText();
				String date_start = txt_date_start.getText();
				String date_end = txt_date_end.getText();
				String proof = txt_proof.getText();
				

				dtm_table_bill.setValueAt(id, selectedUpdate, 0);
				dtm_table_bill.setValueAt(date_start, selectedUpdate, 1);
				dtm_table_bill.setValueAt(date_end, selectedUpdate, 2);
				dtm_table_bill.setValueAt(proof, selectedUpdate, 3);

				//set datanya
				bills.get(selectedUpdate).setId(id);
				bills.get(selectedUpdate).setDate_start(date_start);
				bills.get(selectedUpdate).setDate_end(date_end);
				bills.get(selectedUpdate).setProof(proof);
				
				//write file
				File file = new File("src/database/bill.txt");
				try{
					FileWriter writer = new FileWriter(file);
					for(Bill bill: bills) {
						String billData = bill.getId() + "#" + bill.getDate_start() + "#" + bill.getDate_end() + "#" + bill.getProof();
						writer.write(billData + "\n");
					}
					writer.close();
					JOptionPane.showMessageDialog(null, "Data has been updated!");
				}catch (IOException a){
					System.out.println("File not found!");
				}

				//biar kehapus
				txt_id.setText("");
				txt_date_start.setText("");
				txt_date_end.setText("");
				txt_proof.setText("");
				JOptionPane.showMessageDialog(null, "Bill has been updated!");
			}
		}
	}

}
