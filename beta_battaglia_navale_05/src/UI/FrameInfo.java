package UI;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameInfo extends JFrame implements Serializable {

	public FrameInfo() {
		super("info");

		setResizable(false);

		setBounds(800, 50, 150, 120);
		JPanel pannello = new JPanel();
		add(pannello);
		pannello.setBackground(new Color(240, 255, 220));

		String info = "Created by";

		JLabel label = new JLabel(info);
		pannello.add(label);

		info = "Giovanni Simonini ©";

		JLabel label2 = new JLabel(info);
		pannello.add(label2);

		info = "2011";
		JLabel label3 = new JLabel(info);
		pannello.add(label3);

		info = "versione: 1.0.0";
		JLabel label4 = new JLabel(info);
		pannello.add(label4);
	}

}
