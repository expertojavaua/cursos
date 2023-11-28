import java.awt.*;
import java.awt.event.*;

/**
  * Este es un ejemplo donde se muestran algunos de los controles
  * de AWT, y se modifica un contador bien pulsando un boton,
  * bien modificando el valor a mano, bien mediante un desplegable
  */
public class EjemploAWT2 extends Frame
{
	/**
	  * Contador a modificar
	  */
	TextField txtCont;
	
	/**
	  * Constructor
	  */
	public EjemploAWT2()
	{
		setSize(500, 200);
		
		// ****** Panel con el contador ******
		
		Panel panelCont = new Panel();		
		final Label lblCont = new Label("Contador:");
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
		txtCont = new TextField("0");
		panelCont.add(lblCont);
		panelCont.add(txtCont);
		
		// ****** Panel para el boton ******
		
		Panel panelBoton = new Panel();		
		Label lblBoton = new Label("Incrementar Contador:");
		Button btn = new Button("Incrementar");
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
		
		Panel panelChoice = new Panel();		
		Label lblChoice = new Label("Establecer Contador:");
		final Choice ch = new Choice();
		for (int i = 0; i < 10; i++)
			ch.addItem("" + i);
		ch.addItemListener(new ItemListener()
		{
			// Al elegir una opcion, se asigna ese valor al contador
			public void itemStateChanged(ItemEvent e)
			{
				txtCont.setText(ch.getSelectedItem());
			}
		});
		panelChoice.add(lblChoice);
		panelChoice.add(ch);


		// Colocamos los paneles
		add(panelCont, "South");
		add(panelBoton, "West");
		add(panelChoice, "East");
		
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
		EjemploAWT2 e = new EjemploAWT2();
		e.show();
	}
}