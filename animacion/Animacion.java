package animacion;


public abstract class Animacion {

	protected Escenario p;
	private String nombre;
	private int ancho,alto;
	
	/** 
	 * Constructor de la clase
	 * Crea una animación con el nombre proporcionado que se mostrara en un Escenario de tamano ancho x alto.
	 * @param nombre - el nombre de la animacion 
	 * @param ancho - el ancho del escenario donde mostrarla (en pixels) 
	 * @param alto - el alto del escenario donde mostrarla (en pixels)
	 */
	
	public Animacion(java.lang.String nombre, int ancho, int alto){

		if(nombre==null){

			throw new IllegalArgumentException("No hay nombre");
		}


		if(alto<0 || ancho<0){

			throw new IllegalArgumentException("EL ancho o el alto es negativo");
		}

		this.nombre=nombre;
		this.ancho=ancho;	
		this.alto=alto;	
		p=new Escenario(nombre,ancho,alto);
	}

	/** 
	 * Devuelve el ancho del escenario
	 * @return el ancho del escenario
	 */
	
	public int getAncho(){
		
		return ancho;	
	}
	
	/** 
	 * Devuelve el alto del escenario
	 * @return el alto del escenario
	 */
	
	public int getAlto(){
		
		return alto;		
	}
	
	/** 
	 * @return el nombre de la animacion
	 */
	
	public java.lang.String getNombre(){
		
		return nombre;		
	}
	
	/** 
	 * Ejecuta un paso de la animacion y prepara todo para ejecutar el siguiente paso. Se llamara en un bucle hasta que finalice la animacion, de forma que se vaya ejecutando paso a paso.
	 */
	
	public abstract void ejecutarPaso();
		
	/** 
	 * Devuevle si la animacion ha finalizado o no.
	 * @return true si la animación ha finalizado
	 */
	
	public abstract boolean estaFinalizada();
	
}
