#include <stdio.h>

#define MAX_FILAS 5 //Definimos el tamano maximo de las filas
#define MAX_COLUM 5 //Definimos el tamano maximo de las columnas

void pedirDimensiones(int *filas, int *columnas);
int menu(void);
void pedirMatriz(int matriz[MAX_FILAS][MAX_COLUM], int filas, int colum);
void verMatriz(int matriz[MAX_FILAS][MAX_COLUM], int filas, int colum);
void sumarMatriz(int suma[MAX_FILAS][MAX_COLUM],int s1[MAX_FILAS][MAX_COLUM],int s2[MAX_FILAS][MAX_COLUM],int f1,int c1);
void multiplicarMatriz(int prod[MAX_FILAS][MAX_COLUM],int p1[MAX_FILAS][MAX_COLUM],int p2[MAX_FILAS][MAX_COLUM],int f1,int c1,int c2);

int main (void)
{

	int matrizA[MAX_FILAS][MAX_COLUM],matrizB[MAX_FILAS][MAX_COLUM];
	int multiplicacion[MAX_FILAS][MAX_COLUM]; //Matriz multiplicaci�n
	int suma[MAX_FILAS][MAX_COLUM]; //Matriz suma
	int filasA,columnasA,filasB,columnasB,opcion=0,control=0;

	printf("Introduccion de las dimensiones de las matrices\n\n");
	printf("** Matriz A **\n");
	pedirDimensiones(&filasA,&columnasA);
	printf("\n** Matriz B **\n");
	pedirDimensiones(&filasB,&columnasB);

	do{
		opcion=menu();
		if (opcion>=1 && opcion<=4){ //Condicion para que sea una opcion valida

			switch(opcion){

			case 1:

				control=1; //Ponemos control a uno para saber que ha pasado por aqui
				printf("Introducir datos en las matrices\n\n");
				fflush(stdout);

				printf("** Matriz A **\n");
				fflush(stdout);
				pedirMatriz(matrizA,filasA,columnasA);

				printf("** Matriz B **\n");
				fflush(stdout);
				pedirMatriz(matrizB,filasB,columnasB);

				printf("\nLas matrices introduidas son las siguientes:\n\n");

				printf("** Matriz A **\n");
				verMatriz(matrizA,filasA,columnasA);
				printf("\n** Matriz B **\n");
				verMatriz(matrizB,filasB,columnasB);

				break;

			case 2:

				if(control==1){ //Preguntamos si ha pasado antes por el caso 1

					if(filasA==filasB && columnasA==columnasB){ //Condicion para poder sumar matrices

						printf("\n\n** Matriz A **\n");
						fflush(stdout);
						verMatriz (matrizA,filasA,columnasA);

						printf("\n\n** Matriz B **\n");
						fflush(stdout);
						verMatriz(matrizB,filasB,columnasB);

						printf("\n** Matriz (A+B) **\n");
						sumarMatriz(suma,matrizA,matrizB,filasA,columnasA);
						verMatriz(suma,filasA,columnasA);

					}else
						printf("\nLa Matriz A (%dx%d) no se puede sumar con la Matriz B (%dx%d)",filasA,columnasA,filasB,columnasB);
					fflush(stdout);
				}else
					printf("Las matrices no tienen datos validos");
				fflush(stdout);

				break;

			case 3:

				if(control==1){ //Preguntamos otra vez si ha pasado antes por el caso 1

					if(columnasA==filasB){ //Condicion para poder multiplicar matrices

						printf("\n\n** Matriz A **\n");
						fflush(stdout);
						verMatriz(matrizA,filasA,columnasA);

						printf("\n\n** Matriz B **\n");
						fflush(stdout);
						verMatriz(matrizB,filasB,columnasB);

						printf("\n** Matriz (A*B) **\n");
						multiplicarMatriz(multiplicacion,matrizA,matrizB,filasA,columnasA,columnasB);
						verMatriz(multiplicacion,filasA,columnasB);

					}else
						printf("\nLa Matriz A (%dx%d) no se puede multiplicar por la Matriz B (%dx%d)",filasA,columnasA,filasB,columnasB);
					fflush(stdout);

				}else
					printf("Las matrices no tienen datos validos");
				fflush(stdout);

				break;
			}

		}else
			printf("Opcion no valida");
		fflush(stdout);

	}while(opcion!=4);
	printf("\nFIN.");

	return 0;}

