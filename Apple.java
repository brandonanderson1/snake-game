package game;

import java.awt.*;

public class Apple {

    private int row, col, size;

    public Apple(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.size = size;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(col * size, row * size, size, size);
    }
}
