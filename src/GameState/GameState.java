//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package GameState;

import java.awt.Graphics2D;

public abstract class GameState {
    protected GameStateManager gsm;

    public GameState() {
    }

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D var1);

    public abstract void keyPressed(int var1);

    public abstract void keyReleased(int var1);

    public abstract void back();
}
