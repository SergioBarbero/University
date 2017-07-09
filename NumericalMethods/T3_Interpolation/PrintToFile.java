package NumericalMethods.T3_Interpolation;

import java.io.*;

/**
 * Description: Writes a csv file for every single funcion, for equally spaced nodes and Chebyshev nodes, and for n=5,10,15,.....,30
 */
 public class PrintToFile {


    /**
     * Description: print the kind of the funcion
     * @param i kind of the funcion
     * @return kind of the funcion in String
     */
     public static String printType(int i){
         switch (i){
             case 0:
                 return "Polynomial";
             case 1:
                 return "Rational";
             case 2:
                 return "Modulus";
             case 3:
                 return "Trigonometric";
         }
         return "";
     }


    /**
     * Description: exports the results to csv
     * @param x point x
     * @param a interval [-a, a]
     */
     public static void exportToCSV(double x, double a){
         FileWriter proofs = null;
         PrintWriter pw = null;

         try
         {
             proofs = new FileWriter("results.csv");
             pw = new PrintWriter(proofs);

             //Type of the function
             for(int i=0; i < 4; i++){
                 Interpolation problem = new Interpolation(i);
                 pw.println("Kind of the function: " + printType(i));
                 pw.println("f(" + x + ")" + " = " +  problem.chooseFunction(i, x));
                 //Type of nodes
                 for(int j=0; j < 2; j++){
                     if (j == 0)
                         pw.println("Equally spaced nodes");
                     else
                         pw.println("Chebyshev nodes");
                     for(int n=5; n <= 30; n += 5) {
                         double[] nodes;

                         if (j == 0)
                             nodes = problem.equallySpaced(n, -a, a);
                         else
                             nodes = problem.chebyshevNodes(n, -a, a);

                         double solution = problem.lagrange(nodes, 0);
                         //pw.println("W(x) = " + problem.W);
                         //pw.println("W(" + x + ")" + " = " + problem.Wx);
                         pw.println(n + "," + problem.error(problem.Wx));
                     }
                     pw.println();
                 }
                 pw.println();

             }

         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 // Nuevamente aprovechamos el finally para
                 // asegurarnos que se cierra el fichero.
                 if (null != proofs)
                     proofs.close();
             } catch (Exception e2) {
                 e2.printStackTrace();
             }
         }




     }

    public static void main(String[] args) {
        exportToCSV(7, 10);

    }

}
