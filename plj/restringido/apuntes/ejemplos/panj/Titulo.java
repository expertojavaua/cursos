
import java.awt.*;
import java.awt.image.*;

// ------------------------------------------------------------------------------------
// 	Escena de titulo
// ------------------------------------------------------------------------------------

public class Titulo implements Escena
{
	// Posibles transiciones de estado
	public static final int T_CONTINUA = 0;
	public static final int T_JUEGO = 1;
	public static final int T_SALIR = 2;

	// Numero de frames de cada ciclo de animacion y frame de apagado del texto
	public static final int NUM_FRAMES = 10;
	public static final int FRAME_OFF = 7;

	// Centro y radio de la circunferencia en la que gira el titulo
	public static final int TIT_X = 50;
	public static final int TIT_Y = 25;
	public static final int TIT_RX = 13;
	public static final int TIT_RY = 3;

	// Posición del texto
	public static final int TEX_X = 60;
	public static final int TEX_Y = 150;
	public static final int TEX_W = 210;
	public static final int TEX_H = 10;

	// Nombres de los ficheros de imagenes que vamos a utilizar
	public static final String IMG_TITULO = "titulo.gif";
	public static final String IMG_FONDO = "fondo.jpg";

	// Frame actual de la animacion
	int frame;

	// Imagenes
	Image titulo;
	Image fondo;
	Image texto;

	// Mensaje de texto
	String str_texto = "PULSA START PARA COMENZAR";

	public Titulo() {
		frame = 0;
		initGraphics();
	}

	private void initGraphics() {

		// Cargamos las imagenes necesarias de los ficheros

		Toolkit tk = Toolkit.getDefaultToolkit();

		titulo = tk.getImage(IMG_TITULO);

		fondo = tk.getImage(IMG_FONDO);

		// Creamos una imagen en memoria con el texto con antialiasing
		// El antialiasing es un efecto costoso, por eso lo hacemos solo una vez

		texto = new BufferedImage(TEX_W, TEX_H, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = (Graphics2D)texto.getGraphics();

		g2.setColor(new Color(0,0,0,0));
		g2.fillRect(0,0,TEX_W,TEX_H);

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2.setFont(new Font("Arial", Font.BOLD, 12));
		g2.setColor(Color.yellow);
		g2.drawString(str_texto, 0, TEX_H);
	}

	public int update(GameContext gc) {

		// Actualizamos el frame actual

		frame = (frame + 1) % NUM_FRAMES;

		// Comprobamos a que estado debemos pasar

		if(gc.et.estaPulsada(EntradaTeclado.K_SALIR)) {
			return T_SALIR;
		} else if(gc.et.estaPulsada(EntradaTeclado.K_FUEGO)) {
			return T_JUEGO;
		}

		return T_CONTINUA;
	}

	public void render(Graphics g) {

		// Dibuja los gráficos en el contexto gráfico

		Graphics2D g2 = (Graphics2D)g;

		// Imagen de fondo
		g2.drawImage(fondo, 0, 0, null);

		// Titulo que da vueltas
		g2.drawImage(titulo, 
			(int)(TIT_X + TIT_RX*Math.cos(2.0*(double)frame*Math.PI/NUM_FRAMES)),
			(int)(TIT_Y + TIT_RY*Math.sin(2.0*(double)frame*Math.PI/NUM_FRAMES)), 
			null);

		// Texto intermitente
		if(frame<FRAME_OFF) 
			g2.drawImage(texto, TEX_X, TEX_Y, null);
	}

}