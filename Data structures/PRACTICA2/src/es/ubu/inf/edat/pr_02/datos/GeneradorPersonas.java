package es.ubu.inf.edat.pr_02.datos;

import java.util.ArrayList;
import java.util.List;

public class GeneradorPersonas {
	final static String[] apellidosA = {"Savador Pérez","Gómez Alonso","García Blanco","Tomé Peña","Sacristan Antunez","Sanchez Aguado"};
	final static String[] nombreA = {"Carmen","Luis","María","Isabel","Laura","Pedro"};
	final static String[] generoA = {"Mujer","Hombre","Mujer","Mujer","Mujer","Hombre"};
	final static String[] nifA = {"A00001002","A00001009","A00001006","A00001001","A00001010","A00001024"};
	final static int[] expedienteA = {1002,1009,1006,1001,1010,1024};
	
	final static String[] apellidosP = {"Calvo López","Hialgo Cuesta","Alza Alcalde"};
	final static String[] nombreP = {"Manuel","Pablo","Victoria"};
	final static String[] generoP = {"Hombre","Hombre","Mujer"};
	final static String[] nifP = {"A00002001","A00002010","A00002024"};
	final static int[] expedienteP = {2001,2010,2024};

	final static String[] apellidosTr = {"Estevan Cazador","Cantal Zamora","Manso Verde","Peña Santidrian"};
	final static String[] nombreTr = {"Tomas","Vicente","Lucia","Sonia"};
	final static String[] generoTr = {"Hombre","Hombre","Mujer","Mujer"};
	final static String[] nifTr = {"A00003002","A00003009","A00003006","A00003001"};
	final static int[] expedienteTr = {3002,3009,3006,3001};

