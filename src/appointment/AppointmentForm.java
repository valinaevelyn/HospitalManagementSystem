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
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
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
import profile.User;

public class AppointmentForm extends JFrame implements ActionListener{	
    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    private JDesktopPane jdPane = new JDesktopPane();

    // Header
    private JPanel panel_north = new JPanel();
    private JLabel lbl_makeAppointment = new JLabel("MAKE APPOINTMENT");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_space_north2 = new JPanel();
	private JPanel panel_north_table = new JPanel();
	
    // Component
    private JPanel panel_left = new JPanel();
    private JPanel panel_right = new JPanel();
    
    private JTable table_appointment;
    private JScrollPane scrollpane_table_appointment;
    private DefaultTableModel dtm_table_appointment;
    
    private JPanel panel_center = new JPanel();
    private JPanel panel_center_frame = new JPanel();
    private JPanel panel_center_frame1 = new JPanel();
    private JPanel panel_space_center1 = new JPanel();
    private JPanel panel_space_center2 = new JPanel();
    private JTextField txt_id = new JTextField();
    private JTextField txt_name = new JTextField();
    private JTextArea txt_complaint = new JTextArea();
    private JTextField txt_date = new JTextField();
    private JTextField txt_time = new JTextField();
    private JTextField txt_doctorName = new JTextField();

    // Footer 
    private JPanel panel_south = new JPanel();
    private JPanel panel_south_frame = new JPanel();
    private JPanel panel_space_south1 = new JPanel();
    private JPanel panel_space_south2 = new JPanel();
    private JButton btn_save = new JButton("SAVE");
    private JButton btn_clear = new JButton("CLEAR TEXT");
    private JButton btn_exit = new JButton("EXIT");

    // Date & Time
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

