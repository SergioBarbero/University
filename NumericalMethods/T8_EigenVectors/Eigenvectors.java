package NumericalMethods.T8_EigenVectors;
import Jama.Matrix;


/**
 * Created by sergio on 20/04/17.
 */
public class Eigenvectors {

    double[][] matrix;
    double hmodeigenValue;
    double[] eigenvector;
    double[] eigenvectorAprox;
    double eigenValueAprox;
    double[] analyticEigenValues;

    public Eigenvectors(double[][] matrix){
        this.matrix = matrix;
        eigenvector = new double[matrix.length];
    }

    /**
     * Calculate our Gauss matrix of a system with 3 variables and 3 ecuations
     * @param matrix 3x4
     * @return Gaus Matrix
     */
    public static double[] GausMethod(double[][] matrix) {
        //System.out.println("INITIAL MATRIX: ");
        double mul = 0;
        for (int m = 0; m <= 1; m++) {
            for (int n = 2; n > m; n--) {
                if (matrix[n][m] != 0) {
                    int aux = n - 1;
                    mul = matrix[n][m] / matrix[aux][m];
                    for (int j = 0; j < matrix[0].length; j++) {
                        matrix[n][j] = matrix[n][j] / mul;
                        matrix[n][j] = matrix[n][j] - matrix[aux][j];
                    }
                }
            }
        }


        //System.out.println("FINAL MATRIX: ");


        return solver(matrix);
    }

    /**
     * Calculate the values of x, y, z
     * @param matrix of Gauss
     * @return final results
     */
    public static double[] solver(double[][] matrix){
        double z = matrix[2][3] / matrix[2][2];
        double y = (matrix[1][3] - matrix[1][2]*z ) / matrix[1][1];
        double x = (matrix[0][3] - (matrix[0][1]*y + matrix[0][2]*z)) / matrix[0][0];

        //System.out.println("RESULTS: ");
        //System.out.println("x = " + x);
        //System.out.println("y = " + y);
        //System.out.println("z = " + z);
        double[] result = {x, y, z};
        return result;

    }

    public void analyticSolution(){
        Matrix mat = new Matrix(this.matrix);
        double[][] eigenvectors = mat.eig().getV().getArray();
        double[] eigenvalues = mat.eig().getRealEigenvalues();
        int index = 0;

        for(int j=0; j < eigenvectors.length; j++){
            double multiplier = 1.0 / eigenvectors[eigenvectors.length-1][j];
            double max = 0;
            double modulus = 0;
            double partial = 0;
            for(int i=0; i < eigenvectors[0].length; i++){
                eigenvectors[i][j] = eigenvectors[i][j]*multiplier;
                partial += Math.pow(eigenvectors[i][j], 2);
            }
            System.out.println();
            modulus = Math.sqrt(partial);
            if(modulus > max){
                max = modulus;
                index = j;

            }
        }
        for(int i=0; i < eigenvectors.length; i++){
            eigenvector[i] = eigenvectors[i][index];
        }
        hmodeigenValue = eigenvalues[index];
        analyticEigenValues = eigenvalues;
    }

    //[ 1 1 1 1 2 3 1 3 6]

    public double[]  KrylovMethod(double[][] matrix, int n){
        //Common part
        double[][] y = new double[n][matrix.length];

        //Initialization of y[0]
        for(int i = 0; i < matrix.length; i++) {
            if (i == 0)
                y[0][0] = 1;
            else
                y[0][i] = 0;
        }

        for(int i = 1, k = 0; i < n; i++, k++){
            y[i] = multiplication(y[k], matrix);

        }

        //KryklovMethod
        double[][] system = new double[3][4];

        for(int i=0; i < 3; i++){
            for(int j=0; j < 4; j++){
                if(j != 3) {
                    system[i][j] = y[3 - j - 1][i];
                }else{
                    system[i][j] = -y[4 - 1][i];
                }

            }
        }
        double[] coeficients = GausMethod(system);
        for(int i=0; i < coeficients.length; i++){
            System.out.println(i + ": " + coeficients[i]);
        }
        return coeficients;
    }

    public  double powerMethod(double[][] matrix,int n){
        double[][] y = new double[n][matrix.length];

        //Common part
        //Initialization of y[0]
        for(int i = 0; i < matrix.length; i++) {
            if (i == 0)
                y[0][0] = 1;
            else
                y[0][i] = 0;
        }

        for(int i = 1, k = 0; i < n; i++, k++){
            y[i] = multiplication(y[k], matrix);

        }

        /*for(int i = 0; i < n; i++){
            for(int j = 0; j < matrix.length; j++){
                System.out.print(y[i][j] + " ");
            }
            System.out.println();
        }*/

        //Power Method
        //Calculating eigenvalue
        double sum = 0;
        for(int i = 0; i < matrix.length; i++){
            sum += y[n-1][i] / (y[n-2][i]);
        }

        double eigenvalue = sum / matrix.length;

        //Calculating eigenvectors
        double[] eigenvectors = new double[matrix.length];

        double max = getMaxValue(y[n-1]);

        System.out.print("Eigenvector: ");
        for(int i = 0; i < matrix.length; i++){
            eigenvectors[i] =  y[n-1][i] / max;
            System.out.print(eigenvectors[i] + " ");
        }
        System.out.println();
        eigenValueAprox = eigenvalue;
        eigenvectorAprox = eigenvectors;

        System.out.println("Eigenvalue: " + eigenvalue);

        return eigenValueAprox;

    }

    public static double[] multiplication(double[] v, double[][] m1){
        double[] finalVector = new double[v.length];
        for(int i = 0; i < m1.length; i++){
            double sum = 0;
            for(int j = 0; j < m1[0].length; j++){
                sum += m1[i][j]*v[j];
            }
            finalVector[i] = sum;
            //System.out.print(finalVector[i] + " ");
        }

        return finalVector;
    }

    public static double getMaxValue(double[] numbers){
        double maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int n = 4;
        double[][] matrix = {{1, 1, 1},{ 1, 2, 3},{ 1, 3, 6}};
        Eigenvectors problem = new Eigenvectors(matrix);
        problem.analyticSolution();



        System.out.println("Analytical solution");
        System.out.print("Eigenvector: ");
        for(int j=0; j < matrix[0].length; j++){
                System.out.print(problem.eigenvector[j] + " ");
        }
        System.out.println();
        for(int j=0; j < matrix[0].length; j++){
            System.out.print(problem.analyticEigenValues[j]+ "   ");
        }


        System.out.println("\n");
        System.out.println("Aproximation with Power method n = " + n);
        problem.powerMethod(matrix, n);

        System.out.println("\nAproximation with Krylov method");
        System.out.println("Caracteristic coeficients: ");
        double[] coef = problem.KrylovMethod(matrix, n);

        System.out.print("Characteristic eq:\n lambda^3 + " + coef[0] + "*lambda^2 + " + coef[1] + "*lambda"  + " " + coef[2] +  " =  0");
        System.out.print("\nEigenvalues: 1    0.12702    7.8730");

    }
}
