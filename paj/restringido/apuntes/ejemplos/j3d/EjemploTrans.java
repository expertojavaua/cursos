
import java.applet.Applet;
import java.awt.BorderLayout;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
  * Este ejemplo muestra un cubo de colores en un canvas
  */
public class EjemploTrans extends Applet 
{
	/**
	  * Crea la rama del cono para el grafo de escena
	  */
	public BranchGroup crearGrafoEscena() 
	{
		// Creamos la rama y la apariencia
		BranchGroup bg = new BranchGroup();
        Appearance apariencia = new Appearance();

		// Creamos el cubo
        ColorCube cubo = new ColorCube(0.25f);

        // Es necesario poner una cierta apariencia. 
        // Sin luz no veríamos al objeto.
        cubo.setAppearance(apariencia);

		// Transformacion
		Transform3D t3D = new Transform3D();
		
		t3D.rotX(Math.PI / 4.0);		// Rotar 45º en X

		Transform3D t3D2 = new Transform3D();
		t3D2.rotY(-Math.PI / 4.0); 		// Rotar 45º en Y
		t3D.mul(t3D2); 

		t3D.setTranslation(new Vector3f(0.5f, 0.0f, 0.0f));

		TransformGroup objTrans = new TransformGroup(t3D);

		// Colgamos el cubo de la rama de transformacion
        objTrans.addChild(cubo);
        
        // Colgamos la rama de transformacion de la rama principal
        bg.addChild(objTrans);

        bg.compile();

        return bg;
    }

	/**
	  * Constructor. Inicializa el universo y le añade la rama
	  * con el cono
	  */	  
    public EjemploTrans() 
    {
    	setSize(256,256);
    	setLayout(new BorderLayout());
		Canvas3D canvas3d = new Canvas3D(null);
		add("Center", canvas3d);

        // Crea una escena y la une al universo virtual
        BranchGroup escena = crearGrafoEscena();
        SimpleUniverse simple = new SimpleUniverse(canvas3d);

        // Este comando mueve el ViewPlatform ligeramente hacia
        // atrás para que el objeto pueda ser visualizado
        simple.getViewingPlatform().setNominalViewingTransform();

        simple.addBranchGraph(escena);        
    }

    public static void main(String[] args) 
    {
        new MainFrame(new EjemploTrans(), 256, 256);
    }   
}
