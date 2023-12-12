package animacion;

public class EfectoSecuencia implements Efecto{
	
	Efecto primero,segundo;
	
	/** 
	 * Constructor de la clase
	 * Crea un efecto compuesto de dos efectos que se aplicaran uno a continuacion del otro (segundo despues de primero)
	 * @param primero - primer efecto a aplicar 
	 * @param segundo - segundo efecto a aplicar
	 */
	
	public EfectoSecuencia(Efecto primero, Efecto segundo){
		
		this.primero=primero;
		this.segundo=segundo;
	}
	
	/** 
	 * Genera una imagen de salida a partir de la imagen de entrada aplicando el efecto que corresponda.
	 * @param entrada - imagen de entrada
	 * @return la imagen de salida con el efecto aplicado
	 */
	
	public Imagen transformar(Imagen entrada){
	
		Imagen temp,temp2;
		
		temp=primero.transformar(entrada);
		temp2=segundo.transformar(temp);
					
		return temp2;
		
	}

}
