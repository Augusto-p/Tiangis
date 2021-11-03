package Entidades;

public class ArticulosEntidad implements Comparable<ArticulosEntidad>{
	public int ID;
	private String name;
	private double price;
	public ArticulosEntidad(int id, String name, double price) {
		this.ID = id;
		this.name = name;
		this.price = price;
	}
	
	
	public int getId() {
		return ID;
	}


	public void setId(int id) {
		this.ID = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public int compareTo(ArticulosEntidad p2) {
		int r = 0;
		if (this.getId() < p2.getId()) {
			r = -1;
		}else if (this.getId() > p2.getId()) {
			r = 1;
		}
		return r;
	}
	
	
	

}
