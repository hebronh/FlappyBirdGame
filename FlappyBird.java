import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 
import java.awt.Color;


public class FlappyBird extends JPanel {
    private List<Cloud> clouds;
    private int scroll;
    private Ball bird;
    private Pipe topPipe;
    private Pipe botPipe;
    private int score;
    private final int GAP_SIZE = 140;

    public FlappyBird() {
      //  loadResources();
        initGame();
        setPreferredSize(new Dimension(700, 700));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bird.setVelocityY(-8);
            }
        });
    }

    private void initGame() {
        scroll = 500;
        clouds = new ArrayList<>();
        clouds.add(new Cloud(-1000, 200, 100, 50));
        clouds.add(new Cloud(-400, 500, 150, 60));

        bird = new Ball(getWidth() / 2, getHeight() / 2, 20, 20);
        makePipes();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        g.setColor(new Color(120, 160, 190));
        g.fillRect(0, 0, getWidth(), getHeight());

        // clouds
        for (Cloud cloud : clouds) {
            cloud.draw(g);
        }

        // pipes
        topPipe.draw(g);
        botPipe.draw(g);

     

        // score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(String.valueOf(score), getWidth() - 100, 100);
        
        // pipes and bird
        updateGame();
    }

    private void updateGame() {
        topPipe.setPositionX(topPipe.getPositionX() - 2);
        botPipe.setPositionX(topPipe.getPositionX());

        // Check if the pipe is off canvas
        if (topPipe.isOffCanvas()) {
            makePipes();
            score += 10;
        }

        if (bird.isOffCanvas(getHeight())) {
            bird.reset(getWidth() / 2, getHeight() / 2);
            score = 0;
        }
    }

    private void makePipes() {
        double gap = Math.random() * (getHeight() * 0.75 - getHeight() * 0.25) + getHeight() * 0.25;
        double gapStart = gap - GAP_SIZE / 2;
        double gapEnd = gap + GAP_SIZE / 2;

        topPipe = new Pipe(getWidth(), gapStart / 2, 80, gapStart);
        botPipe = new Pipe(getWidth(), (getHeight() + gapEnd) / 2, 80, getHeight() - gapEnd);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Game loop
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

