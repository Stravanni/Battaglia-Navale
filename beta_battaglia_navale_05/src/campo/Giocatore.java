/*
 * LIST & ARRAYLIST
 * 
 *	Sono entrambe implementazioni "concrete" della interfaccia List.
 * Solo che ArrayList è basata su un array, mentre LinkedList è basata su una lista linkata.
 * Questo porta ad una differenza nella complessità (e quindi nelle "performance") delle varie operazioni.
 * Il get() per ArrayList ha una complessità O(1) mentre per LinkedList è O(n).
 * Con un array se chiedi l'elemento all'indice 10, lo ottieni subito,
 * mentre con una lista linkata deve scansionare tutta la lista fino a quel punto!
 * 
 */

package campo;

import java.io.Serializable;
import java.util.*;

public class Giocatore implements Serializable{
	private Campo campo_mio;
	public Campo campo_avversario;
	// private Nave[] nave;
	private ArrayList<Nave> navi_da_posizionare;
	private LinkedList<Nave> navi;
	private int[] lunghezze = { 4, 3, 3, 2 };
	private String[] nomi = { "Portaerei", "incrociatore_1", "incrociatore_2",
			"Sottomarino" };
	private String nome_giocatore;
	private int turno;
	private boolean pronto_a_giocare;

	public boolean fine_turno, fine_partita;

	public Giocatore(String nome, int turno, Campo[] campo) {
		this.campo_mio = campo[0 + turno];

		this.campo_avversario = campo[(1 + turno) % 2]; // campo_avversario =
														// (1+turno) mod 2
														// così posso passare
														// sempre lo stesso
														// array di campo ad
														// entrambi i giocatori
		navi_da_posizionare = new ArrayList<Nave>();
		navi = new LinkedList<Nave>();
		Nave n;
		for (int i = 0; i < 4; i++) {
			n = new Nave(lunghezze[i], nomi[i], i);
			navi_da_posizionare.add(n);
			navi.add(n);
		}
		this.nome_giocatore = nome;
		this.turno = turno;

		//
		// Usati dall'ascoltatoregiocoTurno e dal pannelloGriocaTurno
		//
		fine_turno = false;
		fine_partita = false;

		pronto_a_giocare = false;
	};

	/**
	 * Posiziona una nave nel campo di gioco.
	 * 
	 * @param {Nave} nave
	 * @param {Posizione} posizione
	 * @param {boolean} verticale
	 * @return {boolean} risultato_posizionamento
	 */
	public boolean posizionaNave(Nave nave, Posizione posizione,
			boolean verticale) {
		boolean ret = false;

		System.out.println("Giocatore posiziona nave");
		//
		// int ris = 0;
		if (navi_da_posizionare.contains(nave)) {
			ret = campo_mio.posizionaNave(nave, posizione, verticale);
			navi_da_posizionare.remove(nave);
			if (navi_da_posizionare.isEmpty())
				pronto_a_giocare = true;
		} else {
			System.out.println("_________________");
			System.out
					.println(nome_giocatore + " non puoi posizionare la nave "
							+ nave.getName() + " lì");
			System.out.println("_________________");
		}
		System.out.println("Giocatore posiziona nave: " + ret);
		return ret;
	};

	/**
	 * 
	 * @return
	 */
	public boolean posizionaRandom() {
		// Simulo l'utilizzo di una coda per il posizionamento delle vavi RANDOM
		while (!navi_da_posizionare.isEmpty()) {
			// int i = 0;
			Nave nave = navi_da_posizionare.get(0);
			boolean[] vertical = new boolean[2];
			vertical[0] = true;
			vertical[1] = false;
			// giocatore_1.posizionaNave(nave, posizione, verticale);
			int x = (int) (Math.random() * 10) % 10;
			int y = (int) (Math.random() * 10) % 10;
			int z = (int) (Math.random() * 10) % 2;
			boolean verticale = vertical[z];
			Posizione posizione = new Posizione(x, y);
			if (posizionaNave(nave, posizione, verticale)) {
				System.out.println("===>               ### Posizionata nave:  "
						+ nave.getName());
				navi_da_posizionare.remove(nave);
			} else {
				System.out.println("### NO:  " + nave.getName());
				navi_da_posizionare.add(nave);
			}
			// System.out.println("### ___ " + navi_1.toString());
		}
		return true;
	}

	/**
	 * Colpisci casella avversaria in posizione selezionat.
	 * 
	 * @param {Posizione} pos
	 * @return {int} risultato_colpisci_casella
	 */
	public int colpisci(Posizione pos) {
		return campo_avversario.colpisci(pos);
	}

	/**
	 * Stampa il mio campo con la vista delle navi, poi stampa quello avversario
	 * senza far vedere le navi
	 */
	public void stampaVistaCampo() {
		campo_mio.stampaCampo(false);
		campo_avversario.stampaCampo(true);
	};

	/**
	 * Campo MIO.
	 * 
	 * @return {Campo} campo_mio
	 */
	public Campo getCampoMio() {
		return campo_mio;
	}

	/**
	 * Campo AVVERSARIO
	 * 
	 * @return {Campo} campo_mio
	 */
	public Campo getCampoAvversario() {
		return campo_avversario;
	}

	/**
	 * Restituisce la lista delle navi del giocatore
	 * 
	 * @return {Nave[4]} navi
	 */
	public ArrayList<Nave> getNaviDaPosizionare() {
		return navi_da_posizionare;
	};

	/**
	 * 
	 * @return
	 */
	public LinkedList<Nave> getNavi() {
		return navi;
	};

	/**
	 * Restituisce un array di stringhe di nomi delle navi
	 * 
	 * @return {String[4]} nome_navi
	 */
	public String[] getNomeNavi() {
		return nomi;
	};

	/**
	 * 
	 * @return {int} turno
	 */
	public int getTurno() {
		return turno;
	};

	/**
	 * 
	 * @return {boolean} pronto_a_giocare
	 */
	public boolean getPronto() {
		return pronto_a_giocare;
	};

	public String getNome() {
		return nome_giocatore;
	}

}
