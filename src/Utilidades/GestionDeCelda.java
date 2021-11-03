package Utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionDeCelda extends DefaultTableCellRenderer  {
	private String tipo = "text";
	private Font normal = new Font("Verdana", Font.PLAIN, 12);
	private Font bold = new Font("Verdana", Font.BOLD, 12);
	
	private JLabel label = new JLabel();
	
		public GestionDeCelda(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
			int row, int column) {
		
		Color colorFondoNoSeleccionado = new Color(255, 255, 255);		
		Color colorFilasInPares = new Color(192, 192, 192);
		Color colorFilasSeleccion = new Color(255, 187, 65);
		Color colorFondoActivo = new Color(255, 187, 65);

		this.setForeground(new Color(0, 0, 0));
		this.setHorizontalAlignment(JLabel.LEFT);
		// asigna el valor
		this.setText((String) value);
		// this.setText( "uno");
		boolean esInPar = (row % 2) != 0;
		boolean Val = (Double.valueOf((String) table.getValueAt(row, Utilidades.PRECIO))) > 100;

		this.setBackground(colorFondoNoSeleccionado);
		if (esInPar) {
			this.setBackground(colorFilasInPares);
		}
		if (selected) {
			this.setBackground(colorFilasSeleccion);
		}
		if (focused) {
			this.setBackground(colorFondoActivo);
		}

		this.setFont(normal);
		
		// this.setFont(bold);
		if (tipo.equals("texto")) {
			this.setHorizontalAlignment(JLabel.LEFT);
			
		} 
		if (tipo.equals("numerico")) {
			this.setHorizontalAlignment(JLabel.CENTER);
			
		}
		if (Val) {
			this.setForeground(Color.RED);
		}
		
		return this;
	}
	

}
