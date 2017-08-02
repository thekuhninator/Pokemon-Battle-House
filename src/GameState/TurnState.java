//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import Entity.Controller;
import Entity.Pokemon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class TurnState extends GameState {
    private boolean canAttack;
    private boolean moreThanOne;
    private Image menu;
    private PlayState playstate;
    private Controller controller;
    HashMap<Point, Pokemon> mapObjects;
    private int currentOption;
    private int selectedRow;
    private int selectedCol;
    private Pokemon controlledPokemon;
    private Pokemon possibleDefender;
    private Font font;
    private Font selectedFont;
    private ArrayList<String> options;
    private boolean choosing;

    public TurnState(GameStateManager gsm) {
        this.gsm = gsm;
        this.playstate = gsm.getPlayState();
        this.controller = this.playstate.getController();

        try {
            this.menu = ImageIO.read(this.getClass().getResourceAsStream("/Images/GUI/moveMenu.png"));
            this.font = Font.createFont(0, this.getClass().getResourceAsStream("/Fonts/theboldfont.ttf"));
            this.font = this.font.deriveFont(40.0F);
            this.selectedFont = this.font.deriveFont(50.0F);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void init() {
        this.controlledPokemon = this.playstate.getControlledPokemon();
        this.currentOption = 0;
        this.moreThanOne = false;
        this.canAttack = false;
        Point p = this.playstate.getPosition();
        this.selectedCol = p.x;
        this.selectedRow = p.y;
        this.options = new ArrayList();
        this.options.add("Wait");
        this.options.add("Cancel");
        this.checkAttack();
        this.choosing = false;
    }

    public void back() {
    }

    public void update() {
        this.playstate.update();
    }

    public void draw(Graphics2D g) {
        this.playstate.draw(g);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawImage(this.menu, 100, 100, (ImageObserver)null);
        g.setColor(Color.WHITE);

        for(int x = 0; x < this.options.size(); ++x) {
            if(this.currentOption == x) {
                g.setFont(this.selectedFont);
            } else {
                g.setFont(this.font);
            }

            g.drawString((String)this.options.get(x), 150, 150 + x * 75);
        }

    }

    private void checkAttack() {
        int count = 0;
        this.mapObjects = this.playstate.getMapObjects();
        Point left = new Point(this.selectedCol - 1, this.selectedRow);
        Point right = new Point(this.selectedCol + 1, this.selectedRow);
        Point up = new Point(this.selectedCol, this.selectedRow - 1);
        Point down = new Point(this.selectedCol, this.selectedRow + 1);
        int team = this.controlledPokemon.getTeam();
        if(this.mapObjects.get(left) != null && ((Pokemon)this.mapObjects.get(left)).getTeam() != team) {
            ++count;
            this.possibleDefender = (Pokemon)this.mapObjects.get(left);
        }

        if(this.mapObjects.get(right) != null && ((Pokemon)this.mapObjects.get(right)).getTeam() != team) {
            ++count;
            this.possibleDefender = (Pokemon)this.mapObjects.get(right);
        }

        if(this.mapObjects.get(up) != null && ((Pokemon)this.mapObjects.get(up)).getTeam() != team) {
            ++count;
            this.possibleDefender = (Pokemon)this.mapObjects.get(up);
        }

        if(this.mapObjects.get(down) != null && ((Pokemon)this.mapObjects.get(down)).getTeam() != team) {
            ++count;
            this.possibleDefender = (Pokemon)this.mapObjects.get(down);
        }

        if(count >= 1) {
            if(count > 1) {
                this.moreThanOne = true;
            }

            this.options.add(0, "Attack");
            this.canAttack = true;
            BattleState.setPokemon1(this.controlledPokemon);
            BattleState.setPokemon2(this.possibleDefender);
        }
    }

    public void keyPressed(int k) {
        int newRow = this.selectedRow;
        int newCol = this.selectedCol;
        if(!this.choosing) {
            switch(k) {
                case 38:
                    --this.currentOption;
                    if(this.currentOption < 0) {
                        this.currentOption = this.options.size() - 1;
                    }
                    break;
                case 40:
                    ++this.currentOption;
                    if(this.currentOption > this.options.size() - 1) {
                        this.currentOption = 0;
                    }
                    break;
                case 88:
                    this.gsm.setState(2);
                    break;
                case 90:
                    this.select();
            }

        } else {
            switch(k) {
                case 37:
                    --newCol;
                    break;
                case 38:
                    --newRow;
                    break;
                case 39:
                    ++newCol;
                    break;
                case 40:
                    ++newRow;
                    break;
                case 88:
                    this.choosing = false;
                    break;
                case 90:
                    BattleState.setPokemon2((Pokemon)this.mapObjects.get(new Point((int)(this.controller.getPosX() / 125.0D), (int)(this.controller.getPosY() / 125.0D))));
                    this.controller.setPos((double)(this.selectedCol * 125), (double)(this.selectedRow * 125));
                    this.playstate.update();
                    this.playstate.battle();
            }

            if(this.mapObjects.get(new Point(newCol, newRow)) != null) {
                this.controller.setPosX((double)(newCol * 125));
                this.controller.setPosY((double)(newRow * 125));
            }

        }
    }

    private void select() {
        String var1;
        switch((var1 = (String)this.options.get(this.currentOption)).hashCode()) {
            case 2688405:
                if(var1.equals("Wait")) {
                    this.playstate.movePokemon();
                }
                break;
            case 70973344:
                if(!var1.equals("Items")) {
                    ;
                }
                break;
            case 1971575400:
                if(var1.equals("Attack")) {
                    if(this.moreThanOne) {
                        this.choosing = true;
                        return;
                    }

                    BattleState.setPokemon1(this.controlledPokemon);
                    this.controlledPokemon.move();
                    this.playstate.battle();
                }
        }

        this.gsm.setState(2);
    }

    public void keyReleased(int k) {
    }
}
