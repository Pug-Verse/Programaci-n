package Dados;

import java.util.Random;

public class Dado extends Prueba{

//Atributos de la clase
private int numeroCaras;
private int valor;

/** 
 * Constructor de la clase
 * 
 * @param numCaras Numero de caras del dado
 * @return Las caras del dado
 */

	public Dado(int numCaras){
		
		numeroCaras = numCaras;
	}
	
	
	/** 
	    * Devuelve el valor que se haya generado por ultima vez en tirar()
	    * @return Devuelve valor
	    */
	
	
	public int getValor(){
		
		return valor;
	}
	
	/** 
	    * Genera un valor aleatorio entre 1 y numeroCaras
	    */
	
	public void tirar(){
			
	 Random r=new Random();
	
	 valor=r.nextInt(numeroCaras)+1;
	
	}
	
}
