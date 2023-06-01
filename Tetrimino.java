import java.util.*;

public class Tetrimino {
  protected boolean isPlaced;
  protected String name;
  protected ArrayList<int[]> squares;
  protected int[] URBound;
  protected int[] BLBound;

  public Tetrimino() {
  }

  // rotates the tetrimino clockwise
  public void rotateClockwise() {
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = false;
    }
    rotateClockwiseNoChange();
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = true;
    }
  }

  // rotates the indicies clockwise without changing board values
  public void rotateClockwiseNoChange() {
    for (int[] temp : squares) {
      int xcord = temp[1];
      int ycord = temp[0];
      temp[0] = URBound[1] - xcord + BLBound[0];
      temp[1] = URBound[1] - (URBound[0] - ycord);
    }
  }

  // rotates the tetrimino counter clockwise
  public void rotateCounterClockwise() {
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = false;
    }
    rotateCounterClockwiseNoChange();
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = true;
    }
  }

  // rotates the indicies counter-clockwise without changing board values
  public void rotateCounterClockwiseNoChange() {
    for (int[] temp : squares) {
      int xcord = temp[1];
      int ycord = temp[0];
      temp[0] = xcord - BLBound[1] + BLBound[0];
      temp[1] = URBound[1] - (ycord - BLBound[0]);
    }
  }

  // moves all indicies in the square objects down on on the board
  // does NOT check if it can move down

  public void moveDown() {
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = false;
    }
    for (int[] temp : squares) {
      temp[0]--;
    }
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = true;
    }

    URBound[0]--;
    BLBound[0]--;
  }

  public void moveLeft() {
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = false;
    }
    for (int[] temp : squares) {
      temp[1]--;
    }
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = true;
    }
    URBound[1]--;
    BLBound[1]--;
  }

  public void moveRight() {
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = false;
    }
    for (int[] temp : squares) {
      temp[1]++;
    }
    for (int[] temp : squares) {
      Game.board[temp[0]][temp[1]] = true;
    }
    URBound[1]++;
    BLBound[1]++;
  }

  // returns an arraylist of the lowest squares in each column
  public ArrayList<int[]> getLowSquares() {
    ArrayList<int[]> lowSquares = new ArrayList<int[]>();
    for (int i = 0; i < 10; i++) {
      int[] lowest = null;
      for (int[] coords : squares) {
        if (coords[1] == i && (lowest == null || coords[0] < lowest[0]))
          lowest = coords;
      }
      if (lowest != null)
        lowSquares.add(lowest);
    }
    return lowSquares;
  }

  // returns an arraylist of the rightmost squares in each row
  public ArrayList<int[]> getRightSquares() {
    ArrayList<int[]> rightSquares = new ArrayList<int[]>();
    for (int i = 0; i < 20; i++) {
      int[] rightest = null;
      for (int[] coords : squares) {
        if (coords[0] == i && (rightest == null || coords[1] > rightest[1]))
          rightest = coords;
      }
      if (rightest != null)
        rightSquares.add(rightest);
    }
    return rightSquares;
  }

  // returns an arraylist of the leftmost squares in each row
  public ArrayList<int[]> getLeftSquares() {
    ArrayList<int[]> leftSquares = new ArrayList<int[]>();
    for (int i = 0; i < 20; i++) {
      int[] leftest = null;
      for (int[] coords : squares) {
        if (coords[0] == i && (leftest == null || coords[1] < leftest[1]))
          leftest = coords;
      }
      if (leftest != null)
        leftSquares.add(leftest);
    }
    return leftSquares;
  }

  // returns squares
  public ArrayList<int[]> getSquares() {
    return squares;
  }

  public String getName() {
    return name;
  }

}