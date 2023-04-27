package register;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import profile.User;

public class RegisterForm extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	//Panel
	private JPanel panel_north = new JPanel();
	private JPanel panel_north_south = new JPanel();
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_frame = new JPanel();
    private JPanel panel_center_frame1 = new JPanel();
	private JPanel panel_space_center1 = new JPanel();
    private JPanel panel_space_center2 = new JPanel();
	
	//Textfield
	private JTextField txt_email = new JTextField();
	private JTextField txt_name = new JTextField();
	private JComboBox<String> combo_position = new JComboBox<String>();
	private JTextField txt_username = new JTextField();
	private JPasswordField txt_password = new JPasswordField();
	private JPasswordField txt_confirm_password = new JPasswordField();
	
	//Label
	private JLabel header_title = new JLabel("REGISTER FORM");
	private JLabel email = new JLabel("Email : ");
	private JLabel name = new JLabel("Name : ");
	private JLabel position = new JLabel("Position : ");
	private JLabel username = new JLabel("Username : ");
	private JLabel password = new JLabel("Password : ");
	private JLabel confirm_password = new JLabel("Confirm Password : ");
	
	//foooter
	private JPanel panel_south = new JPanel();
	private JButton btn_submit = new JButton("Submit");

	private static ArrayList<User> users = new ArrayList<User>();

	public void load_user_data(){
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
	}
	
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
		panel_center_frame.setLayout(new BorderLayout());
        panel_center_frame.add(panel_space_center1, "Center");
        add(panel_center_frame, BorderLayout.WEST);

        panel_center_frame1.setLayout(new BorderLayout());
        panel_center_frame1.add(panel_space_center2, "Center");
        add(panel_center_frame1, BorderLayout.EAST);
		panel_center.setLayout(new GridLayout(6, 2));
		
		panel_center.add(email);
		panel_center.add(txt_email);
		
		panel_center.add(name);
		panel_center.add(txt_name);
		
		panel_center.add(position);
		combo_position.addItem("Patient");
		combo_position.addItem("Doctor");
		combo_position.addItem("Pharmacist");
		combo_position.addItem("Receptionist");
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
		setSize(600,330);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public RegisterForm() {
		init_component();
		load_user_data();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RegisterForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String email = txt_email.getText();
		String name = txt_name.getText();
		String position = combo_position.getSelectedItem().toString();
		String username = txt_username.getText();
		String password = new String(txt_password.getPassword());
		String confirm_password = new String(txt_confirm_password.getPassword());

		boolean validate;
		//untuk .com
		String email_pattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
		//untuk .co.id
		String email_pattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"; 

		if(email.matches(email_pattern)){
			validate = true;
		}else if(email.matches(email_pattern2)){
			validate = true;
		}else{
			txt_email.setText("");
			validate = false;
			JOptionPane.showMessageDialog(null, "Email wrong!");
			return;
		}

		if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "Name field can not be empty!");
			validate = false;
			txt_name.setText("");
			return;
		}else {
			validate = true;
		}
		
		if(username.startsWith("patient_")) {
			validate = true;
		}else if(username.startsWith("doctor_")) {
			validate = true;
		}else if(username.startsWith("receptionist_")) {
			validate = true;
		}else if(username.startsWith("pharmacist_")) {
			validate = true;
		}else {
			txt_username.setText("");
			validate = false;
			JOptionPane.showMessageDialog(null, "Username must start with[position_]!");
			return;
		}    

		for(User u: users){
			if(username.equals(u.getUsername())){
				validate = false;
				JOptionPane.showMessageDialog(null, "Username was taken! Please change your username");
				txt_username.setText("");
				return;
			}
		}
		
		if(username.length()<8){
			txt_username.setText("");
			validate = false;
			JOptionPane.showMessageDialog(null, "Username must be more than 8 characters");
			return;
		}

		if(password.length()<8){
			txt_password.setText("");
			txt_confirm_password.setText("");
			validate = false;
			JOptionPane.showMessageDialog(null, "Password must be more than 8 characters");
			return;
		}

		if(confirm_password.equals(password) && validate == true){
			users.add(new User(username, position, password, name));

			File file = new File("src/database/user.txt");
            try{
                FileWriter writer = new FileWriter(file, true);
                writer.write(username+"#"+position+"#"+password+"#"+name);
                users.add(new User(username, position, password, name));
                writer.close();
            } catch (IOException a){
				System.out.println("File not found!");
			}

			JOptionPane.showMessageDialog(null, "User Registered Succcessfully!");
		} else{
			JOptionPane.showMessageDialog(null, "Password and Confirmed Password must match!");
			validate = false;
			txt_confirm_password.setText("");
			return;
		}
		
	}

}
