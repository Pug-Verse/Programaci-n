/*****************************************************************
*  buscaWifi.c	Aplicacion para gestion de redes wifi     	     *
*  Revision:	1.0											     *
*															     *
*  Notas: 													     *
*															     *
******************************************************************/

#include "buscaWifi.h"
#include <ctype.h>

/**********************************************************
*                 Programa principal.                     *
***********************************************************/
int main( void )
{
		/* Almacena los valores de los PA activos en un momento concreto */

		const  tInfo redesDetectadas[MAX_REDES] = {
				{"WiFi-UPM",{0x5F, 0xF3, 0xB1, 0x9D, 0x50, 0x2C},  95, 1},
				{"WiFi-UPM",{0x3F, 0xF6, 0xB1, 0x9D, 0x50, 0x1C},  50, 1},
				{"Vodafone",{0x2F, 0xE6, 0xA1, 0x4D, 0x30, 0x2C},  80, 0},
				{"Orange-706",{0x2A, 0xF6, 0xA1, 0x4A, 0x30, 0x2C},  67, 0},
				{"UPM-INVITADO",{0x61, 0x99, 0x7E, 0x9F, 0xEC, 0xFC}, 74, 0},
				{"UPM-INVITADO",{0x62, 0x89, 0x6E, 0x8F, 0xEC, 0x1C}, 50, 0},
				{"Movistar_1",{0x2A, 0xB6, 0xA8, 0x5D, 0x30, 0x2C},  70, 0},
				{"EMT-E",{0x2E, 0xB7, 0xC8, 0x5A, 0x30, 0x2B},  50, 0},
				{"UPM-ALUMNOS",{0xB5, 0x97, 0x88, 0xA8, 0x99, 0x28}, 80, 0},
				{"UPM-ALUMNOS",{0xA5, 0xA7, 0x88, 0xA9, 0x97, 0x25}, 10, 0},
				{"UPM-ALUMNOS",{0xB4, 0x97, 0x87, 0xA8, 0x19, 0x20}, 40, 0},
				{"Orange-Cafet",{0x9A, 0xF2, 0xA2, 0x4B, 0x30, 0x1C},  87, 0},
				{"eduroam",{0x7F, 0x48, 0xFD, 0xC8, 0xC9, 0x73}, 16, 1},
				{"eduroam",{0x6F, 0x45, 0xFD, 0xC7, 0xC9, 0x72}, 97, 1},
				{"UPM-BIBLIO",{0x4E, 0x38, 0x49, 0xB5, 0x87, 0x9F}, 6, 0},
				{"UPM-BIBLIO",{0x3E, 0x28, 0x49, 0xA5, 0x87, 0x2F}, 76, 0},
				{"Wifi-ETSIST",{0x3D, 0x18, 0x29, 0xA5, 0x77, 0x1F}, 26, 1},
				{"Wifi-ETSIST",{0x2D, 0x12, 0x29, 0xA6, 0x67, 0x5F}, 56, 1},
				{"Wifi-DTE",{0x63, 0xEA, 0x9B, 0x65, 0xD3, 0x7C}, 80, 1},
				{"Wifi-DTE",{0x64, 0xEA, 0x9A, 0x6B, 0xD4, 0x8C}, 70, 1}
		};


		tRedes redes;
		int opcion;

		printf("\n** REDES CARGADAS **");
		printf("\nIntroduzca Preferencia <1-5> para las redes detectadas:\n");

		inicializaRedes(redes);
		cargaRedesActivas(redesDetectadas,redes);

		if(hayRedes(redes)){ //Si las redes estan cargadas

			do{
				opcion=menu();
        		
				if (opcion>=1 && opcion<=4){ //Condicion para que sea una opcion valida

					switch(opcion){

					case 1:

						imprimeRedes(redes);
						break;

					case 2:

						muestraMejorRed(redes);
						break;

					case 3:

						borraRed(redes);
						break;
					}

				}else
					printf("Opcion no valida");
				    fflush(stdout);

			}while(opcion!=4);

		}else

			printf("No hay redes disponibles");
		    fflush(stdout);

		return 0;
	}

