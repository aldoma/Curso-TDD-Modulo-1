/**
 * Tercer ejercicio del módulo 1.
 * <p>
 * <h2>Enunciado del ejercicio:</h2>
 * <p>
 * Se desea desarrollar un fragmento de código que reciba una lista de elementos y construya tantas
 * listas como elementos tenga la lista original. En cada lista faltará uno de los elementos de la
 * lista, de tal manera que el elemento que falta nunca se repetirá.
 * <p>
 * Por ejemplo, si se toma la lista <code>{1, 2, 3}</code> se generarán las siguientes tres listas:
 * <code>{2, 3}</code>, <code>{1, 3}</code> y <code>{1, 2}</code>
 * <p>
 * Defina un conjunto de casos de prueba para el fragmento de código anterior.
 * <p>
 * <h4>Ampliación (del 01/02/2013):</h4>
 * <p>
 * Siguiendo las indicaciones de <a href="mailto:javierj@us.es">Javier Jesús Gutiérrez Rodríguez</a>
 * procedo a realizar las siguientes modificaciones / ampliaciones:
 * <ul>
 * <li>Modifico el método
 * {@link org.aldoma.cursotdd.modulo1.ejercicio3.Ejercicio3#generateLists(List)} para que desordene
 * las listas dado que uno de mis test dependía de ese orden y eso no era un requisito:
 * <em><blockquote>Creo que el enunciado no menciona la siguiente restricción: “Verifica que
 * cada lista retornada sea igual a la lista original menos el elemento que ocupa la misma posición
 * que ocupa ella en la lista retornada.”. Hay que intentar que las pruebas no introduzcan
 * restricciones que realmente no existen.</blockquote></em></li>
 * <li>Añadimos nuevos casos de prueba para contemplar casos límite o "extraños".</li>
 * </ul>
 * 
 * @author Alberto Dominguez Matamoros
 */
package org.aldoma.cursotdd.modulo1.ejercicio3;

