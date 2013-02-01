/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio7.junit;

import org.aldoma.cursotdd.modulo1.ejercicio7.Ejercicio7;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Suite for {@link Ejercicio7}.
 * 
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( "static-method" )
public class Ejercicio7Test {

	/**
	 * Test method for {@link Ejercicio7#decorarTexto(String)}.
	 */
	@Test
	@SuppressWarnings( "nls" )
	public void testDecorarTexto() {
		final String expected = new String( "[color=\"red\"]P[/color]" + "[color=\"blue\"]r[/color]"
				+ "[color=\"yellow\"]u[/color]" + "[color=\"white\"]e[/color]" + "[color=\"black\"]b[/color]"
				+ "[color=\"magenta\"]a[/color]" );

		final String actual = Ejercicio7.decorarTexto( "Prueba" );

		Assert.assertEquals( expected, actual );
	}

	/**
	 * Test method for {@link Ejercicio7#decorarTexto(String)} con una cadena vacía.
	 */
	@Test
	@SuppressWarnings( "nls" )
	public void testDecorarTexto_ConArgumentoVacio() {
		final String actual = Ejercicio7.decorarTexto( "" );

		Assert.assertTrue( actual.isEmpty() );
	}

	/**
	 * Test method for {@link Ejercicio7#decorarTexto(String)} con una cadena muy larga.
	 * Concretamente mayor que el número de colores posibles.
	 */
	@Test
	@SuppressWarnings( "nls" )
	public void testDecorarTexto_ConArgumentoLargo() {
		final String expected = new String( "[color=\"red\"]A[/color]" + "[color=\"blue\"]l[/color]"
				+ "[color=\"yellow\"]b[/color]" + "[color=\"white\"]e[/color]" + "[color=\"black\"]r[/color]"
				+ "[color=\"magenta\"]t[/color]" + "[color=\"cyan\"]o[/color]" + "[color=\"red\"] [/color]"
				+ "[color=\"blue\"]D[/color]" + "[color=\"yellow\"]o[/color]" + "[color=\"white\"]m[/color]"
				+ "[color=\"black\"]í[/color]" + "[color=\"magenta\"]n[/color]" + "[color=\"cyan\"]g[/color]"
				+ "[color=\"red\"]u[/color]" + "[color=\"blue\"]e[/color]" + "[color=\"yellow\"]z[/color]"
				+ "[color=\"white\"] [/color]" + "[color=\"black\"]M[/color]" + "[color=\"magenta\"]a[/color]"
				+ "[color=\"cyan\"]t[/color]" + "[color=\"red\"]a[/color]" + "[color=\"blue\"]m[/color]"
				+ "[color=\"yellow\"]o[/color]" + "[color=\"white\"]r[/color]" + "[color=\"black\"]o[/color]"
				+ "[color=\"magenta\"]s[/color]" );

		final String actual = Ejercicio7.decorarTexto( "Alberto Domínguez Matamoros" );

		Assert.assertEquals( expected, actual );
	}

}
