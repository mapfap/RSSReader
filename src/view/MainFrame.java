package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Main JFrame, holds whole application in it. 
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8456560429229699542L;
	private FeedPanel feedPanel;
	private JButton fetchButton;
	private JTextField urlTextField;
	
	public MainFrame() {
		super("RSSReader");
		
		setLayout(new FlowLayout());
		
		feedPanel = new FeedPanel();
		JScrollPane scrollPane = new JScrollPane(feedPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(new Dimension( 650, 800));
		fetchButton = new JButton("Fetch");
		
//		frame.add(new JLabel("Enter URL of the RSS feed"));
		
		urlTextField = new JTextField(35);
		urlTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            		fetchData();
            	}
            }
            public void keyTyped(KeyEvent e) { }
            public void keyPressed(KeyEvent e) { }
        });
		
		fetchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				fetchData();
			}
			
		});
		
		add(urlTextField);
		add(fetchButton);
		add(scrollPane);
		
		ClassLoader loader = this.getClass().getClassLoader();
		URL url = loader.getResource("res/rss.xml");
		feedPanel.fetchData(url);
		
		Dimension fixedDimension = new Dimension(650, 800);
		setPreferredSize(fixedDimension);
		setMinimumSize(fixedDimension);
		setMaximumSize(fixedDimension);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void fetchData() {
		try {
			feedPanel.clearItems();
			feedPanel.fetchData(new URL(urlTextField.getText()));
		} catch (MalformedURLException ex) {
			urlTextField.setFocusable(false);
			if( JOptionPane.showConfirmDialog( null, "Invalid URL", null, JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION ) { 
				urlTextField.setFocusable(true);
			}
		}
	}
}
