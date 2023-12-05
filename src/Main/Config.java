package Main;

import java.awt.*;

public class Config {
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static int displayHeight = toolkit.getScreenSize().height;
    public static int displayWidth = toolkit.getScreenSize().width;
    public static int windowWidth = 1600, windowHeight = 800;
    public static Color backgroundColor = Color.BLACK;
    public static double elasticityCoefficientObjects = 1;
    public static double elasticityCoefficientWalls = 1;
    public static Color circleColor = Color.WHITE;
    public static int circleCount = 100;
    public static double circleDiameter = 10;
    public static double circleMass = Math.pow(10, 3);
    public static double deltaT = Math.pow(10, 5);
    public static double G = 6.67430e-11;
    public static int FPS = 60;
}
