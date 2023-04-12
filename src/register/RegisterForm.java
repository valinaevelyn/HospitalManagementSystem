package register;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	//Panel
	private JPanel panel_north = new JPanel();
	private JPanel panel_north_south = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_right = new JPanel();
	private JPanel panel_center_left = new JPanel();
	
	//Textfield
	private JTextField txt_email = new JTextField();
	private JComboBox<String> combo_position = new JComboBox<String>();
	private JTextField txt_username = new JTextField();
	private JPasswordField txt_password = new JPasswordField();
	private JPasswordField txt_confirm_password = new JPasswordField();
	
	//Label
	private JLabel header_title = new JLabel("REGISTER FORM");
	private JLabel email = new JLabel("Email : ");
	private JLabel position = new JLabel("Position : ");
	private JLabel username = new JLabel("Username : ");
	private JLabel password = new JLabel("Password : ");
	private JLabel confirm_password = new JLabel("Confirm Password : ");
	
	//foooter
	private JPanel panel_south = new JPanel();
	private JButton btn_submit = new JButton("Submit");
	
	public void init_component() {
		setLayout(new BorderLayout());
		//Header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(header_title, "North");
//		panel_north.setBackground(Color.RED);
		header_title.setFont(font_title);
		header_title.setHorizontalAlignment(JLabel.CENTER);
		
		panel_north.add(panel_north_south);
		add(panel_north, "North");
		
		//content
		panel_center.setLayout(new GridLayout(5, 2));
		
		panel_center.add(email);
		panel_center.add(txt_email);
		
		panel_center.add(position);
		combo_position.addItem("Patient");
		combo_position.addItem("Doctor");
		combo_position.addItem("Pharmacist");
		combo_position.addItem("Admin");
		panel_center.add(combo_position);
		
		panel_center.add(username);
		panel_center.add(txt_username);
		
		panel_center.add(password);
		panel_center.add(txt_password);
		
		panel_center.add(confirm_password);
		panel_center.add(txt_confirm_password);
		
		add(panel_center, "Center");
		
		//Footer				
		panel_south.setLayout(new FlowLayout());
		panel_south.add(btn_submit);
		btn_submit.addActionListener(this);
		
		add(panel_south, "South");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 250);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public RegisterForm() {
		init_component();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RegisterForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String email = txt_email.getText();
		String position = combo_position.getSelectedItem().toString();
		String username = txt_username.getText();
		String password = txt_password.getText();
		String confirm_password = txt_confirm_password.getText();
		
	}

}
