package game;

import utilities.GDV5;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static utilities.GDV5.KeysPressed;

public class Snake extends Rectangle {
    private ArrayList<Tile> body = new ArrayList<>();
    private Tile[][] board;
    private int direction;
    public static final int up = 0;
    public static final int right = 1;
    public static final int down = 2;
    public static final int left = 3;
    private Color color = Color.green;
    private int row;
    private int col;
    private boolean grow = false;
    private Score score;

    public Snake(Tile[][] board, int row, int col) {
        this.board = board;
        this.row = row;
        this.col = col;
        direction = right;
        body.add(board[row][col]);
        body.add(board[row][col - 1]);
        body.add(board[row][col - 2]);
    }

    public void checkDirection() {
        if (KeysPressed[KeyEvent.VK_UP] && direction != down) {
            direction = up;
        }
        if (KeysPressed[KeyEvent.VK_RIGHT] && direction != left) {
            direction = right;
        }
        if (KeysPressed[KeyEvent.VK_DOWN] && direction != up) {
            direction = down;
        }
        if (KeysPressed[KeyEvent.VK_LEFT] && direction != right) {
            direction = left;
        }
    }

    public boolean move() {

        if (direction == up) row--;
        if (direction == down) row++;
        if (direction == left) col--;
        if (direction == right) col++;

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        Tile next = board[row][col];

        for (Tile t : body) {
            if (t == next) {
                return false;
            }
        }

        body.add(0, board[row][col]);

        if (!grow) {
            body.remove(body.size() - 1);
        }

        return true;
    }

    public boolean eatApple(Apple apple, Score score) {
        if (apple != null && row == apple.getRow() && col == apple.getCol()) {
            grow = true;
            score.addScore(1);
            return true;
        }
        grow = false;
        return false;
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public void draw(Graphics2D pb) {
        for (int i = 0; i < body.size(); i++) {
            Tile t = body.get(i);

            if (i % 2 == 0) {
                pb.setColor(Color.GREEN);
            } else {
                pb.setColor(Color.YELLOW);
            }

            pb.fillRect(t.x, t.y, t.width, t.height);
        }
    }
}