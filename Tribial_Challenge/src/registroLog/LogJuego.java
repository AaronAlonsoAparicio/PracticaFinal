package registroLog;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import constantes.ConstantesRutas;

/**
 * Clase que genera el log del juego con cada una de las acciones que se
 * realicen en el programa
 */
public class LogJuego {

	/**
	 * Crea un directorio en la raiz del proyecto
	 * 
	 * @see ConstantesRutas
	 * @return crearDirectorioLog Devuelve true si el directorio se ha creado
	 *         correctamente, false en el caso de que exista el directorio.
	 */

	public static boolean crearDirectorioLog() {

		Path rutaDirectorio = Paths.get(ConstantesRutas.DIRECTORIO_LOG); // Crea la ruta donde se va a ubicar dentro del
																		// proyecto.

		try {
			if (!Files.exists(rutaDirectorio)) {
				Files.createDirectories(rutaDirectorio);
				System.out.println("Directorio creado con exito.");
				return true;
			}
		} catch (Exception errorDirectorio) {

		}

		return false;

	}
	
	public static boolean creaArchivoLog() {
		if(crearDirectorioLog()) {
			
			
			
			
			
		}
		return false;
		
		
		
	}
	
	
	
	

}
