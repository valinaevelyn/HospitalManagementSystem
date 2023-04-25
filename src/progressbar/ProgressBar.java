package progressbar;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBar extends JFrame{
	
	private JProgressBar jpb = new JProgressBar();	
	
	public void updateProgressBar() {
		while(jpb.getValue() < jpb.getMaximum()) {
			jpb.setValue(jpb.getValue() + 5);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		jpb.setStringPainted(true);
		jpb.setMaximum(1000);
		jpb.setValue(0);
		add(jpb);
		
		setSize(400, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public ProgressBar() {
		updateProgressBar();
	}
}
