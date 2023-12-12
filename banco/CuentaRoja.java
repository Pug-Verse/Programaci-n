
import java.io.*;


/**
 * Cuenta roja. Las primeras Nr transferencias estan exentas de comision. 
 * A partir de ese momento se cargara un porcentaje Pr sobre el importe de la transferencia.
 */

public class CuentaRoja extends Cuenta implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private int nR ;
  private double comision ;
  private String descripcion;
  
  
  /**
   * Crea una cuenta roja con los parametros indicados para la comision.
   */
  
  public CuentaRoja (String codigoC, int nR, double pR){
    
    super(codigoC);
    this.nR = nR;
    this.comision = pR;
    this.descripcion = "Roja";
      
  }
  
  
  /**
   * Transfiere una cantidad de esta cuenta a otra cuenta.
   * 
   * @param cantidad - Cantidad que se desea transferir.
   * @param destino - Cuenta beneficiaria de la transferencia.
   * 
   * @throws BancoException - Saldo insuficiente para hacer la transferencia.
   */
  public double transferir (double cantidad, Cuenta destino) throws BancoException {
    
    int cont = numeroTransferencias();
    
    double sDestino = destino.getSaldo();
    double sCuenta = getSaldo();
    double salida = 0;
    
    if( sCuenta < cantidad){
      throw new BancoException ("Saldo insuficiente en la cuenta para hacer la transferencia");
    }else{
      if(cont < getNR()){
        destino.setSaldo (sDestino + cantidad);
        setSaldo (sCuenta - cantidad);
        salida = cantidad;
      }else{
        destino.setSaldo (sDestino + (cantidad +( (getComision()*cantidad)/100) ));
        setSaldo (sCuenta - cantidad);
        salida = (cantidad +( (getComision()*cantidad)/100) );
      }
    }
    
    setContador(cont);
    return salida;
  }
  
  
  /**
   * Devuelve el valor de nR
   * 
   * @return nR - nR
   */
  
  public int getNR(){
    return nR ;
  }
  
  
  /**
   * Devuelve el valor de la comision
   * 
   * @return comision - comision.
   */
  
  public double getComision(){
    return comision;
  }
  
  
  /**
   * Devuelve la descripcion
   * 
   * @return descripcion - Descripcion (Roja)
   */
  
  public String getDescripcion(){
    return descripcion;
  } 
  
}
