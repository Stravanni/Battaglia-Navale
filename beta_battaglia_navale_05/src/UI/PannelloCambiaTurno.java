package UI;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.*;
import eventi.*;
import campo.*;

public class PannelloCambiaTurno extends JPanel implements Serializable {

	public PannelloCambiaTurno(Giocatore giocatore,
			AscoltatoreCambiaTurno ascoltatore) {

		super();
		setBackground(new Color(255, 255, 240));
		System.out.println("P.CT) Turno di " + giocatore.getNome());
		JButton bottone_cambio_turno = new JButton(giocatore.getNome()
				+ " PRONTO");

		bottone_cambio_turno.addActionListener(ascoltatore);

		add(bottone_cambio_turno);
	}

}
