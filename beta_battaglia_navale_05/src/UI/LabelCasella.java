package UI;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

import campo.Posizione;

public class LabelCasella extends JLabel implements Serializable {

	private int x;
	private int y;
	// immagine di partenza "casella_da_scoprire".
	private Image im0;
	//
	// In realtˆ servono solo 2 top, 2 bottom 2centri.
	//
	private Image[] top;
	private Image[] center;
	private Image[] bottom;

	// private static String[] immagine = { "top_NORTH", "top_EST", "top_SOUTH",
	// "top_WEST", "bottom_NORTH", "bottom_EST", "bottom_SOUTH",
	// "bottom_WEST", "cental_ORIZONTAL_1", "cental_VERTICAL_1",
	// "cental_ORIZONTAL_2", "cental_VERTICAL_2" };

	// imm0
	MediaTracker m0;
	Toolkit t0;
	// altre
	MediaTracker[] m;
	Toolkit[] t;

	//
	// Immagini di GIOCO
	//
	private Image vuota, colpita, affondata, hidden;
	MediaTracker m_sfondo;
	Toolkit t_sfondo;

	public LabelCasella(int x, int y) {
		super();

		//
		//
		//

		m_sfondo = new MediaTracker(this);
		t_sfondo = Toolkit.getDefaultToolkit();

		vuota = t_sfondo.getImage("bn_gif/vuoto.gif");
		m_sfondo.addImage(vuota, 1);

		colpita = t_sfondo.getImage("bn_gif/colpito.gif");
		m_sfondo.addImage(vuota, 2);

		affondata = t_sfondo.getImage("bn_gif/affondato.gif");
		m_sfondo.addImage(affondata, 3);
		hidden = t_sfondo.getImage("bn_gif/hidden.gif");
		m_sfondo.addImage(hidden, 4);

		try {
			m_sfondo.waitForAll();
		} catch (InterruptedException e) {
		}
		//
		//
		//

		top = new Image[2];
		center = new Image[2];
		bottom = new Image[2];
		m = new MediaTracker[3];
		t = new Toolkit[3];

		t0 = Toolkit.getDefaultToolkit();
		m0 = new MediaTracker(this);
		im0 = t0.getImage("bn_gif/da_scoprire.gif");
		m0.addImage(im0, 1);
		try {
			m0.waitForAll();
		} catch (InterruptedException e) {
		}

		//

		t[0] = Toolkit.getDefaultToolkit();
		m[0] = new MediaTracker(this);
		top[0] = t[0].getImage("bn_gif/top_WEST.gif");
		m[0].addImage(top[0], 1);
		top[1] = t[0].getImage("bn_gif/top_NORTH.gif");
		m[0].addImage(top[1], 2);
		try {
			m[0].waitForAll();
		} catch (InterruptedException e) {
		}

		t[1] = Toolkit.getDefaultToolkit();
		m[1] = new MediaTracker(this);
		center[0] = t[1].getImage("bn_gif/center_ORIZONTAL_1.gif");
		m[1].addImage(center[0], 1);
		center[1] = t[1].getImage("bn_gif/center_VERTICAL_1.gif");
		m[1].addImage(center[1], 2);
		try {
			m[1].waitForAll();
		} catch (InterruptedException e) {
		}

		t[2] = Toolkit.getDefaultToolkit();
		m[2] = new MediaTracker(this);

		bottom[0] = t[2].getImage("bn_gif/bottom_EST.gif");
		m[2].addImage(bottom[0], 1);
		bottom[1] = t[2].getImage("bn_gif/bottom_SOUTH.gif");
		m[2].addImage(bottom[1], 2);
		try {
			m[2].waitForAll();
		} catch (InterruptedException e) {
		}

		//
		//
		//

		this.x = x;
		this.y = y;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gg = getGraphics();
		gg.drawImage(im0, 0, 0, 42, 42, null);
	}

	/**
	 * Cambia l'immagine durante la disposizione
	 * 
	 * @param {int [10}[10]} stato
	 */
	public void cambiaImm(int[][] stato) {
		// System.out.println(x+" "+y);
		if (stato[x][y] == 10) {
			im0 = top[1];
			System.out.println("TESTA: " + x + " " + y);
		}
		if (stato[x][y] == 00) {
			im0 = top[0];
		}
		if (stato[x][y] == 12) {
			im0 = bottom[1];
			System.out.println("CODA: " + x + " " + y);
		}
		if (stato[x][y] == 02) {
			im0 = bottom[0];
		}
		if (stato[x][y] == 11) {
			System.out.println("TRONCO: " + x + " " + y);
			im0 = center[1];
		}
		if (stato[x][y] == 01) {
			im0 = center[0];
		}
	};

	/**
	 * Cambia l'immagine durante il GIOCO
	 */
	public void cambiaSfondo(int stato) {
		int i = stato;
		if (i == 1) {
			im0 = vuota;
			// System.out.println("");
		}
		if (i == 2) {
			im0 = colpita;
		}
		if (i == 3) {
			im0 = affondata;
		}
		if (i == -1) {
			im0 = hidden;
		}
	}

	/**
	 * Cambia l'immagine durante il GIOCO
	 */
	public void cambiaSfondoMio(int stato) {
		int i = stato;
		if (i == 1) {
			im0 = vuota;
			// System.out.println("");
		}
		if (i == 2) {
			im0 = colpita;
		}
		if (i == 3) {
			im0 = affondata;
		}
	}

	/**
	 * Coordinata x casella.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Coordinata y casella.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Posizione casella.
	 * 
	 * @return {Posizione} pos
	 */
	public Posizione getPos() {
		return new Posizione(x, y);
	}
}
