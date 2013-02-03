/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio2.junit;

import org.aldoma.cursotdd.modulo1.ejercicio2.Triangle;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link Triangle}.
 * <p>
 * En la versión 0.0.2 modificamos los casos de prueba a sugerencia de <a
 * href="mailto:javierj@us.es">Javier Jesús Gutiérrez Rodríguez</a> para "rotar" los valores pasados
 * como argumentos del constructor y de esta forma realizar unas pruebas mucho más exhaustivas.
 * 
 * @author Alberto Dominguez Matamoros
 * @version 0.0.2
 */
@SuppressWarnings( "static-method" )
public class TriangleTest {

	/**
	 * Test method for {@link Triangle#getClassByLength()}.
	 * 
	 * @since 0.0.1
	 */
	@Test
	public final void testGetClassByLength_NoEsUnTriangulo() {
		final int[] lados = new int[] { 1, 2, 5 };

		Triangle testSUP = new Triangle( lados[0], lados[1], lados[2] );
		Assert.assertSame( Triangle.ClassByLength.NO_ES_UN_TRIÁNGULO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[1], lados[2], lados[0] );
		Assert.assertSame( Triangle.ClassByLength.NO_ES_UN_TRIÁNGULO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[2], lados[0], lados[1] );
		Assert.assertSame( Triangle.ClassByLength.NO_ES_UN_TRIÁNGULO, testSUP.getClassByLength() );
	}

	/**
	 * Test method for {@link Triangle#getClassByLength()}.
	 * <p>
	 * Caso límite.
	 * 
	 * @since 0.0.1
	 */
	@Test
	public final void testGetClassByLength_NoEsUnTriangulo_CasoLimite() {
		final int[] lados = new int[] { 2, 3, 5 };

		Triangle testSUP = new Triangle( lados[0], lados[1], lados[2] );
		Assert.assertSame( Triangle.ClassByLength.NO_ES_UN_TRIÁNGULO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[1], lados[2], lados[0] );
		Assert.assertSame( Triangle.ClassByLength.NO_ES_UN_TRIÁNGULO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[2], lados[0], lados[1] );
		Assert.assertSame( Triangle.ClassByLength.NO_ES_UN_TRIÁNGULO, testSUP.getClassByLength() );
	}

	/**
	 * Test method for {@link Triangle#getClassByLength()}.
	 * 
	 * @since 0.0.1
	 */
	@Test
	public final void testGetClassByLength_Escaleno() {
		final int[] lados = new int[] { 3, 4, 5 };

		Triangle testSUP = new Triangle( lados[0], lados[1], lados[2] );
		Assert.assertSame( Triangle.ClassByLength.ESCALENO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[1], lados[2], lados[0] );
		Assert.assertSame( Triangle.ClassByLength.ESCALENO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[2], lados[0], lados[1] );
		Assert.assertSame( Triangle.ClassByLength.ESCALENO, testSUP.getClassByLength() );
	}

	/**
	 * Test method for {@link Triangle#getClassByLength()}.
	 * 
	 * @since 0.0.1
	 */
	@Test
	public final void testGetClassByLength_Isosceles() {
		final int[] lados = new int[] { 3, 3, 5 };

		Triangle testSUP = new Triangle( lados[0], lados[1], lados[2] );
		Assert.assertSame( Triangle.ClassByLength.ISÓSCELES, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[1], lados[2], lados[0] );
		Assert.assertSame( Triangle.ClassByLength.ISÓSCELES, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[2], lados[0], lados[1] );
		Assert.assertSame( Triangle.ClassByLength.ISÓSCELES, testSUP.getClassByLength() );
	}

	/**
	 * Test method for {@link Triangle#getClassByLength()}.
	 * 
	 * @since 0.0.1
	 */
	@Test
	public final void testGetClassByLength_Equilatero() {
		final int[] lados = new int[] { 3, 3, 3 };

		Triangle testSUP = new Triangle( lados[0], lados[1], lados[2] );
		Assert.assertSame( Triangle.ClassByLength.EQUILÁTERO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[1], lados[2], lados[0] );
		Assert.assertSame( Triangle.ClassByLength.EQUILÁTERO, testSUP.getClassByLength() );

		testSUP = new Triangle( lados[2], lados[0], lados[1] );
		Assert.assertSame( Triangle.ClassByLength.EQUILÁTERO, testSUP.getClassByLength() );

	}
}