/*********************************************************
	  Funcion: cargaRedesActivas
			Pasa los datos de las redes desde una estructura
			de tipo tInfo a una estructura de tipo tRedes.
		Parametros de entrada:
			redAct:
	 	Precondiciones:
	 	Parametros de salida:
	  		redes:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void cargaRedesActivas(const tInfo redAct[MAX_REDES], tRedes redes){

		int i=0,j=0;

		while(i<MAX_REDES){

			copiaRed (&redes[j].datos.informacion, redAct[i]);
			redes[j].ocupado=1;
			i++;

			if(!strcmp(redes[j].datos.informacion.ESSID,redAct[i].ESSID)){
				if(redes[j].datos.informacion.calidad<redAct[i].calidad){

					copiaRed (&redes[j].datos.informacion, redAct[i]);
					redes[j].ocupado=1;
				}

			}else{

				j++;
			}
		}

		for(i=0;i<MAX_REDES;i++){


			if(redes[i].ocupado==1){

				printf("%s -> ",redes[i].datos.informacion.ESSID);
				fflush(stdout);
				aniadePreferencia(&redes[i]);

				while(redes[i].datos.preferencia<1 || redes[i].datos.preferencia>5){

					printf("%s -> ",redes[i].datos.informacion.ESSID);
					fflush(stdout);
					aniadePreferencia(&redes[i]);
				}
			}
		}
	}

/*********************************************************
	  Funcion: copiaRed
			Copia un elemento de un tipo tInfo a otro otro
			tipo tInfo.
		Parametros de entrada:
			redOrg:
	 	Precondiciones:
	 	Parametros de salida:
	  		redDst:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void copiaRed (tInfo* redDst, const tInfo redOrg){

	*redDst = redOrg;
}

/*********************************************************
	  Funcion: copiaMac
			Copia un elmento de tipo tMAC a otro de tipo tMAC
		Parametros de entrada:
			macOrg:
	 	Precondiciones:
	 	Parametros de salida:
	  		macDst:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void copiaMac(tMAC macDst,const tMAC macOrg){

		int i;

		for(i=0;i<MAC_SIZE;i++){

			macDst[i]=macOrg[i];
		}
	}

/*********************************************************
	  Funcion: presentarMenu
			Presenta menu de opciones y solicita opcion
		Parametros de entrada:
			macOrg:
	 	Precondiciones:
	 	Parametros de salida:
	  		valor entero:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
int menu(void){

		int opcion;

		printf("\n\nMenu de opciones");
		printf("\n\n     1.- Mostrar informacion de las redes\n"
				"     2.- Mostrar la red con mejor calidad de senal\n"
				"     3.- Borrar la informacion de una red\n"
				"     4.- Salir");
		fflush(stdout);


		printf("\n\nSeleccione opcion: ");
		fflush(stdout);
		scanf("%d",&opcion);
		fflush(stdin);

		return opcion;
	}

/*********************************************************
	  Funcion: inicializaRedes
			Inicializa un array de tipo tRedes a vacio
		Parametros de entrada:
	 	Precondiciones:
	 	Parametros de salida:
	  		redes:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void inicializaRedes(tRedes redes){

	int i;

	for(i=0;i<MAX_REDES;i++){

		redes[i].ocupado=0;
	}
}

/*********************************************************
	  Funcion: imprimeRedes
			Muestra por pantalla la informacion de las
			redes seguras y el numero de redes inseguras.
		Parametros de entrada:
		  	redes:
	 	Precondiciones:
	 	Parametros de salida:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void imprimeRedes(const tRedes redes){

	int i,insegura=0;

	printf("\n\n** INFORMACIÓN DE LAS REDES **");

	for(i=0;i<MAX_REDES;i++){

		if(redes[i].ocupado==1){
			muestraRed(redes[i].datos);

			if(!redes[i].datos.informacion.cifrado){ //Las redes que no esten cifradas inseguras
				insegura++;
			}
		}
	}

	printf("\n\nRedes inseguras-> %d",insegura);
}

/*********************************************************
	  Funcion: aniadePreferencia
		Añade el campo "Preferencia"
		Parametros de entrada:
		  	redes:
	 	Precondiciones:
	 	Parametros de salida:
	 		redes:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void aniadePreferencia(tRedes redes){

		scanf("%d",&redes->datos.preferencia);
		fflush(stdout);
	}

/*********************************************************
	  Funcion: muestraMac
		Visualiza por pantalla una direccion MAC con el
		formato adecuado
		Parametros de entrada:
		  	mac:
	 	Precondiciones:
	 	Parametros de salida:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void muestraMac(const tMAC mac){

		int i=0;

		printf("\nMAC-> ");
		fflush(stdout);

		for(i=0;i<MAC_SIZE;i++){

			if(i==5){

				printf("%x",mac[i]);
				fflush(stdout);

			}else{

				printf("%x:",mac[i]);
				fflush(stdout);
			}
		}
	}

/*********************************************************
	  Funcion: muestraRed
		Visualiza por pantalla los datos de una red
		Parametros de entrada:
		  	red:
	 	Precondiciones:
	 	Parametros de salida:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void muestraRed(const tDatosRed red){

		printf("\n\nESSID-> %s",red.informacion.ESSID);

		muestraMac(red.informacion.mac);

		printf("\nCALIDAD-> %d",red.informacion.calidad);

		if(red.informacion.cifrado){

			printf("\nCIFRADO-> Si");

		}else{

			printf("\nCIFRADO-> No");
		}

		printf("\nPREFERENCIA-> %d",red.preferencia);
	}

/*********************************************************
	  Funcion: muestraMejorRed
		Visualiza por pantalla los datos de la red que ofrece
		mejor calidad de se�al
		Parametros de entrada:
		  	redes:
	 	Precondiciones:
	 	Parametros de salida:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void muestraMejorRed(const tRedes redes){

		int pos_calidad=posicionMejor(redes);
		int i=0,encontrada=0;

		while(i<MAX_REDES && !encontrada){
			if(redes[i].ocupado==1){

				printf("\n** RED CON LA MEJOR CALIDAD **");
				fflush(stdout);

				muestraRed(redes[pos_calidad].datos);
				encontrada=1;
				i++;
			}
		}
	}

/*********************************************************
	  Funcion: posicionMejor
			Devuelve el indice del array de redes donde esta
			el elemento que almacena la red de mejor calidad
			de se�al.
		Parametros de entrada:
		  	redes:
	 	Precondiciones:
	 	Parametros de salida:
	 		Valor entero:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
int posicionMejor (const tRedes redes){

		int i=0,mejor_calidad=0,posicion=0;

		for(i=0;i<MAX_REDES;i++){
			if(redes[i].datos.informacion.calidad>mejor_calidad && redes[i].ocupado==1){

				mejor_calidad=redes[i].datos.informacion.calidad;
				posicion=i;
			}
		}

		return posicion;
	}


/*********************************************************
	  Funcion: hayRedes
			Devuelve un valor indicativo de si existe
			alguna red en el array de redes.
		Parametros de entrada:
		  	redes:
	 	Precondiciones:
	 	Parametros de salida:
	 		Valor entero:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
int hayRedes(const tRedes redes){

		int i=0,encontrada=0;

		while(i<MAX_REDES && !encontrada){
			if(redes[i].ocupado==1){

				encontrada=1;

			}else{

				i++;
			}
		}

		return encontrada;
	}


/*********************************************************
	  Funcion: borraRed
		Pide al usuario el ESSID de la red a borrar y si
		existe, muestra la red a borrar y pide confirmacion
		de borrado. Si finalmente se confirma, la red se
		elimina del array de redes, en caso contrario no se
		eliminara.
		Parametros de entrada:
		  	redes:
	 	Precondiciones:
	 	Parametros de salida:
	 		redes:
	 	Parametro de salida (especifico para errores):
 **********************************************************/
