
import java.awt.*;

// ------------------------------------------------------------------------------------
// 	Sprite del personaje
// ------------------------------------------------------------------------------------

public class PersSprite extends Sprite
{
	// Posicion X e Y inicial del sprite
	public static final int INI_X = 125;
	public static final int INI_Y = 198;

	// Ancho y alto del sprite
	public static final int WIDTH = 25;
	public static final int HEIGHT = 38;

	// Nombre de las imagenes de los frames del sprite
	public static final String FILE_PREFIX = "pers";
	public static final String FILE_EXT = ".gif";

	// Numero de frames del sprite
	public static final int NUM_FRAMES = 6;

	// Secuencia de frames del sprite
	Image [] frames;

	// Frame actual
	int frame;

	public PersSprite() {

		super(WIDTH, HEIGHT);

		setLocation(INI_X, INI_Y);

		// Cargamos la secuencia de frames del sprite

		Toolkit tk = Toolkit.getDefaultToolkit();

		frames = new Image[NUM_FRAMES];

		for(int i=0;i<frames.length;i++)
		{
			frames[i] = tk.getImage(FILE_PREFIX + "0" + (i+1) + FILE_EXT);
		}
	}

	public void stepLeft() {

		// Alterna los frames del sprite andando hacia la izquierda

		if(frame == 1) {
			frame = 2;
		} else {
			frame = 1;
		}

	}

	public void stepRight() {

		// Alterna los frames del sprite andando hacia la derecha

		if(frame == 3) {
			frame = 4;
		} else {
			frame = 3;
		}

	}

	public void stay() {

		// Establece el frame del sprite parado

		frame = 0;

	}

	public void die() {

		// Establece el frame del sprite muriendo

		frame = 5;

	}

	public void render(Graphics g) {

		// Dibuja el frame actual en la posicion del sprite

		g.drawImage(frames[frame], (int)bb.x, (int)bb.y, WIDTH, HEIGHT, null);

	}

}