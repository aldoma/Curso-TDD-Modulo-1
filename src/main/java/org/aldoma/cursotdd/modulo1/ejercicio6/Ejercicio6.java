/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio6;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Alberto Dominguez Matamoros
 */
public class Ejercicio6 {

	/**
	 * Realiza la <em>unión</em> de dos conjuntos ordenados del mismo tipo
	 * 
	 * @param <E>
	 *            Tipo de los distintos conjuntos ordenados
	 * @param arg1
	 *            Primer conjunto ordenado
	 * @param arg2
	 *            Segundo conjunto ordenado
	 * @return un conjunto ordenado conteniendo todos los elementos de ambos argumentos de entrada.
	 */
	public static <E> SortedSet<E> Union(	final SortedSet<E> arg1,
											final SortedSet<E> arg2 ) {
		final SortedSet<E> union = new TreeSet<E>( arg1 );
		union.addAll( arg2 );
		return union;
	}
}
