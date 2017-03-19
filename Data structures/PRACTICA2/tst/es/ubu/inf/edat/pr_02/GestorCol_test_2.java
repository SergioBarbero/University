package es.ubu.inf.edat.pr_02;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.ubu.inf.edat.pr_02.datos.Alumno;
import es.ubu.inf.edat.pr_02.datos.GeneradorPersonas;
import es.ubu.inf.edat.pr_02.datos.Persona;

/**
 * @author prenedo
 *
 */
public class GestorCol_test_2 {

	private GestorCol_2 gestor;
	
	@Before
	public void antes(){
		 gestor = new GestorCol_2(); 
	}
	
	@Test
	public void testAdd() {
		
		System.out.println("-- testAdd --");
		
		Persona[] nuevos = GeneradorPersonas.alumnosYProfesores();
		
		assertTrue(gestor.isEmpty());
		
		System.out.println("Se espera insertar un objeto sin errores.");
		
		for(int i=0; i<3; i++){
			assertTrue( "La coleccion debería haberse modificado.", gestor.add(nuevos[i]) );
		}
		
		System.out.println("El tamaño de la coleccion debería ser igual al numero de los elmentos insertados");
		assertEquals(3, gestor.size());
		assertFalse("La coleccion no debería estar vacía", gestor.isEmpty());
		
	}
	
	/**
	 * Test method for {@link es.ubu.inf.edat.pr02_1415.GestorMatriculados_Coleccion#addAll(java.util.Collection)}.
	 */
	@Test
	public void testAddAll() {
		
		System.out.println("-- testAddAll --");
		
		Persona[] nuevos = GeneradorPersonas.alumnosYProfesores();
		
		assertTrue("La coleccion se debería iniciar como vacía", gestor.isEmpty());
		
		System.out.println("Se espera insertar la nueva coleccion sobre la coleccion de prueba sin errores.");
		
		assertTrue("La coleccion debería haberse modificado.", gestor.addAll(Arrays.asList(nuevos)));
		
		System.out.println("El tamaño de la coleccion bajo prueba debería ser el mismo de la coleccion insertada");
		assertEquals(nuevos.length, gestor.size());
		assertFalse("La coleccion no debería estar vacía", gestor.isEmpty());		
		
	}

	@Test
	public void testClear() {
		
		System.out.println("-- testClear --");
		
		testAddAll();
		
		System.out.println("Se una vez llena, se espera limpiar la coleccion sin errores.");
		gestor.clear();
		
		System.out.println("Tras ejecutar el clear(), el tamaño debería ser 0");
		assertEquals(0, gestor.size());
		
		assertTrue("Tras ejecutar el clear(), la coleccion debería estar vacía", gestor.isEmpty());
		
	}

	/* Este test debería servir para explicar el metodo "equals" en java.
	 * Los Personas deberían ser Equals, dependiendo de (al menos) la matrícula */
	@Test
	public void testContains() {
		
		System.out.println("-- testContains --");
		
		testAddAll();
		
		Persona[] nuevos = GeneradorPersonas.alumnosYProfesores();

		System.out.println("Se espera encontrar un objeto contenido en la coleccion.");
		
		// Persona contenido
		assertTrue("Debería encontrar un objeto contenido en la coleccion.", gestor.contains(nuevos[3]));
		
		System.out.println("Se espera NO encontrar un objeto NO contenido en la coleccion.");
		
		// Persona No contenido
		Alumno a = new Alumno("Apellidos","Nombre","13131131S", "Hombre",13001);
		assertFalse("No se debería encontrar un objeto no contenido en la coleccion.", gestor.contains(a));
		
	}

	@Test
	public void testRemove() {
		
		System.out.println("-- testRemove --");
		
		testAddAll();
		
		Persona[] nuevos = GeneradorPersonas.alumnosYProfesores();

		// Persona contenido
		System.out.println("Se prueba a borrar un objeto sin errores.");
		assertTrue("La coleccion debería haberse modificado.", gestor.remove(nuevos[3]));
		
		System.out.println("El tamaño de la coleccion debería ser menor en un elemento.");
		assertEquals(nuevos.length-1, gestor.size());
		
		// Persona No contenido
		Alumno a = new Alumno("Apellidos","Nombre","13131131S", "Hombre",13001);
		
		System.out.println("Se prueba a borrar un objeto no contenido.");
		assertFalse("La coleccion NO debería haberse modificado.", gestor.remove(a));
		
		System.out.println("El tamaño de la coleccion debería ser igual.");
		assertEquals(nuevos.length-1, gestor.size());

	}

