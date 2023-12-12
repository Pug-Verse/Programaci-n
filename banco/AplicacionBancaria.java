
import java.util.*;
import java.io.*;

public class AplicacionBancaria {
 private static Scanner teclado;

 public static void main(String args[]) {
  Banco banco = null;
  
  try{
    banco = new Banco ("banco.dat");
  }catch (IOException e1){
    banco = new Banco();
  }catch ( ClassNotFoundException e1){
    System.out.println (e1.getMessage());
  }
  
  menuPrincipal(banco);
  
  try{
    banco.guardar ("banco.dat");
  }catch(BancoException e){
    System.out.println (e.getMessage());
  }catch(IOException e2){
    System.out.println (e2.getMessage());
  }catch (ClassNotFoundException e3){
    System.out.println (e3.getMessage());
  }
 }

 private static void menuPrincipal(Banco banco) {
  int opcion;
  String codigo;
  teclado = new Scanner(System.in);
  String est = "inactivo";
  int resp = 1;
  
  do {
    System.out.println("\n=========================");
   System.out.println("      BANCO ALVARO");
   System.out.println("=========================\n");
   System.out.println("1 - Listado de cuentas");
   System.out.println("2 - Abrir una cuenta nueva");
   System.out.println("3 - Eliminar una cuenta");
   System.out.println("4 - Operar con una cuenta");
   System.out.println("5 - Activar/Desactivar registro de operaciones");
   System.out.println("99 - Salir del programa");
   System.out.print("\nOpcion: ");
   opcion = teclado.nextInt();
   teclado.nextLine();

   switch(opcion) {  
   case 1: // Listar cuentas
     System.out.println ( "\n --> LISTA DE CUENTAS: \n" + banco.getListaCodigos() );
    break;
    
   case 2: // Abrir cuenta nueva 
    menuAbrirCuenta(banco);
    break;
    
   case 3: // Eliminar cuenta
     System.out.println (" --> ELIMINAR CUENTA:");
     if(banco.hayCuentas()){
       System.out.print ("Indique el codigo de la cuenta que desea eliminar: ");
       codigo = teclado.nextLine();
       if(banco.existeCuenta(codigo)){
         Cuenta vieja = banco.getCuenta(codigo);
         System.out.println(banco.eliminar(vieja));
       }
       else{
         System.out.println( "Cuenta con codigo " + codigo + " inexistente");
       }
     }else{ System.out.println ("No existen cuentas");}
    break;
    
   case 4: // Operar con una cuenta
     if(banco.hayCuentas()){
     System.out.print("Indique el codigo de la cuenta: ");
     codigo = teclado.nextLine();
     if (banco.existeCuenta (codigo)){
       menuCuenta(banco, banco.getCuenta(codigo), est, resp);
     }else{
       System.out.println ("ERROR. Cuenta " + codigo + " no existente.");
     }
   }else{System.out.println ("No existen cuentas");}
   break;
     
   case 5: // Activar/desactivar registro de operaciones
     int respuesta;
     do{
       System.out.println("\nREGISTRO DE OPERACIONES:\n************************\nElija una operacion:\n1.Activar\n2.Desactivar\n99.Volver.");
       respuesta = teclado.nextInt();
      
       switch (respuesta){
         case 1:
           est = ("activo");
           
           System.out.println("¿Que registro desea hacer?\n 1.En pantalla\n 2.En fichero");
           resp = teclado.nextInt();
           while ((resp != 1)&&(resp != 2)){
             System.out.print("Opcion no valida, introduzca una nueva: ");
             resp = teclado.nextInt();
           }
           if(resp == 1){
             System.out.println("Registro en Pantalla ACTIVADO.");
           }
           if(resp == 2){
             System.out.println("Registro en Fichero ACTIVADO.");
           }
           break;
           
         case 2:
           est = "inactivo" ;
           System.out.println("Registro DESACTIVADO.");
           break;
       }
     }while (respuesta != 99);
     
    break;
   }
  } while (opcion != 99);
  System.out.println("Gracias por confiar en Banco Alvaro. \nHasta pronto");
 }

