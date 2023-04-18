package hospitalFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener, WindowListener{
    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 25);
    private HospitalFrame hospitalFrame;
    private LoginForm loginForm;
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

    public LoginForm(HospitalFrame hospitalFrame){
        this.hospitalFrame = hospitalFrame;
        init_components();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 210);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Logic Login
        String username = txt_username.getText();
        if(e.getSource().equals(btn_submit)){
            dispose();
 
            String[] split = username.split("_", 2);
            print(split[0]);
            print(split[1]);

            while(true){
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
                } else{
                    JOptionPane.showMessageDialog(null, "Wrong input!");
                    loginForm = new LoginForm(hospitalFrame);
                    jdPane.add(loginForm);
                }
            }
        }

        Object obj = e.getSource();
        if(obj.equals(btn_submit)){
            JOptionPane.showMessageDialog(null, "Logged in as " +username+".");
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
