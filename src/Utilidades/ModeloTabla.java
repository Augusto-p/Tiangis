package Utilidades;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel{

	String[] titulos;
	Object[][] datos;
	public ModeloTabla(Object[][] datos, String[] titulos) {
		super();
		this.titulos = titulos;
		this.datos = datos;
		setDataVector(datos, titulos);
	}
	public void ActualisarDatos(Object[][] datos) {
		setDataVector(datos, this.titulos);
		
	}
	public boolean isCellEditable(int row, int column) {
		// Definimos si una celda puede ser o no editable
		return false;
	}
}
