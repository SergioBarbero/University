package NumericalMethods.T4_NumericalDifferentiation;


public class NumericalDifferentiation {

    double x0;
    int function;
    int k;

    /**
     * Description: Constructor of the class, for a given function
     * @param function Given function: 0 : Polynomial |  1 : NonLinear | 2 : Trigonometric
     * @param x0 point to calculate the derivative
     */
    public NumericalDifferentiation(int function, double x0){
        this.x0 = x0;
        this.function = function;
    }

    /**
     * Description: Provides the accurate result of the derivative
     * @return k-order derivative
     */
    public double analyticalDerivative(){
        switch (function){
            case 0:
                if( k == 1)
                    return 5*Math.pow(x0, 4) + 3*Math.pow(x0, 2)+ 2; // f'(x) = 2x+2
                else
                    return 20*Math.pow(x0, 3) + 6*x0; // f''(x) = 2
            case 1:
                if( k == 1)
                    return 1 / (Math.pow(x0, 2) + 1); // f'(x) = 1 / (x² + 1)
                else
                    return -2*x0 /(Math.pow(x0, 2)+ 1); // f''(x) = -2x / (x²+1)
            case 2:
                if ( k == 1)
                    return -1*Math.sin(x0); // f(x) = -sin(x)
                else
                    return -1*Math.cos(x0); // f''(x) = -cos(x)
        }
        return 0;
    }

    /**
     * Description: Main function
     * @param n order
     * @param h increment
     * @param k order of the derivative
     */
    public double difference(int n, double h, int k) {
        double[] p = new double[n];
        double[] qLarger = new double[2 * n];
        double solution;
        this.k = k;

        double x = 1;
        for (int i = 0; i < n; i++) {
            if (x % 2 != 0)
                p[i] = 1 / x;
            else
                p[i] = -1 / x;
            x++;
            //System.out.print(p[i] + " ");

        }
        System.out.println();


        if(k == 2) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    qLarger[i + j] += p[i] * p[j];
                }
            }
        }else{
            for(int i=0; i< n; i++) {
                qLarger[i] = p[i];
            }
        }


        double sum = 0;
        double[] partial = new double[n];
        System.out.print("f^(" + k + ") (x) = [1 / " + "(" + h + "^" + k + ") ]*");
        int index = k;
        --index;
        for(int i = 0, m = k; i < n; m++, index++, i++){
            partial[i] = recursiveDifference(m, 0, h);
            sum += qLarger[i]*partial[i];
            System.out.print(qLarger[i] + "*" + partial[i] + " ");
        }

        solution = sum / (Math.pow(h, k));

        System.out.println(" = " + solution);
        System.out.println();
        if(k == 1)
            System.out.println("Aproximated " + k + "st derivative for " + n + "th order = " + solution );
        else
            System.out.println("Aproximated " + k + "nd derivative for " + n + "th order = " + solution );


        return solution;

    }

    public double absoluteError(double numerical){
        double analytical = analyticalDerivative();
        double error = Math.abs(numerical - analytical);
        System.out.println("Exact value of the derivative = " + analytical);
        System.out.println();
        System.out.println("Absolute error : |" + analytical + " - (" + numerical + ")| = " + error);
        return error;
    }

    public double relativeError(double solution){
        double analytical = analyticalDerivative();
        double error = Math.abs(solution - analytical) / Math.abs(analytical);
        System.out.println("Relative error : |" + analytical + " - (" + solution + ")| / |" +  analytical +" | = " + error);
        return error;
    }

    /**
     * Description: Recursive method, it calculates the forward difference table for a given order n
     * @param n order
     * @param ih represents how many times we are going to multiply h
     * @param h increment
     * @return value of the forward difference
     */
    public double recursiveDifference(int n, int ih, double h){
        ih++;
        n--;

        if(n == 0){
            double firstTerm = selectFuction(ih, h);
            ih--;
            double scndTerm =  selectFuction(ih, h);
            return  firstTerm - scndTerm;
        }else{

            return recursiveDifference(n, ih, h) - recursiveDifference(n, --ih, h);
        }

    }

    /**
     *Description: Returns the value for the function in one point + i-times h
     * @return value of the function for x = x0 with h
     */
    public double selectFuction(int ih, double h){
        switch (function){
            case 0:
                return Math.pow(x0+ih*h, 5)+Math.pow(x0+ih*h, 3)+ 2*(x0+ih*h)+3; //f(x) = x²+2x+3
            case 1:
                return Math.atan(x0+ih*h); // f(x) = arctg(x)
            case 2:
                return Math.cos(x0+ih*h); // f(x) = cos(x)
        }
        return 0;
    }

    public static void main(String[] args) {
        int k = 2;
        double h = 1E-6;
        int n = 5;
        double x0 = 2*Math.PI;
        int function = 2; //refers the type of the function:  0 : Polynomial |  1 : NonLinear | 2 : Trigonometric |
        double solution, absoluteE, relativeE;

        NumericalDifferentiation problem = new NumericalDifferentiation(function, x0);

        solution = problem.difference(n, h, k);


        problem.absoluteError(solution);
        problem.relativeError(solution);
    }
}
