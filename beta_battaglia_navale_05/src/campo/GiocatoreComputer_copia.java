package campo;

import java.io.Serializable;
import java.util.ArrayList;

public class GiocatoreComputer_copia extends Giocatore implements Serializable {

	ArrayList<Posizione> bersaglio;
	ArrayList<Posizione> cercato;

	ArrayList<Posizione> pos_intorno;

	// s

	Posizione[][] posizioni_possibili;

	boolean bersaglio_usato;
	boolean secondo_colpo;

	int affondate;

	Posizione pos;
	Posizione new_pos;

	Posizione pos_vecchia;

	public GiocatoreComputer_copia(String nome, int turno, Campo[] campo) {
		super(nome, turno, campo);

		posizioni_possibili = new Posizione[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				posizioni_possibili[i][j] = new Posizione(i, j);
			}
		}

		// posizionaRandom();

		bersaglio_usato = false;
		secondo_colpo = false;

		bersaglio = new ArrayList<Posizione>();
		cercato = new ArrayList<Posizione>();

	}

	// Colpisce autormaticamente.

	public void colpisci_auto_1() {

		if (bersaglio.isEmpty()) {
			bersaglio_usato = false;
			secondo_colpo = false;
			pos = random();
			while (cercato.contains(pos)) {
				pos = random();
			}
		} else {
			bersaglio_usato = true;
			while (cercato.contains(pos)) {
				pos = bersaglio.remove(0);
				// almeno una pos dovrebbe non essere ancora stata cercata,
				// altrimenti sarebbe stata nave affondata ---> svuotato
				// l'ArrayList bersaglio.
			}
		}
		cercato.add(pos);
		int ris = campo_avversario.colpisci(pos);

		if (ris == -1) {
			if (cercato.contains(pos)) {
				System.out.println("COMPUTER) :::::::::::: Giˆ cercato");
			}
		}
		if (ris == 0) {
			if (cercato.contains(pos)) {
				System.out.println("COMPUTER) :::::::::::: VUOTO");
			}
			if (bersaglio.isEmpty())
				bersaglio = aggiorna();
		}
		if (ris == 1 && !secondo_colpo) {
			// per i turno dopo
			secondo_colpo = true;
			pos_vecchia = pos;

			pos_intorno = intorno(pos);

			// bersaglo = intorno
			while (!pos_intorno.isEmpty()) {
				bersaglio.add(pos_intorno.remove(0));
			}

		} else if (ris == 1 && secondo_colpo) {

			bersaglio.clear();

			if (pos.getX() > pos_vecchia.getX()) {
				new_pos = new Posizione(pos.getX() + 1, pos.getY());
				bersaglio.add(posizioni_possibili[new_pos.getX()][new_pos
						.getY()]);
			}

			if (pos.getY() > pos_vecchia.getY()) {
				new_pos = new Posizione(pos.getX(), pos.getY() + 1);
				bersaglio.add(posizioni_possibili[new_pos.getX()][new_pos
						.getY()]);

			}

			if (pos.getX() < pos_vecchia.getX()) {
				new_pos = new Posizione(pos.getX() - 1, pos.getY());
				bersaglio.add(posizioni_possibili[new_pos.getX()][new_pos
						.getY()]);
			}

			if (pos.getY() < pos_vecchia.getY()) {
				new_pos = new Posizione(pos.getX(), pos.getY() - 1);
				bersaglio.add(posizioni_possibili[new_pos.getX()][new_pos
						.getY()]);
			}
		}

		if (ris == 2) {
			bersaglio.clear();
			bersaglio = aggiorna();
			secondo_colpo = false;
		}

	}

	private Posizione random() {
		Posizione pos = new Posizione((int) (Math.random() * 10) % 10,
				(int) (Math.random() * 10) % 10);
		return posizioni_possibili[pos.getX()][pos.getY()];
	}

	private ArrayList<Posizione> aggiorna() {
		ArrayList<Posizione> posizioni = new ArrayList<Posizione>();

		int[][] stato = campo_avversario.getStato(true);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (stato[i][j] == 2) {
					for (int h = 0; h < intorno(posizioni_possibili[i][j])
							.size(); h++)
						posizioni
								.add(intorno(posizioni_possibili[i][j]).get(h));
				}
			}
		}
		return posizioni;
	}

	/**
	 * Trova le posizioni_intorno.
	 * 
	 * @param {Posizione} pos
	 * @return {ArrayList<Posizoine>} posizioni_intorno
	 */
	private ArrayList<Posizione> intorno(Posizione pos) {
		ArrayList<Posizione> posizioni = new ArrayList<Posizione>();
		Posizione new_pos = new Posizione(0, 0);
		if (pos.getX() > 0) {
			new_pos = new Posizione(pos.getX() - 1, pos.getY());
			posizioni.add(posizioni_possibili[new_pos.getX()][new_pos.getY()]);
		}

		if (pos.getX() < 9) {
			new_pos = new Posizione(pos.getX() + 1, pos.getY());
			posizioni.add(posizioni_possibili[new_pos.getX()][new_pos.getY()]);
		}

		if (pos.getY() < 9) {
			new_pos = new Posizione(pos.getX(), pos.getY() + 1);
			posizioni.add(posizioni_possibili[new_pos.getX()][new_pos.getY()]);
		}

		if (pos.getY() > 0) {
			new_pos = new Posizione(pos.getX(), pos.getY() - 1);
			posizioni.add(posizioni_possibili[new_pos.getX()][new_pos.getY()]);
		}

		System.out.println("intorno ");
		// for (int i = 0; i < posizioni.size(); i++) {
		// System.out.println("intorno " + i + ") " + posizioni.get(i).getX()
		// + ";" + posizioni.get(i).getY());
		// }
		return posizioni;
	}

	private void stampaBersagli() {
		for (int i = bersaglio.size(); i == 0; i--) {
			System.out.println(bersaglio.get(i).getX() + ";"
					+ bersaglio.get(i).getY());
		}
	}

}
