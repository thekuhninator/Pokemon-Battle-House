//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import java.awt.Graphics2D;

public class GameStateManager {
    private GameState[] gameStates = new GameState[9];
    private int currentState = 0;
    public static final int NUMGAMESTATES = 9;
    public static final int TITLESTATE = 0;
    public static final int MENUSTATE = 1;
    public static final int PLAYSTATE = 2;
    public static final int BATTLESTATE = 3;
    public static final int VICTORYSTATE = 4;
    public static final int PAUSESTATE = 5;
    public static final int OPTIONSTATE = 6;
    public static final int TURNSTATE = 7;
    public static final int CREDITSSTATE = 8;

    public GameStateManager() {
        this.loadState(this.currentState);
    }

    private void loadState(int state) {
        if(this.gameStates[state] == null) {
            switch(state) {
                case 0:
                    this.gameStates[state] = new TitleState(this);
                    break;
                case 1:
                    this.gameStates[state] = new MenuState(this);
                    break;
                case 2:
                    this.gameStates[state] = new PlayState(this);
                    break;
                case 3:
                    this.gameStates[state] = new BattleState(this);
                    break;
                case 4:
                    this.gameStates[state] = new VictoryState(this);
                    break;
                case 5:
                    this.gameStates[state] = new PauseState(this);
                    break;
                case 6:
                    this.gameStates[state] = new OptionState(this);
                    break;
                case 7:
                    this.gameStates[state] = new TurnState(this);
                    break;
                case 8:
                    this.gameStates[state] = new CreditsState(this);
            }
        }

    }

    public PlayState getPlayState() {
        return (PlayState)this.gameStates[2];
    }

    private void unloadState(int state) {
    }

    public void setState(int state) {
        this.unloadState(this.currentState);
        this.currentState = state;
        this.loadState(this.currentState);
        this.gameStates[this.currentState].init();
        if(this.gameStates[this.currentState] != null) {
            this.gameStates[this.currentState].back();
        }

    }

    public void update() {
        if(this.gameStates[this.currentState] != null) {
            this.gameStates[this.currentState].update();
        }

    }

    public void draw(Graphics2D g) {
        if(this.gameStates[this.currentState] != null) {
            this.gameStates[this.currentState].draw(g);
        }

    }

    public void keyPressed(int k) {
        if(this.gameStates[this.currentState] != null) {
            this.gameStates[this.currentState].keyPressed(k);
        }

    }

    public void keyReleased(int k) {
        if(this.gameStates[this.currentState] != null) {
            this.gameStates[this.currentState].keyReleased(k);
        }

    }
}
