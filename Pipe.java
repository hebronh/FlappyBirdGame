import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pipe {
    private int x; 
    private double y; 
    private int width; 
    private int height; 

    public Pipe(int x, double y, int width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = (int) height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, (int) y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, (int) y, width, height);
    }

    public void setPositionX(int newX) {
        this.x = newX;
    }

    public int getPositionX() {
        return this.x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getY() {
        return y;
    }

    public boolean isOffCanvas() {
        return x + width < 0;
    }
}