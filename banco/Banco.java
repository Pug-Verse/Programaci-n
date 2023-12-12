import java.util.*;
import java.io.*;


public class Banco implements Serializable{
  
  private static final long serialVersionUID = 1L;
  private List <Cuenta> cuentas ;
  public Iterator <Cuenta> iter;
  public Random r;
  /**
   * Crea un banco, recuperando sus datos del fichero binario indicado.
   * 
   * @param nombreFichero - Nombre del fichero binario.
   * 
   * @throws BancoException -  No se ha podido recuperar la informacion 
   * del fichero, por ejemplo, por un problema de E/S.
   */
  
  public Banco(String nombreFichero) throws IOException, ClassNotFoundException {
  
    cuentas = new ArrayList <Cuenta>();
    Object cuenta ;
    boolean condicion = true;
    ObjectInputStream entrada = new ObjectInputStream (new FileInputStream (nombreFichero));
   
    try{
      cuenta = entrada.readObject();
      
      while (cuenta != null){
        
        if (cuenta instanceof CuentaRoja){
          cuentas.add( (CuentaRoja)cuenta );
        }
        if (cuenta instanceof CuentaVerde){
          cuentas.add( (CuentaVerde)cuenta );
        }
        if (cuenta instanceof CuentaAmarilla){
          cuentas.add( (CuentaAmarilla)cuenta );
        }
        if(cuenta == null){
          condicion = true;
        }
        
        cuenta = entrada.readObject();
      }
      
    }catch(EOFException e){ 
      condicion = false;
    }finally{
      if(condicion = false){
        entrada.close();
      }
    } 
  }
  
  
  /**
   * Constructor sin fichero.
   * Crea un banco vacio.
   */
  
  public Banco(){
    cuentas = new ArrayList <Cuenta>();
  }
  
  
  /**
   * Devuelve una cuenta por su codigo.
   * 
   * @param codigo - Codigo de la cuenta buscada.
   * 
   * @return Cuenta bancaria con ese codigo o null si no hay ninguna cuenta con ese codigo.
   */
  
  public Cuenta getCuenta(String codigo){
    
    iter = cuentas.iterator();
    Cuenta aux = null;
    Cuenta salida = null;
    
    while( iter.hasNext()){
      aux = iter.next();
      if (aux.getCodigo().equals(codigo)){
        salida = aux;
      }
    }
    return salida;
  }
    
       
  /**
   * Abre una cuenta roja. En la cuenta roja,las primeras Nr transferencias estan 
   * exentas de comision. A partir de ese momento, se carga un porcentaje Pr sobre 
   * el importe de cada transferencia.
   * 
   * @param nr - Numero de transferencias exentas.
   * @param pr - Porcentaje de comision para las transferencias no exentas.
   *
   * @return Codigo de la nueva cuenta.
   * 
   * @throws BancoException - Codigo de cuenta repetido.
   * @throws IllegalArgumentException - Alguno de los parametros Pr o Nr no son validos.
   * 
   */
  
  public String abrirCuentaRoja(int nr, double pr) throws BancoException, IllegalArgumentException{
    
    CuentaRoja cuentaR;
    
    if ( (nr < 0)||(pr < 0) ){
      throw new IllegalArgumentException ("Algun parametro no valido.");
    }
    
    String codigoCuenta = creaPass();
    
    if (existeCuenta (codigoCuenta)){
      throw new BancoException ("Codigo de cuenta repetido. OPERACION CANCELADA");
    }
    else{
      cuentaR = new CuentaRoja (codigoCuenta, nr, pr);
      cuentas.add(cuentaR);
    }
    
    return cuentaR.getCodigo();
  }
  
  
  /**
   * Abre una cuenta amarilla. En la cuenta amarilla, se carga una 
   * comision fija de Ca euros a cada transferencia.
   * 
   * @param ca - Comision en euros que se aplicara a las transferencias.
   *
   * @return Codigo de la nueva cuenta.
   * 
   * @throws BancoException - Codigo de cuenta repetido.
   * @throws IllegalArgumentException - Parametro Ca no valido.
   * 
   */
  
  public String abrirCuentaAmarilla(double ca) throws BancoException, IllegalArgumentException{
   
    CuentaAmarilla cuentaA;
    
    if (ca < 0){
      throw new IllegalArgumentException ("Parametro Ca no valido.");
    }
    
    String codigoCuenta = creaPass();
    if (existeCuenta (codigoCuenta)){
      throw new BancoException ("Codigo de cuenta repetido. OPERACION CANCELADA");
    }else{
      cuentaA = new CuentaAmarilla (codigoCuenta,  ca);
      cuentas.add(cuentaA);
    }
   
    return cuentaA.getCodigo();
  }
      
  
  /**
   * Abre una cuenta verde. En la cuenta verde, si el importe de la transferencia es menor o igual que Iv, 
   * se carga la comision porcentual Pv1. En otro caso, se carga la comision porcentual Pv2.
   * 
   * @param Iv - Cantidad en euros que sera el umbral para aplicar un porcentaje u otro.
   * @param Pv1 - Porcentaje que se aplicara a las transferencias de importe menor o igual al umbral.
   * @param Pv2 - Porcentaje que se aplicara a las transferencias de importe mayor al umbral.
   * 
   * 
   * @return Codigo de la nueva cuenta.
   * 
   * @throws BancoException - Codigo de cuenta repetido.
   * @throws IllegalArgumentException - Alguno de los par�metros Iv, Pv1 o Pv2 no son validos.
   */
  
