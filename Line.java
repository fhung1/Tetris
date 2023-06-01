import java.util.*;

public class Line extends Tetrimino{
  private ArrayList<int[]> squaresTemp = new ArrayList<int[]> (List.of(new int[]{19, 3}, new int[]{19, 4}, new int[]{19, 5}, new int[]{19, 6}));
  private int[] URBoundTemp = {21, 6};
  private int[] BLBoundTemp = {18, 3};
  
  public Line(){
    squares = squaresTemp;
    URBound = URBoundTemp;
    BLBound = BLBoundTemp;
    name = "line";
  }  
}