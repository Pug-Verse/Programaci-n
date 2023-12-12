
import java.io.*;

/**
 * Representa una cuenta bancaria generica.
 */

public abstract class Cuenta implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private String codigoCuenta;
  private double saldo;
  private int contador = 0;
  
  /**
   * Crea una cuenta con el codigo indicado y saldo inicial cero.
   *  
   * @param codigoC - codigo de la cuenta a crear.
   */
  
  public Cuenta (String codigoC){
    this.codigoCuenta = codigoC ;
    this.saldo = 0;
  }
  
  
  /**
   * Devuelve el codigo de la cuenta.
   */
  
  public String getCodigo(){
    return codigoCuenta;
  }
  
  
  /**
   *  Devuelve la descripcion de la cuenta.
   */
  public abstract String getDescripcion();
  
  
  /**
   *  Devuelve el saldo actual de la cuenta.
   */
  
  public double getSaldo (){
    return saldo;
  }
  
  
  /**
   * Actualiza el saldo de la cuenta.
   * 
   * @param cantidad - dinero del saldo a modificar
   */
  
  public void setSaldo(double cantidad){
    this.saldo = cantidad;
  }
  
  
   /**
   * Ingresa una cantidad en la cuenta.
   * 
   * @param cantidad - cantidad a ingresar.
   * 
   * @throws IllegalArgumentException - Se ha especificado una cantidad negativa.
   */
  
  public void ingresar (double cantidad){
    
    double dinero = getSaldo();
    double total = 0;
     
    if (cantidad > 0){  
      total = dinero + cantidad;
      setSaldo (total);
    }else {
      throw new IllegalArgumentException ("Se ha especificado una cantidad negativa.");
    }
    
  }
    
  /**
   * Saca una cantidad de la cuenta, siempre que haya saldo suficiente.
   * 
   * @param cantidad - dinero que se quiere sacar.
   * 
   * @throws IllegalArgumentException - Se ha especificado una cantidad negativa.
   * @throws BancoException - La cantidad es superior al saldo disponible.
   * 
   */
  
  public void sacar (double cantidad) throws BancoException, IllegalArgumentException{
    
    double saldo = getSaldo();
    double total;
    
    
    if (cantidad > 0){
      if(cantidad < saldo){
        total = saldo - cantidad ;
        setSaldo (total);
      }
      else{
        throw new BancoException  ("Operacion cancelada. La cantidad es superior al saldo disponible.");
      }
    }
    else{
      throw new IllegalArgumentException ("Error. Se ha especificado una cantidad negativa.");
    }
    
  }
  
  
  
  /**
   * Transfiere una cantidad de esta cuenta a otra cuenta
   * 
   * @param cantidad - dinero para la transferencia.
   * @param destino - cuenta de destino.
   */
  
  public abstract  double transferir (double cantidad, Cuenta destino) throws BancoException ;
  
  
   
  public int getContador(){
    return contador;
  }
  
  public void setContador (int a){
    this.contador = a;
  }
  
  
  
  /**
   * Metodo que incrementa y modifica el contador de transferencias.
   * 
   * return salida - numero del contador
   */
  
  public int numeroTransferencias(){
    int salida = getContador();
   
    int numero = getContador()+1;
    setContador(numero);
    
    return salida;
    
  }
}
    
 
    
  