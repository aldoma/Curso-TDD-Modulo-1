/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Alberto Dominguez Matamoros
 * @version 0.0.2
 */
public final class Ejercicio3 {
	private Ejercicio3() {
	}

	/**
	 * Tal y como se solicita en el ejercicio, este método construye tantas listas como elementos
	 * tenga la lista pasada como argumento. Cada una de las listas creada ha de tener todos los
	 * elementos de la lista original menos uno.
	 * 
	 * @param aList
	 *        Lista de enteros utilizados para la creación de las listas devueltas.
	 * @return Una lista con todas las listas creadas.
	 * @since 0.0.1
	 */
	public static List<List<Integer>> generateLists( final List<Integer> aList ) {

		final List<List<Integer>> result = new ArrayList<List<Integer>>( aList.size() );

		final Integer[] asArray = aList.toArray( new Integer[aList.size()] ); //NOPMD
		for (int i = 0; i < aList.size(); i++) {
			final Integer[] newArray = new Integer[aList.size() - 1]; //NOPMD
			for (int j = 0, k = 0; j < asArray.length; j++) { //NOPMD
				if (j != i) {
					newArray[k++] = asArray[j]; //NOPMD
				}
			}
			result.add( Arrays.asList( newArray ) ); //NOPMD
		}
		/*
		 * En la revisión del código, Javier Jesús Gutiérrez Rodríguez (javierj@us.es) descubrio que
		 * los test estaban condicionados por el orden en que se generaban las listas. Para evitar
		 * esto desordeno las listas.
		 */
		Collections.shuffle( result );
		return result;
	}
}
