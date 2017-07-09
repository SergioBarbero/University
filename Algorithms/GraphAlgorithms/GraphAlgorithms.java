package Algorithms.GraphAlgorithms;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sergio on 13/06/17.
 */
public class GraphAlgorithms {

    /**
     * If there is no direct connection between vertices (i,j) -> weight[i][j] = 1000
     */
    int[][] weights;
    int[][] dist;
    Integer[][] next;

    public GraphAlgorithms(int[][] weights){
        this.weights = weights;
    }



    public int[][] floydWarshall() {
        //Initialize our matrix dist and matrix of next nodes
        next = new Integer[weights.length][weights.length];
        dist = new int[weights.length][weights.length];
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = weights[i][j];
                    next[i][j] = j;
                }
            }
        }


        //Updating distances
        for (int k = 0; k < weights.length; k++) {
            for (int i = 0; i < weights.length; i++) {
                if (dist[i][k] != 1000) {
                    for (int j = 0; j < weights[0].length; j++) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            next[i][j] = next[i][k];
                        }
                    }
                }
            }
        }

        return dist;
    }

    public List<Integer> path(int i, int j){

        if (next[i][j] == null){
            return null;
        }else{
            List path =new ArrayList<>();
            path.add(i);
            while (i != j){
                i = next[i][j];
                path.add(i);
            }
            return path;
        }

    }

    public Node BreathFirstSearch(int root, int goal){
        List<Node> trace = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();

        Node s = new Node(root);
        Node f = new Node(goal);

        trace.add(s);
        queue.add(s);

        while(!queue.isEmpty()){
            Node current = queue.poll();
            if( current.vertix == goal){
                return current;
            }else{
                for(int i = 0; i < weights.length; i++){
                    if(weights[current.vertix][i] != 1000 && weights[current.vertix][i] != 0){
                        Node sucessor = new Node(i);
                        List<Integer> vertices = trace.stream().map(Node::getVertix).collect(Collectors.toList());
                        if(!vertices.contains(i)){
                            Node x = new Node(i);
                            x.father = current;
                            trace.add(x);
                            queue.add(x);
                        }
                    }
                }
            }
        }
        return null;
    }

    public List<Integer> retrieveTrace(Node goal, int root){
        List<Integer> trace = new ArrayList<>();
        Node f = goal;
        trace.add(f.vertix);
        while(f.vertix != root){
            trace.add(f.father.vertix);
            f = f.father;
        }
        //Collections.reverse(trace);
        for(Integer x: trace){
            System.out.print(" -> " + x);
        }
        return trace;

    }


    public static void main(String[] args) {
        int[][] graph = {{0,1000,-2,1000},{4,0,3,1000},{1000,1000,0,2},{1000,-1,1000,0}};
        GraphAlgorithms problem = new GraphAlgorithms(graph);

        int[][] paths = problem.floydWarshall();

        System.out.println("FloydWarsall Table: ");
        for(int i=0; i < paths.length; i++){
            for(int j=0; j < paths.length; j++){
                System.out.print(paths[i][j] + " ");
            }
            System.out.println();
        }

        Node goal = problem.BreathFirstSearch( 3, 2);

        System.out.println("Breath First Search algorithm");
        problem.retrieveTrace(goal, 3 );



    }




}
