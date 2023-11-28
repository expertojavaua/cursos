import java.util.Enumeration;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.Vector3f;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.*;

/**
  * Esta clase es un ejemplo de uso de varios canvas y de universos complejos
  */
public class EjemploUnivComplejo extends Applet 
{
	/**
	  * Constructor
	  */
	public EjemploUnivComplejo() 
	{
		// Dos regiones, una por canvas
		setLayout(new GridLayout(1,2));
		
		// Definimos el universo y el locale
		VirtualUniverse miUniverso = new VirtualUniverse();
		Locale miLocale = new Locale(miUniverso);

		// Creamos los dos canvas
		Canvas3D canvasIzq = new Canvas3D(null);
		add(canvasIzq);
		Canvas3D canvasDer = new Canvas3D(null);
		add(canvasDer);

		// Vistas de cada canvas
		View miVistaIzq = construirVista(canvasIzq);
     	canvasIzq.setMonoscopicViewPolicy(View.LEFT_EYE_VIEW);
		View miVistaDer = construirVista(canvasDer);
		canvasDer.setMonoscopicViewPolicy(View.RIGHT_EYE_VIEW);
		constructViewBranch(miVistaIzq, miLocale, false);
		constructViewBranch(miVistaDer, miLocale, true);

		constructContentBranch(miLocale);
	}

	/**
	  * Construye una vista asociada a un canvas
	  */
	private View construirVista(Canvas3D canvas) 
	{
		View miVista = new View();
		miVista.addCanvas3D(canvas);
		PhysicalBody miCuerpo = new PhysicalBody();
		miCuerpo.setLeftEyePosition(new Point3d(-.006,0.0, 0.0));
		miCuerpo.setRightEyePosition(new Point3d(+.006,0.0, 0.0));
		miVista.setPhysicalBody(miCuerpo);

		miVista.setPhysicalEnvironment(new PhysicalEnvironment());
		return(miVista);
	}

	/**
	  * Construye la rama de vista, el puente entre la vista y el universo
	  */
	private void constructViewBranch(View miVista, Locale miLocale, boolean vista) 
	{
		BranchGroup miBranchGroup = new BranchGroup();
		
		// Realizamos una transformacion u otra en funcion de la vista
		
		TransformGroup miTransformGroup = new TransformGroup();
		Transform3D t = new Transform3D();
		if (vista) 
		{
			t.rotY(Math.PI/2.0f);
			t.setTranslation(new Vector3f(4.0f, 0.0f, 0.0f));
		}
		else t.setTranslation(new Vector3f(0.0f, 0.0f, 4.0f));
		miTransformGroup.setTransform(t);
		
		// Establecemos el ViewPlatform
		
		ViewPlatform miViewPlatform = new ViewPlatform();
		miTransformGroup.addChild(miViewPlatform);
		miBranchGroup.addChild(miTransformGroup);
		
		// Añadimos la rama de vista		
		miLocale.addBranchGraph(miBranchGroup);

		miVista.attachViewPlatform(miViewPlatform);
	}
	
	/**
	  * Construye la rama de contenido
	  */
	private void constructContentBranch(Locale miLocale) 
	{
    	BranchGroup objRoot = new BranchGroup();
    	TransformGroup objTrans = new TransformGroup ();
    	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		// Interaccion con el raton

    	BoundingSphere bounds =
	    	new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		objTrans.addChild(new ColorCube(0.6));
		objRoot.addChild(objTrans);
		MouseRotate miMouseRotate = new MouseRotate();
		miMouseRotate.setTransformGroup(objTrans);
		miMouseRotate.setSchedulingBounds(bounds);
		objRoot.addChild(miMouseRotate);
		
		// Añadimos la rama de contenido
		
		miLocale.addBranchGraph(objRoot);
	}
	
	/**
	  * Main
	  */
	public static void main(String args[]) 
	{
		new MainFrame(new EjemploUnivComplejo(),256,128);
	}
}
