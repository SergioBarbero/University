package es.ubu.inf.edat.pr12.g1;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.inf.edat.datos.GeneradorEnteros;
import es.ubu.inf.edat.pr12.g1.EmparejadorDatos;

public class TestEmparejadorDatos {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmpareja_cola() {
		Integer[] l1 = {5, 8, 3, 2};
		Integer[] l2 = {6, 9, 2, 5, 12};
		
		Integer[][] esp = {{8,2}, {5,5}, {3,6}, {2,9}, {0,12}};
		List<Integer[]> esperado = Arrays.asList(esp);
		
		List<Integer[]>emparejados = EmparejadorDatos.emparejaElementos_ColaPrioridad(l1, l2);

		assertEquals(esperado.size(), emparejados.size());
		
		for(int i=0; i<esperado.size(); i++){
			assertArrayEquals(esperado.get(i), emparejados.get(i));
		}
		
	}

	@Test
	public void testEmpareja_lista() {
		Integer[] l1 = {5, 8, 3, 2};
		Integer[] l2 = {6, 9, 2, 5, 12};
		
		Integer[][] esp = {{8,2}, {5,5}, {3,6}, {2,9}, {0,12}};
		List<Integer[]> esperado = Arrays.asList(esp);
		
		List<Integer[]>emparejados = EmparejadorDatos.emparejaElementos_ListaOrdenada(l1, l2);

		assertEquals(esperado.size(), emparejados.size());
		
		for(int i=0; i<esperado.size(); i++){
			assertArrayEquals(esperado.get(i), emparejados.get(i));
		}
		
	}
	
/*	@Test
	public void testRendimiento() {

		int maxSize = 100000;
		int numMedidas = 10;
		double ini, fin, elapsed;
		
		for(int j=1; j<maxSize; j = j + (maxSize/numMedidas)){
		
			Integer [] array = new Integer[j];
			Integer [] l1 = GeneradorEnteros.listaAleatoria(j).toArray(array);
			Integer [] l2 = GeneradorEnteros.listaAleatoria(j).toArray(array);
			
			ini = System.currentTimeMillis();
			EmparejadorDatos.emparejaElementos_ColaPrioridad(l1, l2);
			fin = System.currentTimeMillis();
			elapsed = fin-ini;
			System.out.println("empareja (PriorityQueue),tamaño,"+j+",tiempo,"+elapsed);

			ini = System.currentTimeMillis();
			EmparejadorDatos.emparejaElementos_ListaOrdenada(l1, l2);
			fin = System.currentTimeMillis();
			elapsed = fin-ini;

			System.out.println("empareja (Lista Ordenada),tamaño,"+j+",tiempo,"+elapsed);
					
		}
		
	}*/
	
	
}
