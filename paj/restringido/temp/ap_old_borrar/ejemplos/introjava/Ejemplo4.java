
/**
  * Este ejemplo devuelve los numeros primos encontrados hasta un
  * cierto valor
  */
public class Ejemplo4
{	  
	/**
	  * Obtiene si un número es primo o no
	  */
	public static boolean esPrimo (int valor) 
	{ 
		int i = 2;
		while (i < valor)
		{
			if (valor % i == 0)
				return false;
			i++;
		}
		return true; 
	} 

	/**
	  * Main
	  */
	public static void main(String[] args) 
	{ 
		System.out.println ("Numeros primos hasta el " + args[0] + ":"); 
		for (int i = 1; i < Integer.parseInt(args[0]); i++)
			if (esPrimo(i))
				System.out.print (" " + i);
		System.out.println ("\nFinalizado");
	} 
}
