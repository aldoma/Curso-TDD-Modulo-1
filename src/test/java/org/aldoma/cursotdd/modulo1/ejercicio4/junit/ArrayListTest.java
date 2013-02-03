/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio4.junit;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link java.util.ArrayList#lastIndexOf(Object)} y
 * {@link java.util.ArrayList#remove(Object)} mediante la técnica de cobertura de decisiones.
 * 
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( { "nls", "static-method", "javadoc" } )
public class ArrayListTest {
	@Test
	public void testIndexOf_PrimerNull() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", null, "cinco",
				"seis", null, "ocho" ) );

		Assert.assertEquals( 3, testClass.indexOf( null ) );
	}

	@Test
	public void testIndexOf_PrimerNullNoExisteNinguno() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", "cuatro",
				"cinco", "seis", "siete", "ocho" ) );

		Assert.assertEquals( -1, testClass.indexOf( null ) );
	}

	@Test
	public void testIndexOf_UnElemento() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", null, "cinco",
				"seis", null, "ocho" ) );

		Assert.assertEquals( 4, testClass.indexOf( "cinco" ) );
	}

	@Test
	public void testIndexOf_UnElementoInexistente() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", null, "cinco",
				"seis", null, "ocho" ) );

		Assert.assertEquals( -1, testClass.indexOf( "quince" ) );
	}

	@Test
	public void testRemove_BorrarElPrimerNull() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", null, "cinco",
				"seis", null, "ocho" ) );
		final ArrayList<String> resultClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", "cinco",
				"seis", null, "ocho" ) );

		Assert.assertTrue( testClass.remove( null ) );
		Assert.assertEquals( testClass, resultClass );
	}

	@Test
	public void testRemove_BorrarElPrimerNullNoExisteNinguno() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", "cinco",
				"seis", "ocho" ) );
		final ArrayList<String> resultClass = new ArrayList<String>( testClass );

		Assert.assertFalse( testClass.remove( null ) );
		Assert.assertEquals( testClass, resultClass );
	}

	@Test
	public void testRemove_BorrarUnElemento() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", null, "tres",
				"cinco", "seis", null, "ocho" ) );
		final ArrayList<String> resultClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", null, "tres",
				"cinco", "seis", null, "ocho" ) );

		Assert.assertTrue( testClass.remove( "tres" ) );
		Assert.assertEquals( testClass, resultClass );
	}

	@Test
	public void testRemove_BorrarUnElementoInexistente() {
		final ArrayList<String> testClass = new ArrayList<String>( Arrays.asList( "Uno", "dos", "tres", null, "tres",
				"cinco", "seis", null, "ocho" ) );
		final ArrayList<String> resultClass = new ArrayList<String>( testClass );

		Assert.assertFalse( testClass.remove( "trece" ) );
		Assert.assertEquals( testClass, resultClass );
	}

}
