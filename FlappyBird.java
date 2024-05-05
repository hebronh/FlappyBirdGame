import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FlappyBird extends JPanel {
   // private List<Cloud> clouds; 
    private Ball ball;
    private Pipe topPipe;
    private Pipe botPipe;
    private int score;
    private final int GAP_SIZE = 140;
    private boolean gameOver = false;

    public FlappyBird() {
        initGame();
        setPreferredSize(new Dimension(700, 700));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameOver) {
                    ball.setVelocityY(-17); 
                }
            }
        });
    }

    private void initGame() {
     //   clouds = new ArrayList<>();
        // Add static clouds (x, y, width, height)
   //     clouds.add(new Cloud(100, 100, 100, 50));
     //   clouds.add(new Cloud(400, 200, 120, 60));
        ball = new Ball(getWidth() / 2, getHeight() / 2, 20);
        makePipes();
        score = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawGame(Graphics g) {
        g.setColor(new Color(120, 160, 190));
        g.fillRect(0, 0, getWidth(), getHeight());

   //    for (Cloud cloud : clouds) {
     //       cloud.draw(g);
      //  }

        topPipe.draw(g);
        botPipe.draw(g);

        // score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(String.valueOf(score), getWidth() - 100, 100);

        //  ball
        ball.draw(g);

        // game over
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", getWidth() / 2 - 150, getHeight() / 2);
        }

        if (!gameOver) {
            updateGame();
        }
    }


    private void updateGame() {
        ball.update();
        topPipe.setPositionX(topPipe.getPositionX() - 4);
        botPipe.setPositionX(topPipe.getPositionX());
        
        if (topPipe.isOffCanvas()) {
            makePipes();
            score += 10;
        }
        
        boolean passedThroughGap = ball.getX() > topPipe.getPositionX() &&
                                    ball.getX() < topPipe.getPositionX() + topPipe.getWidth();
        boolean passedThroughTopPipe = ball.getY() + ball.getSize() / 2 > topPipe.getHeight();
        boolean passedThroughBottomPipe = ball.getY() + ball.getSize() / 2 < topPipe.getHeight() + GAP_SIZE;
        
        if (passedThroughGap) {
            if (!passedThroughTopPipe || !passedThroughBottomPipe) {
                gameOver = true;
            }
        } else {
            gameOver = false;
        }
    }    
    
    

    private void makePipes() {
        int pipeWidth = 80;
        int screenHeight = getHeight();
        int gapStart = (int) (Math.random() * (screenHeight - GAP_SIZE)); 
    
        if (gapStart < 0) {
            gapStart = 0;
        } else if (gapStart + GAP_SIZE > screenHeight) {
            gapStart = screenHeight - GAP_SIZE;
        }
    
        int gapEnd = gapStart + GAP_SIZE;
    
        topPipe = new Pipe(getWidth(), 0, pipeWidth, gapStart);
        botPipe = new Pipe(getWidth(), gapEnd, pipeWidth, screenHeight - gapEnd);
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
