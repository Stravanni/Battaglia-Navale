package UI;

import campo.*;
import eventi.AscoltatoreCambiaTurno;
import eventi.AscoltatoreDisponiNave;
import eventi.AscoltatoreGiocaTurno;

public interface I_MainFrame {

	/**
	 * Crea la vista di sisposizione delle navi
	 * 
	 * @param {Giocatore} giocatore
	 * @param {AscoltatoreDisponiNave} ascoltatore
	 */
	public void disponiNave(Giocatore giocatore,
			AscoltatoreDisponiNave ascoltatore);

	/**
	 * Crea la vista di gioco di un turno
	 * 
	 * @param {Giocatore} giocatore
	 * @param {AscoltatoreGiocaTurno} ascoltatore
	 */
	public void giocaTurno(Giocatore giocatore,
			AscoltatoreGiocaTurno ascoltatore);

	public void cambiaTurno(Giocatore giocatore,
			AscoltatoreCambiaTurno ascoltatore);
}
