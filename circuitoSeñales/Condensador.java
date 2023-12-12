

public class Condensador extends java.lang.Object implements ITransferencia {

	
	private double salidaAnterior;
	
	
	
	/** 
	    * Devuelve la senal de salida que se obtiene tras aplicar la entrada a un condensador. El condensador tiene perdidas, con coeficiente de perdidas fijo = 0,1%. (porcentaje de perdida en su estado (senal de salida) cada muestra (t) que la se�al de entrada no tiene capacidad para cargar el condensador.
	    * @param entrada Senal de entrada. Debe ser un valor positivo (mayor que 0)
	    * @return Siguiente valor de la senal de salida producida
	    */
	
	public double getSalida(double entrada) throws CircuitoException{
		
		double salida;	
		
		if(entrada<0){
			
			throw new CircuitoException("Entrada condensador negativa");
		}
		
		if(entrada>=0.999*salidaAnterior){
			
			salida=entrada;
		}
		
		else{
		
			salida=0.999*salidaAnterior;
		
		}
		
	salidaAnterior=salida;
	
	return salida;}
	
}
