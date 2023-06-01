import java.awt.*;
import javax.swing.*;

class BoardDisplay extends JPanel {
    static final int BLOCK_SIZE = 30;
    static final int BOARD_WIDTH = 10;
    static final int BOARD_HEIGHT = 20;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 550, 629);

        g.setColor(Color.WHITE);
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            g.drawLine(0, BLOCK_SIZE * row, BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * row);
        }
        for (int col = 0; col < BOARD_WIDTH + 1; col++) {
            g.drawLine(col * BLOCK_SIZE, 0, col * BLOCK_SIZE, BLOCK_SIZE * BOARD_HEIGHT);
        }
        arrToBoard(g);
    }

    public static void arrToBoard(Graphics g) {
        for (int r = 0; r < Game.getBoard().length; r++) {
            for (int c = 0; c < Game.getBoard()[r].length; c++) {
                if (Game.getBoard()[r][c]) {
                    PlacedSquare currBlock = Game.getSquare(r, c);
                    String name;
                    if (currBlock != null) {
                        name = currBlock.getName();

                    } else {
                        name = Game.getCurrent().getName();

                    }

                    Color currColor;
                    if (name.equals("reverse l")) { // Blue
                        currColor = Color.BLUE;
                    } else if (name.equals("squiggly")) {// Red
                        currColor = Color.RED;
                    } else if (name.equals("reverse s")) {// Green
                        currColor = Color.GREEN;
                    } else if (name.equals("line")) {// LightBlue
                        currColor = Color.CYAN;
                    } else if (name.equals("l")) {// Orange
                        currColor = Color.ORANGE;
                    } else if (name.equals("t")) {// Purple
                        currColor = Color.MAGENTA;
                    } else {// Yellow
                        currColor = Color.YELLOW;
                    }
                    g.setColor(Color.BLACK);
                    g.drawRect(c * BLOCK_SIZE, (19 - r) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(currColor);
                    g.fillRect(c * BLOCK_SIZE, (19 - r) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            Color nextColor;
            Tetrimino current = Game.getNext().get(i);
            if (current.getName().equals("reverse l")) { // Blue
                nextColor = Color.BLUE;
            } else if (current.getName().equals("squiggly")) {// Red
                nextColor = Color.RED;
            } else if (current.getName().equals("reverse s")) {// Green
                nextColor = Color.GREEN;
            } else if (current.getName().equals("line")) {// LightBlue
                nextColor = Color.CYAN;
            } else if (current.getName().equals("l")) {// Orange
                nextColor = Color.ORANGE;
            } else if (current.getName().equals("t")) {// Purple
                nextColor = Color.MAGENTA;
            } else {// Yellow
                nextColor = Color.YELLOW;
            }
            g.setColor(nextColor);
            for (int[] coords : Game.getNext().get(i).getSquares()) {
                g.fillRect(300 + coords[1] * BLOCK_SIZE / 2, 100 + (19 - coords[0]) * BLOCK_SIZE / 2 + 50 * i,
                        BLOCK_SIZE / 2,
                        BLOCK_SIZE / 2);
            }
        }
        g.setColor(Color.white);
        g.drawString("Score: " + Game.getScore() + "  Level: " + Game.getLevel(), 400, 471);

    }

}