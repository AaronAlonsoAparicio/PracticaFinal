package registrosSalida;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clases.Jugador;
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
	
	/**
     * Método que muestra el ranking de jugadores ordenado por puntuación.
     * 
     * @param jugadores Lista de jugadores a mostrar en el ranking.
     */
    public static void mostrarRanking(ArrayList<Jugador> jugadores) {
        // Calcular el ranking (ordenar la lista de jugadores por puntaje)
        Collections.sort(jugadores, (j1, j2) -> Integer.compare(j2.getPuntuacion(), j1.getPuntuacion()));

        // Mostrar el ranking
        System.out.println("*** RANKING ***");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + " " + jugador.getPuntuacion());
        }
    }

}
