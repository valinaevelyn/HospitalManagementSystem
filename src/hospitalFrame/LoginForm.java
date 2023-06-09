package hospitalFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import profile.User;
import profile.Account;
import appointment.DoctorAppointment;
import appointment.ViewAppointment;
import patient.BuyMedicineForm;
import appointment.CancelAppointment;
import appointment.AppointmentForm;

public class LoginForm extends JFrame implements ActionListener, WindowListener{
    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 25);
    private HospitalFrame hospitalFrame;
    private LoginForm loginForm;
    private Account account;
    private ViewAppointment viewAppointment;
    private BuyMedicineForm buyMedicineForm;
    private DoctorAppointment doctorAppointment;
    private CancelAppointment cancelAppointment;
    private AppointmentForm appointmentForm;
    private JDesktopPane jdPane = new JDesktopPane();

    // Header
    private JPanel panel_north = new JPanel();
    private JLabel lbl_login = new JLabel("LOGIN");
    private JPanel panel_space_north = new JPanel();
    private JPanel panel_space_north1 = new JPanel();
    private JPanel panel_space_north2 = new JPanel();
    private JPanel panel_space_north3 = new JPanel();
    private JPanel panel_space_north4 = new JPanel();
    
    // Component
    private JPanel panel_center = new JPanel();
    private JPanel panel_center_frame = new JPanel();
    private JPanel panel_center_frame1 = new JPanel();
    private JPanel panel_space_center1 = new JPanel();
    private JPanel panel_space_center2 = new JPanel();
    private JTextField txt_username = new JTextField();
    private JPasswordField txt_password = new JPasswordField();

    // Footer
    private JPanel panel_south = new JPanel();
    private JPanel panel_south_frame = new JPanel();
    private JPanel panel_space_south1 = new JPanel();
    private JPanel panel_space_south2 = new JPanel();
    private JButton btn_submit = new JButton("Login");

    private ArrayList<User> users = new ArrayList<User>();
    private static int index;

    void load_user_data(){
        File file = new File("src/database/user.txt");
        try{
            Scanner scan = new Scanner(file);
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

    void init_components(){
        setLayout(new BorderLayout());

        // Header
        panel_north.setLayout(new BorderLayout());
        panel_space_north.setLayout(new BorderLayout());
        panel_space_north.add(panel_space_north3, "Center");
        panel_space_north.add(panel_space_north4, "South");
        add(panel_space_north, BorderLayout.NORTH);

        lbl_login.setFont(font_title);
        panel_north.add(lbl_login, "North");
        lbl_login.setHorizontalAlignment(JLabel.CENTER);
        panel_north.add(panel_space_north1, "Center");
        panel_north.add(panel_space_north2, "South");
        add(panel_north, BorderLayout.NORTH);
        
        // Content
        panel_center_frame.setLayout(new BorderLayout());
        panel_center_frame.add(panel_space_center1, "Center");
        add(panel_center_frame, BorderLayout.WEST);

        panel_center_frame1.setLayout(new BorderLayout());
        panel_center_frame1.add(panel_space_center2, "Center");
        add(panel_center_frame1, BorderLayout.EAST);

        panel_center.setLayout(new GridLayout(2, 2));

        // Row 1
        panel_center.add(new JLabel("Username"));
        panel_center.add(txt_username);
        add(panel_center);

        // Row 2
        panel_center.add(new JLabel("Password"));
        panel_center.add(txt_password);
        add(panel_center);

        // Footer
        panel_south_frame.setLayout(new BorderLayout());
        panel_south_frame.add(panel_space_south1, "North");
        panel_south_frame.add(panel_space_south2, "Center");
        
        panel_south.setLayout(new FlowLayout());
        btn_submit.addActionListener(this);
        panel_south.add(btn_submit);
        panel_south_frame.add(panel_south, "South");
        add(panel_south_frame, "South");
    }

    // Constructor
    public LoginForm(HospitalFrame hospitalFrame){
        this.hospitalFrame = hospitalFrame;
        load_user_data();
        init_components();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 210);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public int findUser(String username){
        int i = 0;
        for(User u: users){
            if(u.getUsername().equals(username)) return i;
            else i++;
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Logic Login
        String username = txt_username.getText();
        String password = new String(txt_password.getPassword());

        if(e.getSource().equals(btn_submit)){
            dispose();

            index = findUser(username);
            String[] split = username.split("_", 2);

            while(true){
                if(index != -1 && users.get(index).getPassword().equals(password)){

                    if(split[0].equals("patient")){
                        hospitalFrame.doPatient();
                        hospitalFrame.doLandingPage();
                        setTitle("Patient");
                        break;
                    } else if(split[0].equals("receptionist")){
                        hospitalFrame.doReceptionist();
                        hospitalFrame.doLandingPage();
                        setTitle("Receptionist");
                        break;
                    } else if(split[0].equals("doctor")){
                        hospitalFrame.doDoctor();
                        hospitalFrame.doLandingPage();
                        setTitle("Doctor");
                        break;
                    } else if(split[0].equals("pharmacist")){
                        hospitalFrame.doPharmacist();
                        hospitalFrame.doLandingPage();
                        setTitle("Pharmacist");
                        break;
                    } 
                } else{
                    JOptionPane.showMessageDialog(null, "Wrong input!");
                    loginForm = new LoginForm(hospitalFrame);
                    jdPane.add(loginForm);
                }
            }
        }
        
        account.setIndex(index);
        viewAppointment.setIndex(index);
        doctorAppointment.setIndex(index);
        cancelAppointment.setIndex(index);
        appointmentForm.setIndex(index);
        buyMedicineForm.setIndex(index);

        Object obj = e.getSource();
        if(obj.equals(btn_submit)){
//            progressBar = new ProgressBar();
//            jpb.add(progressBar);
            JOptionPane.showMessageDialog(null, "Logged in as " +username+".");
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowOpened'");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'windowClosing'");
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
