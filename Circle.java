import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Circle {
    private int x;
    private int y;
    private int size;
    private int velocityX;
    private int velocityY;

    // Constructor
    public Circle(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    // Getter and setter for velocityX
    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    // Getter and setter for velocityY
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
        int distanceX = mouseX - (x + size / 2);
        int distanceY = mouseY - (y + size / 2);
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        int separation = -50; // Adjust this value to control the separation
        
        x = mouseX - (int)(distanceX * (size / 2 - separation) / distance) - size / 2;
        y = mouseY - (int)(distanceY * (size / 2 - separation) / distance) - size / 2;
        velocityX = 0;
        velocityY = 0;
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
        Rectangle circleRect = new Rectangle(x, y, size, size);
        Rectangle pipeRect = new Rectangle(pipe.getPositionX(), (int) pipe.getY(), pipe.getWidth(), pipe.getHeight());
        return circleRect.intersects(pipeRect);
    }
    
    // New method to get the position along the y-axis
    public int getPositionY() {
        return y;
    }
    
    // New method to get the diameter of the circle
    public int getDiameter() {
        return size;
    }
}
