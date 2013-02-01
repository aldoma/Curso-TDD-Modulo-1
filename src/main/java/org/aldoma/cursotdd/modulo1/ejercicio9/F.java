/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Factoria para la creación de objetos de clase {@link C}
 * 
 * @author Alberto Dominguez Matamoros
 */
public class F {
	/**
	 * @return Una lista de objetos de clase {@link C} correctamente inicializados.
	 */
	public static List<C> creaLista() {
		final List<String> letras = new ArrayList<String>();
		for (final char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) { //$NON-NLS-1$
			letras.add( String.valueOf( c ) );
		}
		final List<C> toReturn = new ArrayList<C>( letras.size() );

		Collections.shuffle( letras );
		for (final String cs : letras) {
			toReturn.add( new C( cs ) );
		}
		return toReturn;
	}
}
