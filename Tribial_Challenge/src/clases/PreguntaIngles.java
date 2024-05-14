package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import constantes.ConstantesRutas;

public class PreguntaIngles {
	
	
	static List<String> preguntasIngles = null;
	
	
	
	/**
	 * Estructura que siguen las preguntas de lengua
	 */
	
	/**
	 * Define las preguntas de ingles
	 */
	public static boolean ingles() {
		System.out.println("PREGUNTA DE INGLES");
		System.out.println("Dada esta pregunta, dime cual de las siguientes opciones es la correcta para sumar puntos");
		Path rutaArchivoIngles = Paths.get(ConstantesRutas.ARCHIVO_PREGUNTAS_INGLES);
		return leerArchivoIngles(rutaArchivoIngles);

	}

	/**
	 * Metodo para leer y generar las preguntas y sus repuestas
	 * 
	 * @param rutaArchivoIngles Ruta del archivo donde estas todas las preguntas de
	 *                          ingles.
	 */
	private static boolean leerArchivoIngles(Path rutaArchivoIngles) {
		if (!Files.exists(rutaArchivoIngles)) {
			System.out.println("Archivo no encontrado");
			return false;
		} else {
			try (BufferedReader preguntaDeIngles = new BufferedReader(
					new FileReader(ConstantesRutas.ARCHIVO_PREGUNTAS_INGLES))) {

				List<String> seleccionarPregunta = new ArrayList<>();
				List<List<String>> respuestas = new ArrayList<>();

				extraerPreguntasRespuestas(preguntaDeIngles, seleccionarPregunta, respuestas);

				return elegirPregunta(seleccionarPregunta, respuestas);

			} catch (IOException e) {
				System.err.println("Error al leer el archivo: " + e.getMessage());
				return false;
			}
		}
	}

	/**
	 * Metodo que nos extrae una de las preguntas y sus respuestas
	 * 
	 * @param preguntaDeIngles    Lee las pregunas de ingles
	 * @param seleccionarPregunta Escoge una de las pregunas leidas
	 * @param respuestas          Coje las respuestas
	 * @throws IOException Lanza una excepcion en el caso en el caso de haber algun
	 *                     error relaccionado con el archivo
	 */
	 private static void extraerPreguntasRespuestas(BufferedReader preguntaDeIngles, List<String> seleccionarPregunta,
	            List<List<String>> respuestas) throws IOException {
	        String linea;
	        while ((linea = preguntaDeIngles.readLine()) != null) {
	            if (linea.trim().endsWith("?")) {
	                seleccionarPregunta.add(linea);
	                List<String> respuestasActuales = new ArrayList<>();
	                // Leer la respuesta correcta
	                linea = preguntaDeIngles.readLine();
	                if (linea != null) {
	                    respuestasActuales.add(linea);
	                }
	                // Leer las tres respuestas incorrectas
	                for (int contadorLineas = 0; contadorLineas < 3; contadorLineas++) {
	                    linea = preguntaDeIngles.readLine();
	                    if (linea != null) {
	                        respuestasActuales.add(linea);
	                    }
	                }
	                respuestas.add(respuestasActuales);
	            }
	        }
	    }
	/**
	 * Elige aleatoriamente una de las preguntas de ingles y sus respuestas
	 * 
	 * @param seleccionarPregunta Seleciona una de las preguntas del archivo
	 * @param respuestas          Seleciona las repuestas de la pregunta generada
	 */

	private static boolean elegirPregunta(List<String> seleccionarPregunta, List<List<String>> respuestas) {
		if (!seleccionarPregunta.isEmpty()) {
			Random elegirPreguntaExtraida = new Random();
			int preguntaFinal = elegirPreguntaExtraida.nextInt(seleccionarPregunta.size());
			System.out.println(seleccionarPregunta.get(preguntaFinal));
			List<String> respuestasSeleccionadas = new ArrayList<>(respuestas.get(preguntaFinal));
			Collections.shuffle(respuestasSeleccionadas);
		
			char[] opcion = {'A','B','C','D'};
			for (int respuesta = 0; respuesta < respuestasSeleccionadas.size(); respuesta++) {
                System.out.println(opcion[respuesta] + ") " + respuestasSeleccionadas.get(respuesta));
            }
			Scanner teclado = new Scanner(System.in);
			char respuestaUsuario = teclado.next().charAt(0);
			int respuestaCorrecta = respuestaUsuario - 'A';
			
			 if (respuestaCorrecta < 0 || respuestaCorrecta >= respuestasSeleccionadas.size()) {
	                System.out.println("Opción inválida.");
	                return false;
	            }
			String correcta = respuestas.get(preguntaFinal).get(0);
			String opcionRespuesta = respuestasSeleccionadas.get(respuestaCorrecta);
			boolean acierto = opcionRespuesta.equals(correcta);
            if (!acierto) {
                System.out.println("Respuesta incorrecta. La respuesta correcta era: " + correcta);
            }
            return acierto;
			
			
		}
		return false;
	}

	
}
