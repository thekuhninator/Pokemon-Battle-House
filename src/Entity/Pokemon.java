//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Entity;

import Pathfinder.AStarPathFinder;
import Pathfinder.Path;
import Pathfinder.PathFinder;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Pokemon {
    private TileMap tileMap;
    private int tileSize;
    private double xmap;
    private double ymap;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private int width;
    private int height;
    private int cwidth;
    private int cheight;
    private int currRow;
    private int currCol;
    private int numRows;
    private int numCols;
    private Animation animation;
    private int currentAction;
    private int previousAction;
    private boolean facingRight;
    private int level;
    private int hp;
    private int maxHP;
    private int attack;
    private int defense;
    private int specAttack;
    private int specDefense;
    private int speed;
    private int baseHP;
    private int baseAttack;
    private int baseDefense;
    private int baseSpecAttack;
    private int baseSpecDefense;
    private int baseSpeed;
    private long flinchTimer;
    private Attack attack1;
    private Attack attack2;
    private Attack attack3;
    private Attack attack4;
    private boolean[] canLearnMove;
    private Font font;
    private int statusEffect;
    private int currAttack;
    private int currDefense;
    private int currSpecAttack;
    private int currSpecDefense;
    private int currSpeed;
    private int currAccuracy;
    private int currEvasiveness;
    private String name;
    private String nickname;
    private int type;
    private int type2;
    public static final int NONE = -1;
    public static final int NORMAL = 0;
    public static final int FIGHTING = 1;
    public static final int FLYING = 2;
    public static final int POISON = 3;
    public static final int GROUND = 4;
    public static final int ROCK = 5;
    public static final int BUG = 6;
    public static final int GHOST = 7;
    public static final int STEEL = 8;
    public static final int FIRE = 9;
    public static final int WATER = 10;
    public static final int GRASS = 11;
    public static final int ELECTRIC = 12;
    public static final int PSYCHIC = 13;
    public static final int ICE = 14;
    public static final int DRAGON = 15;
    public static final int DARK = 16;
    public static final int IDLE = 0;
    public static final int WALKING = 1;
    public static final int HURT = 2;
    public static final int NOTHING = 0;
    public static final int PARALYZED = 1;
    public static final int POISONED = 2;
    public static final int SLEEP = 3;
    public static final int BURNED = 4;
    public static final int FROZEN = 5;
    private boolean walking;
    private int colDestination;
    private int rowDestination;
    private Path path;
    private int[] directions;
    private int distanceCount;
    private int directionLCV;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private double moveSpeed;
    private double maxSpeed;
    private double stopSpeed;
    private boolean foundPossiblePaths;
    private Color teamColor;
    private Image headShot;
    private static String[] types = new String[]{"Normal", "Fighting", "flying", "Posion", "Ground", "Rock", "Bug", "Ghost", "Steel", "Fire", "Water", "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark"};
    private ArrayList<BufferedImage[]> sprites;
    public static final double[][] effectiveness = new double[][]{{1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 0.5D, 1.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D}, {2.0D, 1.0D, 0.5D, 0.5D, 1.0D, 2.0D, 0.5D, 0.0D, 2.0D, 1.0D, 1.0D, 1.0D, 1.0D, 0.5D, 2.0D, 1.0D, 2.0D}, {1.0D, 2.0D, 1.0D, 1.0D, 1.0D, 0.5D, 2.0D, 1.0D, 0.5D, 1.0D, 1.0D, 2.0D, 0.5D, 1.0D, 1.0D, 1.0D, 1.0D}, {1.0D, 1.0D, 1.0D, 0.5D, 0.5D, 0.5D, 1.0D, 0.5D, 0.0D, 1.0D, 1.0D, 2.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D}, {1.0D, 1.0D, 0.0D, 2.0D, 1.0D, 2.0D, 0.5D, 1.0D, 2.0D, 2.0D, 1.0D, 0.5D, 2.0D, 1.0D, 1.0D, 1.0D, 1.0D}, {1.0D, 0.5D, 2.0D, 1.0D, 0.5D, 1.0D, 2.0D, 1.0D, 0.5D, 2.0D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 1.0D, 1.0D}, {1.0D, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D, 0.5D, 0.5D, 0.5D, 1.0D, 2.0D, 1.0D, 2.0D, 1.0D, 1.0D, 2.0D}, {0.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 0.5D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 1.0D, 1.0D, 0.5D}, {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 1.0D, 1.0D, 0.5D, 0.5D, 0.5D, 1.0D, 0.5D, 1.0D, 2.0D, 1.0D, 1.0D}, {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 0.5D, 2.0D, 1.0D, 1.0D, 0.5D, 0.5D, 0.5D, 1.0D, 0.5D, 1.0D, 2.0D, 1.0D, 1.0D}, {1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 2.0D, 1.0D, 1.0D, 1.0D, 2.0D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D, 0.5D, 1.0D}, {1.0D, 1.0D, 0.5D, 0.5D, 2.0D, 2.0D, 0.5D, 1.0D, 0.5D, 0.5D, 2.0D, 0.5D, 1.0D, 1.0D, 1.0D, 0.5D, 1.0D}, {1.0D, 1.0D, 2.0D, 1.0D, 0.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 0.5D, 0.5D, 1.0D, 1.0D, 0.5D, 1.0D}, {1.0D, 2.0D, 1.0D, 2.0D, 1.0D, 1.0D, 1.0D, 1.0D, 0.5D, 1.0D, 1.0D, 1.0D, 1.0D, 0.5D, 1.0D, 1.0D, 0.0D}, {1.0D, 1.0D, 2.0D, 1.0D, 2.0D, 1.0D, 1.0D, 1.0D, 0.5D, 0.5D, 0.5D, 2.0D, 1.0D, 1.0D, 0.5D, 2.0D, 1.0D}, {1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 0.5D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 1.0D}, {1.0D, 0.5D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 0.5D, 1.0D, 1.0D, 1.0D, 1.0D, 2.0D, 1.0D, 1.0D, 0.5D}};
    private boolean movedAlready;
    private int team;

    public Pokemon(TileMap tm, String name, int team) {
        this(name, team);
        this.tileMap = tm;
        this.tileSize = tm.getTileSize();
        this.numRows = this.tileMap.getNumRows();
        this.numCols = this.tileMap.getNumCols();
    }

    public Pokemon(String name, int team) {
        this.sprites = new ArrayList();
        this.name = name;
        this.team = team;
        this.init();
    }

    public void init() {
        this.moveSpeed = 1.0D;
        this.maxSpeed = 5.0D;
        this.stopSpeed = 1.0D;
        this.tileSize = 128;
        this.level = 5;
        this.width = this.tileSize;
        this.height = this.tileSize;
        this.animation = new Animation();
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.walking = false;
        this.colDestination = 10;
        this.rowDestination = 10;
        this.distanceCount = 0;
        this.foundPossiblePaths = false;
        this.movedAlready = false;
        this.nickname = this.name;
        int idleSprites = 1;
        BufferedImage spriteSheet = null;

        try {
            spriteSheet = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/" + this.name + ".png"));
            this.headShot = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/Headshots/" + this.name + "-head.png"));
            this.font = new Font("Arial", 0, 30);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        String var3 = this.name;
        switch(this.name.hashCode()) {
            case -1979385418:
                if(var3.equals("Mudkip")) {
                    this.baseHP = 50;
                    this.baseAttack = 70;
                    this.baseDefense = 50;
                    this.baseSpecAttack = 50;
                    this.baseSpecDefense = 50;
                    this.baseSpeed = 40;
                    this.type = 10;
                    this.type2 = -1;
                    this.nickname = "Leffen";
                    this.attack1 = new Attack("Aqua Tail");
                    this.attack2 = new Attack("Mud Bomb");
                    this.attack3 = new Attack("Tackle");
                    this.attack4 = new Attack("Ice Beam");
                }
                break;
            case -1943782531:
                if(var3.equals("Spinarak")) {
                    this.baseHP = 40;
                    this.baseAttack = 60;
                    this.baseDefense = 40;
                    this.baseSpecAttack = 40;
                    this.baseSpecDefense = 40;
                    this.baseSpeed = 30;
                    this.type = 6;
                    this.type2 = 3;
                    this.attack1 = new Attack("Agillity");
                    this.attack2 = new Attack("Night Slash");
                    this.attack3 = new Attack("Bug Bight");
                    this.attack4 = new Attack("Posion Jab");
                }
                break;
            case -714555539:
                if(var3.equals("Charmander")) {
                    this.baseHP = 39;
                    this.baseAttack = 52;
                    this.baseDefense = 43;
                    this.baseSpecAttack = 60;
                    this.baseSpecDefense = 50;
                    this.baseSpeed = 65;
                    this.type = 9;
                    this.type2 = -1;
                    this.attack1 = new Attack("Flamethrower");
                    this.attack2 = new Attack("Dragon Claw");
                    this.attack3 = new Attack("Growl");
                    this.attack4 = new Attack("Scratch");
                }
                break;
            case 66848470:
                if(var3.equals("Eevee")) {
                    this.baseHP = 55;
                    this.baseAttack = 55;
                    this.baseDefense = 50;
                    this.baseSpecAttack = 45;
                    this.baseSpecDefense = 65;
                    this.baseSpeed = 55;
                    this.type = 0;
                    this.type2 = -1;
                    this.attack1 = new Attack("Fake Tears");
                    this.attack2 = new Attack("Return");
                    this.attack3 = new Attack("Quick Attack");
                    this.attack4 = new Attack("Bite");
                }
                break;
            case 78725948:
                if(var3.equals("Ralts")) {
                    this.baseHP = 28;
                    this.baseAttack = 25;
                    this.baseDefense = 25;
                    this.baseSpecAttack = 45;
                    this.baseSpecDefense = 35;
                    this.baseSpeed = 40;
                    this.type = 13;
                    this.type2 = -1;
                    this.attack1 = new Attack("Psychic");
                    this.attack2 = new Attack("Shadow Ball");
                    this.attack3 = new Attack("Hypnosis");
                    this.attack4 = new Attack("Nightmare");
                }
                break;
            case 78966913:
                if(var3.equals("Riolu")) {
                    this.baseHP = 40;
                    this.baseAttack = 70;
                    this.baseDefense = 40;
                    this.baseSpecAttack = 35;
                    this.baseSpecDefense = 40;
                    this.baseSpeed = 60;
                    this.type = 1;
                    this.type2 = -1;
                    this.nickname = "Ryu";
                    this.attack1 = new Attack("Force Palm");
                    this.attack2 = new Attack("Ice Punch");
                    this.attack3 = new Attack("Bulk Up");
                    this.attack4 = new Attack("Blaze Kick");
                }
                break;
            case 86539759:
                if(var3.equals("Zoura")) {
                    this.baseHP = 40;
                    this.baseAttack = 65;
                    this.baseDefense = 40;
                    this.baseSpecAttack = 80;
                    this.baseSpecDefense = 40;
                    this.baseSpeed = 65;
                    this.type = 16;
                    this.type2 = -1;
                    this.attack1 = new Attack("Dark Pulse");
                    this.attack2 = new Attack("Nasty Plot");
                    this.attack3 = new Attack("Agillity");
                    this.attack4 = new Attack("Extrasensory");
                }
                break;
            case 1093716257:
                if(var3.equals("Pikachu")) {
                    this.baseHP = 35;
                    this.baseAttack = 55;
                    this.baseDefense = 40;
                    this.baseSpecAttack = 50;
                    this.baseSpecDefense = 50;
                    this.baseSpeed = 90;
                    this.type = 12;
                    this.type2 = -1;
                    idleSprites = 2;
                    this.nickname = "Sparky";
                    this.attack1 = new Attack("Thunderbolt");
                    this.attack2 = new Attack("Quick Attack");
                    this.attack3 = new Attack("Iron Tail");
                    this.attack4 = new Attack("Double Team");
                }
                break;
            case 1891220428:
                if(var3.equals("Chikorita")) {
                    this.baseHP = 45;
                    this.baseAttack = 49;
                    this.baseDefense = 65;
                    this.baseSpecAttack = 49;
                    this.baseSpecDefense = 65;
                    this.baseSpeed = 45;
                    this.type = 11;
                    this.type2 = -1;
                    this.attack1 = new Attack("Razor Leaf");
                    this.attack2 = new Attack("Light Screen");
                    this.attack3 = new Attack("Body Slam");
                    this.attack4 = new Attack("Synthesis");
                }
                break;
            case 1986116851:
                if(var3.equals("Beldum")) {
                    this.baseHP = 45;
                    this.baseAttack = 55;
                    this.baseDefense = 80;
                    this.baseSpecAttack = 35;
                    this.baseSpecDefense = 60;
                    this.baseSpeed = 30;
                    this.type = 8;
                    this.type2 = 13;
                    this.attack1 = new Attack("Take Down");
                    this.attack2 = new Attack("Iron Defense");
                    this.attack3 = new Attack("Zen Headbutt");
                    this.attack4 = new Attack("Iron Head");
                }
        }

        int numRunning = spriteSheet.getWidth() / 250;
        BufferedImage[] idle = new BufferedImage[idleSprites];
        BufferedImage[] running = new BufferedImage[numRunning];
        BufferedImage[] hurt = new BufferedImage[1];

        int i;
        for(i = 0; i < idleSprites; ++i) {
            idle[i] = spriteSheet.getSubimage(i * 250, 0, 250, 250);
        }

        for(i = 0; i < numRunning; ++i) {
            running[i] = spriteSheet.getSubimage(i * 250, 250, 250, 250);
        }

        for(i = 0; i < hurt.length; ++i) {
            hurt[i] = spriteSheet.getSubimage(i * 250, 500, 250, 250);
        }

        this.sprites.add(idle);
        this.sprites.add(running);
        this.sprites.add(hurt);
        this.currentAction = 0;
        this.animation.setFrames((BufferedImage[])this.sprites.get(0));
        this.animation.setDelay(100L);
        switch(this.team) {
            case 0:
                this.teamColor = Color.BLUE;
                break;
            case 1:
                this.teamColor = Color.RED;
                break;
            case 2:
                this.teamColor = Color.GREEN;
                break;
            case 3:
                this.teamColor = Color.ORANGE;
        }

        this.maxHP = this.calculateHP();
        this.hp = this.maxHP;
        this.attack = this.calculateStat(this.baseAttack);
        this.defense = this.calculateStat(this.baseDefense);
        this.specAttack = this.calculateStat(this.baseSpecAttack);
        this.specDefense = this.calculateStat(this.baseSpecDefense);
        this.speed = this.calculateStat(this.baseSpeed);
    }

    public int getx() {
        return (int)this.x;
    }

    public int gety() {
        return (int)this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getCWidth() {
        return this.cwidth;
    }

    public int getCHeight() {
        return this.cheight;
    }

    public void setDestination(int row, int col) {
        this.walking = false;
        this.colDestination = col;
        this.rowDestination = row;
    }

    public void move() {
        this.walking = true;
        this.foundPossiblePaths = false;
        this.currentAction = 1;
        this.animation.setFrames((BufferedImage[])this.sprites.get(1));
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition() {
        this.xmap = this.tileMap.getx();
        this.ymap = this.tileMap.gety();
    }

    public void setLeft(boolean b) {
        this.left = b;
    }

    public void setRight(boolean b) {
        this.right = b;
    }

    public void setUp(boolean b) {
        this.up = b;
    }

    public void setDown(boolean b) {
        this.down = b;
    }

    public boolean notOnScreen() {
        return this.x + this.xmap + (double)this.width < 0.0D || this.x + this.xmap - (double)this.width > 1280.0D || this.y + this.ymap + (double)this.height < 0.0D || this.y + this.ymap - (double)this.height > 720.0D;
    }

    public void update() {
        this.animation.update();
        if(this.walking) {
            if(this.directionLCV > this.directions.length - 1) {
                this.left = false;
                this.right = false;
                this.up = false;
                this.down = false;
                this.walking = false;
                this.animation.setFrames((BufferedImage[])this.sprites.get(0));
                this.directionLCV = 0;
                this.distanceCount = 0;
                this.setPosition((double)(this.colDestination * this.tileSize), (double)(this.rowDestination * this.tileSize));
            } else if(this.distanceCount >= this.tileSize) {
                this.findDirection();
                this.distanceCount = 0;
            }

            this.distanceCount += 16;
            if(this.up) {
                this.y -= 16.0D;
            } else if(this.down) {
                this.y += 16.0D;
            } else if(this.left) {
                this.x -= 16.0D;
            } else if(this.right) {
                this.x += 16.0D;
            }
        }

        if(this.currentAction == 2) {
            long var1 = (System.nanoTime() - this.flinchTimer) / 1000000L;
        }

    }

    private void findDirection() {
        switch(this.directions[this.directionLCV]) {
            case 0:
                this.right = true;
                this.left = false;
                this.down = false;
                this.up = false;
                break;
            case 1:
                this.left = true;
                this.right = false;
                this.down = false;
                this.up = false;
                break;
            case 2:
                this.up = true;
                this.left = false;
                this.down = false;
                this.right = false;
                break;
            case 3:
                this.down = true;
                this.left = false;
                this.right = false;
                this.up = false;
        }

        ++this.directionLCV;
    }

    public boolean pathFinderVisited(int row, int col) {
        return false;
    }

    public void getShortestPath(int row, int col) {
        this.rowDestination = row;
        this.colDestination = col;
        this.currRow = (int)this.y / this.tileSize;
        this.currCol = (int)this.x / this.tileSize;
        if(row != this.currRow || col != this.currCol) {
            PathFinder finder = new AStarPathFinder(this.tileMap, this.speed, false);
            if(!this.foundPossiblePaths) {
                boolean[][] possiblePaths = new boolean[this.tileMap.getNumRows()][this.tileMap.getNumCols()];

                for(int r = this.currRow - this.speed <= 0?0:this.currRow - this.speed; r < this.tileMap.getNumRows() && r <= this.currRow + this.speed; ++r) {
                    for(int c = this.currCol - this.speed <= 0?0:this.currCol - this.speed; c < this.tileMap.getNumCols() && c <= this.currCol + this.speed; ++c) {
                        boolean possible = finder.findPath(this, (int)this.y / this.tileSize, (int)this.x / this.tileSize, r, c) != null;
                        possiblePaths[r][c] = possible;
                    }
                }

                this.tileMap.setPossiblePaths(possiblePaths);
                this.foundPossiblePaths = true;
            }

            Path path = finder.findPath(this, (int)this.y / this.tileSize, (int)this.x / this.tileSize, row, col);
            if(path != null) {
                int right = 0;
                int left = 1;
                int up = 2;
                int down = 3;
                int horizontal = 1;
                int vertical = 0;
                int upRight = 2;
                int upLeft = 3;
                int downRight = 5;
                int downLeft = 4;
                this.directions = new int[path.getLength()];

                for(int x = 0; x < path.getLength() - 1; ++x) {
                    if(path.getCol(x + 1) > path.getCol(x)) {
                        this.directions[x] = right;
                    } else if(path.getCol(x + 1) < path.getCol(x)) {
                        this.directions[x] = left;
                    } else if(path.getRow(x + 1) > path.getRow(x)) {
                        this.directions[x] = down;
                    } else if(path.getRow(x + 1) < path.getRow(x)) {
                        this.directions[x] = up;
                    }
                }

                int[] arrow = new int[path.getLength()];

                for(int x = 1; x < path.getLength(); ++x) {
                    if(this.directions[x] == right && this.directions[x - 1] == right || this.directions[x] == left && this.directions[x - 1] == left) {
                        arrow[x] = horizontal;
                    } else if((this.directions[x] != up || this.directions[x - 1] != up) && (this.directions[x] != down || this.directions[x - 1] != down)) {
                        if((this.directions[x] != up || this.directions[x - 1] != right) && (this.directions[x] != left || this.directions[x - 1] != down)) {
                            if((this.directions[x] != up || this.directions[x - 1] != left) && (this.directions[x] != right || this.directions[x - 1] != down)) {
                                if(this.directions[x] == down && this.directions[x - 1] == left || this.directions[x] == right && this.directions[x - 1] == up) {
                                    arrow[x] = upRight;
                                } else if(this.directions[x] == down && this.directions[x - 1] == right || this.directions[x] == left && this.directions[x - 1] == up) {
                                    arrow[x] = upLeft;
                                }
                            } else {
                                arrow[x] = downRight;
                            }
                        } else {
                            arrow[x] = downLeft;
                        }
                    } else {
                        arrow[x] = vertical;
                    }
                }

                arrow[0] = this.directions[0];
                arrow[arrow.length - 1] = this.directions[this.directions.length - 2] + 6;
                this.tileMap.setPath(path, arrow);
            }

        }
    }

    public void hurt() {
        if(this.currentAction != 2) {
            this.currentAction = 2;
            this.animation.setFrames((BufferedImage[])this.sprites.get(2));
        }

        this.flinchTimer = System.nanoTime();
    }

    public void idle() {
        if(this.currentAction != 0) {
            this.currentAction = 0;
            this.animation.setFrames((BufferedImage[])this.sprites.get(0));
        }

    }

    public void draw(Graphics2D g, int x, int y, int w, int h) {
        if(this.currentAction == 2) {
            long elapsed = (System.nanoTime() - this.flinchTimer) / 1000000L;
            if(elapsed / 100L % 2L == 0L) {
                return;
            }
        }

        if(this.facingRight) {
            g.drawImage(this.animation.getImage(), x, y - this.height / 2, w, h, (ImageObserver)null);
        } else {
            g.drawImage(this.animation.getImage(), x - this.width / 2 + this.width, y - this.height / 2, -w, h, (ImageObserver)null);
        }

    }

    public void draw(Graphics2D g) {
        this.setMapPosition();
        if(!this.notOnScreen()) {
            g.setFont(this.font);
            g.setColor(this.teamColor);
            g.drawString(this.nickname, (int)(this.x + this.xmap), (int)(this.y + this.ymap));
            if(this.facingRight) {
                g.drawImage(this.animation.getImage(), (int)(this.x + this.xmap), (int)(this.y + this.ymap), this.width, this.height, (ImageObserver)null);
            } else {
                g.drawImage(this.animation.getImage(), (int)(this.x + this.xmap + (double)this.width), (int)(this.y + this.ymap), -this.width, this.height, (ImageObserver)null);
            }
        }

    }

    public static double getStatChange(int stage) {
        switch(stage) {
            case -6:
                return 0.25D;
            case -5:
                return 0.285D;
            case -4:
                return 0.33D;
            case -3:
                return 0.4D;
            case -2:
                return 0.5D;
            case -1:
                return 0.66D;
            case 0:
                return 1.0D;
            case 1:
                return 1.5D;
            case 2:
                return 2.0D;
            case 3:
                return 2.5D;
            case 4:
                return 3.0D;
            case 5:
                return 3.5D;
            case 6:
                return 4.0D;
            default:
                return 1.0D;
        }
    }

    public void drawHeadshot(int x, int y, int w, int h, Graphics2D g) {
        g.drawImage(this.headShot, x, y, w, h, (ImageObserver)null);
    }

    public int calculateHP() {
        return (2 * this.baseHP + 100) * this.level / 100 + 10;
    }

    public int calculateStat(int base) {
        return 2 * base * this.level / 100 + 5;
    }

    public static double getEffectiveness(int attackingType, int defendingType, int defendingType2) {
        if(defendingType2 != -1) {
            System.out.println(types[attackingType] + " vs " + types[defendingType] + "1st Type " + effectiveness[attackingType][defendingType]);
            System.out.println(types[attackingType] + " vs " + types[defendingType2] + effectiveness[attackingType][defendingType2]);
            return effectiveness[attackingType][defendingType] * effectiveness[attackingType][defendingType2];
        } else {
            System.out.println(types[attackingType] + " vs " + types[defendingType]);
            System.out.println(effectiveness[attackingType][defendingType]);
            return effectiveness[attackingType][defendingType];
        }
    }

    public int getCurrentAction() {
        return this.currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getCurrAttack() {
        return this.currAttack;
    }

    public void setCurrAttack(int currAttack) {
        this.currAttack = currAttack;
    }

    public int getCurrAccuracy() {
        return this.currAccuracy;
    }

    public void setCurrAccuracy(int currAccurracy) {
        this.currAccuracy = currAccurracy;
    }

    public int getCurrDefense() {
        return this.currDefense;
    }

    public void setCurrDefense(int currDefense) {
        this.currDefense = currDefense;
    }

    public int getCurrSpecAttack() {
        return this.currSpecAttack;
    }

    public void setCurrSpecAttack(int currSpecAttack) {
        this.currSpecAttack = currSpecAttack;
    }

    public int getCurrSpecDefense() {
        return this.currSpecDefense;
    }

    public void setCurrSpecDefense(int currSpecDefense) {
        this.currSpecDefense = currSpecDefense;
    }

    public int getCurrSpeed() {
        return this.currSpeed;
    }

    public void setCurrSpeed(int currSpeed) {
        this.currSpeed = currSpeed;
    }

    public int getCurrEvasiveness() {
        return this.currEvasiveness;
    }

    public void setCurrEvasiveness(int currEvasiveness) {
        this.currEvasiveness = currEvasiveness;
    }

    public int getStatusEffect() {
        return this.statusEffect;
    }

    public void setStatusEffect(int statusEffect) {
        this.statusEffect = statusEffect;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecAttack() {
        return this.specAttack;
    }

    public void setSpecAttack(int specAttack) {
        this.specAttack = specAttack;
    }

    public int getSpecDefense() {
        return this.specDefense;
    }

    public void setSpecDefense(int specDefense) {
        this.specDefense = specDefense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return this.type;
    }

    public int getType2() {
        return this.type2;
    }

    public int getBaseSpeed() {
        return this.baseSpeed;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public Attack getAttack1() {
        return this.attack1;
    }

    public Attack getAttack2() {
        return this.attack2;
    }

    public Attack getAttack3() {
        return this.attack3;
    }

    public Attack getAttack4() {
        return this.attack4;
    }

    public void setMovedAlready(boolean moved) {
        this.movedAlready = moved;
    }

    public boolean hasMovedAlready() {
        return this.movedAlready;
    }

    public int getTeam() {
        return this.team;
    }

    public Attack getAttack(int attack) {
        switch(attack) {
            case 0:
                return this.attack1;
            case 1:
                return this.attack2;
            case 2:
                return this.attack3;
            case 3:
                return this.attack4;
            default:
                return null;
        }
    }

    public void setDamage(int damage) {
        this.hp -= damage;
    }

    public boolean isFacingRight() {
        return this.facingRight;
    }

    public Color getTeamColor() {
        return this.teamColor;
    }

    public boolean isWalking() {
        return this.walking;
    }
}
