package clases;

import java.util.Random;

import constantes.ConstantesPreguntas;

/**
 * Clase que define los atributos y metodos de las preguntas del programa.
 */
public class Pregunta {
	private int tipoPregunta;
	
	
	public int getTipoPregunta() {
		return tipoPregunta;
	}
	
	
	/**
	 * Metodo que nos genera un numero aleatorio del 1 al 3
	 * @return tipoDePreguntaGenerada
	 */
	public static int preguntas() {
		Random preguntaAleatoria = new Random(); 
		int tipoDePreguntaGenerada = preguntaAleatoria.nextInt(3) +1 ;
		return tipoDePreguntaGenerada;
	}
	
	
	
	/**
	 * Metodo que nos va a generar las preguntas del programa.
	 * @param tipoPregunta	unos da un numero conrrespondinte a cada tipo de pregunta.
	 * 
	 */
	public static void generarPreguntas(int tipoPregunta) {
		tipoPregunta = preguntas();
		if(tipoPregunta == ConstantesPreguntas.PREGUNTA_MATES) {
			
			
		}else if(tipoPregunta == ConstantesPreguntas.PREGUNTA_LENGUA){
			
		}else {
			
			
			
		}
		
	}

	

}


