
/**
  * Este ejemplo resuelve el teorema de Pitágoras:
  * hipotenusa = raiz (cateto1 * cateto1 + cateto2 * cateto2)
  */
public class Ejemplo3 
{
	// Primer Cateto
	public static final int CATETO1 = 20;

	// Segundo Cateto
	public static final int CATETO2 = 50;
	  
	/**
	  * Obtiene la hipotenusa de dos catetos que se pasan como parametro
	  */
	public static double hipotenusa(int cateto1, int cateto2) 
	{ 
		return Math.sqrt(Math.pow(cateto1, 2) + Math.pow(cateto2, 2)); 
	} 

	/**
	  * Main
	  */
	public static void main(String[] args) 
	{ 
		System.out.println ("La hipotenusa de los catetos indicados es:"); 
		double h = hipotenusa(CATETO1, CATETO2);
		System.out.println ("h = " + h);
	} 
}
