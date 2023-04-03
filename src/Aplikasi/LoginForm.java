import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener{
    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 25);
    
    // Header
    private JPanel panel_north = new JPanel();
    private JLabel lbl_login = new JLabel("LOGIN");
    private JPanel panel_space_north1 = new JPanel();
    private JPanel panel_space_north2 = new JPanel();
    
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
    private JButton btn_submit = new JButton("SUBMIT");

    void init_components(){
        setLayout(new BorderLayout());

        // Header
        panel_north.setLayout(new BorderLayout());
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
        
        setTitle("Login Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 210);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public LoginForm(){
        init_components();
    }

    public static void main(String[] args) {
        new LoginForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj.equals(btn_submit)){
            String username = txt_username.getText();
            JOptionPane.showMessageDialog(null, "Logged in as " +username);
        }
    }
}