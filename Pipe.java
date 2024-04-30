import java.awt.Graphics; 
import java.awt.Color;


public class Pipe {
    private int x, width, height;
    private double y;

    public Pipe(int x, double y, int width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = (int) height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, (int) y, width, height);
    }

    public void setPositionX(int newX) {
        this.x = newX;
    }

    public int getPositionX() {
        return this.x;
    }

    public boolean isOffCanvas() {
        return x + width < 0;
    }
}