	@Test
	public void testRemoveAll() {
		
		System.out.println("-- testRemoveAll --");
		
		testAddAll();
		
		Persona[] nuevos = GeneradorPersonas.alumnosYProfesores();
		List<Persona> contenidos =  Arrays.asList(nuevos).subList(1,4);
		
		System.out.println("Se prueba a borrar un conjunto de objetos sin errores.");
		assertTrue( "La coleccion debería haberse modificado.",	gestor.removeAll(contenidos) );
		
		System.out.println("El tamaño de la coleccion debería ser menor en el num. de elementos eliminados.");
		assertEquals(nuevos.length-3, gestor.size());
		
		// Los insertados en 'contenidos' ya estaban eliminados
		// El unico elemento nuevo no estaba contenido en la coleccion
		
		System.out.println("Se prueba a borrar un conjunto de objetos entre los que hay un "
				+ "objeto no contenido y los otras ya han sido eliminados.");
		
		Alumno a = new Alumno("Apellidos","Nombre","13131131S", "Hombre",13001);
		contenidos.set(0,a);
		
		assertFalse("La coleccion NO debería haberse modificado.",	gestor.removeAll(contenidos) );
		System.out.println("El tamaño de la coleccion debería ser igual que antes.");
		assertEquals(nuevos.length-3, gestor.size());
	}


	@Test
	public void testToArray() {
		
		System.out.println("-- testToArray --");
		
		testAddAll();
		
		Persona[] generados = GeneradorPersonas.alumnosYProfesores();
		
		Object[] obtenidos = gestor.toArray();
		
		assertEquals(generados.length, obtenidos.length);
		
		for(int i=0; i<generados.length; i++){
			assertEquals(generados[i], obtenidos[i]);
		}
		
	}

	/**
	 * Test method for {@link es.ubu.inf.edat.pr02_1415.GestorMatriculados_Coleccion#toArray(T[])}.
	 */
	@Test
	public void testToArrayTArray() {
		
		System.out.println("-- testToArrayTArray --");
		
		/* 
		 * Se insertan alumnos y profesores.
		 * Se solicita obtenerlos como un array de "Persona".
		 */
		
		testAddAll();
		
		Persona[] generados = GeneradorPersonas.alumnosYProfesores();
		
		Persona[] obtenidos = gestor.toArray(new Persona[0]);
		
		System.out.println("Se inserta un conjunto de Personas en la coleccion de prueba."
				+ "Se obtiene de nuevo como array de Personas y se comprueba que es igual que el introducido.");
		
		System.out.println("El tamaño de ambos debe ser igual.");
		assertEquals(generados.length, obtenidos.length);
		
		System.out.println("El contenido de ambos debe ser igual.");
		for(int i=0; i<generados.length; i++){
			assertEquals(generados[i], obtenidos[i]);
		}
		
		/* 
		 * Se insertan solo profesores.
		 * Se solicita obtenerlos como un array de "Alumnos".
		 */

		System.out.println("Se inserta un conjunto de Alumnos en la coleccion de prueba."
				+ "Se obtiene de nuevo como array de Alumnos y se comprueba que es igual que el introducido.");

		gestor.clear();
		generados = GeneradorPersonas.soloAlumnos();
		gestor.addAll(Arrays.asList(generados));	
		
		obtenidos = gestor.toArray(new Alumno[0]);
		
		System.out.println("El tamaño de ambos debe ser igual.");
		assertEquals(generados.length, obtenidos.length);
		
		System.out.println("El contenido de ambos debe ser igual.");
		for(int i=0; i<generados.length; i++){
			assertEquals(generados[i], obtenidos[i]);
		}

	}

}
