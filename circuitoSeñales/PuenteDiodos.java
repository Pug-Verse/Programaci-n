
public class PuenteDiodos extends java.lang.Object implements ITransferencia{


	/** 
	    * Devuelve la salida que se obtiene tras aplicar la entrada a un puente de diodos ideal
	    * @param entrada Siguiente valor de senal de entrada
	    * @return Siguiente valor de la senal de salida producida
	    */

	public double getSalida(double entrada){
		
		return Math.abs(entrada); 
		
	}	
}
