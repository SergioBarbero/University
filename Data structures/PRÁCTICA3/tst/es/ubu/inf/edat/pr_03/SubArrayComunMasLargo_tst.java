package es.ubu.inf.edat.pr_03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author prenedo
 * @author sergio barbero bascones
 */
public class SubArrayComunMasLargo_tst {

	private SubArrayComunMasLargo<Integer> gestor;
	
	@Before
	public void antes(){
	}
	
	@Test
	public void comprobar() {
		
		List<Integer> arrayUno = Arrays.asList(1,2,4,5,3,4,6,7,9,4,5,33,45,63,2,4,44,32);
		List<Integer> arrayDos = Arrays.asList(4,2,1,2,4,5,3,4,6,7,9,4,5,33,45,63,2,4,55,2,9,10);

		
        gestor = new SubArrayComunMasLargo<Integer>(arrayUno,arrayDos); 

		List<Integer> resultado = Arrays.asList(1,2,4,5,3,4,6,7,9,4,5,33,45,63,2,4);

		assertTrue(resultado.equals(gestor.resultadoMetodoA()));
		
		System.out.println("Método 1 funciona correctamente");

		assertTrue(resultado.equals(gestor.resultadoMetodoB()));
		System.out.println("Método 2 funciona correctamente\n");
		
	}
	//List<Integer> array = aleatorio(15);
	
	public List<Integer> listaAleatoria(int tam){
		List<Integer> array = new ArrayList<Integer>();
		Random rnd = new Random();
		for(int i= 0; i< tam; i++){
			array.add((int) (rnd.nextDouble() * 100));
		}
		return array;
	}
	
	
	
	@Test
	public void tiempo(){
		int tam = 10;
		Random rnd = new Random();
		List<Integer> lista1 = listaAleatoria(tam);
		List<Integer> lista2 = listaAleatoria(tam);
		List<Integer> resultado = Arrays.asList(1,2,4,5,3,4,6,7,9,4,5,33,45,63,2,4);
		long tini, tfin;
		
		//Añado la cadena resultado en una posicion aleatoria de las listas
		lista1.addAll((int) (rnd.nextDouble() * tam), resultado); 
		lista2.addAll((int) (rnd.nextDouble() * tam), resultado);
		
		
		System.out.println(lista1);
		System.out.println(lista2);
		
		gestor = new SubArrayComunMasLargo<Integer>(lista1, lista2); 
		
		tini = System.currentTimeMillis();
		assertTrue(resultado.equals(gestor.resultadoMetodoA()));
		tfin = System.currentTimeMillis();
		
		System.out.println("-- Aleatorio método A --");
		System.out.println("Tamaño: "+ tam + ", Tiempo: " + (tfin - tini));
		
		tini = System.currentTimeMillis();
		assertTrue(resultado.equals(gestor.resultadoMetodoB()));
		tfin = System.currentTimeMillis();
		
		System.out.println("-- Aleatorio método B --");
		System.out.println("Tamaño: "+ tam + ", Tiempo: " + (tfin - tini)); 
	}
	
	@Test
	public void matrizVacia(){
		List<Integer> array1 = new ArrayList<Integer>(Collections.nCopies(20, null));
		List<Integer> array2 = new ArrayList<Integer>(Collections.nCopies(20, null));
		
		gestor = new SubArrayComunMasLargo<Integer>(array1, array2); 
		assertTrue(listaNull(20, gestor.resultadoMetodoA()));
		System.out.println("-- ArrayList de null --");
		System.out.println(gestor.resultadoMetodoA());
	}
	
	public boolean listaNull(int i, List<Integer> lista){
		for(i = 0; i < lista.size(); i++ ){
			if(lista.get(i) != null)
				return false;
		}
		return true;
	}
	
}
