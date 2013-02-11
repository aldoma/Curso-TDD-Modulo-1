/**
 *
 */
package org.aldoma.cursotdd.modulo1.ejercicio2;

/**
 * Implementación mínima de una clase Triangulo que cumpla las especificaciones del ejercicio 2.
 * 
 * @author Alberto Dominguez Matamoros
 * @version 0.0.1
 */
public class Triangle {
	/**
	 * Longitud de los distintos lados del triángulo.
	 * 
	 * @since 0.0.1
	 */
	final private int side_a, side_b, side_c; //NOPMD: No es un bean

	/**
	 * Constructor con inicialización explicita de las longitudes de los lados.
	 * 
	 * @param side1
	 *        Longitud del primer lado.
	 * @param side2
	 *        Longitud del segundo lado.
	 * @param side3
	 *        Longitud del tercer lado.
	 * @since 0.0.1
	 */
	public Triangle(	final int side1,
						final int side2,
						final int side3 ) {
		side_a = side1;
		side_b = side2;
		side_c = side3;

		if (!satisfyTriangleInequality()) { //NOPMD
			classByLength = ClassByLength.NO_ES_UN_TRIÁNGULO;
		}
		else if (side_a == side_b && side_b == side_c) {
			classByLength = ClassByLength.EQUILÁTERO;
		}
		else if (side_a == side_b || side_b == side_c || side_a == side_c) {
			classByLength = ClassByLength.ISÓSCELES;
		}
		else {
			classByLength = ClassByLength.ESCALENO;
		}
	}

	/**
	 * Clasificación de los triángulos por la longitud relativa de sus lados.
	 * 
	 * @author Alberto Dominguez Matamoros
	 * @version 0.0.1
	 */
	public enum ClassByLength {
		/**
		 * Las longitudes de los lados pasados como parámetros al
		 * {@link Triangle#Triangle(int, int, int) constructor} no cumplen el <a
		 * href="http://es.wikipedia.org/wiki/Desigualdad_triangular">teorema de la desigualdad
		 * triangular</a>.
		 * 
		 * @since 0.0.1
		 */
		NO_ES_UN_TRIÁNGULO,
		/**
		 * Un <a href="http://es.wikipedia.org/wiki/Tri%C3%A1ngulo_equil%C3%A1tero">triángulo
		 * equilátero</a>, es un triángulo con tres lados iguales.
		 * 
		 * @since 0.0.1
		 */
		EQUILÁTERO,
		/**
		 * Un <a href=
		 * "http://es.wikipedia.org/wiki/Tri%C3%A1ngulo#Clasificaci.C3.B3n_de_los_tri.C3.A1ngulos"
		 * >triángulo isósceles</a>, es un triángulo con dos lados iguales.
		 * 
		 * @since 0.0.1
		 */
		ISÓSCELES,
		/**
		 * Un <a href=
		 * "http://es.wikipedia.org/wiki/Tri%C3%A1ngulo#Clasificaci.C3.B3n_de_los_tri.C3.A1ngulos"
		 * >triángulo escaleno</a>, es un triángulo en que todos sus lados tienen longitudes
		 * diferentes.
		 * 
		 * @since 0.0.1
		 */
		ESCALENO
	}

	/**
	 * Clase del triángulo en función de la longitud de sus lados.
	 * 
	 * @since 0.0.1
	 */
	final private ClassByLength classByLength; //NOPMD: No es un bean

	/**
	 * Determina si las longitudes de los lados pasados como argumentos al
	 * {@link #Triangle(int, int, int) constructor} cumplen o no el teorema de la desigualdad
	 * triangular.
	 * 
	 * @return {@code true} si cumple el teorema o {@code false} en caso contrario.
	 * @since 0.0.1
	 */
	private boolean satisfyTriangleInequality() {
		return side_a < side_b + side_c && side_b < side_a + side_c && side_c < side_a + side_b;
	}

	/**
	 * Retorna la clase del triángulo en función de la longitud de sus lados.
	 * 
	 * @return the classByLength
	 * @since 0.0.1
	 */
	public ClassByLength getClassByLength() {
		return classByLength;
	}
}
