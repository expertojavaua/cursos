
import java.applet.Applet;
import java.awt.BorderLayout;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
  * Este ejemplo muestra un cubo en un canvas
  */
public class EjemploUnivSimple extends Applet 
{
	/**
	  * Crea la rama del cono para el grafo de escena
	  */
	public BranchGroup crearGrafoEscena() 
	{
		// Creamos la rama y la apariencia
		BranchGroup bg = new BranchGroup();
        Appearance apariencia = new Appearance();
        
        // Creamos la esfera
        Sphere esfera = new Sphere(0.5f);
        esfera.setAppearance(apariencia);

		// Colgamos la esfera de la rama
        bg.addChild(esfera);    	
        
        bg.compile();

        return bg;
    }

	/**
	  * Constructor. Inicializa el universo y le añade la rama
	  * con el cono
	  */ 
    public EjemploUnivSimple() 
    {
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
        new MainFrame(new EjemploUnivSimple(), 256, 256);
    }
}
