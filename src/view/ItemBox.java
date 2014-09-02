package view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import model.Item;

/**
 * Box of UI that holds an Item of rss.
 * It can be expanded and collapsed by clicking.
 * It suppose to place inside the feed panel.
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class ItemBox extends JLabel implements MouseListener {

	private static final long serialVersionUID = 2361705357295173302L;
	private static final String normalHeadHexColor = "b3b3b3";
	private static final String hoverHeadHexColor = "aabbcc";
	private static final String bodyHexColor = "e2e2e2";
	private static final int boxWidth = 480;

	private final Item item;
	private boolean isExpanding;

	public ItemBox(Item item) {
		super();
		this.item = item;
		addMouseListener(this);
		collapse();
	}

	/**
	 * Get HTML text for head part of item box.
	 * 
	 * @param hexColor specifies background color using hexcode.
	 * @return HTML String for head part of item box.
	 */
	protected String getHeader(String hexColor) {
		return 	"<div style='"
				+ "background: #" + hexColor + ";"
				+ "width: " + boxWidth + "px;"
				+ "padding: 10px;"
				+ "font-size: 22px;"
				+ "'>"
				+ item.getTitle()
				+ "</div>";
	}

	/**
	 * Toggle the item box to expand or collapse.
	 */
	public void toggleExpanding() {
		if (isExpanding) {
			collapse();
		} else {
			expand();
		}
	}

	/**
	 * Expands the item box to show description.
	 */
	private void expand() {
		setData(getHeader(normalHeadHexColor) + getBody());
		isExpanding = true;
	}

	/**
	 * Collapses the item box to hide the description.
	 */
	private void collapse() {
		setData(getHeader(normalHeadHexColor));
		isExpanding = false;
	}

	/**
	 * Set HTML text to JLabel.
	 * @param data
	 */
	private void setData(String data) {
		setText("<html>" + data  + "</html>");
	}

	/**
	 * Get HTML String for body part of item box.
	 * 
	 * @param hexColor specifies background color using hexcode.
	 * @return HTML String for body part of item box.
	 */
	private String getBody() {
		return "<div style='"
				+ "background: #" + bodyHexColor + ";"
				+ "width: " + boxWidth + "px;"
				+ "padding: 10px;"
				+ "font-size: 16px;"
				+ "'>"
				+ item.getDescription()
				+ "</div>";
	}

	/**
	 * Left Click to toggle expanding.
	 * Right Click to open a web browser.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			if(Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI(item.getLink()));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			toggleExpanding();						
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	/**
	 * Implements hover, likes in CSS.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (!isExpanding) {
			setData(getHeader(hoverHeadHexColor));
		}
	}

	/**
	 * Cancel hover action. 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (!isExpanding) {
			setData(getHeader(normalHeadHexColor));
		}
	}

}
