package game;

import utilities.GDV5;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SnakeRunner extends GDV5 {

    private Tile[][] board = new Tile[15][15];
    private Snake snake;
    private Apple apple;
    private Apple apple2;
    private Apple apple3;
    private Score score = new Score(0);
    private int level = 1;
    private boolean gameStarted = false;
    private boolean gameOver = false;

    public SnakeRunner() {
        super();
        int x = 0, y = 0, size = 60;
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                board[row][col] = new Tile(x, y, size, size, row, col);
                x += size;
            }
            x = 0;
            y += size;
        }
        snake = new Snake(board, 7, 7);
        spawn();
    }


    public static void main(String[] args) {
        SnakeRunner s = new SnakeRunner();
        s.start();
    }

    public void reset() {
        gameStarted = false;
        gameOver = false;
        snake = new Snake(board, 7, 7);
        score.reset();
        level = 1;
        apple2 = null;
        apple3 = null;
        spawn();
    }

    @Override
    public void update() {
        if (!gameStarted) {
            if (KeysPressed[KeyEvent.VK_UP] ||
                    KeysPressed[KeyEvent.VK_DOWN] ||
                    KeysPressed[KeyEvent.VK_LEFT] ||
                    KeysPressed[KeyEvent.VK_RIGHT]) {

                gameStarted = true;
            }
            return;
        }
        if (gameOver) {
            if (KeysPressed[KeyEvent.VK_UP] ||
                    KeysPressed[KeyEvent.VK_DOWN] ||
                    KeysPressed[KeyEvent.VK_LEFT] ||
                    KeysPressed[KeyEvent.VK_RIGHT]) {

                reset();
            }
            return;
        }

        snake.checkDirection();

        if (!snake.move()) {
            score.addAttempts(1);
            gameOver = true;
            return;
        }

        level = score.getScore() / 10 + 1;

        if (snake.eatApple(apple, score) ||
                (apple2 != null && snake.eatApple(apple2, score)) ||
                (apple3 != null && snake.eatApple(apple3, score))) {

            spawn();
        }
    }

    public Apple makeApple(Apple a1, Apple a2) {
        int appleRow;
        int appleCol;
        boolean badSpot;

        do {
            appleRow = (int) (Math.random() * board.length);
            appleCol = (int) (Math.random() * board[0].length);

            badSpot = false;

            for (Tile t : snake.getBody()) {
                if (t.getRow() == appleRow && t.getCol() == appleCol) {
                    badSpot = true;
                }
            }

            if (a1 != null && a1.getRow() == appleRow && a1.getCol() == appleCol) {
                badSpot = true;
            }

            if (a2 != null && a2.getRow() == appleRow && a2.getCol() == appleCol) {
                badSpot = true;
            }

        } while (badSpot);

        return new Apple(appleRow, appleCol, board[0][0].width);
    }

    public void spawn() {
        apple = makeApple(null, null);

        if (level >= 2) {
            apple2 = makeApple(apple, null);
        } else {
            apple2 = null;
        }

        if (level >= 3) {
            apple3 = makeApple(apple, apple2);
        } else {
            apple3 = null;
        }
    }

    @Override
    public void draw(Graphics2D win) {

        if (!gameStarted) {
            win.setColor(Color.BLACK);
            win.fillRect(0, 0, getWidth(), getHeight());

            win.setColor(Color.WHITE);
            win.setFont(new Font("Arial", Font.BOLD, 50));
            win.drawString("Brandon Anderson's Snake Game", 150, 350);

            win.setFont(new Font("Arial", Font.PLAIN, 30));
            win.drawString("Eat apples to grow and gain points.", 230, 490);
            win.drawString("More apples will spawn as the levels increase.", 230, 560);
            win.drawString("Avoid hitting walls and running into yourself.", 230, 630);
            win.drawString("Press an Arrow Key to start!", 230, 700);

            return;
        }

        for (Tile[] row : board) {
            for (Tile t : row) {
                t.draw(win);
            }
        }

        snake.draw(win);

        if (apple != null) {
            apple.draw(win);
        }
        if (apple2 != null) {
            apple2.draw(win);
        }
        if (apple3 != null) {
            apple3.draw(win);
        }

        score.draw(win);

        if (gameOver) {
            win.setColor(new Color(0, 0, 0, 150));
            win.fillRect(0, 0, getWidth(), getHeight());

            win.setColor(Color.WHITE);
            win.setFont(new Font("Arial", Font.BOLD, 50));
            win.drawString("Game Over", 320, 350);

            win.setFont(new Font("Arial", Font.PLAIN, 30));
            win.drawString("Press any arrow key to restart", 240, 420);
        }
    }
}