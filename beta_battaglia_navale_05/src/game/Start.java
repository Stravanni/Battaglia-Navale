package game;

import java.io.*;

import eventi.AscoltatoreBottoniMenu;

import UI.MainFrame;

public class Start {

	public Partita p;

	public PartitaSingola partita_singola;

	Salvataggio salvatggio;
	MainFrame frame;

	AscoltatoreBottoniMenu ascoltatore_bottone_menu;

	/**
	 * E' la classe che cestisce la partita
	 * 
	 * @param {MainFrame} frame
	 */
	public Start(MainFrame frame) {

		this.frame = frame;
		// nuovaPartita();
		frame.setVisible(true);
		salvatggio = new Salvataggio();

		ascoltatore_bottone_menu = new AscoltatoreBottoniMenu(this);
		frame.menu.setA(ascoltatore_bottone_menu);

	}

	public void nuovaPartita() {
		p = new Partita(frame);
		p.start();
		ascoltatore_bottone_menu.singola=false;
	}

	public void nuovaPartita(boolean singola) {
		partita_singola = new PartitaSingola(frame);
		partita_singola.start();
		ascoltatore_bottone_menu.singola=true;
		System.out.println("start) " + singola);
	}

	public void caricaPartita() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("prova_09"));
			salvatggio = (Salvataggio) ois.readObject();
		} catch (Exception e) {
			System.out.println("---------------------------");
			System.out.println("NON CARICATO::::::: " + e.toString());
			System.out.println("---------------------------");
		}
		if (!salvatggio.fine_partita) {

			if (salvatggio.singola) {
				nuovaPartita(true);
				partita_singola.restart(salvatggio);
				partita_singola.cancellaPartita();
			} else {
				nuovaPartita();
				p.restart(salvatggio);
				// salvatggio.fine_partita=true;
				p.cancellaPartita();
			}
		}

	}

	/**
	 * 
	 * MAIN
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		MainFrame frame = new MainFrame();

		Start start = new Start(frame);
		// start.nuovaPartita();
		start.caricaPartita();

		// start.nuovaPartita(true);
	}

}
