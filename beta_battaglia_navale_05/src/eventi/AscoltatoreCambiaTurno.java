/**
 * Associare al bottone "fatto" di un pannello "GiocaTurno"
 * 
 * FATTO ---> partita.giocaTurno();
 */

package eventi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import campo.*;
import game.*;

public class AscoltatoreCambiaTurno implements ActionListener,Serializable {

	Partita partita;

	public AscoltatoreCambiaTurno(Partita partita) {
		this.partita = partita;
	}

	public void actionPerformed(ActionEvent e) {
		partita.giocaTurno();
		//partita.cambiaTurno();
	}

}
