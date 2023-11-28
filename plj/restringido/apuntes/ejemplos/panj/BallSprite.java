
import java.awt.*;
import java.awt.image.*;

// ------------------------------------------------------------------------------------
// 	Sprite de las bolas
// ------------------------------------------------------------------------------------

public class BallSprite extends Sprite 
{

	// Nombre de las imagenes del sprite
	public final static String FILE_NAME = "bola";
	public final static String FILE_EXT = ".gif";

	// Tamaño máximo de las bolas
	public final static int MAX_SIZE = 3;

	// Imagen del sprite
	Image sprite;

	// Dimensiones reales (en pixels) para cada tamaño
	int [] dim = { 10, 25, 50, 75 };

	// Velocidad x e y del sprite
	public double vx, vy;

	// Descendientes del sprite (cuando la bola se parte en 2)
	public BallSprite desc1, desc2;

	public BallSprite(int tam, String color, double vx) {

		// Establece tamaño, velocidad e imagen del sprite
		setSize(dim[tam], dim[tam]);
		sprite = createSprite(tam, color);
		this.vx = vx;
		this.vy = 0.0;

		// Si no tiene el tamaño minimo crea dos descendientes de tamaño inferior

		if(tam > 0) {
			desc1 = new BallSprite(tam - 1, color, vx);
			desc2 = new BallSprite(tam - 1, color, -vx);
		} else {
			desc1 = desc2 = null;
		}
	}

	public Image createSprite(int tam, String color) {

		// Carga la imagen del sprite para el color adecuado
		
		Toolkit tk = Toolkit.getDefaultToolkit();

		String nombre = FILE_NAME + color + FILE_EXT;
		Image img = tk.getImage(nombre);

		return img;
	}

	public void render(Graphics g) {

		// Dibuja el sprite en su posicion
		
		g.drawImage(sprite, (int)bb.x, (int)bb.y, (int)bb.width, (int)bb.height, null);

	}

}