package es.ubu.inf.edat.pr_02.datos;

/**
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Alumno extends Persona {
	protected String estudios;
    private int curso;
    
    /**
     * Constructor de la clase Alumno
     */
    public Alumno(String apellidos, String nombre, String nif, String genero, int expediente){
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.nif = nif;
        this.genero = genero;
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
	
	public boolean equals(Object o){
		boolean retorno = false;
		if(o instanceof Alumno && nif.equals(((Alumno)o).getNif())){
				retorno = true;
			}
				return retorno;
	}
	
}
