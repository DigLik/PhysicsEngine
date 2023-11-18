package Main;

import java.util.ArrayList;

import static Main.Config.*;

public class Functions {
    public static double random(double max) {
        return (int) Math.round(Math.random() * (max - circleRadius));
    }
}

class vectorFunctions {
    public static double getAngle(ArrayList<Double> object1, ArrayList<Double> object2) {
        return Math.atan2(object2.get(1) - object1.get(1), object2.get(0) - object1.get(0));
    }
    public static double distance(ArrayList<Double> object1, ArrayList<Double> object2) {
        return Math.sqrt(Math.pow(object1.get(0) - object2.get(0), 2) + Math.pow(object2.get(1) - object1.get(1), 2));
    }
    public static double gravityForce(ArrayList<Double> object1, ArrayList<Double> object2) {
        if (distance(object1, object2) < circleRadius) return -1.0;
        else return G * (object1.get(5) * object2.get(5)) / Math.pow(distance(object1, object2), 1) * deltaT;
    }
}
