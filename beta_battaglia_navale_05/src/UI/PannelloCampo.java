package UI;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

public/* abstract */class PannelloCampo extends JPanel implements Serializable {

	GridBagConstraints c;
	LabelCasella[][] label;

	public JLabel l_vuoto_2;

	public PannelloCampo() {

		super();

		setBackground(new Color(240, 255, 255));
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();

		label = new LabelCasella[10][10];

		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0.3;
		c.ipadx = 1;
		c.ipady = 1;

		// PannelloCampo pannello_campo = new PannelloCampo();
		JPanel pannello_campo = new JPanel();

		pannello_campo.setLayout(new GridLayout(10, 10, 0, 0));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				label[i][j] = new LabelCasella(i, j);
				pannello_campo.add(label[i][j]);
			}
		}
		pannello_campo.setBackground(Color.white);
		add(pannello_campo, c);

		//
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.35;
		c.weighty = 0.8;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 1;
		c.ipady = 1;

		JLabel l_vuoto_1 = new JLabel("");
		// add(p_vuoto_1, c);
		add(l_vuoto_1, c);

		//
		// Il label_vuoto_2 lo uso nelle classi figlie per inserire le navi
		// posizionate, colpite, etc.
		//

	}

	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// Graphics gg = label[1][1].getGraphics();
	// gg.drawImage(label[1][1].imm[2], 0, 0, 42, 42,null);
	// }

}
