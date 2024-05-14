package clases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import constantes.ConstantesJugador;
import registrosSalida.Historico;
import registrosSalida.LogJuego;
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
		Ranking.crearRanking();
		LogJuego.crearLog();
		Historico.crearHistorico();
		presentacionPrograma();
		jugadores = jugadoresPartida();
		opcionesPrograma(jugadores);

	}

	public static ArrayList<Jugador> jugadoresPartida() {
		teclado = new Scanner(System.in);
		ArrayList<Jugador> jugadoresPartida = new ArrayList<>();
		System.out.println("¿Cuántos jugadores van a participar?");
		int jugadoresHumanos = teclado.nextInt();

		crearJugadorHumano(jugadoresPartida, jugadoresHumanos);

		crearJugadoresCpu(jugadoresPartida, jugadoresHumanos);

		return jugadoresPartida;

	}

	
	/**
	 * Metodo para crear los jugadores CPU de la partida
	 * @param jugadoresPartida Jugadores que van a jugar la partida
	 * @param jugadoresHumanos numero de jugadores que ya hay en la partida
	 * @see crearJugadoresHumano 
	 */
	private static void crearJugadoresCpu(ArrayList<Jugador> jugadoresPartida, int jugadoresHumanos) {
		System.out.println("¿Cuantos jugadores CPU van a jugar?");
		int jugadoresCpu = teclado.nextInt();

		int totalJugadores = jugadoresCpu + jugadoresHumanos;
		if (totalJugadores > ConstantesJugador.MAX_JUGADORES) {
			System.out.println(
					"El número total de jugadores no puede exceder de " + ConstantesJugador.MAX_JUGADORES + ".");
			jugadoresCpu = ConstantesJugador.MAX_JUGADORES - jugadoresHumanos;
			System.out.println("Se ajustará el número de jugadores CPU a " + jugadoresCpu + ".");
		}

		for (int numeroCpu = 0; numeroCpu < jugadoresCpu; numeroCpu++) {
			jugadoresPartida.add(new Jugador("CPU" + (numeroCpu + 1)));
		}
	}

	/**
	 * Metodo para crear los jugadores humanos de la partida
	 * @param jugadoresPartida Jugadores que van a jugar la partida
	 * @param jugadoresHumanos Jugadores humanos que van a jugar la partida
	 */
	
	private static void crearJugadorHumano(ArrayList<Jugador> jugadoresPartida, int jugadoresHumanos) {
		for (int numeroJugadores = 0; numeroJugadores < jugadoresHumanos; numeroJugadores++) {
			boolean nombreValido = false;

			do {
				System.out.println("Nombre del jugador" + (numeroJugadores + 1) + ": ");
				String nombreJugador = teclado.next();
				Jugador nuevoJugador = new Jugador(nombreJugador);
				if (nuevoJugador != null && nuevoJugador.comprobarNombreJugador()) {
					jugadoresPartida.add(new Jugador(nombreJugador));
					nombreValido = true;
				}

			} while (!nombreValido);
		}
	}

	/**
	 * Menu de las opciones propias que tienen los jugadores
	 * 
	 * @throws IOException
	 * @since 1.0
	 */
	private static void menuJugadores() throws IOException {
		jugadores = new ArrayList<Jugador>();
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
			System.out.println("¿Qué jugador deseas eliminar?");
			String nombreJugadorEliminar = teclado.next();
			boolean jugadorEncontrado = false;
			for (Jugador jugador : jugadores) {
				if (jugador.getNombre().equalsIgnoreCase(nombreJugadorEliminar)) {
					jugadores.remove(jugador);
					jugadorEncontrado = true;
					System.out.println("Jugador eliminado con éxito");
					LogJuego.salidaAcciones("Jugador eliminado " + nombreJugadorEliminar);
					break;
				}
			}
			if (!jugadorEncontrado) {
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
				LogJuego.salidaAcciones("Añadido nuevo jugador " + anaydirNuevoJugador);

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
	private static void opcionesPrograma(ArrayList<Jugador> listaJugadores) throws IOException {

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
				ArrayList<Jugador> jugadoresPartida = Partida.generarPartida(jugadores);
				jugadores.clear();
				jugadores.addAll(jugadoresPartida);

				break;

			case 2:
				Ranking.mostrarRanking(); // Mostramos el ranking

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
