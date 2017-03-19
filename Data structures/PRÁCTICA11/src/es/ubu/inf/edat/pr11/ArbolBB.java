package es.ubu.inf.edat.pr11;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Descripcion: Implementacion de un arbol binario de busqueda
 * 
 * @author Sergio Barbero Báscones
 *
 * @param <T>
 *            tipo de dato introducido en el arbol
 */
public class ArbolBB<T> extends AbstractSet<T> {

	/**
	 * Descripcion: Referencia al nodo raiz del arbol
	 */
	Nodo<T> raiz;
	/**
	 * Descripcion: Referencia a la coleccion de elementos introducidos
	 */
	Collection<T> datos;
	/**
	 * Descripcion: Referencia al comparador
	 */
	Comparator<T> comparador;
	/**
	 * Descripcion: Referencia al recorrido inOrder
	 */
	ArrayList<T> inOrden = new ArrayList<T>();

	/**
	 * Descripcion: Constructor de la clase para datos comparables, se le pasa
	 * una coleccion de elementos
	 * 
	 * @param datos
	 *            Coleccion de elementos introducidos en el arbol
	 */
	public ArbolBB(Collection<T> datos) {
		if (!datos.isEmpty()) {
			this.datos = datos;
			Comparable<T> elemento = (Comparable<T>) datos.stream().findFirst()
					.get();
			this.raiz = new Nodo<T>((T) elemento);
			Collection<T> collIz = datos.stream()
					.filter(e -> elemento.compareTo(e) > 0)
					.collect(Collectors.toCollection(LinkedHashSet::new));
			Collection<T> collDer = datos.stream()
					.filter(e -> elemento.compareTo(e) < 0)
					.collect(Collectors.toCollection(LinkedHashSet::new));
			raiz.izquierda = new ArbolBB<T>(collIz);
			raiz.derecha = new ArbolBB<T>(collDer);
		}
	}

	/**
	 * Descripcion: Constructor de la clase para datos no comparables
	 * 
	 * @param datosNC
	 *            Coleccion de datos no comparables
	 * @param comparador
	 *            Comparador proporcionado para comparar elementos entre si
	 */
	public ArbolBB(Collection<T> datosNC, Comparator<T> comparador) {

		if (!datosNC.isEmpty()) {
			this.datos = datosNC;
			this.comparador = comparador;
			T elemento = datos.stream().findFirst().get();
			this.raiz = new Nodo<T>((T) elemento);
			Collection<T> collIz = datos.stream()
					.filter(e -> comparador.compare(elemento, e) > 0)
					.collect(Collectors.toCollection(LinkedHashSet::new));
			Collection<T> collDer = datos.stream()
					.filter(e -> comparador.compare(elemento, e) < 0)
					.collect(Collectors.toCollection(LinkedHashSet::new));
			raiz.izquierda = new ArbolBB<T>(collIz, comparador);
			raiz.derecha = new ArbolBB<T>(collDer, comparador);
		}
	}

	/**
	 * Descripcion: Metodo que nos muestra si el arbol es comparable o no
	 * 
	 * @return false si no es comparable, true en caso contrario
	 */
	public boolean comparable() {
		if (this.comparador != null)
			return false;
		else
			return true;
	}

	/**
	 * Descripcion: Crea una instancia de un iterador de arbol binario de
	 * busqueda
	 * 
	 * @return new IteradorArbol() instancia del iterador del arbolBB
	 */
	@Override
	public Iterator<T> iterator() {
		return new IteradorArbolBB();
	}

	/**
	 * Descripcion: Metodo que devuelve el tamano de nuestro arbol
	 * 
	 * @return tamano del arbol
	 */
	@Override
	public int size() {
		Set<T> sinRepetidos = new LinkedHashSet<T>(datos);
		return sinRepetidos.size();
	}

