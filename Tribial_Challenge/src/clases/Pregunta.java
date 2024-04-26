package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import constantes.ConstantesPreguntas;
import constantes.ConstantesRutas;
import excepciones.ExcepcionesFicheros;

/**
 * Clase que define los atributos y metodos de las preguntas del programa.
 */
public class Pregunta {

	private int numeroPregunta;

	public int getTipoPregunta() {
		return numeroPregunta;
	}

	static List<String> lineasDiccionario = null;

	/**
	 * Metodo que nos genera un numero aleatorio del 1 al 3
	 * 
	 * @return tipoDePreguntaGenerada
	 */
	public static int preguntas() {
		Random preguntaAleatoria = new Random();
		int tipoDePreguntaGenerada = preguntaAleatoria.nextInt(3) + 1;
		return tipoDePreguntaGenerada;
	}

	/**
	 * Metodo que nos va a generar las preguntas del programa.
	 * 
	 * 
	 * @throws ExcepcionesFicheros
	 * @throws IOException
	 * 
	 */
	public static void generarPreguntas() throws IOException {
		int tipoPregunta = preguntas();
		if (tipoPregunta == ConstantesPreguntas.PREGUNTA_MATES) {

		} else if (tipoPregunta == ConstantesPreguntas.PREGUNTA_LENGUA) {
			Random palabraAleatoria = new Random();
			int palabraEscogida = palabraAleatoria.nextInt(lineasMaximasDiccionario() + 1);
			// TODO: ocultar letras de la palabra obtenida.
			String palabraAUsar = lineasDiccionario.get(palabraEscogida);
			System.out.println(palabraAUsar);

		} else {
			Path rutaArchivoInlges = Paths.get(ConstantesRutas.archivoPreguntasIngles);
			int lineaInicial;
			int lineaFinal;
		}

	}

	/**
	 * Metodo que nos cuenta el número de líneas que tiene el fichero diccionario.tx
	 * 
	 * @return lineasDiccionario Número total de lineas del archivo diccionario.txt
	 * @throws ExcepcionesFicheros Errores relacionados con los ficheros.
	 */

	public static int lineasMaximasDiccionario() {
		Path archivoDiccionario = Paths.get(ConstantesRutas.archivoDiccionario);

		if (!Files.exists(archivoDiccionario)) {
			System.out.println("El archivo en la ruta " + ConstantesRutas.archivoDiccionario
					+ " no existe o no se encuentra en este directorio.");

		} else {
			try {
				lineasDiccionario = Files.readAllLines(archivoDiccionario);

			} catch (IOException errorFicheros) {

				errorFicheros.printStackTrace();
			}

		}
		return lineasDiccionario.size();

	}

	public static void main(String[] args) throws IOException {
		generarPreguntas();
	}

}
