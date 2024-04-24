package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	 * @param tipoPregunta unos da un numero conrrespondinte a cada tipo de
	 *                     pregunta.
	 * @throws ExcepcionesFicheros
	 * @throws IOException
	 * 
	 */
	public static void generarPreguntas(int tipoPregunta) throws IOException {
		tipoPregunta = preguntas();
		if (tipoPregunta == ConstantesPreguntas.PREGUNTA_MATES) {

		} else if (tipoPregunta == ConstantesPreguntas.PREGUNTA_LENGUA) {
			Random palabraAleatoria = new Random();
//			int palabraEscogida = palabraAleatoria.nextInt(linasMaximasDiccionario(rutaArchivoDiccionario) + 1);

		} else {
			Path rutaArchivoInlges = Paths.get(ConstantesRutas.archivoPreguntasIngles);
			int lineaInicial;
			int lineaFinal;
		}

	}

	/**
	 * Metodo que nos cuenta el número de líneas que tiene el fichero diccionario.tx
	 * 
	 * @param archivoDiccionario Fichero al cual le vamos a contar las líneas.
	 * @return numeroTotalDeLineas
	 * @throws ExcepcionesFicheros Errores relacionados con los ficheros.
	 */
	public static int linasMaximasDiccionario(String archivoDiccionario) throws IOException {

	}
}
