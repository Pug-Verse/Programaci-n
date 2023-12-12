package juegoPalabras;
import java.util.Scanner;

public class Palabras_Encadenadas {
	
	/**
	 * JuegoEncadenado, empezar la siguiente palabra con ultima letra de la palabra anterior 
	 * @param args Argumento que define el numero de jugadores
	 */

	public static void main(String[] args) {

		//Declaracion de arrays//
		
		String[] nombre = new String[100];
		String[] palabra = new String[100];
		
        //Declaracion de variables//
		
		Scanner sc = new Scanner(System.in);
		int i, j, k, w=0;
		char ultimaLetra;
		String entrada;
		int eof=0; // Entero usado como condicion para salir del for y del while
		int totalPalabras=0;
		int contNombre;
		int indiceNombre;
        String nombreActual;
		

		System.out.println("\n\033[4mPALABRAS ENCADENADAS\033[0m\n");
				

		
		// Peticion del nombre de los jugadores//
		
		if(args.length!=0){
		
		  for (i=0; i<Integer.parseInt(args[0]); i++) {

			 System.out.print("Nombre del jugador "+(i+1)+": ");
			 nombre[i] = sc.nextLine();
			
			    while(nombre[i].isEmpty()){
				
				System.out.println("Error: Debe introducir un nombre\n");
				System.out.print("Nombre del jugador "+(i+1)+": ");
				nombre[i] = sc.nextLine();
				
				}

		}

		 System.out.println();
		
		// Peticion de la palabras //

		while (eof==0) {

			for (j=0; eof<1;j++) {

				boolean palabraEncontrada = false;
				contNombre = w;
				System.out.print("Turno de "+nombre[w]+" -> Palabra: ");
				nombreActual=nombre[w];
				w++;

				  if (w==Integer.parseInt(args[0])) {
					   w=0;
				   }

				entrada = sc.nextLine();
				
				while(entrada.isEmpty()){
					
					
					System.out.println("Error: Debe introducir una palabra\n");
					System.out.print("Turno de "+nombreActual+" -> Palabra: ");
					entrada = sc.nextLine();
                }
				
			
				k=0;
				while (k<=totalPalabras) {

					if (entrada.equals(palabra[k])) {

						palabraEncontrada = true;

						k = totalPalabras;

					} else
						k++;
				}

				if (palabraEncontrada) {

					indiceNombre = w-1;

					if (indiceNombre < 0) {

						indiceNombre = 1;
					}
					
					System.out.println("Palabra repetida por lo que "+ nombre[contNombre]+ ", has perdido");

					eof=1;
					totalPalabras++;
					listaPalabras(totalPalabras, palabra);
					listaPalabras5letras(totalPalabras, palabra,palabra);


				} else {

					palabra[j] = entrada;
					totalPalabras = totalPalabras+1;
					if (j > 0) {
						
		                ultimaLetra = palabra[j-1].charAt((palabra[j-1].length())-1);

						if (ultimaLetra != entrada.charAt(0)) {
							
							indiceNombre = w-1;
							
							if (indiceNombre < 0) {

								indiceNombre = 1;
							}
							
							System.out.println("La palabra no esta concatenada por lo que "+nombre[contNombre]+ ", has perdido");
			
							eof=1;
							listaPalabras(totalPalabras, palabra);
							listaPalabras5letras(totalPalabras, palabra,palabra);
						}
					}
				}

			}

		}
		
		}else
			System.out.println("Error: Introduce numero de jugadores por argumento");
		
		sc.close();
	}

	
	/** 
	    * Funcion imprimir lista palabras total
	    * 
	    * @param totalPalabras 
	    * @param lista[]
	    * @return Devuelve la lista de palabras
	    */

	private static void listaPalabras(int totalPalabras, String lista[]) {
		
		   int i=0;
		
		System.out.println("\nLa lista de palabras es la siguiente:");

		for (i=0; i<totalPalabras-1; i++) {

			System.out.println(""+lista[i]);

		}

	}
	
	
	  /** 
	    * Funcion imprimir lista palabras mayor 5 caracteres
	    * 
	    * @param totalPalabras 
	    * @param palabra[]
	    * @param lista5letras[]
	    * @return Devuelve la lista de palabras mayores de 5 caracteres
	    */
	
	private static void listaPalabras5letras(int totalPalabras,String palabra[], String lista5letras[]) {
		
		int i=0,cont=0;
		
		System.out.println("\nLa lista de palabras de mas de 5 caracteres es la siguiente:");

		for (i=0; i<totalPalabras-1; i++) {
			 if(palabra[i].length()>5){
                	 
			       System.out.println(""+lista5letras[i]);
			       cont++;
			          
			           }
                 
		}
		
		if(cont==0){
			
               System.out.println("No hay palabras mayores de 5 caracteres");

		} 
		       
	  }
   }
