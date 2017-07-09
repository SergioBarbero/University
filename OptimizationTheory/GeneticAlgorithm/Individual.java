package OptimizationTheory.GeneticAlgorithm;

import java.util.Comparator;

/**
 * Created by sergio on 14/04/17.
 */
public class Individual implements  Comparable<Individual>{

    /**
     * Description: Item took or not
     */
    private boolean[] introduced;
    /**
     * Description: Fitness of each individual
     */
    private int fitness;
    /**
     * Description: Number of items into de knapsack
     */
    static private int nElements;
    /**
     * Description: Array of weights
     */
    static private int[] weights;
    /**
     * Description: Array of values
     */
    static private int[] values;
    /**
     * Description: Max weight in our knapsack
     */
    static private int maxWeight;

    /**
     * Empty constructor
     */
    public Individual(){ };

    /**
     * Description: Constructor. Individual initialized with a given array of boolean values
     * @param introduced
     */
    public Individual(boolean[] introduced){
        this.introduced = introduced;
        nElements = introduced.length;
    }

    /**
     * Description: Constructor. Individual initialized with the number of items into de knapsack
     * @param nElements
     */
    public Individual(int nElements){
        introduced = new boolean[nElements];
        this.nElements = nElements;
    }

    /**
     * Description: Initialize our static variables, weights, values and max weight
     * @param w array of weights
     * @param v array of values
     * @param mW max weight
     */
    public static void inicialiceProblem(int[] w, int[] v, int mW){
        Individual.weights = w;
        Individual.values = v;
        Individual.maxWeight = mW;
        nElements = w.length;
    }

    /**
     * Description: Initialize a single individual with random values
     * @return Random individual
     */
    public Individual inicialiceValues(){
        this.introduced = new boolean[nElements];
        for(int i=0; i < nElements; i++){
            this.introduced[i] = Math.random() < 0.5;
        }
        return this;
    }


    /**
     * Description: Modify our genome in a single point
     * @param i Index given
     * @param newIntroduced new value given
     */
    public void setIntroduced(int i, boolean newIntroduced){
        introduced[i] = newIntroduced;
    }

    /**
     * Description: Modify our genome completely
     * @param newIntroduced new genome
     */
    public void setIntroducedValues(boolean[] newIntroduced) {
        introduced = newIntroduced;
    }

    /**
     * Description: Set a fitness value for one individual
     */
    public void setFitness(){
        int penalization = 10;
        int sum = 0;
        int totalWeight = 0;
        for(int j = 0; j < nElements; j++){
            if(this.getIntroducedValue(j)) {
                totalWeight += getWeight(j);
                sum += Individual.getValue(j);
            }
            if(j == nElements - 1 && totalWeight > maxWeight)
                sum -= penalization*(totalWeight- maxWeight);
        }
        fitness = sum;
    }

    /**
     * Description: It returns a value of one item in a given position
     * @param i Index
     * @return value
     */
    public static int getValue(int i){
        return values[i];
    }

    /**
     * Description: It returns a weight of one item in a given position
     * @param i index
     * @return weight
     */
    public static int getWeight(int i){
        return weights[i];
    }

    /**
     * Description: It returns the value of one gene of one Individual
     * @param i index of our gene
     * @return value
     */
    public boolean getIntroducedValue(int i){
        return introduced[i];
    }

    /**
     * Description: It returns the complete genome of one Individual
     * @return genome
     */
    public boolean[] getIntroducedValues(){
        return introduced;
    }

    /**
     * Description: It returns a fitness value of one Individual
     * @return fitness of my Individual
     */
    public int getFitness(){
        return fitness;
    }

    /**
     * Description: It returns the number of items in our knapsack
     * @return number of items
     */
    public static int getnElements(){
        return nElements;
    }

    /**
     * Description: It returns the array of weights
     * @return weights
     */
    public static int[] getWeights(){
        return weights;
    }

    /**
     * Description: It returns the array of values
     * @return values
     */
    public static int[] getValues(){
        return values;
    }

    /**
     * Description: It returns the max weight allowed in our knapsack
     * @return maximum weight
     */
    public static int getMaxWeight(){
        return maxWeight;
    }

    /**
     * Description: It returns a copy of my Individual
     * @return copy of my individual
     */
    public Individual copyIndividual(){
        Individual copy = new Individual(nElements);
        copy.setIntroducedValues(this.getIntroducedValues());
        copy.setFitness();
        return copy;
    }

    public void printIndividual(){
        for(int j = 0; j < Individual.getnElements(); j++){
            System.out.print(this.getIntroducedValue(j) + " ");
        }
        System.out.print("(" + this.getFitness()+ ")");
    }

    /**
     * Description: Compare two individuals by fitness
     * @param ind another individual
     * @return 1 if this is smaller, -1 otherwise
     */
    @Override
    public int compareTo(Individual ind) {
        if(ind.getFitness() < this.getFitness())
            return -1;
        else
            return 1;
    }

}
