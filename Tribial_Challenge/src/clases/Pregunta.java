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
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.Expression;
import constantes.ConstantesExpresionesMatematicas;
import constantes.ConstantesPreguntas;
import constantes.ConstantesRutas;

/**
 * Clase que define los atributos y metodos de las preguntas del programa.
 */
public class Pregunta {


	private int numeroPregunta;

	
	static Scanner respuesta;

	static List<String> lineasDiccionario = null;
	static List<String> preguntasIngles = null;

	/**
	 * Metodo que nos genera un numero aleatorio del 1 al 3
	 * 
	 * @return tipoDePreguntaGenerada
	 */
	private static int preguntas() {
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
			matematicas(); // genera las preguntas de matematicas

		} else if (tipoPregunta == ConstantesPreguntas.PREGUNTA_LENGUA) {
			lengua(); // Genera las preguntas de lengua

		} else {
			ingles(); //Genera las preguntas de ingles
		

		}
	}

	
	//Empezamos con las preguntas de matematicas
	/**
	 * Estructura de las preguntas de matematicas
	 */
	private static void matematicas() {
		System.out.println("PREGUNTA DE MATEMATICAS");
		System.out.println("Dada esta expresion matematica ¿Podrias decirme cual es el resultado?");
		String operacionAresolver = expresionMatematica(4, 8);
		System.out.println(operacionAresolver);
		
		Expression expresionMatematica = new ExpressionBuilder(operacionAresolver).build();
		//La solucion solo puede ser un numero entero para la simpleza del resultado
		int resultadoOperacion = (int) expresionMatematica.evaluate();
		//System.out.println(resultadoOperacion);
		System.out.println("¿Cúal es el resultado de la operacion?");
		respuesta = new Scanner(System.in);
		int resultadoUsuario = respuesta.nextInt();
		if (resultadoUsuario == resultadoOperacion) {
			System.out.println("Enhorabuena, la respuesta es correcta + 1 punto ");
			
		}else {
			System.out.println("Buen intento, la proxima seguro que lo adivinas");
		}
	}
	
	/**
	 * Generamos las expresiones matematicas que el juegador va a tener que resolver
	 * @param minimoOperadores                Numero minimo de numeros que se van a usar
	 * @param maximoOperadores				  Numero maximo de numeros que se van a usar
	 * @return expresionGenerada              Nos devulve las expresion matematica
	 */
	private static String expresionMatematica(int minimoOperadores, int maximoOperadores) {
	    Random objetosAleatorios = new Random();
	    int numeroDeOperadores = objetosAleatorios.nextInt(maximoOperadores - minimoOperadores + 1) + minimoOperadores;
	    int numerosValidosParaOperacion = objetosAleatorios.nextInt(11) + 2; // Números de la operación serán del 2 al 12

	    // Inicializamos la expresión con el primer número
	    String expresionGenerada = Integer.toString(numerosValidosParaOperacion);

	    String[] operadoresMatematicos = {
	        ConstantesExpresionesMatematicas.EXPRESION_SUMA,
	        ConstantesExpresionesMatematicas.EXPRESION_RESTA,
	        ConstantesExpresionesMatematicas.EXPRESION_MULTIPLICACION
	    };
	    String ultimoOperadorUtilizado = "";

	    for (int contador = 0; contador < numeroDeOperadores; contador++) {
	        // Elegimos un operado aleatorio para empezar la expresion matematica
	        String operador = operadoresMatematicos[objetosAleatorios.nextInt(operadoresMatematicos.length)];

	        // No puede haber dos multiplicaciones seguidas
	        while (ultimoOperadorUtilizado.equals(ConstantesExpresionesMatematicas.EXPRESION_MULTIPLICACION) && operador.equals(ConstantesExpresionesMatematicas.EXPRESION_MULTIPLICACION)) {
	            operador = operadoresMatematicos[objetosAleatorios.nextInt(operadoresMatematicos.length)];
	        }

	        int siguienteNumero = objetosAleatorios.nextInt(11) + 2;
	        expresionGenerada += " " + operador + " " + siguienteNumero; 
	        ultimoOperadorUtilizado = operador;
	    }

	    return expresionGenerada;
	}

	
	
	//Empezamos con las preguntas de Lengua
	
	/**
	 * Estructura que siguen las preguntas de lengua
	 */
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
	
	
	/**
	 * Define las preguntas de ingles
	 */
	public static void ingles(){
		System.out.println("PREGUNTA DE INGLES");
		System.out.println("Dada esta pregunta, dime cual de las siguientes opciones es la correcta para sumar puntos");
		 Path rutaArchivoIngles = Paths.get(ConstantesRutas.archivoPreguntasIngles);
	        leerArchivoIngles(rutaArchivoIngles);
		
	}

	/**
	 * Metodo para leer y generar las preguntas y sus repuestas 
	 * @param rutaArchivoIngles      Ruta del archivo donde estas todas las preguntas de ingles.
	 */
	private static void leerArchivoIngles(Path rutaArchivoIngles) {
		if (!Files.exists(rutaArchivoIngles)) {
		    System.out.println("Archivo no encontrado");
		} else {
		    try (BufferedReader preguntaDeIngles = new BufferedReader(new FileReader(ConstantesRutas.archivoPreguntasIngles))) {
		   
		        List<String> seleccionarPregunta = new ArrayList<>();
		        List<List<String>> respuestas = new ArrayList<>();

		        extraerPreguntasRespuestas(preguntaDeIngles, seleccionarPregunta, respuestas);

		        elegirPregunta(seleccionarPregunta, respuestas);

		    } catch (IOException e) {
		        System.err.println("Error al leer el archivo: " + e.getMessage());
		    }
		}
	}
	/**
	 * Metodo que nos extrae una de las preguntas y sus respuestas
	 * @param preguntaDeIngles             Lee las pregunas de ingles
	 * @param seleccionarPregunta          Escoge una de las pregunas leidas
	 * @param respuestas				   Coje las respuestas	
	 * @throws IOException                 Lanza una excepcion en el caso en el caso de haber algun error relaccionado con el archivo
	 */
	private static void extraerPreguntasRespuestas(BufferedReader preguntaDeIngles, List<String> seleccionarPregunta,
			List<List<String>> respuestas) throws IOException {
		String linea;
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
	}
	
	/**
	 * Elige aleatoriamente una de las preguntas de ingles y sus respuestas
	 * @param seleccionarPregunta      Seleciona una de las preguntas del archivo
	 * @param respuestas               Seleciona las repuestas de la pregunta generada
	 */

	private static void elegirPregunta(List<String> seleccionarPregunta, List<List<String>> respuestas) {
		if (!seleccionarPregunta.isEmpty()) {
		    Random elegirPreguntaExtraida = new Random();
		    int preguntaFinal = elegirPreguntaExtraida.nextInt(seleccionarPregunta.size());
		    System.out.println(seleccionarPregunta.get(preguntaFinal));
		    List<String> respuestasSeleccionadas = respuestas.get(preguntaFinal);
		    for (String respuesta : respuestasSeleccionadas) {
		        System.out.println(respuesta);
		    }
		}
	}
	
	public int getTipoPregunta() {
		return numeroPregunta;
	}
	
	
	
	
	
	
	
	

	public static void main(String[] args) throws IOException {
		generarPreguntas();
	}

}
