
import java.awt.*;

// ------------------------------------------------------------------------------------
// 	Sprite del rayo
// ------------------------------------------------------------------------------------

public class RayoSprite extends Sprite
{
	// Ancho y alto del sprite
	public static final int WIDTH = 12;
	public static final int HEIGHT = 250;

	// Nombre de los ficheros de las imagenes
	public static final String FILE_PREFIX = "rayo";
	public static final String FILE_EXT = ".gif";

	// Numero de frames del sprite
	public static final int NUM_FRAMES = 3;

	// Secuencia de frames del sprite
	Image [] frames;

	// Frame actual
	int frame;

	public RayoSprite() {

		super(WIDTH, HEIGHT);

		// Cargamos del disco las imágenes de cada frame

		Toolkit tk = Toolkit.getDefaultToolkit();

		frames = new Image[NUM_FRAMES];

		for(int i=0;i<frames.length;i++)
		{
			frames[i] = tk.getImage(FILE_PREFIX + "0" + (i+1) + FILE_EXT);
		}

		frame = 0;
	}

	public void nextFrame() {

		// Pasamos al siguiente frame

		frame = (frame + 1) % NUM_FRAMES;
	}

	public void render(Graphics g) {

		// Dibujamos el frame actual en la posición del sprite

		g.drawImage(frames[frame], (int)bb.x, (int)bb.y, WIDTH, HEIGHT, null);

	}

}