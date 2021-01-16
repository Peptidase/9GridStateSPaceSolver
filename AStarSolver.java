package GridSolvingQuestion;

import java.io.PrintWriter;
import java.util.ArrayList;

public class AStarSolver extends UniformSolver {


    AStarSolver(int[][] initialBoard) {
        super(initialBoard);
    }


    @Override
    public void solve(PrintWriter PW) throws Exception {
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
                    int newCost = g.heuristic()+ next.getCost() + 1;
                    Node NewNode = new Node(g,next,newCost,next.getDepth()+1);
                    unexpanded.add(NewNode);
                }
            }



        }
        System.out.println("No solution found");
    }
}
