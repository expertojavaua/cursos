import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
  * Este ejemplo muestra algunas caracteristicas adicionales de Swing
  * como son el uso de iconos, y de timers (para un segundero)
  */
public class EjemploSwing2 extends JFrame
{
	/**
	  * Etiqueta con el contador
	  */
	JLabel lblCont;
	 
	/**
	  * Constructor
	  */
	public EjemploSwing2()
	{
		setSize(300, 200);
		getContentPane().setLayout(new GridLayout(1, 2));
		
		// Etiqueta con icono
		JLabel lblEtiq = new JLabel("Contador:", new ImageIcon("clock.gif"), SwingConstants.CENTER);

		// Etiqueta para los segundos
		lblCont = new JLabel("0");

		// Timer: cada segundo incrementa el contador
		Timer t = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				lblCont.setText("" + (Integer.parseInt(lblCont.getText()) + 1));
			}
		});
		t.setRepeats(true);
		t.start();
			
		getContentPane().add(lblEtiq);
		getContentPane().add(lblCont);
		
		// Evento para cerrar la ventana
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing (WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	/**
	  * Main
	  */
	public static void main (String[] args)
	{
		EjemploSwing2 e = new EjemploSwing2();
		e.show();
	}
}