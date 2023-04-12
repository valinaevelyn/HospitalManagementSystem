package profile;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Account extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 24);
	
	private JPanel panel_north = new JPanel();
	private JPanel panel_north_frame = new JPanel();
	private JPanel panel_north_space = new JPanel();
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
	private JPanel panel_south_frame = new JPanel();
	private JPanel panel_south_btn = new JPanel();
	private JPanel panel_south_space = new JPanel();
	private JButton btn_back = new JButton("Back");
	
	private BufferedImage roundImage(Image image) {
	    int diameter = Math.min(image.getWidth(null), image.getHeight(null));
	    BufferedImage mask = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = mask.createGraphics();
	    g2d.fillOval(0, 0, diameter, diameter);
	    g2d.dispose();
	    
	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    int x = (diameter - image.getWidth(null)) / 2;
	    int y = (diameter - image.getHeight(null)) / 2;
	    g2d.drawImage(image, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();
	    
	    return masked;
	}
	
	public void initComponent() {
		setLayout(new BorderLayout());
		
		panel_north_frame.setLayout(new BorderLayout());
		panel_north.setLayout(new FlowLayout());
		panel_north.add(lbl_title);
		lbl_title.setFont(font_title);
		panel_north_frame.add(panel_north, "Center");
		panel_north_frame.add(panel_north_space, "South");
		add(panel_north_frame, "North");
		
		panel_center.setLayout(new GridLayout(1, 2));
		ImageIcon profile = new ImageIcon(getClass().getResource("ten.jpg"));
		Image image = profile.getImage();
		Image roundedImage = roundImage(image);
		ImageIcon newProfile = new ImageIcon(roundedImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
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
		
		
		panel_south.setLayout(new BorderLayout());
		
		panel_south.add(panel_south_frame, "North");
		
		panel_south_btn.setLayout(new FlowLayout());
		panel_south_btn.add(btn_back);
		panel_south.add(panel_south_btn, "Center");
		
		panel_south.add(panel_south_space, "South"); 
		
		add(panel_south, "South");
		
		setVisible(true);
		setSize(550, 350);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
