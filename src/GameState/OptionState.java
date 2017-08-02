//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class OptionState extends GameState {
    private Scanner input;
    private static boolean soundOn;
    private Image bg;
    private Image option;
    private Image pokeball;
    private String[] options = new String[]{"Sound"};
    private Font optionFont;
    private Font selectedFont;
    private int currentOption;

    public OptionState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            soundOn = true;
            this.option = ImageIO.read(this.getClass().getResourceAsStream("/Images/Option.png"));
            this.pokeball = ImageIO.read(this.getClass().getResourceAsStream("/Images/Pokeball.png"));
            this.bg = ImageIO.read(this.getClass().getResourceAsStream("/Images/Menu.png"));
            this.optionFont = Font.createFont(0, this.getClass().getResourceAsStream("/Fonts/GROBOLD.ttf"));
            this.optionFont = this.optionFont.deriveFont(60.0F);
            this.selectedFont = this.optionFont.deriveFont(70.0F);
            this.currentOption = 0;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void init() {
    }

    public void back() {
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.bg, 0, 0, (ImageObserver)null);

        for(int y = 0; y < this.options.length; ++y) {
            g.drawImage(this.option, 50, y * 200 + 100, (ImageObserver)null);
            g.drawImage(this.option, 1405, y * 200 + 100, -650, 140, (ImageObserver)null);
            if(y == this.currentOption) {
                g.setFont(this.selectedFont);
                g.drawImage(this.pokeball, 69, y * 200 + 120, (ImageObserver)null);
            } else {
                g.setFont(this.optionFont);
            }

            this.drawCenteredString(this.options[y], 400, y * 200 + 210, g);
            this.drawCenteredString(String.valueOf(soundOn), 1000, 210, g);
        }

    }

    private void drawCenteredString(String s, int w, int h, Graphics g) {
        w *= 2;
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = fm.getAscent() + (h - (fm.getAscent() + fm.getDescent()));
        g.drawString(s, x, y);
    }

    public void keyPressed(int k) {
        switch(k) {
            case 10:
                this.goBack();
                break;
            case 37:
                soundOn = !soundOn;
                break;
            case 38:
                --this.currentOption;
                if(this.currentOption < 0) {
                    this.currentOption = this.options.length - 1;
                }
                break;
            case 39:
                soundOn = !soundOn;
                break;
            case 40:
                if(this.currentOption > this.options.length - 1) {
                    this.currentOption = 0;
                }
        }

    }

    private void goBack() {
        this.gsm.setState(1);
    }

    public void keyReleased(int k) {
    }

    public static void setSoundOn(boolean s) {
        soundOn = s;
    }

    public static boolean soundOn() {
        return soundOn;
    }
}
