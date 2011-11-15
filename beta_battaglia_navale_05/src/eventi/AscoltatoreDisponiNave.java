package eventi;

/**
 * Serve a far terminare il turno di disposizione navi
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import game.*;

public class AscoltatoreDisponiNave implements ActionListener, Serializable {

	Partita partita;
	PartitaSingola partita_singola;

	boolean singola;

	public AscoltatoreDisponiNave(Partita partita) {
		this.partita = partita;
		singola = false;
	}

	public AscoltatoreDisponiNave(PartitaSingola partita) {
		this.partita_singola = partita;
		singola = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!singola) {
			partita.disponiNavi();
		} else {
			// disponinavi --- computer automatico
			System.out.println("Navi DISPOSTE");
			partita_singola.giocaTurno();
		}
	}
}
