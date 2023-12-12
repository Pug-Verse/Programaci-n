
/**
 * 
 * Excepcion para senalizar problemas con la logica de la aplicacion bancaria.
 * 
 */

public class BancoException extends Exception{
  
   /**
   * Constructor :
   * 
   * @param mensaje - mensaje de error.
   */
  
   private static final long serialVersionUID = 1L;

    
  public BancoException (String mensaje){
    
    super(mensaje);
  }
  
} 
