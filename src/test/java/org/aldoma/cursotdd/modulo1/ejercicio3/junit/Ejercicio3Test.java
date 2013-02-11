/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio3.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aldoma.cursotdd.modulo1.ejercicio3.Ejercicio3;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link Ejercicio3}
 * 
 * @author Alberto Dominguez Matamoros
 * @version 0.0.2
 */
public class Ejercicio3Test {
	@SuppressWarnings( "boxing" )
	final private List<Integer> array = Arrays.asList( 5, 8, 6, 7, 2, 4, 9, 3, 1 ); //NOPMD: No es un bean

	/**
	 * Test method for {@link Ejercicio3#generateLists(List)}.
	 * <p>
	 * Verifica que la lista retornada tenga el mismo número de listas que la lista pasada como
	 * argumento.
	 * 
	 * @since 0.0.1
	 */
	@Test
	public void testGenerateLists_NumeroDeListas() {

		Assert.assertEquals( array.size(), Ejercicio3.generateLists( array ).size() ); //NOPMD
	}

	/**
	 * Test method for {@link Ejercicio3#generateLists(List)}.
	 * <p>
	 * Verifica que cada una las listas retornadas tengan la longitud especificada en el ejercicio.
	 * Esto es, un elemento menos que la lista pasada como argumento.
	 * 
	 * @since 0.0.1
	 */
	@Test
	@SuppressWarnings( "PMD.DataflowAnomalyAnalysis" )
	public void testGenerateLists_TamañoDeLasListas() {
		final List<List<Integer>> result = Ejercicio3.generateLists( array );

		for (final List<Integer> list : result) { //NOPMD: Es un bug de PMD
			Assert.assertEquals( array.size() - 1, list.size() );
		}
	}

	/**
	 * Test method for {@link Ejercicio3#generateLists(List)}.
	 * <p>
	 * Verifica que ninguna de las listas retornadas tengan algun valor que no esté en la lista
	 * original.
	 * 
	 * @since 0.0.1
	 */
	@Test
	@SuppressWarnings( "PMD.DataflowAnomalyAnalysis" )
	public void testGenerateLists_NoElementosExtranios() {
		final List<List<Integer>> result = Ejercicio3.generateLists( array );

		for (final List<Integer> list : result) { //NOPMD: Es un bug de PMD
			Assert.assertTrue( array.containsAll( list ) );
		}
	}

	/**
	 * Test method for {@link Ejercicio3#generateLists(List)}.
	 * <p>
	 * Verifica que en la lista retornada exista cada posible sublista creada según las
	 * especificaciones del ejercicio.
	 * 
	 * @since 0.0.1
	 */
	@Test
	@SuppressWarnings( "PMD.DataflowAnomalyAnalysis" )
	public void testGenerateLists_PruebaReconstrucion() {
		final List<List<Integer>> result = Ejercicio3.generateLists( array );

		for (int j = 0; j < array.size(); j++) {
			final List<Integer> tmp = new ArrayList<Integer>( array ); //NOPMD
			tmp.remove( j );
			Assert.assertTrue( result.contains( tmp ) ); //NOPMD
		}
	}

	/**
	 * Test method for {@link Ejercicio3#generateLists(List)}.
	 * <p>
	 * Llamada con un {@code null}.
	 * 
	 * @since 0.0.2
	 */
	@SuppressWarnings( "static-method" )
	@Test( expected = NullPointerException.class )
	public void testGenerateLists_LlamadaConNull() {
		Ejercicio3.generateLists( null );
	}

	/**
	 * Test method for {@link Ejercicio3#generateLists(List)}.
	 * <p>
	 * Llamada con un lista vacía.
	 * 
	 * @since 0.0.2
	 */
	@Test
	public void testGenerateLists_LlamadaConListaVacia() {
		final List<List<Integer>> result = Ejercicio3.generateLists( new ArrayList<Integer>( array.subList( 0, 0 ) ) );

		Assert.assertTrue( result.isEmpty() );
	}

	/**
	 * Test method for {@link Ejercicio3#generateLists(List)}.
	 * <p>
	 * Llamada con un lista que contiene un sólo elemento.
	 * 
	 * @since 0.0.2
	 */
	@Test
	public void testGenerateLists_LlamadaConUnSoloElemento() {
		final List<List<Integer>> result = Ejercicio3.generateLists( new ArrayList<Integer>( array.subList( 0, 1 ) ) );

		Assert.assertEquals( 1, result.size() );
		Assert.assertTrue( result.get( 0 ).isEmpty() ); //NOPMD
	}
}
