import java.util.*;

public class MiClase
{
   public int a;
   Vector v;

   public MiClase()
   {
      v = new Vector();
   }

   public void imprimirA()
   {
      System.out.println ("a");
   }

   public void insertarVector(String cadena)
   {
      v.addElement(cadena);
   }
}