    private static int index;

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		AppointmentForm.index = index;
	}

    private static ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    
    public void load_appointment_data() {
        File fileUser = new File("src/database/user.txt");
        File fileDoctor = new File("src/database/datadoctor.txt");
    	File file = new File("src/database/appointment.txt");

        try {
			Scanner scan = new Scanner(fileDoctor);
			String[] raw;
			String id;
			String name;
			int age;
			String address;
			String phoneNumber;
			String gender;
			String specialization;
			//while not end of file
			while(scan.hasNextLine()) {
				raw = scan.nextLine().split("#");
				id = raw[0];
				name = raw[1];
				age = Integer.parseInt(raw[2]);
				address = raw[3];
				phoneNumber = raw[4];
				gender = raw[5];
				specialization = raw[6];
				
				doctors.add(new Doctor(id, name, age, address, phoneNumber, gender, specialization));
			}
			
			// for(Doctor doctor : doctors) {
			// 	System.out.println(doctor.getName());
			// }
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
        
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
    
    public void init_components(){
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
        
        // Content
        panel_center.setLayout(new GridLayout(6, 2));

        // Row 1 
        panel_center.add(new JLabel("ID (APXXX)"));
        panel_center.add(txt_id);
        add(panel_center);

        // Row 2
        panel_center.add(new JLabel("Name"));
        panel_center.add(txt_name);
        add(panel_center);

        // Row 3
        panel_center.add(new JLabel("Complaint"));
        panel_center.add(txt_complaint);
        add(panel_center);

        // Row 4
        panel_center.add(new JLabel("Date (dd MMMM yyyy)"));
        panel_center.add(txt_date);
        add(panel_center);

        // Row 5
        panel_center.add(new JLabel("Time (hh:mm)"));
        panel_center.add(txt_time);
        add(panel_center);

        // Row 6 
        panel_center.add(new JLabel("Doctor Name"));
        panel_center.add(txt_doctorName);
        add(panel_center);

        // Footer
        panel_south_frame.setLayout(new BorderLayout());
        panel_south_frame.add(panel_space_south1, "North");
        panel_south_frame.add(panel_space_south2, "Center");

        panel_south.setLayout(new FlowLayout());
        btn_save.addActionListener(this);
        panel_south.add(btn_save);

        btn_clear.addActionListener(this);
        panel_south.add(btn_clear);
        add(panel_south, "South");
        
        btn_exit.addActionListener(this);
        panel_south.add(btn_exit);
        add(panel_south, "South");

        panel_south_frame.add(panel_south, "South");
        add(panel_south_frame, "South");

        setTitle("Make Appointment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 690);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public AppointmentForm() {
        init_components();
        load_appointment_data();
        load_table_appointment();
        appointments.clear();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btn_save)) {
        	// progressBar = new ProgressBar();
            Date dateChecker = null, timeChecker = null;

            int check = 1;
            String id = txt_id.getText();
            String name = txt_name.getText();
            String complaint = txt_complaint.getText();
            String date = txt_date.getText();
            String time = txt_time.getText();
            String doctorName = txt_doctorName.getText();

            // validation
            String id_validation = "A+P+[0-9]+[0-9]+[0-9]";
            if(id.matches(id_validation)) check *= 1;
            else{
                JOptionPane.showMessageDialog(null, "ID format must be ''APXXX'' (XXX = 3 digit numbers)");
                check *= 0;
                return;
            } 
            
            if(id.length() == 5) check *= 1;
            else{
                JOptionPane.showMessageDialog(null, "ID format must be ''APXXX'' (XXX = 3 digit numbers)");
                check *= 0;
                return;
            } 

            File filetest = new File("src/database/appointment.txt");
            try {
                Scanner scan = new Scanner(filetest);
                String[] raw;
                String testid;
                String testname;
                String testcomplaint;
                String testdate;
                String testtime;
                String testdoctorName;
                
                while(scan.hasNextLine()) {
                    raw = scan.nextLine().split("#");
                    testid = raw[0];
                    testname = raw[1];
                    testcomplaint = raw[2];
                    testdate = raw[3];
                    testtime = raw[4];
                    testdoctorName = raw[5];
    
                    appointments.add(new Appointment(testid, testname, testcomplaint, testdate, testtime, testdoctorName));
                }
    
    //            for(Appointment appointment : appointments){
    //                System.out.println(appointment.getPatient());
    //            }
                
            } catch (FileNotFoundException ee) {
                JOptionPane.showMessageDialog(null, ee.getMessage());
            }
            
            for(Appointment a: appointments){
                if(id.matches(a.getId())){
                    txt_id.setText("");
                    check *= 0;
                    JOptionPane.showMessageDialog(null, "ID was taken, try another id!");
                    return;
                } else{
                    check *= 1;
                }
            }

            if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "Name field can not be empty!");
				check *=0;
                txt_name.setText("");
                return;
			}else {
                check *=1;
			}
            
            if(name.equals(users.get(index).getName())) {
                check *=1;
			}else {
                JOptionPane.showMessageDialog(null, "Name must be filled with your name!");
                txt_name.setText("");
                check *=0;
                return;
			}

            if(complaint.equals("")) {
				JOptionPane.showMessageDialog(null, "Complaint field can not be empty!");
				check *=0;
                txt_complaint.setText("");
                return;
			}else {
				check *=1;
			}

            if(date.equals("")) {
				JOptionPane.showMessageDialog(null, "Date field can not be empty!");
				check *=0;
                txt_date.setText("");
                return;
			}else {
                check *=1;
			}

            dateFormat.setLenient(false);
            try {
                Date dateChecker2 = dateFormat.parse(date);
                check *= 1;
            } catch (ParseException ee) {
                JOptionPane.showMessageDialog(null, "Invalid date format!");
                check *= 0;
                txt_date.setText("");
                return;
            }
            
            try {
                dateChecker = dateFormat.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateChecker);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
            
                if (year < 1900 || year > 2100 || month < 0 || month > 11 || day < 1 || day > 31) {
                    JOptionPane.showMessageDialog(null, "Invalid date, please use dd MMMM yyyy format. (ex : 1 April 1900)");
                    check *= 0;
                    txt_date.setText("");
                    return;
                }
            
                check *= 1;
            } catch (ParseException ee) {
                JOptionPane.showMessageDialog(null, "Invalid date, please use dd MMMM yyyy format. (ex : 1 April 1900)");
                check *= 0;
                txt_date.setText("");
                return;
            }

            Date userInputDate = null;
            try {
                userInputDate = dateFormat.parse(date);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if (userInputDate != null) {
                Date currentDate = new Date();

                if (userInputDate.before(currentDate)) {
                    // System.out.println("The date is before today.");
                    JOptionPane.showMessageDialog(null, "Date has to be after today!");
                    check *= 0;
                    txt_date.setText("");
                    return;
                } else {
                    System.out.println("The date is on or after today.");
                    check *= 1;
                }
            } else {
                System.out.println("Invalid date input");
                JOptionPane.showMessageDialog(null, "Date has to be after today!");
                txt_date.setText("");
                check *= 0;
                return;
            }

            if(time.equals("")) {
                JOptionPane.showMessageDialog(null, "Time field can not be empty!");
                txt_time.setText("");
				check *=0;
                return;
			}else {
                check *=1;
			}
            
            String timePattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
            if (time.matches(timePattern)) {
                check *= 1;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Time Format, should be HH:MM (ex: 09:00)!");
                txt_time.setText("");
                check *=0;
                return;
            }
            
            if(doctorName.equals("")) {
                JOptionPane.showMessageDialog(null, "Doctor Name field can not be empty!");
                txt_doctorName.setText("");
				check *=0;
                return;
			}else {
                check *=1;
			}
            
            for(Doctor d: doctors){
                if(id.matches(d.getName())){
                    check *= 0;
                    txt_doctorName.setText("");
                    JOptionPane.showMessageDialog(null, "Doctor name not found. Please refer to datadoctor.txt to find a valid doctor!");
                    return;
                } else{
                    check *= 1;
                }
            }

            // Store data di tabel
            Object[] row = {id, name, complaint, date, time, doctorName};
            if(check == 1){
                dtm_table_appointment.addRow(row);
                appointments.add(new Appointment(id, name, complaint, date, time, doctorName));
                table_appointment.invalidate();
            }

            File file = new File("src/database/appointment.txt");
            try{
                FileWriter writer = new FileWriter(file, true);
                writer.write(id+"#"+name+"#"+complaint+"#"+date+"#"+time+"#"+doctorName+"\n");
                appointments.add(new Appointment(id, name, complaint, date, time, doctorName));
                writer.close();
            } catch (IOException a){
				System.out.println("File not found!");
			}

            if(check == 1){
                txt_id.setText("");
                txt_name.setText("");
                txt_complaint.setText("");
                txt_date.setText("");
                txt_time.setText("");
                txt_doctorName.setText("");
            }

            JOptionPane.showMessageDialog(null, "Appointment has been created successfully!");
        } else if(e.getSource().equals(btn_clear)) {
			txt_id.setText("");
            txt_name.setText("");
            txt_complaint.setText("");
            txt_date.setText("");
            txt_time.setText("");
            txt_doctorName.setText("");
		} else if(e.getSource().equals(btn_exit)){
            dispose();
        }
    }
}