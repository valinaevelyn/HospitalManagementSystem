package appointment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import doctor.Doctor;
import patient.Patient;

public class ViewAppointment extends JFrame implements ActionListener{
    
    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    // Header
    private JPanel panel_north = new JPanel();
    private JLabel lbl_makeAppointment = new JLabel("VIEW APPOINTMENT");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
    // Component
    private JPanel panel_left = new JPanel();
    private JPanel panel_right = new JPanel();
    
    private JTable table_appointment;
    private JScrollPane scrollpane_table_appointment;
    private DefaultTableModel dtm_table_appointment;

    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    
    public void load_appointment_data() {
    	File file = new File("src/database/appointment.txt");
		try {
			Scanner scan = new Scanner(file);
			String[] raw;
			String id;
			String name;
			String complaint;
			String date;
			String time;
			String doctorName;
			
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				id = raw[0];
				name = raw[1];
				complaint = raw[2];
				date = raw[3];
				time = raw[4];
				doctorName = raw[5];
				
				// appointments.add(new Appointment(id, patientt.getName(), complaint, date, time, doctorr.getName()));
			}

            for(Appointment appointment : appointments){
                System.out.println(appointment.getPatient());
            }
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }

    // public void saveFile(ArrayList<Appointment> appointments){
    //     try{
    //         PrintWriter writer = new PrintWriter("src/database/appointment.txt");
    //         for(int i = 0; i < appointments.size(); i++){
    //             writer.println(i);
    //         }
    //         writer.close();
    //     }catch (FileNotFoundException e){
    //         System.out.println("File not found!");
    //     }
    // }
    
    public void load_table_appointment() {
		String[] column = {"ID", "Name", "Complaints", "Date", "Time", "Doctor Name"};
		dtm_table_appointment = new DefaultTableModel(column, 0);
		
		for(Appointment app: appointments) {
			String id = app.getId();
			String name = app.getPatient().getName();
			String complaints = app.getComplaint();
			String date = app.getDate();
			String time = app.getTime();
			String doctorName = app.getDoctor().getName();
			
			Object[] row = {id, name, complaints, date, time, doctorName};
			dtm_table_appointment.addRow(row);
		}
		table_appointment.setModel(dtm_table_appointment); 
    }
    
    void init_components(){
        setLayout(new BorderLayout());

		//Header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(lbl_makeAppointment, "North");
		lbl_makeAppointment.setFont(font_title);
		lbl_makeAppointment.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, "Center");
		
		panel_north_table.setLayout(new BorderLayout());
		
		
		// Table
		table_appointment = new JTable();
		scrollpane_table_appointment = new JScrollPane(table_appointment);
		panel_north_table.add(scrollpane_table_appointment, "North");
		panel_north_table.add(panel_space_north2,"South");
		panel_north.add(panel_north_table, "South");
		
		add(panel_north, "North");
		add(panel_left, "West");
		add(panel_right, "East");
    

        setTitle("View Appointment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public ViewAppointment() {
        init_components();
        load_table_appointment();
        load_appointment_data();
    }
    
    public static void main(String[] args) {
        new ViewAppointment();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

        Object obj = e.getSource();
         
    }

}