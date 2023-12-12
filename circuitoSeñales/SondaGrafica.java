

import java.awt.Color;
import simplegui.SimpleGUI;

/**
 * Sonda grafica. Dibuja las muestras de una se�al en una ventana grafica.
 * 
 * La muestra actual se representa mediante un punto rojo distintivo. Cuando se llega
 * al final de la ventana, se desplaza la senal hacia la izquierda (descartandose la
 * parte desplazada) y se continua dibujando nuevas muestras.
 * 
 * El area de representacion de la senal inicialmente tiene un rango vertical
 * entre -1 y +1, pero se va ajustando si el rango de la senal se hace demasiado
 * grande o demasiado pequeno.
 *
 */
public class SondaGrafica {
	
	private SimpleGUI sg;
	// Valor base para calculo de dimensiones (lo suyo es que coincida con un numero entero de ciclos de los generadores)
	private final static int XBASE = 360;
	// Fraccion de la senal que se avanzara cada vez que se llegue al extremo derecho de la pantalla. Se avanzara un 1/F de la pantalla.
	private final static int FRACCION_AVANCE = 3;
	// Usado para poner un titulo diferente a cada ventana de sonda.
	private static int proximoIdSonda = 1;
	// Valor de t actual (eje de abscisas)
	private double tActual = 0;
	// Valores de t de los extremos izquierdo y derecho visibles del eje de abscisas.
	private double tmin, tmax;
	// Valores inferior y superior del eje de ordenadas.
	private double ymin, ymax;
	// Guarda una pantalla de muestras.
	private double[] muestras;
	// Posicion base en la tabla de muestras guardadas (apunta al inicio de las muestras validas)
	int posBaseMuestras = 0;
	// Indice de la ultima muestra valida en la tabla de muestras guardadas (respecto a la posicion base)
	int posMuestraActual = 0;
	// Margenes (en pixeles)
	int margenIzquierdo = 60, margenDerecho = 10, margenInferior = 10, margenSuperior = 10;
	// Dimensiones del area grafica para la senal (en pixeles)
	private int anchuraGrafica, alturaGrafica;
	
	/**
	 * Crea una sonda grafica.
	 * @param nombre Nombre de la sonda (se muestra en el titulo de la ventana)
	 */
	public SondaGrafica(String nombre) {
		// Dimensiones: 2 ciclos de generador de ancho, 4:3 relacion de aspecto
		sg = new SimpleGUI(2 * XBASE, (int) (2 * XBASE / 4.0 * 3.0), false);
		sg.setTitle("Sonda gr�fica " + proximoIdSonda++ + ": " + nombre);
		ymin = -1.1;
		ymax = 1.1;
		tmin = 0;
		tmax = 4* XBASE ;
		dibujarEjes();
	}
	
	/**
	 * Proporciona a la sonda una muestra adicional de la senal. La sonda lo representa
	 * graficamente.
	 * @param muestra Valor de la muestra.
	 */
	public void addMuestra(double muestra) {
		if (muestras != null) {
			// Borra el punto rojo de la muestra anterior y lo redibuja en negro.
			sg.eraseSingleDrawable("punto");
			dibujarPunto(tActual, muestras[(posBaseMuestras + posMuestraActual) % muestras.length], 1, Color.BLACK, null);
			// Avanza la posicion en la tabla de muestras.
			posMuestraActual++;
			// Si llega al final de la pantalla, desplaza el eje de abscisas
			// y redimensiona el de ordenadas (si es necesario)
			if (posMuestraActual == muestras.length) {
				// Avanza la posicion base en la tabla de muestras
				posBaseMuestras = (posBaseMuestras + muestras.length / FRACCION_AVANCE) % muestras.length;
				posMuestraActual = muestras.length - muestras.length / FRACCION_AVANCE;
				redimensionarEjes();
				sg.eraseAllDrawables();
				dibujarEjes();
				double incrementoX = (tmax - tmin) / FRACCION_AVANCE;
				tmin += incrementoX;
				tmax += incrementoX;
				double t1 = tmin;
				for (int i = 0; i < muestras.length - muestras.length / FRACCION_AVANCE; i++) {
					dibujarPunto(t1, muestras[(posBaseMuestras + i) % muestras.length], 1, Color.BLACK, null);
					t1++;
				}
			}
		}
		else
			// Es la primera muestra que recibe la sonda.
			muestras = new double[(int) tmax];			
		// Guarda la muestra y la dibuja con un punto rojo grande
		muestras[(posBaseMuestras + posMuestraActual) % muestras.length] = muestra;
		dibujarPunto(tActual, muestra, 4, Color.RED, "punto");
		tActual++;
		// Introduce un pequeno retardo
		try { Thread.sleep(2); } catch (InterruptedException e) {}
	}

