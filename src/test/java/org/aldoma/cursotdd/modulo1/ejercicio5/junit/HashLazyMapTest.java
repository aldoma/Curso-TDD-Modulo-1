/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio5.junit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.aldoma.cursotdd.modulo1.ejercicio5.HashLazyMap;
import org.aldoma.cursotdd.modulo1.ejercicio5.LazyMap;
import org.aldoma.cursotdd.modulo1.ejercicio5.LazyMap.Entry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for {@link HashLazyMap}
 * 
 * @author Alberto Dominguez Matamoros
 */
@SuppressWarnings( { "nls", "static-method", "boxing", "PMD.ExcessivePublicCount", "PMD.TooManyMethods", "PMD.GodClass" } )
public class HashLazyMapTest {
	private static final String MSG_BAD_KEY = "Valor de clave de incorrecto, elegir otro";
	private static final String UNO = "Uno";
	private static final String DOS = "Dos";
	private static final String TRES = "Tres";
	private static final String CUATRO = "Cuatro";
	private static final String CINCO = "Cinco";
	private static final String SEIS = "Seis";
	private static final String SIETE = "Siete";
	private static final String OCHO = "Ocho";
	private static final String NUEVE = "Nueve";

	/**
	 * Un {@link HashMap} de {@link Integer} a {@link String} inicializado durante el
	 * {@link #setUp()} y utilizado por distintas pruebas.
	 */
	@SuppressWarnings( "PMD.UseConcurrentHashMap" )
	private final Map<Integer, String> initialMap = new HashMap<Integer, String>(); //PMD: No es un bean
	private final HashLazyMap<Integer, String> initialLazyMap = new HashLazyMap<Integer, String>(); //NOPMD: No es un bean

	/**
	 * Inicializa las variables miembro de la clase utilizadas por los distintos casos de prueba.
	 */
	@Before
	public void setUp() {
		//
		// ATENCIÓN!!!!
		// Para algunos de los casos de prueba es importante que estos conjuntos sean disjuntos
		initialMap.put( 1, HashLazyMapTest.UNO );
		initialMap.put( 2, HashLazyMapTest.DOS );
		initialMap.put( 4, HashLazyMapTest.CUATRO );
		initialMap.put( 5, HashLazyMapTest.CINCO );
		initialMap.put( 6, HashLazyMapTest.SEIS );
		initialMap.put( 8, HashLazyMapTest.OCHO );
		initialMap.put( 9, HashLazyMapTest.NUEVE );

		initialLazyMap.put( 1, "I" );
		initialLazyMap.put( 3, "III" );
		initialLazyMap.put( 4, "IV" );
		initialLazyMap.put( 5, "V" );
		initialLazyMap.put( 6, "VI" );
		initialLazyMap.put( 7, "VII" );
		initialLazyMap.put( 9, "IX" );
	}

