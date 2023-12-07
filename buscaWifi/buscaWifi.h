
#ifndef BUSCAWIFI_H_
#define BUSCAWIFI_H_

#include <stdio.h>
#include <stdlib.h>
#include<string.h>

#define SIZE_ESSID 15
#define MAC_SIZE 6
#define MAX_REDES 20

/* Estructura para almacenar la informacion de las redes escaneadas   */

	typedef char tESSID [SIZE_ESSID];
	typedef unsigned char tMAC [MAC_SIZE];

	typedef struct
	{
		tESSID ESSID;            /* ESSID */
		tMAC mac;				 /* MAC del punto */
		unsigned int calidad;    /* calidad del punto */
		unsigned int cifrado;    /* 0: No, 1: Si */

	}tInfo;

	typedef struct
	{
	    tInfo informacion;
		int preferencia;             /* Campo preferencia (0: No, 1: Si ) */

	}tDatosRed;  /* Estructura basica que guarda la informacion de cada punto de acceso */


	typedef struct
	{
		tDatosRed datos;
		int ocupado;             /* Campo preferencia (0: No, 1: Si ) */

	}tElem; /* Estructura para el manejo del array */


	typedef tElem tRedes [MAX_REDES];

	/*********************************************************/
	/*Prototipos de las funciones utilizadas en el programa  */
	/*********************************************************/

	void cargaRedesActivas(const tInfo redAct[MAX_REDES], tRedes redes);
	void copiaRed (tInfo* redDst, const tInfo redOrg);
	void copiaMac(tMAC macDst,const tMAC macOrg);
	int menu(void);
	void inicializaRedes(tRedes redes);
	void imprimeRedes(const tRedes redes);
	void aniadePreferencia(tRedes redes);
	void muestraMac(const tMAC mac);
	void muestraRed(const tDatosRed red);
	void muestraMejorRed(const tRedes redes);
	int posicionMejor (const tRedes redes);
	int hayRedes(const tRedes redes);
	void borraRed(tRedes redes);

#endif /* BUSCAWIFI_H_ */
