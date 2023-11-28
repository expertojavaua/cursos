import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  * Esta clase muestra un ejemplo de como trabajar a pantalla completa.
  * Muestra una animacion donde un circulo rojo se va moviendo por la pantalla
  */
public class EjPantallaCompleta extends JFrame implements Runnable
{
	int ANCHO = 320;						// Anchura del area grafica
	int ALTO = 240;							// Altura del area grafica
	int CICLO = 50;							// Milisegundos entre dos frames de animacion consecutivos
	Image backbuffer = null;				// Imagen para hacer el doble buffer al dibujar cada frame de la animacion
	int xIni = 10;							// La animacion consiste en mover un circulo desde xIni = 10 hasta 200
	Thread t = new Thread(this);			// Hilo para hacer las animaciones
	
	/**
	  * Constructor: establece el dispositivo, elige el modo grafico e inicia la animacion
	  */
	public EjPantallaCompleta()
	{
		// Quitamos la barra superior de la ventana
		this.setUndecorated(true);
		
		// Tomamos el dispositivo grafico
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		
		// Mostramos dialogo para elegir un modo grafico
		DlgModos dlg = new DlgModos(gd, this);
		dlg.show();
		
		// Obtenemos la pantalla completa, mostrando previamente si se puede tener o no:
		System.out.println ("Pantalla completa soportada: " + (gd.isFullScreenSupported()?"SI":"NO"));
		try
		{ 
			Thread.currentThread().sleep(5000);
		} catch (Exception e) {}
		gd.setFullScreenWindow(this);
		gd.setDisplayMode(dlg.modoSeleccionado);
		
		// Comenzamos la animacion
		t.start();
	}
	
	/**
	  * Actualiza el contexto gráfico del frame: en nuestro caso, solo llama a paint
	  */
	public void update(Graphics g)
	{
		paint(g);
	}
	
	/**
	  * Redibuja el contexto grafico del frame: dibuja la imagen en el backbuffer, 
	  * y luego la vuelca sobre el frame
	  */
	public void paint(Graphics g)
	{
		if(backbuffer == null)
			backbuffer = createImage(ANCHO, ALTO);

		// Dibujamos los gráficos en el backbuffer

		Graphics off_g = backbuffer.getGraphics();
		dibuja(off_g);

		// Volcamos el backbuffer a pantalla

		g.drawImage(backbuffer, 0, 0, ANCHO, ALTO, this);
		g.dispose();
	}
	
	/**
	  * Dibuja cada frame de animacion: primero limpia el area de dibujo, 
	  * y luego va dibujando un circulo rojo en distintas coordenadas X
	  */
	public void dibuja(Graphics g)
	{
		g.clearRect(0, 0, ANCHO, ALTO);
		g.setColor(Color.red);
		if (xIni < 200)
			xIni++;
		g.fillOval(xIni, 5, 100, 100);
		g.dispose();
	}
	
	/**
	  * Metodo del hilo: bucle que va realizando la animacion
	  */
	public void run()
	{
		while (xIni < 200)
		{
			long t1, t2;
			t1 = System.currentTimeMillis();
			repaint();
			t2 = System.currentTimeMillis();
			if (t2 - t1 < CICLO)
				try
				{
					Thread.currentThread().sleep(CICLO - (t2 - t1));
				} catch (Exception e) {}
		}
		System.exit(0);
	}
	
	/**
	  * Metodo principal
	  */
	public static void main(String[] args)
	{
		EjPantallaCompleta epc = new EjPantallaCompleta();
		epc.show();
	}
}

/**
  * Esta clase muestra un cuadro de dialogo para elegir uno 
  * de los modos graficos disponibles
  */
class DlgModos extends JDialog
{
	DisplayMode [] modes;				// Modos graficos disponibles
	DisplayMode modoSeleccionado;		// Modo grafico finalmente seleccionado
	String [] smodes;					// Cadenas descriptivas de cada modo grafico
	JList lstModos;						// Lista de modos graficos
	JDialog dlg = this;					// Puntero a este dialogo
	
	public DlgModos(GraphicsDevice gd, JFrame padre)
	{
		super(padre, true);
		setSize (300, 300);
		
		// Obtenemos los modos graficos, los pasamos a cadena, y los metemos en la lista
		modes = gd.getDisplayModes();
		smodes = new String[modes.length];
		for(int i=0;i<modes.length;i++) 
			smodes[i] = modes[i].getWidth() + "x" + modes[i].getHeight() + ", " + modes[i].getBitDepth() + "bpp, " + modes[i].getRefreshRate() + " Hz";
		lstModos = new JList(smodes);		
		JScrollPane scrll = new JScrollPane(lstModos);
		
		// Boton para confirmar la eleccion de un modo grafico
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (lstModos.getSelectedIndex() != -1)
				{
					System.out.println ("Cambiando a modo " + smodes[lstModos.getSelectedIndex()]);
					modoSeleccionado = modes[lstModos.getSelectedIndex()];
					dlg.hide();
				}
			}
		});
		
		// Colocamos los componentes
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrll, BorderLayout.CENTER);
		getContentPane().add(btnAceptar, BorderLayout.SOUTH);
	}
}