package clases;

import java.util.Objects;

public class Jugador {
	
	private String nombre;
	
	
	
	public Jugador(String nombreJugador) {
		super();
		this.nombre = nombreJugador;
	}



	public String getNombre() {
		return nombre;
	}







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


 public void imprimirInformacion() {
	 
	 System.out.println(toString());
	 
 }
	
	

	@Override
	public String toString() {
		return "Jugador: [ "+ nombre + "]";
	}

	
	

	

	
	
	
	

}
