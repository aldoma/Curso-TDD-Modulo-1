/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio9.junit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aldoma.cursotdd.modulo1.ejercicio9.C;
import org.aldoma.cursotdd.modulo1.ejercicio9.F;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alberto Dominguez Matamoros
 */
public class FTest {
	/**
	 * Test method for {@link F#creaLista()}.
	 */
	@Test
	@SuppressWarnings( "static-method" )
	public void testCreaLista() {
		final List<C> lista = F.creaLista();
		final Set<String> result = new HashSet<String>( lista.size() );
		for (final C c : lista) {
			Assert.assertTrue( result.add( c.getA() ) );
		}
	}
}