	/**
	 * Dibuja un punto en el area de la senal.
	 * @param t Valor del eje de abscisas
	 * @param y Valor del eje de ordenadas
	 * @param radio Radio del punto (en pixeles)
	 * @param color Color del punto.
	 * @param nombre Nombre del drawable o null si no se quiere dar nombre.
	 */
	private void dibujarPunto(double t, double y, double radio, Color color, String nombre) {
		sg.drawDot(margenIzquierdo + (t - tmin) / (tmax - tmin) * anchuraGrafica, margenSuperior + (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica, radio, color, 1, nombre);
	}
	
	/**
	 * Dibuja los ejes. En realidad solo dibuja el eje de ordenadas, ya que
	 * el de abscisas engorrina la pantalla y no vale para mucho.
	 */
	private void dibujarEjes() {
		double y, separacion;

		// Calcula las dimensiones del area grafica para la senal
		anchuraGrafica = sg.getWidth() - margenIzquierdo - margenDerecho;
		alturaGrafica = sg.getHeight() - margenInferior - margenSuperior;
		// Dibuja el eje de ordenadas
		sg.drawLine(margenIzquierdo, 0, margenIzquierdo, sg.getHeight());
		// Pone marcas en el eje de ordenadas
		y = ymin;
		separacion = (ymax - ymin) / 10;
		while (y <= ymax) {
			sg.drawLine(margenIzquierdo - 5, (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica + margenSuperior, margenIzquierdo, (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica + margenSuperior);
			String valor = String.format("%.2f", y);
			sg.drawText(valor, 0, (1 - (y - ymin) / (ymax - ymin)) * alturaGrafica + margenSuperior);
			y += separacion;
		}
	}
	
	/**
	 * Comprueba si es necesario redimensionar los ejes y, en caso afirmativo,
	 * calcula los nuevos valores extremos. En realidad solo redimensiona
	 * el eje de ordenadas, ya que el de abscisas siempre tiene el mismo tamano.
	 */
	private void redimensionarEjes() {
		// Calcula los valores minimo y maximo del eje de ordenadas para las muestras guardadas 
		double mmin, mmax;
		mmin = mmax = muestras[posBaseMuestras];
		for (int i = 0; i < muestras.length - muestras.length / FRACCION_AVANCE; i++) {
			if (muestras[(posBaseMuestras + i) % muestras.length] < mmin)
				mmin = muestras[(posBaseMuestras + i) % muestras.length];
			if (muestras[(posBaseMuestras + i) % muestras.length] > mmax)
				mmax = muestras[(posBaseMuestras + i) % muestras.length];
		}
		// Mira si hay que redimensionar el eje de ordenadas
		if ((mmax - mmin) / (ymax - ymin) < 0.75 || (mmax - mmin) / (ymax - ymin) > 1) {
			// Para cada extremo, calcula el valor mas cercano (por abajo para ymin y por arriba para ymax)
			// que es un numero redondo (entendiendose por "redondo" que es multiplo de una potencia de 10
			// de orden cercano al tamano del eje)
			double orden, valorSignificativo;
			final double cercaniaOrdenRedondeo = 2;
			orden = Math.floor(Math.log10(Math.abs(mmax - mmin))) - cercaniaOrdenRedondeo;
			valorSignificativo = Math.floor(mmin / Math.pow(10, orden));
			ymin = valorSignificativo * Math.pow(10, orden);
			valorSignificativo = Math.ceil(mmax / Math.pow(10, orden));
			ymax = valorSignificativo * Math.pow(10, orden);
			// Si el cero esta mas o menos cerca de la mitad del rango del eje, lo pone exactamente en el centro.
			if (Math.signum(ymax) != Math.signum(ymin) && Math.abs(Math.abs(ymax/ymin) - 1) <= 0.25) {
				double max = Math.max(Math.abs(ymax), Math.abs(ymin));
				ymax = max;
				ymin = -max;
			}
		}
	}

}
