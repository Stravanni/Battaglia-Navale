//
//COLPISCI RITORNA 
//
//-1 errore
// 0 vuoto
// 1 colpito
// 2 affondato
// 3 fine_partita
//
//

/**
 * @author giovannisimonini
 * 
 * 
 *         Userei le liste per le caselle cercate. Per quelle colpite,
 *         affondate, vuote....
 */
package campo;

import java.io.Serializable;
import java.util.ArrayList;

//import java.util.List;

public class Campo implements Interfaccia_Campo, Serializable {
	Cella[][] casella;

	// Provo ad usare le liste
	ArrayList<Nave> navi;
	ArrayList<Nave> navi_colpite;
	ArrayList<Nave> navi_affondate;

	public Campo() {
		casella = new Cella[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				casella[i][j] = new Cella();
			}
		}

		navi = new ArrayList<Nave>();
		navi_colpite = new ArrayList<Nave>();
		navi_affondate = new ArrayList<Nave>();
	};

	/**
	 * Posiziona una nave nel campo.
	 * 
	 * @param {Nave} nave
	 * @param {Posizione} posizione
	 * @param {boolean} verticale
	 * @return {boolean} risultato
	 */
	public boolean posizionaNave(Nave nave, Posizione posizione,
			boolean verticale) {
		int x = posizione.getX();
		int y = posizione.getY();
		int lunghezza = nave.getLunghezza();
		if (verticale) {
			// < stretto e non <=.
			if (x + lunghezza > 10) {
				System.out.println("ERRORE POSIZIONAMENTO --- TROPPO IN BASSO");
				return false;
			}
			for (int i = 0; i < lunghezza; i++) {
				if (!casella[x + i][y].getLibera()) {
					System.out.println("ERRORE POSIZIONAMENTO --- NON LIBERO");
					return false;
				}
			}
			for (int i = 0; i < lunghezza; i++) {
				if (i == 0)
					casella[x + i][y].setVerso(10);
				else if (i == lunghezza - 1)
					casella[x + i][y].setVerso(12);
				else
					casella[x + i][y].setVerso(11);
				casella[x + i][y].assegnaNave(nave);
			}
		} else {
			if (y + lunghezza > 10) {
				System.out.println("ERRORE POSIZIONAMENTO --- TROPPO A DESTRA");
				return false;
			}
			for (int i = 0; i < lunghezza; i++) {
				if (!casella[x][y + i].getLibera()) {
					System.out.println("ERRORE POSIZIONAMENTO --- NON LIBERO");
					return false;
				}
			}
			for (int i = 0; i < lunghezza; i++) {
				if (i == 0)
					casella[x][y + i].setVerso(00);
				else if (i == lunghezza - 1)
					casella[x][y + i].setVerso(02);
				else
					casella[x][y + i].setVerso(01);
				casella[x][y + i].assegnaNave(nave);
			}
		}
		navi.add(nave);
		// if (navi.contains(nave))
		// System.out.println("Contains ok!!");

		return true;
	};

	/**
	 * Colpisci una casella. Ritorna TRUE se finita la partita ritorna:
	 * 
	 * @param {Posizione} pos
	 * 
	 *        //*@return {boolean} fine_partita
	 * @return -1 errore
	 * @return 0 vuoto
	 * @return 1 colpito
	 * @return 2 affondato
	 * @return 3 fine_partita
	 * 
	 */
	public int colpisci(Posizione pos) {
		// boolean fine_partita = false;
		int ret = 0;

		int x = pos.getX();
		int y = pos.getY();

		int stato_casella = casella[x][y].colpisci();

		if (stato_casella < 0) {
			System.out.println("ERRORE --- GIA' CERCATO IN QEUSTA CASELLA");
			return -1;
		} else
			System.out.println("Cerco in casella: (" + x + ";" + y + ") ");
		//
		// Se colpita tolgo la nave da "nave" e metto in "navi_colpite"
		//
		if (stato_casella == 2) {
			navi.remove(casella[x][y].getNave());
			if (navi_colpite.contains(casella[x][y].getNave()))
				;
			else
				navi_colpite.add(casella[x][y].getNave());
			System.out.println("NAVE COLPITA: "
					+ casella[x][y].getNave().getName());
			ret = 1;
		}
		if (stato_casella == 3) {
			navi_colpite.remove(casella[x][y].getNave());
			if (navi_affondate.contains(casella[x][y].getNave()))
				;
			else
				navi_affondate.add(casella[x][y].getNave());
			System.out.println("NAVE AFFONDATA: "
					+ casella[x][y].getNave().getName() + " navi e colpite: "
					+ navi.isEmpty() + " - " + navi_colpite.isEmpty());
			ret = 2;
		}
		if (navi.isEmpty() && navi_colpite.isEmpty())
			ret = 3;// fine_partita = true;
		return ret;// return fine_partita;
	};

	/**
	 * Stampa in Console lo stato del campo. Se avversario == TRUE non sono
	 * visualizzate le navi non colpite
	 * 
	 * @param {boolean} avversario
	 */
	public void stampaCampo(boolean avversario) {
		int stato = 0;
		String nome_campo = "CAMPO MIO";
		if (avversario)
			nome_campo = "CAMPO AVVERSARIO";
		String carattere = " ";
		System.out.println(nome_campo);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (avversario) {
					stato = casella[i][j].getStato();
					carattere = this.cercaCarattere(stato);
				} else {
					stato = casella[i][j].getStato();
					if (stato == 0 && !casella[i][j].getLibera()) {
						carattere = "#";
					} else {
						carattere = this.cercaCarattere(stato);
					}
				}
				System.out.print("|" + carattere);
			}// fine ciclo j
			System.out.println("|");
		}// fine cicloi
	};

	/**
	 * Ottine lo stato del campo. Se avversario == TRUE non sono visualizzate le
	 * navi non colpite.________________________________________________________
	 * 0 = niente. 1 = colpo vuoto. 2 = colpito. 3 = affondato. -1 = HIDDEN
	 * 
	 * @param {boolean} avversario
	 * @return {int[10][10]} stato_campo
	 */
	public int[][] getStato(boolean avversario) {
		int stato = 0;
		int[][] stato_campo = new int[10][10];
		String nome_campo = "CAMPO MIO";
		if (avversario)
			nome_campo = "CAMPO AVVERSARIO";
		String carattere = " ";
		//System.out.println(nome_campo);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (avversario) {
					stato = casella[i][j].getStato();
					stato_campo[i][j] = stato;
					carattere = this.cercaCarattere(stato);
				} else {
					stato = casella[i][j].getStato();
					if (stato == 0 && !casella[i][j].getLibera()) {
						carattere = "#";
						//
						// -1 = HIDDEN STATE
						//
						//  visto solo da "CAMPO MIO"
						//
						stato_campo[i][j] = -1;
					} else {
						carattere = this.cercaCarattere(stato);
						stato_campo[i][j] = stato;
					}
				}
				//System.out.print("|" + carattere);
			}// fine ciclo j
			//System.out.println("|");
		}// fine cicloi
		return stato_campo;
	};

	public int[][] getStatoPosizionamento() {
		int[][] stato;
		stato = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				stato[i][j] = casella[i][j].getVerso();
			}
		}
		return stato;
	}

	/**
	 * Funzione che mi serve in stampaCampo per assegnare un carattere visivo
	 * 
	 * @param {int} stato
	 * @return {String} carattere
	 */
	public String cercaCarattere(int stato) {
		String carattere = " ";
		if (stato == 1)
			carattere = "O";
		if (stato == 2)
			carattere = "X";
		if (stato == 3)
			carattere = "@";
		return carattere;
	};

	/**
	 * Lista navi non colpite
	 * 
	 * @return {ArrayList<Nave>} navi "sane"
	 */
	public ArrayList<Nave> getNavi() {
		return navi;
	}

	/**
	 * Lista navi colpite
	 * 
	 * @return {ArrayList<Nave>} navi_colpire
	 */
	public ArrayList<Nave> getNaviColpite() {
		return navi;
	}

	/**
	 * Lista navi affondate
	 * 
	 * @return {ArrayList<Nave>} navi_affondate
	 */
	public ArrayList<Nave> getNaviAffondate() {
		return navi;
	}
}
