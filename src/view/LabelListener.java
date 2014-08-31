package view;

import java.awt.event.MouseListener;

import javax.swing.JLabel;

public abstract class LabelListener implements MouseListener {

	private JLabel label;
	
	public LabelListener(JLabel label) {
		this.label = label;
	}
	
	public JLabel getLabel() {
		return label;
	}

}
