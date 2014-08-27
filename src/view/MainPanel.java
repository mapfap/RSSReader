package view;

import java.awt.Button;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Sarun Wongtanakarn 5510546166
 *
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font font;
	private boolean isFontConflicted;

	private void loadFont() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/mapfap/Desktop/thaisansneue-regular-webfont.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			isFontConflicted = !ge.registerFont(font);
			System.out.println(isFontConflicted);

		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
	}

	public MainPanel() {

		loadFont();

		String html = "<html>"
				+ "<div style='"
				+ "background: #b3b3b3;"
				+ "width: 480px;"
				+ "padding: 10px;"
				+ "font-size: 30px;"
				+ "'>"
				+ ""
				+ "Abuse report police chief to stay [FULL]"
				+ "</div>"
				+ "<div style='"
				+ "background: #d2d2d2;"
				+ "width: 480px;"
				+ "padding: 10px;"
				+ "font-size: 18px;"

				+ "'>"

				+ "The Police and Crime Commissioner for South Yorkshire vows to stay in the job despite calls for him to quit over a damning report into child abuse in Rotherham."
				+ " see more.."
				+ "</div>"

   			+ "</html>";

		JLabel feedBox = new JLabel(html);
		add(new JLabel("Enter URL of the RSS feed"));

		JTextField urlTextField = new JTextField(35);

		if ( isFontConflicted ) {
			feedBox.setFont(new Font("ThaiSans Neue", Font.BOLD, 10));
			urlTextField.setFont(new Font("ThaiSans Neue", Font.BOLD, 25));
		} else {
			feedBox.setFont(font);
			urlTextField.setFont(font);
		}

		add(urlTextField);
		add(new Button("Fetch"));
		add(feedBox);
	}

}
