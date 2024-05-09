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
				Files.createDirectories(rutaDirectorio);
				System.out.println("Directorio creado con exito.");
			}

			Path rutaFicheroHistorico = Paths.get(ConstantesRutas.ARCHIVO_HISTORICO);
			if (!Files.exists(rutaFicheroHistorico)) {
				Files.createFile(rutaFicheroHistorico);
				System.out.println("Archivo creado con exito");
			}

		} catch (Exception errorDirectorio) {
			System.out.println("No se ha podido crear el directorio.");
			System.out.println("Ruta no valida o el directorio ya existe");
			System.out.println(errorDirectorio);
		}

	}

	public static void almacenarInforamcionJugadores(String mensaje) {
		Path rutaFicheroHistorico = Paths.get(ConstantesRutas.ARCHIVO_HISTORICO);

		try {
			Files.writeString(rutaFicheroHistorico, mensaje + System.lineSeparator(), StandardOpenOption.APPEND);
		} catch (IOException errorFichero) {
			System.out.println("Error al escribir en el historico" + errorFichero.getMessage());
		}

	}

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
