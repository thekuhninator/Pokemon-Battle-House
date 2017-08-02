//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

public class Explosion {
    private int x;
    private int y;
    private int xmap;
    private int ymap;
    private int width;
    private int height;
    private Animation animation;
    private BufferedImage[] sprites;
    private boolean remove;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 120;
        this.height = 120;

        try {
            BufferedImage spritesheet = ImageIO.read(this.getClass().getResourceAsStream("/Backgrounds/Explosion.png"));
            this.sprites = new BufferedImage[22];

            for(int i = 0; i < this.sprites.length; ++i) {
                this.sprites[i] = spritesheet.getSubimage(i * this.width, 0, this.width, this.height);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            System.out.println("can't find the file");
        }

        this.animation = new Animation();
        this.animation.setFrames(this.sprites);
        this.animation.setDelay(50L);
    }

    public void update() {
        this.animation.update();
        if(this.animation.hasPlayedOnce()) {
            this.remove = true;
        }

    }

    public boolean shouldRemove() {
        return this.remove;
    }

    public void setMapPosition(int x, int y) {
        this.xmap = x;
        this.ymap = y;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.animation.getImage(), this.x + this.xmap - this.width / 2, this.y + this.ymap - this.height / 2, (ImageObserver)null);
    }
}
