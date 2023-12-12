package animacion;
import java.awt.Color;

public class EfectoEscalaGrises extends java.lang.Object implements Efecto{
		
	/** 
	 * Genera una imagen de salida a partir de la imagen de entrada aplicando el efecto que corresponda.
	 * @param original - imagen de entrada
	 * @return la imagen de salida con el efecto aplicado
	 */
	
	public Imagen transformar(Imagen original){
		
		int i,j;
		Color color;
		int gris;
		
		for(i=0;i<original.getAncho();i++){
			for(j=0;j<original.getAlto();j++){

			    color=original.getColor(i,j);
			    gris=((color.getRed()+color.getGreen()+color.getBlue())/3);
			    
			    Color grey=new Color(gris,gris,gris);
				original.setColor(i,j,grey);
				
			}
			
		}
		
		return original;	
	}
}
