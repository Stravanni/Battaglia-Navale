package UI;

import java.awt.Container;
import java.io.Serializable;

import javax.swing.*;

import campo.Giocatore;
import eventi.AscoltatoreBottoniMenu;
import eventi.AscoltatoreCambiaTurno;
import eventi.AscoltatoreDisponiNave;
import eventi.AscoltatoreGiocaTurno;

public class MainFrame extends JFrame implements I_MainFrame, Serializable {

	// AscoltatoreDisponiNave ascoltatore_1;
	// AscoltatoreGiocaTurno ascoltatore_2;
	Container contenitore;
	public BarraMenu menu;

	public MainFrame() {
		super();

		//
		// Chiude quando esce
		//
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(470, 50, 800, 630);
		setResizable(false);

		//
		// Container per l'aggiornamento della grafica
		//
		contenitore = getContentPane();
		
		menu = new BarraMenu();
		setJMenuBar(menu);
	}

	public void disponiNave(Giocatore giocatore,
			AscoltatoreDisponiNave ascoltatore) {
		// PANNELLO DISPONI
		// PannelloCampo pp = new PannelloCampo();
		PannelloDisponiNavi pannello_disponi_navi = new PannelloDisponiNavi(
				giocatore, ascoltatore);

		contenitore.removeAll();
		contenitore.add(pannello_disponi_navi);
		contenitore.validate();
	}

	public void giocaTurno(Giocatore giocatore,
			AscoltatoreGiocaTurno ascoltatore) {
		// PANNELLO GIOCA TURNO
		contenitore.removeAll();

		PannelloGiocaTurno pannello_gioca_turno = new PannelloGiocaTurno(
				giocatore, ascoltatore);
		contenitore.add(pannello_gioca_turno);

		contenitore.validate();

	}

	public void cambiaTurno(Giocatore giocatore,
			AscoltatoreCambiaTurno ascoltatore) {
		// PANNELLO CAMBIO TURNO
		PannelloCambiaTurno pannello_cambia_turno = new PannelloCambiaTurno(
				giocatore, ascoltatore);

		contenitore.removeAll();
		contenitore.add(pannello_cambia_turno);
		contenitore.validate();
	}
	
}
