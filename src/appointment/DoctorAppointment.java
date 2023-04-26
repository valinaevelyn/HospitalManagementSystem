package appointment;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

import hospitalFrame.LoginForm;
import profile.User;

public class DoctorAppointment extends JFrame implements ActionListener, WindowListener{
    
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

	private static int index;

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		DoctorAppointment.index = index;
	}

    private static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private static ArrayList<User> users = new ArrayList<User>();
    
    public static void load_appointment_data(int index) {
    	File file = new File("src/database/appointment.txt");
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
                username = raw[0];
                role = raw[1];
                password = raw[2];
                name = raw[3];

                users.add(new User(username, role, password, name));
            }

        } catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

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

				if(doctorName.equals(users.get(index).getName())){
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
    
    public void load_table_appointment() {
		String[] column = {"ID", "Name", "Complaints", "Date", "Time", "Patient Name"};
		dtm_table_appointment = new DefaultTableModel(column, 0);
		
		for(Appointment app: appointments) {
			String id = app.getId();
			String name = app.getPatientName();
			String complaints = app.getComplaint();
			String date = app.getDate();
			String time = app.getTime();
			String doctorName = app.getDoctorName();
			
			Object[] row = {id, doctorName, complaints, date, time, name};
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

	public DoctorAppointment() {
        init_components();
        load_appointment_data(index);
        load_table_appointment();
		appointments.clear();
    }
    
    public static void main(String[] args) {
        new DoctorAppointment();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

        Object obj = e.getSource();
         
    }

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'windowClosing'");
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'windowClosed'");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'windowIconified'");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'windowDeiconified'");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'windowActivated'");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'windowDeactivated'");
	}
}