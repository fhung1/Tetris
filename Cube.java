import java.util.*;

class Cube extends Tetrimino{
  private ArrayList<int[]> squaresTemp = new ArrayList<int[]> (List.of(new int[]{19, 4}, new int[]{19, 5}, new int[]{18, 4}, new int[]{18, 5}));
  private int[] URBoundTemp = {19, 5};
  private int[] BLBoundTemp = {18, 4};
  public Cube(){
    squares = squaresTemp;
    URBound = URBoundTemp;
    BLBound = BLBoundTemp;
    name = "cube";
  } 

}