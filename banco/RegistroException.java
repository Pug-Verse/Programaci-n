
/**
 * Excepcion para senalizar problemas con el registro de operaciones.
 */

public class RegistroException extends Exception{
  
   /**
   * Constructor :
   * 
   * @param mensaje - mensaje de error.
   */
  
   private static final long serialVersionUID = 1L;

    
  public RegistroException (String mensaje){
    
    super(mensaje);
  }
  
} 
