package appointment;

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
import profile.User;

public class CancelAppointment extends JFrame implements ActionListener{
    
    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    // Header
    private JPanel panel_north = new JPanel();
    private JLabel lbl_makeAppointment = new JLabel("CANCEL APPOINTMENT");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
    // Component
    private JPanel panel_left = new JPanel();
    private JPanel panel_right = new JPanel();
    
    private JTable table_appointment;
    private JScrollPane scrollpane_table_appointment;
    private DefaultTableModel dtm_table_appointment;

    // Footer 
    private JPanel panel_south = new JPanel();
    private JPanel panel_south_frame = new JPanel();
    private JPanel panel_space_south1 = new JPanel();
    private JPanel panel_space_south2 = new JPanel();
    private JButton btn_save = new JButton("SAVE CHANGES");
    private JButton btn_cancel = new JButton("CANCEL");

    private static int index;

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		CancelAppointment.index = index;
	}

    private static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private static ArrayList<User> users = new ArrayList<User>();

    public void load_appointment_data() {
        File fileUser = new File("src/database/user.txt");
        try{
            Scanner scan = new Scanner(fileUser);
            String[] raw;
            String username;
            String role;
            String password;
            String name;
            
            while(scan.hasNextLine()){
                raw = scan.nextLine().split("#");
                // if(raw.length != 4) {
				// 	// System.out.println("Invalid data format");
				// 	continue;
				// }
                username = raw[0];
                role = raw[1];
                password = raw[2];
                name = raw[3];
                
                users.add(new User(username, role, password, name));
            }
            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
		}
        
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

				if(name.equals(users.get(index).getName())){
					appointments.add(new Appointment(id, name, complaint, date, time, doctorName));
				} 
			}

//            for(Appointment appointment : appointments){
//                System.out.println(appointment.getPatient());
//            }
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }

    public void saveFile(ArrayList<Appointment> appointments){
        try{
            PrintWriter writer = new PrintWriter("src/database/appointment.txt");
            for(int i = 0; i < appointments.size(); i++){
                writer.println(i);
            }
            writer.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        }
    }
    
    public void load_table_appointment() {
		String[] column = {"ID", "Name", "Complaints", "Date", "Time", "Doctor Name"};
		dtm_table_appointment = new DefaultTableModel(column, 0);
		
		for(Appointment app: appointments) {
			String id = app.getId();
			String name = app.getPatientName();
			String complaints = app.getComplaint();
			String date = app.getDate();
			String time = app.getTime();
			String doctorName = app.getDoctorName();
			
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
        
        // Footer
        panel_south_frame.setLayout(new BorderLayout());
        panel_south_frame.add(panel_space_south1, "North");
        panel_south_frame.add(panel_space_south2, "Center");

        panel_south.setLayout(new FlowLayout());
        btn_save.addActionListener(this);
        panel_south.add(btn_save);

        btn_cancel.addActionListener(this);
        panel_south.add(btn_cancel);
        add(panel_south, "South");

        panel_south_frame.add(panel_south, "South");
        add(panel_south_frame, "South");

        setTitle("Cancel Appointment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 543);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public CancelAppointment() {
        init_components();
        load_appointment_data();
        load_table_appointment();
        appointments.clear();
    }
    
    public static void main(String[] args) {
        new CancelAppointment();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

        Object obj = e.getSource();
        if(obj.equals(btn_save)){
            dispose();
            JOptionPane.showMessageDialog(null, "Changes Saved Successfully!");
        } 

        if(obj.equals(btn_cancel)){
            int selectedRow = table_appointment.getSelectedRow();
			if(selectedRow != -1) {
				// dtm_table_appointment.removeRow(selectedRow);
				// appointments.remove(selectedRow);
                File file = new File("src/database/appointment.txt");
                ArrayList<Appointment> tempApt = new ArrayList<Appointment>();

                try {
                    Scanner scan = new Scanner(file);
                    String[] raw;
                    String id;
                    String name;
                    String complaint;
                    String date;
                    String time;
                    String doctorName;
                    String tempId = (String) dtm_table_appointment.getValueAt(selectedRow, 0);
                    
                    while(scan.hasNextLine()) {
                        raw = scan.nextLine().split("#");
                        id = raw[0];
                        name = raw[1];
                        complaint = raw[2];
                        date = raw[3];
                        time = raw[4];
                        doctorName = raw[5];

                        if(!id.equals(tempId)) tempApt.add(new Appointment(id, name, complaint, date, time, doctorName));
                    }

        //            for(Appointment appointment : appointments){
        //                System.out.println(appointment.getPatient());
        //            }
                    
                } catch (FileNotFoundException a) {
                    JOptionPane.showMessageDialog(null, a.getMessage());
                }

                try{
                    FileWriter writer = new FileWriter(file);
                    for(Appointment appointment: tempApt){
                        String aptData = appointment.getId() + "#" + appointment.getPatientName() + "#" + appointment.getComplaint() + "#" + appointment.getDate() + "#" + appointment.getTime() + "#" + appointment.getDoctorName();
                        writer.write(aptData + "\n");
                    }

                    writer.close();
                } catch (IOException a) {
			        JOptionPane.showMessageDialog(null, a.getMessage());
			    }

                appointments.clear();
                load_appointment_data();
                load_table_appointment();
				table_appointment.invalidate();
                JOptionPane.showMessageDialog(null, "Appointment Cancelled Successfully!");
			}
        } 
        
    }

}