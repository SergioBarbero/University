package es.ubu.inf.edat.pr_02;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import es.ubu.inf.edat.pr_02.datos.*;


public class GestorCol_2 implements Collection<Persona>{

	/* Ya que no se conoce un numero fijo de Personas,
	 * se desea que el listado pudiera ser redimensionable en funci�n
	 * de las necesidades de cada mes. --> Se pasar� a un List<Persona>
	 */
	private int nobjetos = 0;
	private Persona[] listado = new Persona[100]; 

	// Listado solo los Personas cuyo g�nero se pase como argumento
	public Persona[] listadoGenero(String genero){

		Persona[] listadoGenero = new Persona[listado.length];

		for(int i=0; i<listado.length; i++){
			Persona actual = listado[i];
			if(actual.getGenero().equalsIgnoreCase(genero))
				listadoGenero[i] = actual;
		}
		return listadoGenero;
	}

	// Solo los Alumnos de un determinado genero
	public List<Persona> listadoAlumnosGenero(String genero){

		ArrayList<Persona> listadoGenero = new ArrayList<Persona>();

		for(int i=0; i<listado.length; i++){
			Persona actual = listado[i];
			if( actual instanceof Alumno && actual.getGenero().equalsIgnoreCase(genero))
				listadoGenero.add(actual);
		}
		return listadoGenero;
	}


	@Override
	public Iterator<Persona> iterator(){
		return new IteradorM();
	}

	protected class IteradorM implements Iterator<Persona>{

		private int posicion;
		private int contadorAlumnos;
		private int totalAlumnos;

		public IteradorM(){
			posicion = 0;
			totalAlumnos = 0;
			for (Persona v:listado){
				if (v instanceof Alumno)
					totalAlumnos ++;
			}
		} 

		@Override
		public boolean hasNext() {
			if (posicion < listado.length && contadorAlumnos < totalAlumnos)
				return true;
			return false;
		}

		@Override
		public Alumno next() {
			Persona actual = null;
			do{
				actual = listado[posicion];
				posicion ++;
			}while(!(actual instanceof Alumno));
			contadorAlumnos++;
			return (Alumno) actual;
		}

	}


////
	
	
////
	
	
	@Override
	public Object[] toArray() {
		this.encoger();
		return (Object[]) listado;
	}

	private void encoger() {
	// TODO Auto-generated method stub
	
}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arg0) {
		this.encoger();
		return (T[]) listado;
	}

	@Override
	public int size() { //Hacer
		return nobjetos;
	}

	@Override
	public boolean isEmpty() { //Hacer
		if(nobjetos == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(Object o) { //Hacer
		for(int i = 0; i<nobjetos; i++){
			if(listado[i].equals(o))
				return true;
		}
		return false;
	}

	@Override
	public boolean add(Persona e) {
		if(listado.length > nobjetos){
			listado[nobjetos] = e;
			nobjetos++;
			return true;
		}else
			return false;
	}

	@Override
	public boolean remove(Object o) { //Elimina un objeto de la lista
		for(int i = 0; i<nobjetos; i++){
			if(listado[i].equals(o)){
				listado[i] = null;
				for(int j=i+1; j<nobjetos; j++, i++){
					listado[i] = listado[j];
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) { 
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Persona> c) {
		boolean retorno = true;
		Iterator<Persona> i = (Iterator<Persona>) c.iterator();
		while(i.hasNext() && retorno){
			this.add(i.next());
		}
		retorno = true;
		return retorno;
	}

	@Override
	public boolean removeAll(Collection<?> c) { //Hacer
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) { 
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() { //Hacer
		nobjetos = 0;
	}

}
