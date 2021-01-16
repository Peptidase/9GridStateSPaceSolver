package GridSolvingQuestion;

import java.util.ArrayList;

public class Grid {
    //This class is used to define the AssignmentFiles.Grid as an object, It contains functions
    //To calculate the heuristics of the current Array, as well as move squares between each position


    int[][] GridArray = {{8,7,6},
                        {5,4,3},
                        {2,1,0}};

    static final int[][] GridArrayInitial = {{8,7,6},
                                             {5,4,3},
                                            {2,1,0}};
    //accessable Goal and Initial Root nodes set as static.
    static final int[][] GOAL = {{0,1,2},
                                {3,4,5},
                                {6,7,8}};



    //Constructor that takes a 2d array and generates a grid from it.
    //It specifies the values iteratively. Useful for copying values.
    public  Grid(int[][] GA){
        for(int i = 0; i<3 ;i++){
            for(int i2 = 0; i2<3; i2++){
                GridArray[i][i2] = GA[i][i2];
            }
        }
    }


    //Method which is used to return the Matrix/grid in a readable format which we print to the output.txt
    public String toString(){
        String out ="";

        for (int[] i1:GridArray) {
            out+="";
            for (int i2: i1) {
                out+=i2;
                out+=" ";
            }
        }

    return out;
    }

    public Grid clone(Grid grid){
        int[][] GridArray = new int[2][2];

        for (int i = 0; i < 3; i++) {
            for(int i2 = 0; i2 < 3; i2++){
                this.GridArray[i][i2] = grid.GridArray[i][i2];
                }
            }

        return new Grid(GridArray);
        }



        public boolean isGoal(){
            for (int i = 0; i < 3; i++) {
                for(int i2 = 0; i2 < 3; i2++){
                    if(this.GridArray[i][i2] != GOAL[i][i2]){
                     return false;
                    }
                }
            }
            return true;
        }


        public boolean sameBoard(Grid g){
            for (int i = 0; i < 3; i++) {
                for(int i2 = 0; i2 < 3; i2++){
                    if(this.GridArray[i][i2] != g.GridArray[i][i2]){
                        return false;
                    }
                }
            }
            return true;
        }




    //Below is a private function that will return an array with the values of where the passed item is
    //Made to allow Move functions to quickly manage illegal movements and check for if moves are possible.
    //Also made incase in the future I want to know where another tile is for heuristic sake?
    private int[] location(int value){
        int[] loc = new int[2];
        int GridNum;
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2<3;i2++) {
                GridNum = GridArray[i][i2];
                if(GridArray[i][i2] == (value)){
                    loc[0] = i;
                    loc[1] = i2;
                }


            }
        }
        return loc; //Return is y,x
    }

    ArrayList<Grid> expand() throws Exception { //TODO maybe calculate which moves it can do, instead of 4 try catches?
        ArrayList<Grid> ExpansionNodes = new ArrayList<Grid>(); //Will store the plausible expanded nodes
        Grid EU = new Grid(this.GridArray);
        Grid ED = new Grid(this.GridArray);
        Grid EL = new Grid(this.GridArray);
        Grid ER = new Grid(this.GridArray);
        //Essentially will just try to move all of the nodes a specific way, if an exception occurs we ignore it and they
        //Arent added to the ExpansionNodes arraylist

        try{
            EU.Moveup();
            ExpansionNodes.add(EU);
        }catch (Exception ignore){}
        try {
            ED.Movedown();
            ExpansionNodes.add(ED);
        }catch (Exception ignore){}
        try{
            EL.Moveleft();
            ExpansionNodes.add(EL);
        }catch (Exception ignore){}
        try {
            ER.Moveright();
            ExpansionNodes.add(ER);
        }catch (Exception ignore){}


        return ExpansionNodes;
    }



    //Below are movement functions to change position of the AssignmentFiles.Grid, each with their Exceptions defined.
    //They move the tile respective to the space (0) e.g.
    // moveup will move the tile below the 0 upwards.
    // moveleft will move the tile to the right of the 0 left

    public void Moveup() throws Exception {
        int[] loc = location(0);
        if(loc[0] != 2)
        {
            GridArray[loc[0]][loc[1]] = GridArray[loc[0]+1][loc[1]];
            GridArray[loc[0]+1][loc[1]] = 0;

        }
        else{

            throw new Exception("Illegal Move!"); //Throws an exception so I know what is wrong with program
        }
    }
    public void Movedown() throws Exception {
        int[] loc = location(0);
        if(loc[0] != 0)
        {
            GridArray[loc[0]][loc[1]] = GridArray[loc[0]-1][loc[1]];
            GridArray[loc[0]-1][loc[1]] = 0;

        }
        else{
            throw new Exception("Illegal Move!");//Throws an exception so I know what is wrong with program
        }
    }
    public void Moveright() throws Exception {
        int[] loc = location(0);
        if(loc[1] != 0)
        {
            GridArray[loc[0]][loc[1]] = GridArray[loc[0]][loc[1]-1];
            GridArray[loc[0]][loc[1]-1] = 0;

        }
        else{
            throw new Exception("Illegal Move!");//Throws an exception so I know what is wrong with program
        }
    }
    public void Moveleft() throws Exception {
        int[] loc = location(0);
        if(loc[1] != 2)
        {
            GridArray[loc[0]][loc[1]] = GridArray[loc[0]][loc[1]+1];
            GridArray[loc[0]][loc[1]+1] = 0;

        }
        else{
            throw new Exception("Illegal Move!");//Throws an exception so I know what is wrong with program
        }
    }

    public int heuristic(){

        //Returns the manhatten distance by integer.
      int h = 0;
      int[] loc = this.location(0);
      h += Math.abs(loc[0] - 0);
      h += Math.abs(loc[1] - 0);

      loc = this.location(1);
      h += Math.abs(loc[0] - 0);
      h += Math.abs(loc[1] - 1);

      loc = this.location(2);
      h += Math.abs(loc[0] - 0);
      h += Math.abs(loc[1] - 2);

      loc = this.location(3);
      h += Math.abs(loc[0] - 1);
      h += Math.abs(loc[1] - 0);

      loc = this.location(4);
      h += Math.abs(loc[0] - 1);
      h += Math.abs(loc[1] - 1);

      loc = this.location(5);
      h += Math.abs(loc[0] - 1);
      h += Math.abs(loc[1] - 2);

      loc = this.location(6);
      h += Math.abs(loc[0] - 2);
      h += Math.abs(loc[1] - 0);

      loc = this.location(7);
      h += Math.abs(loc[0] - 2);
      h += Math.abs(loc[1] - 1);

      loc = this.location(8);
      h += Math.abs(loc[0] - 2);
      h += Math.abs(loc[1] - 2);


      return h;
  }





}
