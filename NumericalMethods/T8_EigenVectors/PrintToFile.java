package NumericalMethods.T8_EigenVectors;

import NumericalMethods.T3_Interpolation.Interpolation;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Description: Writes a csv file for every single funcion, for equally spaced nodes and Chebyshev nodes, and for n=5,10,15,.....,30
 */
 public class PrintToFile {



    /**
     * Description: exports the results to csv
     * @param max maximum number of iterations
     */
     public static void exportToCSV(double[][] matrix, int max){
         FileWriter proofs = null;
         PrintWriter pw = null;



         try
         {
             proofs = new FileWriter("src/NumericalMethods/T8_EigenVectors/results.csv");
             pw = new PrintWriter(proofs);
             Eigenvectors problem = new Eigenvectors(matrix);
             problem.analyticSolution();
             for(int n = 2; n <= max; n++){

                 problem.powerMethod(matrix, n);
                 pw.write(n + "," + Double.toString(problem.eigenValueAprox) + "\n");
             }
             pw.write("analytical," + problem.hmodeigenValue);

         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 if (null != proofs)
                     proofs.close();
             } catch (Exception e2) {
                 e2.printStackTrace();
             }
         }




     }

    public static void main(String[] args) {

        double[][] matrix = {{1, 1, 1},{ 1, 2, 3},{ 1, 3, 6}};
        int max = 20;


        exportToCSV(matrix, max);

    }

}
