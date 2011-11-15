/**
 * Click casella ===> verifica se casella si può colpire ===> schermata cambio turno
 */

package eventi;

import game.Partita;
import game.PartitaSingola;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

public class AscoltatoreGiocaTurno implements MouseListener, Serializable {

	Partita partita;
	PartitaSingola partita_singola;
	boolean singola;

	public AscoltatoreGiocaTurno(Partita partita) {
		this.partita = partita;
		singola = false;
	}

	public AscoltatoreGiocaTurno(PartitaSingola partita) {
		this.partita_singola = partita;
		singola = true;
	}

	public void mouseClicked(MouseEvent e) {

		if (!singola) {
			// Da gestire con if... else ...:
			System.out.println("A.GT) cambiaTurno()");

			//

			//
			// CI VUOLE QUESTO
			//
			partita.cambiaTurno();
			// TMP:
			// partita.giocaTurno();
		} else {
			System.out.println("A.GT) cambiaTurno --- gioca Computer");
			// giocaturno di nuovo...il computer fa in automatico
			partita_singola.computer.colpisci_auto_1();
			partita_singola.framecampo_mio.aggiorna(partita_singola.giocatore);

			//
			// Non serve più far partire un nuovo turno, il pannello è già in
			// ascolto...Così risolvo il problema della prima casella colpita
			// Ghost
			//

			// partita_singola.giocaTurno();

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
