import java.util.*;
public class CircuitoSerie extends java.lang.Object implements ITransferencia {

	private ArrayList<ITransferencia> componentes;
	
	/** 
	    * Constructor. Crea un circuito serie
	    * @param maxComponentes el maximo de componentes que admite el circuito. Debe ser mayor que 0.
	    */
	
	public CircuitoSerie(int maxComponentes) {
		
		if(maxComponentes<=0){
			
			throw new IllegalArgumentException("Componentes negativos");
			
		}
		
		componentes=new ArrayList<ITransferencia>();
		
		}
		
	
	/** 
	    * Agrega un nuevo componente al circuito, en serie tras los existentes
	    * @param componente el objeto a agregar
	    * @return Booleano que indica si fue --cierto-- (o no --falso--, por ejemplo por estar lleno el circuito) posible agregar con exito el componente
	    */
	
	
	public boolean addComponente(ITransferencia componente){
		
		return componentes.add(componente);
	}
	
	/** 
	    * Indica si un circuito ha alcanzado o no el l�mite de su capacidad
	    * @return Cierto si el circuito esta lleno, falso si existe al menos un hueco en el circuito.
	    */
	
	public boolean isLleno(){
		
		return false; // El ArrayList no tiene limite, a no se la capacidad de la memoria
	}
	
	/** 
	    * Devuelve la salida que se obtiene tras aplicar la senal de entrada a este circuito serie El circuito debe contener al menos un componente.
	    * @param entrada Siguiente valor de senal de entrada
	    * @return Siguiente valor de la senal de salida producida
	    */
	
	public double getSalida(double entrada) throws CircuitoException{
	
		double salida=entrada;
		
		if(componentes==null){
			
			throw new CircuitoException("No hay ningun componente en el circuito");

		}
		
		Iterator<ITransferencia> it=componentes.iterator();
		while(it.hasNext())
		{
			ITransferencia comp=it.next();
			salida=comp.getSalida(salida);
		}
		
		return salida;
	}
}