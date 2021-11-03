package Persistencia;
import java.util.ArrayList;

import Entidades.ArticulosEntidad;
public interface IArticulos {
	ArrayList<ArticulosEntidad> obternerListaArticulos();


	boolean eliminarArticulo(int id);

	boolean modificarArticulo(ArticulosEntidad are);

	int agregarArticulo(ArticulosEntidad art);
}
