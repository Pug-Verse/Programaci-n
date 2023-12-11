#include <stdio.h>

int main(void)
{

int filas,separador,numerofilas,i,j,k ;
int newfilas,nfilas;
char caracter,letra;

     printf("       \nDIBUJAR TRIANGULO O ROMBO\n\n");
     fflush(stdout);
     
do{

    do{
     printf("Entre numero de filas (1-15): ");
     fflush(stdout);
     scanf("%d",&numerofilas);
     fflush(stdin);



    }while (numerofilas < 1 || numerofilas > 15);


    printf("Entre numero de espacios (a la izquierda): ");
    fflush(stdout);
    scanf("%d",&separador);
    fflush(stdin);

    printf("Entre caracter de relleno (numero->triangulo, caracter->rombo): ");
    fflush(stdout);
    scanf(" %c",&caracter);
    fflush(stdin);

    printf ("\n");
    printf ("\n");


    for (filas=1 ; filas<=numerofilas ; filas++){

         for (i=1 ; i<=separador ; i++)
              {
              printf (" ") ; /* Separador */
              fflush(stdout);
              }

              for (j=1 ; j<=numerofilas - filas ; j++)
                   {
                   printf(" ") ; /* blancos, espacios... */
                   fflush(stdout);
                   }

                   for (k=1 ; k<=2 * filas - 1 ; k++)
                        {
                        printf ("%c",caracter);
                        fflush(stdout);
                        }
                        printf ("\n");
               }
               /*=========================*/
               /* Segunda Parte del Rombo */
               /*=========================*/
               if (caracter >= 'a' && caracter <='z' )
                  {
                  newfilas=numerofilas - 1;


                  for (nfilas=newfilas ; nfilas >=1 ; nfilas--)
                      {
                            for (i=1 ; i<=separador ; i++)
                            {
                            printf (" ") ; /* Separador */
                            fflush(stdout);
                            }
                            for (j=1 ; j<=numerofilas - nfilas ; j++)
                                  {
                                  printf(" ") ; /* blancos, espacios... */
                                  fflush(stdout);
                                  }
                                  for (k=0; k<2*nfilas - 1 ; k++)
                                        {
                                        printf ("%c",caracter);
                                        fflush(stdout);
                                        }
                                        printf ("\n");
                                        fflush (stdout);

                        }
                   }




printf ("\n\n");
fflush(stdout);
printf ("Otro dibujo??\n");
fflush(stdout);
printf ("Respuesta afirmativa: s / Respuesta negativa: caracter distinto de s ---> ");
fflush(stdout);
scanf(" %c",&letra);
fflush(stdin);


   }while(letra == 's' || letra == 'S');
   return 0;

}
