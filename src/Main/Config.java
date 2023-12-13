package Main;

import java.awt.*;

public class Config {
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static int displayHeight = toolkit.getScreenSize().height;
    static int displayWidth = toolkit.getScreenSize().width;
    static int windowWidth = 1600, windowHeight = 800;
    static Color backgroundColor = Color.BLACK;
    static double elasticityCoefficientWalls = 1;
    static double elasticityCoefficient = 1;
    static Color circleColor = Color.WHITE;
    static int circleCount = 150;
    static double circleDiameter = 25;
    static double circleMass = Math.pow(10, 5);
    static double deltaT = Math.pow(10, 3);
    static double G = 6.67430e-11;
    static int FPS = 165;
}
