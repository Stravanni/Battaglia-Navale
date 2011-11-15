/**
 * In questa classe gestisco anche gli ascoltatori delle caselle (label) quindi MouseListener
 * La classe stessa estende MouseListener
 */

package UI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import campo.*;
import eventi.AscoltatoreGiocaTurno;

public class PannelloGiocaTurno extends PannelloCampo implements MouseListener,
		Serializable {

	Giocatore giocatore;
	JButton b1;

	LabelCasella[][] label2;

	boolean fine_turno;

	AscoltatoreGiocaTurno ascoltatore;

	PannelloGiocaTurno aa;

	// public boolean fine_turno, fine_partita;

	public PannelloGiocaTurno(Giocatore giocatore,
			AscoltatoreGiocaTurno ascoltatore) {

		this.giocatore = giocatore;

		this.ascoltatore = ascoltatore;
		aa = this;

		fine_turno = false;
		// fine_partita = false;
		c.gridx = 3;
		c.gridy = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.EAST;

		l_vuoto_2 = new JLabel("");
		// add(p_vuoto_2, c);
		add(l_vuoto_2, c);
		//

		b1 = new JButton(giocatore.getNome());
		b1.setEnabled(false);

		//
		// Ascoltatore è un MouseListener, non un actionListener...
		//
		// b1.addActionListener(ascoltatore);

		c.weightx = 0;
		c.weighty = 0.2;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		add(b1, c);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				label[i][j].addMouseListener(this);
				label[i][j].addMouseListener(ascoltatore);
			}
		}

		//
		//
		//
		// AGGIORNA CASELLE
		aggiornaCaselle();
		//
		// Secondo campo (MIO)
		//
		// con frame di LARGHEZZA doppia
		//

		// label2 = new LabelCasella[10][10];
		//
		// c.fill = GridBagConstraints.BOTH;
		// c.gridwidth = 1;
		// c.gridx = 2;
		// c.gridy = 1;
		// c.weightx = 1;
		// c.weighty = 0.3;
		// c.ipadx = 1;
		// c.ipady = 1;
		//
		// // PannelloCampo pannello_campo = new PannelloCampo();
		// JPanel pannello_campo2 = new JPanel();
		//
		// pannello_campo2.setLayout(new GridLayout(10, 10, 0, 0));
		// for (int i = 0; i < 10; i++) {
		// for (int j = 0; j < 10; j++) {
		// label2[i][j] = new LabelCasella(i, j);
		// pannello_campo2.add(label2[i][j]);
		// }
		// }
		//
		// pannello_campo2.setBackground(Color.white);
		// add(pannello_campo2, c);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		//
		// INIBISCO GLI ASCOLTATORI
		//
		((LabelCasella) e.getSource()).removeMouseListener(aa);
		((LabelCasella) e.getSource()).removeMouseListener(ascoltatore);
		//
		//

		Posizione pos = ((LabelCasella) e.getSource()).getPos();
		System.out.println("P) " + giocatore.getNome() + " colpisco casella: "
				+ ((LabelCasella) e.getSource()).getX() + ";"
				+ ((LabelCasella) e.getSource()).getY());
		int ris = giocatore.colpisci(pos);
		System.out.println("P.GT) ris: " + ris);
		if (ris < 0) {
			System.out.println("P.GT) GIA' CERCATO");
		} else if (ris == 0) {
			System.out.println("P.GT) VUOTO, FINE TURNO");
			aggiornaCaselle();
			fine_turno = true;
			giocatore.fine_turno = true;

		} else if (ris == 1 || ris == 2) {
			if (ris == 1) {

				System.out.println("P.GT) COLPITO");
			} else {

				System.out.println("P.GT) AFFONDATO");
			}

			// AGGIORNA CASELLE
			aggiornaCaselle();

		} else if (ris == 3) {
			System.out.println("P.GT) FINE PARTITA");
			giocatore.fine_partita = true;
			// AGGIORNA CASELLE
			aggiornaCaselle();
			// fine_partita = true;
		}
	}

	public void aggiornaCaselle() {
		//
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				label[i][j].cambiaSfondo(giocatore.getCampoAvversario()
						.getStato(true)[i][j]);
				label[i][j].repaint();

				//
				// INIBISCO GLI ASCOLTATORI
				//
				// Se le caselle sono già state cercate!!!
				//

				int s = giocatore.getCampoAvversario().getStato(true)[i][j];
				if (s == 1 || s == 2 || s == 3) {
					label[i][j].removeMouseListener(this);
					label[i][j].removeMouseListener(ascoltatore);
				}
			}
		}
	};

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
