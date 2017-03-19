package es.ubu.inf.edat.pr10;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Descripcion: Implementacion de un conjuntoLRU, el cual consiste en mantener
 * un registro de los ultimos datos introducidos y accedidos
 * 
 * @author Sergio Barbero Bascones
 *
 * @param <T>
 *            tipo de dato introducido en el conjuntoLRU
 */
public class ConjuntoLRU<T> extends AbstractSet<T> implements SortedSet<T> {
	int capacidad;
	int contador;
	public HashMap<T, Integer> tabla;

	/**
	 * Descripcion: Constructor de la clase, se le pasa un entero que indica el
	 * tamano maximo
	 * 
	 * @param max
	 *            maximo elementos sobre los que tenemos un registro
	 */
	public ConjuntoLRU(int max) {
		capacidad = max;
		contador = 0;
		tabla = new LinkedHashMap<T, Integer>();
	}

	/**
	 * Descipcion: añade un nuevo elemento al conjuntoLRU
	 * 
	 * @param e
	 *            elemento a anadir
	 * @return false si ya existe el elemento en el conjunto, true si es un
	 *         elemento nuevo
	 */
	@Override
	public boolean add(T e) {
		contador++;
		if (tabla.containsKey(e)) {
			tabla.replace(e, contador);
			return false;
		} else {
			if (tabla.size() < capacidad) {
				tabla.put(e, contador);
			} else {
				tabla.remove(this.first());
				tabla.put(e, contador);
			}
			return true;
		}
	}

	/**
	 * Descripcion: Elimina un elemento del conjuntoLRU
	 * 
	 * @param e
	 *            elemento a eliminar
	 * @return true si ha sido eliminado, false si no ha sido eliminado
	 */
	@Override
	public boolean remove(Object e) {
		if (tabla.containsKey(e)) {
			contador--;
			tabla.remove(e);
			return true;
		} else
			return false;
	}

	/**
	 * Descripcion: Devuelve un comparador para los elementos de tipo T
	 * 
	 * @return instancia del comparador
	 */
	@Override
	public Comparator<? super T> comparator() {
		SortedSet<T> valores = (SortedSet<T>) this.ordenaMapa().values();
		return valores.comparator();
	}

	/**
	 * Descripcion: A partir de dos elementos T del conjunto devuelve el subset
	 * comprendido entre ellos
	 * 
	 * @param fromElement
	 *            elemento inicial
	 * @param toElement
	 *            elemento final
	 * @return subset comprendido
	 */
	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		SortedMap<Integer, T> prioridades = this.ordenaMapa();
		Integer desde = tabla.get(fromElement);
		Integer hasta = tabla.get(toElement);
		SortedSet<T> sub = new TreeSet<T>();
		sub.addAll(prioridades.subMap(desde, hasta).values());
		return sub;
	}

	/**
	 * Descripcion: Devuelve un subset desde el elemento mas antiguo hasta el
	 * pasado por argumento
	 * 
	 * @param toElement
	 *            elemento final
	 * @return subset
	 */
	@Override
	public SortedSet<T> headSet(T toElement) {
		SortedMap<Integer, T> prioridades = this.ordenaMapa();
		Integer valor = tabla.get(toElement);
		SortedSet<T> sub = new TreeSet<T>();
		sub.addAll(prioridades.headMap(valor).values());
		return sub;
	}

	public SortedMap<Integer, T> ordenaMapa() {
		SortedMap<Integer, T> prioridades = new TreeMap<Integer, T>();
		Iterator<T> it1 = tabla.keySet().iterator();
		Iterator<Integer> it2 = tabla.values().iterator();
		while (it1.hasNext()) {
			prioridades.put(it2.next(), it1.next());
		}

		return prioridades;
	}

	/**
	 * Descripcion: Devuelve un subset desde el elemento proporcionado hasta el
	 * mas reciente
	 * 
	 * @param fromElement
	 *            elemento inicial
	 * @return subset
	 */
	@Override
	public SortedSet<T> tailSet(T fromElement) {
		SortedMap<Integer, T> prioridades = this.ordenaMapa();
		Integer valor = tabla.get(fromElement);
		SortedSet<T> sub = new TreeSet<T>();
		sub.addAll(prioridades.tailMap(valor).values());
		return sub;
	}

	/**
	 * Descripcion: Devuelve el elemento mas antiguo del conjuntoLRU
	 * 
	 * @return elemento mas antiguo T
	 */
	@Override
	public T first() {
		SortedMap<Integer, T> prioridades = this.ordenaMapa();
		return prioridades.get(prioridades.firstKey());
	}

	/**
	 * Descripcion: Devuelve el elemento mas reciente del conjuntoLRU
	 * 
	 * @return elemento mas reciente T
	 */
	@Override
	public T last() {
		SortedMap<Integer, T> prioridades = this.ordenaMapa();
		return prioridades.get(prioridades.lastKey());
	}

	/**
	 * Descripcion: Devuelve una instancia del iterador del conjuntoLRU
	 * 
	 * @return instancia del iterador
	 */
	@Override
	public Iterator<T> iterator() {
		return new IteradorLRU();
	}

	/**
	 * Descripcion: Limpia el conjunto de elementos
	 */
	@Override
	public void clear() {
		tabla.clear();
	}

	/**
	 * Descripcion: Devuelve el tamano del conjuntoLRU
	 * 
	 * @return tamano del conjunto
	 */
	@Override
	public int size() {
		return tabla.size();
	}

	/**
	 * Descripcion: Iterador para el conjuntoLRU
	 * 
	 * @author Sergio Barbero Bascones
	 *
	 */
	public class IteradorLRU implements Iterator<T> {
		int pos = 0;
		List<T> lista = new ArrayList<T>(ordenaMapa().values());

		/**
		 * Descripcion: Nos muestra si un elemento tiene elemento siguiente o no
		 * 
		 * @return false si no tiene elemento siguiente, true en caso contrario
		 */
		@Override
		public boolean hasNext() {
			if (pos < tabla.size())
				return true;
			else
				return false;
		}

		/**
		 * Descripcion: Nos muestra el elemento siguiente del conjunto
		 * 
		 * @return elemento siguiente del conjunto
		 */
		@Override
		public T next() {
			ConjuntoLRU.this.tabla.replace(lista.get(pos), ++contador);
			return lista.get(pos++);
		}
	}
}