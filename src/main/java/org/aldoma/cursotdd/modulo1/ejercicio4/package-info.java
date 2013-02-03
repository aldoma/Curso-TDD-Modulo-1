/**
 * Cuarto ejercicio del módulo 1.
 * <p>
 * <h2>Enunciado del ejercicio:</h2>
 * <p>
 * Vamos a definir más pruebas utilizando la interfaz {@link java.util.Set} definida en Java. Esta
 * interfaz define el método {@link java.util.HashSet#removeAll(Collection)} para borrar todos los
 * elementos del conjunto que estén en la colección {@code c}.
 * <p>
 * A partir de su código fuente (definido en la clase {@link java.util.AbstractSet}), y que se
 * muestra a continuación, diseñe un conjunto de pruebas aplicando la técnica de cobertura de
 * decisiones.
 * <p>
 * 
 * <pre>
 * public boolean removeAll( Collection&lt;?&gt; c ) {
 * 	boolean modified = false;
 * 
 * 	if (size() &gt; c.size()) {
 * 		for (Iterator&lt;?&gt; i = c.iterator(); i.hasNext();)
 * 			modified |= remove( i.next() );
 * 	}
 * 	else {
 * 		for (Iterator&lt;?&gt; i = iterator(); i.hasNext();) {
 * 			if (c.contains( i.next() )) {
 * 				i.remove();
 * 				modified = true;
 * 			}
 * 		}
 * 	}
 * 	return modified;
 * }
 * </pre>
 * <p>
 * Realice este mismo ejercicio con otros métodos de otros contratos para tipos de datos, bien
 * tomando como referencia la propia definición del contrato, bien tomando como referencia el código
 * fuente de la implementación si está disponible.
 * <p>
 * <h3>Respuesta:</h3>
 * <p>
 * Los casos de prueba <code>testRemoveAll_*</code> de
 * {@link org.aldoma.cursotdd.modulo1.ejercicio4.junit.SetTest} cubren todos las casos que se me han
 * ocurrido y ejercitan todos los path de ejecución posibles.
 * <p>
 * Respecto a la segunda parte del ejercicio, se me plantea una duda respecto a la definición del
 * mismo:<blockquote><i>¿Cómo es posible realizar pruebas aplicando la técnica de cobertura de
 * decisiones tomando como referencia la definición de un contrato? ¿No es necesaria la
 * implementación para la aplicación de esta técnica?</i></blockquote>
 * <p>
 * Por otro lado, de las distintas técnicas estudiadas en este módulo, entiendo que las de
 * "Particiones equivalentes" y "Análisis de valores límite" pertenecen a la categoría de
 * <em>Caja negra</em>, mientras que la técnica de cobertura de decisiones pertenece a la categoría
 * de <em>Caja blanca</em>. Mi duda entonces es,<blockquote><i>¿las pruebas centradas en un interfaz
 * no deberían ser del tipo caja negra?</blockquote></i>Al fin y al cabo entiendo que uno de los
 * objetivos de crear una interfaz es separar esta de su implementación. Entiendo que el acceso al
 * código fuente (implementación de referencia) puede inspirarnos para encontrar nuevos casos de
 * prueba que pueden ser muy útiles en el futuro para testear muy extensamente nuevas
 * implementaciones del interfaz, pero, ¿no pueden hacer depender los test (al menos su utilidad) de
 * una implementación concreta?
 * <p>
 * En cualquier caso realizo el ejercicio con las funciones
 * {@link java.util.ArrayList#lastIndexOf(Object)} y {@link java.util.ArrayList#remove(Object)}.
 * 
 * @author Alberto Dominguez Matamoros
 */
package org.aldoma.cursotdd.modulo1.ejercicio4;

