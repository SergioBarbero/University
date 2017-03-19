package es.ubu.inf.edat.pr_02.datos;

public class Trabajador extends Persona {
	protected String servicio;
    private int mesesAntiguedad;
    
    
    /**
     * Constructor de la clase Trabajador de Administraci�n y Servicios
     */
    public Trabajador (String apellidos, String nombre, String nif, String genero, int expediente){
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.nif = nif;
        this.genero = genero;
        this.expediente = expediente;
    }

	public String getServicio() {
		return this.servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	        
	public int getMesesAntiguedad() {
		return this.mesesAntiguedad;
	}

	public void setMesesAntiguedad(int mesesAntiguedad) {
		this.mesesAntiguedad = mesesAntiguedad;
	}

	
	public boolean equals(Object o){
		boolean retorno = false;
		if(o instanceof Trabajador && nif.equals(((Trabajador)o).getNif())){
				retorno = true;
			}
				return retorno;
	}
}
