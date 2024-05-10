import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Clouds {
    private List<Rectangle> clouds;
    private int panelWidth;

    public Clouds(int panelWidth) {
        this.panelWidth = panelWidth;
        clouds = new ArrayList<>();
        init();
    }

    public void init() {
        clouds.clear();
        clouds.add(new Rectangle(100, 100, 80, 40));
        clouds.add(new Rectangle(300, 50, 100, 50));
        clouds.add(new Rectangle(500, 150, 70, 30));
        clouds.add(new Rectangle(500, 200, 90, 45));
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        for (Rectangle cloud : clouds) {
            g.fillOval(cloud.x, cloud.y, cloud.width, cloud.height);

            int overlapWidth = cloud.width / 1;
            int overlapHeight = cloud.height / 2;

            g.fillOval(cloud.x - overlapWidth / 2, cloud.y, overlapWidth, overlapHeight);
            g.fillOval(cloud.x + cloud.width - overlapWidth / 2, cloud.y, overlapWidth, overlapHeight);
            g.fillOval(cloud.x + overlapWidth / 2, cloud.y + overlapHeight / 2, overlapWidth, overlapHeight);
        }
    }

    public void update() {
        for (Rectangle cloud : clouds) {
            cloud.x -= 1;
            if (cloud.x + cloud.width < 0) {
                cloud.x = panelWidth;            }
        }
    }
}