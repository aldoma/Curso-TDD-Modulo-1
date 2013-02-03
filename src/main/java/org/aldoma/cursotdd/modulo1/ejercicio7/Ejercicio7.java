/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio7;

/**
 * @author Alberto Dominguez Matamoros
 */
public class Ejercicio7 {
	final static String OPEN_LABEL_FORMAT = "[color=\"%s\"]"; //$NON-NLS-1$
	final static String CLOSE_LABEL = "[/color]"; //$NON-NLS-1$
	@SuppressWarnings( "nls" )
	final static String[] COLORS = new String[] { "red", "blue", "yellow", "white", "black", "magenta", "cyan" };

	/**
	 * Implementación del Ejercicio 7 del primer módulo.
	 * <p>
	 * Añade a cada carácter de una cadena de texto pasada como argumento los tag necesarios para
	 * ser mostrado en un color diferente del resto.
	 * 
	 * @param s
	 *            Cadena a la que añadir los códigos de formato.
	 * @return La cadena de entrada despues de añadir las etiquetas de color correspondientes.
	 */
	public static String decorarTexto( final String s ) {
		final StringBuilder result = new StringBuilder( s.length()
				* (Ejercicio7.OPEN_LABEL_FORMAT.length() + Ejercicio7.CLOSE_LABEL.length()) );
		for (int i = 0, j = 0; i < s.length(); i++, j++) {
			if (j >= Ejercicio7.COLORS.length) {
				j = 0;
			}
			result.append( String.format( Ejercicio7.OPEN_LABEL_FORMAT, Ejercicio7.COLORS[j] ) ).append( s.charAt( i ) )
					.append( Ejercicio7.CLOSE_LABEL );
		}
		return result.toString();
	}
}
