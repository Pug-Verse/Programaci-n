package animacion;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int opcion=0;
		Scanner sc=new Scanner(System.in);

		String [] imagenes={"T2.gif","T3.gif","T4.gif","T5.gif","T6.gif"};
		Animacion animacion=null;
		Efecto efect=new EfectoEscalaGrises();
		Efecto libre=new EfectoLibre();
		Efecto secuencia=new EfectoSecuencia(efect,libre);


			System.out.println("\n               MENU\n      1.- Animacion Imagen"
					+ "\n      2.- Animacion Imagenes"
					+ "\n      3.- Animacion Circular"
					+ "\n      4.- Animacion Movimiento"
					+ "\n      5.- Animacion con Efecto"
					+ "\n      6.- Animacion Libre"
					+ "\n      7.- Animacion Secuencia");
	
			System.out.print("\nIntroduce opcion: ");
			opcion=sc.nextInt();

			try{
	
					switch(opcion){
	
					case 1:
	
						animacion=new AnimacionImagen("AnimacionImagen",0,0,"T5.gif");
						break;
	
					case 2:
	
						animacion=new AnimacionImagenes("AnimacionImagenes",0,0,imagenes);
						break;
	
	
					case 3: 
	
						animacion= new AnimacionImagenesCircular("AnimacionImagenesCircular",0,0,imagenes);
						break;
	
					case 4: 
	
						animacion= new AnimacionMovimiento("AnimacionMovimiento",0,0,"T5.gif",0,0,10,10);
						break;
	
					case 5:
	
						animacion= new AnimacionImagenConEfecto("AnimacionImagenConEfecto",0,0,"T5.gif",efect);
						break;
	
					case 6:
	
						animacion= new AnimacionImagenConEfecto("AnimacionImagenConEfectoLibre",0,0,"T5.gif",libre);
						break;
	
					case 7:
	
						animacion= new AnimacionImagenConEfecto("AnimacionImagenSecuencia",0,0,"T5.gif",secuencia);
						break;
						
					default:
                        System.out.println("\nOpcion no valida. Intente nuevamente.");
	
					}
		

				player(animacion,450);

				
			}catch(IllegalArgumentException error){
	
				System.out.print(error.getMessage());
	
			}catch(AnimacionException error2){
	
				System.out.print(error2.getMessage());
	
			}catch(IllegalStateException error3){
	
				System.out.print(error3.getMessage());
	
			}catch(RuntimeException error4){
	
				System.out.print(error4.getMessage());
			}
			
		
		sc.close();

	}

	public static void player(Animacion a, int retardo) {
		while (!a.estaFinalizada()){
			a.ejecutarPaso();
			// Generacion de un retardo entre pasos para ralentizar la animacion
			try {
				Thread.sleep( retardo );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

