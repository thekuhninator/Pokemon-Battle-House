//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

public class CreditsState extends GameState {
    private int y;
    private int endY;
    private boolean speedUp;
    private String[] credits = new String[]{"Coded by Roman Kuhn", "Art by Roman and Marcus Kuhn", "Music from newgrounds.com", "8-Bit-Thing - joeybab3", "Gameboy Battle - EpicRPGRemixes", "Mechanical Princess - PixlCrushr", "Summer-Skyes - Necroionutwiz", "Victory! (8bit) - AlxEllis", "Astronomixel - jonadrew", "Tiles are variations of DokuCraft Light by Doku", "Pokemon is a trademark of Nintendo.", "No Copyright infrigment intended."};
    private Font menuFont;
    private Image bg;

    public CreditsState(GameStateManager gsm) {
        this.gsm = gsm;
        this.endY = -1280;
        this.y = 720;

        try {
            this.bg = ImageIO.read(this.getClass().getResourceAsStream("/Images/Menu.png"));
            this.menuFont = new Font("IMPACT", 0, 40);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void init() {
        this.endY = -1280;
        this.y = 720;
    }

    public void back() {
    }

    public void update() {
        this.y -= 2;
        if(this.speedUp) {
            this.y -= 5;
        }

        if(this.endY > this.y) {
            this.gsm.setState(1);
        }

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        g.drawImage(this.bg, 0, 0, (ImageObserver)null);
        g.setColor(Color.WHITE);
        g.setFont(this.menuFont);

        for(int n = 0; n < this.credits.length; ++n) {
            g.drawString(this.credits[n], 100, n * 100 + this.y);
        }

    }

    public void keyPressed(int k) {
        switch(k) {
            case 10:
            case 88:
            case 90:
                this.gsm.setState(1);
                break;
            case 16:
                this.speedUp = true;
        }

    }

    public void keyReleased(int k) {
        switch(k) {
            case 16:
                this.speedUp = false;
            default:
        }
    }
}
