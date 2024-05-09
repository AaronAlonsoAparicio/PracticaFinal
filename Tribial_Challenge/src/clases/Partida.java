package clases;

import java.util.Random;
import java.util.Scanner;

import constantes.ConstantesJugador;
import constantes.ConstantesPreguntas;
import constantes.ConstantesTipoPartida;
import registroLog.Historico;

//TODO: Hacer constantes algunas variables fijas.
/**
 * Clase que define las caracteristicas de las partidas y sus atributos.
 * 
 * @author Aaron Alonso
 */
public class Partida {
	private static Pregunta preguntas; // preguntas de la partida
	private static Jugador[] jugadores; // Array del numero de jugadores que hay en la partida
	static Scanner teclado; // Scanner para que el usuario nos de informacion

	/**
	 * Metodo que genenera aleatoriamente una de las distintas preguntas del
	 * programa
	 * 
	 * @return preguntaAleatoria genera un numero que identifica a cada una de las
	 *         preguntas
	 */
	private static int preguntas() {
		Random preguntaAleatoria = new Random();
		return preguntaAleatoria.nextInt(3) + 1;
	}

	/**
	 * Genera las partidas del juego.
	 */
	public static void generarPartida() {

		preguntas = new Pregunta();
		jugadores = new Jugador[ConstantesJugador.MAX_JUGADORES];
		teclado = new Scanner(System.in);

		System.out.println("¿Cuantos jugadores van a participar?");
		int cantidadJugadores = teclado.nextInt();

		creacionJugadores(cantidadJugadores);
		elegirPartida();
		int tipoPartida = teclado.nextInt();
		int numeroTurnos = ConstantesTipoPartida.NUMERO_TURNOS;
		partida(tipoPartida, numeroTurnos);

	}

	/**
	 * Genera la partirda que vamos a jugar
	 * 
	 * @param tipoPartida  Numero que define un tipo de partidas @see
	 *                     CostantesTipoPartida
	 * @param numeroTurnos Numero de turnos que va a terner la partida segun el
	 *                     tipoPartida
	 */
	private static void partida(int tipoPartida, int numeroTurnos) {
		int turnosPartida = 0;
		do {
			numeroTurnos = seleccionTipoPartida(tipoPartida, numeroTurnos);
			for (int turno = 1; turno < numeroTurnos; turno++) {
				System.out.println("Turno " + turno + " de " + numeroTurnos);
				for (Jugador jugador : jugadores) {
					System.out.println("Le toca al jugador " + jugador.getNombre());
					boolean hasAcertado = preguntasFormuladas();
					if (hasAcertado) {
						System.out.println("Has acertado, " + jugador.getNombre() + " enhorabuena sumas 1 punto");
						jugador.sumarPunto(1);

					} else {
						System.out.println("Fallaste, " + jugador.getNombre() + " la proxima vez sera:");
					}

				}

			}

			System.out.println("ASI QUEDAN LOS MARCADORES:");
			String registroPartida = "";
			for (Jugador jugador : jugadores) {
				jugador.imprimirInformacion();
				// Con esto comprobamos que el jugador es humano por que no empieza por CPU
				if (!jugador.getNombre().startsWith("CPU")) {
					registroPartida += jugador.getNombre() + " " + jugador.getPuntuacion();
				}
			}
			registroPartida = registroPartida.trim();

			if (!registroPartida.isEmpty()) {
				Historico.almacenarInforamcionJugadores(registroPartida);

			}

			turnosPartida++;

		} while (turnosPartida > numeroTurnos);
	}

	/**
	 * Contenido de informacion para que el usuario eliga que tipo de partida quiere
	 * juegar
	 */
	private static void elegirPartida() {
		System.out.println("¿Qué tipo de pasrtida quieres jugar?");
		System.out.println("Tenemos varios tipos de partidas.");
		System.out.println("1) Partida Rapida. Tiene " + ConstantesTipoPartida.PARTIDA_RAPIDA + " turnos.");
		System.out.println("2) Partida Corta. Tiene " + ConstantesTipoPartida.PARTIDA_CORTA + " turnos.");
		System.out.println("3) Partida Normal. Tiene " + ConstantesTipoPartida.PARTIDA_NORMAL + " turnos.");
		System.out.println("4) Partida Larga. Tiene " + ConstantesTipoPartida.PARTIDA_LARGA + " turnos.");
	}

	/**
	 * Crea un numero de jugadares segun el numero que nos indique el usuario
	 * 
	 * @param cantidadJugadores Cantidad de jugadores humanos.
	 */
	private static void creacionJugadores(int cantidadJugadores) {
		for (int numeroJugador = 0; numeroJugador < ConstantesJugador.MAX_JUGADORES; numeroJugador++) {
			if (numeroJugador < cantidadJugadores) {
				System.out.println("Nombre del jugador:" + (numeroJugador + 1) + ":");
				String nombreJugador = teclado.next();
				jugadores[numeroJugador] = new Jugador(nombreJugador); // Numero de jugadores humanos que van a jugar la
																		// partida

			} else {
				jugadores[numeroJugador] = new Jugador("CPU" + (numeroJugador - 1)); // Numero de jugadaores CPU de la
																						// partida

			}

		}
	}

	/**
	 * Genera las distintas partidas y nos dice si hemos acertado o no.
	 * 
	 * @return hasAcertado Nos devuleve true si hemos acertado en la pregunta, false
	 *         si no.
	 */
	private static boolean preguntasFormuladas() {
		int preguntaFormulada = preguntas();
		boolean hasAcertado = false;
		switch (preguntaFormulada) {
		case ConstantesPreguntas.PREGUNTA_MATES: // Caso para las partidas de matematicas
			hasAcertado = preguntas.matematicas(); // true si se ha acertado la partida

			break;
		case ConstantesPreguntas.PREGUNTA_LENGUA: // Caso para las partidas de lengua
			hasAcertado = preguntas.lengua(); // true si se ha acertado la partida

			break;
		case ConstantesPreguntas.PREGUNTA_INGLES: // Caso para las partidas de ingles.
			hasAcertado = preguntas.ingles(); // true si se ha acertado la partida

			break;

		default:
			System.out.println("Opcion no valida. Escoge una opcion de 1 al 4. Gracias");
			break;
		}
		return hasAcertado;
	}

	/**
	 * Nos da el numero de turnos que se van a jugar en cada una de las partidas
	 * segun el tipo de partida
	 * 
	 * @see ConstantesTipoPartida
	 * @param tipoPartida  Numero que nos indica el tipo de partida que se va a
	 *                     jugar
	 * @param numeroTurnos Numero de turnos correspondiente a cada tipo de partida
	 * @return numeroTurnos Numero de turnos de de las partidas
	 */
	private static int seleccionTipoPartida(int tipoPartida, int numeroTurnos) {
		switch (tipoPartida) {
		case 1:
			numeroTurnos = ConstantesTipoPartida.PARTIDA_RAPIDA; // Numero de turnos de las las partidas rapidas
			break;
		case 2:
			numeroTurnos = ConstantesTipoPartida.PARTIDA_CORTA; // Numero de turnos de las las partidas cortas
			break;
		case 3:
			numeroTurnos = ConstantesTipoPartida.PARTIDA_NORMAL; // Numero de turnos de las las partidas normales
			break;
		case 4:
			numeroTurnos = ConstantesTipoPartida.PARTIDA_LARGA; // Numero de turnos de las las partidas largas
			break;

		default:
			break;
		}
		return numeroTurnos;
	}

	// Borrar cuando se finalize completamente la clase Partida
	public static void main(String[] args) {
		generarPartida();
	}

}
