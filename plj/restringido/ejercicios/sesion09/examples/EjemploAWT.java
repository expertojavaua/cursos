import java.awt.*;
import java.awt.event.*;

/**
  * Este es un ejemplo donde se muestran algunos de los controles
  * de AWT
  */
public class EjemploAWT extends Frame
{
	/**
	  * Constructor
	  */
	public EjemploAWT()
	{
		setSize(400, 300);
		setLayout(new GridLayout(4, 2));

		// Menus
		
		MenuBar mb = new MenuBar();
		Menu m1 = new Menu ("Menu 1");
		Menu m11 = new Menu ("Menu 1.1");
		Menu m2 = new Menu ("Menu 2");
		MenuItem mi1 = new MenuItem ("Item 1.1");
		MenuItem mi11 = new MenuItem ("Item 1.1.1");
		CheckboxMenuItem mi2 = new CheckboxMenuItem("Item 2.1");
		mb.add(m1);
		mb.add(m2);		
		m1.add(mi1);
		m1.add(m11);
		m11.add(mi11);
		m2.add(mi2);
		setMenuBar(mb);
		
		// Componentes
		
		Label lblBoton = new Label("Boton:");
		Label lblCheck = new Label("Checkbox:");
		Label lblChoice = new Label("Choice:");
		Label lblText = new Label("TextField:");

		Button btn = new Button ("Boton");
		Checkbox chk = new Checkbox ("Checkbox", true);
		Choice ch = new Choice();
		ch.addItem("Opcion 1");
		ch.addItem("Opcion 2");
		TextField txt = new TextField("Texto");
		
		add(lblBoton);
		add(btn);
		add(lblCheck);
		add(chk);
		add(lblChoice);
		add(ch);
		add(lblText);
		add(txt);
		
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
		EjemploAWT e = new EjemploAWT();
		e.show();
	}
}