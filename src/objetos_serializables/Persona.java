package objetos_serializables;
import java.io.Serializable;

public class Persona implements Serializable{
	/**
	 * El IDE pide la version al serializable 
	 */
	private static final long serialVersionUID = 1L;
	public String nombre;
	public String apellidos;
	public int edad;
	
	public Persona(String nombre, String apellidos, int edad) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return this.nombre + " " + this.apellidos + ", " + this.edad;
	}
}
