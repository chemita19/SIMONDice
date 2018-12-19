
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class simonUI extends JFrame {

	private JPanel contentPane;
	private static int rondas = 1;
	private JTextField info;
	private static int[] secuencia = new int[100];
	private static String[] simon = new String[100]; 
	private static int contador;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					simonUI frame = new simonUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public simonUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 582);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		/*Funcion del menu que te explica como funciona el juego*/
		JMenuItem mntmCmoFunciona = new JMenuItem("Cómo funciona..");
		mnOpciones.add(mntmCmoFunciona);
		mntmCmoFunciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Bienvenido al juego de Simon\n"
						+ "Para empezar la partida hay que pulsar el botón Iniciar Partida\n"
						+ "El objetivo del juego es ir recordando la secuencia que te muestra el programa\n"
						+ "para dar una solución una vez termine el programa de mostrarte la secuencia debes\n"
						+ "pulsar los botones que estan debajo de los rectángulos, abajo hay una caja de información\n"
						+ "que te dice 'Perdiste' en el momento que fallas, o si aciertas la secuencia 'Ganas, siguiente ronda'\n"
						+ "para pasar a la siguiente ronda debes presionar el boton que pone 'Siguiente ronda'\n"
						+ "\n"
						+ "Tambien he implementado un botón de 'Más velocidad' para aumentar la dificultad del juego.\n"
						+ "\n"
						+ "Juego creado por: Jose María Castro Palacios DA1D1E");
			}
		});
		
		JCheckBoxMenuItem velocidad = new JCheckBoxMenuItem("Más velocidad ");
		mnOpciones.add(velocidad);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBounds(68, 68, 269, 164);
		panelVerde.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panelVerde.setBackground(Color.GREEN.darker());
		contentPane.add(panelVerde);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBounds(68, 268, 269, 164);
		panelAzul.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panelAzul.setBackground(Color.BLUE.darker());
		contentPane.add(panelAzul);
		
		JPanel panelRojo = new JPanel();
		panelRojo.setBounds(362, 68, 269, 164);
		panelRojo.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panelRojo.setBackground(Color.RED.darker());
		contentPane.add(panelRojo);
		
		JPanel panelAmarillo = new JPanel();
		panelAmarillo.setBounds(362, 268, 269, 164);
		panelAmarillo.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panelAmarillo.setBackground(Color.YELLOW.darker());
		contentPane.add(panelAmarillo);
		JButton btnVerde = new JButton("VERDE");
		btnVerde.setBounds(142, 233, 130, 23);
		JButton btnRojo = new JButton("ROJO");
		btnRojo.setBounds(431, 233, 139, 23);
		JButton btnAzul = new JButton("AZUL");
		btnAzul.setBounds(142, 435, 130, 23);
		JButton btnAmarillo = new JButton("AMARILLO");
		btnAmarillo.setBounds(431, 435, 139, 23);
		btnVerde.setEnabled(false);
		btnRojo.setEnabled(false);
		btnAzul.setEnabled(false);
		btnAmarillo.setEnabled(false);
		JLabel panelronda = new JLabel("");
		panelronda.setBounds(306, 233, 101, 23);
		contentPane.add(panelronda);
		JButton btnNewButton = new JButton("INICIAR PARTIDA");
		btnNewButton.setBounds(158, 12, 412, 31);
		/*Botón para iniciar partida o pasar a la siguiente ronda, llama a la función InitPartida que se encuentra al final*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelronda.setText("");
				panelronda.setText("RONDA: " + rondas);
				panelronda.paintAll(panelronda.getGraphics());
				try {
					InitPartida(panelVerde, panelAzul, panelRojo, panelAmarillo, btnNewButton, velocidad);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				info.setText("");
				btnVerde.setEnabled(true);
				btnRojo.setEnabled(true);
				btnAzul.setEnabled(true);
				btnAmarillo.setEnabled(true);
				
			}
		});
		contentPane.add(btnNewButton);
		
		
		/*Funciones para los botones, se encargan de ir haciendo la comprobación, si fallas pone todo con los valores iniciales para que puedas volver a iniciar una partida*/
		/*Si aciertas pero todavía no has llegado al último color simplemente aumenta el tamaño de la array para que puedas seguir comprobando, si aciertas y ya has llegado al 
		 * ultimo color te imprime GANASTE, SIGUIENTE RONDA y puedes volver a darle al botón de SIGUIENTE RONDA*/
		btnVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
				panelVerde.setBackground(Color.GREEN);

				if(!simon[contador].equals("verde")) {
					info.setText("Perdiste");
					rondas=1;
					btnNewButton.setText("Iniciar partida");
					btnNewButton.setEnabled(true);
					contador = 0;

					for(int i=0; i<simon.length; i++) {
						simon[i] = "";
					}
				}
				else if(simon[contador].equals("verde") && rondas - 1 == contador ) {
					info.setText("GANASTE, SIGUIENTE RONDA");
					info.paint(info.getGraphics());	
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					info.setText("");
					rondas++;
					contador=0;
					btnVerde.setEnabled(false);
					btnRojo.setEnabled(false);
					btnAzul.setEnabled(false);
					btnAmarillo.setEnabled(false);

					btnNewButton.setText("Siguiente ronda");
					colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
					btnNewButton.setEnabled(true);
				}
				else contador++;


			}
		});
		btnVerde.setForeground(Color.BLACK);
		btnVerde.setBackground(Color.GREEN);
		contentPane.add(btnVerde);
		
		btnRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
				panelRojo.setBackground(Color.RED);

				if(!simon[contador].equals("rojo")) {
					info.setText("Perdiste");
					System.out.println("HOLA");
					rondas=1;
					btnNewButton.setText("Iniciar partida");
					btnNewButton.setEnabled(true);
					contador = 0;

					for(int i=0; i<simon.length; i++) {
						simon[i] = "";
					}	
				}
				else if(simon[contador].equals("rojo") && rondas - 1 == contador) {
					info.setText("GANASTE, SIGUIENTE RONDA");
					info.paint(info.getGraphics());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					info.setText("");
					rondas++;
					contador=0;
					btnVerde.setEnabled(false);
					btnRojo.setEnabled(false);
					btnAzul.setEnabled(false);
					btnAmarillo.setEnabled(false);

					btnNewButton.setText("Siguiente ronda");
					colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
					btnNewButton.setEnabled(true);
				}
				else contador++;


			}
		});
		btnRojo.setBackground(Color.RED);
		contentPane.add(btnRojo);
		
		btnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
				panelAzul.setBackground(Color.BLUE);
				if(!simon[contador].equals("azul")) {
					info.setText("Perdiste");
					System.out.println("HOLA");
					rondas=1;
					btnNewButton.setText("Iniciar partida");
					btnNewButton.setEnabled(true);
					contador = 0;
					for(int i=0; i<simon.length; i++) {
						simon[i] = "";
					}
				}
				else if(simon[contador].equals("azul") && rondas - 1 == contador) {
					info.setText("GANASTE, SIGUIENTE RONDA");
					info.paint(info.getGraphics());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					info.setText("");
					rondas++;
					contador=0;
					btnVerde.setEnabled(false);
					btnRojo.setEnabled(false);
					btnAzul.setEnabled(false);
					btnAmarillo.setEnabled(false);

					btnNewButton.setText("Siguiente ronda");
					colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
					btnNewButton.setEnabled(true);
				}
				else contador++;

			}
		});
		btnAzul.setBackground(Color.CYAN);
		contentPane.add(btnAzul);
		

		btnAmarillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
				panelAmarillo.setBackground(Color.YELLOW);
				if(!simon[contador].equals("amarillo")) {
					info.setText("Perdiste");
					System.out.println("HOLA");
					rondas=1;
					btnNewButton.setText("Iniciar partida");
					btnNewButton.setEnabled(true);
					contador = 0;
					for(int i=0; i<simon.length; i++) {
						simon[i] = "";
					}
				}
				if(simon[contador].equals("amarillo") && rondas - 1 == contador) {
					info.setText("GANASTE, SIGUIENTE RONDA");
					info.paint(info.getGraphics());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					info.setText("");
					rondas++;
					contador=0;
					btnVerde.setEnabled(false);
					btnRojo.setEnabled(false);
					btnAzul.setEnabled(false);
					btnAmarillo.setEnabled(false);

					btnNewButton.setText("Siguiente ronda");
					colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
					btnNewButton.setEnabled(true);
				}
				else contador++;

			}
		});
		btnAmarillo.setBackground(Color.YELLOW);
		contentPane.add(btnAmarillo);
		
		info = new JTextField();
		info.setBounds(239, 462, 225, 42);
		contentPane.add(info);
		info.setColumns(10);
		
		JLabel lblInformacin = new JLabel("INFORMACIÓN");
		lblInformacin.setBounds(290, 444, 139, 15);
		contentPane.add(lblInformacin);
		
		
		
	}
	
	/*Función que se encarga de colorear en oscuro todos los paneles*/
	public static void colorearOscuro(JPanel panelVerde, JPanel panelAzul, JPanel panelRojo, JPanel panelAmarillo) {
		panelVerde.setBackground(Color.GREEN.darker());
		panelAzul.setBackground(Color.BLUE.darker());
		panelRojo.setBackground(Color.RED.darker());
		panelAmarillo.setBackground(Color.YELLOW.darker());
		panelVerde.paintAll(panelVerde.getGraphics());
		panelAzul.paintAll(panelAzul.getGraphics());
		panelAmarillo.paintAll(panelAmarillo.getGraphics());
		panelRojo.paintAll(panelRojo.getGraphics());
	}

	/*Bucle principal del juego que se encarga de randomizar los colores y de recordar la secuencia de los colores*/
	public static void InitPartida(JPanel panelVerde, JPanel panelAzul, JPanel panelRojo, JPanel panelAmarillo, JButton btnNewButton, JCheckBoxMenuItem velocidad) throws InterruptedException {
		    int turno = 0;
		    int nsecuencia = 0;
		    secuencia[rondas-1] = (int) (Math.random()*4);
		    int contadorsimon=0;
			while(turno < rondas) {
				int elegido = secuencia[nsecuencia];
				
				if(elegido == 0) {
					panelVerde.setBackground(Color.GREEN.brighter().brighter());
					panelVerde.paintAll(panelVerde.getGraphics());
					simon[contadorsimon] = "verde";
				}
				else if(elegido == 1) {
					panelAzul.setBackground(Color.BLUE.brighter().brighter());
					panelAzul.paintAll(panelAzul.getGraphics());
					simon[contadorsimon] = "azul";
				}
				else if(elegido == 2) {
					panelAmarillo.setBackground(Color.YELLOW.brighter().brighter());
					panelAmarillo.paintAll(panelAmarillo.getGraphics());
					simon[contadorsimon] = "amarillo";
				}
				else if(elegido == 3) {
					panelRojo.setBackground(Color.RED.brighter().brighter());
					panelRojo.paintAll(panelRojo.getGraphics());
					simon[contadorsimon] = "rojo";
				}	
				if(!velocidad.isSelected()){
				Thread.sleep(1000);
				}else Thread.sleep(500);
				colorearOscuro(panelVerde, panelAzul, panelRojo, panelAmarillo);
				turno++;
				nsecuencia++;
				btnNewButton.setEnabled(false);
				contadorsimon++;	
			}
			for(int i=0; i<turno; i++) {
				System.out.println(simon[i]);
			}
			
			
	   }
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
