package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eventi.AscoltatoreBottoniMenu;

public class BarraMenu extends JMenuBar implements Serializable, ActionListener {

	JMenuItem salva;
	JMenuItem singlePlayer;
	JMenuItem multiPlayer;

	public BarraMenu() {
		super();
		// Crea un menu
		JMenu menu = new JMenu("Menu");
		add(menu);

		//
		// Creo gli itemes del menu
		//

		salva = new JMenuItem("Salva partita");

		//
		// Serve un ascoltatore
		//

		// item.addActionListener(actionListener);
		menu.add(salva);

		JMenu subMenu = new JMenu("new");

		singlePlayer = new JMenuItem("Single player");

		//
		// Serve un ascoltatore
		//

		// item.addActionListener(actionListener);
		subMenu.add(singlePlayer);
		multiPlayer = new JMenuItem("Multiplayers");

		//
		// Serve un ascoltatore
		//

		// item.addActionListener(actionListener);
		subMenu.add(multiPlayer);

		menu.add(subMenu);

		JMenu inf = new JMenu("?");
		add(inf);

		JMenuItem info = new JMenuItem("info");
		info.addActionListener(this);
		inf.add(info);

	}

	public void actionPerformed(ActionEvent arg0) {
		FrameInfo info = new FrameInfo();
		info.setVisible(true);
	}

	public void setA(AscoltatoreBottoniMenu a) {
		singlePlayer.addActionListener(a);
		salva.addActionListener(a);
		multiPlayer.addActionListener(a);
	}
}
