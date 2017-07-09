package NumericalMethods.T12_AproximateIntegrals;

/**
 * Created by sergio on 25/05/17.
 */
public class ApproximateIntegrals {
    /**
     * 1st example k(z) = sin(z); f(x) = cos(x); z = x - t; lambda = 1 -> Y(x) = 1
     * 2nd example k(z) = z; f(x) = x²/2; lambda = -1; Y(x) = 1 - cos(x)
     */

    private double a, b;
    private int lambda;
    private double delta;
    private int type;
    private double[] axis;

    /**
     * Description: Constructor of the class
     * @param axis x-axis points
     * @param type 0 for the first example, otherwise for the second
     * @param lambda value of lambda
     * @param delta difference between points on x-axis
     */
    public ApproximateIntegrals(double[] axis, int type, int lambda, double delta) {
        this.lambda = lambda;
        this.delta = delta;
        this.type = type;
        this.a = axis[0];
        this.b = axis[axis.length-1];
        this.axis = axis;
    }


    /**
     * Description: Analytic solution to our problem
     * @param x point to calculate analytic solution
     * @return analytical solution
     */
    public double analytic(double x)
    {
        if(type == 0)
            return 1.0;
        else
            return 1.0 - Math.cos(x);

    }


    /**
     * Description: Numerical solution to our problem
     * @param n number of iterations
     * @param N number of elements in our axis
     * @return matrix "y" of n rows and N columns
     */
    public double[][] NumericalSolution(int n, int N){
        double[][] y = new double[n][N];

        //Initialization of our matrix y
        for(int i = 0; i < N; i++)
            y[0][i] = 0;

        //Performing iterations
        for(int it = 1, prev = 0; it < n; prev++, it++){
            for(int j = 0; j < N; j++){
                double sum = 0;
                for (int i = 0; i < j; i++) {
                    if(type == 0)
                        sum += Math.sin(axis[j] - axis[i]) * y[prev][i] * delta;
                    else
                        sum += (axis[j] - axis[i]) * y[prev][i] * delta;
                }
                sum *= lambda;
                if(type == 0)
                    y[it][j] = sum + Math.cos(axis[j]);
                else
                    y[it][j] = sum + (axis[j]*axis[j])/2.0;
            }
            System.out.println();
        }


        //Printing results
        for(int i = 0; i < n; i++){
            System.out.print(i + ": ");
            for(int j = 0; j < N; j++){
                System.out.print(y[i][j] + " ");
            }
            System.out.println();
        }
        return y;
    }

    public double[][] AbsoluteError(double[][] y){
        double[][] errorMatrix = new double[y.length][axis.length];
        for(int i=0; i < y.length; i++){
            for(int j = 0; j < axis.length; j++){
                errorMatrix[i][j] = Math.abs(this.analytic(axis[j]) - y[i][j]);
            }
        }

        return errorMatrix;
    }


    /**
     * Description: Main method
     * @param args non-used
     */
    public static void main(String[] args) {
        double[] arrays = {0, 0.5, 1, 1.5, 2, 2.5,3, 3.5, 4};
        double delta = arrays[1] - arrays[0];

        ApproximateIntegrals problem = new ApproximateIntegrals(arrays,1, 1, delta);

        double[][] y = problem.NumericalSolution(9, arrays.length);

        problem.AbsoluteError(y);
    }
}
