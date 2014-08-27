
import java.awt.Dimension;

import javax.swing.JFrame;

import view.MainPanel;

/**
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class Main {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		
		JFrame frame = new JFrame("RSSReader");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new MainPanel());
		
		Dimension fixedDimension = new Dimension(650, 800);
		
		frame.setPreferredSize(fixedDimension);
		frame.setMinimumSize(fixedDimension);
		frame.setMaximumSize(fixedDimension);

		frame.pack();
		frame.setVisible(true);
	}
}
