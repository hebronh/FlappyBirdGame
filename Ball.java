import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ball {
    private int x, y, size, velocityY;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.velocityY = 0;
    }

    public void draw(Graphics g, BufferedImage birdImg) {
        g.drawImage(birdImg, x, y, size, size, null);
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void update() {
        y += velocityY;
    }

    public boolean isOffCanvas(int height) {
        return y < 0 || y > height;
    }

    public void reset(int newX, int newY) {
        x = newX;
        y = newY;
        velocityY = 0;
    }
}
