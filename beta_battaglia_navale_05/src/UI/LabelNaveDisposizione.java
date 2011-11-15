package UI;

import java.io.Serializable;

import javax.swing.*;

public class LabelNaveDisposizione extends JLabel implements Serializable {

	ImageIcon i;

	public LabelNaveDisposizione(String text) {
		super();
		i = new ImageIcon("bn_gif/nave.gif");
		setIcon(i);
		setText(text);
	}

	public void cambiaNave(int n) {
		setText("x" + n);
	}

}
