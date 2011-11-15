/**
 * Salvataggio serve per salare.
 * 
 *  Mette a disposizione la variabile boolean fine_partita che serve per 
 * sapere se la prtita che si stava giocando è terminata o meno.
 * 
 */

package game;

import java.io.Serializable;

import campo.*;

public class Salvataggio implements Serializable {

	public Campo[] campo;

	public Giocatore giocatore_singolo;
	public GiocatoreComputer_copia computer;

	public boolean fine_partita;
	public int turno;
	public Giocatore[] giocatori;
	public boolean singola;

	public Salvataggio(Campo[] campo, Giocatore[] giocatori, int turno) {
		this.campo = campo;
		this.giocatori = giocatori;
		fine_partita = false;
		this.turno = turno;
		singola = false;
	}

	public Salvataggio() {
		campo = new Campo[2];
		campo[0] = new Campo();
		campo[1] = new Campo();
		fine_partita = true;
		turno = 0;
		singola = false;
	}

	public Salvataggio(Campo[] campo, Giocatore giocatore,
			GiocatoreComputer_copia computer) {
		singola = true;
		this.giocatore_singolo = giocatore;
		this.computer = computer;
		this.campo = campo;
	}

}
