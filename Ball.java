import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball {
    private int x;
    private int y;
    private int size;
    private int velocityX;
    private int velocityY;

    public Ball(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }

    public void bounce(int mouseX, int mouseY) {
        velocityX = 8;
        velocityY = -10;
    }
    
    
    
    public void update() {
        x += velocityX;
        y += velocityY;
        if (x < 0 || x + size > 700) {
            velocityX = -velocityX;
        }
        if (y < 0 || y + size > 700) {
            velocityY = -velocityY;
        }
    }

    public boolean intersects(Pipe pipe) {
        Rectangle ballRect = new Rectangle(x, y, size, size);
        Rectangle pipeRect = new Rectangle(pipe.getPositionX(), (int) pipe.getY(), pipe.getWidth(), pipe.getHeight());
        return ballRect.intersects(pipeRect);
    }
    
    public int getPositionY() {
        return y;
    }
    
    public int getDiameter() {
        return size;
    }
}
