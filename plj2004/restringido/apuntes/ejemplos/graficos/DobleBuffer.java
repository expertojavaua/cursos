
import java.awt.*;
import java.applet.*;

public class DobleBuffer extends Applet implements Runnable {

	public final static String TEXTO = "Esto ya es otra cosa. El doble buffer soluciona el efecto flicker.";

	Font f;
	FontMetrics fm;

	int x, y;

	Thread t;

	Image backbuffer = null;

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
		if(backbuffer == null) {
			backbuffer = createImage(getSize().width, getSize().height);
		}

		Graphics off_g = backbuffer.getGraphics();

		off_g.clearRect(0, 0, getSize().width, getSize().height);

		off_g.setFont(f);
		off_g.drawString(TEXTO, x, y);

		off_g.dispose();

		g.drawImage(backbuffer, 0, 0, this);
	}

}