   public String abrirCuentaVerde(double Iv, double Pv1, double Pv2) throws BancoException, IllegalArgumentException{
     
     CuentaVerde cuentaV;
     
     if ( (Iv<0)||(Pv1<0)||(Pv2<0) ){
       throw new IllegalArgumentException ("Algun parametro no valido.");
     }
     
     String codigoCuenta = creaPass();
     if (existeCuenta (codigoCuenta)){
       throw new BancoException ("Codigo de cuenta repetido. OPERACION CANCELADA");
     }
     else{
       cuentaV = new CuentaVerde (codigoCuenta,  Iv,  Pv1,  Pv2);
       cuentas.add(cuentaV);
     }
     
     return cuentaV.getCodigo();
   }
   
   
   
   /**
    * Devuelve una lista con todos los codigos de las cuentas existentes en el banco.
    * 
    * @return Lista de los codigos de las cuentas existentes en el banco.
    */
   
   public String getListaCodigos(){
     
     String lista = (" ");
     if(cuentas.size() == 0){
       lista = ("No existen cuentas");
     }
     else{
       iter = cuentas.iterator();
       Cuenta cuenta;
     
       while (iter.hasNext()){
         cuenta = iter.next();
         lista = (lista + "\nCuenta " + cuenta.getDescripcion() + " con codigo: " + cuenta.getCodigo() + " ---> " + cuenta.getSaldo() + " euros");
       }
     }
     return lista;
   }
   
   
     
   /**
    * Guarda el contenido del banco en el fichero binario indicado, 
    * serializando todos los objetos necesarios.
    * 
    * @param nombreFichero - Nombre del fichero donde se quiere guardar el banco.
    * 
    * @throws BancoException - No se ha podido guardar el banco en el fichero indicado.
    */
   
   public void guardar(String nombreFichero) throws BancoException, IOException, ClassNotFoundException {
     
     ObjectOutputStream salida = new ObjectOutputStream (new FileOutputStream (nombreFichero));
     Cuenta cuenta;
     iter = cuentas.iterator();
     
       while(iter.hasNext()){
   
         cuenta = iter.next();
         if(cuenta instanceof CuentaAmarilla){
           salida.writeObject ( (CuentaAmarilla)cuenta );
         }
         if(cuenta instanceof CuentaVerde){
           salida.writeObject ((CuentaVerde)cuenta);
         }
         if(cuenta instanceof CuentaRoja){
           salida.writeObject ((CuentaRoja)cuenta);
         }
       }
       
    
       salida.close();
     }
   
   
   /**
    * Metodo que determina si una cuenta dada por su codigo existe en el buzon.
    * 
    * @param codigoCuenta - codigo de la cuenta a buscar.
    * 
    * @return salida - true si existe, false en caso contrario.
    */
   
   public boolean existeCuenta (String codigoCuenta){ //devuelve true si existe la cuenta
    
     boolean salida = false;
     iter = cuentas.iterator();
 
     while(iter.hasNext()){
       Cuenta cuenta = iter.next();
       if (cuenta.getCodigo().equals(codigoCuenta)){
         salida = true;
       }
     }
     return salida;
   }
     
   
   /**
    * Elimina una cuenta pasada como parametro.
    * 
    * @param cuenta - cuenta a eliminar.
    * 
    * @return texto - Mensaje de eliminacion correcta.
    */
   
   public String eliminar (Cuenta cuenta){
     String nombre = cuenta.getCodigo();
     String texto;
     
     cuentas.remove(cuenta);
     texto = ("Cuenta "+nombre+" eliminada.\nGracias por confiar en *Banco Alvaro*");
     return texto;
   }
    
   
   /**
    * Metodo que busca si existe alguna cuenta.
    * 
    * @return salida - valor de true o false si existe o no respectivamente alguna cuenta.
    */
   
   public boolean hayCuentas(){
     boolean salida = false;
     if( cuentas.size() >0){
       salida = true;
     }
     return salida;
   }
   
   
   /**
    * Metodo que crea una cadena alfanumerica aleatoria
    * 
    * return pass - cadena alfanumerica.
    */

   public String creaPass(){
     char[] elementos={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','�','o','p','q','r','s','t','u','v','w','x','y','z'};
     
     char[] conjunto = new char[8];
     String pass;
     
     for(int i=0;i<8;i++){
       int el = (int)(Math.random()*37);
       conjunto[i] = (char)elementos[el];
     }
     return pass = new String(conjunto);
   }
   
   /**
    * Metodo que busca si existe una cuenta roja en el buzon
    * 
    * return salida - true si existe alguna, false si no existe ninguna.
    */
   
   public CuentaRoja  existeRoja(){
     
     iter = cuentas.iterator();
     Cuenta cuenta;
     CuentaRoja salida = null;
     
     while (iter.hasNext()){
       cuenta = iter.next();
       if( cuenta instanceof CuentaRoja){
         salida = (CuentaRoja)cuenta;
       }
     }
     return salida;
   }
   
   
   /**
    * Metodo que busca si existe una cuenta amarilla en el buzon
    * 
    * return salida - true si existe alguna, false si no existe ninguna.
    */
   
   public CuentaAmarilla  existeAmarilla(){
     
     iter = cuentas.iterator();
     Cuenta cuenta;
     CuentaAmarilla salida = null;
     
     while (iter.hasNext()){
       cuenta = iter.next();
       if( cuenta instanceof CuentaAmarilla){
         salida = (CuentaAmarilla)cuenta;
       }
     }
     return salida;
   }
   
   
    
    /**
    * Metodo que busca si existe una cuenta verde en el buzon
    * 
    * return salida - true si existe alguna, false si no existe ninguna.
    */
    
    public CuentaVerde existeVerde(){
      
      iter = cuentas.iterator();
      Cuenta cuenta;
      CuentaVerde salida = null;
      
      while (iter.hasNext()){
        cuenta = iter.next();
        if( cuenta instanceof CuentaVerde){
          salida = (CuentaVerde)cuenta;
        }
      }
      return salida;
    }  
}