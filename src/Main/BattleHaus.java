//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Main;

import javax.swing.JFrame;

public class BattleHaus {
    public BattleHaus() {
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Battle Haus");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(3);
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
    }
}
