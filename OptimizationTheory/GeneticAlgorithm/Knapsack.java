package OptimizationTheory.GeneticAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

import OptimizationTheory.GeneticAlgorithm.Knapsack;

/**
 * Description: Knapsack problem with tournament selection, one point locus and one point crossover
 * @author Sergio Barbero
 * @autor Maaz Ashiq
 */
public class Knapsack {

    /**
     *Description: Population of Individuals
     */
    Individual[] population;
    /**
     * Size of population
     */
    int size;

    /**
     * printModes: "AllTraces", "BestIndividual", "IndivGeneration"
     */
    String printMode;


    public Knapsack(int n, String printMode){
        population = new Individual[n];
        this.size = n;
        this.printMode = printMode;
    }

    /**
     * Descripcion: It initializes a random population of Individuals and their fitnesses
     * @return random population
     */
    public Individual[] inicialicePopulation(){
        for(int i=0; i < size; i++){
            this.population[i] = new Individual().inicialiceValues();
            this.population[i].setFitness();

        }
        printPopulation(population);
        return this.population;
    }


    /**
     * Description: Mutation of one point in one Individual
     * @param x Individual to mutate
     * @param p Mutation probability 0-100
     * @return New Individual
     */
    public Individual mutation(Individual x, int p){
        Random gen = new Random();
        int over100 = gen.nextInt(100) + 1;
        int point = gen.nextInt(x.getnElements());
        if(over100 < p){
            if (x.getIntroducedValue(point) == false)
                x.setIntroduced(point, true);
            else
                x.setIntroduced(point, false);
        }

        if(printMode == "AllTraces"){

            System.out.println("Mutation");
            x.printIndividual();
            System.out.println();
        }


        return x;
    }


    /**
     * Description: Crossover of two parents in two children
     * @param parent1 Individual to crossover
     * @param parent2 Individual to crossover
     * @param p Crossover probability 0-100
     * @return Array of two children
     */
    public Individual[] recombination(Individual parent1, Individual parent2, int p){
        //At first, children are equal than their parents
        Individual child1 = parent1.copyIndividual();
        Individual child2 = parent2.copyIndividual();
        Random generator = new Random();
        int pointCrossover = generator.nextInt(Individual.getnElements()-2) + 1;
        int over100 = generator.nextInt(100);
        if(over100 < p){
            for(int i = pointCrossover; i < Individual.getnElements(); i++){
                boolean aux =  child1.getIntroducedValue(i);
                child1.setIntroduced(i, child2.getIntroducedValue(i));
                child2.setIntroduced(i, aux);
            }
        }
        Individual[] children = new Individual[2];
        children[0] = child1;
        children[1] = child2;

        if(printMode == "AllTraces"){
            System.out.println();
            System.out.println("Recombination");
            printPopulation(children);
            System.out.println();
        }


        return children;
    }

    /**
     *Description: Fitness evaluation of a population of Individuals
     * @param myPop Population of individuals
     */
    public void fitnessPopulation(Individual[] myPop){
        if(this.printMode == "AllTraces") {
            System.out.println();
            System.out.println("Fitness evaluation");
        }
        for(Individual x : myPop){
            x.setFitness();
            if(this.printMode == "AllTraces") {
                for (int j = 0; j < Individual.getnElements(); j++) {
                    System.out.print(x.getIntroducedValue(j) + " ");
                }
                System.out.print("(" + x.getFitness() + ")");
                System.out.println();
            }

        }
    }

    /**
     * Description: Tournament selection of a sampling of our population (2 winners)
     * @param l size of our sampling
     * @return array of Individuals selected
     */
    public Individual[] selection(int l){
        //Tournament selection
        Random generator = new Random();
        List<Individual> copy = new LinkedList<Individual>(Arrays.asList(population));
        Individual[] selected = new Individual[2];
        List<Individual> randomPopulation = new ArrayList<Individual>(l);
        for(int i=0; i < l; i++){
            int index = generator.nextInt(copy.size());
            randomPopulation.add(copy.get(index).copyIndividual());
            copy.remove(index);
        }
        Collections.sort(randomPopulation, Individual::compareTo);
        List<Individual> best2Elements = randomPopulation.stream().limit(2).collect(Collectors.toList());
        selected  = best2Elements.toArray(selected);

        if(printMode == "AllTraces"){

               System.out.println();
               System.out.println("Selection");
               printPopulation(selected);
        }


        return selected;
    }


