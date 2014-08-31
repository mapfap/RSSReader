package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.bind.JAXBException;

import model.Channel;
import model.Item;
import model.RSS;
import controller.RSSReader;

/**
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font font;
	private boolean isFontConflicted;

	public MainPanel() {
		
		loadFont();
		setPreferredSize(new Dimension( 600, 10000));
		
		RSSReader rssReader = RSSReader.getInstance();
		
		try {
			RSS rss = rssReader.getRSS();
			Channel channel = rss.getChannel();
			for (Item item : channel.getItems() )  {
				add(new ItemBox(item));
			}
			
		} catch (JAXBException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	private void loadFont() {
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			URL url = loader.getResource("res/thaisansneue-regular-webfont.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, new File(url.getFile()));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			isFontConflicted = !ge.registerFont(font);

		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		
		if ( isFontConflicted ) {
			setFont(new Font("ThaiSans Neue", Font.BOLD, 10));
		} else {
			setFont(font);
		}
	}
}
