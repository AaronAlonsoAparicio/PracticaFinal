package clases;

import java.util.Random;
import java.util.Scanner;

import constantes.ConstantesExpresionesMatematicas;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class PreguntaMatematicas {
	
	static Scanner respuesta;
	
	
	
	
	/**
	 * Estructura de las preguntas de matematicas
	 */
	public static boolean matematicas() {
		System.out.println("PREGUNTA DE MATEMATICAS");
		System.out.println("Dada esta expresion matematica ¿Podrias decirme cual es el resultado?");
		String operacionAresolver = expresionMatematica(4, 8);
		System.out.println(operacionAresolver);

		Expression expresionMatematica = new ExpressionBuilder(operacionAresolver).build();
		// La solucion solo puede ser un numero entero para la simpleza del resultado
		int resultadoOperacion = (int) expresionMatematica.evaluate();
		// System.out.println(resultadoOperacion);
		System.out.println("¿Cúal es el resultado de la operacion?");
		respuesta = new Scanner(System.in);
		int resultadoUsuario = respuesta.nextInt();
		if (resultadoUsuario == resultadoOperacion) {
			System.out.println("Enhorabuena, la respuesta es correcta + 1 punto ");
			return true;

		} else {
			System.out.println("Buen intento, la proxima seguro que lo adivinas");
			return false;
		}
	}

	/**
	 * Generamos las expresiones matematicas que el juegador va a tener que resolver
	 * 
	 * @param minimoOperadores Numero minimo de numeros que se van a usar
	 * @param maximoOperadores Numero maximo de numeros que se van a usar
	 * @return expresionGenerada Nos devulve las expresion matematica
	 */
	private static String expresionMatematica(int minimoOperadores, int maximoOperadores) {
		Random objetosAleatorios = new Random();
		int numeroDeOperadores = objetosAleatorios.nextInt(maximoOperadores - minimoOperadores + 1) + minimoOperadores;
		int numerosValidosParaOperacion = objetosAleatorios.nextInt(11) + 2; // Números de la operación serán del 2 al
																				// 12

		// Inicializamos la expresión con el primer número
		String expresionGenerada = Integer.toString(numerosValidosParaOperacion);

		String[] operadoresMatematicos = { ConstantesExpresionesMatematicas.EXPRESION_SUMA,
				ConstantesExpresionesMatematicas.EXPRESION_RESTA,
				ConstantesExpresionesMatematicas.EXPRESION_MULTIPLICACION };
		String ultimoOperadorUtilizado = "";

		for (int contador = 0; contador < numeroDeOperadores; contador++) {
			// Elegimos un operado aleatorio para empezar la expresion matematica
			String operador = operadoresMatematicos[objetosAleatorios.nextInt(operadoresMatematicos.length)];

			// No puede haber dos multiplicaciones seguidas
			while (ultimoOperadorUtilizado.equals(ConstantesExpresionesMatematicas.EXPRESION_MULTIPLICACION)
					&& operador.equals(ConstantesExpresionesMatematicas.EXPRESION_MULTIPLICACION)) {
				operador = operadoresMatematicos[objetosAleatorios.nextInt(operadoresMatematicos.length)];
			}

			int siguienteNumero = objetosAleatorios.nextInt(11) + 2;
			expresionGenerada += " " + operador + " " + siguienteNumero;
			ultimoOperadorUtilizado = operador;
		}

		return expresionGenerada;
	}
	
	

}
