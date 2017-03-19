package es.ubu.inf.edat.pr_02.datos;

public class Persona {
	protected String apellidos;
	protected String nombre;  
	protected String nif;
	protected String genero;
	protected int expediente;
	    
	protected Persona(){ };
	
    public String toString() {
        return apellidos + "-" + nombre + "-" + nif + "-" + expediente + "-" + genero + "\t";
    }
    
    /**
     * @return devuelve el Número de Expediente.
     */
    public int getExpediente() {
        return expediente;
    }
    
    /**
     * @param Número de Expediente.
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
     * @return devuelve el género de la persona (hombre o mujer).
     */
    public String getGenero() {
        return this.genero;
    }
    
    /**
     * @param género que se desea establecer a la persona.
     */
    public void setGenero(String genero) {
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

}
