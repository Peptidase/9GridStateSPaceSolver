package GridSolvingQuestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner myInp = new Scanner(System.in);

        while(true){


        System.out.println("Please enter which algorithm to run");
        System.out.println("1. A* Algorithm");
        System.out.println("2. Uniform Cost Search");

        String Userchoice = myInp.nextLine();

        if(Userchoice.equals("1")){
            System.out.println("Running A*, Please wait");
            Astar();
        }
        else if(Userchoice.equals("2")){
            System.out.println("Running Uniform Cost Search, Please wait");
            Uniform();
        }
        else{
            System.out.println("Please enter a valid input, ");
        }
                                           // Close the PrintWriter (to ensure output is produced).
        }
    }


    public static void Astar() throws Exception{ //Method for Astar solver. takes average 6 minutes on my PC
        AStarSolver Solver1 = new AStarSolver(Grid.GridArrayInitial);
        File outFile = new File("outputAstar.txt");                 // Create a file as the destination for output
        PrintWriter output = new PrintWriter(outFile);

        Solver1.solve(output);

        output.close();
    }


    public static void Uniform() throws Exception {//Method for Uniform Search solver, takes average 24 minutes on my PC
        UniformSolver Solver = new UniformSolver(Grid.GridArrayInitial);
        File outFile = new File("outputUniform.txt");                 // Create a file as the destination for output
        PrintWriter output = new PrintWriter(outFile);

        Solver.solve(output);

        output.close();
    }
}
