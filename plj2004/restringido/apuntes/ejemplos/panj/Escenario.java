
import java.awt.*;
import java.awt.image.*;

// ------------------------------------------------------------------------------------
// 	Escenario de las fases
// ------------------------------------------------------------------------------------

public class Escenario {

	Image img;

	public Escenario(Image img) {

		this.img = img;

	}

	public void render(Graphics g) {

		// Dibuja la imagen de fondo

		g.drawImage(img, 0, 0, null);

	}
}