package Vista;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;


import Entidades.ArticulosEntidad;
import Modelo.Articulos;
import Persistencia.Singleton;
import Utilidades.ModeloTabla;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField IDV;
	private JTextField NameV;
	private JTextField PrecioV;
	private Articulos articulo;
	private ArticulosEntidad art;



	
	public PopUp(String mode, ArticulosEntidad art, VentanaPrincipal vp) {
		super(vp, true);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 11));
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		btnNewButton.setBounds(34, 299, 198, 34);
		getContentPane().add(btnNewButton);
		
		IDV = new JTextField();
		IDV.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		IDV.setBounds(144, 88, 296, 50);
		getContentPane().add(IDV);
		IDV.setColumns(10);
		
		JLabel IDText = new JLabel("ID:");
		IDText.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		IDText.setBounds(10, 88, 129, 50);
		getContentPane().add(IDText);
				
		JLabel NombreText = new JLabel("Nombre:");
		NombreText.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		NombreText.setBounds(10, 149, 129, 50);
		getContentPane().add(NombreText);
		
		JLabel PrecioText = new JLabel("Precio:");
		PrecioText.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		PrecioText.setBounds(10, 210, 129, 50);
		getContentPane().add(PrecioText);
		
		NameV = new JTextField();
		NameV.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		NameV.setColumns(10);
		NameV.setBounds(144, 149, 296, 50);
		getContentPane().add(NameV);
		
		PrecioV = new JTextField();
		PrecioV.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		PrecioV.setColumns(10);
		PrecioV.setBounds(144, 210, 296, 50);
		getContentPane().add(PrecioV);
		
		if (mode == "mod") {
			IDV.setText(Integer.toString(art.getId()));
			IDV.setEditable(false);
			NameV.setText(art.getName());
			PrecioV.setText(Double.toString(art.getPrice()));
			
			
			JButton btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editararticulo();
				}
			});
			btnGuardar.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			btnGuardar.setBounds(329, 299, 198, 34);
			getContentPane().add(btnGuardar);
					
			JLabel Titulo_1 = new JLabel("Modificar");
			Titulo_1.setFont(new Font("Segoe UI", Font.PLAIN, 40));
			Titulo_1.setBounds(191, 7, 182, 70);
			getContentPane().add(Titulo_1);
			
			
			
			
		}else if (mode == "add") {
			JButton btnGuardar_1 = new JButton("Añadir");
			btnGuardar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirArticulo();
				}
			});
			btnGuardar_1.setFont(new Font("Segoe UI", Font.PLAIN, 25));
			btnGuardar_1.setBounds(329, 299, 198, 34);
			getContentPane().add(btnGuardar_1);
			
			
			JLabel Titulo = new JLabel("Añadir");
			Titulo.setFont(new Font("Segoe UI", Font.PLAIN, 40));
			Titulo.setBounds(191, 7, 182, 70);
			getContentPane().add(Titulo);
			
		}
		
		
	}
	private void exit() {
		this.setVisible(false);
		
	}
	private void añadirArticulo() {
		try {
			ArrayList<ArticulosEntidad> articulos =  Articulos.ListarArticulos();
			int ID_ = Integer.valueOf(IDV.getText());
			boolean Existe = ExiteID(articulos, ID_);
			if (Existe != true) {
				String name = NameV.getText();
				double price = Double.valueOf((PrecioV.getText()).replaceAll(",","."));
				articulo = new Articulos(ID_, name, price);
				articulo.agregarArticulo();
				JOptionPane.showMessageDialog(null, "Se añadio el articulo id =" + ID_);
				this.setVisible(false);

			}else {
				int in = JOptionPane.showConfirmDialog(null, "Ya existe un articulo con la id " + ID_+ " desea Modificarlo");
				if (in == 0) {
					editararticulo();
				}
				
			}
			
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "la ID deve ser un entero y Precio deve ser Decimal");
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}

	}
	private boolean ExiteID(ArrayList<ArticulosEntidad> art, int iD_) {
		boolean res = false;
		for (int i = 0; i != art.size(); i++) {
			ArticulosEntidad articulo = art.get(i);
			if (iD_ == articulo.getId()) {
				res = true;
			}
		}
		return res;
	}
	private void editararticulo() {
		// TODO Auto-generated method stub
		try {
			int ID_ = Integer.valueOf(IDV.getText());
			System.out.println(ID_);
			String name = NameV.getText();
			double price = Double.valueOf(PrecioV.getText());
			
			articulo = new Articulos(ID_, name, price);
			boolean res = articulo.editar();
			if (res) {
				JOptionPane.showMessageDialog(null, "Se edito el cliente con id: " + ID_);
				this.setVisible(false);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El precio debe ser un decimal");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
