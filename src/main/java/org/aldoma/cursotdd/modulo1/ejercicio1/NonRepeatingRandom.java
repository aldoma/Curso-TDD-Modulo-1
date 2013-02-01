/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Genera números aleatorios enteros y no repetidos dentro de un intervalo previamente definido.
 * <p>
 * Esta clase es un <em>wrapper</em> alrededor de un generador de números aleatorios con tres
 * características específicas:
 * <ul>
 * <li>Los números pesudoaleatorios generados son enteros.</li>
 * <li>Los números pseudoaleatorios generados se encuentran en un intervalo cerrado / abierto
 * especificado durante la creación de la instancia. Es decir, para cada número {@code v} retornado
 * se debe cumplir:<em><blockquote>lowerLimit <= v < upperLimit</blockquote></em></li>
 * <li>Los números pseudoaleatorios son únicos para cada instancia de la clase.</li>
 * </ul>
 * Consecuencia de todas las características anteriores es que el conjunto de números aleatorios que
 * puede generar es finito (su tamaño está definido por la diferencia entre el límite superior y el
 * inferior del intervalo y puede ser averiguado consultado la propiedad {@link #size()}). Por
 * tanto, si invocamos a la función {@link #next()} de una determinada instancia un número de veces
 * superior a su tamaño obtendremos una excepción de tipo {@link NoSuchElementException}
 * indicándonos que la instancia en cuestión del generador se encuentra "agotada". Adicionalmente,
 * la propiedad {@link #isExhausted()} nos permite averiguar esto sin necesidad de "consumir" un
 * valor del conjunto.
 * 
 * @author Alberto Dominguez Matamoros
 * @version 0.0.1
 */
public class NonRepeatingRandom {
	/**
	 * Límite inferior (incluido) del rango de enteros a retornar por {@link #next()}.
	 * 
	 * @since 0.0.1
	 */
	final private int LowerLimit;

	/**
	 * Límite superior (excluido) del rango de enteros a retornar por {@link #next()}.
	 * 
	 * @since 0.0.1
	 */
	final private int UpperLimit;

	/**
	 * Lista de valores únicos correspondientes al intervalo [LowerLimit, UpperLimit) en el orden en
	 * que van a ser retornados por la función {@link #next()}.
	 * <p>
	 * La lista es desordenada (barajada) mediante {@link Collections#shuffle(List)} en el
	 * {@link #NonRepeatingRandom(int, int) Constructor}.
	 * 
	 * @since 0.0.1
	 */
	final private List<Integer> theList;

	/**
	 * Índice interno al elemento de la lista que devolverá la próxima llamada a la función
	 * {@link #next()}.
	 * 
	 * @since 0.0.1
	 */
	private int nextIndex = 0;

	/**
	 * Constructor con inicialización explícita de los límites inferior y superior del intervalo.
	 * <p>
	 * Dado que las especificaciones del problema hablan de un intervalo cerrado / abierto, el valor
	 * del límite inferior está incluido aunque no así el superior.
	 * 
	 * @param lowerLimit
	 *        Límite inferior (incluido)
	 * @param upperLimit
	 *        Límite superior (excluido)
	 * @throws IllegalArgumentException
	 *         si {@code lowerLimit > upperLimit}.
	 * @since 0.0.1
	 */
	public NonRepeatingRandom(	final int lowerLimit,
								final int upperLimit )
			throws IllegalArgumentException {

		/* Verificamos que los argumentos sean correctos */
		if (lowerLimit > upperLimit) {
			/*
			 * No confiamos en la excepción generada por el constructor de ArrayList porque es un
			 * detalle de implementación.
			 */
			throw new IllegalArgumentException( "lowerLimit > upperLimit???" ); //$NON-NLS-1$
		}

		LowerLimit = lowerLimit;
		UpperLimit = upperLimit;

		theList = new ArrayList<Integer>( UpperLimit - LowerLimit );
		for (int i = LowerLimit; i < UpperLimit; i++) {
			theList.add( Integer.valueOf( i ) );
		}
		Collections.shuffle( theList );

		/*
		 * Temporal. Este código habrá de ser eliminado en el futuro junto con la variable miembro
		 * maxCapacity.
		 */
		maxCapacity = theList.size();
	}

	/**
	 * Retorna el número de elementos máximos que puede devolver el objeto antes de "agotarse". Es
	 * la diferencia entre los límites superior e inferior.
	 * 
	 * @return Tamaño total del conjunto de números pesudoaletaros generables por esta instancia.
	 * @since 0.0.2
	 * @see #isExhausted()
	 */
	public int size() {
		return theList.size();
	}

	/**
	 * Indica si la instancia se encuentra agotada.
	 * <p>
	 * Entendemos que una instancia de esta clase se encuentra "agotada" cuando ha retornado la
	 * totalidad de los elementos que es capaz de generar con los criterios de creación
	 * especificados. Invocar al método {@link #next()} sobre una instancia agotada genera un
	 * excepción de tipo {@link NoSuchElementException}.
	 * 
	 * @return {@code true} si la instancia se encuentra agotada, {@code false} en caso contrario.
	 * @since 0.0.2
	 */
	public boolean isExhausted() {
		return nextIndex >= size();
	}

	/**
	 * Devuelve el siguiente número pseudoaleatorio dentro del rango expecificado en el constructor.
	 * 
	 * @return El siguiente número pseudoaletorio de esta secuencia.
	 * @throws NoSuchElementException
	 *         Si no existen más elementos disponibles.
	 * @since 0.0.1
	 */
	public int next()
			throws NoSuchElementException {
		try {
			return theList.get( nextIndex++ ).intValue();
		}
		catch (final IndexOutOfBoundsException ex) {
			throw new NoSuchElementException( ex.getLocalizedMessage() );
		}
	}

	/**
	 * Número de elementos máximos que puede devolver el objeto antes de "agotarse". Es la
	 * diferencia entre los límites superior e inferior.
	 * 
	 * @since 0.0.1
	 * @deprecated Desde la 0.0.2 usar {@link #size()} en su lugar.
	 */
	@Deprecated
	public final int maxCapacity;
}
