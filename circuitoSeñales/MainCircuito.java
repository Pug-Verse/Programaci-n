
public class MainCircuito extends java.lang.Object {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String nombre="Fase2";
		SondaGrafica s=new SondaGrafica(nombre); 
		
		
		int maxComponentes=5;
		CircuitoSerie circuito=new CircuitoSerie(maxComponentes);
		GeneradorSeñal señal=new GeneradorSeñal(nombre);
		ITransferencia componente=null;

		try{
			
		if(args.length==2){
			
			
			switch(args[0])
			{
				case "S":   // Para generador de senal sinusoidal
				
					señal=new GeneradorSeñalSinusoidal(nombre);
					break;
				
				case "T": // Para generador de senal triangular
				
					señal=new GeneradorSeñalTriangular(nombre);
					break;
			
				default:
				
					System.out.println("Error: Introduce generador de señal correcto. S para sinusoidal y T para triangular");
					break;
		
			}
	
			
			if(args[1].equals("C")){
				
				componente=new Condensador();
			}
				
		   if(args[1].equals("D")){

			   componente=new Diodo();
		
		}
		   
		   if(args[1].equals("P")){

			   componente=new PuenteDiodos();
		}
		   
		   if(args[1].equals("A")){
			   
			   componente=new Amplificador(2.0);
		}
		   
		 
		   if(args[1].equals("S")){

				if(!circuito.isLleno()){
				
					circuito.addComponente(new Condensador());
					circuito.addComponente(new PuenteDiodos());
				}else
					System.out.println("El circuito esta lleno");
				
				componente=circuito;
			}
		  
		   for(int i=0;i<720;i++)
			{
				s.addMuestra(componente.getSalida(señal.getSalida()));
			}
		   
		}else
			 System.out.println("Error: Introduzca el numero de parametros adecuado.");
		
	}catch(CircuitoException error){

			System.out.println(error.getMessage());
			

   }catch(IllegalArgumentException error2){

			System.out.println(error2.getMessage());
		}

	}	
}

