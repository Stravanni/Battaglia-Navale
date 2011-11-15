package campo;

import java.io.Serializable;

public class Posizione implements Serializable {
	private int x;
	private int y;

	/**
	 * Oggetto posizione
	 * 
	 * @param {int} x
	 * @param {int} y
	 */
	public Posizione(int x, int y) {
		this.x = x;
		this.y = y;
	};

	/**
	 * Ottieni coordinata x
	 * 
	 * @return {int} x
	 */
	public int getX() {
		return x;
	};

	/**
	 * Ottieni coordinata y
	 * 
	 * @return {int} y
	 */
	public int getY() {
		return y;
	};

	public Posizione random() {
		// int x, y;
		Posizione pos;
		x = (int) (Math.random() * 10) % 10;
		y = (int) (Math.random() * 10) % 10;
		pos = new Posizione(x, y);
		return pos;
	};

}