    /**
     * Description: Genetic algorithm, selection -> recombination -> mutation -> fitness evaluation
     * @param w Array of weights
     * @param v Array of values
     * @param max maximum value of our knapsack
     * @param generations Generations of our algorithm
     * @param l tournament size
     * @param size size of our population
     * @param mutationP probability of mutation
     * @param recombP probability of recombination
     */
    public static void runGeneticAlgorithm(int[] w, int[] v, int max, int generations, int l, int size, int mutationP, int recombP){

        String printMode = "AllTraces";

        Individual.inicialiceProblem(w, v, max);


        Knapsack problem = new Knapsack(size, printMode);

        System.out.println("Random population: ");
        problem.population = problem.inicialicePopulation();
        System.out.println();


        for(int p = 1; p <= generations; p++) {
            System.out.println();
            System.out.println("Generation " + p);

            List<Individual> emptyPopulation = new ArrayList<Individual>(Arrays.asList());
            for (int i = 0, n = 1; i < size; i+=2 , n+=2) {

                //Selection
                Individual[] selected = problem.selection(l);

                //Recombination
                Individual[] children = problem.recombination(selected[0], selected[1], recombP);

                //Mutation
                Individual mut1 = problem.mutation(children[0], mutationP);
                Individual mut2 = problem.mutation(children[1], mutationP);

                Individual[] mutations = new Individual[2];
                mutations[0] = mut1;
                mutations[1] = mut2;

                //Fitnes evaluation
                problem.fitnessPopulation(mutations);

                emptyPopulation.add(mut1);
                emptyPopulation.add(mut2);



            }
            if(printMode == "IndivGeneration" || printMode == "AllTraces") {
                System.out.println();
                System.out.println("My population");
                for (Individual x : emptyPopulation) {
                    for (int j = 0; j < Individual.getnElements(); j++) {
                        System.out.print(x.getIntroducedValue(j) + " ");
                    }
                    System.out.print("(" + x.getFitness() + ")");
                    System.out.println();
                }
            }else{
                Individual best = emptyPopulation.stream().max(Individual::compareTo).get();

                best.printIndividual();
            }

            //Overriding my population for the new one
            problem.population = emptyPopulation.toArray(problem.population);
        }
    }

    /**
     * Description: Prints a specific population
     * @param population Individual[] to print
     */
    public void printPopulation(Individual[] population){
        for (Individual x : population) {
            for (int j = 0; j < Individual.getnElements(); j++) {
                System.out.print(x.getIntroducedValue(j) + " ");
            }
            System.out.print("(" + x.getFitness()+ ")");
            System.out.println();
        }
    }


    /**
     * Description: Data input
     */
    public static void scanner(){

        int max;
        int l;
        int size;
        int generations;
        int mutationP;
        int recombP;

        Scanner sc = new Scanner(System.in);
        System.out.print("Size of our population: ");
        size = sc.nextInt();
        System.out.println();

        System.out.print("Number of generations: ");
        generations = sc.nextInt();
        System.out.println();

        System.out.print("Max weight allowed: ");
        max = sc.nextInt();
        System.out.println();


        System.out.print("Number of items in our knapsack: ");
        int items = sc.nextInt();
        System.out.println();
        int[] w = new int[items];
        int[] v = new int[items];
        for(int i = 0; i < items; i++){
            System.out.print("Weight of item n " + i + ": " );
            w[i] = sc.nextInt();
            System.out.println();

            System.out.print("Value of item n: " + i + ": " );
            v[i] = sc.nextInt();
            System.out.println();
        }


        System.out.print("Mutation probability(0-100): ");
        mutationP = sc.nextInt();
        System.out.println();

        System.out.print("Recombination probability(0-100): ");
        recombP = sc.nextInt();
        System.out.println();

        System.out.print("Size of tournament: ");
        l = sc.nextInt();
        System.out.println();

        runGeneticAlgorithm(w,  v, max, generations, l, size, mutationP, recombP);
    }


    /**Description: Main function
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    /*    int[] w = {5, 4, 6, 2, 6, 3, 5};
        int[] v = {1, 2, 3, 5, 4, 7, 8};
        int max = 15;
        int l = 50;
        int size = 50;
        int generations = 300;
        int mutationP = 1;
        int recombP = 100; */

        scanner();
        //runGeneticAlgorithm(w,  v, max, generations, l, size, mutationP, recombP);

    }

}