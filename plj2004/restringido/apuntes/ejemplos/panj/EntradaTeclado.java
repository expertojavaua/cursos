
import java.awt.event.*;

// ------------------------------------------------------------------------------------
// 	Lee la entrada del dispositivo del teclado
// ------------------------------------------------------------------------------------

public class EntradaTeclado extends KeyAdapter
{

	// Codigos de teclas usadas en el juego
	public static int K_SALIR = KeyEvent.VK_ESCAPE;
	public static int K_FUEGO = KeyEvent.VK_SPACE;
	public static int K_IZQ = KeyEvent.VK_LEFT;
	public static int K_DER = KeyEvent.VK_RIGHT;

	// Vector que indica si cada tecla está pulsada o no
	boolean [] teclas;

	public EntradaTeclado() {

		// La longitud del vector será el número de código de tecla máximo

		teclas = new boolean[KeyEvent.KEY_LAST];

		// Todas las teclas están sin pulsar

		for(int i=0;i<teclas.length;i++) {
			teclas[i] = false;
		}

	}

	public boolean estaPulsada(int codigo) {
		// Nos indica si una determinada tecla está pulsada o no
		return teclas[codigo];
	}

	public void keyPressed(KeyEvent e) {
		// Al ocurrir este evento se registra una tecla como pulsada
		teclas[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		// Al ocurrir este evento se libera una tecla de estar pulsada
		teclas[e.getKeyCode()] = false;
	}

}