package es.ubu.inf.edat.pr05;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Barbero Báscones Sergio
 *
 *         Descripcion: Clase de la cola
 */
public class ColaCircularEnlazada<E> extends java.util.AbstractQueue<E> {
	/**
	 * Descripcion: referencia al nodo inicial de la cola
	 */
	Nodo<E> inicial;
	/**
	 * Descripcion: referencia al nodo terminal de la cola
	 */
	Nodo<E> terminal;
	/**
	 * Lista que usaremos para representar la cola
	 */
	List<E> lista;

	/**
	 * Descripcion: Constructor de la clase, mantendrá 3 referencias, una a la
	 * lista, la cual instanciaremos como LinkedList, otra al nodo inicial y
	 * otra al nodo terminal, los cuales inicializaremos como null
	 */
	public ColaCircularEnlazada() {
		this.lista = new LinkedList();
		this.inicial = null;
		this.terminal = null;
	};

	/**
	 * Descripcion: Añade un nuevo nodo a la cola
	 * 
	 * @param e
	 *            elemento a añadir a nuestra cola
	 */
	@Override
	public boolean offer(E e) {
		lista.add(e);
		if (lista.get(lista.size() - 1) == null)
			throw new NullPointerException();
		else {
			inicial = new Nodo(lista.get(0));
			if (lista.size() > 1) // Si la lista tiene 2 o mas elementos
									// entonces asignamos el elemento siguiente
									// del inicial
				inicial.siguiente = new Nodo(lista.get(1));
			terminal = new Nodo(e);
			inicial.anterior = terminal;
			terminal.siguiente = inicial;
			return true;
		}
	}

	/**
	 * Descripcion: Sacamos la cabeza de la cola de la misma y la devuelve, si
	 * nuestra cola está vacía nos devolverá null
	 * 
	 * @return dato dato a devolver en el caso de que exista dato a devolver
	 * @return null en caso de que no exista nada que devolver
	 */
	@Override
	public E poll() {
		if (lista.size() > 0) {
			E dato = inicial.getDato();
			if (lista.size() > 1) // Si la lista tiene al menos 2 elementos
									// entonces debemos adjudicar el elemento
									// siguiente como inicial
				inicial = new Nodo(lista.get(1));
			lista.remove(0); // Eliminamos el nodo inicial de la lista
			return dato;
		} else {
			System.out.println("Cola vacía!");
			return null;
		}

	}

	/**
	 * Descripcion: Devuelve la cabeza de la cola, si no existe dato devuelve
	 * una excepción
	 * 
	 * @return lista.get(0) en caso de que exista dato a devolver
	 * @return new NoSuchElementException() lanza una Exception que nos indica
	 *         que no hay elemento que extraer
	 */
	@Override
	public E element() {
		if (lista.size() > 0)
			return lista.get(0);
		else
			throw new NoSuchElementException();
	}

	/**
	 * Descripcion: Devuelve la cabeza de la cola, si no existe dato devuelve
	 * null
	 * 
	 * @return dato a devolver
	 * @return null en el caso de que la cola esté vacía
	 */
	@Override
	public E peek() {
		E dato = this.element();
		if (lista.size() > 0)
			return dato;
		else {
			System.out.println("Cola Vacía");
			return null;
		}
	}

	/**
	 * Descripcion: Elimina la cabeza de la cola de la misma, ademas lo
	 * devuelve, en caso de que la cola esté vacía devuelve una excepción
	 * 
	 * @return dato a extraer
	 * @return new NoSuchElementException() Excepcion a lanzar en caso de cola
	 *         vacía
	 */
	@Override
	public E remove() {
		if (lista.size() > 0) {
			E dato = inicial.getDato();
			if (lista.size() > 1) // Si la lista tiene al menos 2 elementos
									// entonces debemos adjudicar el elemento
									// siguiente como inicial
				inicial = new Nodo(lista.get(1));
			lista.remove(0); // Eliminamos el nodo inicial de la lista
			return dato;
		} else {
			System.out.println("Cola vacía!");
			throw new NoSuchElementException();
		}
	}

	/**
	 * Descripcion: Devuelve el tamaño de la cola
	 * 
	 * @return lista.size() tamaño de la lista
	 */
	@Override
	public int size() {
		return lista.size();
	}

