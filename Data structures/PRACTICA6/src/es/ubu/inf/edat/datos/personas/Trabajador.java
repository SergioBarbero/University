package es.ubu.inf.edat.datos.personas;

public class Trabajador extends Persona {
	protected String servicio;
    private int mesesAntiguedad;
    
    /**
     * Constructor de la clase Trabajador de Administraci�n y Servicios
     */
    public Trabajador (String apellidos, String nombre, String nif, Persona.sexo genero, int edad, int expediente){
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.nif = nif;
        this.genero = genero;
        this.edad = edad;
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
	
	@Override
	public String toString(){
		return "TRABAJADOR:" + super.toString() + " - servicio: " +this.servicio + " - antiguedad: " + this.getMesesAntiguedad();
	}
	
}
