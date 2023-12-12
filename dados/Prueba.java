package Dados;

public class Prueba {

	/**
	 * Programa Prueba las tiradas de un dado 
	 */

	public static void main(String[] args) {
		
		int caras,numtiradas,i,numdados,arrayV[],j,k;
		
		
		int contador=0;
		numtiradas=5;
		caras=6;
		
		Dado dado=new Dado(caras);
		
		
		System.out.println("Prueba un dado de "+caras+" caras\n");
		
		
		
		for(i=1;i<=numtiradas;i++){
			
			 dado.tirar();
			
			 System.out.println("Tirada "+i+" - valor "+dado.getValor());
			
			}
		
		
       caras=3;
       
	   dado=new Dado(caras);
       
       System.out.println("\nPrueba un dado de "+caras+" caras\n");
       
       for(i=1;i<=numtiradas;i++){
    	   
			dado.tirar();
			
			System.out.println("Tirada "+i+" - valor "+dado.getValor());
			
			}
       
       
       System.out.println("\nPrueba un cubilete de hasta 5 dados con dados de 6 caras");
       
		
        
       numdados=5;
       Cubilete cubilete=new Cubilete(numdados);
       
	   caras=6;
	  
	  
		
		for(i=0;i<5;i++){
			
			dado=new Dado(caras);
			
			 cubilete.añadirDado(dado);
			 
	   			contador++;
      	   
    	   
    	   
    	  System.out.println("\n\nCubilete con "+(i+1)+" dado/s");
	   			
    	   for(j=0;j<5;j++){
    		   
    		   cubilete.tirar();
    		   arrayV= cubilete.obtenerValoresDeDados();
    		 
    		   
    		   
    		   System.out.print("\nTirada "+(j+1)+" - valor ");
    		  
    		   for(k=0;k<contador;k++){
    			   
    			  
    		 System.out.print(arrayV[k]+" ");
    		   
    		   }
    	   }
		}

	}
}