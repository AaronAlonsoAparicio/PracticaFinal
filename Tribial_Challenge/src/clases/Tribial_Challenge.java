package clases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import constantes.ConstantesJugador;
import registrosSalida.Historico;
import registrosSalida.Ranking;

/**
 * Practica tercer trimestre, programa principal.
 * 
 * @author Aaron Alonso Aparicio
 * @version 1.0
 * 
 */
public class Tribial_Challenge {
	static Scanner teclado;
	static ArrayList<Jugador> jugadores;

	public static ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public static void main(String[] args) throws IOException {
		Historico.crearHistorico();
		presentacionPrograma();
		ArrayList<Jugador> jugadores = jugadoresPartida();
		opcionesPrograma(jugadores);

	}

	public static ArrayList<Jugador> jugadoresPartida() {
		teclado = new Scanner(System.in);
		ArrayList<Jugador> jugadores = new ArrayList<>();
		System.out.println("¿Cuántos jugadores van a participar?");
		int jugadoresHumanos = teclado.nextInt();

		for (int numeroJugadores = 0; numeroJugadores < jugadoresHumanos; numeroJugadores++) {
			System.out.println("Nombre del jugador" + (numeroJugadores + 1) + ": ");
			String nombreJugador = teclado.next();
			jugadores.add(new Jugador(nombreJugador));
		}

		for (int jugadoresCpu = jugadores.size(); jugadoresCpu < ConstantesJugador.MAX_JUGADORES; jugadoresCpu++) {
			jugadores.add(new Jugador("CPU" + (jugadoresCpu - jugadoresHumanos + 1)));

		}

		return jugadores;

	}

	/**
	 * Menu de las opciones propias que tienen los jugadores
	 * 
	 * @throws IOException
	 * @since 1.0
	 */
	private static void menuJugadores() throws IOException {
		jugadores = new ArrayList<Jugador>();
		int opcion;

		teclado = new Scanner(System.in);
		System.out.println("Has seleccionado la opcion: Jugadores");
		System.out.println("Que deseas hacer ahora:");
		System.out.println("Antes de hacer nada dinos tu nombre para poder identificarte");
		String nombre = teclado.next();
		opcionesJugadores(nombre);

	}

	/**
	 * Opciones del menu propio de los jugadores.
	 * 
	 * @param nombre indicamos el nombre del jugador a registrarse
	 * @throws IOException
	 */

	private static void opcionesJugadores(String nombre) throws IOException {
		int opcion;
		do {

			Jugador nuevoJugador = new Jugador(nombre);
			System.out.println("1) Ver jugadores");
			System.out.println("2) Añadir jugador");
			System.out.println("3) Eliminar jugador");
			System.out.println("4) Volver al menu principal");
			opcion = teclado.nextInt();
			switch (opcion) {
			case 1:
				mostrarJugador(); // Mostramos lo jugadores

				break;
			case 2:

				anyadirJugador(nuevoJugador); // Caso para añadir jugadores

				break;

			case 3:
				eliminarJugador(); // Caso para eliminar a jugadores

				break;

			case 4:

				opcionesPrograma(jugadores); // Volver al menu principal del programa
				break;

			default:
				break;
			}
		} while (opcion != 5);
	}

	/**
	 * Metodo por el cual eliminamos a jugadores, opcion 3 del menu de los
	 * jugadores.
	 * 
	 * @since 1.0
	 */
	private static void eliminarJugador() {
		System.out.println("Has entrado en Eliminar Jugador.");
		System.out.println("¿Desea continuar? (Si/No)");
		String continuar = teclado.next();
		if (continuar.equalsIgnoreCase("Si")) {
			System.out.println("¿Que jugador deseas eliminar?");
			int jugadorAEliminar = 0;
			for (Jugador jugador : jugadores) {
				System.out.println(jugadorAEliminar + ". " + jugador.getNombre());
				jugadorAEliminar++;
			}
			int jugadrorEliminado = teclado.nextInt();
			if (jugadrorEliminado >= 0 && jugadrorEliminado < jugadores.size()) {
				jugadores.remove(jugadrorEliminado);
				System.out.println("Jugador eliminado con exito");

			} else {
				System.out.println("Jugador no encontrado.");

			}

		}
	}

