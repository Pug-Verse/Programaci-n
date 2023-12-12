import java.io.*;


/**
 * Cuenta verde. Si el importe de la transferencia es menor o igual que Iv, se carga una
 * comision porcentual de Pv1. Si no, se carga una comision porcentual de Pv2.
 */

public class CuentaVerde extends Cuenta implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private double Iv;
  private double Pv1;
  private double Pv2;
  private String descripcion;
  
  /**
   * Crea una cuenta verde con los parametros indicados para la comision.
   */
  
  public CuentaVerde (String codigoCuenta, double Iv, double Pv1, double Pv2){
    
    super (codigoCuenta);
    this.Iv = Iv;
    this.Pv1 = Pv1;
    this.Pv2 = Pv2;
    this.descripcion = ("Verde");
  }
  
  
  public double getIv (){
    return Iv;
  }
  
  public double getPv1 (){
    return Pv1;
  }
  
  public double getPv2 (){
    return Pv2;
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
    
    double sDestino = destino.getSaldo();
    double sCuenta = getSaldo();
    double salida = 0;
    double resultado = sDestino+cantidad;
    
    if( sCuenta < cantidad){
      throw new BancoException ("Saldo insuficiente en la cuenta para hacer la transferencia");
    }else{
      if (cantidad <= getIv()){
        destino.setSaldo (resultado - ((cantidad*getPv1())/100));
        setSaldo (sCuenta - cantidad);
        salida = ((cantidad*getPv1())/100);
      }else{
      destino.setSaldo (resultado - ((cantidad*getPv2())/100));
      setSaldo (sCuenta - cantidad);
      salida = ((cantidad*getPv2())/100);
      }
     }
    return salida;
  }
  
  
  /**
   * Devuelve la descripcion
   * 
   * return descripcion - Descripcion (Verde)
   */
  
  public String getDescripcion(){
    return descripcion;
  }
}
