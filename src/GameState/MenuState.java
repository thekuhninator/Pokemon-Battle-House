//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import Audio.AudioPlayer;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class MenuState extends GameState {
    private int currentOption;
    private String[] options = new String[]{"EXHIBITION", "OPTIONS", "CREDITS"};
    private Image[] pictures;
    private Image option;
    private Image pokeball;
    private Image bg;
    private Image frame;
    private Font optionFont;
    private Font selectedFont;
    private AudioPlayer menuMusic;
    private HashMap<String, AudioPlayer> sfx;
    private boolean playing;
    private Font playingFont;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        this.currentOption = 0;

        try {
            this.pictures = new Image[this.options.length];
            this.option = ImageIO.read(this.getClass().getResourceAsStream("/Images/Option.png"));
            this.pokeball = ImageIO.read(this.getClass().getResourceAsStream("/Images/Pokeball.png"));
            this.bg = ImageIO.read(this.getClass().getResourceAsStream("/Images/Menu.png"));
            this.frame = ImageIO.read(this.getClass().getResourceAsStream("/Images/Frame.png"));
            this.optionFont = Font.createFont(0, this.getClass().getResourceAsStream("/Fonts/GROBOLD.ttf"));
            this.optionFont = this.optionFont.deriveFont(60.0F);
            this.selectedFont = this.optionFont.deriveFont(70.0F);
            this.menuMusic = new AudioPlayer("/Audio/Summer-Skyes.mp3", 0.0F);
            this.menuMusic.loop();
            this.sfx = new HashMap();
            this.sfx.put("Switch", new AudioPlayer("/SFX/Laser.wav", 0.0F));
            this.sfx.put("Smash", new AudioPlayer("/SFX/Monster smash.wav", 0.0F));
            this.playing = false;
            this.playingFont = this.optionFont.deriveFont(120.0F);

            for(int n = 0; n < this.options.length; ++n) {
                this.pictures[n] = ImageIO.read(this.getClass().getResourceAsStream("/Images/" + this.options[n] + ".png"));
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void init() {
    }

    public void update() {
    }

    private void drawCenteredString(String s, int w, int h, Graphics g) {
        w *= 2;
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = fm.getAscent() + (h - (fm.getAscent() + fm.getDescent()));
        g.drawString(s, x, y);
    }

    public void draw(Graphics2D g) {
        g.setFont(this.optionFont);
        g.setColor(Color.WHITE);
        g.drawImage(this.bg, 0, 0, (ImageObserver)null);

        for(int n = 0; n < this.options.length; ++n) {
            g.drawImage(this.option, 50, n * 200 + 100, (ImageObserver)null);
            if(n == this.currentOption) {
                g.setFont(this.selectedFont);
                g.drawImage(this.pokeball, 69, n * 200 + 120, (ImageObserver)null);
            } else {
                g.setFont(this.optionFont);
            }

            this.drawCenteredString(this.options[n], 400, n * 200 + 210, g);
        }

        g.drawImage(this.frame, 670, 63, (ImageObserver)null);
        g.drawImage(this.pictures[this.currentOption], 730, 118, (ImageObserver)null);
        if(this.playing) {
            g.setColor(Color.BLACK);
            g.setComposite(AlphaComposite.getInstance(3, 0.5F));
            g.fillRect(0, 0, 1280, 720);
            g.setColor(Color.GREEN);
            g.setFont(this.playingFont);
            g.drawString("LOADING", 200, 300);
        }

    }

    private void select() {
        switch(this.currentOption) {
            case 0:
                this.playing = true;
                this.gsm.setState(2);
                this.menuMusic.stop();
                break;
            case 1:
                this.gsm.setState(6);
                break;
            case 2:
                this.gsm.setState(8);
        }

    }

    public void keyPressed(int k) {
        switch(k) {
            case 10:
                sfx.get("Switch").stop();
                sfx.get("Smash").play();
                this.select();
                break;
            case 38:
                sfx.get("Switch").stop();
                sfx.get("Switch").play();
                --this.currentOption;
                if(this.currentOption < 0) {
                    this.currentOption = this.options.length - 1;
                }
                break;
            case 40:
                sfx.get("Switch").stop();
                sfx.get("Switch").play();
                ++this.currentOption;
                if(this.currentOption >= this.options.length) {
                    this.currentOption = 0;
                }
        }

    }

    public void keyReleased(int k) {
    }

    public void back() {
        this.playing = false;
        this.menuMusic.stop();
        this.menuMusic.loop();
    }
}
