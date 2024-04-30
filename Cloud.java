import java.awt.Graphics; 
import java.awt.Color;


public class Cloud {
    private int x, y, w, h;

    public Cloud(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, w, h);
    }
}
