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
import javax.swing.JTextField;

public class GeneratePrescription extends JFrame implements ActionListener{

    private Font font_title = new Font(Font.SANS_SERIF, Font.BOLD, 24);

    // Header
    private JPanel panel_north = new JPanel();
    private JLabel lbl_prescription = new JLabel("GENERATE PRESCRIPTION");

    // Component
    private JPanel panel_center = new JPanel();
    private JPanel panel_center_frame = new JPanel();
    private JPanel panel_center_frame1 = new JPanel();
    private JPanel panel_space_center1 = new JPanel();
    private JPanel panel_space_center2 = new JPanel();
    private JTextField txt_id = new JTextField();
    private JTextField txt_name = new JTextField();
    private JTextField txt_diagnosis = new JTextField();
    private JTextField txt_medication = new JTextField();
    private JTextField txt_price = new JTextField();
    
    // Footer
    private JPanel panel_south = new JPanel();
    private JButton btn_submit = new JButton("OK");

    void init_components(){
        setLayout(new BorderLayout());

        // Header
        panel_north.setLayout(new FlowLayout());
        lbl_prescription.setFont(font_title);
        panel_north.add(lbl_prescription);
        add(panel_north, "North");

        // Content
        panel_center_frame.setLayout(new BorderLayout());
        panel_center_frame.add(panel_space_center1, "Center");
        add(panel_center_frame, BorderLayout.WEST);

        panel_center_frame1.setLayout(new BorderLayout());
        panel_center_frame1.add(panel_space_center2, "Center");
        add(panel_center_frame1, BorderLayout.EAST);

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
        panel_center.add(new JLabel("Diagnosis"));
        panel_center.add(txt_diagnosis);
        add(panel_center);

        // Row 4
        panel_center.add(new JLabel("Medication"));
        panel_center.add(txt_medication);
        add(panel_center);

        // Row 5
        panel_center.add(new JLabel("Price (in $)"));
        panel_center.add(txt_price);
        add(panel_center);

        // Footer 
        panel_south.setLayout(new FlowLayout());
        btn_submit.addActionListener(this);
        panel_south.add(btn_submit);
        add(panel_south, "South");

        setTitle("Generate Prescription");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public GeneratePrescription() {
        init_components();
    }

    public static void main(String[] args) {
        new GeneratePrescription();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");

        Object obj = e.getSource();

        if(obj.equals(btn_submit)){
            String name = txt_name.getName();
            JOptionPane.showMessageDialog(null, "Prescription has been generated for "+name);
        }
    }

}
