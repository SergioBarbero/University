package es.ubu.inf.edat.datos.personas;

/**
 * @author prenedo, bbaruque
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Alumno extends Persona {
	protected String estudios;
    private int curso;
    
    /**
     * Constructor de la clase Alumno
     */
    public Alumno(String apellidos, String nombre, String nif, Persona.sexo genero, int edad, int expediente){
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.nif = nif;
        this.genero = genero;
        this.edad = edad;
        this.expediente = expediente;
    }

	public String getEstudios() {
		return this.estudios;
	}

	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}
	        
	public int getCurso() {
		return this.curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	@Override
	public String toString(){
		return super.toString() + ", estudios: "+estudios+", curso: "+curso;
	}
	
}
