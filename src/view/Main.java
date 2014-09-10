package view;

import controller.RSSReader;

/**
 * Main class, create the main JFrame.
 * Program starts from here.
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class Main {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RSSReader rssReader = RSSReader.getInstance();
				new MainFrame(rssReader);
			}
		});
	}
	
}
