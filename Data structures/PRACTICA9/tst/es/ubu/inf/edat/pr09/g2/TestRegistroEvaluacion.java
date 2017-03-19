package es.ubu.inf.edat.pr09.g2;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.junit.Test;

import es.ubu.inf.edat.datos.Alumno;

public class TestRegistroEvaluacion {

	private RegistroEvaluacion clase = null;
	private NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
	private DecimalFormat df = (DecimalFormat)nf;

	@org.junit.Before
	public void antes(){
		clase = new RegistroEvaluacion();
	}

	@org.junit.After
	public void despues(){
		clase.limpiaListado();
	}

	/**
	 * Metodo que permite la comprobaci�n de la inserci�n de nuevas notas
	 */
	@Test
	public void insertaNotas(){

		Alumno ret = null;
		ret = clase.anadeNota("11111111A", (float) 5.7);
		assertTrue ("Error al a�adir",(float) 5.7 == ret.getMedia()); 
		ret = clase.anadeNota("22222222B", (float) 3.2);
		assertTrue ("Error al a�adir",(float) 3.2 == ret.getMedia());
		ret = clase.anadeNota("33333333C", (float) 8.5);
		assertTrue ("Error al a�adir",(float) 8.5 == ret.getMedia());
		ret = (clase.anadeNota("33333333C", (float) 7.7));
		assertTrue ("Error al a�adir",(float) 8.1 == ret.getMedia());

		assertTrue ("Error al calcular numero de alumnos",3 == clase.numeroAlumnos());

	}

	/**
	 * Metodo que permite la comprobacion de las medias sobre los alumnos ya insertados
	 */
	@Test
	public void calculaMedias(){

		inicializaEvaluaciones();

		assertTrue ("Error al calcular media",(float) 2.3 == Float.parseFloat(df.format(clase.devuelveNota("11111111A"))));
		assertTrue ("Error al calcular media",(float) 3.55 == Float.parseFloat(df.format(clase.devuelveNota("22222222B"))));
		assertTrue ("Error al calcular media",(float) 5.9 == Float.parseFloat(df.format(clase.devuelveNota("33333333C"))));
		assertTrue ("Error al calcular media",(float) 7.35 == Float.parseFloat(df.format(clase.devuelveNota("44444444D"))));
		assertTrue ("Error al calcular media", (float) 5.7 == Float.parseFloat(df.format(clase.devuelveNota("55555555E"))));
		
	}

	/***
	 * Metodo que comprueba la obtencion del listados de alumnos (únicos).
	 * Se comprueba que aparezcan ordenados en funcion de su DNI.
	 */
	@Test
	public void obtenerListadoPorNota(){

		List<Alumno> lista;
		inicializaEvaluaciones();

		// Listado completo (ordenado por Nota)
		lista = clase.listadoNotas();
		assertEquals (5, lista.size());

		for (int i=1; i<lista.size(); i++){
			float media1 = lista.get(i-1).getMedia();
			float media2 = lista.get(i).getMedia();
			assertTrue (media1 > media2); 
		}
		
	}

	/**
	 * Metodo que comprueba la obtención de un rango de alumnos dentro de la clase.
	 */
	@Test
	public void obtenerListadoPorDNIs(){
		
		inicializaEvaluaciones();
		
		List<Alumno> rango = clase.listadoRangoDNI("22222222B", "44444444D");
		
		assertEquals(2, rango.size());
		assertTrue(rango.contains(new Alumno("22222222B")));
		assertTrue(rango.contains(new Alumno("33333333C")));

	}
		
	/**
	 * Metodo auxiliar para los tests de las clases.
	 */
	private void inicializaEvaluaciones(){
		
		clase.anadeNota("22222222B", (float) 3.2);
		clase.anadeNota("33333333C", (float) 9.1);
		clase.anadeNota("44444444D", (float) 7.9);
		clase.anadeNota("11111111A", (float) 2.3);
		clase.anadeNota("33333333C", (float) 2.7);
//		clase.anadeNota("11111111A", (float) 6.2);
		clase.anadeNota("22222222B", (float) 3.9);
		clase.anadeNota("44444444D", (float) 6.8);
		clase.anadeNota("55555555E", (float) 5.7);
		clase.anadeNota("55555555E", (float) 5.7);
		clase.anadeNota("55555555E", (float) 5.7);
		clase.anadeNota("55555555E", (float) 5.7);
		
		assert 5 == clase.numeroAlumnos();
		
	}

}
