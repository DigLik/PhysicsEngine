package Main;

import javax.swing.*;
import java.awt.*;

public class GUI extends JComponent {
    public static JFrame jframe = new JFrame();

    public static void createWindow() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("PhysicsEngine");
        jframe.setBackground(Config.backgroundColor);
        jframe.getContentPane().setPreferredSize(new java.awt.Dimension(Config.windowWidth, Config.windowHeight));
        jframe.setLocation(
                Config.displayWidth  / 2 - Config.windowWidth  / 2,
                Config.displayHeight / 2 - Config.windowHeight / 2
        );
        jframe.pack();
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public static void clearWindow(Graphics g) {
        g.setColor(Config.backgroundColor);
        g.fillRect(0, 0, Config.windowWidth, Config.windowHeight);
    }

    public static void drawCircle(int x, int y, int radius, Graphics g) {
        g.setColor(Config.circleColor);
        g.fillOval(x, y, radius, radius);
    }
}
