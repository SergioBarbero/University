package es.ubu.inf.edat.datos.personas;

import java.util.ArrayList;
import java.util.List;

import es.ubu.inf.edat.datos.personas.Persona.sexo;

public class GeneradorPersonas {
	final static String[] apellidosA = {"Savador Perez","Gomez Alonso","Garcia Blanco","Tome Pena","Sacristan Antunez","Sanchez Aguado"};
	final static String[] nombreA = {"Carmen","Luis","Maria","Isabel","Laura","Pedro"};
	final static sexo[] generoA = {sexo.MUJER,sexo.HOMBRE,sexo.MUJER,sexo.MUJER,sexo.MUJER,sexo.HOMBRE};
	final static String[] nifA = {"A00001002","A00001009","A00001006","A00001001","A00001010","A00001024"};
	final static int[] expedienteA = {1002,1009,1006,1001,1010,1024};
	final static int[] edadA = {18,18,20,22,28,19};
	
	final static String[] apellidosP = {"Hialgo Lopez","Calvo Cuesta","Alza Alcalde"};
	final static String[] nombreP = {"Victoria","Maria","Pablo",};
	final static sexo[] generoP = {sexo.MUJER,sexo.MUJER,sexo.HOMBRE};
	final static String[] nifP = {"A00002001","A00002010","A00002024"};
	final static int[] expedienteP = {52,10,80};
	final static int[] edadP = {52,23,35};

	final static String[] apellidosTr = {"Estevan Cazador","Cantal Zamora","Manso Verde","Pena Santidrian"};
	final static String[] nombreTr = {"Tomas","Vicente","Lucia","Sonia"};
	final static sexo[] generoTr = {sexo.HOMBRE,sexo.HOMBRE,sexo.MUJER,sexo.MUJER};
	final static String[] nifTr = {"A00003002","A00003009","A00003006","A00003001"};
	final static int[] expedienteTr = {3002,3009,3006,3001};
	final static int[] edadTr = {32,39,25,60};

	/**
	 * 
	 * @return
	 */
	public static Persona[] soloAlumnos(){
		
		List<Persona> lp = new ArrayList<Persona>();
		
		for(int i=0;i<apellidosA.length;i++){
			
			String estudio = generaEstudio();
			
			Alumno a = new Alumno(apellidosA[i], nombreA[i], nifA[i], generoA[i], edadA[i], expedienteA[i] );
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
			
			Profesor p = new Profesor(apellidosP[i], nombreP[i], nifP[i], generoP[i], edadP[i], expedienteP[i]); // TODO <-- FALTA LA EDADDDD
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
			
			Trabajador t = new Trabajador(apellidosTr[i], nombreTr[i], nifTr[i], generoTr[i], edadTr[i], expedienteTr[i]); 
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
		lp.add(2,trabajadores[3]);
		

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
				nuevo = new Alumno(apellidosTr[generaAleatorio(tam)], nombreTr[tam], nifTr[tam], generaGenero(), edadTr[tam], expedienteTr[tam]);
				((Alumno)nuevo).setEstudios(generaEstudio());
				((Alumno)nuevo).setCurso(generaCurso());
			}
			else{
				tam = apellidosP.length;
				nuevo = new Profesor(apellidosTr[generaAleatorio(tam)], nombreTr[tam], nifTr[tam], generaGenero(), edadTr[tam], expedienteTr[tam]);
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
		return (int) (Math.round(Math.random()* max));
	}

	private static String generaEstudio(){
		String[] estudios = {"Doble Grado en Ingenier�a de Tecnolog�as de Caminos y en Arquitectura T�cnica","Doble Grado en Ingenier�a Civil y en Arquitectura T�cnica","Grado en Ingenieria Electronica Industrial y Automatica","Grado en Arquitectura T�cnica","Grado en Ingenier�a Inform�tica","Grado en Ingenier�a Agroalimentaria y del Medio Rural","Grado en Ingenier�a de Organizaci�n Industrial","Grado en Ingenier�a Civil","Grado en Ingenier�a Mec�nica","Grado en Ingenier�a de Tecnolog�as de Caminos","M�ster Universitario en Integridad y Durabilidad de Materiales. Componentes y Estructuras","M�ster Universitario en Ingenier�a de Caminos, Canales y Puertos","M�ster Universitario en Ingenier�a Inform�tica","M�ster Universitario en Ingenier�a Industrial"};
		
		// un 33% de las veces se elige el mismo estudio
		if(generaAleatorio(100) < 33)		
			return estudios[2];
		return estudios[generaAleatorio(estudios.length-1)];
		
	}

	private static String generaDepartamento(){
		String[] departamentos = {"Departamento de Expresi�n Gr�fica","Departamento de Filolog�a","Departamento de F�sica","Departamento de Ingenier�a Civil","Departamento de Ingenier�a Electromec�nica","Matem�ticas y Computaci�n","Departamento de Qu�mica"};
		return departamentos[generaAleatorio(departamentos.length-1)];
	}

	private static String generaServicio(){
		String[] servicios = {"Servicio de Gesti�n de la Investigaci�n","Servicio de Gesti�n Econ�mica","Servicio de Informaci�n y Orientaci�n en Salud Joven Universitaria","Servicio de Inform�tica y Comunicaciones","Servicio de Inspecci�n","Servicio de Publicaciones e Imagen Institucional","Servicio de Recursos Humanos","Servicio de Relaciones Internacionales","UBUEmprende","Unidad de Atenci�n a la Diversidad","Unidad de Igualdad de Oportunidades","Unidad de Prevenci�n de Riesgos Laborales","Unidad de Protocolo","Unidad T�cnica de Calidad"};
		return servicios[generaAleatorio(servicios.length-1)];
	}

	private static int generaIdInterno(){
		return (int) generaAleatorio(20000)+1;
	}
	
	private static sexo generaGenero(){
		sexo retorno = sexo.MUJER;
		if ((int) generaAleatorio(1)>0){
			retorno = sexo.HOMBRE;
		};
		return retorno;
	}
}
