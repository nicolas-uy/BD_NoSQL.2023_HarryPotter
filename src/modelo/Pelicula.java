package modelo;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {

	private String titulo;
	private String nombre;
	private String basadaen;
	private List<String> actores;
	private Integer anio;
	private String sinopsis;

	public Pelicula() {
		super();
		this.actores = new ArrayList<String>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getBasadaen() {
		return basadaen;
	}

	public void setBasadaen(String basadaen) {
		this.basadaen = basadaen;
	}

	public List<String> getActores() {
		return actores;
	}

	public void setActores(List<String> actores) {
		this.actores = actores;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public void agregarActor(String actor) {
		this.actores.add(actor);
	}

	@Override
	public String toString() {
		return "Peliculas:\n"  
					+ "Titulo: " + titulo + "\n"
					+ "Nombre: " + nombre + "\n"
					+ "Basada en: " + basadaen + "\n"
					+ "Actores: " + actores + "\n"
					+ "Anio:" + anio + "\n"
					+ "Sinopsis:" + sinopsis + ".";
	}

}
