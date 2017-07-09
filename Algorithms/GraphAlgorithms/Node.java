package Algorithms.GraphAlgorithms;

/**
 * Created by sergio on 15/06/17.
 */
public class Node{
    protected int vertix;
    protected Node father;


    public Node(int index){
        vertix = index;
        father = null;
    }

    public Integer getVertix(){
        return vertix;
    }


}
