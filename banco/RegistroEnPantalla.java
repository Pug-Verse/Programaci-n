
/**
 * Registra las operaciones bancarias en la pantalla del ordenador.
 */

class RegistroEnPantalla implements IRegistro {
 
  
  /**
   * Registra una operacion bancaria en la pantalla del ordenador.
   * 
   * @param tipoOperacion - Tipo de operacion (INGRESO, RETIRADA, TRANSFERENCIA).
   * @param cuentaOrigen - Cuenta sobre la que se ha hecho la operacion.
   * @param cuentaDestino - En transferencias, cuenta de destino. En otro caso, null.
   * @param importe - Importe de la operacion.
   * @param resultado - Resultado de la operacion (REALIZADA, NO_REALIZADA).
   * 
   * @throws - RegistroException - No se ha podido registrar la operacion.
   * 
   */
  
  public void registrar (int tipoOperacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, double importe, int resultado) throws RegistroException{
    
    if ((tipoOperacion == 1)||(tipoOperacion == 2)||(tipoOperacion == 3)){
      if((cuentaOrigen != null) && (cuentaDestino != null) && (importe > 0)){
        if((resultado == 1)||(resultado == 2)){
          System.out.println ("\nREGISTRO OPERACION:"); 
          System.out.println ("Resultado: " + resultado);
          System.out.println ("Tipo de Operacion: " + tipoOperacion);
          System.out.println ("Cuenta de Origen: " + cuentaOrigen);
          System.out.println ("Cuenta de Destino: " + cuentaDestino);
          System.out.println ("Importe: " + importe);
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
