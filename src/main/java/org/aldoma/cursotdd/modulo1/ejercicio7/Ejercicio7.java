/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio7;

/**
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( { "nls", "PMD.UseSingleton" } )
public class Ejercicio7 {
	private final static String OPEN_LABEL_FORMAT = "[color=\"%s\"]";
	private final static String CLOSE_LABEL = "[/color]";
	private final static String[] COLORS = new String[] { "red", "blue", "yellow", "white", "black", "magenta", "cyan" };

	/**
	 * Implementación del Ejercicio 7 del primer módulo.
	 * <p>
	 * Añade a cada carácter de una cadena de texto pasada como argumento los tag necesarios para
	 * ser mostrado en un color diferente del resto.
	 * 
	 * @param string
	 *        Cadena a la que añadir los códigos de formato.
	 * @return La cadena de entrada despues de añadir las etiquetas de color correspondientes.
	 */
	public static String decorarTexto( final String string ) {
		final StringBuilder result = new StringBuilder( string.length()
				* (Ejercicio7.OPEN_LABEL_FORMAT.length() + Ejercicio7.CLOSE_LABEL.length()) ); //NOPMD
		for (int i = 0, j = 0; i < string.length(); i++, j++) { //NOPMD
			if (j >= Ejercicio7.COLORS.length) {
				j = 0;
			}
			result.append( String.format( Ejercicio7.OPEN_LABEL_FORMAT, Ejercicio7.COLORS[j] ) ) //NOPMD
					.append( string.charAt( i ) ).append( Ejercicio7.CLOSE_LABEL );
		}
		return result.toString(); //NOPMD
	}
}
