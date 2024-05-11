package registrosSalida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

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

	public static void crearHistorico() {

		Path rutaDirectorio = Paths.get(ConstantesRutas.DIRECTORIO_SALIDA); // Crea la ruta donde se va a ubicar
																			// dentro del  proyecto.

		try {
			if (!Files.exists(rutaDirectorio)) {
				Files.createDirectories(rutaDirectorio); //Crea el directorio si no existe
				
			}

			Path rutaFicheroHistorico = Paths.get(ConstantesRutas.ARCHIVO_HISTORICO);
			if (!Files.exists(rutaFicheroHistorico)) {  //Crea el archivo historico.txt si se ha creado correctamente el directorio.
				Files.createFile(rutaFicheroHistorico);
				
			}

		} catch (Exception errorDirectorio) {
			System.out.println("No se ha podido crear el directorio.");
			System.out.println("Ruta no valida o el directorio ya existe"); // Mensajes de error en el caso de no haberse podido crear correctamente.
			System.out.println(errorDirectorio);
		}

	}
	/**
	 * Metodo para almacenar informacion en el archivo historico.txt
	 * @param mensaje     Contenido la informacion a almacenar
	 */

	public static void almacenarInforamcionJugadores(String mensaje) {
		Path rutaFicheroHistorico = Paths.get(ConstantesRutas.ARCHIVO_HISTORICO);

		try {
			Files.writeString(rutaFicheroHistorico, mensaje + System.lineSeparator(), StandardOpenOption.APPEND); // StandarOpenOption.APPEND Guarda la inforamcion al final 
																																		   // del archivo sin sobreescribir
		} catch (IOException errorFichero) {
			System.out.println("Error al escribir en el historico" + errorFichero.getMessage());
		}

	}
	
	/**
	 * Clase  que lee el archivo historico.txt linea a linea y las enseña por pantalla
	 */
	public static void mostrarHistorico() {
		Path rutaHistorico = Paths.get(ConstantesRutas.ARCHIVO_HISTORICO);
		if (Files.exists(rutaHistorico)) {
			try {
				List<String> lineasHistorico = Files.readAllLines(rutaHistorico); //Leemos todas la lineas del archivo 
				for (String linea : lineasHistorico) { //Mostramos todas las lineas
					System.out.println(linea);

				}

			} catch (Exception error) {
				System.out.println("Error al leer el archivo historico.txt" + error.getMessage());
			}

		}
	}

}
