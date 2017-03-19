package es.ubu.inf.edat.pr_02.datos;

public class Profesor extends Persona{
	protected String departamento;
    private int idInterno;
    
    /**
     * Constructor de la clase Profesor
     */
    public Profesor (String apellidos, String nombre, String nif, String genero, int expediente){
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.nif = nif;
        this.genero = genero;
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
	
	public boolean equals(Object o){
		boolean retorno = false;
		if(o instanceof Profesor && nif.equals(((Profesor)o).getNif())){
				retorno = true;
			}
				return retorno;
	}

}
