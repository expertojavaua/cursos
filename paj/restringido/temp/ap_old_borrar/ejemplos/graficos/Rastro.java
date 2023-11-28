
import java.awt.*;
import java.applet.*;

public class Rastro extends Applet implements Runnable {

	public final static String TEXTO = "Aquí no se puede leer nada";

	Font f;
	FontMetrics fm;

	int x, y;

	Thread t;

	public void init() {

		f = new Font("Arial", Font.PLAIN, 20);
		fm = getFontMetrics(f);

		x = getSize().width;
		y = 40;

	}

	public void start() {
		t = new Thread(this);
		t.start();
	}

	public void stop() {
		t = null;
	}

	public void run() {
		while(t == Thread.currentThread()) {
			x--;

			if(x < -fm.stringWidth(TEXTO)) {
				x = getSize().width;
			}

			repaint();

			try {
				Thread.currentThread().sleep(10);
			} catch(InterruptedException e) {}
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {

		g.setFont(f);
		g.drawString(TEXTO, x, y);

	}

}