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

import constantes.ConstantesRutas;

public class PreguntaLengua{
	
	
	static List<String> lineasDiccionario = null;
	
	static boolean lengua() {
		System.out.println("PREGUNTA DE LENGUA");
		System.out.print("Te damos esta palabra: ");
		String palabraAAdivinar = preguntasDeLengua("");
		System.out.println("Ahora tienes que adivinar la palabra completa para sumar puntos.");
		System.out.println("¿De qué palabra crees que se trata?");
		Scanner palabraAdivinada = new Scanner(System.in);
		String palabraUsuario = palabraAdivinada.next();
		if (palabraUsuario.equalsIgnoreCase(palabraAAdivinar)) {
			System.out.println("Enhorabuenas has acertado te llevas 1 punto");
			return true;

		} else {
			System.out.println("OHH te has equivocado la palabra es: " + palabraAAdivinar + " la proxima vez sera.");
			return false;
		}

	}

	/**
	 * Código que genera las preguntas de Lengua
	 * 
	 * @return
	 * 
	 * @since 1.1
	 */

	public static String preguntasDeLengua(String palabraAUsar) {
		Random palabraAleatoria = new Random();
		int palabraEscogida = palabraAleatoria.nextInt(lineasMaximasDiccionario() + 1);
		palabraAUsar = lineasDiccionario.get(palabraEscogida);
		String palabraOculta = ocultarLetras(palabraAUsar);
		System.out.println(palabraOculta);
		return palabraAUsar;

	}

	/**
	 * Metodo que nos cuenta el número de líneas que tiene el fichero diccionario.tx
	 * 
	 * @return lineasDiccionario Número total de lineas del archivo diccionario.txt
	 * @throws ExcepcionesFicheros Errores relacionados con los ficheros.
	 */

	public static int lineasMaximasDiccionario() {
		Path archivoDiccionario = Paths.get(ConstantesRutas.ARCHIVO_DICCIONARIO);

		if (!Files.exists(archivoDiccionario)) {
			System.out.println("El archivo en la ruta " + ConstantesRutas.ARCHIVO_DICCIONARIO
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


	
}
