package GridSolvingQuestion;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;


public class UniformSolver {

    ArrayList<Node> unexpanded = new ArrayList<Node>(); //The arraylist that stores all the nodes that are unexpanded
    ArrayList<Node> expanded = new ArrayList<Node>(); //The arraylist that stores all the nodes that are expanded
    Node rootNode; //The starting root node

    //Sets up the initial Node with the intended final goal board specified.
    UniformSolver(int[][] initialBoard){


        Grid InitialState = new Grid(initialBoard);
        rootNode = new Node(InitialState);
    }



    public void solve(PrintWriter PW) throws Exception { //Throws an exception as the expand method throws an exception
        //for trying to move all directions and returning the resulting list.
        unexpanded.add(rootNode);
        int moves = 0;
        //Initial moves and rootnode.
        while(unexpanded.size() > 0){
            int LowestCost = Integer.MAX_VALUE; //Max value to compare costs to
            int nextIndex = 0;// THe index of the next node to be expanded
            Node next;//the next node to be expanded
            for (Node n:unexpanded) //find the lowest costing node.
            {
                if (n.getCost() < LowestCost){
                    nextIndex = unexpanded.indexOf(n); // Get the index of the lowest node
                    LowestCost = n.getCost();
                }
            }
            next = unexpanded.get(nextIndex);
            if(next.State.isGoal()){
                System.out.println("Goal Found");
                reportSolution(next, PW);

                return;
            }
            expanded.add(next);
            unexpanded.remove(next);
            moves++;
            System.out.println(next.toString());
            System.out.println(moves);
            System.out.println(next.getCost());
            ArrayList<Grid> moveList = next.State.expand();

            for(Grid g:moveList){
                if(Node.findNodeWithState(unexpanded,g) == null && (Node.findNodeWithState(expanded,g)) ==null){
                        int newCost = next.getCost() + 1;
                        Node NewNode = new Node(g,next,newCost,next.getDepth()+1);
                        unexpanded.add(NewNode);
                }
            }



        }
        System.out.println("No solution found");


    }

    public void printSolution(Node n, PrintWriter PW) {
        if (n.parent != null) printSolution(n.parent,PW);
        PW.println(n.toString());

    }
    public void reportSolution(Node n, PrintWriter PW) {
        PW.println("Solution found!");
        printSolution(n, PW);
        PW.println(n.getDepth() + " Moves");
        PW.println("Nodes expanded: " + this.expanded.size());
        PW.println("Nodes unexpanded: " + this.unexpanded.size());
        PW.println();
    }





}
