package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
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
	
//	private

	private void loadFont() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/mapfap/Desktop/thaisansneue-regular-webfont.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			isFontConflicted = !ge.registerFont(font);
			System.out.println(isFontConflicted);

		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		
		if ( isFontConflicted ) {
			setFont(new Font("ThaiSans Neue", Font.BOLD, 10));
		} else {
			setFont(font);
		}
	}

	public MainPanel() {

		loadFont();
		setPreferredSize(new Dimension( 600, 10000));
		
		RSSReader rssReader = RSSReader.getInstance();
		
		try {
			RSS rss = rssReader.getRSS();
			Channel channel = rss.getChannel();
			for (Item item : channel.getItems() )  {
				addFeedItem(item);
			}
			
		} catch (JAXBException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	private void addFeedItem(Item item) {
		String html = "<html>"
				+ "<div style='"
				+ "background: #b3b3b3;"
				+ "width: 480px;"
				+ "padding: 10px;"
				+ "font-size: 22px;"
				+ "'>"
				+ ""
				+ item.getTitle()
				+ "</div>"
				+ "<div style='"
				+ "background: #e2e2e2;"
				+ "width: 480px;"
				+ "padding: 10px;"
				+ "font-size: 16px;"

				+ "'>"
				+ item.getDescription()
				+ " see more.."
				+ "</div>"

   			+ "</html>";
		
		JLabel feedItem = new JLabel(html); 
		feedItem.addMouseListener(new LabelListener(feedItem) {

			@Override
			public void mouseClicked(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) {
				this.getLabel().setText("CLICKED");
			}

			@Override
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) { }
			
		});
		add(feedItem);
	}
}
