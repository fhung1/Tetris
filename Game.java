import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Game {
  private static ArrayList<Tetrimino> next = new ArrayList<Tetrimino>();
  private static Tetrimino current; // public or static?
  private static ArrayList<PlacedSquare> placed = new ArrayList<PlacedSquare>();
  public static boolean[][] board = new boolean[20][10];
  private static double rowsPerSecond;
  private static int score = 0;
  private static int level = 1;
  private static int clearedRows = 0;
  public static GamePanel panel = new GamePanel();

  public Game() {
    next.add(getRandom());
    next.add(getRandom());
    next.add(getRandom());
    current = getRandom();

    for (int[] coords : current.getSquares())
      board[coords[0]][coords[1]] = true;
    rowsPerSecond = Math.pow(1.1, level - 1);
    runTime();
  }

  // runs the game
  private void runTime() {
    while (findEmptyRow() != -1) {
      long startTime = System.currentTimeMillis();
      runTick(startTime);
      if (clearedRows >= (level) + 3) {
        // if (level == 20)
        // gameWon();
        level++;
        clearedRows = 0;
        rowsPerSecond = Math.pow(1.1, level - 1);
      }
    }
    // gameLost();
  }

  public static void printBoard() {
    for (int i = 19; i >= 0; i--) {
      for (boolean index : board[i]) {
        if (index)
          System.out.print(1 + " ");
        else
          System.out.print(0 + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  // runs one tick of the game
  private void runTick(long startTime) {
    while ((System.currentTimeMillis() - startTime) / 1000.0 < (1.0 / rowsPerSecond)) {
    }
    moveRowsDown(findEmptyRow());
    if (!moveDownIfCan()) {
      for (int[] square : current.getSquares())
        placed.add(new PlacedSquare(square, current.getName()));
      current = next.remove(0);
      for (int[] coords : current.getSquares())
        board[coords[0]][coords[1]] = true;
      if (findEmptyRow() >= findHighest())
        next.add(getRandom());

      ArrayList<Integer> fullRows = findFullRows();
      if (fullRows.size() != 0) {
        clearedRows += fullRows.size();
        removeFullRows(fullRows);
        if (fullRows.size() == 1)
          score += 40 * (level + 1);
        else if (fullRows.size() == 2)
          score += 100 * (level + 1);
        else if (fullRows.size() == 3)
          score += 300 * (level + 1);
        else if (fullRows.size() == 4)
          score += 1200 * (level + 1);
      }
    }
    panel.getBoardDisplay().repaint();
  }

  // moves the Tetrimino down if it can be, then returns true
  // else, returns false
  public static boolean moveDownIfCan() {
    for (int[] coords : current.getLowSquares())
      if (coords[0] == 0 || board[coords[0] - 1][coords[1]] == true)
        return false;
    current.moveDown();
    return true;
  }

  // rotates the Tetrimino clockwise if it can be, then returns true
  // else, returns false
  public static boolean clockwiseIfCan() {
    ArrayList<int[]> oldCoords = (ArrayList<int[]>) current.getSquares().clone();
    current.rotateClockwiseNoChange();
    for (int[] coords : current.getSquares()) {
      if (coords[0] > 19 || coords[0] < 0 || coords[1] < 0 || coords[1] > 9
          || (board[coords[0]][coords[1]] == true && !isOccupiedByPrev(coords, oldCoords))) {
        current.rotateCounterClockwiseNoChange();
        return false;
      }
    }
    current.rotateCounterClockwiseNoChange();
    current.rotateClockwise();
    return true;

  }

  // rotates the Tetrimino counter-clockwise if it can be, then returns true
  // else, returns false
  public static boolean counterClockwiseIfCan() {
    ArrayList<int[]> oldCoords = (ArrayList<int[]>) current.getSquares().clone();
    current.rotateCounterClockwiseNoChange();
    for (int[] coords : current.getSquares()) {
      if (coords[0] > 19 || coords[0] < 0 || coords[1] < 0 || coords[1] > 9
          || (board[coords[0]][coords[1]] == true && !isOccupiedByPrev(coords, oldCoords))) {
        current.rotateClockwiseNoChange();
        return false;
      }
    }
    current.rotateClockwiseNoChange();
    current.rotateCounterClockwise();
    return true;

  }

  // returns true if the int array coords is present in oldCoords
  private static boolean isOccupiedByPrev(int[] coords, ArrayList<int[]> oldCoords) {
    for (int[] temp : oldCoords)
      if (temp[0] == coords[0] && temp[1] == coords[1])
        return true;
    return false;
  }

  // moves the Tetrimino right if it can be, then returns true
  // else, returns false
  public static boolean moveRightIfCan() {
    for (int[] coords : current.getRightSquares())
      if (coords[1] == 9 || board[coords[0]][coords[1] + 1] == true)
        return false;
    current.moveRight();
    return true;
  }

  // moves the Tetrimino left if it can be, then returns true
  // else, returns false
  public static boolean moveLeftIfCan() {
    for (int[] coords : current.getLeftSquares())
      if (coords[1] == 0 || board[coords[0]][coords[1] - 1] == true)
        return false;
    current.moveLeft();
    return true;
  }

  // returns an arraylist of the rows full of squares
  private ArrayList<Integer> findFullRows() {
    ArrayList<Integer> fullRows = new ArrayList<Integer>();
    for (int r = 0; r < 20; r++) {
      for (int c = 0; c < 10; c++) {
        if (board[r][c] == false)
          break;
        if (c == 9)
          fullRows.add(r);
      }
    }
    return fullRows;
  }

  // returns the first empty row
  private int findEmptyRow() {
    for (int r = 0; r < 20; r++) {
      for (int c = 0; c < 10; c++) {
        if (board[r][c] == true)
          break;
        if (c == 9)
          return r;
      }
    }
    return -1;
  }

  // sets the given rows of board to false
  private void removeFullRows(ArrayList<Integer> rows) {
    for (Integer r : rows) {
      for (int c = 0; c < 10; c++) {
        board[r][c] = false;
      }
      for (int i = 0; i < placed.size(); i++) {
        if (placed.get(i).getCoords()[0] == r) {
          placed.remove(i);
          i--;
        }
      }
    }
  }

  // moves all squares with space below them down one squares
  // emptyRows must be an increasing array
  private void moveRowsDown(int emptyRow) {
    ArrayList<PlacedSquare> moveTheseDown = getCoords(emptyRow + 1);
    for (PlacedSquare coords : moveTheseDown) {
      board[coords.getCoords()[0]][coords.getCoords()[1]] = false;
    }
    for (PlacedSquare coords : moveTheseDown) {
      coords.getCoords()[0]--;
    }
    for (PlacedSquare coords : moveTheseDown) {
      board[coords.getCoords()[0]][coords.getCoords()[1]] = true;
    }

  }

  // returns an arraylist of the coords in placed that are in this row or above
  private static ArrayList<PlacedSquare> getCoords(int row) {
    ArrayList<PlacedSquare> coords = new ArrayList<PlacedSquare>();
    for (PlacedSquare c : placed)
      if (c.getCoords()[0] >= row)
        coords.add(c);
    return coords;
  }

  // returns a random instantiated Tetrimino
  private Tetrimino getRandom() {
    Random rand = new Random();
    int piece = rand.nextInt(7);
    if (piece == 0)
      return new Line();
    else if (piece == 1)
      return new Cube();
    else if (piece == 2)
      return new LPiece();
    else if (piece == 3)
      return new ReverseLPiece();
    else if (piece == 4)
      return new ReverseSquiggly();
    else if (piece == 5)
      return new Squiggly();
    else
      return new T();
  }

  public static Tetrimino getCurrent() {
    return current;
  }

  public static ArrayList<PlacedSquare> getPlaced() {
    return placed;
  }

  public static int getScore() {
    return score;
  }

  public static int getLevel() {
    return level;
  }

  public static boolean[][] getBoard() {
    return board;
  }

  // returns the adress of the placedSquare given the row and column
  public static PlacedSquare getSquare(int row, int col) {
    for (PlacedSquare square : placed) {
      if (square.getCoords()[0] == row && square.getCoords()[1] == col)
        return square;
    }
    return null;
  }

  // returns the arraylist of the next tetriminos
  public static ArrayList<Tetrimino> getNext() {
    return next;
  }

  // returns the row that the highest placed square is in
  private static int findHighest() {
    int high = 0;
    for (PlacedSquare square : placed) {
      if (square.getCoords()[0] > high)
        high = square.getCoords()[0];
    }
    return high;
  }
}
