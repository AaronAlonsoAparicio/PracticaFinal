package clases;

import java.util.Random;
import java.util.Scanner;

import constantes.ConstantesJugador;
import constantes.ConstantesPreguntas;

public class Partida {
	private static Pregunta preguntas;
	private static Jugador[] jugadores;

	// TODO: Crear un for para crear los jugadores y poderles sumar puntos.
	private static int preguntas() {
		Random preguntaAleatoria = new Random();
		int tipoDePreguntaGenerada = preguntaAleatoria.nextInt(3) + 1;
		return tipoDePreguntaGenerada;
	}

	public static void generarPartida() {
		preguntas = new Pregunta();
		jugadores = new Jugador[ConstantesJugador.MAX_JUGADORES]; // indicamos el numero maximo de jugadores que vamos
																	// a usar en la partida
		Scanner nombre = new Scanner(System.in);
		String nombreJugador = nombre.next();
		Jugador jugador = new Jugador(nombreJugador);
		// Creamos los jugadores depende de los jugadores Humanos que vayamos a usar.
		for (int numeroJugador = 0; numeroJugador < jugadores.length; numeroJugador++) {
			jugadores[0] = jugador;
		}

		int tipoPregunta = preguntas();

		if (tipoPregunta == ConstantesPreguntas.PREGUNTA_MATES) {
			preguntas.matematicas();

		} else if (tipoPregunta == ConstantesPreguntas.PREGUNTA_LENGUA) {
			if (preguntas.lengua()) {
				jugadores[0].sumarPunto(1);
			}

		} else {
			preguntas.ingles(); // Genera las preguntas de ingles

		}
	}

	public static void main(String[] args) {
		generarPartida();
	}

}
