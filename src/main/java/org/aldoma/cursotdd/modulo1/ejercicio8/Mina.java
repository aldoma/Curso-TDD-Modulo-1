/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio8;

/**
 * @author Alberto Dominguez Matamoros
 */
public class Mina {
	final String mina;

	/**
	 * Constructor con inicialización explícita de la mina.
	 * 
	 * @param aMina
	 *            Cadena de carácteres que representa la mina.
	 */
	public Mina( final String aMina ) {
		this.mina = aMina;
	}

	/**
	 * Calcula y devuelve el número de diamantes existentes en la mina.
	 * 
	 * @return número de diamantes en la mina.
	 */
	@SuppressWarnings( "nls" )
	public int getNumDiamantes() {

		int numDiamantes = 0;
		int j = 0;
		String tmp = mina;
		do {
			final String s = tmp.replace( "<>", "" );
			j = (tmp.length() - s.length()) / 2;
			tmp = s;
			numDiamantes += j;
		}
		while (j > 0);
		return numDiamantes;
	}

}
