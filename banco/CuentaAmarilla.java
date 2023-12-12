
import java.io.*;


/**
 * Cuenta amarilla. Se carga una cantidad fija de Ca euros a cada transferencia.
 */

public class CuentaAmarilla extends Cuenta implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private String descripcion;
  private double comision;
  /**
   *  Crea una cuenta amarilla con los parametros indicados para la comision.
   *  
   * @param codigoC - C�digo de la nueva cuenta.
   * @param cA - Cantidad en euros que se aplicara como comision a las transferencias.
   */
  
  public CuentaAmarilla (String codigoC, double cA){
    super (codigoC);
    this.comision = cA ;
    this.descripcion = "Amarilla";
  }
  
  /**
   *  Devuelve la descripcion de la cuenta.
   * 
   */
  
  public String getDescripcion (){
    return descripcion;
  }

  
  public double getComision(){
    return comision;
  }
   
  /**
   *  Transfiere una cantidad de esta cuenta a otra cuenta.
   * 
   *  @param cantidad - Cantidad que se desea transferir.
   *  @param destino - Cuenta beneficiaria de la transferencia.
   * 
   * @throws BancoException - Saldo insuficiente para hacer la transferencia.
   */
  
  public double transferir (double cantidad, Cuenta destino) throws BancoException{
    
    double sDestino = destino.getSaldo();
    double sCuenta = getSaldo();
    double salida = 0;
    
    if( sCuenta < cantidad){
      throw new BancoException ("Saldo insuficiente en la cuenta para hacer la transferencia");
    }else{
      destino.setSaldo (sDestino + cantidad - ((cantidad*getComision())/100));
      setSaldo (sCuenta - cantidad);
    }
    salida = ((cantidad*getComision())/100);
    return salida;
  }
    
  
} 
  
  