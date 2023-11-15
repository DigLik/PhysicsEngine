package Main;

import static Main.Config.*;

public class Functions {
    public static double random(double max) {
        return (int) Math.round(Math.random() * max);
    }
    public static double hypotenuse(double x, double y) {
        return Math.hypot(x, y);
    }
    public static double legX(double x1, double x2) {
        return x2 - x1;
    }
    public static double legY(double y1, double y2) {
        return y2 - y1;
    }
    public static double angle(double legX , double legY) {
        return Math.atan2(legY, legX);
    }
    public static double gravityForce(double mass1, double mass2, double distance) {
        if (distance <= circleRadius) {return 0;}
        return G * ((mass1 * mass2) / Math.pow(distance, 2)) * deltaT;
    }
    public static double[] sumVector(double alpha1, double force1, double alpha2, double force2) {
        double x1 = force1 * Math.cos(alpha1);
        double y1 = force1 * Math.sin(alpha1);
        double x2 = force2 * Math.cos(alpha2);
        double y2 = force2 * Math.sin(alpha2);
        double x = x1 + x2;
        double y = y1 + y2;
        double alpha = Math.atan2(y, x);
        double force = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return new double[]{alpha, force};
    }
}
