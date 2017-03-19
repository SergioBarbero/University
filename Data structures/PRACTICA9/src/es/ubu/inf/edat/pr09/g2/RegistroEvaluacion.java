package es.ubu.inf.edat.pr09.g2;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import es.ubu.inf.edat.datos.Alumno;

public class RegistroEvaluacion {

	// TODO - Instanciar una clase de tipo Set que permita resolver el problema
	/* TODO - Contestar a la siguiente pregunta: 
	 * ¿Cual es la diferencia entre emplear un HashSet y un TreeSet para implementar
	 * cada uno de los métodos? ¿Se pueden intercambiar sin más o es necesario hacer alguna
	 * pequeña modificación en el código? ¿Que complejidad algortimica tendría la solución
	 * en caso de emplear uno u otro?
	 */
	TreeSet<Alumno> clase = new TreeSet<Alumno>();
	Alumno alumno;

	/**
	 * Metodo que permite incorporar una nueva nota de evaluacion
	 * al expediente de un alumno.
	 * 
	 * @param dni identificador del alumno
	 * @param nota nota obtenida en la prueba
	 * @return Expediente del alumno tras ser modificado.   
	 */
	public Alumno anadeNota(String dni, float nota){
		if(!clase.contains(dni)){
			alumno = new Alumno(dni);
			clase.add(alumno);
		}
		else
			alumno = clase.tailSet(alumno).first();
		
		alumno.acumulaNota(nota);
		return alumno;
	}


	/**
	 * Metodo que permite consultar la nota de un alumno
	 * 
	 * @param dni identificador unico del alumno sobre el que se consulta
	 * @return nota media obtenida por el alumno
	 */

	public float devuelveNota(String dni){
		return 0;
	}

	/**
	 * Metodo que permite obtener un listado de los expedientes de alumnos de
	 * la clase. Deberan aparecer ORDENADOS por su nota de mayor a menor.
	 *  
	 * @return Listado completo de todos los alumnos incluidos en la clase
	 */

	public List<Alumno> listadoNotas(){
		return null;
	}

	/**
	 * Devuelve un listado de todos los alumnos cuyo identificador de DNI está comprendido entre 
	 * los DNIs por los que se está consultando.
	 *  
	 * @return Listado de las instancias de Alumno que cumplen con las condiciones de DNI
	 */
	public List<Alumno> listadoRangoDNI(String inicio, String fin){	
		return null;
	}
	

	/**
	 * Devuelve el numero de alumnos diferentes que se han evaluado hasta ahora. 
	 * Aquellos alumnos que no han recibido ninguna evaluacion hasta el momento, por definicion 
	 * no se habran incluido en el conjunto de evaluados, por lo que no se necesita tener en cuenta la nota
	 * de los incluidos en el conjunto de evaluados.  
	 *  
	 * @return Numero de alumnos evaluados
	 */
	public int numeroAlumnos(){
		return 0;
	}

	/**
	 * Permite limpiar completamente el listado de alumnos.
	 */
	public void limpiaListado(){
		
	}


} // RegistoEvaluacion
