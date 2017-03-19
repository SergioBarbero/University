package es.ubu.inf.edat.pr07_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.ubu.inf.edat.datos.personas.*;

public class AsignacionDepartamental {

	// TODO - Crear mapas para almacenar datos. Se recomienda emplear dos mapas
	// diferentes.
	// No se considerará válida aquella solución que base su funcionamiento en
	// Listas.

	static Map<String, List<Trabajador>> miembrosDe = new HashMap<String, List<Trabajador>>();

	/**
	 * Metodo que permite almacenar un listado de trabajadores en otra
	 * estructura que permita realizar facilmente consultas en base a un nombre
	 * de servicio, para obtener los trabajadores que pertenecen al mismo.
	 * 
	 * Se debe tener en cuenta que el listado puede incluir el mismo empleado
	 * repetido. En ese caso solo se deberá almacenar el mismo una vez en la
	 * estructura.
	 * 
	 * @param trabajadores
	 *            - Listado con los trabajadores a almacenar
	 * @return tamaño del mapa
	 */
	public static int asignaAServicio(List<Trabajador> trabajadores) {
		for (int i = 0; i < trabajadores.size(); i++) {
			String servicio = trabajadores.get(i).getServicio();
			List<Trabajador> tporservicio = new ArrayList<Trabajador>();
			if (miembrosDe.containsKey(servicio)) {
				if (!miembrosDe.containsValue(trabajadores.get(i))) {
					tporservicio = miembrosDe.get(servicio);
					tporservicio.add(trabajadores.get(i));
					miembrosDe.put(servicio, tporservicio);
				}
			} else
				miembrosDe.put(servicio, tporservicio);
		}
		return miembrosDe.size();
	}

	/**
	 * Metodo que permita recuperar el listado de los trabajadores que están
	 * asignados a un determinado servicio que se pasa como parámetro.
	 * 
	 * @param servicio
	 *            - cadena de texto con el nombre del servicio que se está
	 *            consultando.
	 * @return - Coleccion conteniendo todos los trabajadores asociados a ese
	 *         servicio
	 */
	public static Collection<Trabajador> trabajadoresEnservicio(String servicio) {
		if (miembrosDe.containsKey(servicio))
			return miembrosDe.get(servicio);
		else
			return null;
	}

	/**
	 * Metodo que permita obtener un listado de los servicios de los que se ha
	 * almacenado trabajadores y el número de trabajadores que se han registrado
	 * en cada servicio.
	 * 
	 * Ejemplo:
	 * 
	 * Dept: UBUEmprende. Num Trab.: 1 Dept: Servicio de Inspeccion. Num Trab.:
	 * 4 ... etc
	 */

	public static void tamanoServicios() {
		for (String m : miembrosDe.keySet()) {
			System.out.println("Dept: " + miembrosDe.keySet().iterator().next().toString()
					+ ". Num Trab.:"
					+ miembrosDe.values().iterator().next().size());
		}

	}

	/**
	 * Metodo que recibe un listado con los nombres de varios servicios y
	 * devuelve un array con el número de trabajadores que se han registrado en
	 * cada uno de los servicios pasados como parámetro.
	 * 
	 * @param serviciosConsultados
	 *            Array con los nombres de los servicios sobre los que se desea
	 *            conocer el tamaño
	 * @return Array de enteros que contiene el numero de trabajadores en cada
	 *         servicio Las posiciones de la respuesta coinciden con las
	 *         posiciones de los nombres del departamento en el array
	 *         introducido como parámetro.
	 */

	public static int[] tamanoServicios(String[] serviciosConsultados) {
		int[] servicios;
		for(int i = 0; i < serviciosConsultados.length; i++){
			//for(miembrosDe.containsKey(serviciosConsultados[i])))
		//	if(miembrosDe.containsKey(serviciosConsultados[i]))
		//		servicios[i] += miembrosDe.get(serviciosConsultados[i].s);
			
				
				
		}
		return null;
	}

	/**
	 * Permite conocer el numero total de trabajadores registrados en el
	 * sistema. Es decir, la suma de trabajadores de todos los servicios.
	 * 
	 * @return - Suma del numero de trabajadores de toda la institucion.
	 */

	public static int numTrabajadoresRegistrados() {
		return 0;
	}

}
