package clases; 

import java.util.ArrayList;
import java.util.Scanner;
import constantes.constantesPartida;

/**
 * Practica tercer trimestre, programa principal.
 * @author Aaron Alonso Aparicio
 * @version 1.0 
 * 
 */
public class Tribial_Challenge {
	static Scanner teclado;
	
	public static void main(String[] args) {
		
		presentacionPrograma();
		opcionesPrograma();
		menuJugadores();
		
		
		
	}




	
	
	/**
	 * Menu de las opciones propias que tienen los jugadores
	 * @since 1.0
	 */
	private static void menuJugadores() {
		teclado = new Scanner(System.in);
		System.out.println("Has seleccionado la opcion: Jugadores");
		System.out.println("Que deseas hacer ahora:");
		System.out.println("Antes de hacer nada dinos tu nombre para poder identificarte");
		String nombre = teclado.next();
		Jugador nuevoJugador = new Jugador(nombre);
		System.out.println("1) Ver jugadores");
		System.out.println("2) Añadir jugador");
		System.out.println("3) Eliminar jugador");
		System.out.println("4) Volver al menu principal");
		int opcion = teclado.nextInt();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		switch (opcion) {
		case 1:
			if(jugadores.isEmpty()) {
				System.out.println("Todavia no hay nigun jugador registrador.");
				System.out.println("Porfavor seleciona la opcion 2 para registarse");
			}
			
			break;
		case 2:
			if(!jugadores.contains(nuevoJugador)) {
				System.out.println("Registrador correctamente.");
				jugadores.add(nuevoJugador);
				
				
			}else {
				System.out.println("Ha habido algun problema con su identificacion.");
			}
			
			
			
			break;

		case 3:
	
			break;

		case 4:
	
			break;


		default:
			break;
		}
	}



	
	
	/**
	 * Creamo el menu de opciones principal que vamos a usar en el programa.
	 * @since 1.0
	 */
	private static void opcionesPrograma() {
		teclado = new Scanner(System.in);
		System.out.println("Vamos a ver que deciden hacer los jugadores de hoy:");
		System.out.println("Podemos elegir entre:");
		System.out.println("Jugar Partida.");
		System.out.println("Ranking.");
		System.out.println("Historial.");
		System.out.println("Jugadores.");
		System.out.println("Salir.");

		System.out.println("¿Que deseas hacer?");
		String opcionElegida = teclado.next();
		switch (opcionElegida) {
		case "Jugar Partida":
			
			break;
			
		case "Ranking":
			
			break;
		case "Historial":
			
			break;
			
		case "Jugadores" :
			
			break;
			
		case "Salir":
		default:
			System.out.println("Hasta la proxima.");
			break;
		}
	}
	
	
	
	/**
	 * Presentacion del programa.
	 * @since 1.0  
	 */

	private static void presentacionPrograma() {
		System.out.println("*** TRIBIAL CHALLENGE ***");
		System.out.println("     Acierta y gana    ");
		System.out.println("Un programa prensentado por Aaron Alonso.");
		System.out.println("¿Cuantos jugadores humanos van a participar?");
		teclado = new Scanner(System.in); 
		int jugadoresHumanos = teclado.nextInt();
		System.out.println("En el dia a de hoy van a participar  " + jugadoresHumanos +" jugadores" );
		int jugadoresMaquina = constantesPartida.MAX_JUGADORES - jugadoresHumanos;
		System.out.println("Y con " + jugadoresMaquina + " jugadores maquina");
		int numJugadores = jugadoresHumanos + jugadoresMaquina;
		System.out.println("Hoy van a particiar " + numJugadores + " demoles la bienvenida."  );
	}

}
