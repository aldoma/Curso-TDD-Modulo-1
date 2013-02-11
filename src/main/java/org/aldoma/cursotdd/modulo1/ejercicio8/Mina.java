/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio8;

/**
 * @author Alberto Dominguez Matamoros
 */
public class Mina { //NOPMD
	private final String theMina; //NOPMD: No es un bean

	/**
	 * Constructor con inicialización explícita de la theMina.
	 * 
	 * @param aMina
	 *        Cadena de carácteres que representa la theMina.
	 */
	public Mina( final String aMina ) {
		theMina = aMina;
	}

	/**
	 * Calcula y devuelve el número de diamantes existentes en la theMina.
	 * 
	 * @return número de diamantes en la theMina.
	 */
	@SuppressWarnings( "nls" )
	public int getNumDiamantes() {

		int numDiamantes = 0;
		int i = 0; //NOPMD
		String tmp = theMina;
		do {
			final String string = tmp.replace( "<>", "" ); //NOPMD
			i = (tmp.length() - string.length()) / 2; //NOPMD
			tmp = string; //NOPMD
			numDiamantes += i;
		}
		while (i > 0);
		return numDiamantes;
	}

}
