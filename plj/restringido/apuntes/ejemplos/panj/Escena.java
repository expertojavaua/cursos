
import java.awt.Graphics;

// ------------------------------------------------------------------------------------
// 	Clase general para las escenas (escena de titulo, escena de juego, etc)
// ------------------------------------------------------------------------------------

public interface Escena
{

	public int update(GameContext gc);

	public void render(Graphics g);

}