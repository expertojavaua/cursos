import java.awt.Frame;
import java.awt.event.*;
import java.awt.image.renderable.*;
import java.awt.image.*;
import javax.media.jai.*;
import javax.media.jai.widget.ScrollingImagePanel;

/**
  * Este ejemplo crea dos imagenes constantes y las suma
  */
public class EjemploJAI2 extends Frame
{

	public static void main(String[] args)
	{
		Byte[] valores1 = new Byte[1];
		Byte[] valores2 = new Byte[1];
		valores1[0] = new Byte((byte)10);
		valores2[0] = new Byte((byte)20);

		// Crear imagenes constantes

		ParameterBlock param1 = new ParameterBlock();
		param1.add(new Float(100.0f));		// Anchura
		param1.add(new Float(150.0f));		// Altura
		param1.add(valores1);				// Constantes para cada banda
		RenderedOp im0 = JAI.create("constant", param1);
		
		ParameterBlock param2 = new ParameterBlock();
		param2.add(new Float(100.0f));		// Anchura
		param2.add(new Float(150.0f));		// Altura
		param2.add(valores2);				// Constantes para cada banda
		RenderedOp im1 = JAI.create("constant", param2);

		// Sumar las imágenes
		RenderedOp im2 = JAI.create("add", im0, im1);

		/* Visualiza la imagen */
		ScrollingImagePanel panel = new ScrollingImagePanel(im2, im2.getWidth(), im2.getHeight());
		Frame window = new Frame("JAI Sample Program");
		window.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		window.add(panel);
		window.pack();
		window.show();
	}
}
