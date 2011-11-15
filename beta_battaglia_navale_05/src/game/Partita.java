package game;

import java.io.*;

import campo.*;
import eventi.AscoltatoreCambiaTurno;
import eventi.AscoltatoreDisponiNave;
import eventi.AscoltatoreGiocaTurno;
import UI.*;

public class Partita implements Serializable {

	Giocatore[] giocatori;
	Campo[] campo;

	MainFrame frame;
	FramoeCampoMio framecampo_mio;

	int turno;
	boolean fine_disposizione;

	AscoltatoreDisponiNave ascoltatore_disposizione;
	AscoltatoreGiocaTurno ascoltatore_gioca_turno;
	AscoltatoreCambiaTurno ascoltatore_cambio_turno;

	boolean partita_finita;

	public Partita(MainFrame frame) {

		this.frame = frame;

		framecampo_mio=new FramoeCampoMio();
		
		turno = 0;
		fine_disposizione = false;

		//
		// CAMPI
		//
		campo = new Campo[2];
		campo[0] = new Campo();
		campo[1] = new Campo();
		//

		//
		// GIOCATORI
		//
		giocatori = new Giocatore[2];
		giocatori[0] = new Giocatore("Giocatore_1", 0, campo);
		giocatori[1] = new Giocatore("Giocatore_2", 1, campo);
		//

		//
		// ASCOLTATORI
		//
		// Per fine disposizione:
		ascoltatore_disposizione = new AscoltatoreDisponiNave(this);
		// Per fine turno
		ascoltatore_gioca_turno = new AscoltatoreGiocaTurno(this);
		// Per fine schermo cambio-turno
		ascoltatore_cambio_turno = new AscoltatoreCambiaTurno(this);
		//

		// Partit finita
		partita_finita = false;

		// FRAME
		// frame = new MainFrame();

	}

	public void start() {
		frame.setVisible(true);
		this.disponiNavi();
	}

	public void restart(Salvataggio salvataggio) {
		System.out.println("---------------------------");
		System.out.println("RESTART");
		System.out.println("---------------------------");

		this.giocatori = salvataggio.giocatori;
		this.campo = salvataggio.campo;
		this.turno = salvataggio.turno;
		this.fine_disposizione = true;
		this.giocaTurno();

		//
		// Faccio un repaint di FRAME solo perché se no posso avere un ritardo
		// del caricamento delle immagini delle icone dei label del campo.
		// Risulterebbero degli spazi senza icona in alcuni punti.
		// Così risolvo però.
		//

		frame.repaint();
	}

	/**
	 * Fa comparire il pannello per la disposizione delle navi
	 * 
	 * @return {boolean} disposizione_navi_successo
	 */
	public boolean disponiNavi() {
		if (!fine_disposizione) {
			frame.disponiNave(giocatori[turno], ascoltatore_disposizione);
			if (turno == 0) {
				turno = 1;
			} else {
				turno = 0;
				fine_disposizione = true;
			}
			return true;
		} else {
			this.giocaTurno();
			return false;
		}
	}

	/**
	 * Fa comparire il pannello per il gioco del tutno
	 */
	public void giocaTurno() {

		// salvaPartita();
		//
		// Se aggiungo un campo in giocaTurno devo aumentare la LARGHEZZA
		//
		// frame.setBounds(50, 50, 1600, 600);
		//


		Giocatore giocatore = giocatori[turno];

		frame.giocaTurno(giocatori[turno], ascoltatore_gioca_turno);
		
		
		framecampo_mio.setVisible(true);
		framecampo_mio.aggiorna(giocatori[turno]);
	}

	/**
	 * Fa comparire il pannello per il cambio del turno
	 */
	public void cambiaTurno() {
		Giocatore giocatore = giocatori[turno];

		//
		// Mostra la schermata di cambio turno, e cambia turno
		// solo se giocatore.fineTurno == TRUE
		// cioè se ha trovato una casella vuota, altrimenti continua:
		// in questo caso la funzione passa gli "if" e non fa niente
		//
		if (giocatore.fine_turno) {
			framecampo_mio.setVisible(false);
			
			if (turno == 0)
				turno = 1;
			else
				turno = 0;
			giocatore.fine_turno = false;
			frame.cambiaTurno(giocatori[turno], ascoltatore_cambio_turno);
		}
		if (giocatore.fine_partita) {
			System.out.println("--------------------------");
			System.out.println("PARTITA FINE PARTITA");
			System.out.println("--------------------------");
		}
		// this.giocaTurno();
	}

	public void salvaPartita() {
		if (!partita_finita) {

			Salvataggio s = new Salvataggio(campo, giocatori, turno);

			campo[0].stampaCampo(false);
			campo[1].stampaCampo(false);

			ObjectOutputStream oss;
			try {
				oss = new ObjectOutputStream(new FileOutputStream("prova_09"));
				oss.writeObject(s);
				oss.close();
			} catch (Exception e) {
				System.out.println("---------------------");
				System.out.println("ERRORE SALVATAGGIO: " + e.toString());
				System.out.println("---------------------");
			}
		}
	}

	public void cancellaPartita() {
		if (!partita_finita) {

			Salvataggio s = new Salvataggio(campo, giocatori, turno);

			s.fine_partita=true;
			
			campo[0].stampaCampo(false);
			campo[1].stampaCampo(false);

			ObjectOutputStream oss;
			try {
				oss = new ObjectOutputStream(new FileOutputStream("prova_09"));
				oss.writeObject(s);
				oss.close();
			} catch (Exception e) {
				System.out.println("---------------------");
				System.out.println("ERRORE SALVATAGGIO: " + e.toString());
				System.out.println("---------------------");
			}
		}
	}

	public boolean getFinita() {
		return partita_finita;
	}
}
