package es.ubu.inf.edat.pr07_2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import es.ubu.inf.edat.datos.personas.GeneradorPersonas;
import es.ubu.inf.edat.datos.personas.Persona;
import es.ubu.inf.edat.datos.personas.Trabajador;
import es.ubu.inf.edat.pr07_2.AsignacionDepartamental;

public class AsignacionDepartamentalTest {

	static Persona [] curritosArr;
	static List<Trabajador> curritos;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		curritosArr = GeneradorPersonas.soloTrabajadores();
		curritos = new ArrayList<Trabajador>(curritosArr.length);
		
		for(Persona p : curritosArr)
			curritos.add((Trabajador)p);
		
		for (int i=0;i<3;i++){
			int j = (int) Math.floor(Math.random()*curritosArr.length);
			curritos.add((Trabajador) curritosArr[j]);
		}
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAsignaServicio() {
		int numServ = (int) curritos.stream().map(t->t.getServicio()).distinct().count();
		assertEquals(numServ, 
				AsignacionDepartamental.asignaAServicio (curritos));
	}

	@Test
	public void testTamanoServicios() {
		/* TODO Al no devolver un resultado, se debe comprobar de forma visual
		 * por el alumno
		 */
		AsignacionDepartamental.tamanoServicios();
	}
	
	
	@Test
	public void testTamanoServiciosListado() {
		
		String [] consulta = new String[3]; 
		consulta[0] = curritos.get(0).getServicio();
		consulta[1] = curritos.get(1).getServicio();
		consulta[2] = "Servicio Inventado";
		
		int numTrab0 = (int) Arrays.asList(curritosArr).stream().filter(t->((Trabajador) t).getServicio().equals(consulta[0])).count();
		int numTrab1 = (int) Arrays.asList(curritosArr).stream().filter(t->((Trabajador) t).getServicio().equals(consulta[1])).count();
		
		int [] respuesta = AsignacionDepartamental.tamanoServicios(consulta);
		
		assertEquals(numTrab0, respuesta[0]);
		assertEquals(numTrab1, respuesta[1]);
		assertEquals(0, respuesta[2]);
	}
	
	
	@Test
	public void testNumeroTrabajadores() {
		testAsignaServicio();
		assertEquals(8, // ¿Es el equals consistente con el hashCode()?
				AsignacionDepartamental.numTrabajadoresRegistrados());
	}
	
}
