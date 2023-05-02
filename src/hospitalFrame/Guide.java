package hospitalFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Guide extends JFrame implements ActionListener{
	private Font font_title = new Font(Font.SERIF, Font.BOLD, 22);
	
	private JPanel panel_north = new JPanel();
	private JLabel lbl_register = new JLabel("Guide to Hospital Management System");
	private JPanel panel_space_north = new JPanel();
	private JPanel panel_left = new JPanel();
	private JPanel panel_right = new JPanel();
	
	public void initComponent() {
		setLayout(new BorderLayout());
    
		// Header
		panel_north.setLayout(new BorderLayout());
		panel_north.add(lbl_register, BorderLayout.NORTH);
		lbl_register.setFont(font_title);
		lbl_register.setHorizontalAlignment(JLabel.CENTER);
		panel_north.add(panel_space_north, BorderLayout.CENTER);

		add(panel_north, BorderLayout.NORTH);
		add(panel_left, BorderLayout.WEST);
		add(panel_right, BorderLayout.EAST);

		JPanel imagesPanel = new JPanel();
		imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.Y_AXIS));

		ImageIcon imageIcon1 = new ImageIcon("src/hospitalFrame/pic-1.jpg");
		JLabel imageLabel1 = new JLabel(imageIcon1);
		JLabel titleLabel1 = new JLabel("First, click the menu File to use this system.");
		JPanel imagePanel1 = new JPanel();

		imagePanel1.setLayout(new BorderLayout());
		imagePanel1.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
		imagePanel1.add(imageLabel1, BorderLayout.CENTER);

		JPanel titlePanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel1.add(titleLabel1);
		imagePanel1.add(titlePanel1, BorderLayout.SOUTH);

		imagesPanel.add(imagePanel1);

		ImageIcon imageIcon2 = new ImageIcon("src/hospitalFrame/pic-2.png");
		JLabel imageLabel2 = new JLabel(imageIcon2);
		JLabel titleLabel2 = new JLabel("Choose Log in Menubar if you already have an account.");
		JPanel imagePanel2 = new JPanel();

		imagePanel2.setLayout(new BorderLayout());
		imagePanel2.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
		imagePanel2.add(imageLabel2, BorderLayout.CENTER);

		JPanel titlePanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel2.add(titleLabel2);
		imagePanel2.add(titlePanel2, BorderLayout.SOUTH);

		imagesPanel.add(imagePanel2);

		ImageIcon imageIcon3 = new ImageIcon("src/hospitalFrame/pic-3.png");
		JLabel imageLabel3 = new JLabel(imageIcon3);
		JLabel titleLabel3 = new JLabel("Choose Register Menu bar if you don't have an account yet.");
		JPanel imagePanel3 = new JPanel();

		imagePanel3.setLayout(new BorderLayout());
		imagePanel3.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
		imagePanel3.add(imageLabel3, BorderLayout.CENTER);

		JPanel titlePanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel3.add(titleLabel3);
		imagePanel3.add(titlePanel3, BorderLayout.SOUTH);

		imagesPanel.add(imagePanel3);

		panel_north.add(imagesPanel, BorderLayout.CENTER);

		setTitle("Guide");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,688);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public Guide() {
		initComponent();
	}
	
	public static void main(String[] args) {
		new Guide();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	}

}

