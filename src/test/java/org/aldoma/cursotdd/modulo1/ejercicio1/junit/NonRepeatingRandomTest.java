/**
 * Test Suite for {@link NonRepeatingRandom}.
 */
package org.aldoma.cursotdd.modulo1.ejercicio1.junit;

import java.util.HashSet;
import java.util.NoSuchElementException;

import org.aldoma.cursotdd.modulo1.ejercicio1.NonRepeatingRandom;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link NonRepeatingRandom}
 * 
 * @author Alberto Dominguez Matamoros
 * @version 0.0.2
 */
@SuppressWarnings( { "static-method", "nls" } )
public class NonRepeatingRandomTest {

	/**
	 * Verifica que ninguno de los valores retornados por las sucesivas llamadas a
	 * {@link NonRepeatingRandom#next()} se encuentren repetidos.
	 * 
	 * @since 0.0.1
	 */
	@Test
	public void valoresNoRepetidos() {
		final NonRepeatingRandom testClass = new NonRepeatingRandom( 20, 60 );

		final HashSet<Integer> comprobador = new HashSet<Integer>( testClass.size() );

		for (int i = 0; i < testClass.size(); i++) {
			Assert.assertTrue( comprobador.add( Integer.valueOf( testClass.next() ) ) );
		}
	}

	/**
	 * Fuerza la generación de una excepción de tipo {@link NoSuchElementException} llamando a la
	 * función {@link NonRepeatingRandom#next()} despues de que la instancia del SUT se encuentre
	 * agotada.
	 * 
	 * @since 0.0.1
	 */
	@Test( timeout = 1000 )
	public void generarUnaException() {
		final NonRepeatingRandom testClass = new NonRepeatingRandom( 3, 8 );

		while (!testClass.isExhausted()) {
			testClass.next();
		}

		try {
			testClass.next();
			Assert.fail( "La instancia se encuentra agotada -> debería generarse una excepción" );
		}
		catch (final NoSuchElementException ex) {
			// OK, es el comportamiento esperado
		}
	}

	/**
	 * Verifica que todos los números retornados se encuentren dentro del intevalo [LowerLimit,
	 * UpperLimit).
	 * 
	 * @since 0.0.1
	 */
	@Test
	public void valoresDentroIntervalo() {
		final int lowerLimit = 12;
		final int upperLimit = 81;

		final NonRepeatingRandom testClass = new NonRepeatingRandom( lowerLimit, upperLimit );

		for (int i = 0; i < testClass.size(); i++) {
			final int testValue = testClass.next();
			Assert.assertTrue( lowerLimit <= testValue && testValue < upperLimit );
		}
	}

	/**
	 * Test method for {@link NonRepeatingRandom#isExhausted()}
	 * 
	 * @since 0.0.2
	 */
	@Test
	public void testIsExhausted() {
		final NonRepeatingRandom theSUT = new NonRepeatingRandom( 45, 87 );

		for (int i = 0; i < theSUT.size() - 1; i++) {
			theSUT.next();
		}
		Assert.assertFalse( "Aun debería quedar un elemento en el conjunto", theSUT.isExhausted() );
		theSUT.next(); // Consumimos el último elemento
		Assert.assertTrue( "La intancia debería estar agotada.", theSUT.isExhausted() );
	}

	/**
	 * Test method for {@link NonRepeatingRandom#NonRepeatingRandom(int, int)} - Valores límite -
	 * Llamamos al constructor con los dos límites iguales.
	 * <p>
	 * La instancia no debería "contener" ningún elemento. <strong>¡Está agotada antes de ser
	 * utilizada!</strong> No parece un comportamiento muy útil pero es matemáticamente correcto ;-)
	 * 
	 * @since 0.0.2
	 */
	@Test
	public void testNonRepeatingRandom_DosLimitesIguales() {
		final int lowerLimit = 12;
		final int upperLimit = lowerLimit;

		final NonRepeatingRandom theSUT = new NonRepeatingRandom( lowerLimit, upperLimit );

		Assert.assertEquals( 0, theSUT.size() );
		Assert.assertTrue( theSUT.isExhausted() );
	}

	/**
	 * Test method for {@link NonRepeatingRandom#NonRepeatingRandom(int, int)} - Valores límite -
	 * Llamamos al constructor con el límite inferior mayor que el superior (
	 * {@code lowerLimit > upperLimit}).
	 * 
	 * @since 0.0.2
	 */
	@SuppressWarnings( "unused" )
	@Test( expected = IllegalArgumentException.class )
	public void testNonRepeatingRandom_LimitesInvertidos() {
		new NonRepeatingRandom( 49, 32 );
	}
}
