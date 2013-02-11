/**
 * Primer ejercicio del módulo 1.
 * <p>
 * <h2>Enunciado del ejercicio:</h2>
 * <p>
 * Se desea diseñar un fragmento de código que permita calcular números aleatorios entre un rango de
 * dos números, de manera que dichos números aleatorios nunca se repitan. Por ejemplo, dado el rango
 * [1, 6), los tres primeros números podrían ser 4, 2 y 3. Los sucesivos números tendrían que ser
 * distintos a todos los anteriores. Resuelva las siguientes preguntas.
 * <p>
 * <ol>
 * <li>¿Cómo diseñaría el código? Por ejemplo, utilizaría un método, una clase, usaría algún tipo de
 * dato o interfaz proporcionada por Java, etc.</li>
 * <li>¿Cómo podría probar el diseño anterior? ¿De qué manera afectaría trabajar con resultados que
 * se generan automáticamente?</li>
 * <li>Escriba una posible implementación y al menos un caso de prueba.</li>
 * </ol>
 * <h3>Respuestas:</h3>
 * <p>
 * <ol>
 * <li>Se implementa mediante una clase que hace uso de {@link java.util.Collections#shuffle(List)}
 * para barajar un {@link java.util.ArrayList} creado durante la construcción del objeto.</li>
 * <li>Todos los casos que se me ocurren han sido implementados en los casos de prueba. Respecto a
 * la segunda pregunta, creo que no la estoy entendiendo. ¿Estamos hablando de respuestas
 * preprogramadas? Si es así, y hasta donde yo entiendo, estaríamos probando la prueba (la
 * implementación de la misma) y no el SUT.</li>
 * <li>{@link org.aldoma.cursotdd.modulo1.ejercicio1.NonRepeatingRandom} contiene una implementación
 * posible, y {@link org.aldoma.cursotdd.modulo1.ejercicio1.junit.NonRepeatingRandomTest} los casos
 * de prueba.</li>
 * </ol>
 * <p>
 * <h4>Ampliación (del 01/02/2013):</h4>
 * <p>
 * Siguiendo las indicaciones de <a href="mailto:javierj@us.es">Javier Jesús Gutiérrez Rodríguez</a>
 * procedo a realizar las siguientes modificaciones / ampliaciones:
 * <ul>
 * <li>Cambio el número de versión del proyecto a {@code 0.0.2-SNAPSHOT} para acoger estas
 * modificaciones.</li>
 * <li>Modificaciones en la estructura de la clase:
 * <ul>
 * <li>Sustituyo la propiedad {@code maxCapacity} por
 * {@link org.aldoma.cursotdd.modulo1.ejercicio1.NonRepeatingRandom#size()}. La primera podía
 * confundirse con el límite superior, creo que este segundo nombre es mucho más claro.</li>
 * <li>Añado la propiedad
 * {@link org.aldoma.cursotdd.modulo1.ejercicio1.NonRepeatingRandom#isExhausted()} para que los
 * clientes puedan saber si el generador ha sido "agotado" o no sin tener que "consumir" uno de los
 * elementos de la colección. (
 * <em>Esto no es muy ágile, ¿verdad? Esta funcionalidad no ha aparecido aun como necesidad</em>).</li>
 * <li>Actualizo el conjunto de pruebas con estas modificaciones.</li>
 * </ul>
 * <li>Comienzo a utilizar el tag {@literal @since} para identificar claramente los nuevos casos de
 * prueba y funcionalidades.</li>
 * <li>Añado pruebas para definir el comportamiento cuando ambos valores del rango son iguales o el
 * rango inferior sea mayor que el superior.</li>
 * <li>Documento brevemente todos los casos de prueba para que una revisión de la documentación haga
 * más sencillo reflexionar sobre qué casos pueden faltar.</li>
 * </ul>
 * 
 * @author Alberto Dominguez Matamoros
 */
package org.aldoma.cursotdd.modulo1.ejercicio1;

