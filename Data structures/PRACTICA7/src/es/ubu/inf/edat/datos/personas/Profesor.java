package es.ubu.inf.edat.datos.personas;

public class Profesor extends Persona{
	protected String departamento;
    private int idInterno;
    
    /**
     * Constructor de la clase Profesor
     */
    public Profesor (String apellidos, String nombre, String nif, Persona.sexo genero, int edad, int expediente){
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.nif = nif;
        this.genero = genero;
        this.edad = edad;
        this.expediente = expediente;
    }

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	        
	public int getIdInterno() {
		return this.idInterno;
	}

	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}

}
