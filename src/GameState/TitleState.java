//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import Audio.AudioPlayer;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class TitleState extends GameState {
    private int currentOption;
    private AudioPlayer menuMusic;
    private HashMap<Integer, AudioPlayer> cries;
    private BufferedImage[] powerLogos;
    private Image battleHaus;
    private Image background;
    private Image pokemonTitle;
    private boolean fadeIn;
    private float opacity;
    private Font startFont;
    private Font font;
    private Image[] energySymbols;
    private Timer timer;
    private boolean drawSymbol;
    private int drawIndex;
    private float drawOpacity;
    private ArrayList<Poke> pokemon;

    public TitleState(GameStateManager gsm) {
        this.gsm = gsm;
        this.pokemon = new ArrayList();
        this.cries = new HashMap();
        this.currentOption = 0;
        this.drawIndex = 0;
        this.drawSymbol = false;
        this.drawOpacity = 1.0F;
        OptionState.setSoundOn(true);
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int randomType = (int)(Math.random() * 10.0D);
                TitleState.this.drawSymbol = true;
                TitleState.this.drawIndex = randomType;
                switch(randomType) {
                    case 0:
                        TitleState.this.pokemon.add(new Poke("Chikorita"));
                        break;
                    case 1:
                        TitleState.this.pokemon.add(new Poke("Charmander"));
                        break;
                    case 2:
                        TitleState.this.pokemon.add(new Poke("Mudkip"));
                        break;
                    case 3:
                        TitleState.this.pokemon.add(new Poke("Pikachu"));
                        break;
                    case 4:
                        TitleState.this.pokemon.add(new Poke("Ralts"));
                        break;
                    case 5:
                        TitleState.this.pokemon.add(new Poke("Riolu"));
                        break;
                    case 6:
                        TitleState.this.pokemon.add(new Poke("Zoura"));
                        break;
                    case 7:
                        TitleState.this.pokemon.add(new Poke("Beldum"));
                        break;
                    case 8:
                        TitleState.this.pokemon.add(new Poke("Eevee"));
                        break;
                    case 9:
                        TitleState.this.pokemon.add(new Poke("Spinarak"));
                }

            }
        };
        this.timer = new Timer(3000, taskPerformer);

        try {
            this.battleHaus = ImageIO.read(this.getClass().getResourceAsStream("/Images/Title/Battle-Haus.png"));
            this.background = ImageIO.read(this.getClass().getResourceAsStream("/Images/Title/Background.png"));
            this.pokemonTitle = ImageIO.read(this.getClass().getResourceAsStream("/Images/Title/pokemon.png"));
            this.startFont = new Font("IMPACT", 1, 60);
            this.font = new Font("Arial", 0, 15);
            this.menuMusic = new AudioPlayer("/Audio/8-bit-thing.mp3", 0.0F);
            this.menuMusic.play();
            this.timer.start();
            this.energySymbols = new Image[10];
            BufferedImage energySymbol = ImageIO.read(this.getClass().getResourceAsStream("/Images/Title/energy-symbols.png"));

            for(int x = 0; x < this.energySymbols.length; ++x) {
                if(x < 5) {
                    this.energySymbols[x] = energySymbol.getSubimage(x * 205, 35, 205, 210);
                } else {
                    this.energySymbols[x] = energySymbol.getSubimage((x - 5) * 205, 250, 205, 250);
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void init() {
        this.timer.start();
        this.menuMusic.play();
    }

    public void update() {
        for(int i = 0; i < this.pokemon.size(); ++i) {
            ((Poke)this.pokemon.get(i)).update();
            if(((Poke)this.pokemon.get(i)).getX() > 1280) {
                this.pokemon.remove(i--);
            }
        }

        System.out.println(2147483647);
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

        System.out.println(this.drawSymbol);
        if(this.drawSymbol) {
            this.drawOpacity += 0.1F;
            if(this.drawOpacity > 1.0F) {
                this.drawOpacity = 1.0F;
                this.drawSymbol = false;
            }
        } else {
            this.drawOpacity -= 0.01F;
            if(this.drawOpacity < 0.0F) {
                this.drawOpacity = 0.0F;
            }
        }

    }

    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(1.0F));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(this.background, 0, 0, (ImageObserver)null);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1280, 50);
        g.fillRect(0, 670, 1280, 50);
        g.drawImage(this.pokemonTitle, 265, 40, (ImageObserver)null);
        g.drawImage(this.battleHaus, 115, 225, (ImageObserver)null);
        g.setComposite(AlphaComposite.getInstance(3, this.opacity));
        g.setColor(Color.WHITE);
        g.setFont(this.startFont);
        g.drawString("PRESS ENTER", 490, 650);
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        g.setFont(this.font);
        g.drawString("CREATED BY ROMAN KUHN", 50, 695);
        g.drawString("ART BY MARCUS KUHN", 50, 710);
        g.drawString("VERSION 1.0", 1130, 695);

        for(int i = 0; i < this.pokemon.size(); ++i) {
            ((Poke)this.pokemon.get(i)).draw(g);
        }

        g.setComposite(AlphaComposite.getInstance(3, this.drawOpacity));
        System.out.println(this.drawOpacity);
        g.drawImage(this.energySymbols[this.drawIndex], 590, 420, this.energySymbols[this.drawIndex].getWidth((ImageObserver)null) / 2, this.energySymbols[this.drawIndex].getHeight((ImageObserver)null) / 2, (ImageObserver)null);
        g.setComposite(AlphaComposite.getInstance(3, 1.0F));
    }

    public void keyPressed(int k) {
        switch(k) {
            case 10:
                this.gsm.setState(1);
                this.timer.stop();
                this.menuMusic.stop();
                break;
            case 27:
                System.exit(0);
        }

    }

    public void keyReleased(int k) {
    }

    public void back() {
    }
}
