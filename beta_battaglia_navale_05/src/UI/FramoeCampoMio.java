package UI;

import java.awt.Container;

import javax.swing.JFrame;

import campo.Giocatore;

public class FramoeCampoMio extends JFrame {

	Container contenitore;

	public FramoeCampoMio() {
		super();

		setBounds(0, 50, 450, 500);
		contenitore = getContentPane();
	}

	public void aggiorna(Giocatore giocatore) {

		contenitore.removeAll();

		PannelloCampoMio pp = new PannelloCampoMio(giocatore);

		contenitore.add(pp);
		contenitore.validate();

	}

}
