import java.util.*;

class Squiggly extends Tetrimino{
  private ArrayList<int[]> squaresTemp = new ArrayList<int[]> (List.of(new int[]{19, 4}, new int[]{19, 5}, new int[]{18, 3}, new int[]{18, 4}));
  private int[] URBoundTemp = {20, 5};
  private int[] BLBoundTemp = {18, 3};
  public Squiggly(){
    squares = squaresTemp;
    URBound = URBoundTemp;
    BLBound = BLBoundTemp;
    name = "squiggly";   
  }  
}