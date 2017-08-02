//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Main;

import GameState.GameStateManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int SCALE = 2;
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime;
    private BufferedImage image;
    private Graphics2D g;
    private GameStateManager gsm;

    public GamePanel() {
        this.targetTime = (long)(1000 / this.FPS);
        this.setPreferredSize(new Dimension(1280 * SCALE,720  * SCALE));
        this.setFocusable(true);
        this.requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(this.thread == null) {
            this.thread = new Thread(this);
            this.addKeyListener(this);
            this.thread.start();
        }

    }

    private void init() {
        this.image = new BufferedImage(1280, 720, 1);
        this.g = (Graphics2D)this.image.getGraphics();
        this.running = true;
        this.gsm = new GameStateManager();
    }

    public void run() {
        this.init();

        while(this.running) {
            long start = System.nanoTime();
            this.update();
            this.draw();
            this.drawToScreen();
            long elapsed = System.nanoTime() - start;
            long wait = this.targetTime - elapsed / 1000000L;
            if(wait < 0L) {
                wait = 5L;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

    }

    private void update() {
        this.gsm.update();
    }

    private void draw() {
        this.gsm.draw(this.g);
    }

    private void drawToScreen() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(this.image, 0, 0, 1280 * SCALE, 720 * SCALE, (ImageObserver)null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        this.gsm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
        this.gsm.keyReleased(key.getKeyCode());
    }
}
