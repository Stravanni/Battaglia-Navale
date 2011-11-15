package UI;

import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;

import campo.*;
import eventi.AscoltatoreDisponiNave;

public class PannelloDisponiNavi extends PannelloCampo implements
		MouseListener, ActionListener, Serializable {

	Giocatore giocatore;
	AscoltatoreDisponiNave ascoltatore;

	JButton bottone_fine, bottone_orientamento;

	ArrayList<Nave> navi_da_posizionare;
	Nave nave;
	boolean verticale;

	LabelNaveDisposizione label_nave;

	public PannelloDisponiNavi(Giocatore giocatore,
			AscoltatoreDisponiNave ascoltatore) {

		this.ascoltatore = ascoltatore;
		this.giocatore = giocatore;

		c.gridx = 3;
		c.gridy = 1;
		c.weighty = 0.8;
		c.anchor = GridBagConstraints.EAST;

		label_nave = new LabelNaveDisposizione("");

		// l_vuoto_2 = new JLabel("");
		// add(p_vuoto_2, c);
		add(label_nave, c);
		//

		navi_da_posizionare = giocatore.getNaviDaPosizionare();
		verticale = true;

		bottone_fine = new JButton("FINE");
		bottone_fine.setEnabled(false);

		bottone_fine.addActionListener(ascoltatore);

		c = new GridBagConstraints();

		c.weightx = 0.9;
		c.weighty = 0.2;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		add(bottone_fine, c);

		//

		bottone_orientamento = new JButton("vert.");
		bottone_orientamento.addActionListener(this);

		c.weightx = 0.1;
		// c.weighty = 0;
		// c.fill = GridBagConstraints.NONE;
		// c.anchor = GridBagConstraints.EAST;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;

		add(bottone_orientamento, c);

		// l_vuoto_2.setText("portaerei [4]");

		//
		//
		//

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				label[i][j].addMouseListener(this);
			}
		}

		nave = navi_da_posizionare.get(0);
		label_nave.cambiaNave(nave.getLunghezza());
		// l_vuoto_2.setText("dim_nave: " + nave.getLunghezza());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(((LabelCasella) e.getSource()).getX() + " - "
				+ ((LabelCasella) e.getSource()).getY());
		if (!navi_da_posizionare.isEmpty()) {
			nave = navi_da_posizionare.get(0);
			if (navi_da_posizionare.size() > 1)
				label_nave
						.cambiaNave(navi_da_posizionare.get(1).getLunghezza());
			// navi_da_posizionare.remove(0);
			Posizione pos = ((LabelCasella) e.getSource()).getPos();
			if (!giocatore.posizionaNave(nave, pos, verticale)) {
				navi_da_posizionare.add(0, nave);
				label_nave
						.cambiaNave(navi_da_posizionare.get(0).getLunghezza());
			} else {

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						label[i][j].cambiaImm(giocatore.getCampoMio()
								.getStatoPosizionamento());
						label[i][j].repaint();
					}
				}

			}
			if (navi_da_posizionare.isEmpty()) {
				bottone_fine.setEnabled(true);
				label_nave.setText("");
			}
		} else {
			bottone_fine.setEnabled(true);
			System.out.println(" Finito di posizionare ");
			System.out.println(" Finito di posizionare ");
			System.out.println(" Finito di posizionare ");
		}

	}

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

	//
	// Questa classe implementa anche un ACTIONLISTENR per il bottone
	// orizzontale/verticale.
	//
	public void actionPerformed(ActionEvent arg0) {
		if (verticale) {
			bottone_orientamento.setText("oriz.");
			verticale = false;
		} else {
			bottone_orientamento.setText("vert.");
			verticale = true;
		}
	}
}
