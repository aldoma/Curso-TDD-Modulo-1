/**
 * Octavo ejercicio del módulo 1.
 * <p>
 * <h2>Enunciado del ejercicio:</h2>
 * <p>
 * Tenemos una mina de diamantes, representada mediante una cadena de caracteres '<' y '>' de cualquier longitud y orden, devolver el número de diamantes que existen en la mina. Un diamante está formado por el símbolo '<' seguido de '>', esto es "<>". Hay que tener en cuenta que cada vez que se extrae un diamante podría formarse otro.
 * <p>
 * <b>Ejemplos:</b>
 * <table frame="box" rules="all"><tr>
 * <th><b>Mina<br />(Entrada)</b></th><th><b>#Diamantes<br />(Salida)</b></th><th><b>Explicación</b></th></tr>
 * <tr><td>""</td><td>0</td><td>Mina vacía por tanto 0 diamantes</td></tr>
 * <tr><td>"<>"</td><td>1</td><td>Mina con un único diamante</td></tr>
 * <tr><td>"<<>>"</td><td>2</td><td>Mina con un diamante entero y otro que se forma al extraer el primero</td></tr>
 * <tr><td>"<><<>><<"</td><td>3</td><td>Mina con dos diamantes y un tercero que se forma con la extracción del segundo</td></tr>
 * </table>
 * <p>
 * Diseñe un conjunto de casos de prueba para este ejercicio. Una vez hecho envíe sus resultados a su tutor y este le entregará una implementación de este ejercicio. Ejecute sus pruebas con dicha implementación y conteste a la siguiente pregunta: ¿puede asegurar que dicha implementación es correcta y que funcionará ante cualquier mina?
 * <p>
 * <h3>Respuesta:</h3>
 * <p>
 * En mi propia implementación de la solución el tercer caso de prueba del ejercicio ya me ha obligado a
 * crear un bucle para descubrir los diamantes creados por la extracción de otros. Después de esta modificación
 * el tercer y cuarto test se han pasado sin problemas. No soy capaz de pensar en ninguna otra casuística que
 * no esté contemplada en el conjunto de test actual, con lo que doy el ejercicio por terminado.
 *
 * @author Alberto Dominguez Matamoros
 */
package org.aldoma.cursotdd.modulo1.ejercicio8;