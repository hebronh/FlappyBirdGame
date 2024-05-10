import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball {
    private int x;
    private int y;
    private int size; 
    private int velocityY = 0; 

    public Ball(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public Rectangle getBounds() {
        int radius = size / 2;
        int boundingBoxX = x - radius;
        int boundingBoxY = y - radius;
        return new Rectangle(boundingBoxX, boundingBoxY, size, size);
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
    
    public int getVelocityY() {
        return velocityY;
    }

    public void setY(int y) { 
        this.y = y;
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

    public void update() {
        velocityY += 1;
        y += velocityY;

        if (y < 0) {
            y = 0; 
            velocityY = 0; 
        } else if (y + size > 700) {
            y = 700 - size; 
            velocityY = 0; 
        }
    }

public boolean madeItThroughPipe(Pipe topPipe, Pipe botPipe) {
    Rectangle ballBounds = getBounds();

    Rectangle topPipeBounds = topPipe.getBounds();
    Rectangle botPipeBounds = botPipe.getBounds();

    int buffer = 1; 

    Rectangle ballBufferBounds = new Rectangle(
        ballBounds.x - buffer,
        ballBounds.y - buffer,
        ballBounds.width + 2 * buffer,
        ballBounds.height + 2 * buffer
    );

    if (ballBufferBounds.intersects(topPipeBounds)) {
        if (ballBounds.intersects(topPipeBounds)) {
            return false; 
        }
    }

    if (ballBufferBounds.intersects(botPipeBounds)) {
        if (ballBounds.intersects(botPipeBounds)) {
            return false;
        }
    }

    return true;
} 
}