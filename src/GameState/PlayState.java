//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import Audio.AudioPlayer;
import Entity.Controller;
import Entity.Pokemon;
import TileMap.Background;
import TileMap.MapMaker;
import TileMap.TileMap;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class PlayState extends GameState {
    private TileMap tileMap;
    private int tileSize;
    private Background bg;
    private Controller controller;
    private int selectedRow;
    private int selectedCol;
    private ArrayList<Pokemon> team1;
    private ArrayList<Pokemon> team2;
    private ArrayList<ArrayList<Pokemon>> teams;
    private int numTeams;
    private HashMap<Point, Pokemon> mapObjects;
    private Pokemon viewedPokemon;
    private Pokemon controlledPokemon;
    private Pokemon possibleDefender;
    private boolean drawObject;
    private AudioPlayer music;
    private Font menuFont;
    private boolean selected;
    private boolean drawStats;
    private Image stats;
    private Image gradient;
    private HashMap<Integer, BufferedImage> types;
    private HashMap<String, AudioPlayer> sfx;
    private HashMap<Integer, Color> colors;
    private Image[] rpgIcons;
    private int turn;
    private boolean battleOption;
    private Image[] playerTurns;
    private boolean drawTurn;
    private float turnOpacity;
    private boolean battle;

    public PlayState(GameStateManager gsm) {
        this.gsm = gsm;

        int x;
        try {
            this.turnOpacity = 1.0F;
            this.drawTurn = true;
            this.music = new AudioPlayer("/Audio/Mechanical Princess.mp3", -6.0F);
            this.menuFont = new Font("Arial", 0, 20);
            this.stats = ImageIO.read(this.getClass().getResourceAsStream("/Images/GUI/Pallete.png"));
            this.gradient = ImageIO.read(this.getClass().getResourceAsStream("/Images/GUI/Gradient.png"));
            BufferedImage typeSprites = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/type-sprite-sheet.png"));
            this.types = new HashMap();
            this.colors = new HashMap();
            this.rpgIcons = new Image[5];
            BufferedImage rpg = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/RpgIconSpiteSheet.png"));

            for(x = 0; x < rpg.getWidth() / 100; ++x) {
                this.rpgIcons[x] = rpg.getSubimage(x * 100, 0, 100, 100);
            }

            for(x = 0; x < typeSprites.getWidth() / 200; ++x) {
                this.types.put(Integer.valueOf(x), typeSprites.getSubimage(x * 200, 0, 200, 100));
            }

            this.colors.put(Integer.valueOf(0), new Color(167, 173, 186));
            this.colors.put(Integer.valueOf(1), new Color(145, 60, 15));
            this.colors.put(Integer.valueOf(2), new Color(160, 235, 235));
            this.colors.put(Integer.valueOf(3), new Color(96, 21, 101));
            this.colors.put(Integer.valueOf(4), new Color(77, 54, 28));
            this.colors.put(Integer.valueOf(5), new Color(119, 113, 87));
            this.colors.put(Integer.valueOf(6), new Color(116, 225, 178));
            this.colors.put(Integer.valueOf(7), new Color(62, 10, 65));
            this.colors.put(Integer.valueOf(8), new Color(89, 92, 98));
            this.colors.put(Integer.valueOf(9), new Color(255, 68, 0));
            this.colors.put(Integer.valueOf(10), new Color(52, 151, 228));
            this.colors.put(Integer.valueOf(11), new Color(54, 199, 32));
            this.colors.put(Integer.valueOf(12), new Color(230, 202, 14));
            this.colors.put(Integer.valueOf(13), new Color(207, 159, 209));
            this.colors.put(Integer.valueOf(14), new Color(137, 202, 230));
            this.colors.put(Integer.valueOf(15), new Color(51, 51, 153));
            this.colors.put(Integer.valueOf(16), new Color(40, 40, 40));
            this.playerTurns = new Image[4];
            this.sfx = new HashMap();
            this.sfx.put("ding", new AudioPlayer("/SFX/Change Turn.wav", 0.0F));

            for(x = 1; x < 4; ++x) {
                this.playerTurns[x - 1] = ImageIO.read(this.getClass().getResourceAsStream("/Images/GUI/Player" + x + "Turn.png"));
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.tileSize = 128;
        MapMaker mappy = new MapMaker(this.tileSize, 0);
        mappy.makeMap(25, 25);
        this.tileMap = mappy.getMap();
        this.tileMap.setPosition(0.0D, 0.0D);
        this.tileMap.setTween(0.3D);
        this.controller = new Controller(this.tileMap);
        this.controller.setPos(1280.0D, 720.0D);
        this.team1 = new ArrayList();
        this.team2 = new ArrayList();
        this.teams = new ArrayList();
        this.teams.add(this.team1);
        this.teams.add(this.team2);
        this.mapObjects = new HashMap();
        this.viewedPokemon = null;
        this.controlledPokemon = null;
        this.possibleDefender = null;
        this.selected = false;
        this.team1.add(new Pokemon(this.tileMap, "Chikorita", 0));
        this.team2.add(new Pokemon(this.tileMap, "Charmander", 1));
        this.team1.add(new Pokemon(this.tileMap, "Mudkip", 0));
        this.team2.add(new Pokemon(this.tileMap, "Pikachu", 1));
        this.team1.add(new Pokemon(this.tileMap, "Ralts", 0));
        this.team2.add(new Pokemon(this.tileMap, "Riolu", 1));
        this.team1.add(new Pokemon(this.tileMap, "Beldum", 0));
        this.team2.add(new Pokemon(this.tileMap, "Spinarak", 1));
        this.team1.add(new Pokemon(this.tileMap, "Eevee", 0));
        this.team2.add(new Pokemon(this.tileMap, "Zoura", 1));
        this.numTeams = 2;
        this.battle = false;

        int y;
        int j;
        for(j = 0; j < this.team2.size(); ++j) {
            x = 0; // CHANGING THIS
            y = ((this.tileMap.getNumRows() / 2 + this.team1.size()) / 2 - 1) * this.tileSize + j * this.tileSize;
            ((Pokemon)this.team2.get(j)).setPosition((double)x, (double)y);
            ((Pokemon)this.team2.get(j)).setFacingRight(true);
            this.mapObjects.put(new Point(x / this.tileSize, y / this.tileSize), (Pokemon)this.team2.get(j));
        }

        for(j = 0; j < this.team1.size(); ++j) {
            x = (this.tileMap.getNumCols() - 1) * this.tileSize;
            y = ((this.tileMap.getNumRows() / 2 + this.team1.size()) / 2 - 1) * this.tileSize + j * this.tileSize;
            ((Pokemon)this.team1.get(j)).setPosition((double)x, (double)y);
            ((Pokemon)this.team1.get(j)).setFacingRight(false);
            this.mapObjects.put(new Point(x / this.tileSize, y / this.tileSize), (Pokemon)this.team1.get(j));
        }

        this.turn = 0;
        this.music.loop();
    }

    public void init() {
    }

    public void back() {
        if(!this.music.isPlaying()) {
            this.music.resume();
        }

        this.checkDead();
        this.checkGameOver();
        this.checkNextTurn();
    }

    private void select() {
        Pokemon poke = (Pokemon)this.mapObjects.get(new Point(this.selectedCol, this.selectedRow));
        if(this.viewedPokemon != null && poke != null && poke.getTeam() == this.turn && !poke.hasMovedAlready()) {
            this.controlledPokemon = (Pokemon)this.mapObjects.get(new Point(this.selectedCol, this.selectedRow));
            this.selected = true;
        }

    }

    private void checkDead() {
        for(int x = 0; x < this.teams.size(); ++x) {
            for(int y = 0; y < ((ArrayList)this.teams.get(x)).size(); ++y) {
                if(((Pokemon)((ArrayList)this.teams.get(x)).get(y)).getHp() <= 0) {
                    ((ArrayList)this.teams.get(x)).remove(this.mapObjects.remove(new Point(((Pokemon)((ArrayList)this.teams.get(x)).get(y)).getx() / this.tileSize, ((Pokemon)((ArrayList)this.teams.get(x)).get(y)).gety() / this.tileSize)));
                }
            }
        }

    }

    private void checkGameOver() {
        for(int i = 0; i < this.teams.size(); ++i) {
            if(((ArrayList)this.teams.get(i)).size() == 0) {
                this.teams.remove(i--);
            }
        }

        if(this.teams.size() == 1) {
            VictoryState.setTeam((ArrayList)this.teams.get(0));
            this.gsm.setState(4);
            this.music.stop();
        }

    }

    public void update() {
        if(this.controller.positionChanged()) {
            this.selectedRow = (int)this.controller.getPosY() / this.tileSize;
            this.selectedCol = (int)this.controller.getPosX() / this.tileSize;
            if(!this.selected && this.mapObjects.get(new Point(this.selectedCol, this.selectedRow)) != null) {
                this.viewedPokemon = (Pokemon)this.mapObjects.get(new Point(this.selectedCol, this.selectedRow));
                this.drawObject = true;
                this.drawStats = true;
            } else {
                this.drawObject = false;
            }

            if(this.selected && this.controlledPokemon != null && !this.controlledPokemon.hasMovedAlready()) {
                this.controlledPokemon.getShortestPath(this.selectedRow, this.selectedCol);
            }
        }

        this.tileMap.setPosition(640.0D - this.controller.getPosX(), 360.0D - this.controller.getPosY());

        int j;
        for(j = 0; j < this.team1.size(); ++j) {
            ((Pokemon)this.team1.get(j)).update();
        }

        for(j = 0; j < this.team2.size(); ++j) {
            ((Pokemon)this.team2.get(j)).update();
        }

        if(this.drawTurn) {
            this.turnOpacity -= 0.005F;
            if(this.turnOpacity < 0.0F) {
                this.turnOpacity = 1.0F;
                this.drawTurn = false;
            }
        }

        this.controller.update();
        if(this.battle && !this.controlledPokemon.isWalking()) {
            this.controlledPokemon = null;
            this.music.pause();
            this.gsm.setState(3);
            this.battle = false;
        }

    }

    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(1.0F));
        this.tileMap.draw(g, 0, this.controller.getPosX(), this.controller.getPosY());

        int j;
        for(j = 0; j < this.team1.size(); ++j) {
            ((Pokemon)this.team1.get(j)).draw(g);
        }

        for(j = 0; j < this.team2.size(); ++j) {
            ((Pokemon)this.team2.get(j)).draw(g);
        }

        if(this.drawObject) {
            this.drawObjectDescription(g);
        }

        if(this.battleOption) {
            this.drawOptions(g, 0, 0);
        }

        if(this.drawTurn) {
            g.setComposite(AlphaComposite.getInstance(3, this.turnOpacity));
            g.drawImage(this.playerTurns[this.turn], 140, 100, (ImageObserver)null);
            g.setComposite(AlphaComposite.getInstance(3, 1.0F));
        }

        this.drawHUD(g);
    }

    private void drawHUD(Graphics2D g) {
        for(int i = 0; i < ((ArrayList)this.teams.get(this.turn)).size(); ++i) {
            g.setColor(((Pokemon)((ArrayList)this.teams.get(this.turn)).get(i)).getTeamColor());
            g.fillRect(214 + 170 * i + 45, 595, 110, 110);
            ((Pokemon)((ArrayList)this.teams.get(this.turn)).get(i)).drawHeadshot(214 + 170 * i + 50, 600, 100, 100, g);
        }

    }

    private void drawObjectDescription(Graphics2D g) {
        if(this.selected) {
            g.drawString("THIS GUY SELECTED", 100, 100);
        }

        g.drawImage(this.stats, 830, 0, (ImageObserver)null);
        g.setColor((Color)this.colors.get(Integer.valueOf(this.viewedPokemon.getType())));
        g.fillRoundRect(840, 10, 170, 190, 25, 25);
        g.fillRoundRect(1025, 10, 245, 160, 25, 25);
        g.drawImage(this.gradient, 850, 10, 150, 150, (ImageObserver)null);
        g.drawImage(this.viewedPokemon.getAnimation().getImage(), 850, 10, 150, 150, (ImageObserver)null);
        g.setFont(this.menuFont);
        g.setColor(Color.WHITE);
        if(!this.viewedPokemon.getNickname().equalsIgnoreCase(this.viewedPokemon.getName())) {
            this.drawCenteredString(this.viewedPokemon.getNickname().toUpperCase() + " /", 1850, 340, g);
            this.drawCenteredString(this.viewedPokemon.getName().toUpperCase(), 1850, 380, g);
        } else {
            this.drawCenteredString(this.viewedPokemon.getName().toUpperCase(), 1850, 360, g);
        }

        g.drawString("LV: " + this.viewedPokemon.getLevel(), 840, 240);
        g.drawString("HP: " + this.viewedPokemon.getHp() + " / " + this.viewedPokemon.getMaxHP(), 900, 240);
        if(this.viewedPokemon.getType2() != -1) {
            g.drawImage((Image)this.types.get(Integer.valueOf(this.viewedPokemon.getType())), 845, 250, 75, 38, (ImageObserver)null);
            g.drawImage((Image)this.types.get(Integer.valueOf(this.viewedPokemon.getType2())), 925, 250, 75, 38, (ImageObserver)null);
        } else {
            g.drawImage((Image)this.types.get(Integer.valueOf(this.viewedPokemon.getType())), 880, 250, 75, 38, (ImageObserver)null);
        }

        for(int y = 0; y < this.rpgIcons.length; ++y) {
            g.drawImage(this.rpgIcons[y], 1030, 20 + y * 30, 25, 25, (ImageObserver)null);
        }

        g.drawString("ATK: " + this.viewedPokemon.getAttack(), 1055, 40);
        g.drawString("DEF: " + this.viewedPokemon.getDefense(), 1055, 70);
        g.drawString("SPATK: " + this.viewedPokemon.getSpecAttack(), 1055, 100);
        g.drawString("SPDEF: " + this.viewedPokemon.getSpecDefense(), 1055, 130);
        g.drawString("SPEED: " + this.viewedPokemon.getSpeed(), 1055, 160);
        g.setColor(Color.BLACK);
        g.drawString(this.viewedPokemon.getAttack1().getName(), 1025, 210);
        g.drawString(this.viewedPokemon.getAttack2().getName(), 1150, 210);
        g.drawString(this.viewedPokemon.getAttack3().getName(), 1025, 275);
        g.drawString(this.viewedPokemon.getAttack4().getName(), 1150, 275);
    }

    private void drawOptions(Graphics2D g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 400, 400);
        g.setColor(Color.WHITE);
        g.fillRect(x + 5, y + 5, 400 - x * 2, 400 - y * 2);
    }

    private void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2;
        g.drawString(s, x, y);
    }

    public Point getPosition() {
        return new Point(this.selectedCol, this.selectedRow);
    }

    public void keyPressed(int k) {
        if(!this.battle || this.controlledPokemon != null && this.controlledPokemon.isWalking()) {
            this.controller.keyPressed(k);
            switch(k) {
                case 84:
                    this.music.pause();
                    this.gsm.setState(5);
                    break;
                case 90:
                    if(this.controlledPokemon != null && this.tileMap.pathExists() && this.tileMap.isPossible(this.selectedRow, this.selectedCol) && !this.controlledPokemon.hasMovedAlready()) {
                        if(this.mapObjects.get(new Point(this.selectedCol, this.selectedRow)) == null) {
                            this.gsm.setState(7);
                            this.controller.stop();
                            return;
                        }

                        this.controlledPokemon = (Pokemon)this.mapObjects.get(new Point(this.selectedCol, this.selectedRow));
                        this.tileMap.deletePath();
                    } else {
                        this.select();
                    }

                    this.tileMap.deletePath();
            }
        }

    }

    public void movePokemon() {
        this.mapObjects.put(new Point(this.selectedCol, this.selectedRow), (Pokemon)this.mapObjects.remove(new Point(this.controlledPokemon.getx() / this.tileSize, this.controlledPokemon.gety() / this.tileSize)));
        this.controlledPokemon.setMovedAlready(true);
        this.controlledPokemon.move();
        this.selected = false;
        this.tileMap.deletePath();
        this.controlledPokemon = null;
        this.checkNextTurn();
    }

    private void battleOptionSelect() {
        this.controlledPokemon = null;
        this.music.pause();
        this.gsm.setState(3);
        this.battleOption = false;
    }

    public Pokemon getControlledPokemon() {
        return this.controlledPokemon;
    }

    private void checkNextTurn() {
        boolean allMoved = true;

        // NEW CODE
        if(this.teams.size() == 1) {
            VictoryState.setTeam((ArrayList)this.teams.get(0));
            this.gsm.setState(4);
            this.music.stop();
        }

        if(this.teams.get(this.turn) == null) {
            ++this.turn;
        }

        int i;
        for(i = 0; i < ((ArrayList)this.teams.get(this.turn)).size(); ++i) {
            System.out.println(((Pokemon)((ArrayList)this.teams.get(this.turn)).get(i)).getName() + " " + ((Pokemon)((ArrayList)this.teams.get(this.turn)).get(i)).hasMovedAlready());
            if(!((Pokemon)((ArrayList)this.teams.get(this.turn)).get(i)).hasMovedAlready()) {
                allMoved = false;
            }
        }

        if(allMoved) {
            for(i = 0; i < ((ArrayList)this.teams.get(this.turn)).size(); ++i) {
                ((Pokemon)((ArrayList)this.teams.get(this.turn)).get(i)).setMovedAlready(false);
            }

            ++this.turn;
            if(this.turn >= this.numTeams) {
                this.turn = 0;
            }

            this.drawTurn = true;
            ((AudioPlayer)this.sfx.get("ding")).play();
        }

    }

    public void battle() {
        this.mapObjects.put(new Point(this.selectedCol, this.selectedRow), (Pokemon)this.mapObjects.remove(new Point(this.controlledPokemon.getx() / this.tileSize, this.controlledPokemon.gety() / this.tileSize)));
        this.controlledPokemon.setMovedAlready(true);
        this.controlledPokemon.move();
        this.tileMap.deletePath();
        this.selected = false;
        this.battle = true;
    }

    public void keyReleased(int k) {
        if(!this.battle || this.controlledPokemon != null && this.controlledPokemon.isWalking()) {
            this.controller.keyReleased(k);
        }

    }

    public ArrayList<Pokemon> getTeam() {
        return (ArrayList)this.teams.get(this.turn);
    }

    public HashMap<Point, Pokemon> getMapObjects() {
        return this.mapObjects;
    }

    public Controller getController() {
        return this.controller;
    }
}
