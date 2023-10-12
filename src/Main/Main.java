package Main;

import java.util.concurrent.TimeUnit;
import java.awt.*;

import static Main.ArrayFunctions.*;
import static Main.Config.*;
import static Main.Functions.*;

public class Main {
    public static void main(String[] args) {
        Object[][] array = new Object[0][];

        array = addObject(array, 10, 10, circleRadius, 0, 0, circleMass, circleColor);
        array = addObject(array, 20, 0, circleRadius, 0, 0, circleMass, circleColor);
        array = addObject(array, 20, 10, circleRadius, 0, 0, circleMass, circleColor);
        array = addObject(array, 20, 20, circleRadius, 0, 0, circleMass, circleColor);

        /*createWindow();
        while (true) {
            clearWindow(gui.graphics);
            for (Object[] objects : array) {
                drawCircle(
                        (int) Math.round((Double) objects[0]),
                        (int) Math.round((Double) objects[1]),
                        (int) Math.round((Double) objects[2]),
                        circleColor, gui.graphics
                );
            }
            try { Thread.sleep(1000 / FPS);
            } catch (InterruptedException ignored) {}
        }*/
        while (true) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (i != j) {
                        array[i] = objectsPhysicsCalculation(array[i], array[j]);
                    }
                }
            }
            System.out.println("\n\n\n" + array[0][3] + "\n" + array[0][4]);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static Object[] objectsPhysicsCalculation(Object[] object1, Object[] object2) {
        double alpha1 = alpha(object1, object2);
        double totalForce = force(object2, object1);
        double force1 = (totalForce * (Double) object2[5]) / ((Double) object1[5] + (Double) object2[5]);
        double[] sumVector = sumVector(alpha1, force1, alpha1, force1);
        return new Object[]
                {object1[0], object1[1], object1[2], (Double) object1[3] + sumVector[0],
                (Double) object1[4] + sumVector[1], object1[5], object1[6]};
    }
    public static double distance(Object[] object1, Object[] object2) {
        return hypotenuse(legX((Double) object1[0], (Double) object2[0]), legY((Double) object1[1], (Double) object2[1]));
    }
    public static double alpha(Object[] object1, Object[] object2) {
        return angle(legX((Double) object1[0], (Double) object2[0]), legY((Double) object1[1], (Double) object2[1]));
    }
    public static double force(Object[] object1, Object[] object2) {
        return gravityForce((Double) object1[2], (Double) object2[2], distance(object1, object2));
    }
}