	/**
	 * Descripcion: Metodo que devuelve un objeto de tipo iterador circular,
	 * sobre el podemos ejecutar las operaciones propias del iterador
	 * 
	 * @return new RoundRobin() instancia de nuestro iterador circular
	 */
	public Iterator<E> circularIterator() {
		return new RoundRobin();
	}

	/**
	 * Descripcion: Metodo que devuelve un objeto de tipo iterador comun
	 * 
	 * @return new ItComun() instancia de iterador comun
	 */
	@Override
	public Iterator<E> iterator() {
		return new ItComun();
	}

	/**
	 * 
	 * Descripcion: Clase RoundRobin, implementa las operaciones del iterador
	 * circular
	 *
	 */
	public class RoundRobin implements Iterator<E> {
		/**
		 * Descripcion: Referencia a la posicion del iterador
		 */
		int pos;

		/**
		 * Descripcion: Constructor de la clase, contiene la posicion del
		 * iterador
		 */
		public RoundRobin() {
			this.pos = -1;
		}

		/**
		 * Descripcion: Método que obtiene el elemento siguiente de un iterador,
		 * al ser circular una vez llegado al ultimo elemento volvera al
		 * elemento inicial
		 */
		@Override
		public E next() {
			pos++;
			return lista.get(pos % (lista.size()));
		}

		/**
		 * Descripcion: Método que dice si un elemento tiene elemento siguiente,
		 * al ser circular esta condicion siempre se va a cumplir
		 */
		@Override
		public boolean hasNext() {
			return true;
		}

		/**
		 * Descripcion: Método que elimina el ultimo elemento de la lista, al
		 * contrario que hace el metodo remove de la clase padre
		 */
		@Override
		public void remove() {
			lista.remove(lista.size() - 1);
		}

	}

	/**
	 * Descripcion: Iterador comun, una vez llegado al final no vuelve al
	 * principio
	 *
	 */
	public class ItComun implements Iterator<E> {
		/**
		 * Descripcion: Referencia a la posicion del iterador
		 */
		int pos;

		public ItComun() {
			this.pos = -1;
		}

		/**
		 * Descripcion: Metodo que devuelve el ultimo elemento de la iteracion,
		 * en caso de no existir mas elementos lanza una excepcion
		 * 
		 * @return lista.get(pos) ultimo elemento de la iteracion
		 * @return new NoSuchElementException() excepcion que indica que no hay
		 *         mas elementos
		 */
		@Override
		public E next() {
			pos++;
			if (hasNext())
				return lista.get(pos);
			else
				throw new NoSuchElementException();
		}

		/**
		 * Descripcion: Método que dice si un elemento tiene elemento siguiente,
		 * en el caso de que haya llegado al final no tendra elemento siguiente
		 * 
		 *  @return boolean true en el caso de que el tamaño de la
		 *         lista sea mayor que la posicion
		 */
		@Override
		public boolean hasNext() {
			return (pos < lista.size());
		}

		/**
		 * Descripcion: Método que elimina el ultimo elemento de la lista, al
		 * contrario que hace el metodo remove de la clase padre
		 */
		@Override
		public void remove() {
			lista.remove(lista.size() - 1);
		}

	}

	/**
	 * 
	 * Descripcion: Clase que representa a cada uno de los nodos de nuestra cola
	 * 
	 * @param <E>
	 *            tipo de elemento introducido en el nodo
	 */
	public class Nodo<E> {
		/**
		 * Descripcion: elemento introducido en el nodo
		 */
		private E elemento;

		/**
		 * Descripcion: Nodo anterior
		 */
		protected Nodo<E> anterior;
		/**
		 * Descripcion: Nodo posterior
		 */
		protected Nodo<E> siguiente;

		/**
		 * Descripcion: Constructor de la clase Nodo, establecemos elemento a e,
		 * anterior y siguiente a null
		 * 
		 * @param e
		 *            Elemento a introducir
		 */
		public Nodo(E e) {
			this.elemento = e;
			this.anterior = null;
			this.siguiente = null;
		}

		/**
		 * Descripcion: Devuelve un nodo a partir de un dato
		 * 
		 * @param e
		 *            Elemento a introducir
		 * @return new Nodo(e) nodo devuelto
		 */
		public Nodo<E> setDato(E e) {
			return new Nodo(e);
		}

		/**
		 * Descripcion: Devuelve el dato de un nodo
		 * 
		 * @return this.elemento elemento devuelto
		 */
		public E getDato() {
			return this.elemento;
		}
	}

}
