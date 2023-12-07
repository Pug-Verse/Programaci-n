#include <stdio.h>
#include <math.h>
#include <stdlib.h> //Libreria para usar la funcion valor absoluto abs();

#define PI 3.141592653588 // Constante PI
#define TERMINOS pow(10,-6) // Términos de la serie 10^–6


void seno (const double ang, double *res);
double calcula_term_N (const double t_ant, const double x, const int n);
void coseno (const double ang, double *res);
double factorial(int x);

int main (void){

		double grados,angulo,ang_normalizado;
		double resultado;

		printf("Introduzca angulo(grados): ");
		fflush(stdout);
		scanf("%lf",&grados);
		fflush(stdin);

		angulo=(grados*PI)/180;//Conversión de grados a radianes

		printf("En angulo en radianes es: %lf",angulo);
		fflush(stdout);

		//Normalizar angulo de [–PI,PI]
		if(angulo>-PI && angulo<=PI){
			ang_normalizado=angulo;
		}

		//Precondición para llamar a la función: ángulo entre [–PI,PI]
		if(ang_normalizado>=(-PI) && ang_normalizado<=PI){

			seno(ang_normalizado,&resultado);
		}

		printf("\nEl seno matematico es: %lf",sin(ang_normalizado));
		fflush(stdout);

		//Precondición para llamar a la función: ángulo entre [–PI,PI]
		if(ang_normalizado>=(-PI) && ang_normalizado<=PI){

			coseno(ang_normalizado,&resultado);
		}

		printf("\nEl coseno matematico es: %lf",cos(ang_normalizado));
		fflush(stdout);


		return 0;
	}

/*
* Función: seno
* Calcula el valor del seno de ángulo medido en radianes usando la función
* calcula_term_N para calcular cada término de la serie de McLaurin.
* Parámetros de entrada:
* ang: real pasado por valor. Contiene el valor del ángulo medido en radianes.
* Precondiciones:
* El ángulo debe estar comprendido en el intervalo [–π,π].
* Parámetros de salida:
* - res: real pasado por referencia. Seno de ang.
 */

void seno (const double ang, double *res){

		int n;

		printf("\n\n¿Con cuantos terminos desea aproximar la serie del seno?: ");
		fflush(stdout);
		scanf("%d",&n);
		fflush(stdin);

		//Siempre cuando el valor absoluto de los términos sea mayor que 10^–6
		while(abs(n)<=TERMINOS){

			printf("\n¿Con cuantos terminos desea aproximar la serie del seno?: ");
			fflush(stdout);
			scanf("%d",&n);
			fflush(stdin);
		}

		*res=calcula_term_N(ang,ang,n);

		printf("El seno de McLaurin es: %lf",*res);
		fflush(stdout);
	}

/*
* Función: calcula_term_N
* Calcula un término de la serie de McLaurin para la función seno.
* Parámetros de entrada:
* t_ant: real pasado por valor. Término n-1 de la serie.
* x: real pasado por valor. Ángulo para el que se calcula el seno.
* n: entero pasado por valor. Qué término de la serie hay que calcular.
* Precondiciones:
* n tiene que ser mayor que 0.
* t_ant debe contener el valor del término n-1 de la serie.
* Rarámetros de salida:
* valor del término n-ésimo. Valor real devuelto por la función.
 */

double calcula_term_N (const double t_ant, const double x, const int n){


		int i;
		double T_enesimo=0;

		for(i=0;i<n;i++){

			T_enesimo=T_enesimo + pow(-1.0, i) / factorial((2*i) + 1) * pow(x, ((2*i) + 1));
		}

		return T_enesimo;
	}

/*
* Función: coseno
* Calcula el valor del coseno de un ángulo medido en radianes usando
* la función seno, a partir de la fórmula sen2 x + cons2 x = 1.
* Parámetros de entrada:
* ang: real pasado por valor. Contiene el valor del ángulo medido en radianes.
* Precondiciones:
* El ángulo debe estar comprendido en el intervalo [–π,π].
* Parámetros de salida:
* - res: real pasado por referencia. Coseno de ang.
*/

void coseno (const double ang, double *res){


		int n;

		printf("\n\n¿Con cuantos terminos desea aproximar la serie del coseno?: ");
		fflush(stdout);
		scanf("%d",&n);
		fflush(stdin);

		//Siempre cuando el valor absoluto de los términos sea mayor que 10^–6
		while(abs(n)<=TERMINOS){

			printf("¿Con cuantos terminos desea aproximar la serie del coseno?: ");
			fflush(stdout);
			scanf("%d",&n);
			fflush(stdin);
		}

		double seno_Alcuadrado=pow(calcula_term_N (ang,ang,n),2); //Elevamos el seno al cuadrado

		if(ang>(-PI/2) && ang<=(PI/2)){

			*res=sqrt(1-seno_Alcuadrado);
		}
		else if(ang>(PI/2) && ang<=(3*PI/2)){

			*res=(-1)*(sqrt(1-seno_Alcuadrado));
		}

		printf("El coseno de McLaurin es: %lf",*res);
		fflush(stdout);
	}

double factorial(int x){

		double factorial=1;

		while(x>0){

			factorial=factorial*x;
			x--;
		}

		return factorial;
	}
