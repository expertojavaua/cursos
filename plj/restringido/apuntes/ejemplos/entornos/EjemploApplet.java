import java.awt.*;
import java.applet.*;
import java.awt.event.*;

/**
  * Este es un ejemplo de applet 
  */
public class EjemploApplet extends Applet
{
	/**
	  * Panel
	  */
	Panel panel;

	/**
	  * Posiciones del panel
	  */
	int[][] posiciones = {{0, 0}, {200, 0}};
	
	/**
	  * Posicion actual del panel
	  */
	int posActual = 0;
	
	/**
	  * Inicializa el applet
	  */
	public void init()
	{		
		setLayout(null);		
		
		panel = new Panel();
		panel.setBounds (posiciones[posActual][0], posiciones[posActual][1], 100, 100);
		Button btn = new Button ("Pulsame");
		panel.add(btn);
		panel.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				posActual = (posActual + 1) % posiciones.length;
				panel.setBounds (posiciones[posActual][0], posiciones[posActual][1], 100, 100);				
			}
		});
		
		add(panel);	
	}
	
	/**
	  * Pinta el applet
	  */
	public void paint (Graphics g)
	{
	}
}