package game;

import java.awt.*;

public class Score {
    private int score = 0;
    private int attempts = 0;
    private Color color;
    private int level = score / 10 + 1;

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel(){
        return level;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public void addAttempts(int amount) {
        attempts += amount;
    }
    public void reset() {
        score = 0;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Score: " + score, 925, 100);
        g.drawString("Deaths: " + attempts, 925, 150);
        g.drawString("Level: " + (score / 10 + 1), 925, 200);
    }
}