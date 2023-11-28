import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
  * Este es un ejemplo donde se muestran algunos de los controles
  * de Swing
  */
public class EjemploSwing extends JFrame
{
	/**
	  * Constructor
	  */
	public EjemploSwing()
	{
		setSize(400, 300);
		getContentPane().setLayout(new GridLayout(4, 2));

		// Menus
		
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu ("Menu 1");
		JMenu m11 = new JMenu ("Menu 1.1");
		JMenu m2 = new JMenu ("Menu 2");
		JMenuItem mi1 = new JMenuItem ("Item 1.1");
		JMenuItem mi11 = new JMenuItem ("Item 1.1.1");
		JCheckBoxMenuItem mi2 = new JCheckBoxMenuItem("Item 2.1");
		mb.add(m1);
		mb.add(m2);		
		m1.add(mi1);
		m1.add(m11);
		m11.add(mi11);
		m2.add(mi2);
		setJMenuBar(mb);
		
		// Componentes
		
		JLabel lblBoton = new JLabel("Boton:");
		JLabel lblCheck = new JLabel("Checkbox:");
		JLabel lblChoice = new JLabel("Choice:");
		JLabel lblText = new JLabel("TextField:");

		JButton btn = new JButton ("Boton");
		JCheckBox chk = new JCheckBox ("Checkbox", true);
		JComboBox ch = new JComboBox();
		ch.addItem("Opcion 1");
		ch.addItem("Opcion 2");
		JTextField txt = new JTextField("Texto");
		
		getContentPane().add(lblBoton);
		getContentPane().add(btn);
		getContentPane().add(lblCheck);
		getContentPane().add(chk);
		getContentPane().add(lblChoice);
		getContentPane().add(ch);
		getContentPane().add(lblText);
		getContentPane().add(txt);
		
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
		EjemploSwing e = new EjemploSwing();
		e.show();
	}
}