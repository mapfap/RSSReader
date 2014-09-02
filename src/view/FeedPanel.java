package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.JAXBException;

import model.Channel;
import model.Item;
import model.RSS;
import controller.RSSReader;

/**
 * Feed panel of the program, fetch and show the list of items.
 * It suppose to place inside the main frame.
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class FeedPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private RSSReader rssReader;

	public FeedPanel() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(600, 5000));
		rssReader = RSSReader.getInstance();
	}

	/**
	 * Fetch RSS from specified URL and show them on UI.
	 * @param url source of RSS. 
	 * @throws JAXBException if the is a fault in unmarshalling.
	 */
	public void fetchData(URL url) throws JAXBException {
		clearItems();
		
		RSS rss = rssReader.getRSS(url);
		
		Channel channel = rss.getChannel();
		showChannelDetails(channel);

		add(new JLabel("  _  "));
		List<Item> items = channel.getItems();
		setPreferredSize(new Dimension( 600, items.size() * 300));

		for (Item item : items)  {
			if (item.getEnclosure() != null) {				
				System.out.println("Enclosure detected: " + item.getEnclosure());
			}
			add(new ItemBox(item));
		}
	}

	/**
	 * Show channel description like it's just a item.
	 * @param channel to be displayed.
	 */
	private void showChannelDetails(Channel channel) {
		Item mainItem = new Item();
		mainItem.setTitle(channel.getTitle());
		mainItem.setLink(channel.getLink());
		mainItem.setDescription(channel.getDescription());
		ItemBox mainItemBox = new ItemBox(mainItem);
		mainItemBox.toggleExpanding();
		add(mainItemBox);
	}

	/**
	 * Removes all item and repaint the swing components.
	 */
	public void clearItems() {
		removeAll();
		updateUI();
	}

}
