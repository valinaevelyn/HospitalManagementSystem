package hospitalFrame;

import appointment.AppointmentForm;
import register.RegisterForm;
import room.RoomForm;
import bill.BillForm;
import patient.PatientForm;
import patient.ViewPatient;
import doctor.RegisterDoctorForm;
import medicine.MedicineForm;
import medicine.ViewMedicine;
import hospitalFrame.Guide;
import medicine.ViewMedicine;
import room.ViewRoom;
import doctor.ViewDoctor;
import patient.BuyMedicineForm;
import appointment.ViewAppointment;
import appointment.CancelAppointment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class HospitalFrame extends JFrame implements ActionListener, WindowListener, MouseListener{

    private JMenuBar menuBar = new JMenuBar();

    // Landing Page
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuItemFileLogin = new JMenuItem("Login");
    private JMenuItem menuItemFileForm2 = new JMenuItem("Register");
    private JMenu menuOption = new JMenu("Guide");

    // Doctor Page
    private JMenu menuAppointment = new JMenu("View Appointment");
    private JMenu menuBill = new JMenu("Create Bill");

    // Pharmacist Page
    private JMenu menuMedicine1 = new JMenu("Add Medicine");
    private JMenu menuMedicine2 = new JMenu("View Medicine"); 
    
    // Patient Page
    private JMenu menuAppointmentPatient = new JMenu("Appointment");
    private JMenu menuMedicine = new JMenu("Buy Medicine");
    private JMenuItem menuAppointment1 = new JMenuItem("Make Appointment");
    private JMenuItem menuAppointment2 = new JMenuItem("View Appointment");
    private JMenuItem menuAppointment3 = new JMenuItem("Cancel Appointment");

    // Receptionist Frame
    private JMenu menuRegister = new JMenu("Register");
    private JMenu menuData = new JMenu("View Data");
    private JMenu menuPayment = new JMenu("Payment");
    private JMenuItem menuItemRegister1 = new JMenuItem("Patient");
    private JMenuItem menuItemRegister2 = new JMenuItem("Doctor");
    private JMenuItem menuItemRegister3 = new JMenuItem("Room");
    private JMenuItem menuItemView1 = new JMenuItem("Patient");
    private JMenuItem menuItemView2 = new JMenuItem("Doctor");
    private JMenuItem menuItemView3 = new JMenuItem("Room");
    
    // Log Out and Profile
    private JMenu menuLogOut = new JMenu("Log Out");
    private JMenu menuProfile = new JMenu("Profile");

    private LoginForm loginForm;
    private AppointmentForm makeAppointmentForm;
    private RegisterForm registerForm;
    private RoomForm roomForm;
    private BillForm billForm;
    private PatientForm patientForm;
    private RegisterDoctorForm registerDoctorForm;
    private MedicineForm medicineForm;
    private ViewMedicine viewMedicine;
    private Guide guide;
    private ViewDoctor viewDoctor;
    private ViewRoom viewRoom;
    private ViewPatient viewPatient; 
    private BuyMedicineForm buyMedicineForm;
    private ViewAppointment viewAppointment;
    private CancelAppointment cancelAppointment;
    private JDesktopPane jdPane = new JDesktopPane();

    public HospitalFrame(){
        // Landing Page
        menuBar.add(menuFile);
        menuFile.add(menuItemFileLogin);
        menuFile.add(new JSeparator());
        menuFile.add(menuItemFileForm2);
        add(jdPane);

        menuBar.add(menuOption);
        menuItemFileLogin.addActionListener(this);
        menuItemFileForm2.addActionListener(this);
        menuOption.addMouseListener(this);
        

        // Doctor Page
        menuBar.add(menuAppointment);
        menuAppointment.setVisible(false);
        menuBar.add(menuBill);
        menuBill.setVisible(false);
        
        add(jdPane);

        menuBill.addMouseListener(this);

        // Pharmacist Page
        menuBar.add(menuMedicine1);
        menuMedicine1.setVisible(false);
        menuBar.add(menuMedicine2);
        menuMedicine2.setVisible(false);
        add(jdPane);

        menuMedicine1.addMouseListener(this);
        menuMedicine2.addMouseListener(this);

        // Patient Page
        menuBar.add(menuAppointmentPatient);
        menuAppointmentPatient.add(menuAppointment1);
        menuAppointmentPatient.add(new JSeparator());
        menuAppointmentPatient.add(menuAppointment2);
        menuAppointmentPatient.add(new JSeparator());
        menuAppointmentPatient.add(menuAppointment3);
        menuAppointmentPatient.add(new JSeparator());
        menuAppointmentPatient.setVisible(false);
        add(jdPane);

        menuBar.add(menuMedicine);
        menuMedicine.setVisible(false);
        add(jdPane);
        menuAppointment1.addActionListener(this);
        menuMedicine.addMouseListener(this);
        menuAppointment2.addActionListener(this);
        menuAppointment3.addActionListener(this);
        
        // Receptionist Frame
        menuBar.add(menuRegister);
        menuRegister.add(menuItemRegister1);
        menuRegister.add(new JSeparator());
        menuRegister.add(menuItemRegister2);
        menuRegister.add(new JSeparator());
        menuRegister.add(menuItemRegister3);
        menuRegister.setVisible(false);
        add(jdPane);

        menuBar.add(menuData);
        menuData.add(menuItemView1);
        menuData.add(new JSeparator());
        menuData.add(menuItemView2);
        menuData.add(new JSeparator());
        menuData.add(menuItemView3);
        menuData.setVisible(false);
        add(jdPane);

        menuBar.add(menuPayment);
        menuPayment.setVisible(false);
        add(jdPane);

        menuItemRegister1.addActionListener(this);
        menuItemRegister2.addActionListener(this);
        menuItemRegister3.addActionListener(this);
        menuItemView1.addActionListener(this);
        menuItemView2.addActionListener(this);
        menuItemView3.addActionListener(this);
        
        // Log Out Profile
        menuBar.add(menuLogOut);
        menuLogOut.setVisible(false);
        menuBar.add(menuProfile);
        menuProfile.setVisible(false);
        
        setJMenuBar(menuBar);
        setTitle("Hospital Management System");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HospitalFrame();
    }

    public void doReceptionist(){
        menuRegister.setVisible(true);
        menuData.setVisible(true);
        menuPayment.setVisible(true);
        menuLogOut.setVisible(true);
        menuProfile.setVisible(true);
    }

    public void doPharmacist(){
        menuMedicine2.setVisible(true);
        menuMedicine1.setVisible(true);
        menuLogOut.setVisible(true);
        menuProfile.setVisible(true);
    }

    public void doPatient(){
        menuAppointmentPatient.setVisible(true);
        menuMedicine.setVisible(true);
        menuLogOut.setVisible(true);
        menuProfile.setVisible(true);
    }

    public void doDoctor(){
        menuAppointment.setVisible(true);
        menuBill.setVisible(true);
        menuLogOut.setVisible(true);
        menuProfile.setVisible(true);
    }

    public void doLandingPage(){
        menuFile.setVisible(false);
        menuOption.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(menuItemFileLogin)){
            loginForm = new LoginForm(this); 
            jdPane.add(loginForm);
            dispose();
        }

        if(e.getSource().equals(menuItemFileForm2)){
            registerForm = new RegisterForm(); 
            jdPane.add(registerForm);
            dispose();
        }

        if(e.getSource().equals(menuAppointment)){
            
        }

        if(e.getSource().equals(menuAppointment1)){
            makeAppointmentForm = new AppointmentForm(); 
            jdPane.add(makeAppointmentForm);
            dispose();
        }
        
        if(e.getSource().equals(menuAppointment2)){
            viewAppointment = new ViewAppointment(); 
            jdPane.add(viewAppointment);
            dispose();
        }
        
        if(e.getSource().equals(menuAppointment3)){
            cancelAppointment = new CancelAppointment(); 
            jdPane.add(cancelAppointment);
            dispose();
        }

        if(e.getSource().equals(menuMedicine)){
            
        }

        if(e.getSource().equals(menuItemRegister1)){
            patientForm = new PatientForm();
            jdPane.add(patientForm);
            dispose();
        }

        if(e.getSource().equals(menuItemRegister2)){
            registerDoctorForm = new RegisterDoctorForm();
            jdPane.add(registerDoctorForm);
            dispose();
        }

        if(e.getSource().equals(menuItemRegister3)){
            roomForm = new RoomForm();
            jdPane.add(roomForm);
            dispose();
        }

        if(e.getSource().equals(menuItemView1)){
            viewPatient = new ViewPatient();
            jdPane.add(viewPatient);
            dispose();
        }
        
        if(e.getSource().equals(menuItemView2)){
            viewDoctor = new ViewDoctor();
            jdPane.add(viewDoctor);
            dispose();
        }

        if(e.getSource().equals(menuItemView3)){
            viewRoom = new ViewRoom();
            jdPane.add(viewRoom);
            dispose();
        }
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
        // new HospitalFrame().setVisible(true);
        new HospitalFrame().show();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(menuBill)){
            billForm = new BillForm(); 
            jdPane.add(billForm);
            dispose();
        }
        
        if(e.getSource().equals(menuMedicine1)){
            medicineForm = new MedicineForm(); 
            jdPane.add(medicineForm);
            dispose();
        }
        
        if(e.getSource().equals(menuMedicine2)){
            viewMedicine = new ViewMedicine(); 
            jdPane.add(viewMedicine);
            dispose();
        }
        
        if(e.getSource().equals(menuOption)){
            guide = new Guide(); 
            jdPane.add(guide);
            dispose();
        }
        
        if(e.getSource().equals(menuMedicine)){
            buyMedicineForm = new BuyMedicineForm(); 
            jdPane.add(buyMedicineForm);
            dispose();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}