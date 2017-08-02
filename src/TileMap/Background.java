//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

public class Background {
    private BufferedImage image;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double moveScale;

    public Background(String s, double ms) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream(s));
            this.moveScale = ms;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void setPosition(double x, double y) {
        this.x = x * this.moveScale % (double)this.image.getHeight();
        this.y = y * this.moveScale % (double)this.image.getHeight();
    }

    public void setVector(double dx, double dy) {
        dx = this.dx;
        dy = this.dy;
    }

    public void update() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image.getSubimage((int)(-this.x), (int)(-this.y), 1280, 720), 0, 0, (ImageObserver)null);
    }
}
