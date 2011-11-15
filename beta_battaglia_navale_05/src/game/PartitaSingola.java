package game;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import UI.FramoeCampoMio;
import UI.MainFrame;

import campo.*;
import eventi.AscoltatoreDisponiNave;
import eventi.AscoltatoreGiocaTurno;

public class PartitaSingola implements Serializable {

	Campo[] campo;

	public Giocatore giocatore;
	public GiocatoreComputer_copia computer;

	private MainFrame frame;

	public FramoeCampoMio framecampo_mio;
	
	int turno;
	boolean fine_disposizione;

	AscoltatoreDisponiNave ascoltatore_disposizione;
	AscoltatoreGiocaTurno ascoltatore_gioca_turno;

	boolean partita_finita;

	public PartitaSingola(MainFrame frame) {
		
		this.frame = frame;
		
		framecampo_mio = new FramoeCampoMio();

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
		giocatore = new Giocatore("Giocatore_normale", 0, campo);
		computer = new GiocatoreComputer_copia("Computer", 1, campo);

		//
		// ASCOLTATORI
		//
		// Per fine disposizione:
		ascoltatore_disposizione = new AscoltatoreDisponiNave(this);
		// Per fine turno
		ascoltatore_gioca_turno = new AscoltatoreGiocaTurno(this);
	}

	// START
	public void start() {
		frame.setVisible(true);
		this.disponiNavi();
	}

	// RESTART
	public void restart(Salvataggio salvataggio) {
		System.out.println("---------------------------");
		System.out.println("RESTART");
		System.out.println("---------------------------");

		this.giocatore = salvataggio.giocatore_singolo;
		this.computer = salvataggio.computer;
		this.campo = salvataggio.campo;
		// this.turno = salvataggio.turno;
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

	public void disponiNavi() {

		frame.disponiNave(giocatore, ascoltatore_disposizione);
		computer.posizionaRandom();
		// giocaTurno();

	}

	/**
	 * Fa comparire il pannello per il gioco del tutno
	 */
	public void giocaTurno() {
		
		//salvaPartita();
		//
		// Se aggiungo un campo in giocaTurno devo aumentare la LARGHEZZA
		//
		// frame.setBounds(50, 50, 1600, 600);
		//
		if (giocatore.fine_partita || computer.fine_partita) {
			System.out.println("--------------------------");
			System.out.println("--------------------------");
			System.out.println("--------------------------");
			if (computer.fine_partita)
				System.out.println("FINE --- vinto COMPUTER ");
			else
				System.out.println("FINE --- vinto Giocatore ");
			partita_finita = true;
			System.out.println("--------------------------");
			System.out.println("--------------------------");
			System.out.println("--------------------------");
		} else {
			frame.giocaTurno(giocatore, ascoltatore_gioca_turno);
			// computer.colpisci_auto();
			framecampo_mio.aggiorna(giocatore);
			framecampo_mio.setVisible(true);
			
		}
	}

	public void salvaPartita() {
		if (!partita_finita) {

			Salvataggio s = new Salvataggio(campo, giocatore, computer);

			// campo[0].stampaCampo(false);
			// campo[1].stampaCampo(false);

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

			Salvataggio s = new Salvataggio(campo, giocatore, computer);

			s.fine_partita = true;

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

}
