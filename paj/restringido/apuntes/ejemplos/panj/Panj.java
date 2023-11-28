
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

// ------------------------------------------------------------------------------------
// 	Clase principal del juego
// ------------------------------------------------------------------------------------

public class Panj extends JFrame {

	// Ancho y alto originales de los gráficos

	public final static int WIDTH = 320;
	public final static int HEIGHT = 240;

	// Estados del juego

	public final static int E_TITULO = 0;
	public final static int E_JUEGO = 1;

	// Milisegundos que transcurren entre dos instantes / frames consecutivos

	public final static int CICLO = 50;

	// Contexto del juego (acceso a teclado, etc ...)

	GameContext gc;

	// Escena y estado actual (pantalla de titulo, juego, etc)

	Escena escena;
	int estado;

	// Imagen de doble buffer donde volcamos los gráficos
	Image backbuffer = null;

	// Ancho y alto de la pantalla
	int width = WIDTH;
	int height = HEIGHT;

	public Panj() {

		// Crea la escena de titulo inicial. Partimos de dicho estado

		escena = new Titulo();
		estado = E_TITULO;

		// Inicializa contexto del juego (control por teclado)

		gc = new GameContext();

		gc.et = new EntradaTeclado();
		addKeyListener(gc.et);

		// Comienza el juego

		Thread t = new HiloJuego();
		t.start();

	}

	public void paint(Graphics g) {

		// Creamos el backbuffer si no está creado

		if(backbuffer == null) {
			backbuffer = createImage(WIDTH,HEIGHT);
		}

		// Dibujamos los gráficos del juego en el backbuffer

		Graphics off_g = backbuffer.getGraphics();

		escena.render(off_g);

		// Volcamos el backbuffer a pantalla

		g.drawImage(backbuffer, 0, 0, width, height, this);
	}

	public void update(Graphics g) {
		// Solo pintamos encima para evitar efecto parpadeo

		paint(g);
	}

	class HiloJuego extends Thread {

		public void run() {

			// Transicion entre estados

			int trans=0;

			while(true) {

				long t_ini, t_dif;

				t_ini = System.currentTimeMillis();

				// Actualiza la escena

				trans = escena.update(gc);

				switch(estado) {
					case E_TITULO:

						switch(trans) {
							case Titulo.T_CONTINUA:
								break;
							case Titulo.T_JUEGO:
								escena = new Juego();
								estado = E_JUEGO;
								break;
							case Titulo.T_SALIR:
								System.exit(0);
						}

						break;

					case E_JUEGO:
						switch(trans) {
							case Juego.T_TERMINA:
								escena = new Titulo();
								estado = E_TITULO;
								break;
							case Juego.T_SALIR:
								System.exit(0);
						}

						break;
				}

				// Repinta los gráficos

				repaint();

				t_dif = System.currentTimeMillis() - t_ini;

				// Duerme hasta el siguiente frame

				if(t_dif < CICLO) {
					try {
						this.sleep(CICLO - t_dif);
					} catch(InterruptedException e) { }
				}
			}

		}

	}

	public void setScreenSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public static void main(String [] args) {

		Panj panj = new Panj();

		// Elimina decoracion de la ventana (marco)

		panj.setUndecorated(true);

		// Obtiene dispositivo gráfico

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		// Muestra la ventana de seleccion de modo grafico

		DisplayModeSelect sel = new DisplayModeSelect(gd, panj);
		sel.setSize(400,400);
		sel.show();

		// Cambia el modo gráfico

		DisplayMode modo = sel.getSelection();
		if(modo == null)
			System.exit(0);
		panj.setScreenSize(modo.getWidth(), modo.getHeight());

		gd.setFullScreenWindow(panj);
		gd.setDisplayMode(modo);

		panj.show();
	}

}