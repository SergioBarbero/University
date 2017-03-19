/**
 * 
 */
package es.ubu.inf.edat.pr_04;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author prenedo
 * @author bbaruque
 * @author sergio barbero báscones
 *
 */
public class OperacionesListas_A_G2 {

	/**
	 * GRUPO 2 Metodo: Comprueba sub-listas.
	 * 
	 * Descripcion: Comprueba si todos los elementos menos de la ListaB estan en
	 * el mismo prden en la lista1 y la lista2 a partir de las posiciones de
	 * inicio indicadas en ini1 e ini2
	 * 
	 * @param lista1 
	 * 			  lista1
	 * @param lista2
	 *            lista2
	 * @param listaB
	 *            lista patron
	 * @param ini1
	 *            posicion inicial de la lista1
	 * @param ini2
	 *            posicion inicial de la lista 2
	 * @return boolResultado 
	 * 			  booleano que indica si existe esa subcadena en
	 *            ambas listas
	 */
	static public <E> boolean compruebaSubListas(List<E> lista1,
			List<E> lista2, List<E> listaB, int ini1, int ini2) {
		if (lista2.subList(ini2, ini2 + listaB.size()).equals(
				lista1.subList(ini1, ini1 + listaB.size())))
			return true;
		else
			return false;
	}

	/**
	 * Método: Comprueba Secuencia Patrón.
	 * 
	 * Descripción: Comprueba si los elementos indicados dentro de una lista
	 * �Patr�n� esta, siguiendo la misma frecuencia en la lista de datos.
	 * Ejemplos: Datos 3,1,2,4,1,7,2,9,3,12,23,44,2,1,6,4,9 Patrón 3,1,7,9,2,6
	 * Resultado Verdadero
	 * 
	 * @param listaD, 
	 * 			lista a buscar
	 * @param listaP
	 * 			lista patron
	 * @return bool 
	 * 			booleano que indica si existen los caracteres de la listaP en listaD
	 */
	@SuppressWarnings("unchecked")
	static public <E> boolean compruebaSecuenciaPatron(List<E> listaD,
			List<E> listaP) {
		int i = 0;
		for (int j = 0; j < listaD.size(); j++) {
			if (listaD.get(j) == listaP.get(i)) {
				i++;
				if (i == listaP.size())
					return true;
			}
		}
		return false;
	}
}
