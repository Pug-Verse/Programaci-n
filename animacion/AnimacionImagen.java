package animacion;

public class AnimacionImagen extends Animacion {
	
	private String imagen;
	private boolean finalizado;

	/** 
	 * Constructor de la clase
	 * Crea una animacion con el nombre proporcionado que se mostrara en un Escenario de tamano ancho x alto. La animacion mostrara la imagen cuyo nombre de fichero se pasa como parametro y terminara.
	 * @param nombre - el nombre de la animacion 
	 * @param ancho - el ancho del escenario donde mostrarla (en pixels) 
	 * @param alto - el alto del escenario donde mostrarla (en pixels)
	 * @param imagen - el nombre del fichero con la imagen a mostrar
	 */
	
	public AnimacionImagen(java.lang.String nombre,int ancho,int alto,java.lang.String imagen){
	
		super(nombre,ancho,alto);
		this.imagen=imagen;
		finalizado=false;
		
		if(imagen==null){

			throw new IllegalArgumentException("No hay imagen");
		}	

	}
	
	/** 
	 * Ejecuta un paso de la animacion y prepara todo para ejecutar el siguiente paso. Se llamara en un bucle hasta que finalice la animacion, de forma que se vaya ejecutando paso a paso.
	 */
	
	public void ejecutarPaso(){
		
		if(estaFinalizada()){

			throw new IllegalStateException("Ya esta finalizada");

		}	
		
		p.dibujarImagen(0,0,new Imagen(imagen));
		finalizado=true;
	}
	
	/** 
	 * Devuevle si la animacion ha finalizado o no.
	 * @return true si la animacion ha finalizado
	 */
	
	public boolean estaFinalizada(){
		
		return finalizado;
	}
}
