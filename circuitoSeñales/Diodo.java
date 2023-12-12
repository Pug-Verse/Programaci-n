

public class Diodo extends java.lang.Object implements ITransferencia {

	/** 
	    * Devuelve la salida que se obtiene tras aplicar esta senal de entrada a un diodo ideal
	    * @param entrada Siguiente valor de senal de entrada
	    * @return Siguiente valor de la senal de salida producida
	    */
	
	public double getSalida(double entrada){
		
		double salida;
		
		if(entrada<0){
			salida=0;
	}else
			salida=entrada;
		
		return salida;
	
	}

}

