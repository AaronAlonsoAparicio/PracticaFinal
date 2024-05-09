package clases;

import java.util.Objects;

import constantes.ConstantesJugador;

/**
 * Clase que nos define las funcionalidades y caracteristicas de los jugadores.
 * 
 * @author Aaron Alonso Aparicio
 * @version 1.0
 */

public class Jugador {

	private String nombre;
	private int puntuacion = ConstantesJugador.PUNTUACION_INICIAL;

	// TODO: Crear metodo para ayadir jugador
	/**
	 * Contructor de los objetos de Jugador.
	 * 
	 * @param nombreJugador
	 */
	public Jugador(String nombreJugador) {
		super();
		this.nombre = nombreJugador;

	}

	/**
	 * Clase que nos dice el nombre de los jugadores.
	 * 
	 * @return nombre del Jugador
	 */

	public int getPuntuacion() {
		return puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void sumarPunto(int puntos) {
		this.puntuacion += puntos;
	}

	/**
	 * Metodo que nos comprueba mediente el nombre del jugador si ya hay uno igual.
	 * 
	 * @param obj Le pasamos el objeto que queremos comprobar
	 * @return true si el objeto si existe, false sino.
	 * @since 1.0
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(nombre, other.nombre);
	}

	/**
	 * Metodo para imprimir la informacion de los jugadores.
	 */

	public void imprimirInformacion() {

		System.out.println(toString());

	}

	public String toString() {
		return "Jugador:" + nombre + "[" + puntuacion + "]";
	}

	/**
	 * Metodo que no comprueba que el nombre del jugador esta correctamente formado.
	 * 
	 * @return truen si el nombre esta correctamente formado, false si tiene algo
	 *         invalido.
	 */
	public boolean comprobarNombreJugador() {
		int contador = 0;
		while (contador < this.nombre.length()) {
			if (!Character.isLetter(this.nombre.charAt(contador))) {
				System.out.println("Nombre invadilo.");
				System.out.println("El nombre de los jugadores humanos no pueden contener numeros.");
				return false;

			}

			contador++;
		}

		System.out.println("Nombre valido.");
		return true;

	}

}
