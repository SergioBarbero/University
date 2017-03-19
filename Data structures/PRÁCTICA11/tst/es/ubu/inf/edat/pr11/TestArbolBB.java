package es.ubu.inf.edat.pr11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.inf.edat.datos.coches.Coche;
import es.ubu.inf.edat.datos.coches.ComparaCaballos;
import es.ubu.inf.edat.datos.coches.GeneradorCoches;

public class TestArbolBB {

	ArbolBB<Integer> arbol;
	ArbolBB<Coche> arbol_NoComp;
	List<Integer> datos;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd_NoRepetidos() {
		
		datos = Arrays.asList(375,450,400,350);
		
		arbol = new ArbolBB<Integer>(datos);
		assertEquals(4, arbol.size());
		
		datos = Arrays.asList(375,450,400,350,300,90,125);
		
		arbol = new ArbolBB<Integer>(datos);
		assertEquals(7, arbol.size());
		
	}

	@Test
	public void testAdd_Repetidos() {

		datos = Arrays.asList(375,450,400,350);
		
		arbol = new ArbolBB<Integer>(datos);
		assertEquals(4, arbol.size());
		
		datos = Arrays.asList(375,450,400,350,375,450,400,350);
		
		arbol = new ArbolBB<Integer>(datos);
		assertEquals(4, arbol.size());
	
	}
	
	@Test
	public void testAdd_NoComparablesNoRepetidos() {
		
		List<Coche> datosNC = this.generaNoComparables();

		arbol_NoComp = new ArbolBB<Coche>( datosNC, new ComparaCaballos() );
		
		assertEquals(datosNC.size(), arbol_NoComp.size());
		assertTrue(datosNC.containsAll(arbol_NoComp));
		
	}
	
	@Test
	public void testAdd_NoComparablesRepetidos() {

		List<Coche> datosNC = this.generaNoComparables();
		List<Coche> repetidos = new ArrayList<Coche>(datosNC.size()*2);
		
		repetidos.addAll(datosNC);
		repetidos.addAll(datosNC);
		
		arbol_NoComp = new ArbolBB<Coche>( repetidos, new ComparaCaballos() );
		
		assertEquals(datosNC.size(), arbol_NoComp.size());
		assertTrue(datosNC.containsAll(arbol_NoComp));
		
	}
	
	@Test
	public void testIterator() {

		testAdd_NoRepetidos();
		
		datos = Arrays.asList(375,450,400,350,300,90,125);
		Collections.sort(datos);
		
		Iterator<Integer> it = arbol.iterator();
		
		for(int i = 0; it.hasNext(); i++){
			assertEquals(it.next(), datos.get(i));
		}
		
	}

	@Test
	public void testAltura() {
		
		testAdd_NoRepetidos();
		
		assertEquals(4, arbol.altura(375));
		assertEquals(3, arbol.altura(350));
		assertEquals(2, arbol.altura(300));
		assertEquals(1, arbol.altura(450));
		assertEquals(1, arbol.altura(90));
		assertEquals(0, arbol.altura(400));
		assertEquals(0, arbol.altura(125));
		
	}

	@Test
	public void testProfundidad() {
		
		testAdd_NoRepetidos();
		
		assertEquals(0, arbol.profundidad(375));
		assertEquals(1, arbol.profundidad(350));
		assertEquals(1, arbol.profundidad(450));
		assertEquals(2, arbol.profundidad(300));
		assertEquals(2, arbol.profundidad(400));
		assertEquals(3, arbol.profundidad(90));
		assertEquals(4, arbol.profundidad(125));
		
	}

	protected List<Coche> generaNoComparables(){
		
		List<Coche> datos = GeneradorCoches.listaSecuencial(9);
	
		Collections.swap(datos, 0, 4);
		Collections.swap(datos, 1, 7);
		Collections.swap(datos, 5, 8);

		return datos;
		
	}
	
}
