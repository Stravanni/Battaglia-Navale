package UI;

import java.awt.GridLayout;

import javax.swing.JPanel;

import campo.Giocatore;

public class PannelloCampoMio extends JPanel {

	LabelCasella[][] label;

	public PannelloCampoMio(Giocatore giocatore) {
		super();

		label = new LabelCasella[10][10];

		int stato;
		
		setLayout(new GridLayout(10, 10, 0, 0));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				label[i][j] = new LabelCasella(i, j);
				add(label[i][j]);
				stato = giocatore.getCampoMio().getStato(false)[i][j];
				label[i][j].cambiaSfondo(stato);
			}
		}// fine for
	}

}
