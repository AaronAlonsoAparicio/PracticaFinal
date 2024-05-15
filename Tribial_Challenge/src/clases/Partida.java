package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import constantes.ConstantesJugador;
import constantes.ConstantesPreguntas;
import constantes.ConstantesTipoPartida;
import registrosSalida.Historico;
import registrosSalida.LogJuego;
import registrosSalida.Ranking;

/**
 * Clase que define las caracteristicas de las partidas y sus atributos.
 * 
 * @author Aaron Alonso
 */
public class Partida {

	// TODO: Añadir el comprobante de los nombres de los jugadores.

	public static Jugador[] jugadores; // Array del numero de jugadores que hay en la partida
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
	public static ArrayList<Jugador> generarPartida(ArrayList<Jugador> jugadores) {
		Historico.crearHistorico();
		Ranking.crearRanking();
		PreguntaLengua.diccionario();
		teclado = new Scanner(System.in);
		Partida.jugadores = jugadores.toArray(new Jugador[ConstantesJugador.MAX_JUGADORES]);
		System.out.println("¿Cuantos jugadores van a participar?");
		elegirPartida();
		int tipoPartida = teclado.nextInt();
		int numeroTurnos = ConstantesTipoPartida.NUMERO_TURNOS;
		partida(tipoPartida, numeroTurnos);

		ArrayList<Jugador> jugadoresDePartida = new ArrayList<>();
		for (Jugador jugador : Partida.jugadores) {
			if(jugador != null) {
			Jugador infoJugador = new Jugador(jugador.getNombre());
			infoJugador.setPuntuacion(jugador.getPuntuacion());

			jugadoresDePartida.add(infoJugador);
			
			}

		}
		
		
		return jugadoresDePartida;

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
			for (int turno = 1; turno <= numeroTurnos; turno++) {
				System.out.println("Turno " + turno + " de " + numeroTurnos);
				for (Jugador jugador : jugadores) {
					if (jugador != null && jugador.getNombre() != null) { 
	                    System.out.println("Le toca al jugador " + jugador.getNombre());
	                    
	                    boolean hasAcertado = false;
	                    if (jugador.getNombre().startsWith("CPU")) {
	                        hasAcertado = resolverPreguntaParaCPU();
	                    } else {
	                        hasAcertado = preguntasFormuladas();
	                    }
	                    if (hasAcertado) {
	                        System.out.println("Has acertado, " + jugador.getNombre() + " enhorabuena sumas 1 punto");
	                        jugador.sumarPunto(1);
	                        jugador.sumarPreguntaCorrecta(1);
	                    } else {
	                        System.out.println("Fallaste, " + jugador.getNombre() + " la próxima vez será:");
	                    }
	                }
	            }
			}

			System.out.println("ASI QUEDAN LOS MARCADORES:");
			for (Jugador jugador : jugadores) {
				
				if(jugador != null) {
				jugador.imprimirInformacion();
				}
			}

			registrarEnArchivosSalida();

			turnosPartida++;
			

		} while (turnosPartida > numeroTurnos);
	}

	private static void registrarEnArchivosSalida() {
		String registroPartida = "";
		for (Jugador jugador : jugadores) {
			// Con esto comprobamos que el jugador es humano por que no empieza por CPU
			if (jugador!= null &&!jugador.getNombre().startsWith("CPU")) {
				registroPartida += jugador.getNombre() + " " + jugador.getPuntuacion() + "\n";
			}
		}

		registroPartida = registroPartida.trim();

		if (!registroPartida.isEmpty()) {
			LogJuego.salidaAcciones("Partida empezada");
			Historico.almacenarInforamcionJugadores(registroPartida);
			Ranking.almacenarRaking();
			LogJuego.salidaAcciones("Partida terminada");
		}
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
			hasAcertado = PreguntaMatematicas.matematicas(); // true si se ha acertado la partida

			break;
		case ConstantesPreguntas.PREGUNTA_LENGUA: // Caso para las partidas de lengua
			hasAcertado = PreguntaLengua.lengua(); // true si se ha acertado la partida

			break;
		case ConstantesPreguntas.PREGUNTA_INGLES: // Caso para las partidas de ingles.
			hasAcertado = PreguntaIngles.ingles(); // true si se ha acertado la partida

			break;

		default:
			System.out.println("Opcion no valida. Escoge una opcion de 1 al 4. Gracias");
			break;
		}
		return hasAcertado;
	}
	
	private static boolean resolverPreguntaParaCPU() {
		// La CPU siempre acierta
		return true;
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



}
