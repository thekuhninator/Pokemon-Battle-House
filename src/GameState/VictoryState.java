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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class VictoryState extends GameState {
    private AudioPlayer victoryTheme;
    private Font font;
    private Image background;
    private static ArrayList<Pokemon> team;
    private Image confeti;
    private int confetiX;
    private double confetiY;
    private boolean fadeIn;
    private float opacity;

    public VictoryState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            this.background = ImageIO.read(this.getClass().getResourceAsStream("/Images/Backgrounds/Victory State.png"));
            this.victoryTheme = new AudioPlayer("/Audio/Victory Battle.mp3", 0.0F);
            this.victoryTheme.loop();
            this.font = new Font("IMPACT", 1, 60);
            this.confeti = ImageIO.read(this.getClass().getResourceAsStream("/Images/Confeti.png"));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void init() {
        this.confetiX = 0;
        this.confetiY = 0.0D;
        this.fadeIn = true;
        this.opacity = 0.0F;
    }

    public void back() {
    }

    public void update() {
        this.confetiX -= 20;
        this.confetiY += 11.25D;
        if(this.confetiX <= -1280) {
            this.confetiX = 0;
            this.confetiY = 0.0D;
        }

        if(this.fadeIn) {
            this.opacity += 0.025F;
            if(this.opacity > 1.0F) {
                this.opacity = 1.0F;
                this.fadeIn = false;
            }
        } else {
            this.opacity -= 0.025F;
            if(this.opacity < 0.0F) {
                this.opacity = 0.0F;
                this.fadeIn = true;
            }
        }

    }

    public void draw(Graphics2D g) {
        g.drawImage(this.background, 0, 0, (ImageObserver)null);
        g.setComposite(AlphaComposite.getInstance(3, this.opacity));
        g.setColor(Color.WHITE);
        g.setFont(this.font);
        g.drawString("PRESS ENTER", 490, 650);
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        g.setFont(this.font);

        for(int x = 0; x < team.size(); ++x) {
            ((Pokemon)team.get(x)).draw(g, (int)((double)(x * 250 + 200) + (double)(5 - team.size()) / 2.0D * 250.0D), 400, 250, 250);
            System.out.println(x * 250 + 200 + (5 - team.size() * 250));
        }

        g.drawImage(this.confeti, this.confetiX, (int)this.confetiY, (ImageObserver)null);
        g.drawImage(this.confeti, this.confetiX + 1280, (int)this.confetiY - 720, (ImageObserver)null);
        g.drawImage(this.confeti, this.confetiX + 1280, (int)this.confetiY, (ImageObserver)null);
        g.drawImage(this.confeti, this.confetiX, (int)this.confetiY - 720, (ImageObserver)null);
    }

    public static void setTeam(ArrayList<Pokemon> t) {
        team = t;
    }

    public void keyPressed(int k) {
        if(k == 10) {
            this.gsm.setState(1);
            this.victoryTheme.stop();
        }

    }

    public void keyReleased(int k) {
    }
}