/*
 * Funcion: pedirDimensiones
 * lee de teclado las filas y columnas de los arrays con los que se va a operar
 * Parametros de entrada:
 * ninguno.
 * Precondiciones:
 * ninguna.
 * Parametros de salida:
 * - filas: entero. Numero de filas que tendran los arrays. Por referencia.
 * - columnas: entero. Numero de columnas que que tendran los arrays.
 * Por referencia.
 * Parametro de salida (especifico para errores):
 * - No existe.
 */

void pedirDimensiones (int *filas, int *columnas){

	printf("Matriz. Numero de Filas(1-5): ");
	fflush(stdout);
	scanf("%d",filas);
	fflush(stdin);

	while(*filas<1 || *filas>5){

		printf("Matriz. Numero de Filas(1-5): ");
		fflush(stdout);
		scanf("%d",filas);
		fflush(stdin);
	}

	printf("Matriz. Numero de Columnas(1-5): ");
	fflush(stdout);
	scanf("%d",columnas);
	fflush(stdin);

	while(*columnas<1 || *columnas>5){

		printf("Matriz. Numero de Columnas(1-5): ");
		fflush(stdout);
		scanf("%d",columnas);
		fflush(stdin);
	}

	printf("\nLas dimensiones de la matriz son %d filas por %d columnas\n",*filas,*columnas);

}

/*
 * Funcion: menu
 * muestra el menu por pantalla y lee de teclado la opcion seleccionada
 * Parametros de entrada:
 * ninguno.
 * Precondiciones:
 * ninguna.
 * Parametros de salida:
 * valor entero. Opcion de menu seleccionada. Valor devuelto.
 * Parametro de salida (especifico para errores):
 * - No existe.
 */

int menu (void){

	int opcion;

	printf("\n\n     1.- Introducir datos en las matrices\n"
			"     2.- Sumar matrices (A+B)\n"
			"     3.- Multiplpicar matrices (A*B)\n"
			"     4.- Salir de la aplicacion");
	fflush(stdout);


	printf("\n\nSeleccione opcion: ");
	fflush(stdout);
	scanf("%d",&opcion);
	fflush(stdin);

	return opcion;
}

/*
 * Funcion: pedirMatriz
 * rellena las filas y columnas del array matriz indicadas en los par�metros
 * filas y columnas con valores enteros leidos del teclado.
 * Parametros de entrada:
 * - filas: entero. Numero de filas que se rellenaran de matriz. Por valor.
 * - columnas: entero. Numero de columnas que se rellenaran de matriz. Por valor.
 * Precondiciones:
 * ninguna.
 * Parametros de salida:
 * - matriz: Array de enteros de dimension MAX_FILAS por MAX_COLUM.
 * Por referencia.
 * Parametro de salida (especifico para errores):
 * - No existe.
 */

void pedirMatriz (int matriz[MAX_FILAS][MAX_COLUM], int filas, int colum){

	int i,j;

	for(i=0; i<filas; i++){
		for (j=0; j<colum; j++){

			printf("Fila %d Columna %d: ",i+1,j+1);
			fflush(stdout);
			scanf("%d",&matriz[i][j]);
			fflush(stdin);
		}
	}
}

