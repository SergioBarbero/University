package NumericalMethods.T12_AproximateIntegrals;

import NumericalMethods.T3_Interpolation.Interpolation;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Description: Writes a csv file for every single funcion
 */
 public class PrintToFile {


    public static void exportToCSV() {
        FileWriter proofs = null;
        PrintWriter pw = null;

        try {
            proofs = new FileWriter("src/NumericalMethods/T12_AproximateIntegrals/results.csv");
            pw = new PrintWriter(proofs);

            for (int k = 0; k <= 1; k++) {
                double[] arrays = {0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7};
                double delta = arrays[1] - arrays[0];

                ApproximateIntegrals problem;
                if(k == 0)
                    problem = new ApproximateIntegrals(arrays, k, 1, delta);
                else
                    problem = new ApproximateIntegrals(arrays, k, -1, delta);

                double[][] matrix = problem.NumericalSolution(10, arrays.length);

                pw.println("x,analytic,firstIt,secIt,thrIt..., nIt");
                //Type of the function
                for (int i = 0; i < matrix[0].length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (j == 0)
                            pw.print(arrays[i] + "," + problem.analytic(arrays[i]));
                        pw.print("," + matrix[j][i]);

                    }
                    pw.println();
                }
                double[][] error = problem.AbsoluteError(matrix);

                pw.println("Absolute error");
                pw.println("x,firstIt,secIt,thrIt..., nIt");
                for (int i = 0; i < matrix[0].length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (j == 0)
                            pw.print(arrays[i]);
                        pw.print("," + error[j][i]);
                    }
                    pw.println();
                }
            }
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
        exportToCSV();

    }

}
