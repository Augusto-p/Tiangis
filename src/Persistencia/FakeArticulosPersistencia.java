package Persistencia;

import java.util.ArrayList;
import java.util.Iterator;

import Entidades.ArticulosEntidad;


public class FakeArticulosPersistencia implements IArticulos{

	@Override
	public ArrayList<ArticulosEntidad> obternerListaArticulos() {
		Singleton singleton = Singleton.getInstancia();
		ArrayList<ArticulosEntidad> lista = singleton.listarArticulos();
		return lista;
	}

	@Override
	public boolean eliminarArticulo(int id) {
		boolean res=false;
		Singleton singleton = Singleton.getInstancia();
		ArrayList<ArticulosEntidad> lista = singleton.listarArticulos();
		Iterator<ArticulosEntidad> iterator=lista.iterator();
		while (iterator.hasNext()) {
			ArticulosEntidad elem = iterator.next();
			if (elem.getId() == id) {
				iterator.remove();
				res=true;
			}			
		}
		return res;
	}

	

	@Override
	public int agregarArticulo(ArticulosEntidad art) {
		Singleton singleton = Singleton.getInstancia();
		singleton.agregarArticulo(art);
		return 0;
	}

	@Override
	public boolean modificarArticulo(ArticulosEntidad arto) {
		Singleton singleton = Singleton.getInstancia();
		ArrayList<ArticulosEntidad> lista = singleton.listarArticulos();
		boolean encontrado = false;
		int i = 0;
		int pos = 0;
		while (!encontrado && i < lista.size()) {
			ArticulosEntidad elemento = lista.get(i);
			if (elemento.getId() == arto.getId()) {				
				encontrado=true;
				pos = i;
			}
			i++;
		}
		if (encontrado) {
			lista.get(pos).setId(arto.getId());
			lista.get(pos).setName(arto.getName());
			lista.get(pos).setPrice(arto.getPrice());
		}			
		return encontrado;
	}
	
}