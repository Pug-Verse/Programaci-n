

public interface ITransferencia {


	/** 
	    * Devuelve el siguiente valor (o muestra) de la senal de salida, en base al valor de senal de entrada y/o el estado interno del objeto.
	    * @param entrada Siguiente valor de senal de entrada
	    * @return Siguiente valor de la senal de salida producida
	    */
	
	public double getSalida(double entrada) throws CircuitoException; 
	
}
