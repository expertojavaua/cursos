
import java.awt.*;

// ------------------------------------------------------------------------------------
// 	Sprites de los límites invisibles de la pantalla (solo para colisiones)
// ------------------------------------------------------------------------------------

public class Limite extends Sprite {

	public Limite(int x, int y, int width, int height) {

		super(width, height);

		setLocation(x,y);

	}

	public void render(Graphics g) {

	}

}