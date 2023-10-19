package Main;

import javax.swing.*;
import java.awt.*;

public class GUI extends JComponent {
    public static JFrame jframe = new JFrame();

    public static void createWindow() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("Windows");
        jframe.setBackground(Config.backgroundColor);
        jframe.setSize(Config.windowWidth, Config.windowHeight);
        jframe.setLocation(
                Config.displayWidth  / 2 - Config.windowWidth  / 2,
                Config.displayHeight / 2 - Config.windowHeight / 2
        );
        jframe.getContentPane().add(new GUI());
        jframe.setVisible(true);
    }

    public static void clearWindow(Graphics g) {
        try {
            g.setColor(Config.backgroundColor);
            g.fillRect(0, 0, Config.windowWidth, Config.windowHeight);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void drawCircle(int x, int y, int radius, Color color, Graphics g) {
        try {
            g.setColor(color);
            g.fillOval(x, y, radius, radius);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