void borraRed(tRedes redes){

		int i=0,eof=0;
		char confirmacion='a';

		tESSID nombre;

		printf("\n** BORRAR UNA RED **\n\n");

		printf("Introduzca el ESSID de la red a borrar: ");
		fflush(stdout);
		fgets(nombre,SIZE_ESSID,stdin);

		nombre[strlen (nombre) - 1]= '\0';

		while(i<MAX_REDES && !eof){

			if(!strcmp(nombre,redes[i].datos.informacion.ESSID)){

				printf("\nLa red a borrar es:");
				fflush(stdout);

				muestraRed(redes[i].datos);

				printf("\n\nSeguro (S/N): ");
				fflush(stdout);
				scanf("%c",&confirmacion);
				fflush(stdin);

				while(confirmacion!='S' && confirmacion!='s' && confirmacion!='N' && confirmacion!='n'){

					printf("Seguro (S/N): ");
					fflush(stdout);
					scanf("%c",&confirmacion);
					fflush(stdin);
				}

				if(confirmacion=='S' || confirmacion=='s'){

					redes[i].ocupado=0;

					printf("La red %s ha sido eliminada",nombre);
					fflush(stdout);
				}

				if(confirmacion=='N' || confirmacion=='n'){

					printf("La red %s no sera eliminada",nombre);
					fflush(stdout);
				}

				eof=1;
			}

			i++;
		}
	}

