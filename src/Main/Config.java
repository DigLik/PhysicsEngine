package Main;

import java.awt.*;

public class Config {
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static int displayHeight = toolkit.getScreenSize().height;
    public static int displayWidth = toolkit.getScreenSize().width;
    public static int windowHeight = 400, windowWidth = 600;
    public static Color backgroundColor = Color.BLACK;
    public static Color circleColor = Color.WHITE;
    public static int circleRadius = 20;
    public static int circleMass = 1000;
    public static int FPS = 165;
}
