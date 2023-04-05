package appointment;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AppointmentForm extends JFrame implements ActionListener{
    
    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    // Header
    private JPanel panel_north = new JPanel();
    private JLabel lbl_makeAppointment = new JLabel("MAKE APPOINTMENT");

    // Component
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
    private JButton btn_cancel = new JButton("CANCEL");

    void init_components(){
        setLayout(new BorderLayout());

        // Header
        panel_south.setLayout(new BorderLayout());
        lbl_makeAppointment.setFont(font_title);
        panel_north.add(lbl_makeAppointment, "North");
        add(panel_north, BorderLayout.NORTH);

        panel_center_frame1.setLayout(new BorderLayout());
        panel_center_frame1.add(panel_space_center2);
        add(panel_center_frame1, BorderLayout.EAST);
        
        panel_center_frame.setLayout(new BorderLayout());
        panel_center_frame.add(panel_space_center1);
        add(panel_center_frame, BorderLayout.WEST);

        // Content
        panel_center.setLayout(new GridLayout(6, 2));

        // Row 1 
        panel_center.add(new JLabel("ID"));
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
        panel_center.add(new JLabel("Date"));
        panel_center.add(txt_date);
        add(panel_center);

        // Row 5
        panel_center.add(new JLabel("Time"));
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

        btn_cancel.addActionListener(this);
        panel_south.add(btn_cancel);
        add(panel_south, "South");

        panel_south_frame.add(panel_south, "South");
        add(panel_south_frame, "South");

        setTitle("Make Appointment");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 320);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public AppointmentForm() {
        init_components();
    }
    
    public static void main(String[] args) {
        new AppointmentForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

        Object obj = e.getSource();
        if(obj.equals(btn_save)) JOptionPane.showMessageDialog(null, "Appointment Created Successfully!");
        
    }

}