package Dados;


public class Cubilete extends Prueba {
	
	//Atributos
	int valoresdedados[];
	private int maxNumDados=5;
	Dado dados[];
	
	/** 
	 * Constructor de la clase
	 * 
	 * @param maxNumDados Numero maximo de dados
	 * @return El numero de dados
	 */

	public Cubilete(int maxNumDados){
		
		
		this.maxNumDados=maxNumDados;
		dados=new Dado[maxNumDados];
		valoresdedados=new int [maxNumDados];
		
	}
	
	/** 
	 * Anade dados al array dados[i]
	 * 
	 * @param Dado dado 
	 * @return Dados 
	 */
	
	public void añadirDado(Dado dado){
		
        
		int i;
		boolean encontrado=false;
		
		for(i=0;i<maxNumDados && !encontrado;i++){
			if(dados[i]==null){
			
				  dados[i]=dado;
			      encontrado=true;
			}
		}
			
	}
		
	/** 
	 * Obtiene los valores de los dados
	 * @return Los valores de los dados
	 */

	public int[] obtenerValoresDeDados(){
		int i;
		
		for(i=0;i<maxNumDados;i++){
			if(dados[i]!=null){
				
				valoresdedados[i]=dados[i].getValor();
			}
			
		}
		   
		return valoresdedados;
		
	}
	
	/** 
	 * Tira el cubilete con los dados
	 */

	
	public void tirar(){
		
		int i;
		
		for(i=0;i<maxNumDados;i++){
			if(dados[i]!=null)
			{
			dados[i].tirar();
			}
		
		}
		
	}
}