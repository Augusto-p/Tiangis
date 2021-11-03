package Vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;

import Entidades.ArticulosEntidad;
import Modelo.Articulos;
import Utilidades.GestionDeCelda;
import Utilidades.GestionDeEncabesado;
import Utilidades.ModeloTabla;
import Utilidades.Utilidades;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


import javax.swing.JOptionPane;


public class VentanaPrincipal extends JFrame implements MouseListener {

	private JFrame frame;
	private JTable table;
	private ModeloTabla modelo;
	private int filasTabla;
	private int columnaTabla;
	private JScrollPane scrollPane;
	private String[] Baner;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 
	 
	public VentanaPrincipal() {

		frame = new JFrame();
		frame.setBounds(100, 100, 920, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Tiangis");
		frame.setIconImage(new ImageIcon(getClass().getResource("/img/Logo.png")).getImage());
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 819, 446);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setBackground(Color.white);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table.addMouseListener(this);
		table.setOpaque(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		ArrayList<ArticulosEntidad> lista = Articulos.ListarArticulos();
		String[][] data = getdata(lista);
		String[] Baner = new String[3];
		Baner[0] = "ID";
		Baner[1] = "Nombre";
		Baner[2] = "Precio";

		JButton del = new JButton("");
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarArticulo();
			}
		});
		del.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Supr.png")));
		del.setBounds(835, 150, 60, 40);
		frame.getContentPane().add(del);

		JButton mod = new JButton("");
		mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAMOd();

			}

		});
		mod.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Mod.png")));
		mod.setBounds(835, 250, 60, 40);
		frame.getContentPane().add(mod);

		JButton add = new JButton("");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAADD();
			}
		});
		add.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Add.png")));
		add.setBounds(835, 50, 60, 40);
		frame.getContentPane().add(add);
		crateTable(Baner, data);

	}

	public void actualizarPantalla() {
		String[][] data = getdata(Articulos.ListarArticulos());
		modelo.ActualisarDatos(data);
		table.getColumnModel().getColumn(Utilidades.ID).setCellRenderer(new GestionDeCelda("numerico"));
		table.getColumnModel().getColumn(Utilidades.NOMBRE).setCellRenderer(new GestionDeCelda("texto"));
		table.getColumnModel().getColumn(Utilidades.PRECIO).setCellRenderer(new GestionDeCelda("numerico"));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(25);// tamaño de las celdas
		table.setGridColor(new java.awt.Color(0, 0, 0));
		// Se define el tamaño de largo para cada columna y su contenido
		table.getColumnModel().getColumn(Utilidades.ID).setPreferredWidth(150);// id
		table.getColumnModel().getColumn(Utilidades.NOMBRE).setPreferredWidth(350);// nombre
		table.getColumnModel().getColumn(Utilidades.PRECIO).setPreferredWidth(350);// edad

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int fila = table.rowAtPoint(e.getPoint());
		int columna = table.columnAtPoint(e.getPoint());
		Utilidades.filaSeleccionada = fila;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Esbozo de método generado automáticamente

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Esbozo de método generado automáticamente

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Esbozo de método generado automáticamente

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Esbozo de método generado automáticamente

	}

	public void crateTable(String[] Titulos, Object[][] data) {
		modelo = new ModeloTabla(data, Titulos);
		table.setModel(modelo);
		filasTabla = table.getRowCount();
		columnaTabla = table.getColumnCount();
		actualizarPantalla();
		// personalisar encavesado
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new GestionDeEncabesado());
		table.setTableHeader(header);

		// scrollpane
		scrollPane.setViewportView(table);
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());

	}

	public String[][] getdata(ArrayList<ArticulosEntidad> lista) {
		lista.sort(null);
		String data[][] = new String[lista.size()][3];
		for (int i = 0; i < lista.size(); i++) {
			ArticulosEntidad articulo = lista.get(i);
			data[i][0] = Integer.toString(articulo.getId());
			data[i][1] = Maysucula(articulo.getName());
			data[i][2] = Double.toString(articulo.getPrice());
		}
		return data;
	}

	private String Maysucula(String name) {
		char[] nameChar = name.toCharArray();
		nameChar[0] = Character.toUpperCase(nameChar[0]);
		name = new String(nameChar);
		return name;
	}

	private void irAADD() {
		PopUp addPopUP = new PopUp("add", null, this);
		addPopUP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addPopUP.setVisible(true);
		actualizarPantalla();

	}

	private void irAMOd() {
		int ID_ = Integer.valueOf((String) table.getValueAt(Utilidades.filaSeleccionada, Utilidades.ID));
		String name = (String) table.getValueAt(Utilidades.filaSeleccionada, Utilidades.NOMBRE);
		double price = Double.valueOf((String) table.getValueAt(Utilidades.filaSeleccionada, Utilidades.PRECIO));
		ArticulosEntidad articulo = new ArticulosEntidad(ID_, name, price);
		PopUp dialog = new PopUp("mod", articulo, this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		actualizarPantalla();

	}

	private void eliminarArticulo() {
		int fila = Utilidades.filaSeleccionada;
		if (fila >= 0) {
			ArticulosEntidad art = (Articulos.ListarArticulos()).get(fila);
			int input = JOptionPane.showConfirmDialog(null, "quieres eliminar el Articulo con id: " + art.getId());
			// 0=yes, 1=no, 2=cancel
			if (input == 0) {
				Articulos articulo = new Articulos(art.getId(), art.getName(), art.getPrice());
				articulo.supr();

			}
		}

	}
}
