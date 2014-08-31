package view;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.Item;

public class ItemBox extends JLabel implements MouseListener {
	
	private static final long serialVersionUID = 2361705357295173302L;
	private final Item item;
	private boolean isExpanding;
	
	public ItemBox(Item item) {
		super();
		this.item = item;
		addMouseListener(this);
		collapse();
	}
	
	private String getHeader(String hexColor) {
		return 	"<div style='"
				+ "background: #" + hexColor + ";"
				+ "width: 480px;"
				+ "padding: 10px;"
				+ "font-size: 22px;"
				+ "'>"
				+ ""
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
		setData(getHeader("b3b3b3") + getBody());
		isExpanding = true;
	}

	private void collapse() {
		setData(getHeader("b3b3b3"));
		isExpanding = false;
	}

	private void setData(String data) {
		setText("<html>" + data  + "</html>");
	}

	private String getBody() {
		return "<div style='"
				+ "background: #e2e2e2;"
				+ "width: 480px;"
				+ "padding: 10px;"
				+ "font-size: 16px;"

				+ "'>"
				+ item.getDescription()
				+ "</div>";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		toggleExpanding();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if (!isExpanding) {
			setData(getHeader("aabbcc"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isExpanding) {
			setData(getHeader("b3b3b3"));
		}
	}
	
}
