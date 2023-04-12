package hospitalFrame;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class HospitalFrame extends JFrame implements ActionListener, WindowListener{

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

    private LoginForm loginForm;
    private MakeAppointmentForm makeAppointmentForm;
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

        // Doctor Page
        menuBar.add(menuAppointment);
        menuAppointment.setVisible(false);
        menuBar.add(menuBill);
        menuBill.setVisible(false);
        add(jdPane);

        // Pharmacist Page
        menuBar.add(menuMedicine1);
        menuMedicine1.setVisible(false);
        menuBar.add(menuMedicine2);
        menuMedicine2.setVisible(false);
        add(jdPane);

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
    }

    public void doPharmacist(){
        menuMedicine2.setVisible(true);
        menuMedicine1.setVisible(true);
    }

    public void doPatient(){
        menuAppointmentPatient.setVisible(true);
        menuMedicine.setVisible(true);
    }

    public void doDoctor(){
        menuAppointment.setVisible(true);
        menuBill.setVisible(true);
    }

    public void doLandingPage(){
        menuFile.setVisible(false);
        menuOption.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(menuItemFileLogin)){
            if (loginForm == null ) {
                loginForm = new LoginForm(this); 
                jdPane.add(loginForm);
                dispose();
            }
        }

        if(e.getSource().equals(menuItemFileForm2)){
            
        }

        if(e.getSource().equals(menuOption)){
            
        }

        if(e.getSource().equals(menuAppointment)){
            
        }

        if(e.getSource().equals(menuBill)){
            
        }

        if(e.getSource().equals(menuMedicine1)){
            
        }

        if(e.getSource().equals(menuMedicine2)){
            
        }

        if(e.getSource().equals(menuAppointment1)){
            if (makeAppointmentForm == null) {
                makeAppointmentForm = new MakeAppointmentForm(this); 
                jdPane.add(makeAppointmentForm);
            }
        }

        if(e.getSource().equals(menuAppointment2)){
            
        }

        if(e.getSource().equals(menuAppointment3)){
            
        }

        if(e.getSource().equals(menuMedicine)){
            
        }

        if(e.getSource().equals(menuItemRegister1)){
            
        }

        if(e.getSource().equals(menuItemRegister2)){
            
        }

        if(e.getSource().equals(menuItemRegister3)){
            
        }

        if(e.getSource().equals(menuItemView1)){
            
        }

        if(e.getSource().equals(menuItemView2)){
            
        }

        if(e.getSource().equals(menuItemView3)){
            
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
        new HospitalFrame().setVisible(true);
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
