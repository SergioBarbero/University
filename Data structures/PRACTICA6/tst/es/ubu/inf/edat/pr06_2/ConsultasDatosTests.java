package es.ubu.inf.edat.pr06_2;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import es.ubu.inf.edat.datos.personas.GeneradorPersonas;
import es.ubu.inf.edat.datos.personas.Persona;

public class ConsultasDatosTests {

	private static List<Persona> listado  = Arrays.asList(GeneradorPersonas.todos());
	
	@BeforeClass
	public static void datosDeTrabajo(){
		System.out.println(listado);
	}
	
	@Test
	public void testPromedioEdad() {
		
		// TODO - Completa primero los metodos de stream, completa luego el ejercicio con los de iteradores
// 		double promedioI = ConsultasDatos.promedioEdad_iteracion(listado);
// 		assertEquals(30.0769,promedioI,0.0001);
		
		double promedioS = ConsultasDatos.promedioEdad_stream(listado);
		assertEquals(30.0769,promedioS,0.0001);
		
	}

	/** 
	 * Al tratarse de un método que NO devuelve ningún valor, el test debe 
	 * ser una comprobación por parte del alumno. 
	 */
	@Test
	public void testListadoTodos (){
		
		//TODO - Comprobar (por inspección) que se han listado todos
		//TODO - Comprobar (por inspección) que aparecen en el orden indicado
		//TODO - Comprobar (por inspección) que ambos listados son IGUALES

		/* Esperados: 
		 * 
		Pablo, Alza Alcalde
		Maria, Calvo Cuesta
		Vicente, Cantal Zamora
		Tomas, Estevan Cazador
		Maria, Garcia Blanco
		Luis, Gomez Alonso
		Victoria, Hialgo Lopez
		Lucia, Manso Verde
		Laura, Sacristan Antunez
		Pedro, Sanchez Aguado
		Carmen, Savador Perez
		Isabel, Tome Pena
		 */
		
		// TODO - Completa primero los metodos de stream, completa luego el ejercicio con los de iteradores
//		ConsultasDatos.listadoTodos_iterador(listado);
		ConsultasDatos.listadoTodos_stream(listado);
		
	}
	
	@Test
	public void testNumMatric (){
		
		// TODO - Completa primero los metodos de stream, completa luego el ejercicio con los de iteradores
//		assertEquals(0, ConsultasDatos.numeroMatriculados_iterador(listado, ""));
		assertEquals(0, ConsultasDatos.numeroMatriculados_stream(listado, ""));

		String listadoStr = listado.toString();
		String buscado = "Grado en Ingenieria Electronica Industrial y Automatica";
		
		int veces = 0;
		int ind = listadoStr.indexOf(buscado);
		while( ind>0 && ind<listadoStr.length() ){
			veces ++;
			listadoStr = listadoStr.substring(ind+buscado.length());
			ind = listadoStr.indexOf(buscado);
		}
			
		// TODO - Completa primero los metodos de stream, completa luego el ejercicio con los de iteradores
//		assertEquals(veces,ConsultasDatos.numeroMatriculados_iterador(listado,buscado));
		assertEquals(veces,ConsultasDatos.numeroMatriculados_stream(listado,buscado));
		
	}
	
	@Test
	public void testListadosSeparados(){
		
		List<List<Persona>> resultado;
		
		List<Persona> alumnos = Arrays.asList(GeneradorPersonas.soloAlumnos());
		List<Persona> profesores = Arrays.asList(GeneradorPersonas.soloProfesores());
		List<Persona> trabajadores = Arrays.asList(GeneradorPersonas.soloTrabajadores());
		
		// TODO - Completa primero los metodos de stream, completa luego el ejercicio con los de iteradores
		
//		resultado = ConsultasDatos.desglosadoPorProfesion_iterador(listado);
		
//		assertEquals(alumnos,resultado.get(0));
//		assertEquals(profesores,resultado.get(2));
//		assertEquals(trabajadores,resultado.get(3));

		resultado = ConsultasDatos.desglosadoPorProfesion_stream(listado);
		
		assertEquals(alumnos.size(),resultado.get(0).size());
		assertTrue(alumnos.containsAll(resultado.get(0)));
		assertEquals(profesores.size(),resultado.get(1).size());
		assertTrue(profesores.containsAll(resultado.get(1)));
		assertEquals(trabajadores.size(),resultado.get(2).size());
		assertTrue(trabajadores.containsAll(resultado.get(2)));

	}
	
	@Test 
	public void testMayorAntiguedad(){

	// TODO - Completa primero los metodos de stream, completa luego el ejercicio con los de iteradores
//		assertFalse(ConsultasDatos.mayorAntiguedad_iterador(listado, 200) );
//		assertTrue(ConsultasDatos.mayorAntiguedad_iterador(listado, 2) );

		assertFalse(ConsultasDatos.mayorAntiguedad_stream(listado, 200) );
		assertTrue(ConsultasDatos.mayorAntiguedad_stream(listado, 2) );
		
	}
	
}