	/**
	 * Test method for {@link HashLazyMap#HashLazyMap(Map)}.
	 */
	@Test
	public final void testHashLazyMap_MapOfQextendsKQextendsV() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );

		Assert.assertEquals( initialMap.size(), testClass.size() );
		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertEquals( initialMap.get( key ), testClass.get( key ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#HashLazyMap(LazyMap)} .
	 */
	@Test
	public final void testHashLazyMap_LazyMapOfQextendsKQextendsV() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		Assert.assertEquals( initialLazyMap.size(), testClass.size() );
		for (final Integer key : initialLazyMap.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertEquals( initialLazyMap.get( key ), testClass.get( key ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#isEmpty()} para una instancia creada vacía.
	 */
	@Test
	public void testIsEmpty_NuevaClase() {
		final HashLazyMap<Integer, Integer> testClass = new HashLazyMap<Integer, Integer>();

		Assert.assertTrue( testClass.isEmpty() );

		testClass.put( Integer.valueOf( 1 ), Integer.valueOf( 1 ) );
		Assert.assertFalse( testClass.isEmpty() );
	}

	/**
	 * Test method for {@link HashLazyMap#isEmpty()} para una instancia creada mediante el Ctor
	 * {@link HashLazyMap#HashLazyMap(Map)}
	 */
	@Test
	public void testIsEmpty_MapOfQextendsKQextendsV() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );

		Assert.assertFalse( testClass.isEmpty() );
		testClass.clear();
		Assert.assertTrue( testClass.isEmpty() );
	}

	/**
	 * Test method for {@link HashLazyMap#isEmpty()} para una instancia creada mediante el Ctor
	 * {@link HashLazyMap#HashLazyMap(LazyMap)}
	 */
	@Test
	public void testIsEmpty_LazyMapOfQextendsKQextendsV() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		Assert.assertFalse( testClass.isEmpty() );
		testClass.clear();
		Assert.assertTrue( testClass.isEmpty() );
	}

	/**
	 * Test method for {@link HashLazyMap#get(Object)}
	 */
	@Test
	public void testGet() {
		final Integer keyToTest = Integer.valueOf( 3 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );

		Assert.assertEquals( initialMap.get( keyToTest ), testClass.get( keyToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#get(Object)} con una clave {@code null}
	 */
	@Test
	public void testGet_ConNull() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );

		Assert.assertNull( testClass.get( null ) );
	}

	/**
	 * Test method for {@link HashLazyMap#get(Object)} con una clave inexistente
	 */
	@Test
	public void testGet_ConClaveInexistente() {
		final Integer keyToTest = Integer.valueOf( 13 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );

		// Tambien Nos aseguramos que la key a testear no existe en el mapa original
		Assert.assertNull( initialMap.get( keyToTest ) );
		Assert.assertNull( testClass.get( keyToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#containsKey(Object)}
	 */
	@Test
	public void testContainsKey() {
		final Integer keyToTest = Integer.valueOf( 3 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		Assert.assertTrue( testClass.containsKey( keyToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#containsKey(Object)} con una clave {@code null}
	 */
	@Test
	public void testContainsKey_ConNull() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		Assert.assertFalse( testClass.containsKey( null ) );
	}

	/**
	 * Test method for {@link HashLazyMap#containsKey(Object)} con una clave inexistente
	 */
	@Test
	public void testContainsKey_ConClaveInexistente() {
		final Integer keyToTest = Integer.valueOf( 13 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		// Nos aseguramos que la key a testear no existe tampoco en el mapa original
		Assert.assertFalse( initialLazyMap.containsKey( keyToTest ) );
		Assert.assertFalse( testClass.containsKey( keyToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#put(Object, Object)}
	 */
	@Test
	public void testPut() {
		final int numElementos = 10;
		final HashLazyMap<Integer, Integer> testClass = new HashLazyMap<Integer, Integer>();

		for (int i = 0; i < numElementos; i++) {
			Assert.assertTrue( testClass.put( i, i ) );
		}
		// verificamos el numero de elementos añadidos...
		Assert.assertEquals( numElementos, testClass.size() );

		// ...y su valor
		for (int i = 0; i < numElementos; i++) {
			Assert.assertEquals( i, testClass.get( i ).intValue() ); //NOPMD
		}
	}

	/**
	 * Test method for {@link HashLazyMap#put(Object, Object)} sobreescribiendo
	 */
	@Test
	public void testPut_Sobreescribiendo() {
		final Integer keyToTest = 5;
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );

		// Nos aseguramos que la clave exista antes de intentar sobreescribirla
		Assert.assertTrue( testClass.containsKey( keyToTest ) );
		Assert.assertFalse( testClass.put( keyToTest, "sklflslfjsdgsioñw" ) ); //$NON-NLS-1$

		// Y verificamos que, efectivamente, no haya sido modificado
		Assert.assertEquals( initialMap.get( keyToTest ), testClass.get( keyToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#put(Object, Object)} con una key {@code null}
	 */
	@Test
	public void testPut_ConKeyNull() {
		final String valueToTest = "Esto es un null"; //$NON-NLS-1$
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();

		Assert.assertTrue( testClass.put( null, valueToTest ) );

		// Y verificamos que, efectivamente, haya sido insertado
		Assert.assertEquals( valueToTest, testClass.get( null ) );
	}

	/**
	 * Test method for {@link HashLazyMap#put(Object, Object)} intentando sobreescribir una key
	 * {@code null}
	 */
	@Test
	public void testPut_SobreescribiendoConKeyNull() {
		final String valueToTest = "Esto es un null"; //$NON-NLS-1$
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();

		Assert.assertTrue( testClass.put( null, valueToTest ) );
		Assert.assertFalse( testClass.put( null, "Cualquier otra cosa" ) ); //$NON-NLS-1$
		// Y verificamos que, efectivamente, no haya sido modificado
		Assert.assertEquals( valueToTest, testClass.get( null ) );
	}

	/**
	 * Test method for {@link HashLazyMap#putAll(Map)}
	 */
	@Test
	public void testPutAll() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();
		testClass.putAll( initialMap );

		Assert.assertEquals( initialMap.size(), testClass.size() );
		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertEquals( initialMap.get( key ), testClass.get( key ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#putAll(Map)} intentando sobreescribir claves
	 */
	@Test
	public void testPutAll_Sobreescribiendo() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );
		testClass.putAll( initialMap );

		// A pesar del putAll anterior los valores asociados a las claves ya existenten no han
		// debido ser modificados...
		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			if (initialLazyMap.containsKey( key )) {
				Assert.assertEquals( initialLazyMap.get( key ), testClass.get( key ) );
			}
		}
		// ...sin embargo aquellas no existentes si han debido ser añadidas
		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			if (!initialLazyMap.containsKey( key )) {
				Assert.assertEquals( initialMap.get( key ), testClass.get( key ) );
			}
		}
	}

	/**
	 * Test method for {@link HashLazyMap#remove(Object)}
	 */
	@Test
	public void testRemove() {
		final Integer keyToTest = Integer.valueOf( 6 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );

		Assert.assertTrue( testClass.containsKey( keyToTest ) );
		final String result = testClass.remove( keyToTest );
		Assert.assertNotNull( result );
		Assert.assertEquals( initialMap.get( keyToTest ), result );
		Assert.assertFalse( testClass.containsKey( keyToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#remove(Object)} intentando eliminar una clave null
	 */
	@Test
	public void testRemove_ConNull() {
		final String valueToTest = "Esto es un null"; //$NON-NLS-1$
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();

		Assert.assertTrue( testClass.put( null, valueToTest ) );
		Assert.assertEquals( 1, testClass.size() );
		Assert.assertNotNull( testClass.remove( null ) );
		Assert.assertEquals( 0, testClass.size() );
	}

	/**
	 * Test method for {@link HashLazyMap#remove(Object)} intentando eliminar una clave inexistente
	 */
	@Test
	public void testRemove_ConClaveInexistente() {
		final Integer keyToTest = Integer.valueOf( 6 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();

		Assert.assertFalse( testClass.containsKey( keyToTest ) );
		Assert.assertNull( testClass.remove( keyToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#clear()}
	 */
	@Test
	public void testClear() {
		final HashLazyMap<Integer, Integer> testClass = new HashLazyMap<Integer, Integer>();

		Assert.assertTrue( testClass.put( 1, 1 ) );
		Assert.assertFalse( testClass.isEmpty() );

		testClass.clear();
		Assert.assertTrue( testClass.isEmpty() );

	}

	/**
	 * Test method for {@link HashLazyMap#containsValue(Object)}
	 */
	@Test
	public void testContainsValue() {
		final Integer keyToTest = Integer.valueOf( 7 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		Assert.assertTrue( HashLazyMapTest.MSG_BAD_KEY, initialLazyMap.containsKey( keyToTest ) );
		final String valueToTest = initialLazyMap.get( keyToTest );

		Assert.assertTrue( testClass.containsValue( valueToTest ) );
	}

	/**
	 * Test method for {@link HashLazyMap#containsValue(Object)} con valor inexistente
	 */
	@Test
	public void testContainsValue_ConValorInexistente() {
		final HashLazyMap<Integer, Integer> testClass = new HashLazyMap<Integer, Integer>();

		Assert.assertFalse( testClass.containsValue( Integer.valueOf( -45 ) ) );
	}

	/**
	 * Test method for {@link HashLazyMap#containsValue(Object)} con valor {@code null}
	 */
	@Test
	public void testContainsValue_ConValorNull() {
		final HashLazyMap<Integer, Integer> testClass = new HashLazyMap<Integer, Integer>();

		Assert.assertTrue( testClass.put( 1, null ) );
		Assert.assertTrue( testClass.containsValue( null ) );
	}

	/**
	 * Test method for {@link HashLazyMap#containsValue(Object)} con valor {@code null} inexistente
	 */
	@Test
	public void testContainsValue_ConValorNullInexistente() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		Assert.assertFalse( testClass.containsValue( null ) );
	}

	/**
	 * Test method for {@link HashLazyMap#clone()}
	 */
	@Test
	public void testClone() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		testClass.clone();

		Assert.assertEquals( initialLazyMap.size(), testClass.size() );
		for (final Integer key : testClass.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertEquals( initialLazyMap.get( key ), testClass.get( key ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()}
	 */
	@Test
	public void testKeySet() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Integer> keys = testClass.keySet();

		Assert.assertEquals( testClass.size(), keys.size() );

		for (final LazyMap.Entry<Integer, String> entry : testClass.entrySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertTrue( keys.contains( entry.getKey() ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()} sobre un mapa vacío.
	 */
	@Test
	public void testKeySet_DeUnMapaVacio() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();

		Assert.assertTrue( testClass.keySet().isEmpty() );
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()}, eliminamos un elemento vía
	 * {@link Set#remove(Object)}
	 */
	@Test
	public void testKeySet_Remove() {
		final Integer keyToTest = Integer.valueOf( 5 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );
		Assert.assertTrue( HashLazyMapTest.MSG_BAD_KEY, testClass.containsKey( keyToTest ) );

		final Set<Integer> keys = testClass.keySet();

		Assert.assertTrue( keys.remove( keyToTest ) );
		Assert.assertEquals( initialLazyMap.size() - 1, testClass.size() );
		Assert.assertEquals( testClass.size(), keys.size() ); //NOPMD
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()}, intentamos eliminar un elemento inexistente vía
	 * {@link Set#remove(Object)}
	 */
	@Test
	public void testKeySet_RemoveClaveInexistente() {
		final Integer keyToTest = Integer.valueOf( -55 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );
		Assert.assertFalse( HashLazyMapTest.MSG_BAD_KEY, testClass.containsKey( keyToTest ) );

		final Set<Integer> keys = testClass.keySet();

		Assert.assertFalse( keys.remove( keyToTest ) );
		Assert.assertEquals( initialLazyMap.size(), testClass.size() );
		Assert.assertEquals( testClass.size(), keys.size() );
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()}, eliminamos varios elementos vía
	 * {@link Set#removeAll(Collection)}
	 */
	@Test
	public void testKeySet_RemoveAll() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Integer> keys = testClass.keySet();

		Assert.assertTrue( keys.removeAll( initialMap.keySet() ) ); //NOPMD

		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertFalse( testClass.containsKey( key ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()}, eliminamos varios elementos vía
	 * {@link Set#retainAll(Collection)}
	 */
	@Test
	public void testKeySet_RetainAll() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Integer> keys = testClass.keySet();

		Assert.assertTrue( keys.retainAll( initialMap.keySet() ) );

		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			if (initialLazyMap.containsKey( key )) {
				Assert.assertTrue( testClass.containsKey( key ) );
			}
		}
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()}, vaciamos la colección vía {@link Set#clear()}
	 */
	@Test
	public void testKeySet_Clear() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Integer> keys = testClass.keySet();
		keys.clear();

		Assert.assertTrue( keys.isEmpty() );
		Assert.assertTrue( testClass.isEmpty() );
	}

	/**
	 * Test method for {@link HashLazyMap#keySet()}, eliminamos algunos miembros de la colección con
	 * ayuda del {@link Iterator#remove()} del {@link Set}.
	 */
	@Test
	public void testKeySet_IteratorRemove() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Integer> keys = testClass.keySet();

		for (final Iterator<Integer> iterator = keys.iterator(); iterator.hasNext();) {
			final Integer key = iterator.next();
			if (initialMap.containsKey( key )) {
				iterator.remove();
			}
		}
		Assert.assertTrue( initialLazyMap.size() > testClass.size() );

		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertFalse( testClass.containsKey( key ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#values()}
	 */
	@Test
	public void testValues() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );
		testClass.putAll( initialMap );
		final Collection<String> values = testClass.values();

		Assert.assertEquals( testClass.size(), values.size() );

		for (final LazyMap.Entry<Integer, String> entry : testClass.entrySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertTrue( values.contains( entry.getValue() ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#values()} sobre un mapa vacío.
	 */
	@Test
	public void testValues_DeUnMapaVacio() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();

		Assert.assertTrue( testClass.values().isEmpty() ); //NOPMD
	}

	/**
	 * Test method for {@link HashLazyMap#values()}, eliminamos un elemento vía
	 * {@link Set#remove(Object)}
	 */
	@Test
	public void testValues_Remove() {
		final Integer keyToTest = Integer.valueOf( 5 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );
		Assert.assertTrue( HashLazyMapTest.MSG_BAD_KEY, testClass.containsKey( keyToTest ) );

		final Collection<String> values = testClass.values();

		Assert.assertTrue( values.remove( testClass.get( keyToTest ) ) );
		Assert.assertEquals( initialLazyMap.size() - 1, testClass.size() );
		Assert.assertEquals( testClass.size(), values.size() );
	}

	/**
	 * Test method for {@link HashLazyMap#values()}, intentamos eliminar un elemento inexistente vía
	 * {@link Set#remove(Object)}
	 */
	@Test
	public void testValues_RemoveElementoInexistente() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Collection<String> values = testClass.values();

		Assert.assertFalse( values.remove( "askljhaflhfhaskjñkjfhas" ) );
		Assert.assertEquals( initialLazyMap.size(), testClass.size() );
		Assert.assertEquals( testClass.size(), values.size() );
	}

	/**
	 * Test method for {@link HashLazyMap#values()}, eliminamos varios elementos vía
	 * {@link Set#removeAll(Collection)}
	 */
	@Test
	public void testValues_RemoveAll() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );
		final String array[] = new String[] { HashLazyMapTest.TRES, HashLazyMapTest.CINCO, HashLazyMapTest.SIETE,
				HashLazyMapTest.NUEVE };

		final Collection<String> values = testClass.values();

		Assert.assertTrue( values.removeAll( Arrays.asList( array ) ) ); //NOPMD

		for (final String value : Arrays.asList( array )) { //NOPMD: Esto es un bug de PMD
			Assert.assertFalse( testClass.containsValue( value ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#values()}, eliminamos varios elementos vía
	 * {@link Set#retainAll(Collection)}
	 */
	@Test
	public void testValues_RetainAll() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );
		final String array[] = new String[] { HashLazyMapTest.TRES, HashLazyMapTest.CINCO, HashLazyMapTest.SIETE,
				HashLazyMapTest.NUEVE };

		final Collection<String> values = testClass.values();

		Assert.assertTrue( values.retainAll( Arrays.asList( array ) ) );

		for (final String value : Arrays.asList( array )) { //NOPMD: Esto es un bug de PMD
			if (initialMap.containsValue( value )) {
				Assert.assertTrue( testClass.containsValue( value ) );
			}
		}
	}

	/**
	 * Test method for {@link HashLazyMap#values()}, vaciamos la colección vía {@link Set#clear()}
	 */
	@Test
	public void testValues_Clear() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Collection<String> values = testClass.values();
		values.clear();

		Assert.assertTrue( values.isEmpty() );
		Assert.assertTrue( testClass.isEmpty() );
	}

	/**
	 * Test method for {@link HashLazyMap#values()}, eliminamos algunos miembros de la colección con
	 * ayuda del {@link Iterator#remove()} del {@link Set}.
	 */
	@Test
	public void testValues_IteratorRemove() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialMap );
		final String array[] = new String[] { HashLazyMapTest.TRES, HashLazyMapTest.CINCO, HashLazyMapTest.SIETE,
				HashLazyMapTest.NUEVE };

		final Collection<String> toRemove = Arrays.asList( array );
		final Collection<String> values = testClass.values();

		for (final Iterator<String> iterator = values.iterator(); iterator.hasNext();) {
			final String value = iterator.next();
			if (toRemove.contains( value )) {
				iterator.remove();
			}
		}
		Assert.assertTrue( initialLazyMap.size() > testClass.size() );

		for (final String value : toRemove) { //NOPMD: Esto es un bug de PMD
			Assert.assertFalse( testClass.containsValue( value ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()}
	 */
	@Test
	public void testEntrySet() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Entry<Integer, String>> entries = testClass.entrySet();

		Assert.assertEquals( testClass.size(), entries.size() ); //NOPMD

		for (final LazyMap.Entry<Integer, String> entry : entries) { //NOPMD: Esto es un bug de PMD
			Assert.assertTrue( testClass.containsKey( entry.getKey() ) );
			Assert.assertTrue( testClass.containsValue( entry.getValue() ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()} sobre un mapa vacío.
	 */
	@Test
	public void testEntrySet_DeUnMapaVacio() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>();

		Assert.assertTrue( testClass.entrySet().isEmpty() ); //NOPMD
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()}, eliminamos un elemento vía
	 * {@link Set#remove(Object)}
	 */
	@Test
	public void testEntrySet_Remove() {
		final Integer keyToTest = Integer.valueOf( 5 );
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );
		Assert.assertTrue( HashLazyMapTest.MSG_BAD_KEY, testClass.containsKey( keyToTest ) );

		final Set<Entry<Integer, String>> entries = testClass.entrySet();
		for (final Entry<Integer, String> entry : entries) { //NOPMD: Esto es un bug de PMD
			if (keyToTest == entry.getKey()) {
				Assert.assertTrue( entries.remove( entry ) );
				break;
			}
		}
		Assert.assertEquals( initialLazyMap.size() - 1, testClass.size() );
		Assert.assertEquals( testClass.size(), entries.size() );
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()}, intentamos eliminar un elemento inexistente
	 * vía {@link Set#remove(Object)}
	 */
	@Test
	public void testEntrySet_RemoveEntryInexistente() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Entry<Integer, String>> entries = testClass.entrySet();
		for (final Map.Entry<Integer, String> entry : initialMap.entrySet()) { //NOPMD: Esto es un bug de PMD
			if (!testClass.containsKey( entry.getKey() )) {
				Assert.assertFalse( entries.remove( entry ) );
				break;
			}
		}

		Assert.assertEquals( initialLazyMap.size(), testClass.size() );
		Assert.assertEquals( testClass.size(), entries.size() );
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()}, eliminamos varios elementos vía
	 * {@link Set#removeAll(Collection)}
	 */
	@Test
	public void testEntrySet_RemoveAll() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Entry<Integer, String>> entries = testClass.entrySet();
		final Set<Entry<Integer, String>> entriesToDelete = new HashSet<LazyMap.Entry<Integer, String>>();
		for (final Entry<Integer, String> entry : entries) { //NOPMD: Esto es un bug de PMD
			if (initialMap.containsKey( entry.getKey() )) {
				entriesToDelete.add( entry );
			}
		}

		Assert.assertTrue( entries.removeAll( entriesToDelete ) );

		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertFalse( testClass.containsKey( key ) );
		}
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()}, eliminamos varios elementos vía
	 * {@link Set#retainAll(Collection)}
	 */
	@Test
	public void testEntrySet_RetainAll() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Entry<Integer, String>> entries = testClass.entrySet();
		final Set<Entry<Integer, String>> entriesToRetain = new HashSet<LazyMap.Entry<Integer, String>>();
		for (final Entry<Integer, String> entry : entries) { //NOPMD: Esto es un bug de PMD
			if (initialMap.containsKey( entry.getKey() )) {
				entriesToRetain.add( entry );
			}
		}

		Assert.assertTrue( entries.retainAll( entriesToRetain ) ); //NOPMD

		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			if (initialLazyMap.containsKey( key )) {
				Assert.assertTrue( testClass.containsKey( key ) );
			}
		}
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()}, vaciamos la colección vía {@link Set#clear()}
	 */
	@Test
	public void testEntrySet_Clear() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Entry<Integer, String>> entries = testClass.entrySet();
		entries.clear();

		Assert.assertTrue( entries.isEmpty() );
		Assert.assertTrue( testClass.isEmpty() );
	}

	/**
	 * Test method for {@link HashLazyMap#entrySet()}, eliminamos algunos miembros de la colección
	 * con ayuda del {@link Iterator#remove()} del {@link Set}.
	 */
	@Test
	public void testEntrySet_IteratorRemove() {
		final HashLazyMap<Integer, String> testClass = new HashLazyMap<Integer, String>( initialLazyMap );

		final Set<Entry<Integer, String>> entries = testClass.entrySet();

		for (final Iterator<Entry<Integer, String>> iterator = entries.iterator(); iterator.hasNext();) {
			final Entry<Integer, String> entry = iterator.next();
			if (initialMap.containsKey( entry.getKey() )) {
				iterator.remove();
			}
		}
		Assert.assertTrue( initialLazyMap.size() > testClass.size() );

		for (final Integer key : initialMap.keySet()) { //NOPMD: Esto es un bug de PMD
			Assert.assertFalse( testClass.containsKey( key ) );
		}
	}
}
