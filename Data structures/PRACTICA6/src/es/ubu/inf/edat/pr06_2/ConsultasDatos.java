package es.ubu.inf.edat.pr06_2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import es.ubu.inf.edat.datos.personas.*;

public class ConsultasDatos {


	/**
	 * Metodo que permite calcular el promedio de edad de las personas incluidas 
	 * en la colección que se facilita. 
	 * 
	 * Se debe realizar este proceso apoyandose en la iteración sobre la colección.
	 * 
	 * @param coleccion - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @return - Media de la edad de esas personas.
	 */
	public double promedioEdad_iteracion(Collection<Persona> coleccion){
		// TODO - Completar el metodo
		return 0;
	}

	/**
	 * Metodo que permite calcular el promedio de edad de las personas incluidas 
	 * en la colección que se facilita. 
	 * 
	 * Se debe realizar este proceso apoyandose en la nueva
	 * funcionalidad de Stream de Java 8.
	 * 
	 * @param coleccion - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @return - Media de la edad de esas personas.
	 */
	
	public double promedioEdad_stream(Collection<Persona> coleccion){
		return coleccion
				.stream()
				.mapToInt(p -> p.getEdad())
				.average().getAsDouble();
	}

	/**
	 * Metodo que permite obtener por pantalla el Nombre y los apellidos de las personas incluidas 
	 * en la colección que se facilita. 
	 * 
	 * Los datos deben aparecer en ese mismo orden en el listado: Nombre, Apellido1 Apellido2
	 * 
	 * Se debe realizar este proceso apoyandose en la iteración sobre la colección.
	 * 
	 * @param coleccion - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 */

	public static void listadoTodos_iterador(Collection<Persona> coleccion){
		// TODO - Completar el metodo
	}


	/**
	 * Metodo que permite obtener por pantalla el Nombre y los apellidos de las personas incluidas 
	 * en la colección que se facilita. 
	 * 
	 * Los datos deben aparecer en ese mismo orden en el listado: Nombre, Apellido1 Apellido2
	 * 
	 * Se debe realizar este proceso apoyandose en la nueva
	 * funcionalidad de Stream de Java 8.
	 * 
	 * @param coleccion - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 */

	public static void listadoTodos_stream(Collection<Persona> coleccion){
		coleccion.stream().forEach(p -> System.out.println(p.getNombre() + p.getApellidos()));
	}
		
	/**
	 * Metodo que permite calcular el numero de alumnos incluidos en la colección que se facilita
	 * que están matriculados en aquella titulación que se suministra como parámetro. 
	 * 
	 * Se debe realizar este proceso apoyandose en la iteración sobre la colección.
	 * 
	 * @param coleccion - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @return - Entero indicando el numero de alumnos que pertenecen a esa titulacion
	 */
	public static int numeroMatriculados_iterador(Collection<Persona> coleccion, String estudios){
		// TODO - Completar el metodo
		return 0;
	}

	/**
	 * Metodo que permite calcular el numero de alumnos incluidos en la colección que se facilita
	 * que están matriculados en aquella titulación que se suministra como parámetro. 
	 * 
	 * Se debe realizar este proceso apoyandose en la nueva
	 * funcionalidad de Stream de Java 8.
	 * 
	 * @param coleccion - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @return - Entero indicando el numero de alumnos que pertenecen a esa titulacion
	 */
	public static int numeroMatriculados_stream(Collection<Persona> coleccion, String estudios){
		return (int) coleccion.stream().filter(p -> p instanceof Alumno).filter(p -> ((Alumno)p).getEstudios() == estudios).count();
	}

	/**
	 * Metodo que permite comprobar si hay algún trabajador dentro de la colección que
	 * tenga una antiguedad igual o mayor aquella que se suministra como parámetro. 
	 * 
	 * Se debe realizar este proceso apoyandose en la iteración sobre la colección.
	 *
	 * @param - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @param antiguedad - Numero de referencia para comprobar la antiguedad
	 * @return true o false en función de que exista algún trabajador que cumpla las condiciones
	 */
	public static boolean mayorAntiguedad_iterador(Collection<Persona> coleccion, int antiguedad){
		// TODO - Completar el metodo
		return false;
	}
	
	/**
	 * Metodo que permite comprobar si hay algún trabajador dentro de la colección que
	 * tenga una antiguedad igual o mayor aquella que se suministra como parámetro. 
	 * 
	 * Se debe realizar este proceso apoyandose en la nueva
	 * funcionalidad de Stream de Java 8.
	 *
	 * @param - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @param antiguedad - Numero de referencia para comprobar la antiguedad
	 * @return true o false en función de que exista algún trabajador que cumpla las condiciones
	 */
	public static boolean mayorAntiguedad_stream(Collection<Persona> coleccion, int antiguedad){
		return coleccion.stream().filter(p -> p instanceof Trabajador).anyMatch(p -> ((Trabajador)p).getMesesAntiguedad() >= antiguedad);
	}
	

	/**
	 * Metodo que recibe una coleección de objetos de la clase Persona y devuelve una lista con 3
	 * listas diferentes, en función de la subclase a la que pertenezca cada uno: Alumno, Profesor, Trabajador
	 * (en ese mismo orden). 
	 * 
	 * Se debe realizar este proceso apoyandose en la iteración sobre la colección.
	 *
	 * @param - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @return - Lista de Listas que incluyen los objetos de la colección, divididos en función de la sublcase 
	 * de pertenencia.
	 */
	public static List<List<Persona>> desglosadoPorProfesion_iterador(Collection<Persona> coleccion){
		// TODO - Completar el metodo
		return null;
	}
	
	/**
	 * Metodo que recibe una coleección de objetos de la clase Persona y devuelve una lista con 3
	 * listas diferentes, en función de la subclase a la que pertenezca cada uno: Alumno, Profesor, Trabajador
	 * (en ese mismo orden). 
	 * 
	 * Se debe realizar este proceso apoyandose en la nueva
	 * funcionalidad de Stream de Java 8.
	 *
	 * @param - Colección sobre la que se consulta. Incluye todas las personas de las que se tienen datos.
	 * @return - Lista de Listas que incluyen los objetos de la colección, divididos en función de la sublcase 
	 * de pertenencia.
	 */	 
	public static List<List<Persona>> desglosadoPorProfesion_stream(Collection<Persona> coleccion){
		// TODO - Completar el metodo
		return null;
	}
	
}
