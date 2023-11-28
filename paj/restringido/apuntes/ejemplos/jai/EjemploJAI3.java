import java.awt.Frame;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.image.renderable.*;
import java.io.IOException;
import javax.media.jai.*;
import com.sun.media.jai.codec.FileSeekableStream;
import javax.media.jai.widget.ScrollingImagePanel;


/**
  * Este ejemplo toma una imagen como entrada, invierte sus
  * valores y le suma una constante
  */
public class EjemploJAI3
{
	public static void main(String[] args)
	{
		double[]valores = new double[1];
		valores[0] = 10.0;
		
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

		/* Convierte la imagen en renderizable */
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(img1);
		RenderableImage ren = JAI.createRenderable("renderable", pb);

		/* Invierte la imagen */
		ParameterBlock pb1 = new ParameterBlock();
		pb1.addSource(ren);
		RenderableOp Op1 = JAI.createRenderable("invert", pb1);

		/* Suma la constante */
		ParameterBlock pb2 = new ParameterBlock();
		pb2.addSource(Op1);        
		pb2.add(valores);           
		RenderableOp Op2 = JAI.createRenderable("addconst", pb2);

		/* Obtener una imagen renderizada */
		RenderedImage rndImg1 = Op2.createScaledRendering(200, 200, Op2.getRenderingHints());

		/* Visualiza la imagen */
		ScrollingImagePanel panel = new ScrollingImagePanel(rndImg1, rndImg1.getWidth(), rndImg1.getHeight());
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
