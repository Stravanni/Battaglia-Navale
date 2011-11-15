package campo;

import java.io.Serializable;
import java.util.ArrayList;

public class GiocatoreComputer extends Giocatore implements Serializable {

	ArrayList<Posizione> bersaglio;
	ArrayList<Posizione> cercato;

	boolean bersaglio_usato;

	int affondate;

	Posizione pos;
	Posizione new_pos;

	public GiocatoreComputer(String nome, int turno, Campo[] campo) {
		super(nome, turno, campo);

		posizionaRandom();

		bersaglio_usato = false;

		bersaglio = new ArrayList<Posizione>();
		cercato = new ArrayList<Posizione>();

	}

	// Colpisce autormaticamente.

	public void colpisci_auto_1() {

		// pos = new Posizione(0, 0);
		boolean gia_colpito = true;

		if (bersaglio.isEmpty()) {
			// pos = random();
			bersaglio_usato = false;

			while (gia_colpito) {

				pos = random();

				for (int h = 0; h < cercato.size(); h++) {
					if (cercato.get(h).getX() == pos.getX()
							&& cercato.get(h).getY() == pos.getY()) {
						System.out
								.println("gia cercato     -_------__-----_----__-----_------__-----_----__-----_------__-----_----__----");
						gia_colpito = true;
						break;
					} else
						gia_colpito = false;
				}
				if (cercato.isEmpty())
					gia_colpito = false;
			}

		} else {

			bersaglio_usato = true;

			while (gia_colpito) {

				pos = bersaglio.remove(0);

				for (int h = 0; h < cercato.size(); h++) {
					if (cercato.get(h).getX() == pos.getX()
							&& cercato.get(h).getY() == pos.getY()) {
						gia_colpito = true;

						System.out
								.println("gia cercato     -_------__-----_----__-----_------__-----_----__-----_------__-----_----__---- 2");
						break;
					} else {
						gia_colpito = false;
					}
					if (cercato.isEmpty())
						gia_colpito = false;
				}

				// pos = bersaglio.remove(0);

				bersaglio_usato = true;
				// solo se l'ultimo elemento  contenuto anch'esso nella lista
				// // dei
				// cercati:
				if (bersaglio.isEmpty() && gia_colpito) {
					while (gia_colpito) {

						pos = random();

						for (int h = 0; h < cercato.size(); h++) {
							if (cercato.get(h).getX() == pos.getX()
									&& cercato.get(h).getY() == pos.getY()) {
								gia_colpito = true;

								System.out
										.println("gia cercato     -_------__-----_----__-----_------__-----_----__-----_------__-----_----__----3");
								break;
							} else
								gia_colpito = false;
						}
						if (cercato.isEmpty())
							gia_colpito = false;
					}
				}
			}
		}

		// for (int h = 0; h < cercato.size(); h++) {
		// System.out.println("@@@@@@@@@@@@@ " + cercato.get(h).getX() + ";"
		// + cercato.get(h).getY() + " == " + pos.getX() + ";"
		// + pos.getY());
		// }

		cercato.add(pos);

		//
		//
		//
		// RISULTATO colpisci.
		//

		int ris = campo_avversario.colpisci(pos);

		System.out.println("Coomputer:::::::::::::::::::::::::::::::::::: ");
		System.out.println("Coomputer:::::::::::::::::::::::::::::::::::: ");
		System.out.println("Coomputer: " + pos.getX() + ";" + pos.getY()
				+ " RIS: " + ris + "             b: " + bersaglio_usato);
		System.out.println("Coomputer:::::::::::::::::::::::::::::::::::: ");
		System.out.println("Coomputer:::::::::::::::::::::::::::::::::::: ");

		//
		//
		//

		for (int i = 0; i < cercato.size(); i++) {
			System.out.println("¡¡¡ " + cercato.get(i).getX() + ";"
					+ cercato.get(i).getY());
		}

		if (ris == -1) {
			if (cercato.contains(pos)) {
				System.out
						.println(":::::::::::::::::::::::::::::::::::: CONTIENE ::::::::::::::::::::::::::::::::::::");
			}
		}

		if (ris == 1) {
			if (pos.getX() > 0 && pos.getX() < 9 && pos.getY() > 0
					&& pos.getY() < 9) {

				// aggiungo le caselle nelle 4 direzioni
				new_pos = new Posizione(pos.getX() - 1, pos.getY());
				bersaglio.add(new_pos);
				new_pos = new Posizione(pos.getX() + 1, pos.getY());
				bersaglio.add(new_pos);
				new_pos = new Posizione(pos.getX(), pos.getY() - 1);
				bersaglio.add(new_pos);
				new_pos = new Posizione(pos.getX(), pos.getY() + 1);
				bersaglio.add(new_pos);

				// fine for }
				// fine if
				if (pos.getX() == 0 && pos.getX() == 9) {
					int xx;
					// int yy;
					if (pos.getX() == 0) {
						xx = 1;
					} else {
						xx = 8;
					}
					if (pos.getY() != 0 && pos.getY() != 9) {
						new_pos = new Posizione(xx, pos.getY() - 1);
						bersaglio.add(new_pos);
						new_pos = new Posizione(xx, pos.getY() + 1);
						bersaglio.add(new_pos);
					} else {
						if (pos.getY() == 0) {
							Posizione new_pos = new Posizione(xx,
									pos.getY() + 1);
							bersaglio.add(new_pos);
						} else {
							Posizione new_pos = new Posizione(xx,
									pos.getY() - 1);
							bersaglio.add(new_pos);
						}
					}
				}
				// fine if (bordo XX)

				// // //

				if (pos.getY() == 0 && pos.getY() == 9) {

					int yy;
					if (pos.getY() == 0) {
						yy = 1;
					} else {
						yy = 8;
					}
					if (pos.getX() != 0 && pos.getX() != 9) {
						Posizione new_pos = new Posizione(pos.getX() - 1, yy);
						bersaglio.add(new_pos);
						new_pos = new Posizione(pos.getX() + 1, yy);
						bersaglio.add(new_pos);
					} else {
						if (pos.getX() == 0) {
							Posizione new_pos = new Posizione(pos.getX() + 1,
									yy);
							bersaglio.add(new_pos);
						} else {
							Posizione new_pos = new Posizione(pos.getX() - 1,
									yy);
							bersaglio.add(new_pos);
						}
					}
				}
				// fine if (bordo YY)

				// // //
				stampaBersagli();
			}
		} else if (ris == 2) {
			bersaglio.clear();

			System.out.println("Coomputer: ********************");
			System.out.println("Coomputer: ********************");
			System.out.println("Coomputer: AFFONDATA");
			System.out.println("Coomputer: ********************");
			System.out.println("Coomputer: ********************");
		}

		else if (ris == 3) {
			this.fine_partita = true;
		}

		// // Rimuovo dai bersagli quelli sulla retta del bersaglio mancato.
		// // //
		// Dovrebbe essere giˆ a posto questa parte. // // PerchŽ quando 
		// == 0 viene
		// tolto e non genera altre // posizione_bersaglio. //

		if (bersaglio_usato && ris == 0) {

			// Posizione pos_tmp;
			//
			// for (int i = 0; i < bersaglio.size(); i++) {
			// pos_tmp = bersaglio.get(i);
			// // X
			// if (pos_tmp.getX() == pos.getX()) {
			// if (pos_tmp.getY() > pos.getY()) {
			// for (int j = 0; j < bersaglio.size(); j++) {
			// if (bersaglio.get(j).getY() > pos.getY())
			// bersaglio.remove(j);
			// }
			// }
			// if (pos_tmp.getY() < pos.getY()) {
			// for (int j = 0; j < bersaglio.size(); j++) {
			// if (bersaglio.get(j).getY() < pos.getY())
			// bersaglio.remove(j);
			// }
			// }
			// }
			// // Y
			// if (pos_tmp.getY() == pos.getY()) {
			// if (pos_tmp.getX() > pos.getX()) {
			// for (int j = 0; j < bersaglio.size(); j++) {
			// if (bersaglio.get(j).getX() > pos.getX())
			// bersaglio.remove(j);
			// }
			// }
			// if (pos_tmp.getX() < pos.getX()) {
			// for (int j = 0; j < bersaglio.size(); j++) {
			// if (bersaglio.get(j).getX() < pos.getX())
			// bersaglio.remove(j);
			// }
			// }
			// }
			// }
			// stampaBersagli();
		}

	}

	private Posizione random() {
		Posizione pos = new Posizione((int) (Math.random() * 10) % 10,
				(int) (Math.random() * 10) % 10);
		return pos;
	}

	private void stampaBersagli() {
		for (int i = bersaglio.size(); i == 0; i--) {
			System.out.println(bersaglio.get(i).getX() + ";"
					+ bersaglio.get(i).getY());
		}
	}

}
