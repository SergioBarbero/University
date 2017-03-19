package es.ubu.inf.edat.pr12.g1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Arrays;

public class EmparejadorDatos {

	/**
	 * Método que devuelva, a partir de dos vectores de enteros facilitados, sus elementos emparejados.  
	 * La condición a cumplir en este cálculo es que los resultados se deberán emparejar de manera que,
	 *  en la primera pareja, los elementos serán el mayor elemento del primer vector y el menor elemento 
	 *  del segundo vector; en la segunda pareja, serán el segundo elemento más grande del vector con el 
	 *  siguiente menor del otro vector, etc. Se debe tener en cuenta que las listas no tienen por qué 
	 *  estar ordenadas o tener el mismo número de elementos.
	 *  
	 *  En este caso se resolverá empleando como estructuras auxiliares, la clase PriorityQueue
	 *  
	 *  Ejemplo:
	 *  Entrada: L1:[5, 8, 3, 2]; L2: [6, 9, 2, 5, 12]
	 *  Salida: {[8,2], [5,5], [3,6], [2,9], [1,12] }
	 * 
	 * @param lista1 - Primera lista con los elementos a emparejar 
	 * @param lista2 - Segunda lista con los elementos a emparejar
	 * @return - Lista con arrays de 2 elementos, correctamente emparejados
	 */
	
	public static List<Integer[]> emparejaElementos_ColaPrioridad(Integer[] lista1, Integer[] lista2){
		PriorityQueue<Integer> prioMayor = new PriorityQueue<Integer>(lista1.length);
		PriorityQueue<Integer> prioMenor = new PriorityQueue<Integer>(lista2.length, Collections.reverseOrder());
		prioMayor.addAll(Arrays.asList(lista1));
		prioMenor.addAll(Arrays.asList(lista2));
		
		List<Integer[]> emparejamientos = new ArrayList<Integer[]>();
		Integer[] elemento = new Integer[2];
		while(!prioMayor.isEmpty() && !prioMenor.isEmpty()){
			elemento[0] = prioMayor.remove();
			elemento[1] = prioMenor.remove();
			emparejamientos.add(elemento);
			System.out.println(elemento);
		}
		if(prioMayor.isEmpty() && prioMenor.isEmpty()){
			return emparejamientos;
		}
		
		while(prioMayor.isEmpty() || prioMenor.isEmpty()){
			if(!prioMayor.isEmpty()){
				elemento[0] = prioMayor.remove();
				elemento[1] = 1;
				emparejamientos.add(elemento);
				System.out.println(elemento);
			}else{
				elemento[1] = prioMenor.remove();
				elemento[0] = 1;
				emparejamientos.add(elemento);
				System.out.println(elemento);
				
			}	
		}
	
		return emparejamientos;
	}

	/**
	 * La condición a cumplir en este cálculo es que los resultados se deberán emparejar de manera que,
	 *  en la primera pareja, los elementos serán el mayor elemento del primer vector y el menor elemento 
	 *  del segundo vector; en la segunda pareja, serán el segundo elemento más grande del vector con el 
	 *  siguiente menor del otro vector, etc. Se debe tener en cuenta que las listas no tienen por qué 
	 *  estar ordenadas o tener el mismo número de elementos.
	 *  
	 *  En este caso se resolverá empleando como estructuras auxiliares, una implementación de List
	 *  
	 *  Ejemplo:
	 *  Entrada: L1:[5, 8, 3, 2]; L2: [6, 9, 2, 5, 12]
	 *  Salida: {[8,2], [5,5], [3,6], [2,9], [12,0] }
	 * 
	 * @param lista1 - Primera lista con los elementos a emparejar 
	 * @param lista2 - Segunda lista con los elementos a emparejar
	 * @return - Lista con arrays de 2 elementos, correctamente emparejados
	 */
	
	public static List<Integer[]> emparejaElementos_ListaOrdenada(Integer[] lista1, Integer[] lista2){
		// TODO - Completar el método
		return null;
	}
	
	// TODO - Contestar a las siguientes preguntas:
	
	/*
	 * ¿Qué diferencias se pueden apreciar en un método y en el otro? 
	 * ¿Cuál es la complejidad algorítmica de cada uno? ¿A qué se puede deber?
	 * Incluye un razonamiento sobre esas observaciones.
	 */
	
}
