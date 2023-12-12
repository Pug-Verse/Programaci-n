

public class Amplificador extends java.lang.Object implements ITransferencia{

	
	private double k;
	
	public Amplificador(double k)
	{
		if(k==0){


			throw new IllegalArgumentException("La k del amplificador no puede ser 0");

		}

		this.k=k;
	}

	public double getSalida(double entrada)
	{
		double salida;

		salida=k*entrada;

		return salida;

	}

}