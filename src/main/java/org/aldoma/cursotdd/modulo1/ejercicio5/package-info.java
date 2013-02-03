/**
 * Quinto ejercicio del módulo 1.
 * <p>
 * <h2>Enunciado del ejercicio:</h2>
 * <p>
 * Habitualmente, cuando en un Map o diccionario o tabla hash se intenta añadir un elemento con una
 * clave que ya tiene asociado un elemento, el antiguo elemento se descarta y se almacena el nuevo.
 * A partir de la implementación de un Map, implemente una clase con un comportamiento similar,
 * llamada {@code LazyMap} que no remplace un elemento antiguo con uno nuevo.
 * <p>
 * Escriba un conjunto de pruebas para garantizar que el nuevo tipo funciona correctamente. ¿Qué
 * métodos crees que son necesarios probar y cuáles no son necesarios?
 * <p>
 * <h3>Respuesta:</h3>
 * <p>
 * No tengo muy claro cómo interpretar la expresión
 * <i>"A partir de la implementación de un Map"</i>. Se me ocurren dos opciones posibles:
 * <ol>
 * <li>Localizar una implementación del interfaz que se acerque a lo que deseamos y heredar nuestra
 * nueva clase {@code LazyMap} de ella. Tiene la ventaja de que únicamente tenemos que escribir
 * código y pruebas para los métodos sobrescritos (respuesta a la pregunta del ejercicio). Sin
 * embargo, a pesar de su comodidad, me parece una mala decisión puesto que rompe el contrato del
 * interfaz {@link java.util.Map} que implementa, lo cual es, definitivamente, una muy mala
 * práctica.</li>
 * <li>Al igual que en la anterior, localizamos una implementación que se acerque lo más posible a
 * lo que deseamos pero, en lugar de heredar, utilizamos la técnica del <em>cut & paste</em> (tanto
 * del interfaz como de su implementación). De esta forma tenemos en realidad un nuevo interfaz
 * (contrato) inspirado en el original y que somos libres de modificar tanto como necesitemos. El
 * usuario ha de entender que un nuevo contrato significa nuevas (o diferentes) clausulas.</br>En
 * este caso, respondiendo a la pregunta del ejercicio, tendremos de implementar casos de prueba
 * para la totalidad de los métodos de la nueva clase, pues no tenemos ninguna garantía de que las
 * futuras evoluciones (y correcciones) de una clase sean trasladas a la otra y viceversa.</br>Esta
 * es la solución tomada por mí para este ejercicio.
 * <p>
 * Adicionalmente, y si esto se realizase en el contexto de una aplicación real en lugar de un
 * ejercicio, siguiendo las recomendaciones de Carlos Blé en su libro (y las metodologías ágiles),
 * únicamente deberíamos implementar (y por tanto escribir casos de prueba de) los métodos mínimos
 * necesarios para cumplir la funcionalidad que se le pida a la clase en cada momento del proyecto.
 * Según se amplíen en el futuro las funcionalidades que se le piden se implementarían los nuevos
 * métodos con sus casos de prueba correspondientes.</li>
 * </ol>
 * 
 * @author Alberto Dominguez Matamoros
 */
package org.aldoma.cursotdd.modulo1.ejercicio5;

