#include<stdio.h>
#include<string.h>

int main() {

	char frase[60],rep[100],temporal[100];
	char pal;
	int longitud,i,j,inicial,acertado=0,temp=0,oportunidades=5;
	int repetido=0,gano=0;

	printf("\tJuego del Ahorcado\n\n");
	fflush(stdout);
	printf("Introduzca la palabra a adivinar: ");
	fflush(stdout);
	gets(frase);

	longitud = 0;
	inicial = 0;
	j = 0;

	rep[0] = ' ';
	rep[1] = '\0';


	do {
		temp=0;

		if(inicial == 0) {
			for(i=0;i<strlen(frase);i++) {
				if(frase[i] == ' ') {
					temporal[i] = ' ';
					longitud++;
				}
				else {
					temporal[i] = '_';
					longitud++;
				}
			}
		}

		inicial = 1;

		temporal[longitud] = '\0';

		for(i=0;i<strlen(rep);i++) {
			if(rep[i] == pal) {
				repetido = 1;
				break;
			}
			else {
				repetido = 0;
			}
		}

		if(repetido == 0) {
			for(i=0;i<strlen(frase);i++) {
				if(frase[i] == pal) {
					temporal[i] = pal;
					acertado++;
					temp=1;
				}
			}
		}

		if(repetido == 0) {
			if(temp == 0) {
				oportunidades--;
			}
		}

		else {
			printf("Ya se ha introducido este caracter");
			fflush(stdout);
			printf("\n\n");
			fflush(stdout);
		}

		printf("\n");
		fflush(stdout);

		for(i=0;i<strlen(temporal);i++) {
			printf(" %c ",temporal[i]);
			fflush(stdout);
		}

		printf("\n");
		fflush(stdout);

		if(strcmp(frase,temporal) == 0) {
			gano = 1;
			break;
		}

		printf("\n");
		fflush(stdout);
		printf("Letras Acertadas: %d",acertado);
		fflush(stdout);
		printf("\nOportunidades Restantes: %d",oportunidades);
		fflush(stdout);

		rep[j] = pal;
		j++;

		if (oportunidades==0)
		{
			break;
		}

		printf("\nIntroduzca una letra: ");
		fflush(stdout);
		scanf("\n%c",&pal);
		fflush(stdin);

	}while(oportunidades != 0);


	if(gano) {
		printf("\n\n");
		fflush(stdout);
		printf("Enhorabuena, has ganado");
		fflush(stdout);
	}
	else {
		printf("\n\n");
		fflush(stdout);
		printf("Has perdido.");
		fflush(stdout);
	}

	printf("\n\n");
	fflush(stdout);

	return 0;

}
