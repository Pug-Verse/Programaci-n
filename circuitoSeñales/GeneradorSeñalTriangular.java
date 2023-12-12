
public class GeneradorSeñalTriangular extends GeneradorSeñal {

private double t;

	
	//Constructor
	   /** 
	    * @param nombre Nombre para el generador 
	    */
	
	public GeneradorSeñalTriangular(java.lang.String nombre){
		
		super(nombre);

		}
	
	/** 
	    * Obtiene una senal triangular en rango -1.0 a 1.0, periodica con periodo 360 muestras.
	    * @return Siguiente valor de la senal que genera. Esta clase base devuelve senal nula (salida siempre 0.0)
	    */
	
	public double getSalida(){
		
		double salida;

		if(t>=0  && t<90)
		{
			salida=t/90;
		}
		else if(t>=90 && t<270)
		{
			salida=(180-t)/90;
		}
		else
		{
			salida=(t-360)/90;
		}

		t=(t+1)%360;

		return salida; 
	}

}
