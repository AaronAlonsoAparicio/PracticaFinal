package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	static List<String> preguntasIngles = null;

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
			lengua(); // Genera las preguntas de lengua

		} else {
			ingles();
		

		}
	}

	private static void lengua() {
		System.out.println("PREGUNTA DE LENGUA");
		System.out.print("Te damos esta palabra: ");
		preguntasDeLengua();
		System.out.println("Ahora tienes que adivinar la palabra completa para sumar puntos.");
		System.out.println("¿De qué palabra crees que se trata?");
		Scanner palabraAdivinada = new Scanner(System.in);
		String palabraPropuestaUsuario = palabraAdivinada.next();
	}

	/**
	 * Código que genera las preguntas de Lengua
	 * 
	 * @since 1.1
	 */

	public static void preguntasDeLengua() {
		Random palabraAleatoria = new Random();
		int palabraEscogida = palabraAleatoria.nextInt(lineasMaximasDiccionario() + 1);
		String palabraAUsar = lineasDiccionario.get(palabraEscogida);
		String palabraOculta = ocultarLetras(palabraAUsar);
		System.out.println(palabraOculta);
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
	
	public static void ingles(){
		 Path rutaArchivoIngles = Paths.get(ConstantesRutas.archivoPreguntasIngles);
	        if (!Files.exists(rutaArchivoIngles)) {
	            System.out.println("Archivo no encontrado");
	        } else {
	            try (BufferedReader preguntaDeIngles = new BufferedReader(new FileReader(ConstantesRutas.archivoPreguntasIngles))) {
	                String linea;
	                List<String> seleccionarPregunta = new ArrayList<>();
	                List<List<String>> respuestas = new ArrayList<>();

	                while ((linea = preguntaDeIngles.readLine()) != null) {
	                    if (linea.trim().endsWith("?")) {
	                        seleccionarPregunta.add(linea);
	                        List<String> respuestasActuales = new ArrayList<>();
	                        for (int contadorLineas = 0; contadorLineas < 4; contadorLineas++) {  // Asumimos que siempre hay 4 líneas de respuestas después de cada pregunta
	                            linea = preguntaDeIngles.readLine();
	                            if (linea != null) {
	                                respuestasActuales.add(linea);
	                            } else {
	                                break;  
	                            }
	                        }
	                        respuestas.add(respuestasActuales);
	                    }
	                }

	                if (!seleccionarPregunta.isEmpty()) {
	                    Random elegirPreguntaExtraida = new Random();
	                    int preguntaFinal = elegirPreguntaExtraida.nextInt(seleccionarPregunta.size());
	                    System.out.println(seleccionarPregunta.get(preguntaFinal));
	                    List<String> respuestasSeleccionadas = respuestas.get(preguntaFinal);
	                    for (String respuesta : respuestasSeleccionadas) {
	                        System.out.println(respuesta);
	                    }
	                }

	            } catch (IOException e) {
	                System.err.println("Error al leer el archivo: " + e.getMessage());
	            }
	        }
		
	}
	

	
	
	
	
	
	
	
	

	public static void main(String[] args) throws IOException {
		generarPreguntas();
	}

}
