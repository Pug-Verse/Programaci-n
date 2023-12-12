package animacion;

public class AnimacionImagenes extends Animacion {

	
	private String[] imagenes;
	private boolean finalizado;
	private int i=0;

	/** 
	 * Constructor de la clase
	 * Crea una animacion con el nombre proporcionado, la animacion se mostrara en un Escenario de tamano ancho x alto. La animacion mostrara las imagenes cuyos nombres de ficheros se pasan como parametro en el array imagenes secuencialmente, en el mismo orden en que estan en el array, y terminara.
	 * @param nombre - el nombre de la animacion 
	 * @param ancho - el ancho del escenario donde mostrarla (en pixels) 
	 * @param alto - el alto del escenario donde mostrarla (en pixels)
	 * @param imagenes - array con el nombre de los ficheros con las imagenes a mostrar
	 */
	
	public AnimacionImagenes(java.lang.String nombre,int ancho,int alto,java.lang.String[] imagenes) throws AnimacionException{
		
		super(nombre,ancho,alto);
		this.imagenes=imagenes;
		finalizado=false;

		if(imagenes.length<2){

			throw new AnimacionException("No hay al menos dos imagenes");
		}
	}
	
	/** 
	 * Permite cambiar la posicion correspondiente a la imagen a mostrar al ejecutar el siguiente paso. Su valor debe estar comprendido entre 1 y el numero de imagenes existentes.
	 * @param posicion de la imagen a mostrar al ejecutar el siguiente paso
	 */
	
	public void setPosicion(int posicion){
		
			i=posicion;	
	}

	/** 
	 * Devuelve el numero de imagenes que forman la animacion
	 * @return int el numero de imágenes de al animacion
	 */
	
	public int getCuantas(){
		
		int cuantos;
		cuantos=imagenes.length;
		
		return cuantos;
	}
	
	/** 
	 * Ejecuta un paso de la animacion y prepara todo para ejecutar el siguiente paso. Se llamara en un bucle hasta que finalice la animacion, de forma que se vaya ejecutando paso a paso.
	 */
	
	public void ejecutarPaso(){
		
		if(estaFinalizada()){

			throw new IllegalStateException("Ya esta finalizada");

		}
		
		p.dibujarImagen(0,0,new Imagen(imagenes[i]));
		i++;
	}

	/** 
	 * Devuevle si la animacion ha finalizado o no.
	 * @return true si la animacion ha finalizado
	 */
	
	public boolean estaFinalizada(){

		if(i==imagenes.length){
			finalizado=true;
		}

		return finalizado;
	}
}
