package campo;

import java.io.Serializable;

public class Cella implements Serializable {
	// private Posizione coordinate;
	private boolean libera;
	private int stato;
	private Nave nave;
	private int verso;

	// Questa convenzione mi serve per disegnare le icone delle navi
	// Devo sapere se sono: orizzonatali (0) oppure verticali (1)
	// e se sono: testa(0) centro(1) coda(2)
	//
	// ----- 10
	// - 00 01-11 02
	// ----- 12
	//

	// private int x, y;

	/**
	 * Oggetto cella, necessita di coordinate (X;Y).
	 * 
	 * @param {Nave} nave || null
	 */
	public Cella() {
		// 0 = niente
		// 1 = cercato
		// 2 = colpito
		// 3 = affondato
		stato = 0;
		libera = true;
		verso = -1;
	};

	/**
	 * assegna nave
	 * 
	 * @param {Nave} nave
	 * @return {boolean}
	 */
	public boolean assegnaNave(Nave nave) {
		boolean ret = true;
		this.nave = nave;
		libera = false;
		return ret;
	};

	/**
	 * Colpisci la casella. Se c'è la nave colpisce anche la nave e ottine lo
	 * stato. Se non c'è la nave segna la casella come "guardata"
	 * 
	 * @return {int} successo_azione/stato
	 */
	public int colpisci() {
		int ret = 0;
		if (stato == 1 || stato == 2 || stato == 3) {
			System.out.println("Già colpita");
			return -1;
		}
		stato = 1;
		ret = stato;
		if (!libera) {
			nave.colpisci();
			stato = nave.getStato();
			// System.out.println("Colpisco....stato: " + stato);
			ret = stato;
		}
		return ret;
	};

	/**
	 * Ritorna lo stato della cella
	 * 
	 * @return {int} stato
	 */
	public int getStato() {
		if (nave != null && nave.getStato() == 3)
			stato = nave.getStato();
		return stato;
	};

	public boolean getLibera() {
		return libera;
	};

	public Nave getNave() {
		return nave;
	};

	public void setVerso(int verso) {
		this.verso = verso;
	};

	public int getVerso() {
		return verso;
	};
}
