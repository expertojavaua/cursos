
import java.io.*;
import java.awt.*;
import java.awt.image.*;

// ------------------------------------------------------------------------------------
// 	Escena de juego
// ------------------------------------------------------------------------------------

public class Juego implements Escena
{
	// Numero de vidas total
	public static final int NUM_VIDAS = 3;

	// Posibles transiciones de estado
	public static final int T_CONTINUA = 0;
	public static final int T_TERMINA = 1;
	public static final int T_SALIR = 2;

	// Fichero con los datos de las fases del juego
	private static final String FICHERO_FASES = "fases.dat";

	// Puntuacion y vidas restantes
	int puntuacion = 0;
	int vidas = NUM_VIDAS;

	// Fase actual, número de la fase y datos de todas las fases
	Fase fase;
	int num_fase;
	DatosFase [] fases;

	// Cara a mostrar como numero de vidas
	Image cara;

	public Juego() {

		// Lee los datos de todas las fases

		try {
			leeDatosFase();
		} catch(FileNotFoundException e1) {
			System.out.println("No se encuentra el fichero " + FICHERO_FASES);
			System.exit(-1);
		} catch(IOException e2) {
			System.out.println("Error leyendo el fichero " + FICHERO_FASES);
			System.exit(-1);
		}

		initGraphics();

		// Comienza con la primera fase

		num_fase = 0;
		fase = new Fase(fases[num_fase]);

	}

	private void initGraphics() {

		// Lee la imagen de la cara del fichero

		Toolkit tk = Toolkit.getDefaultToolkit();
		cara = tk.getImage("cara.gif");
	}

	private void come(StreamTokenizer st, int token) throws IOException {

		// Obtiene un elemento del tipo indicado de la entrada
		// Devuelve error si el elemento encontrado es de otro tipo

		if(st.nextToken() != token) {
			System.out.println("Fichero de datos incorrecto");
			System.exit(-1);
		}
	}

	private void leeDatosFase() throws IOException, FileNotFoundException {

		// Leemos los datos de la fase del fichero de entrada

		FileReader in = new FileReader(FICHERO_FASES);
		StreamTokenizer st = new StreamTokenizer(in);
		st.quoteChar('"');

		come(st, StreamTokenizer.TT_NUMBER);
		int n_fases = (int)st.nval;
		fases = new DatosFase[n_fases];

		for(int i=0;i<n_fases;i++) {

			fases[i] = new DatosFase();

			come(st, '"');
			fases[i].nombre = st.sval;

			come(st, StreamTokenizer.TT_NUMBER);
			fases[i].n_bolas = (int)st.nval;
			fases[i].bola_x = new int[fases[i].n_bolas];
			fases[i].bola_y = new int[fases[i].n_bolas];
			fases[i].bola_tam = new int[fases[i].n_bolas];
			fases[i].bola_vx = new double[fases[i].n_bolas];
			fases[i].bola_color = new String[fases[i].n_bolas];

			for(int j=0;j<fases[i].n_bolas;j++) {

				come(st, StreamTokenizer.TT_NUMBER);
				fases[i].bola_x[j] = (int)st.nval;
				come(st, StreamTokenizer.TT_NUMBER);
				fases[i].bola_y[j] = (int)st.nval;
				come(st, StreamTokenizer.TT_NUMBER);
				fases[i].bola_tam[j] = (int)st.nval;
				come(st, StreamTokenizer.TT_NUMBER);
				fases[i].bola_vx[j] = st.nval;
				come(st, StreamTokenizer.TT_WORD);
				fases[i].bola_color[j] = st.sval;
			}

			come(st, StreamTokenizer.TT_WORD);
			fases[i].img_background = st.sval;
			come(st, StreamTokenizer.TT_WORD);
			fases[i].music = st.sval;

		}
	}

	public int update(GameContext gc) {

		// Sale del juego al pulsar ESC

		if (gc.et.estaPulsada(EntradaTeclado.K_SALIR)) {
			fase.destroy();
			return T_SALIR;
		}

		// Controla las transiciones entre los estados de la fase

		int trans = fase.update(gc);

		// El personaje a muerto, resta una vida o termina el juego

		if(trans == fase.T_MUERTO) {
			vidas--;
			if(vidas<0) {
				return T_TERMINA;
			} else {
				fase = new Fase(fases[num_fase]);
			}
		}

		// La fase se ha completado, pasa a la siguiente

		if(trans == fase.T_COMPLETADA) {
			num_fase++;

			if(num_fase == fases.length) {
				return T_TERMINA;
			} else {
				fase = new Fase(fases[num_fase]);
			}
		}

		// Se ha pulsado ESC, sale del juego

		if(trans == fase.T_SALIR) {
			return T_SALIR;
		}

		return T_CONTINUA;
	}

	public void render(Graphics g) {

		// Dibuja los graficos de la fase

		fase.render(g);

		// Dibuja el numero de vidas (caras)

		for(int i=0;i<vidas;i++) {
			g.drawImage(cara, i*15, 0, 15, 15, null);
		}

	}

}