package game;

import java.awt.*;

public class Tile extends Rectangle {
    private int row, col;
    private Color color = Color.white;

    public Tile(int x, int y, int width, int height, int r, int c){
        super(x, y, width, height);
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void draw(Graphics2D pb){
        pb.setColor(color);
        pb.draw(this);
    }
}
