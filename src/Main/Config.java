package Main;

import java.awt.*;

public class Config {
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static int displayHeight = toolkit.getScreenSize().height;
    public static int displayWidth = toolkit.getScreenSize().width;
    public static int windowHeight = 800, windowWidth = 1600;
    public static Color backgroundColor = Color.BLACK;
    public static Color circleColor = Color.WHITE;
    public static int circleRadius = 25;
    public static double circleMass = Math.pow(10, 6);
    public static double G = 6.67430e-11;
    public static double deltaT = Math.pow(10, 6);
    public static int FPS = 60;
}
