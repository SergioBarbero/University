package es.ubu.inf.edat.datos.personas;

public class Persona {
	protected String apellidos;
	protected String nombre;  
	protected String nif;
	protected int expediente;
	protected int edad;
	protected Persona.sexo genero;

	public enum sexo{HOMBRE, MUJER};

	protected Persona(){ };

	public String toString() {
		return apellidos + "-" + nombre + "-" + nif + "-" + expediente + "-" + genero + "-" + edad + "\t";
	}

	/**
	 * @return devuelve el N�mero de Expediente.
	 */
	public int getExpediente() {
		return expediente;
	}

	/**
	 * @param N�mero de Expediente.
	 */
	public void setExpediente(int expediente) {
		this.expediente = expediente;
	}

	/**
	 * @return devuelve los apellidos.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos que se desean almacenar.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return devuelve el nombre de la persona.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nombre que se desea establecer a la persona.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return devuelve el g�nero de la persona (hombre o mujer).
	 */
	public Persona.sexo getGenero() {
		return this.genero;
	}

	/**
	 * @param g�nero que se desea establecer a la persona.
	 */
	public void setGenero(Persona.sexo genero) {
		this.genero = genero;
	}

	/**
	 * @return devuelve el NIF de la persona.
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * @param NIF que se desea establecer a la persona.
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public boolean equals(Object o){
		Persona p;
		try{
			p = (Persona) o;
		}catch(ClassCastException cce){
			return false;
		}

		return this.getNif().equals(p.getNif());

	}

}
