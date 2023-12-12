
public class GeneradorSeñalSinusoidal extends GeneradorSeñal {

private int t;
	
	//Constructor
	   /** 
	    * @param nombre Nombre para el generador 
	    */
	
	public GeneradorSeñalSinusoidal(java.lang.String nombre){
		
		super(nombre);
		
	}
	
	/** 
	    * Proporciona el siguiente valor de la senal de salida. La primera vez que se invoca a este metodo devuelve el valor de la senal de salida en el instante inicial t=0;
	    * @return Siguiente valor de la senal de salida sinusoidal, de amplitud -1,0 a +1,0.
	    */
	
	public double getSalida(){
		
		double salida;
		
		salida=Math.sin(t*2*Math.PI/360);
		
		t++;
		
		return salida;
		
	}
}
