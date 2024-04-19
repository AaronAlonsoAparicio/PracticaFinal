package clases;

import java.util.ArrayList;

import java.util.Scanner;

import constantes.ConstantesJugador;

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

	public static void main(String[] args) {

		presentacionPrograma();
		opcionesPrograma();
		

	}

	/**
	 * Menu de las opciones propias que tienen los jugadores
	 * @since 1.0
	 */
	private static void menuJugadores() {
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
	 * @param nombre indicamos el nombre del jugador a registrarse
	 */

	private static void opcionesJugadores(String nombre) {
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
				casoUnoJugadores();

				break;
			case 2:

				casoDosJugadores(nuevoJugador);

				break;

			case 3:
				casoTresJugadores();

				break;

			case 4:

				opcionesPrograma();
				break;

			default:
				break;
			}
		} while (opcion != 5);
	}

	
	/**
	 * Metodo por el cual eliminamos a jugadores, opcion 3 del menu de los jugadores.
	 * @since 1.0
	 */
	private static void casoTresJugadores() {
		System.out.println("Has entrado en Eliminar Jugador.");
		System.out.println("¿Desea continuar? (Si/No)");
		String continuar = teclado.next();
		if (continuar.equalsIgnoreCase("Si")) {
			System.out.println("¿Que jugador deseas eliminar?");
			for (Jugador jugador : jugadores) {
				jugador.imprimirInformacion();
			}
			String jugador = teclado.next();
			if (jugadores.contains(jugador)) {

				int jugadorAEliminar = jugadores.indexOf(jugador);
				jugadores.remove(jugadorAEliminar);
				System.out.println("Jugador eliminado con exito");
				for (Jugador jugadoresRestantes : jugadores) {
					jugadoresRestantes.imprimirInformacion();

				}

			}
		}
	}

	/**
	 * Metodo del caso 1 del menu de los jugadores.
	 * 
	 */

	private static void casoUnoJugadores() {
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

	private static void casoDosJugadores(Jugador nuevoJugador) {
		
		if(nuevoJugador.comprobarNombreJugador() != true) {
	
		if (!jugadores.contains(nuevoJugador) && nuevoJugador.comprobarNombreJugador() != true) {
			System.out.println("Registrado correctamente.");
			jugadores.add(nuevoJugador);

		} else {
			System.out.println("Ha habido algun problema con su identificacion.");
		}
		anaydirNuevoJugador();
		
		}else {
			System.out.println("No se ha podido añadir el " + nuevoJugador);
		}
	
		
	}
	
	/**
	 * Metodo para añadir un nuevo jugador
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
			if (!jugadores.contains(anaydirNuevoJugador) && anaydirNuevoJugador.comprobarNombreJugador() != true) {
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
	 * @since 1.0
	 */
	private static void opcionesPrograma() {
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
		opcionElegida= teclado.nextInt();
		switch (opcionElegida) {
		case 1:

			break;

		case 2:

			break;
		case 3:

			break;

		case 4:
			menuJugadores();

			break;

		case 5:
		default:
			System.out.println("Hasta la proxima.");
			break;
		} 
		}while (opcionElegida != 5);
		
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
		System.out.println("¿Cuantos jugadores humanos van a participar?");
		teclado = new Scanner(System.in);
		int jugadoresHumanos = teclado.nextInt();
		System.out.println("En el dia a de hoy van a participar " + jugadoresHumanos + " jugador/es");
		int jugadoresMaquina = ConstantesJugador.MAX_JUGADORES - jugadoresHumanos;
		System.out.println("Y con " + jugadoresMaquina + " jugadores maquina");
		int numJugadores = jugadoresHumanos + jugadoresMaquina;
		System.out.println("Hoy van a particiar " + numJugadores + "jugadores demoles la bienvenida.");

	}

}
