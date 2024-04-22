package clases;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import constantes.ConstantesPreguntas;

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
	 * 
	 */
	public static void generarPreguntas(int tipoPregunta) {
		tipoPregunta = preguntas();
		if (tipoPregunta == ConstantesPreguntas.PREGUNTA_MATES) {

		} else if (tipoPregunta == ConstantesPreguntas.PREGUNTA_LENGUA) {

		} else {

			Path rutaArchivoInlges = Paths.get("./../../materiales/ingles.txt");

		}

	}

}
