package registrosSalida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import clases.Jugador;
import clases.Partida;
import constantes.ConstantesRutas;

public class Ranking {

	/**
	 * Metodo que crea el archivo rankign.txt
	 */

	public static void crearRanking() {

		Path rutaDirectorio = Paths.get(ConstantesRutas.DIRECTORIO_SALIDA); // Crea la ruta donde se va a ubicar
																			// dentro del proyecto.

		try {
			if (!Files.exists(rutaDirectorio)) {
				Files.createDirectories(rutaDirectorio); // Crea el directorio si no existe

			}

			Path rutaFicheroRanking = Paths.get(ConstantesRutas.ARCHIVO_RANKING);
			if (!Files.exists(rutaFicheroRanking)) { // Crea el archivo historico.txt si se ha creado correctamente el
														// directorio.
				Files.createFile(rutaFicheroRanking);

			}

		} catch (Exception errorDirectorio) {
			System.out.println("No se ha podido crear el directorio.");
			System.out.println("Ruta no valida o el directorio ya existe"); // Mensajes de error en el caso de no
																			// haberse podido crear correctamente.
			System.out.println(errorDirectorio);
		}

	}

	public static void almacenarRaking() {
	    // Acceder directamente a la lista de jugadores en Partida
		List<Jugador> jugadoresHumanos = new ArrayList<>();
		
		
		if(Partida.jugadores != null) {
	     jugadoresHumanos = Arrays.stream(Partida.jugadores)
	            .filter(jugador -> jugador != null && !jugador.getNombre().startsWith("CPU"))
	            .collect(Collectors.toList());
		}
	    // Ordena la lista de jugadores filtrada
	    Collections.sort(jugadoresHumanos, (j1, j2) -> Integer.compare(j2.getPreguntasRespondidasCorrectas(),
	            j1.getPreguntasRespondidasCorrectas()));

	    List<String> lineasRanking = new ArrayList<>();
	    lineasRanking.add("*** RANKING  *** ");
	    for (Jugador jugador : jugadoresHumanos) {
	    	if (jugador != null) {
	        String jugadorRanking = jugador.getNombre() + " " + jugador.getPreguntasRespondidasCorrectas();
	        lineasRanking.add(jugadorRanking);
	    	}
	    }
	    
	    Path rutaArchivoRanking = Paths.get(ConstantesRutas.ARCHIVO_RANKING);
	    try {
	        Files.write(rutaArchivoRanking, lineasRanking);
	    } catch (IOException error) {
	        System.out.println("Error al guardar la informacion" + error.getMessage());
	    }
	}


	/**
	 * Método que muestra el ranking de jugadores ordenado por puntuación.
	 */
	public static void mostrarRanking() {
		Path rutaArchivoRanking = Paths.get(ConstantesRutas.ARCHIVO_RANKING);

		if (Files.exists(rutaArchivoRanking)) {
			try {
				List<String> leerRanking = Files.readAllLines(rutaArchivoRanking);
				for (String ranking : leerRanking) {
					System.out.println(ranking);

				}

			} catch (Exception error) {
				System.out.println("Error al leer el archivo " + error.getMessage());
			}

		} else {

		}

	}

}
