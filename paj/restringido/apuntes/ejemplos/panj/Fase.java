
import java.awt.*;
import java.awt.geom.*;
import java.applet.*;
import java.net.*;
import java.util.Vector;

// ------------------------------------------------------------------------------------
// 	Fase del juego (Clase que implementa el comportamiento del juego)
// ------------------------------------------------------------------------------------

public class Fase
{
	// Posibles transiciones de estados
	public static final int T_COMPLETADA = 0;
	public static final int T_MUERTO = 1;
	public static final int T_SALIR = 2;
	public static final int T_CONTINUA = 3;

	// Posibles estados dentro de la fase
	public static final int E_JUGANDO = 0;
	public static final int E_INICIO = 1;
	public static final int E_MURIENDO = 2;

	// Fuerza de gravedad de las bolas
	public static final double GRAVEDAD = 0.5;

	// Sprites del juego
	Vector bolas;
	PersSprite pers;
	RayoSprite rayo;
	Escenario escenario;
	Limite [] lim_v;
	Limite [] lim_h;

	// Datos y estado de la fase
	int estado;
	int frame;
	String nombre_fase;
	boolean dibujar_nombre;
	boolean hay_rayo;

	// Clips de audio
	AudioClip snd_pang;
	AudioClip snd_grito;
	AudioClip music;

	public Fase(DatosFase df) {

		// Construye las bolas necesarias para la fase

		bolas = new Vector();
		BallSprite bola = null;

		for(int i=0;i<df.n_bolas;i++) {

			bola = new BallSprite(df.bola_tam[i], df.bola_color[i], df.bola_vx[i]);
			bola.setLocation(df.bola_x[i], df.bola_y[i]);
			bolas.addElement(bola);

		}

		// Carga el escenario de fondo

		escenario = new Escenario(Toolkit.getDefaultToolkit().getImage(df.img_background));

		nombre_fase = df.nombre;

		// Construye los límites invisibles

		lim_v = new Limite[2];
		lim_v[0] = new Limite(-100, 0, 100, 240);
		lim_v[1] = new Limite( 320, 0, 100, 240);

		lim_h = new Limite[1];
		lim_h[0] = new Limite(0, 220, 320, 20);

		// Construye los sprites del personaje y del rayo

		pers = new PersSprite();

		rayo = new RayoSprite();

		// Carga los clips de audio
	
		try {
			snd_pang = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/pop.wav"));
			snd_grito = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/grito.wav"));
			music = Applet.newAudioClip(new URL("file:" + System.getProperty("user.dir") + "/" + df.music));
		} catch(MalformedURLException e) {
			System.out.println("Error al cargar clip de audio");
			System.exit(-1);
		}

		music.loop();

		// Fija el estado inicial del juego

		estado = E_INICIO;
		frame = 0;
		hay_rayo = false;
	}

	public int update(GameContext gc) {

		// Estado iniciando la fase (parpadea el nombre de la fase)

		if(estado == E_INICIO) {
			if(frame < 50) {

				dibujar_nombre = (frame % 10) < 7;
				frame++;
				return T_CONTINUA;

			} else {
				estado = E_JUGANDO;
			}
		}

		// Estado muriendo el personaje (el personaje cae muerto)

		if(estado == E_MURIENDO) {

			if(frame < 50) {
				Rectangle2D.Double pers_bb = pers.getBoundingBox();
				
				pers_bb.x += 4;
				pers_bb.y = PersSprite.INI_Y + 0.2 * ( Math.pow(frame,2) - 50 * frame );

				frame++;

				return T_CONTINUA;

			} else {
				destroy();
				return T_MUERTO;
			}
		}

		// Entrada del teclado (movimiento del personaje y disparos)

		Rectangle2D.Double pers_bb = pers.getBoundingBox();

		if (gc.et.estaPulsada(EntradaTeclado.K_DER) && pers_bb.x < Panj.WIDTH - PersSprite.WIDTH) {
			pers.stepRight();
			pers_bb.x+=4;
		} else if (gc.et.estaPulsada(EntradaTeclado.K_IZQ) && pers_bb.x > 0) {
			pers.stepLeft();
			pers_bb.x-=4;
		} else {
			pers.stay();
		}

		if(hay_rayo) {
			Rectangle2D.Double rayo_bb = rayo.getBoundingBox();
			rayo_bb.y -= 8;
			rayo.nextFrame();
			if(rayo_bb.y < -25) {
				hay_rayo = false;
			}
		} else {
			if(gc.et.estaPulsada(EntradaTeclado.K_FUEGO)) {
				rayo.setLocation(pers_bb.x + 6, 170);
				hay_rayo = true;
				pers.stay();
			}
		}

		// Movimiento y colisiones de las bolas

		Rectangle2D.Double bb = null;
		BallSprite bola = null;

		for(int i=0;i<bolas.size();i++) {
			bola = (BallSprite) bolas.elementAt(i);

			bb = bola.getBoundingBox();
			bola.vy += GRAVEDAD;

			bb.y += bola.vy;
			bb.x += bola.vx;

			// Colision con muros verticales

			for(int j=0;j<lim_v.length;j++) {
				if(bola.collide(lim_v[j]) != 0) {
					bola.vx = -bola.vx;
				}
			}

			// Colision con el suelo

			for(int j=0;j<lim_h.length;j++) {
				if(bola.collide(lim_h[j]) != 0) {
					bola.vy = -bola.vy-GRAVEDAD;
				}
			}

			// Colision con el personaje

			if(bola.collide(pers) != 0) {
				estado = E_MURIENDO;
				frame = 0;
				snd_grito.play();
				pers.die();
			}

			// Colision con el rayo

			if(hay_rayo) {
				if(bola.collide(rayo) != 0) {
					if(bola.desc1 != null && bola.desc2 != null) {
						Rectangle2D.Double bb_desc = bola.desc1.getBoundingBox();
						double n_x = bb.x + (bb.width / 2) - (bb_desc.width / 2);
						double n_y = bb.y + (bb.height / 2) - (bb_desc.height / 2);

						bola.desc1.setLocation(n_x, n_y);
						bola.desc2.setLocation(n_x, n_y);

						bola.desc1.vy = bola.vy;
						bola.desc2.vy = bola.vy;

						bolas.addElement(bola.desc1);
						bolas.addElement(bola.desc2);
					}

					bolas.removeElement(bola);
					hay_rayo = false;
					i--;

					snd_pang.play();

					if(bolas.size() == 0) {
						destroy();
						return T_COMPLETADA;
					}
				}
			}
		}

		return T_CONTINUA;

	}

	public void destroy() {

		// Detiene la musica

		music.stop();
	}

	public void render(Graphics g) {

		// Dibuja el escenario

		escenario.render(g);

		// Dibuja el rayo si lo hubiese

		if(hay_rayo) {
			Rectangle2D.Double rayo_bb = rayo.getBoundingBox();

			g.setClip((int)rayo_bb.x, (int)rayo_bb.y, (int)rayo_bb.width, 235 - (int)rayo_bb.y);
			rayo.render(g);
			g.setClip(null);
		}

		// Dibuja el personaje

		pers.render(g);

		// Dibuja las bolas

		for(int i=0;i<bolas.size();i++) {
			((BallSprite)bolas.elementAt(i)).render(g);
		}

		// Dibuja el nombre de la fase durante el inicio

		if(dibujar_nombre) {
			g.setColor(Color.yellow);
			g.drawString(nombre_fase, 100,100);
		}

	}
}