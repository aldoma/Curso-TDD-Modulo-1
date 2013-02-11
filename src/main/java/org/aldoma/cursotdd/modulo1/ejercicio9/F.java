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
public class F { //NOPMD
	/**
	 * @return Una lista de objetos de clase {@link C} correctamente inicializados.
	 */
	@SuppressWarnings( { "nls" } )
	public static List<C> creaLista() { //NOPMD
		final List<String> letras = new ArrayList<String>();
		for (final char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) { //NOPMD
			letras.add( String.valueOf( c ) );
		}
		final List<C> toReturn = new ArrayList<C>( letras.size() );

		Collections.shuffle( letras );
		for (final String cs : letras) { //NOPMD
			toReturn.add( new C( cs ) ); //NOPMD
		}
		return toReturn;
	}
}
