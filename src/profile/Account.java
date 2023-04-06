package profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Account extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_title = new JLabel("ACCOUNT PROFILE");
	
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	private JPanel panel_center = new JPanel();
	private JPanel panel_center_kiri = new JPanel();
	private JPanel panel_center_kanan = new JPanel();
	
	private JLabel lbl_name = new JLabel("    Name");
	private JLabel lbl_isi_name = new JLabel();
	private JLabel lbl_username = new JLabel("    Username");
	private JLabel lbl_isi_username = new JLabel();
	private JLabel lbl_email = new JLabel("    Email");
	private JLabel lbl_isi_email = new JLabel();
	private JLabel lbl_role = new JLabel("    Role");
	private JLabel lbl_isi_role = new JLabel("    Patient");
	
	private JPanel panel_south = new JPanel();
	
	public void initComponent() {
		setLayout(new BorderLayout());
		
		panel_north.setLayout(new FlowLayout());
		panel_north.add(lbl_title);
		lbl_title.setFont(font_title);
		add(panel_north, "North");
		
		panel_center.setLayout(new GridLayout(1, 2));
		ImageIcon profile = new ImageIcon(getClass().getResource("ten.jpg"));
		Image image = profile.getImage();
		Image newImage = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon newProfile = new ImageIcon(newImage);
		JLabel label_profile = new JLabel(newProfile, JLabel.CENTER);
		panel_center_kiri.add(label_profile);
		panel_center.add(panel_center_kiri);
		
		add(panel_left, "West");
		add(panel_right, "East");
		
		panel_center_kanan.setLayout(new GridLayout(4, 2));
		panel_center_kanan.add(lbl_name);
		panel_center_kanan.add(lbl_isi_name);
		lbl_isi_name.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_center_kanan.add(lbl_email);
		panel_center_kanan.add(lbl_isi_email);
		lbl_isi_email.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_center_kanan.add(lbl_username);
		panel_center_kanan.add(lbl_isi_username);
		lbl_isi_username.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_center_kanan.add(lbl_role);
		panel_center_kanan.add(lbl_isi_role);
		lbl_isi_role.setFont(new Font("Arial", Font.PLAIN, 12));
		
//		panel_center_kanan.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panel_center.add(panel_center_kanan);
		
		add(panel_center, "Center");
		
		
		
		add(panel_south, "South");
		
		setVisible(true);
		setSize(700, 300);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public Account() {
		initComponent();
	}

	public static void main(String[] args) {
		new Account();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
