
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

	// Vector que indica si cada tecla est� pulsada o no
	boolean [] teclas;

	public EntradaTeclado() {

		// La longitud del vector ser� el n�mero de c�digo de tecla m�ximo

		teclas = new boolean[KeyEvent.KEY_LAST];

		// Todas las teclas est�n sin pulsar

		for(int i=0;i<teclas.length;i++) {
			teclas[i] = false;
		}

	}

	public boolean estaPulsada(int codigo) {
		// Nos indica si una determinada tecla est� pulsada o no
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