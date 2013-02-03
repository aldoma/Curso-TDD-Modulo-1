/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio6.junit;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import org.aldoma.cursotdd.modulo1.ejercicio6.Ejercicio6;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link Ejercicio6}
 * 
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( "boxing" )
public class Ejercicio6Test {
	final SortedSet<Integer> firstSet = new TreeSet<Integer>( Arrays.asList( 1, 2, 3, 5, 8, 13, 21 ) );
	final SortedSet<Integer> secondSet = new TreeSet<Integer>( Arrays.asList( 1, 3, 5, 7, 9, 11, 13 ) );
	final SortedSet<Integer> emptySet = new TreeSet<Integer>();

	/**
	 * Test method for {@link Ejercicio6#Union(SortedSet, SortedSet)}.
	 */
	@Test
	public final void testUnion() {
		final SortedSet<Integer> resultList = new TreeSet<Integer>( Arrays.asList( 1, 2, 3, 5, 7, 8, 9, 11, 13, 21 ) );

		final SortedSet<Integer> unionList = Ejercicio6.Union( firstSet, secondSet );

		Assert.assertEquals( resultList, unionList );
	}

	/**
	 * Test method for {@link Ejercicio6#Union(SortedSet, SortedSet)} con el primer argumento vacío.
	 */
	@Test
	public final void testUnion_PrimerArgumentoVacio() {
		final SortedSet<Integer> resultList = new TreeSet<Integer>( secondSet );

		final SortedSet<Integer> unionList = Ejercicio6.Union( emptySet, secondSet );

		Assert.assertEquals( resultList, unionList );
	}

	/**
	 * Test method for {@link Ejercicio6#Union(SortedSet, SortedSet)} con el segundo argumento
	 * vacío.
	 */
	@Test
	public final void testUnion_SegundoArgumentoVacio() {
		final SortedSet<Integer> resultList = new TreeSet<Integer>( firstSet );

		final SortedSet<Integer> unionList = Ejercicio6.Union( firstSet, emptySet );

		Assert.assertEquals( resultList, unionList );
	}

	/**
	 * Test method for {@link Ejercicio6#Union(SortedSet, SortedSet)} con los dos argumentos
	 * iguales.
	 */
	@Test
	public final void testUnion_DosArgumentosIguales() {
		final SortedSet<Integer> resultList = new TreeSet<Integer>( firstSet );

		final SortedSet<Integer> unionList = Ejercicio6.Union( firstSet, firstSet );

		Assert.assertEquals( resultList, unionList );
	}

	/**
	 * Test method for {@link Ejercicio6#Union(SortedSet, SortedSet)} con los dos argumentos vacíos.
	 */
	@Test
	public final void testUnion_DosArgumentosVacios() {
		final SortedSet<Integer> unionList = Ejercicio6.Union( emptySet, emptySet );

		Assert.assertEquals( emptySet, unionList );
	}
}
