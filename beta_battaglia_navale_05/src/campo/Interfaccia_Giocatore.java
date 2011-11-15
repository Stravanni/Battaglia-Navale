package campo;

import java.util.ArrayList;
import java.util.LinkedList;

public interface Interfaccia_Giocatore {

	/**
	 * Posiziona una nave nel campo di gioco.
	 * 
	 * @param {Nave} nave
	 * @param {Posizione} posizione
	 * @param {boolean} verticale
	 * @return {boolean} risultato_posizionamento
	 */
	public boolean posizionaNave(Nave nave, Posizione posizione,
			boolean verticale);

	/**
	 * Restituisce la lista delle navi del giocatore
	 * 
	 * @return {Nave[4]} navi
	 */
	public ArrayList<Nave> getNaviDaPosizionare();

	/**
	 * 
	 * @return
	 */
	public LinkedList<Nave> getNavi();

}
