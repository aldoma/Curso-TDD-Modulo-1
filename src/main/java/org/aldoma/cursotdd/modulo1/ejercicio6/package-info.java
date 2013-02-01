/**
 * Sexto ejercicio del módulo 1.
 * <p>
 * <h2>Enunciado del ejercicio:</h2>
 * <p>
 * Escriba un conjunto de pruebas para verificar el comportamiento de un método que recibe como
 * parámetro dos conjuntos ordenados (o dos listas o dos arrays ordenadas de menor a mayor) y
 * devuelva un nuevo conjunto ordenado que contenga los elementos de ambos conjuntos en el mismo
 * orden.
 * <p>
 * ¿Cómo podríamos saber el número de elementos comunes que había en ambos conjuntos de entrada?
 * <p>
 * <h3>Respuesta:</h3>
 * <p>
 * La clase {@link Ejercicio6Test} contiene todos los casos de prueba razonables que se me han
 * ocurrido. {@link org.aldoma.cursotdd.modulo1.ejercicio6.Ejercicio6} contiene una posible
 * implementación del algoritmo utilizado para la realización de las pruebas.
 * <p>
 * Respecto a la pregunta, creo que la mejor solución es crear una función similar a la probada en
 * este ejercicio pero que realice la <em>intersección</em> en lugar de la unión de los conjuntos.
 * 
 * @author Alberto Dominguez Matamoros
 */
package org.aldoma.cursotdd.modulo1.ejercicio6;