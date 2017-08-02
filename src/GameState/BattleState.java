//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import Audio.AudioPlayer;
import Entity.Animation;
import Entity.Attack;
import Entity.Pokemon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class BattleState extends GameState {
    private static boolean initialized = false;
    private static boolean mainOptions;
    private static Image[] backgrounds;
    private static Image[] battlePlatforms;
    private static Animation animation;
    private static Animation sunBurst;
    private static ArrayList<BufferedImage[]> attackSprites;
    private static AudioPlayer[] sfx;
    private static int background = 0;
    public static final int FOREST = 0;
    private int wiggleX;
    private int wiggleY;
    private float shakeDecay = 2.0F;
    private float shakeIntensity = 100.0F;
    private static AudioPlayer battleTheme;
    private static Image button;
    private int optionsX;
    private int optionsY;
    private static Pokemon attacker;
    private static Pokemon defender;
    private boolean facingRightTemp1;
    private boolean facingRightTemp2;
    private static Font font;
    private static Font moveFont;
    private static Font moveFont2;
    private boolean shaking;
    private static HashMap<Integer, Image> typeImages;
    private int turn;
    private int moveType;
    private static boolean testing = false;
    private int currentOption;
    private boolean exit;

    public BattleState(GameStateManager gsm) {
        this.gsm = gsm;
        if(testing) {
            attacker = new Pokemon("Chikorita", 0);
            defender = new Pokemon("Charmander", 1);
        }

        this.facingRightTemp1 = attacker.isFacingRight();
        this.facingRightTemp2 = defender.isFacingRight();
        this.turn = 0;
        this.optionsX = 0;
        this.optionsY = 0;
        this.shaking = false;
        this.wiggleX = 0;
        this.wiggleY = 0;
        if(!initialized) {
            initialized = true;
            String[] types = new String[]{"Normal", "Fighting", "Flying", "Poison", "Ground", "Rock", "Bug", "Ghost", "Steel", "Fire", "Water", "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark"};
            int[] numSprites = new int[]{5, 5, 5, 14, 5, 7, 7, 20, 15, 8, 7, 7, 8, 16, 7, 8, 9};

            try {
                typeImages = new HashMap();
                BufferedImage typeSprites = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/type-sprite-sheet.png"));

                for(int x = 0; x < typeSprites.getWidth() / 200; ++x) {
                    typeImages.put(Integer.valueOf(x), typeSprites.getSubimage(x * 200, 0, 200, 100));
                }

                font = new Font("Arial", 1, 40);
                moveFont = Font.createFont(0, this.getClass().getResourceAsStream("/Fonts/theboldfont.ttf"));
                moveFont = moveFont.deriveFont(45.0F);
                moveFont2 = moveFont.deriveFont(37.0F);
                battleTheme = new AudioPlayer("/Audio/Gameboy Battle.mp3", -8.0F);
                attackSprites = new ArrayList();
                sunBurst = new Animation();
                BufferedImage starburst = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/Battle Animations/Starburst.png"));
                BufferedImage[] sunBurstAr = new BufferedImage[6];

                int i;
                for(i = 0; i < 6; ++i) {
                    sunBurstAr[i] = starburst.getSubimage(i * 1280, 0, 1280, 720);
                }

                sunBurst.setFrames(sunBurstAr);
                sunBurst.setDelay(50L);
                sfx = new AudioPlayer[17];

                for(i = 0; i < 17; ++i) {
                    sfx[i] = new AudioPlayer("/SFX/Attacks/" + types[i] + ".wav", 6.0F);
                }

                for(i = 0; i < 17; ++i) {
                    BufferedImage spriteSheet = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/Battle Animations/" + types[i] + ".png"));
                    int width = spriteSheet.getWidth() / 192;
                    int height = spriteSheet.getHeight() / 192;
                    BufferedImage[] sprites = new BufferedImage[numSprites[i]];
                    int count = 0;

                    for(int y = 0; y < height && count < numSprites[i]; ++y) {
                        for(int x = 0; x < width && count < numSprites[i]; ++x) {
                            sprites[count++] = spriteSheet.getSubimage(x * 192, y * 192, 192, 192);
                        }
                    }

                    attackSprites.add(sprites);
                }

                animation = new Animation();
                animation.setDelay(50L);
                backgrounds = new Image[1];
                battlePlatforms = new Image[1];
                battlePlatforms[0] = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/Battle Animations/battlePlatform.png"));
                backgrounds[0] = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/Battle Animations/Forest.png"));
                animation.setFrames((BufferedImage[])attackSprites.get(15));
                animation.setHasPlayedOnce(true);
                defender.setFacingRight(false);
                attacker.setFacingRight(true);
                button = ImageIO.read(this.getClass().getResourceAsStream("/Images/GUI/Button.png"));
            } catch (Exception var15) {
                var15.printStackTrace();
            }

            this.init();
        }

        mainOptions = true;
    }

    public void init() {
        battleTheme.play();
        this.facingRightTemp1 = attacker.isFacingRight();
        this.facingRightTemp2 = defender.isFacingRight();
        this.turn = 0;
        this.optionsX = 0;
        this.optionsY = 0;
        this.facingRightTemp1 = attacker.isFacingRight();
        this.facingRightTemp2 = defender.isFacingRight();
        animation.setHasPlayedOnce(true);
        defender.setFacingRight(false);
        attacker.setFacingRight(true);
        this.exit = false;
    }

    public void update() {
        animation.update();
        sunBurst.update();
        if(this.shaking) {
            this.wiggleX = (int)(Math.random() * (double)(this.shakeIntensity * 2.0F) - (double)this.shakeIntensity);
            this.wiggleY = (int)(Math.random() * (double)(this.shakeIntensity * 2.0F) - (double)this.shakeIntensity);
            this.shakeIntensity -= this.shakeDecay;
        }

        if(this.shakeIntensity < 1.0F && this.shaking) {
            this.shaking = false;
            attacker.idle();
            defender.idle();
        }

        if(!this.shaking && this.exit) {
            this.optionsX = 0;
            this.optionsY = 0;
            this.exit();
        }

    }

    private void shake() {
        this.shaking = true;
        this.shakeDecay = 2.0F;
        this.shakeIntensity = 100.0F;
        sunBurst.setHasPlayedOnce(false);
        sunBurst.setFrame(0);
    }

    public void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(backgrounds[background], -320 + this.wiggleX, -180 + this.wiggleY, (ImageObserver)null);
        g.drawImage(battlePlatforms[background], 70 + this.wiggleX, 250 + this.wiggleY, (ImageObserver)null);
        g.drawImage(battlePlatforms[background], 670 + this.wiggleX, 250 + this.wiggleY, (ImageObserver)null);
        defender.draw(g, 1050 + this.wiggleX, 250 + this.wiggleY, 250, 250);
        attacker.draw(g, 200 + this.wiggleX, 250 + this.wiggleY, 250, 250);
        if(!animation.hasPlayedOnce()) {
            if(this.turn == 1) {
                g.drawImage(animation.getImage(), 780 + this.wiggleX, 150 + this.wiggleY, 384, 384, (ImageObserver)null);
            } else {
                g.drawImage(animation.getImage(), 150 + this.wiggleX, 150 + this.wiggleY, 384, 384, (ImageObserver)null);
            }
        }

        if(!sunBurst.hasPlayedOnce()) {
            g.drawImage(sunBurst.getImage(), 0, 0, (ImageObserver)null);
        }

        this.drawPlayer1Stats(g);
        this.drawPlayer2Stats(g);
        if(!this.shaking) {
            this.drawMoves(g);
        }

    }

    private void drawPlayer1Stats(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(250 + this.wiggleX, 150 + this.wiggleY, 200, 25);
        g.setColor(Color.GREEN);
        g.fillRect(250 + this.wiggleX, 150 + this.wiggleY, (int)(200.0D * ((double)attacker.getHp() / (double)attacker.getMaxHP())), 25);
        g.setColor(attacker.getTeamColor());
        g.fillRoundRect(-50, 50, 300, 100, 50, 50);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(attacker.getName(), 50, 100);
    }

    private void drawPlayer2Stats(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(880 + this.wiggleX, 150 + this.wiggleY, 200, 25);
        g.setColor(Color.GREEN);
        g.fillRect(880 + this.wiggleX, 150 + this.wiggleY, (int)(200.0D * ((double)defender.getHp() / (double)defender.getMaxHP())), 25);
        g.setColor(defender.getTeamColor());
        g.fillRoundRect(1030, 50, 300, 100, 50, 50);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(defender.getName(), 1270 - g.getFontMetrics().stringWidth(defender.getName()), 100);
    }

    private void drawMoves(Graphics2D g) {
        g.setFont(moveFont);

        int count;
        int x;
        for(count = 0; count < 2; ++count) {
            for(x = 0; x < 2; ++x) {
                g.drawImage(button, count * 1280 / 2, x * 110 + 500, (ImageObserver)null);
            }
        }

        g.setColor(this.turn == 0?attacker.getTeamColor():defender.getTeamColor());
        g.setStroke(new BasicStroke(2.0F));
        g.drawRoundRect(this.optionsX * 1280 / 2, this.optionsY * 110 + 500, 640, 110, 25, 25);
        g.setColor(Color.WHITE);
        int y;
        Attack attack;
        if(this.turn == 0) {
            count = 0;

            for(x = 0; x < 2; ++x) {
                for(y = 0; y < 2; ++y) {
                    attack = attacker.getAttack(count);
                    g.setFont(moveFont);
                    g.drawImage((Image)typeImages.get(Integer.valueOf(attack.getType())), 30 + x * 640, 530 + y * 110, 100, 50, (ImageObserver)null);
                    this.drawCenteredString(attack.getName(), 600 + x * 1280, 1125 + y * 220, g);
                    g.setFont(moveFont2);
                    this.drawCenteredString("PP: " + attack.getCurrentPP() + "/" + attack.getMaxPP(), 1100 + x * 1280, 1125 + y * 220, g);
                    ++count;
                }
            }
        } else {
            count = 0;

            for(x = 0; x < 2; ++x) {
                for(y = 0; y < 2; ++y) {
                    attack = defender.getAttack(count);
                    g.setFont(moveFont);
                    g.drawImage((Image)typeImages.get(Integer.valueOf(attack.getType())), 30 + x * 640, 530 + y * 110, 100, 50, (ImageObserver)null);
                    this.drawCenteredString(attack.getName(), 600 + x * 1280, 1125 + y * 220, g);
                    g.setFont(moveFont2);
                    this.drawCenteredString("PP: " + attack.getCurrentPP() + "/" + attack.getMaxPP(), 1100 + x * 1280, 1125 + y * 220, g);
                    ++count;
                }
            }
        }

    }

    private void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2;
        g.drawString(s, x, y);
    }

    private void exit() {
        this.turn = 0;
        this.shaking = false;
        this.exit = false;
        battleTheme.stop();
        attacker.setFacingRight(this.facingRightTemp1);
        defender.setFacingRight(this.facingRightTemp2);
        attacker.idle();
        defender.idle();
        this.gsm.setState(2);
    }

    private void checkDead() {
        if(defender.getHp() < 1) {
            this.exit = true;
        }

    }

    private void attack() {
        if(this.turn == 0) {
            System.out.println(this.currentOption + " " + attacker.getAttack(this.currentOption).getName());
            defender.setHp(defender.getHp() - attacker.getAttack(this.currentOption).calculateDamage(attacker, defender));
            animation.setFrames((BufferedImage[])attackSprites.get(attacker.getAttack(this.currentOption).getType()));
            sfx[attacker.getAttack(this.currentOption).getType()].play();
            defender.hurt();
            this.checkDead();
        } else {
            attacker.setHp(defender.getHp() - defender.getAttack(this.currentOption).calculateDamage(defender, attacker));
            animation.setFrames((BufferedImage[])attackSprites.get(defender.getAttack(this.currentOption).getType()));
            attacker.hurt();
            sfx[defender.getAttack(this.currentOption).getType()].play();
        }

        ++this.turn;
        if(this.turn > 1) {
            this.exit = true;
        }

    }

    public void keyPressed(int k) {
        switch(k) {
            case 37:
                this.optionsX = 0;
                break;
            case 38:
                this.optionsY = 0;
                break;
            case 39:
                this.optionsX = 1;
                break;
            case 40:
                this.optionsY = 1;
                break;
            case 90:
                if(this.turn < 2 && !this.exit && !this.shaking) {
                    this.shake();
                    this.attack();
                }
        }

        this.currentOption = this.optionsX == 0?(this.optionsY == 0?0:1):(this.optionsY == 0?2:3);
    }

    public void keyReleased(int k) {
    }

    public static void setBackground(int bg) {
        background = bg;
    }

    public static void setPokemon1(Pokemon p1) {
        attacker = p1;
    }

    public static void setPokemon2(Pokemon p2) {
        defender = p2;
    }

    public void back() {
        this.currentOption = 0;
        this.optionsX = 0;
        this.optionsY = 0;
    }
}
