/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio4.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link java.util.ArrayList#lastIndexOf(Object)} y
 * {@link java.util.ArrayList#remove(Object)} mediante la técnica de cobertura de decisiones.
 * 
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( { "nls", "javadoc" } )
public class ArrayListTest {
	private static final String CERO = "Cero";
	private static final String UNO = "Uno";
	private static final String DOS = "Dos";
	private static final String TRES = "Tres";
	private static final String CUATRO = "Cuatro";
	private static final String CINCO = "Cinco";
	private static final String SEIS = "Seis";
	private static final String SIETE = "Siete";
	private static final String OCHO = "Ocho";

	@SuppressWarnings( "PMD.BeanMembersShouldSerialize" )
	private final List<String> testClass = new ArrayList<String>( Arrays.asList( ArrayListTest.CERO, ArrayListTest.UNO,
			ArrayListTest.DOS, ArrayListTest.TRES, ArrayListTest.CUATRO, ArrayListTest.CINCO, ArrayListTest.SEIS,
			ArrayListTest.SIETE, ArrayListTest.OCHO ) );

	@Test
	public void testIndexOf_PrimerNull() {
		final ArrayList<String> localTestClass = new ArrayList<String>( testClass );
		localTestClass.set( 3, null );

		Assert.assertEquals( 3, localTestClass.indexOf( null ) );
	}

	@Test
	public void testIndexOf_PrimerNullNoExisteNinguno() {
		Assert.assertEquals( -1, testClass.indexOf( null ) );
	}

	@Test
	public void testIndexOf_UnElemento() {
		Assert.assertEquals( 5, testClass.indexOf( ArrayListTest.CINCO ) );
	}

	@Test
	public void testIndexOf_UnElementoInexistente() {
		Assert.assertEquals( -1, testClass.indexOf( "quince" ) );
	}

	@Test
	public void testRemove_BorrarElPrimerNull() {
		final ArrayList<String> localTestClass = new ArrayList<String>( testClass );
		localTestClass.set( 3, null );
		localTestClass.set( 7, null );
		final ArrayList<String> resultClass = new ArrayList<String>( Arrays.asList( ArrayListTest.CERO,
				ArrayListTest.UNO, ArrayListTest.DOS, ArrayListTest.CUATRO, ArrayListTest.CINCO, ArrayListTest.SEIS,
				null, ArrayListTest.OCHO ) );

		Assert.assertTrue( localTestClass.remove( null ) );
		Assert.assertEquals( localTestClass, resultClass );
	}

	@Test
	public void testRemove_BorrarElPrimerNullNoExisteNinguno() {
		final ArrayList<String> resultClass = new ArrayList<String>( testClass );

		Assert.assertFalse( testClass.remove( null ) );
		Assert.assertEquals( testClass, resultClass );
	}

	@Test
	public void testRemove_BorrarUnElemento() {
		final ArrayList<String> localTestClass = new ArrayList<String>( testClass );
		localTestClass.set( 4, null );
		localTestClass.set( 7, null );
		final ArrayList<String> resultClass = new ArrayList<String>( Arrays.asList( ArrayListTest.CERO,
				ArrayListTest.UNO, ArrayListTest.DOS, null, ArrayListTest.CINCO, ArrayListTest.SEIS, null,
				ArrayListTest.OCHO ) );

		Assert.assertTrue( localTestClass.remove( ArrayListTest.TRES ) );
		Assert.assertEquals( localTestClass, resultClass );
	}

	@Test
	public void testRemove_BorrarUnElementoInexistente() {
		final ArrayList<String> localTestClass = new ArrayList<String>( testClass );
		localTestClass.set( 4, null );
		localTestClass.set( 7, null );
		final ArrayList<String> resultClass = new ArrayList<String>( localTestClass );

		Assert.assertFalse( localTestClass.remove( "trece" ) );
		Assert.assertEquals( localTestClass, resultClass );
	}

}