	/**
	 * 
	 * @return
	 */
	public static Persona[] soloAlumnos(){
		
		List<Persona> lp = new ArrayList<Persona>();
		
		for(int i=0;i<apellidosA.length;i++){
			
			String estudio = generaEstudio();
			
			Alumno a = new Alumno(apellidosA[i], nombreA[i], nifA[i], generoA[i], expedienteA[i]);
			a.setEstudios(estudio);
			a.setCurso(generaCurso());
		
			lp.add(a);
		}

		Persona[] listadoA = new Persona[apellidosA.length];
		return lp.toArray(listadoA);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Persona[] soloProfesores(){

		List<Persona> lp = new ArrayList<Persona>();
		
		for(int i=0;i<apellidosP.length;i++){

			String departamento = generaDepartamento();
			
			Profesor p = new Profesor(apellidosP[i], nombreP[i], nifP[i], generoP[i], expedienteP[i]);
			p.setDepartamento(departamento);
			p.setIdInterno(generaIdInterno());
			
			lp.add(p);
		}		

		Persona[] listadoP = new Persona[apellidosP.length];
		return lp.toArray(listadoP);
		
	}

	/**
	 * 
	 * @return
	 */
	public static Persona[] soloTrabajadores(){

		List<Persona> lp = new ArrayList<Persona>();
		
		for(int i=0;i<apellidosTr.length;i++){

			String servicio = generaServicio();
			
			Trabajador t = new Trabajador(apellidosTr[i], nombreTr[i], nifTr[i], generoTr[i], expedienteTr[i]);
			t.setServicio(servicio);
			t.setMesesAntiguedad(generaAleatorio(120));
			
			lp.add(t);
		}		

		Persona[] listadoP = new Persona[apellidosP.length];
		return lp.toArray(listadoP);
		
	}

	/**
	 * 
	 * @return
	 */
	public static Persona[] alumnosYProfesores(){

		Persona[] alumnos = soloAlumnos();
		Persona[] profesores = soloProfesores();

		List<Persona> lp = new ArrayList<Persona>();
		
		for(Persona a : alumnos)
			lp.add(a);
		
		lp.add(1, profesores[0]);
		lp.add(4, profesores[1]);
		lp.add(profesores[2]);

		Persona[] listado = new Persona[apellidosA.length+3];
		return lp.toArray(listado);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Persona[] profesoresYTrabajadores(){

		Persona[] profesores = soloProfesores();
		Persona[] trabajadores = soloTrabajadores();

		List<Persona> lp = new ArrayList<Persona>();
		
		for(Persona p : profesores)
			lp.add(p);
		
		lp.add(1, trabajadores[0]);
		lp.add(4, trabajadores[1]);
		lp.add(trabajadores[2]);

		Persona[] listado = new Persona[apellidosP.length+3];
		return lp.toArray(listado);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Persona[] todos(){

		Persona[] alumnos = soloAlumnos();
		Persona[] profesores = soloProfesores();
		Persona[] trabajadores = soloTrabajadores();

		List<Persona> lp = new ArrayList<Persona>();
		
		for(Persona c : alumnos)
			lp.add(c);
		
		lp.add(1, profesores[0]);
		lp.add(3, profesores[1]);
		lp.add(profesores[2]);
		
		lp.add(5, trabajadores[0]);
		lp.add(7, trabajadores[1]);
		lp.add(trabajadores[2]);
		

		Persona[] listado = new Persona[apellidosA.length+6];
		return lp.toArray(listado);
		
	}
	

	
	public static Persona[] aleatorios (int tamano){
		
		int tam;
		Persona [] listado = new Persona[tamano];
		
		for (int i=0; i<tamano; i++){
			
			Persona nuevo;
			
			if( (int) Math.round(Math.random()) == 0 ){
				tam = apellidosA.length;
				nuevo = new Alumno(apellidosTr[generaAleatorio(tam)], nombreTr[tam], nifTr[tam], generaGenero(), expedienteTr[tam]);
				((Alumno)nuevo).setEstudios(generaEstudio());
				((Alumno)nuevo).setCurso(generaCurso());
			}
			else{
				tam = apellidosP.length;
				nuevo = new Profesor(apellidosTr[generaAleatorio(tam)], nombreTr[tam], nifTr[tam], generaGenero(), expedienteTr[tam]);
				((Profesor)nuevo).setDepartamento(generaDepartamento());
				((Profesor)nuevo).setIdInterno(generaIdInterno());
			}
			
			listado[i] = nuevo;			
		}
		
		return listado;
		
	}
	
	private static int generaCurso(){
		return (int) generaAleatorio(3)+1;
	}
	
	private static int generaAleatorio(int max){
		return (int) Math.round(Math.random()) * max;
	}

	private static String generaEstudio(){
		String[] estudios = {"Doble Grado en Ingeniería de Tecnologías de Caminos y en Arquitectura Técnica","Doble Grado en Ingeniería Civil y en Arquitectura Técnica","Grado en Ingeniería Electrónica Industrial y Automática","Grado en Arquitectura Técnica","Grado en Ingeniería Informática","Grado en Ingeniería Agroalimentaria y del Medio Rural","Grado en Ingeniería de Organización Industrial","Grado en Ingeniería Civil","Grado en Ingeniería Mecánica","Grado en Ingeniería de Tecnologías de Caminos","Máster Universitario en Integridad y Durabilidad de Materiales. Componentes y Estructuras","Máster Universitario en Ingeniería de Caminos, Canales y Puertos","Máster Universitario en Ingeniería Informática","Máster Universitario en Ingeniería Industrial"};
		return estudios[generaAleatorio(estudios.length-1)];
	}

	private static String generaDepartamento(){
		String[] departamentos = {"Departamento de Expresión Gráfica","Departamento de Filología","Departamento de Física","Departamento de Ingeniería Civil","Departamento de Ingeniería Electromecánica","Matemáticas y Computación","Departamento de Química"};
		return departamentos[generaAleatorio(departamentos.length-1)];
	}

	private static String generaServicio(){
		String[] servicios = {"Servicio de Gestión de la Investigación","Servicio de Gestión Económica","Servicio de Información y Orientación en Salud Joven Universitaria","Servicio de Informática y Comunicaciones","Servicio de Inspección","Servicio de Publicaciones e Imagen Institucional","Servicio de Recursos Humanos","Servicio de Relaciones Internacionales","UBUEmprende","Unidad de Atención a la Diversidad","Unidad de Igualdad de Oportunidades","Unidad de Prevención de Riesgos Laborales","Unidad de Protocolo","Unidad Técnica de Calidad"};
		return servicios[generaAleatorio(servicios.length-1)];
	}

	private static int generaIdInterno(){
		return (int) generaAleatorio(20000)+1;
	}
	
	private static String generaGenero(){
		String retorno = "Mujer";
		if ((int) generaAleatorio(1)>0){
			retorno = "Hombre";
		};
		return retorno;
	}
}
