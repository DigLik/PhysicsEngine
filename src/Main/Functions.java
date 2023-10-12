package Main;

public class Functions {
    public static double G = 6.67430e-11;
    public static int random(int min, int max) {
        return (int) Math.round(Math.random() * (max - min + 1) + min);
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
        return G * ((mass1 * mass2) / Math.pow(distance, 2));
    }
    public static double[] sumVector(double r1, double a1, double r2, double a2) {
        double x1 = a1 * Math.cos(r1);
        double y1 = a1 * Math.sin(r1);
        double x2 = a2 * Math.cos(r2);
        double y2 = a2 * Math.sin(r2);
        double x = x1 + x2;
        double y = y1 + y2;
        double r = Math.atan2(y, x);
        double a = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return new double[]{r, a};
    }
}
