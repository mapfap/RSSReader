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

	public void toggleExpanding() {
		if (isExpanding) {
			collapse();
		} else {
			expand();
		}
	}

	private void expand() {
		setData(getHeader(normalHeadHexColor) + getBody());
		isExpanding = true;
	}

	private void collapse() {
		setData(getHeader(normalHeadHexColor));
		isExpanding = false;
	}

	private void setData(String data) {
		setText("<html>" + data  + "</html>");
	}

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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			//			System.out.println("Go to site");
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

	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (!isExpanding) {
			setData(getHeader(hoverHeadHexColor));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isExpanding) {
			setData(getHeader(normalHeadHexColor));
		}
	}

}