	/**
	 * Metodo del caso 1 del menu de los jugadores.
	 * 
	 */

	private static void mostrarJugador() {
		if (jugadores.isEmpty()) {
			System.out.println("Todavia no hay nigun jugador registrador.");
			System.out.println("Porfavor seleciona la opcion 2 para registarse");
		} else {

			for (Jugador jugador : jugadores) {
				jugador.imprimirInformacion();
			}

		}
	}

	/**
	 * Metodo del caso dos del menu de los jugadores
	 * 
	 * @param nuevoJugador
	 * @since 1.0
	 * 
	 */

	private static void anyadirJugador(Jugador nuevoJugador) {

		if (nuevoJugador.comprobarNombreJugador() == true) {

			if (!jugadores.contains(nuevoJugador)) {
				System.out.println("Registrado correctamente.");
				jugadores.add(nuevoJugador);

			} else {
				System.out.println("Ha habido algun problema con su identificacion.");
			}
			anaydirNuevoJugador();

		} else {
			System.out.println("No se ha podido añadir el " + nuevoJugador);
		}

	}

	/**
	 * Metodo para añadir un nuevo jugador
	 * 
	 * @since 1.0
	 */

	private static void anaydirNuevoJugador() {
		System.out.println("¿Quieres añadir un nuevo jugador?");
		System.out.println("(Si/No)");
		String registrar = teclado.next();

		if (registrar.equalsIgnoreCase("Si")) {
			System.out.println("¿Dime el nombre del nuevo jugador?");
			String nombreJugador = teclado.next();
			Jugador anaydirNuevoJugador = new Jugador(nombreJugador);
			if (!jugadores.contains(anaydirNuevoJugador)) {
				System.out.println("Añadido correctamente el jugador: " + nombreJugador);
				jugadores.add(anaydirNuevoJugador);

			} else {
				System.out.println("Jugador ya existente en el sistema o nombre incorrecto");
				System.out.println("Tendra que eliminar el jugador " + nombreJugador + " para poder registrarse.");

			}

		}
	}

	/**
	 * Creamo el menu de opciones principal que vamos a usar en el programa.
	 * 
	 * @throws IOException
	 * @param jugadores Jugadores que se van a usar en el programa
	 * 
	 * @since 1.0
	 */
	private static void opcionesPrograma(ArrayList<Jugador> jugadores) throws IOException {
		int opcionElegida;
		teclado = new Scanner(System.in);
		do {
			System.out.println("¿Qué quieres hacer?");
			System.out.println("Podemos elegir entre:");
			System.out.println("1) Jugar Partida.");
			System.out.println("2) Ranking.");
			System.out.println("3) Historial.");
			System.out.println("4) Jugadores.");
			System.out.println("5) Salir.");

			System.out.println("¿Que deseas hacer?");
			opcionElegida = teclado.nextInt();
			switch (opcionElegida) {
			case 1:
				Partida.generarPartida(jugadores); // Genera una nueva partida

				break;

			case 2:
			//	Ranking.mostrarRanking(); // Muestra el ranking

				break;
			case 3:
				Historico.mostrarHistorico(); // Muestra el historico

				break;

			case 4:
				menuJugadores(); // Accedemos al menu de los jugadores

				break;

			case 5:
			default:
				System.out.println("Hasta la proxima.");
				break;
			}
		} while (opcionElegida != 5);

	}

	/**
	 * Presentacion del programa.
	 * 
	 * @since 1.0
	 */

	private static void presentacionPrograma() {
		System.out.println("*** TRIBIAL CHALLENGE ***");
		System.out.println("     Acierta y gana    ");
		System.out.println("Un programa prensentado por Aaron Alonso.");

	}

}
