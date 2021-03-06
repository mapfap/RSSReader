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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import controller.RSSReader;

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
	private RSSReader rssReader;

	/**
	 * Create and setup the main frame.
	 * @param rssReader a controller for fetching rss.
	 */
	public MainFrame(RSSReader rssReader) {
		super("RSSReader");

		this.rssReader = rssReader;
		initUI();
		setListeners();
		
		loadDefaultRSS();

		pack();
		setVisible(true);
	}
	
	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		feedPanel = new FeedPanel(rssReader);
		fetchButton = new JButton("Fetch");
		urlTextField = new JTextField(40);
		
		JScrollPane scrollPane = new JScrollPane(feedPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setPreferredSize(new Dimension(650, 800));

		add(new JLabel("Enter URL of the RSS feed"));
		add(urlTextField);
		add(fetchButton);
		add(scrollPane);

		Dimension fixedDimension = new Dimension(650, 800);
		setPreferredSize(fixedDimension);
		setResizable(false);
	}

	private void setListeners() {
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
	}

	/**
	 * Load internal RSS file, contains guideline of the program.
	 */
	private void loadDefaultRSS() {
		ClassLoader loader = this.getClass().getClassLoader();
		URL url = loader.getResource("res/rss.xml");
		try {
			feedPanel.fetchData(url);
		} catch (JAXBException e) {
			JOptionPane.showMessageDialog(null, "Internal data corrupted.");
		}
	}

	/**
	 * Fetch the data from URL acquired from text field.
	 */
	private void fetchData() {
		try {
			feedPanel.fetchData(new URL(urlTextField.getText()));
		} catch (JAXBException e) {
			alert("Invalid RSS format or no Internet Connection");
			loadDefaultRSS();
		} catch (MalformedURLException ex) {
			alert("Invalid URL");
			loadDefaultRSS();
		}
	}

	/**
	 * This prevent users from get stuck with enter loop of alert.
	 * 
	 * @param text to display
	 */
	private void alert(String text) {
		urlTextField.setFocusable(false);
		if( JOptionPane.showConfirmDialog( null, text, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.OK_OPTION ) { 
			urlTextField.setFocusable(true);
		} else {
			urlTextField.setFocusable(true);			
		}
	}

}
