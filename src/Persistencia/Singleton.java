package Persistencia;

import java.util.ArrayList;
import Entidades.ArticulosEntidad;
public class Singleton {
		private ArrayList<ArticulosEntidad> listaArticulos;
		private static Singleton instancia = null;

		private Singleton() {
		}

		public static Singleton getInstancia() {
			if (instancia == null) {
				instancia = new Singleton();
				instancia.listaArticulos = new ArrayList<ArticulosEntidad>();			
				instancia.listaArticulos.add(new ArticulosEntidad(1, "harina", 52.50));
				instancia.listaArticulos.add(new ArticulosEntidad(5, "azucar", 42.50));
				instancia.listaArticulos.add(new ArticulosEntidad(3, "aceite", 95.50));
				instancia.listaArticulos.add(new ArticulosEntidad(25, "aceite", 105.50));
			}
			return instancia;
		}

		public ArrayList<ArticulosEntidad> listarArticulos() {
			return listaArticulos;
			
		}
		public int agregarArticulo(ArticulosEntidad art) {
			listaArticulos.add(art);
			return art.getId();
		}
}