	/**
	 * Descripcion: Devuelve la altura a la que se encuentra un elemento con
	 * respecto al elemento inferior del arbol el cual esta relacionado con el
	 * 
	 * @param elemento
	 *            Elemento a calcular su altura en el arbol
	 * @return altura del elemento en el arbol, en caso de que el elemento no
	 *         haya sido encontrado devuelve -1
	 */
	public int altura(T elemento) {
		Comparable<T> e = (Comparable<T>) elemento;
		Nodo<T> actual = raiz;
		int altura = 0;
		int comp;
		while (actual != null) {
			if (comparable() == true)
				comp = e.compareTo(actual.dato);
			else
				comp = comparador.compare(elemento, actual.dato);

			if (comp == 0) {
				while (actual != null) {
					if (actual.izquierda.raiz == null
							&& actual.derecha.raiz == null) {
						return altura;

					} else {
						if (actual.izquierda.raiz != null) {
							actual = actual.izquierda.raiz;
							altura++;
						} else {
							actual = actual.derecha.raiz;
							altura++;
						}
					}

				}
			} else {
				if (comp > 0)
					actual = actual.derecha.raiz;
				else
					actual = actual.izquierda.raiz;
			}
		}
		return -1;
	}

	/**
	 * Descripcion: Metodo que devuelve una lista con el recorrido inOrden del
	 * arbol
	 * 
	 * @return ArrayList del recorrido inOrden
	 */
	public ArrayList<T> inOrden() {
		if (raiz != null) {
			Stack<Nodo<T>> pila = new Stack<Nodo<T>>();
			Nodo<T> actual = raiz;

			while (!pila.isEmpty() || actual != null) {
				if (actual != null) {
					pila.push(actual);
					actual = actual.izquierda.raiz;
				} else {
					Nodo<T> n = pila.pop();
					inOrden.add(n.dato);
					actual = n.derecha.raiz;
				}
			}
		}
		return inOrden;
	}

	/**
	 * Descripcion: Metodo que calcula la profundidad de un elemento con
	 * respecto a la raiz del arbol
	 * 
	 * @param elemento
	 *            Elemento a buscar en el arbol
	 * @return profundidad del elemento
	 */
	public int profundidad(T elemento) {
		Comparable<T> e = (Comparable<T>) elemento;
		Nodo<T> actual = raiz;
		int i = 0;
		int comp;
		while (actual != null) {
			if (comparable() == true)
				comp = e.compareTo(actual.dato);
			else
				comp = comparador.compare(elemento, actual.dato);

			if (comp == 0) {
				return i;
			} else {
				i++;
				if (comp > 0)
					actual = actual.derecha.raiz;
				else
					actual = actual.izquierda.raiz;
			}
		}
		return -1;
	}

	/**
	 * Descripcion: Clase que representa a un nodo dentro del arbol
	 * 
	 * @author Sergio Barbero Bascones
	 *
	 * @param <T>
	 *            dato a introducir en el nodo
	 */
	public class Nodo<T> {
		/**
		 * Descripcion: Referencia al dato del nodo
		 */
		T dato;
		/**
		 * Descripcion: Referencia al subarbol izquierdo del nodo
		 */
		ArbolBB<T> izquierda;
		/**
		 * Descripcion: Referencia al subarbol derecho del nodo
		 */
		ArbolBB<T> derecha;

		/**
		 * Descripcion: Constructor de la clase, inicializa un nodo a partir de
		 * un dato
		 * 
		 * @param dato
		 *            elemento a introducir en el nodo
		 */
		public Nodo(T dato) {
			this.dato = dato;
		}
	}

	/**
	 * Descripcion: Iterador del Arbol binario de busqueda
	 * 
	 * @author Sergio Barbero Bascones
	 *
	 */
	public class IteradorArbolBB implements Iterator<T> {
		/**
		 * Descripcion: Referencia a la posicion del elemento el cual iteramos
		 */
		int indice = 0;

		/**
		 * Descripcion: Metodo que nos devuelve si el arbol tiene elemento
		 * siguiente o no
		 * 
		 * @return true si la lista tiene elemento siguiente, false en caso
		 *         contrario
		 */
		@Override
		public boolean hasNext() {
			if (indice < size())
				return true;
			else
				return false;
		}

		/**
		 * Descripcion: Devuelve el elemento siguiente en la iteraccion
		 * @return Elemento siguiente
		 */
		@Override
		public T next() {
			return inOrden().get(indice++);
		}
	}
}
