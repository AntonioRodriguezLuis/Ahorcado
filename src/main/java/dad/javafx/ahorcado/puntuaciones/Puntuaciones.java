package dad.javafx.ahorcado.puntuaciones;

public class Puntuaciones {

	private String nombre;
	private int puntuaciones;

	public Puntuaciones(String nombre, int puntuaciones) {
		this.nombre = nombre;
		this.puntuaciones = puntuaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(int puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	@Override
	public String toString() {
		return nombre + ", " + puntuaciones;
	}

}
