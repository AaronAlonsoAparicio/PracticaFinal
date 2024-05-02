package clases;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import constantes.ConstantesPreguntas;
import constantes.ConstantesRutas;

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
	 */
	public static void generarPreguntas() throws IOException {
		int tipoPregunta = preguntas();
		if (tipoPregunta == ConstantesPreguntas.PREGUNTA_MATES) {

		} else if (tipoPregunta == ConstantesPreguntas.PREGUNTA_LENGUA) {
			System.out.println("PREGUNTA DE LENGUA");
			System.out.print("Te damos esta palabra: ");
			preguntasDeLengua();
			System.out.println("Ahora tienes que adivinar la palabra completa para sumar puntos.");
			System.out.println("¿De qué palabra crees que se trata?");
			Scanner palabraAdivinada = new Scanner(System.in);
			String palabraPropuestaUsuario = palabraAdivinada.next();
			

			

		} else {
			Path rutaArchivoInlges = Paths.get(ConstantesRutas.archivoPreguntasIngles);
			int lineaInicial;
			int lineaFinal;
		}

	}
	
	/**
	 * Código que genera las preguntas de Lengua
	 * @since 1.1
	 */
	
	public static void preguntasDeLengua() {
		Random palabraAleatoria = new Random();
		int palabraEscogida = palabraAleatoria.nextInt(lineasMaximasDiccionario() + 1);
		String palabraAUsar = lineasDiccionario.get(palabraEscogida);
		String palabraOculta = ocultarLetras(palabraAUsar);
		System.out.print(palabraOculta);
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

	/**
	 * Metodo para ocultar las letras de la palabra escogida del diccionario.
	 * 
	 * @param palabraAdivinar Palabra del diccionario escogida para ocultar el x
	 *                        numeros de letras.
	 * @return caracterAOcultar La palabra con las letras ocultas
	 */
	public static String ocultarLetras(String palabraAdivinar) {
		Random letraAleatroriaAOcultar = new Random();
		char[] caracterAOcultar = palabraAdivinar.toCharArray();
		int cantidadALetrasOcultar = palabraAdivinar.length() / 3;
		Set<Integer> posicionesOcultas = new HashSet<>();
		while (posicionesOcultas.size() < cantidadALetrasOcultar) {
			int posicionLetaOculta = letraAleatroriaAOcultar.nextInt(palabraAdivinar.length());
			posicionesOcultas.add(posicionLetaOculta);
		}
		for (int posicionLetraOculta : posicionesOcultas) {
			caracterAOcultar[posicionLetraOculta] = '*';
		}
		return new String(caracterAOcultar);
	}
	
	public static void main(String[] args) throws IOException {
		generarPreguntas();
	}

}
