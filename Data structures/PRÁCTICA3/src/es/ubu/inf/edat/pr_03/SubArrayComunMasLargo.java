package es.ubu.inf.edat.pr_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergio barbero bascones
 */
public class SubArrayComunMasLargo<T> {
	List<T> secuencia1;
	List<T> secuencia2;

	public SubArrayComunMasLargo(List<T> secuencia1, List<T> secuencia2) {
		this.secuencia1 = secuencia1;
		this.secuencia2 = secuencia2;
	}
	
	/**
	 * Descripcion: Encuentra la cadena coincidente por medio de un algoritmo iterativo
	 * @return resultado lista resultante
	 */
	public List<T> resultadoMetodoA() {
		int masGrande = 0;
		List<T> resultado = new ArrayList<T>();
		for (int inicio1 = 0; inicio1 < secuencia1.size(); inicio1++) {
			for (int tam = 1; tam <= secuencia1.size() - inicio1; tam++) {
				for (int inicio2 = 0; inicio2 <= secuencia2.size() - tam; inicio2++) {
					if (secuencia1.subList(inicio1, inicio1 + tam).equals(
							secuencia2.subList(inicio2, inicio2 + tam))) {
						if (tam > masGrande) {
							resultado = secuencia1.subList(inicio1, inicio1
									+ tam);
							masGrande = tam;
						}
					}
				}
			}
		}
		return resultado;
	}
    /**
    * Descripcion: Encuentra la cadena coincidente por medio de una matriz en la que se representan las coincidencias
    * @return resultado lista resultante
	*/
	public List<T> resultadoMetodoB() {
		List<T> resultado = new ArrayList<T>();
		int[][] matrizCalculo = new int[secuencia1.size() + 1][secuencia2.size() + 1];
		int max = 0;

		for (int i = 0; i < matrizCalculo.length; i++) {
			for (int j = 0; j < matrizCalculo[0].length; j++) {
				if( j == 0 || i == 0)
					matrizCalculo[i][j] = 0;
				else
					if (secuencia1.get(i-1) == secuencia2.get(j-1)) {
						matrizCalculo[i][j] = ++matrizCalculo[i - 1][j - 1];
						if (matrizCalculo[i][j] > max) {
							max = matrizCalculo[i][j];
							resultado.add(secuencia1.get(i-1));
						} else
							matrizCalculo[i][j] = 0;
					}
				}
		}
		return resultado;
	}
}