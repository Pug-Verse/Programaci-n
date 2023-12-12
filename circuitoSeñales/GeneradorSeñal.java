

public class GeneradorSeñal {

	
	
	private String nombre;

	
	//Constructor
	   /** 
	    * @param nombre Nombre para el generador 
	    */
	public GeneradorSeñal(java.lang.String nombre){
		
		this.nombre=nombre;
			
	}
	
	 /** 
	    * Proporciona el nombre del generador
	    * @return Nombre del generador
	    */
	
	public java.lang.String getNombre(){
	
		return nombre;
	}
	
	 /** 
	    * Proporciona el siguiente valor de la senal que produce el generador. La muestra que se devuelve la primera que se invoca este metodo corresponde al instante inicial t=0
	    * @return Siguiente valor de la senal que genera. Esta clase base devuelve senal nula (salida siempre 0.0)
	    */
	
	public double getSalida(){
	
		return 0;
	}

}
