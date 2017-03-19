package es.ubu.inf.edat.pr_04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import es.ubu.inf.edat.pr_04.OperacionesListas_A_G2;

public class OperacionesListas_Test_A_G2 {


	@Test
	public void testCompruebaSubListas2() {

		List<Integer> lista1 = Arrays.asList(3,5,1,2,4,11,17,12, 9,13,12,23,44, 2, 1, 6, 4, 9);
		List<Integer> lista2 = Arrays.asList(3,1,2,4, 1, 7,12,19,23,12,23,44, 2, 1, 6, 4, 9);
		List<Integer> listaB = Arrays.asList(1,2,4);

		int ini1=2;
		int ini2=1;
		
		assertTrue(OperacionesListas_A_G2.compruebaSubListas(lista1, lista2, listaB, ini1, ini2));
		assertFalse(OperacionesListas_A_G2.compruebaSubListas(lista1, lista2, listaB, ini1, 2));
	}

	@Test
	public void testSecuenciaPatron() {
		List<Integer> listaD = Arrays.asList(3,1,2,4,1,7,2,9,3,12,23,44,2,1,6,4,9);
		List<Integer> listaP = Arrays.asList(3,1,7,9,2,6);
		List<Integer> listaE = Arrays.asList(3,1,7,9,2,6,2);
		
		assertTrue(OperacionesListas_A_G2.compruebaSecuenciaPatron(listaD, listaP));
		assertFalse(OperacionesListas_A_G2.compruebaSecuenciaPatron(listaD, listaE));
	}


}
