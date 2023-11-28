/**
  * Ejemplo que suma dos numeros: un entero y un real
  */
public class Ejemplo2
{
	int n1;		// Primer numero (el entero)
	float n2;	// Segundo numero (el real)
	
	/**
	  * Constructor
	  */
	public Ejemplo2(int n1, float n2)
	{
		this.n1 = n1;
		this.n2 = n2;
	}
	
	/**
	  * Suma dos numeros y devuelve el resultado (real)
	  */
	public float suma()
	{
		return (n1 + n2);
	}
	
	/**
	  * Main
	  */
	public static void main(String[] args)
	{
		if (args.length < 2)
		{
			System.out.println ("Uso: java Ejemplo2 <n1> <n2>");
			System.exit(-1);
		}

		// Tomamos los dos parametros
		int n1 = Integer.parseInt(args[0]);
		float n2 = Float.parseFloat(args[1]);

		// Creamos un objeto Ejemplo2 y le pedimos la suma de los valores
		Ejemplo2 e = new Ejemplo2(n1, n2);
		System.out.println ("Resultado: " + e.suma());
	}
}
