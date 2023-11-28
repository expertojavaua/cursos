import java.awt.Frame;
import java.awt.event.*;
import java.awt.image.renderable.ParameterBlock;
import java.io.IOException;
import javax.media.jai.Interpolation;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import com.sun.media.jai.codec.FileSeekableStream;
import javax.media.jai.widget.ScrollingImagePanel;


/**
  * Este ejemplo toma una imagen como entrada y la escala al 
  * doble de su tamaño
  */
public class EjemploJAI1
{
	public static void main(String[] args)
	{
		/* Lee la imagen del fichero */
		FileSeekableStream stream = null;
		try
		{
			stream = new FileSeekableStream("earth.jpg");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		/* Crea un operador para tomar la imagen de entrada */
		RenderedOp img1 = JAI.create("stream", stream);

		/* Operador de escala (interpolador bilineal) */
		Interpolation interp = Interpolation.getInstance(Interpolation.INTERP_BILINEAR);

		/* Guarda la imagen de entrada y los parámetros necesarios en un ParameterBlock para enviarlos al operador de escalado */
		ParameterBlock params = new ParameterBlock();
		params.addSource(img1);
		params.add(2.0F);         // Factor de escala X
		params.add(2.0F);         // Factor de escala Y
		params.add(0.0F);         // Traslación en X
		params.add(0.0F);         // Traslación en Y
		params.add(interp);       // Método de interpolación

		/* Crea un operador para escalar la imagen */
		RenderedOp img2 = JAI.create("scale", params);

		/* Visualiza la imagen */
		ScrollingImagePanel panel = new ScrollingImagePanel(img2, img2.getWidth(), img2.getHeight());
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
