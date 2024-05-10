import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlappyBird extends JPanel {
    private Ball ball;
    private Pipe topPipe, botPipe;
    private int score;
    private final int GAP_SIZE = 140;
    private boolean gameOver = false;
    private Clouds clouds; 

    public FlappyBird() {
        setPreferredSize(new Dimension(700, 700));
        initGame();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameOver) {
                    gameOver = false;
                    score = 0;
                    initGame();
                } else {
                    // Set a consistent upward velocity when the mouse is pressed
                    ball.setVelocityY(-10);
                }
            }
        }); 
    }
        

    private void initGame() {
        ball = new Ball(getWidth() / 2, getHeight() / 2, 20);
        makePipes();
        score = 0;
        clouds = new Clouds(getWidth()); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        // sky background
        g.setColor(new Color(120, 160, 190));
        g.fillRect(0, 0, getWidth(), getHeight());

        // clouds 
        clouds.draw(g);

        topPipe.draw(g);
        botPipe.draw(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(String.valueOf(score), getWidth() - 100, 100);
        ball.draw(g);

        // game over text
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", getWidth() / 2 - 150, getHeight() / 2);
        }

        // update game 
        if (!gameOver) {
            updateGame();
        }
    }  
    
    private boolean checkCollision() {
        Rectangle ballBounds = ball.getBounds();
        Rectangle topPipeBounds = topPipe.getBounds();
        Rectangle botPipeBounds = botPipe.getBounds();
    
        // Check if the ball collides with the top pipe
        if (ballBounds.intersects(topPipeBounds)) {
            // Bounce the ball back and adjust its position and velocity
            ball.setVelocityY(-ball.getVelocityY() / 2);
            ball.setY(topPipeBounds.y + topPipeBounds.height);
            return true; // Collision detected, but game continues
        }
    
        // Check if the ball collides with the bottom pipe
        if (ballBounds.intersects(botPipeBounds)) {
            // Bounce the ball back and adjust its position and velocity
            ball.setVelocityY(-ball.getVelocityY() / 2);
            ball.setY(botPipeBounds.y - ball.getSize());
            return true; // Collision detected, but game continues
        }
    
        // If no collision occurs
        return false;
    }
    
    private void updateGame() {
        ball.update();
        topPipe.setPositionX(topPipe.getPositionX() - 3);
        botPipe.setPositionX(topPipe.getPositionX());
    
        clouds.update();
    
        if (topPipe.isOffCanvas()) {
            makePipes();
            score += 10;
        }
    
        // Check collision with pipes and handle the bounce effect
        checkCollision();
    }
    

    private void makePipes() {
        int screenHeight = getHeight();
        int gap = (int) (Math.random() * (screenHeight - GAP_SIZE)) + GAP_SIZE / 2;
        int gapStart = gap - GAP_SIZE / 2;
        int gapEnd = gap + GAP_SIZE / 2;

        topPipe = new Pipe(getWidth(), 0, 80, gapStart);
        botPipe = new Pipe(getWidth(), gapEnd, 80, screenHeight - gapEnd);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        while (true) {
            flappyBird.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}