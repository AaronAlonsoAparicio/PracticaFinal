package clases;

import java.util.Random;
import java.util.Scanner;

import constantes.ConstantesJugador;
import constantes.ConstantesPreguntas;
import constantes.ConstantesTipoPartida;

//TODO: Que se termine el bucle al terminar todos los turnos, comentar y extraer a metodos y hacer constantes algunas variables fijas.
public class Partida {
	private static Pregunta preguntas;
	private static Jugador[] jugadores;
	static Scanner teclado;

	private static int preguntas() {
		Random preguntaAleatoria = new Random();
		return preguntaAleatoria.nextInt(3) + 1;
	}

	public static void generarPartida() {
		preguntas = new Pregunta();
		jugadores = new Jugador[ConstantesJugador.MAX_JUGADORES];
		teclado = new Scanner(System.in);

		System.out.println("¿Cuantos jugadores van a participar?");
		int cantidadJugadores = teclado.nextInt();

		// Creamos los jugadores depende de los jugadores Humanos que vayamos a usar.
		for (int numeroJugador = 0; numeroJugador < ConstantesJugador.MAX_JUGADORES; numeroJugador++) {
			if (numeroJugador < cantidadJugadores) {
				System.out.println("Nombre del jugador:" + (numeroJugador + 1) + ":");
				String nombreJugador = teclado.next();
				jugadores[numeroJugador] = new Jugador(nombreJugador);

			} else {
				jugadores[numeroJugador] = new Jugador("CPU" + numeroJugador);

			}

		}
		System.out.println("¿Qué tipo de pasrtida quieres jugar?");
		System.out.println("Tenemos varios tipos de partidas.");
		System.out.println("1) Partida Rapida. Tiene " + ConstantesTipoPartida.PARTIDA_RAPIDA + " turnos.");
		System.out.println("2) Partida Corta. Tiene " + ConstantesTipoPartida.PARTIDA_CORTA + " turnos.");
		System.out.println("3) Partida Normal. Tiene " + ConstantesTipoPartida.PARTIDA_NORMAL + " turnos.");
		System.out.println("4) Partida Larga. Tiene " + ConstantesTipoPartida.PARTIDA_LARGA + " turnos.");
		int tipoPartida = teclado.nextInt();
		int numeroTurnos = 0;
		do {
			switch (tipoPartida) {
			case 1:
				numeroTurnos = ConstantesTipoPartida.PARTIDA_RAPIDA;
				break;
			case 2:
				numeroTurnos = ConstantesTipoPartida.PARTIDA_CORTA;
				break;
			case 3:
				numeroTurnos = ConstantesTipoPartida.PARTIDA_NORMAL;
				break;
			case 4:
				numeroTurnos = ConstantesTipoPartida.PARTIDA_LARGA;
				break;

			default:
				break;
			}
			

			for (int turno = 1; turno < numeroTurnos; turno++) {
				System.out.println("Turno " + turno + " de " + numeroTurnos);
				for (Jugador jugador : jugadores) {
					System.out.println("Le toca al jugador " + jugador.getNombre());
					int preguntaFormulada = preguntas();
					boolean hasAcertado = false;
					switch (preguntaFormulada) {
					case ConstantesPreguntas.PREGUNTA_MATES:
						hasAcertado = preguntas.matematicas();

						break;
					case ConstantesPreguntas.PREGUNTA_LENGUA:
						hasAcertado = preguntas.lengua();

						break;
					case ConstantesPreguntas.PREGUNTA_INGLES:
						// TODO: Cambiar a boolean el metodo de ingles
						hasAcertado = preguntas.ingles();

						break;

					default:
						System.out.println("Opcion no valida. Escoge una opcion de 1 al 4. Gracias");
						break;
					}

					if (hasAcertado) {
						System.out.println("Has acertado, " + jugador.getNombre() + " enhorabuena sumas 1 punto");
						jugador.sumarPunto(1);

					} else {
						System.out.println("Fallaste, " + jugador.getNombre() + " la proxima vez sera:");
					}

				}

			}

			System.out.println("Asi quedan los marcadores.");
			for (Jugador jugador : jugadores) {
				jugador.imprimirInformacion();
			}

		} while (tipoPartida != 5);

	}

	public static void main(String[] args) {
		generarPartida();
	}

}
