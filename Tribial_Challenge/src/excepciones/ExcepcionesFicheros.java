package excepciones;

import java.io.IOException;

/**
 * Clase que nos define los distintos tipos de excepciones que podemos tener a la hora de manejar ficheros.
 * @author Aaron Alonso Aparicio
 * @version 1.0  
 */
public class ExcepcionesFicheros extends IOException {
	
	/**
	 * Caso en el que el archivo que estemos usado o vayamos a crear no se encuentre en esa ruta
	 * @param mensajeErrorFicheros mensaje que nos saltara en caso de error.
	 */

	public void archivoNoEncontrado(String mensajeErrorFicheros) {
		mensajeErrorFicheros = "El archivo no existe.";
		
	}
}
