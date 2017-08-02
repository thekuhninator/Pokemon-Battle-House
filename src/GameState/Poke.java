//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import Entity.Animation;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

class Poke {
    private Animation animation = new Animation();
    private int x = -20;

    public Poke(String name) {
        try {
            String path = "/Images/SpriteSheets/" + name + ".png";
            BufferedImage spriteSheet = ImageIO.read(this.getClass().getResourceAsStream(path));
            int numFrames = spriteSheet.getWidth() / 250;
            BufferedImage[] walking = new BufferedImage[numFrames];

            for(int i = 0; i < numFrames; ++i) {
                walking[i] = spriteSheet.getSubimage(i * 250, 250, 250, 250);
            }

            this.animation.setFrames(walking);
            this.animation.setDelay(100L);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public void update() {
        this.animation.update();
        this.x += 5;
    }

    public int getX() {
        return this.x;
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.animation.getImage(), this.x, 475, 125, 125, (ImageObserver)null);
    }
}
