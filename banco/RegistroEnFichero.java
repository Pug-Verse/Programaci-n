
import java.io.*;


/**
 * Clase que implmenta el interfaz IRegistro. Esta se encarga de realizar el registro en un fichero '.sice' .
 */

class RegistroEnFichero implements IRegistro{
  
  
 /**
  * Registra las operaciones bancarias en un fichero de texto.
  * 
  * @param tipoOperacion - Tipo de operacion (INGRESO, RETIRADA, TRANSFERENCIA).
  * @param cuentaOrigen - Cuenta sobre la que se ha hecho la operacion.
  * @param cuentaDestino - En transferencias, cuenta de destino. En otro caso, null.
  * @param importe - Importe de la operacion.
  * @param resultado - Resultado de la operacion (REALIZADA, NO_REALIZADA).
  * 
  * @throws - RegistroException - No se ha podido registrar la operacion.
  */
  
  public  void registrar (int tipoOperacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, double importe, int resultado) throws RegistroException{
   
    PrintWriter salida = null;
    String nombreFichero = ("registro.txt");
    
    if ((tipoOperacion == 1)||(tipoOperacion == 2)||(tipoOperacion == 3)){
      if((cuentaOrigen != null) && (cuentaDestino != null) && (importe > 0)){
        if((resultado == 1)||(resultado == 2)){
    
          try{
            salida = new PrintWriter (new FileWriter (nombreFichero, true));
            
            salida.print ("Resultado: " + resultado );
            salida.print (". Tipo de Operacion: " + tipoOperacion);
            salida.print  (". Cuenta de Origen: " + cuentaOrigen);
            salida.print (". Cuenta de Destino: " + cuentaDestino);
            salida.println (". Importe: " + importe);
          }catch (IOException e1){
            System.out.println ("error IOException");
          }
          finally{
            salida.close ();
          }
        }
        else{
          throw new RegistroException ("Resultado no valido. Registro cancelado.");
        }
        
      }
      else{ 
        throw new RegistroException ("ERROR en cuentas o importe. Registro cancelado.");
      }
    }
    else{ 
      throw new RegistroException ("ERROR, tipo de operacion no valida. Registro cancelado.");
    }
  }
      
} 