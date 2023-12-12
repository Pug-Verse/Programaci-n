
/**
 *  Esta interfaz permite registrar operaciones bancarias.
 */

public interface IRegistro{
  
 static final int INGRESO = 1;
 static final int RETIRADA = 2;
 static final int TRANSFERENCIA = 3;
 static final int REALIZADA = 1;
 static final int NO_REALIZADA = 2;
  
  
  /**
   * Registra una operacion bancaria.
   * 
   * @param tipoOperacion - ingreso, reintegro, transferencia.
   * @param cuentaOrigen - en caso de transferencia
   * @param cuentaDestino - en caso de transferencia
   * @param importe - sin las posibles comisiones en el caso de las transferencias
   * @param resultado - realizada o no realizada.
   * 
   * @throws - IllegalArgumentException si el parametro texto es null, si el parametro resultado 
   * no tiene un valor dentro de los predefinidos en las constantes.
   * @throws - SICELException si se produce cualquier otro tipo de error.
   */ 
  
  void registrar(int tipoOperacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, double importe, int resultado) throws RegistroException;
   
}