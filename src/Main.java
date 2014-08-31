
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
		frame.setLayout(new FlowLayout());
		
//		frame.add(new JLabel("Enter URL of the RSS feed"));
		JTextField urlTextField = new JTextField(35);
		frame.add(urlTextField);
		frame.add(new Button("Fetch"));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JScrollPane scrollPane = new JScrollPane(new MainPanel());
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(new Dimension( 650, 800));
//		scrollPane.add(new MainPanel());
		frame.add(scrollPane);
//		frame.add(new MainPanel());
		
		Dimension fixedDimension = new Dimension(650, 800);
		
		frame.setPreferredSize(fixedDimension);
		frame.setMinimumSize(fixedDimension);
		frame.setMaximumSize(fixedDimension);

		frame.pack();
		frame.setVisible(true);
		
	}
}
