//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import Audio.AudioPlayer;
import Entity.Pokemon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class PauseState extends GameState {
    private int currentOption;
    private ArrayList<Pokemon> team;
    private PlayState playState;
    private String[] options = new String[]{"Resume", "Team", "Options", "Resign"};
    private HashMap<String, AudioPlayer> sfx;
    private Image button;
    private Font menuFont;
    private Font selectedFont;

    public PauseState(GameStateManager gsm) {
        this.gsm = gsm;
        this.playState = gsm.getPlayState();
        this.team = this.playState.getTeam();
        this.sfx = new HashMap();

        try {
            this.button = ImageIO.read(this.getClass().getResourceAsStream("/Images/GUI/PauseButton.png"));
            this.menuFont = Font.createFont(0, this.getClass().getResourceAsStream("/Fonts/GROBOLD.ttf"));
            this.menuFont = this.menuFont.deriveFont(60.0F);
            this.selectedFont = this.menuFont.deriveFont(80.0F);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void init() {
        this.currentOption = 0;
    }

    public void back() {
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
        this.playState.draw(g);
        g.setComposite(AlphaComposite.getInstance(3, 0.5F));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 720);
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for(int x = 0; x < this.options.length; ++x) {
            if(this.currentOption == x) {
                g.setFont(this.selectedFont);
            } else {
                g.setFont(this.menuFont);
            }

            g.drawImage(this.button, 365, x * 150 + 25, (ImageObserver)null);
            this.drawCenteredString(this.options[x], 1280, x * 300 + 200, g);
        }

    }

    private void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2;
        g.drawString(s, x, y);
    }

    public void keyPressed(int k) {
        switch(k) {
            case 10:
                this.select();
                break;
            case 38:
                --this.currentOption;
                if(this.currentOption < 0) {
                    this.currentOption = this.options.length - 1;
                }
                break;
            case 40:
                ++this.currentOption;
                if(this.currentOption > this.options.length - 1) {
                    this.currentOption = 0;
                }
        }

    }

    public void keyReleased(int k) {
    }

    private void select() {
        switch(this.currentOption) {
            case 0:
                this.gsm.setState(2);
            case 1:
            default:
                break;
            case 2:
                this.gsm.setState(6);
                break;
            case 3:
                VictoryState.setTeam(this.team);
                this.gsm.setState(4);
        }

    }

    public void setTeam(ArrayList<Pokemon> t) {
    }
}