/*
 * Funcion: verMatriz
 * muestra por pantalla las filas y columnas del array matriz indicadas
 * en los parametros filas y columnas.
 * Parametros de entrada:
 * - filas: entero. Numero de filas que se mostraran de matriz. Por valor.
 * - columnas: entero. Numero de columnas que se mostraran de matriz.
 * Por valor.
 * - matriz: Array de enteros de dimension MAX_FILAS por MAX_COLUM.
 * Por referencia.
 * Precondiciones:
 * ninguna.
 * Parametros de salida:
 * ninguno.
 * Parametro de salida (especifico para errores):
 * - No existe.
 */

void verMatriz (int matriz[MAX_FILAS][MAX_COLUM], int filas, int colum){

	int i,j;

	for(i=0; i<filas; i++){
		for (j=0; j<colum; j++){

			printf("%5d",matriz[i][j]);
			fflush(stdout);
		}

		printf("\n");
		fflush(stdout);
	}
}

/*
 * Funcion: sumarMatriz
 * suma las filas y columnas de los arrays s1 y s2 indicadas en los
 * parametros filas y columnas y almacena en resultado en el array suma
 * Parametros de entrada:
 * - f1: entero. Numero de filas que se sumaran de s1 y s2. Por valor.
 * - c1: entero. Numero de columnas que se sumaran de s1 y s2. Por valor.
 * - s1: Array de enteros de dimension maxima MAX_FILAS por MAX_COLUM
 * que es uno de los arrays a sumar. Por referencia.
 * - s2: Array de enteros de dimension maxima MAX_FILAS por MAX_COLUM
 * que es el otro de los arrays a sumar. Por referencia.
 * Precondiciones:
 * los arrays s1 y s2 han de tener la misma dimension
 * Parametros de salida:
 * - suma: Array de enteros de dimension maxima MAX_FILAS por MAX_COLUM
 * que contendra la suma de los arrays s1 y s2. Por referencia.
 * Parametro de salida (especifico para errores):
 * - No existe.
 */

void sumarMatriz (int suma[MAX_FILAS][MAX_COLUM],int s1[MAX_FILAS][MAX_COLUM],int s2[MAX_FILAS][MAX_COLUM],int f1,int c1){

	int i,j;

	for(i=0; i<f1; i++){
		for (j=0; j<c1; j++){

			suma[i][j]=s1[i][j]+s2[i][j];
		}
	}
}

/*
 * Funcion: multiplicarMatriz
 * multiplica las filas y columnas del p1 y por las filas y columnas del array
 * p2 indicadas en los parametros f1, c1 y c2 y almacena en resultado en el array
 * prod.
 * Parametros de entrada:
 * - f1: entero. Numero de filas que se multiplicaran de p1. Por valor.
 * - c1: entero. Numero de columnas que se multiplicaran de p1 y numero
 * de filas que se multiplicaran de p2. Por valor.
 * - c2: entero: Numero de columnas que se multiplicaran de p2. Por valor.
 * - p1: Array de enteros de dimension maxima MAX_FILAS por MAX_COLUMNAS
 * que es uno de los arrays a multiplicar. Por referencia.
 * - p2: Array de enteros de dimension maxima MAX_FILAS por MAX_COLUMNAS
 * que es el otro de los arrays a multiplicar. Por referencia.
 * Precondiciones:
 * El numero de columnas del array p1 ha de ser igual al n�mero de filas
 * del array p2.
 * Parametros de salida:
 * - prod: Array de enteros de dimension maxima MAX_FILAS por MAX_COLUMNAS
 * que contendra el producto de los arrays p1 y p2. Por referencia.
 * Parametro de salida (especifico para errores):
 * - No existe.
 */

void multiplicarMatriz (int prod[MAX_FILAS][MAX_COLUM],int p1[MAX_FILAS][MAX_COLUM],int p2[MAX_FILAS][MAX_COLUM],int f1,int c1,int c2){

	int i,j,h;

	for(i=0; i<f1; i++){
		for (j=0; j<c2; j++){

			prod[i][j]=0;

			for(h=0; h<c1; h++){

				prod[i][j]=prod[i][j]+p1[i][h]*p2[h][j];
			}
		}
	}
}



