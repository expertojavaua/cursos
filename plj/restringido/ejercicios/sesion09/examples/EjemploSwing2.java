import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  * Este es un ejemplo donde se muestran algunos de los controles
  * de Swing, y se modifica un contador bien pulsando un boton,
  * bien modificando el valor a mano, bien mediante un desplegable
  */
public class EjemploSwing2 extends JFrame
{
	/**
	  * Contador a modificar
	  */
	JTextField txtCont;
	
	/**
	  * Constructor
	  */
	public EjemploSwing2()
	{
		setSize(500, 200);
		
		// ****** Panel con el contador ******
		
		JPanel panelCont = new JPanel();		
		final JLabel lblCont = new JLabel("Contador:");
		lblCont.addMouseListener(new MouseAdapter()
		{
			// Al entrar en la etiqueta hacemos que cambie su texto
			public void mouseEntered(MouseEvent e)
			{
				lblCont.setText("En etiqueta!");
			}

			public void mouseExited(MouseEvent e)
			{
				lblCont.setText("Contador:");
			}
		});
		txtCont = new JTextField("0");
		panelCont.add(lblCont);
		panelCont.add(txtCont);
		
		// ****** Panel para el boton ******
		
		JPanel panelBoton = new JPanel();		
		JLabel lblBoton = new JLabel("Incrementar Contador:");
		JButton btn = new JButton("Incrementar");
		btn.addActionListener(new ActionListener()
		{
			// Al pulsar el boton incrementamos en 1 el contador
			public void actionPerformed(ActionEvent e)
			{
				txtCont.setText("" + (Integer.parseInt(txtCont.getText()) + 1));
			}
		});
		panelBoton.add(lblBoton);
		panelBoton.add(btn);
		
		// ****** Panel para el desplegable ******
		
		JPanel panelChoice = new JPanel();		
		JLabel lblChoice = new JLabel("Establecer Contador:");
		final JComboBox ch = new JComboBox();
		for (int i = 0; i < 10; i++)
			ch.addItem("" + i);
		ch.addItemListener(new ItemListener()
		{
			// Al elegir una opcion, se asigna ese valor al contador
			public void itemStateChanged(ItemEvent e)
			{
				txtCont.setText((String)(ch.getSelectedItem()));
			}
		});
		panelChoice.add(lblChoice);
		panelChoice.add(ch);


		// Colocamos los paneles
		getContentPane().add(panelCont, "South");
		getContentPane().add(panelBoton, "West");
		getContentPane().add(panelChoice, "East");
		
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