 private static void menuAbrirCuenta(Banco banco) {
  int opcion;
  
  double pr = 0; //Variables cuenta Roja
  int nr = 0;
  
  double ca = 0; // variable para crear la cuenta amarilla

  double iv = 0; // Variables de la cuenta Verde
  double pv1 = 0;
  double pv2 = 0;
  
  
  do {
   System.out.println("\n\n--> ABRIR UNA CUENTA NUEVA");
  
   System.out.println("\nINDIQUE TIPO DE CUENTA QUE DESEA ABRIR:");
   System.out.println("1 - Cuenta roja");
   System.out.println("2 - Cuenta amarilla");
   System.out.println("3 - Cuenta verde");
   System.out.println("99 - Volver al menu anterior");
   System.out.print("\nOpcion: ");
   opcion = teclado.nextInt();
   teclado.nextLine();
   switch(opcion) {
   case 1: // Abrir cuenta roja
     System.out.println ("\nCUENTA ROJA \n===========");
     try{
       
       if(banco.existeRoja() == null){
         System.out.print ("Introduzca un porcentaje de comision para las transferencias: ");
         pr = teclado.nextDouble();
         
         System.out.print ("Introduzca la cantidad de cuentas exentas de dicha comision: ");
         nr = teclado.nextInt();
       }else{
         CuentaRoja roja = banco.existeRoja();
         nr = roja.getNR();
         pr = roja.getComision();
       }
       String codigo = banco.abrirCuentaRoja(nr, pr);
       System.out.println ("--> Cuenta Roja con codigo "+ codigo + " abierta correctamente <--\nLa transferencia de dinero desde estas cuentas tendra una comision del "+pr+ " %, excepto las "+nr+" primeras cuentas.");
     }catch(BancoException e){
       System.out.println (e.getMessage());
     }
    break;
    
   case 2: // Abrir cuenta amarilla
     System.out.println ("\nCUENTA AMARILLA \n===============");
     try{
       if (banco.existeAmarilla() == null){
         System.out.print ("Introduzca la comision en euros que se aplicara a cada transferencia: ");
         ca = teclado.nextDouble();
       }else{
         CuentaAmarilla amarilla = banco.existeAmarilla();
         ca = amarilla.getComision ();
       }
       String codigo = banco.abrirCuentaAmarilla (ca);
       System.out.println ("--> Cuenta Amarilla con codigo "+ codigo + " abierta correctamente <--\nLas transferencias tendran una comision del "+ca+" %.");
     }catch(BancoException e){
       System.out.println (e.getMessage());
     }
    break;
    
   case 3: // Abrir cuenta verde
     System.out.println ("\nCUENTA VERDE \n============");
     try{
       if(banco.existeVerde() == null){
         System.out.print ("Cantidad en euros que sera el umbral para aplicar un porcentaje u otro: ");
         iv = teclado.nextDouble();
         
         System.out.print ("Porcentaje que se aplicara a las transferencias de importe menor o igual al umbral: ");
         pv1 = teclado.nextDouble();
         
         System.out.print ("Porcentaje que se aplicara a las transferencias de importe mayor al umbral: ");
         pv2 = teclado.nextDouble();
       }else {
         CuentaVerde verde = banco.existeVerde();
         iv = verde.getIv();
         pv1 = verde.getPv1();
         pv2 = verde.getPv2();
       }
       String codigo = banco.abrirCuentaVerde (iv, pv1, pv2);
       System.out.println ("--> Cuenta Verde con codigo "+ codigo + " abierta correctamente <--\nLas transferencias de cantidad menor de "+iv+" euros, tendran una comision del "+pv1+" %, las de mayor cantidad tendran una comision del "+pv2+" %." );
     }catch(BancoException e){
       System.out.println (e.getMessage());
     }
   break;
   }
  } while (opcion != 99);
 }

 
 
 
 private static void menuCuenta(Banco banco, Cuenta cuenta, String estado, int respuesta) {
  int opcion;
   IRegistro registro = new RegistroEnPantalla();
   if (respuesta == 2){
     registro = new RegistroEnFichero();
   }
  
  do {
  
   System.out.println("\n\n--> OPERAR CON LA CUENTA " + cuenta.getDescripcion() + " " + cuenta.getCodigo() + " <--");
  
   System.out.println("\nINDIQUE OPERACION QUE DESEA EFECTUAR:");
   System.out.println("1 - Ingresar");
   System.out.println("2 - Sacar");
   System.out.println("3 - Transferir");
   System.out.println("4 - Consultar el saldo");
   System.out.println("99 - Volver al menu anterior");
   System.out.print("\nOpcion: ");
   opcion = teclado.nextInt();
   teclado.nextLine();
   switch(opcion) {
     case 1: // Ingresar
       System.out.println("\n\n==== INGRESAR ====\n");
       System.out.println("¿Cuanto dinero en euros desea ingresar?") ;
       double cantidad = teclado.nextDouble();
       try{
         cuenta.ingresar (cantidad);
         System.out.println("Ingreso de "+cantidad+" euros en la cuenta "+cuenta.getDescripcion()+" "+cuenta.getCodigo()+" realizado con exito." );
         
         if(estado.equals("activo")){
           try{
             int tipoOperacion = 1;
             Cuenta cuentaOrigen = cuenta;
             Cuenta cuentaDestino = cuenta;
             double importe = cantidad;
             int resultado = 1;
             
             registro.registrar (tipoOperacion, cuentaOrigen, cuentaDestino, importe, resultado);
           }catch(RegistroException e){
             System.out.println (e.getMessage());
           }
         }
       }catch(IllegalArgumentException e){
         System.out.println (e.getMessage());
       }
       
       break;
       
     case 2: // Sacar
       System.out.println("\n\n==== SACAR ====\n");
       System.out.println("¿Cuanto dinero en euros desea sacar?") ;
       cantidad = teclado.nextDouble();
       try{
         cuenta.sacar(cantidad);
         System.out.println ("Recoja sus " + cantidad + " euros");
         
         if(estado.equals ("activo")){
           try{
             int tipoOperacion = 2;
             Cuenta cuentaOrigen = cuenta;
             Cuenta cuentaDestino = cuenta;
             double importe = cantidad;
             int resultado = 1;
             
             registro.registrar (tipoOperacion, cuentaOrigen, cuentaDestino, importe, resultado);
           }catch(RegistroException e){
             System.out.println (e.getMessage());
           }
         }
       }catch (BancoException e1){
         System.out.println (e1.getMessage());
       }catch (IllegalArgumentException e2){
         System.out.println (e2.getMessage());
       }
       break;
    
   case 3: // Transferir
     System.out.println("\n\n==== TRANSFERIR ====\n");
     if(cuenta.getSaldo() > 0){
     
     System.out.println("¿Cuanto dinero en euros desea transferir?") ;
     cantidad = teclado.nextDouble();
     
       System.out.print("Introduzca el numero de cuenta de destino: ");
       String codigoDestino = teclado.next();
       if ( banco.existeCuenta(codigoDestino)){
         try{
           cuenta.transferir(cantidad, banco.getCuenta(codigoDestino));
           System.out.println("Transferencia realizada con sus debidas comisiones");
           
         }catch ( BancoException e ){
           System.out.println(e.getMessage());
         }
       }else{ 
         System.out.println("Cuenta de destino no valida");  
       }
     }else{
       System.out.println("No tiene dinero en la cuenta\nOperacion cancelada.");
     }
     
     
     
    break;
    
   case 4: // Consultar el saldo
     System.out.println("\n\n==== CONSULTA DE SALDO ====\n");
     System.out.println("El saldo de la cuenta " + cuenta.getDescripcion() + " " + cuenta.getCodigo() + " es " + cuenta.getSaldo() + " euros");
    break;
   }
  } while (opcion != 99);
 }
 
 
 

}