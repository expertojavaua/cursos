import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// ------------------------------------------------------------------------------------
// 	Ventana de seleccion de modo gráfico
// ------------------------------------------------------------------------------------

public class DisplayModeSelect extends JDialog {

	// Textos de la ventana
	public static final String TITLE = "Selección de modo gráfico";
	public static final String ACCEPT = "Aceptar";

	// Modos graficos
	DisplayMode [] modes;

	// Descripciones de los modos
	String [] smodes;

	// Modo seleccionado
	DisplayMode selec;

	// Lista de selección de modo
	JList lista;

	public DisplayModeSelect(GraphicsDevice gd, JFrame owner) {

		super(owner, TITLE, true);

		Container panel = getContentPane();
		panel.setLayout(new BorderLayout());

		// Boton de aceptar

		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		JButton btn_acept = new JButton(ACCEPT);
		botones.add(btn_acept);

		// Lista de modos gráficos

		initModes(gd);
		lista = new JList(smodes);
		JScrollPane scroll = new JScrollPane(lista);

		panel.add(scroll, BorderLayout.CENTER);
		panel.add(botones, BorderLayout.SOUTH);

		// Evento del boton

		btn_acept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
	}

	protected void aceptar() {

		// Si seleccionamos un elemento registramos el modo seleccionado y cerramos

		int indice = lista.getSelectedIndex();

		if(indice != -1) {
			selec = modes[indice];
			hide();
		}
	}

	private void initModes(GraphicsDevice gd) {

		// Construye la lista de modos gráficos y sus descripciones

		modes = gd.getDisplayModes();

		smodes = new String[modes.length];

		for(int i=0;i<modes.length;i++) {
			smodes[i] = modes[i].getWidth() + "x" + modes[i].getHeight() + ", " +
					modes[i].getBitDepth() + "bpp, " + 
					modes[i].getRefreshRate() + " Hz";
		}
	}

	public DisplayMode getSelection() {

		// Nos devuelve el modo gráfico seleccionado

		return selec;
	}
}