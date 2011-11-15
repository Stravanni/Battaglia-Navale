package eventi;

import game.Start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JMenuItem;

public class AscoltatoreBottoniMenu implements ActionListener, Serializable {

	Start start;

	public boolean singola;

	public AscoltatoreBottoniMenu(Start start) {
		this.start = start;
		singola = false;
	}

	public void actionPerformed(ActionEvent e) {

		String nome = ((JMenuItem) e.getSource()).getText();

		if (nome == "Salva partita") {
			System.out.println("asc) " + singola);
			if (!singola)
				start.p.salvaPartita();
			else
				start.partita_singola.salvaPartita();
			System.out.println("SALVA PARTITA");
		}
		if (nome == "Single player") {
			// start.nuovaPartita(true);
			start.nuovaPartita(true);
			System.out.println("Single Player");
		}
		if (nome == "Multiplayers") {

			start.nuovaPartita();

			System.out.println("Multiplayers");
		}

	}
}
