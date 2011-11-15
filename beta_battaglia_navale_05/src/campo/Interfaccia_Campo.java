package campo;

/**
 * 
 * @author giovannisimonini
 * 
 */
public interface Interfaccia_Campo {

	/**
	 * Deve permettere il posizionamento di una nave
	 * 
	 * @param nave
	 * @param posizione
	 * @param verticale
	 * @return
	 */
	public boolean posizionaNave(Nave nave, Posizione posizione,
			boolean verticale);

	/**
	 * Deve permettere di colpire una casella
	 * 
	 * @param pos
	 * @return 
	 */
	public int colpisci(Posizione pos);
};
