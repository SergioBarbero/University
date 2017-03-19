package es.ubu.inf.edat.pract08;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
/**
 * 
 * @author Sergio Barbero Báscones
 *
 * @param <K> clave
 * @param <V> valor
 */
public class MapaDispersionAbierta<K, V> extends AbstractMap<K, V> {
	/**
	 * Descripcion: referencia al tamano de cubetas
	 */
	int tam;
	/**
	 * Descripcion: Referencia al numero de cubetas
	 */
	int numCub;

	/**
	 * Descripcion: Referencia a la tabla base
	 */
	public ArrayList<EntradaMultiple<K, V>> tabla;

	/**
	 * Descripcion: Crea un mapa de dispersion abierta
	 * 
	 * @param tamanoCubeta
	 *            tamano de cubetas
	 * @param numeroCubeta
	 *            numero de cubetas
	 */
	public MapaDispersionAbierta(int tamanoCubeta, int numeroCubeta) {
		this.tam = tamanoCubeta;
		this.numCub = numeroCubeta;
		tabla = new ArrayList<EntradaMultiple<K, V>>(tamanoCubeta);

		for (int i = 0; i < tamanoCubeta; i++) {
			tabla.add(new EntradaMultiple<K, V>());
		}
	}

	/**
	 * 
	 * @return numero de cubetas
	 */
	public int getNumberBuckets() {
		return numCub;
	}

	/**
	 * Descripcion: Clase entrada multiple, contiene dos arrays, uno que guarda
	 * las claves y otro los valores
	 * 
	 * @author Sergio Barbero Báscones
	 * @param <K>
	 *            clave del mapa
	 * @param <V>
	 *            valor del mapa
	 */
	public class EntradaMultiple<K, V> {

		/**
		 * Descripcion: referencia al arraylist de claves
		 */
		private ArrayList<K> claves = new ArrayList<K>(numCub);

		/**
		 * Descripcion: Referencia al arraylist de valores
		 */
		private ArrayList<V> valores = new ArrayList<V>(numCub);

		/**
		 * Descripcion: devuelve el arraylist de claves de una EntradaMultiple
		 * 
		 * @return arraylist de claves
		 */
		public ArrayList<K> keySet() {
			return claves;
		}

		/**
		 * Descripcion: Devuelve el arraylist de valores de una EntradaMultiple
		 * 
		 * @return Arraylist de valores
		 */
		public ArrayList<V> valueSet() {
			return valores;
		}
	}

	/**
	 * Descripcion: Clase que representa a una entrada individual (clave-valor)
	 * 
	 * @author Sergio Barbero Báscones
	 *
	 * @param <K>
	 *            clave
	 * @param <V>
	 *            valor
	 */
	public class Entrada<K, V> extends AbstractMap.SimpleEntry<K, V> {
		/**
		 * Descripcion: Crea una entrada individual clave-valor
		 * 
		 * @param key
		 *            clave
		 * @param value
		 *            valor
		 */
		public Entrada(K key, V value) {
			super(key, value);
		}
	}

	/**
	 * Descripcion: Inserta un elemento clave-valor al mapa
	 * @param key clave
	 * @param value valor
	 * @return valor reemplazado o null si el valor insertado es nuevo
	 */
	public V put(K key, V value) {
		int pos = key.hashCode() / numCub;
		V valor = this.get(key);
		if (pos < tam) {
			int indice = tabla.get(pos).keySet().indexOf(key);
			if (indice == -1) {
				tabla.get(pos).valueSet().add(value);
				tabla.get(pos).keySet().add(key);
			} else {
				tabla.get(pos).valueSet().set(indice, value);
				tabla.get(pos).keySet().set(indice, key);
			}
		} else { //Mapa demasiado pequeno, redimensiona y vuelve a intentarlo
			this.redimensionar();
			this.put(key, value);
		}
		return valor;
	}
	
	/**
	 * Descripcion: Redimensiona el mapa a un 150%
	 */
	public void redimensionar() {
		this.numCub *= 1.5;
	}

	/**
	 * Descripcion: Elimina un par clave-valor del mapa
	 * @return valor eliminado
	 */
	@Override
	public V remove(Object key) {
		Iterator it1 = tabla.iterator();
		V valor = null;
		int i = 0;
		while (it1.hasNext()) {
			it1.next();
			int indice = tabla.get(i).keySet().indexOf(key);
			if (indice != -1) {
				valor = tabla.get(i).valueSet().get(indice);
				tabla.get(i).keySet().remove(indice);
				tabla.get(i).valueSet().remove(indice);
			}
			i++;

		}
		return valor;
	}

	/**
	 * Descripcion: Devuelve el valor de una clave
	 * @return valor devuelto o null si no existe tal clave
	 */
	@Override
	public V get(Object key) {
		for (int i = 0; i < tabla.size(); i++) {
			int indice = tabla.get(i).keySet().indexOf(key);
			if (indice != -1) {
				return tabla.get(i).valueSet().get(indice);
			}
		}
		return null;
	}
	/**
	 * Descripcion: Devuelve un Set de entradas clave-valor
	 * @return Set de entradas
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<Entry<K, V>>();
		Iterator it1 = tabla.iterator();
		int i = 0;
		while (it1.hasNext()) {
			it1.next();
			Iterator it2 = tabla.get(i).keySet().iterator();
			int j = 0;
			while (it2.hasNext()) {
				it2.next();
				K clave = tabla.get(i).keySet().get(j);
				V valor = tabla.get(i).valueSet().get(j);
				Entrada<K, V> entry = new Entrada<K, V>(clave, valor);
				set.add(entry);
				j++;
			}
			i++;
		}
		return set;
	}

	/**
	 * Descripcion: Devuelve un set de claves
	 * @return set de claves
	 */
	@Override
	public Set<K> keySet() {
		ArrayList<K> claves = new ArrayList<K>();
		Iterator it = tabla.iterator();
		int i = 0;
		while (it.hasNext()) {
			Iterator it2 = tabla.get(i).keySet().iterator();
			it.next();
			int j = 0;
			while (it2.hasNext()) {
				it2.next();
				claves.add(tabla.get(i).keySet().get(j));
				j++;
			}
			i++;
		}
		return new HashSet<K>(claves);
	}
	
	/**
	 * Devuelve una coleccion de valores
	 * @return coleccion de valores
	 */
	@Override
	public Collection<V> values() {
		ArrayList<V> valores = new ArrayList<V>();
		Iterator it = tabla.iterator();
		int i = 0;
		while (it.hasNext()) {
			Iterator it2 = tabla.get(i).valueSet().iterator();
			it.next();
			int j = 0;
			while (it2.hasNext()) {
				it2.next();
				valores.add(tabla.get(i).valueSet().get(j));
				j++;
			}
			i++;
		}
		return new HashSet<V>(valores);
	}
	
	/**
	 * Descripcion: Devuelve el tamano total del mapa
	 * @return tamano del mapa
	 */
	@Override
	public int size() {
		int tamano = 0;
		int i = 0;
		Iterator it = tabla.iterator();
		while (it.hasNext()) {
			it.next();
			tamano += tabla.get(i).keySet().size();
			i++;
		}
		return tamano;
	}
}
