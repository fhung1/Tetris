import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JFrame {
  public static BoardDisplay board;
  private JFrame window;
  public static final int WIDTH = 550;
  public static final int HEIGHT = 629;
  public static int score = 0;
  public static JLabel scoreTracker;

  GamePanel() {
    window = new JFrame("Tetris");
    window.setSize(WIDTH, HEIGHT);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setBackground(Color.BLACK);
    scoreTracker = new JLabel("Score: " + score, SwingConstants.RIGHT);

    board = new BoardDisplay();
    window.add(board);
    window.setVisible(true);

    window.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37)
          Game.moveLeftIfCan();
        else if (e.getKeyCode() == 39)
          Game.moveRightIfCan();
        else if (e.getKeyCode() == 40)
          Game.moveDownIfCan();
        else if (e.getKeyCode() == 74)
          Game.counterClockwiseIfCan();
        else if (e.getKeyCode() == 75)
          Game.clockwiseIfCan();
        else if (e.getKeyCode() == 32)
          while (Game.moveDownIfCan()) {
            Game.panel.getBoardDisplay().repaint();
          }
        Game.panel.getBoardDisplay().repaint();
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
  }

  public static BoardDisplay getBoardDisplay() {
    return board;
  }

  public static int returnWidth() {
    return WIDTH;
  }

  public static int returnHeight() {
    return WIDTH;
  }
}
