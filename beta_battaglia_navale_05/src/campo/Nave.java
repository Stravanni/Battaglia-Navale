package campo;

import java.io.Serializable;

public class Nave implements Serializable{
	private int lunghezza;
	private String nome;
	private int valore;
	private boolean colpita;
	private boolean affondato;
	private int id;

	// private Cella[] casella;

	public Nave(int lunghezza, String nome, int id) {
		this.lunghezza = lunghezza;
		this.nome = nome;
		this.valore = lunghezza;
		this.id = id;
		this.colpita = false;
		this.affondato = false;

		// this.casella = new Cella[lunghezza];

		// for (int i = 0; i < lunghezza; i++) { casella[i] = new Cella(); }

	};

	/**
	 * Colpisci la nave.
	 * 
	 * @return {boolean} affondata
	 */
	public boolean colpisci() {
		valore--;
		colpita = true;
		if (valore == 0) {
			// System.out.println(nome + " AFFONDATA");
			affondato = true;
		}
		return affondato;
	}

	/**
	 * 2 = COLPITA; 3 = AFFONDATA; -1= NIENTE
	 * 
	 * @return {int} stato
	 */
	public int getStato() {
		int stato = -1;
		if (colpita && !affondato)
			stato = 2;
		else if (affondato)
			stato = 3;
		return stato;
	}

	/**
	 * Restituisce id nave.
	 * 
	 * @return {int} id
	 */
	public int getId() {
		return id;
	};

	/**
	 * Restituisce il nome della nave.
	 * 
	 * @return {String} nome
	 */
	public String getName() {
		return nome;
	};

	/**
	 * Lunghezza nave
	 * 
	 * @return {int} lunghezza
	 */
	public int getLunghezza() {
		return lunghezza;
	}
}
