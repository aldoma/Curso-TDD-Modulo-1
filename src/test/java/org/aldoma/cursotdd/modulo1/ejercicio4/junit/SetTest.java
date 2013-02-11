/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio4.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link java.util.Set#removeAll(java.util.Collection)} mediante la técnica de
 * cobertura de decisiones.
 * 
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( { "boxing", "static-method", "javadoc" } )
public class SetTest {
	@Test( expected = NullPointerException.class )
	public void testRemoveAll_ColeccionABorrarEsNull() {
		final Set<Integer> testClass = new HashSet<Integer>( Arrays.asList( 5, 8, 6, 7, 2, 4, 9, 3, 1 ) );

		Assert.assertFalse( testClass.removeAll( null ) );
	}

	@Test
	public void testRemoveAll_ColeccionABorrarEsMasPequenia() {
		final Set<Integer> testClass = new HashSet<Integer>( Arrays.asList( 5, 8, 6, 7, 2, 4, 9, 3, 1 ) );
		final List<Integer> toRemove = Arrays.asList( 1, 7, 10, 6, 12, -4 );
		final Set<Integer> resultClass = new HashSet<Integer>( Arrays.asList( 5, 8, 2, 4, 9, 3 ) );

		Assert.assertTrue( testClass.removeAll( toRemove ) );
		Assert.assertTrue( testClass.equals( resultClass ) );
	}

	@Test
	public void testRemoveAll_ColeccionABorrarEsMasPequeniaYDisjunta() {
		final Set<Integer> testClass = new HashSet<Integer>( Arrays.asList( 5, 8, 6, 7, 2, 4, 9, 3, 1 ) );
		final List<Integer> toRemove = Arrays.asList( 15, -7, 10, 38, 12, -4 );
		final Set<Integer> resultClass = new HashSet<Integer>( testClass );

		Assert.assertFalse( testClass.removeAll( toRemove ) );
		Assert.assertTrue( testClass.equals( resultClass ) );
	}

	@Test
	public void testRemoveAll_ColeccionABorrarEsMasGrande() {
		final Set<Integer> testClass = new HashSet<Integer>( Arrays.asList( 1, 7, 10, 6, 12, -4 ) );
		final List<Integer> toRemove = Arrays.asList( 5, 8, 6, 7, 2, 4, 9, 3, 1 );
		final Set<Integer> resultClass = new HashSet<Integer>( Arrays.asList( 10, 12, -4 ) );

		Assert.assertTrue( testClass.removeAll( toRemove ) );
		Assert.assertTrue( testClass.equals( resultClass ) );
	}

	@Test
	public void testRemoveAll_ColeccionABorrarEsMasGrandeYDisjunta() {
		final Set<Integer> testClass = new HashSet<Integer>( Arrays.asList( 1, 7, 10, 6, 12, -4 ) );
		final List<Integer> toRemove = Arrays.asList( 5, 8, 27, 2, 4, 9, 3, 11 );
		final Set<Integer> resultClass = new HashSet<Integer>( testClass );

		Assert.assertFalse( testClass.removeAll( toRemove ) );
		Assert.assertTrue( testClass.equals( resultClass ) );
	}

	@Test
	public void testRemoveAll_ColeccionABorrarEsIdentica() {
		final Set<Integer> testClass = new HashSet<Integer>( Arrays.asList( 1, 7, 10, 6, 12, -4 ) );
		final List<Integer> toRemove = new ArrayList<Integer>( testClass );

		Assert.assertTrue( testClass.removeAll( toRemove ) );
		Assert.assertEquals( 0, testClass.size() );
	}

	@Test
	public void testRemoveAll_ColeccionABorrarEsUnSuperConjunto() {
		final Set<Integer> testClass = new HashSet<Integer>( Arrays.asList( 1, 7, 10, 6, 12, -4 ) );
		final List<Integer> toRemove = new ArrayList<Integer>( testClass );
		toRemove.addAll( Arrays.asList( 2, 23, 5, 8, 9 ) );

		Assert.assertTrue( testClass.removeAll( toRemove ) );
		Assert.assertEquals( 0, testClass.size() );
	}
}
