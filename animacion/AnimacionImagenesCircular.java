package animacion;

public class AnimacionImagenesCircular extends AnimacionImagenes {
	
	private int j=0;
	/** 
	 * Constructor de la clase
	 * Crea una animacion con el nombre proporcionado, la animacion se mostrara en un Escenario de tamano ancho x alto. La animacion mostrara las imagenes cuyos nombres de ficheros se pasan como parametro en el array imagenes, en el mismo orden en que estan en el array, al mostrar la ultima imagen volvera empezar por la primera, en circulo, por lo que esta animacion no tiene final.
	 * @param nombre - el nombre de la animacion 
	 * @param ancho - el ancho del escenario donde mostrarla (en pixels) 
	 * @param alto - el alto del escenario donde mostrarla (en pixels)
	 * @param imagenes - array con el nombre de los ficheros con las imagenes a mostrar
	 */
	
	public AnimacionImagenesCircular(java.lang.String nombre,int ancho,int alto,java.lang.String[] imagenes) throws AnimacionException{
		
		super(nombre,ancho,alto,imagenes);	
	}
	
	/** 
	 * Devuevle si la animacion ha finalizado o no.
	 * @return true si la animacion ha finalizado
	 */
	
	public boolean estaFinalizada(){

		boolean finalizado=false;
		int numImagenes=5;

		if(j==numImagenes){
			
			j=0;
			setPosicion(0);
		}
		
		j++;

		return finalizado;
	}
}
