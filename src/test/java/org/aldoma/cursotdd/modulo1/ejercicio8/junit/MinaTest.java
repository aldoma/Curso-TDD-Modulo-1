/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio8.junit;

import org.aldoma.cursotdd.modulo1.ejercicio8.Mina;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link Mina}
 * 
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( { "static-method", "nls" } )
public class MinaTest {

	/**
	 * Test method for {@link Mina} con una mina vacía (primer ejemplo del ejercicio).
	 */
	@Test
	public void testMina_PrimerEjemplo() {
		final Mina testClass = new Mina( "" );

		Assert.assertEquals( 0, testClass.getNumDiamantes() );
	}

	/**
	 * Test method for {@link Mina} con una mina que es un diamante (segundo ejemplo del ejercicio).
	 */
	@Test
	public void testMina_SegundoEjemplo() {
		final Mina testClass = new Mina( "<>" );

		Assert.assertEquals( 1, testClass.getNumDiamantes() );
	}

	/**
	 * Test method for {@link Mina} con una mina con dos diamantes (segundo ejemplo del ejercicio).
	 */
	@Test
	public void testMina_TercerEjemplo() {
		final Mina testClass = new Mina( "<<>>" );

		Assert.assertEquals( 2, testClass.getNumDiamantes() );
	}

	/**
	 * Test method for {@link Mina} con una mina con tres diamantes (tercer ejemplo del edificio).
	 */
	@Test
	public void testMina_CuartoEjemplo() {
		final Mina testClass = new Mina( "<><<>><<" );

		Assert.assertEquals( 3, testClass.getNumDiamantes() );
	}
}
