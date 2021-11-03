package Modelo;

import java.util.ArrayList;

import Entidades.ArticulosEntidad;
import Persistencia.FakeArticulosPersistencia;
import Persistencia.IArticulos;

public class Articulos {
	private int id;
	private String name;
	private double price;
	private IArticulos repositorioArt;
	public Articulos(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public static ArrayList<ArticulosEntidad> ListarArticulos(){
		FakeArticulosPersistencia ArticuloPercistencia = new FakeArticulosPersistencia();
		ArrayList<ArticulosEntidad> lista = ArticuloPercistencia.obternerListaArticulos();
		return lista;
	}
	public void agregarArticulo() {
		repositorioArt = new FakeArticulosPersistencia();
		ArticulosEntidad art = this.getEntidad();
		repositorioArt.agregarArticulo(art);

	}
	public ArticulosEntidad getEntidad() {
		ArticulosEntidad ent = new ArticulosEntidad(this.id, this.name, this.price);
		return ent;
	}
	public boolean supr() {
		boolean res = false;
		repositorioArt = new FakeArticulosPersistencia();
		ArticulosEntidad art = this.getEntidad();
		
		res = repositorioArt.eliminarArticulo(art.getId());
		return res;
		
	}
	public boolean editar() {
		boolean res = false;
		repositorioArt = new FakeArticulosPersistencia();
		ArticulosEntidad art = this.getEntidad();
		res = repositorioArt.modificarArticulo(art);
		return res;
			
	}

}
