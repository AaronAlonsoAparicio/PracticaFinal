package registroLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import constantes.ConstantesRutas;

/**
 * Clase que genera el log del juego con cada una de las acciones que se
 * realicen en el programa
 */
public class Historico {

	/**
	 * Crea un directorio en la raiz del proyecto
	 * 
	 * @see ConstantesRutas
	 * @return crearDirectorioLog Devuelve true si el directorio se ha creado
	 *         correctamente, false en el caso de que exista el directorio.
	 */

	public static boolean crearDirectorioHistorico() {

		Path rutaDirectorio = Paths.get(ConstantesRutas.DIRECTORIO_HISTORICO); // Crea la ruta donde se va a ubicar dentro del
																		// proyecto.

		try {
			if (!Files.exists(rutaDirectorio)) {
				Files.createDirectories(rutaDirectorio);
				System.out.println("Directorio creado con exito.");
				return true;
			}
		} catch (Exception errorDirectorio) {
			System.out.println("No se ha podido crear el directorio.");
			System.out.println("Ruta no valida o el directorio ya existe");
			System.out.println(errorDirectorio);
		}

		return false;

	}
	
	/**
	 *  Crea el fichero log para guardar los resultados de las partidas
	 * @return True si se crea correctamente el fichero.
	 */
	public static boolean creaArchivoHistorico() {
		if(crearDirectorioHistorico()) {
			
			Path rutaFicheroHistorico = Paths.get(ConstantesRutas.ARCHIVO_HISTORICO);
			try {
				if(!Files.exists(rutaFicheroHistorico)) {
					Files.createFile(rutaFicheroHistorico);
					System.out.println("Archivo creado con exito");
					return true;
				}
				
			} catch (Exception errorFichero) {
				System.out.println("Error al encontrar la ruta" + errorFichero);
			}
			
		}
		return false;
		
		
	}
	
	public static void almacenarInforamcionJugadores(String mensaje) {
        Path rutaFicheroHistorico = Paths.get(ConstantesRutas.ARCHIVO_HISTORICO);
		
	
		if(crearDirectorioHistorico()) {
            try {
				Files.writeString(rutaFicheroHistorico, mensaje + System.lineSeparator(), StandardOpenOption.APPEND);
			} catch (IOException errorFichero) {
				System.out.println("Error al escribir en el log" + errorFichero.getMessage());
			}
			
			
		}
		
		
		
	}
	
	
	
	
	

}
