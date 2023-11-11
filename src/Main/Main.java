package Main;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static Main.ArrayFunctions.*;
import static Main.Config.*;
import static Main.Functions.*;

public class Main {
    public static void main(String[] args) {
        Object[][] array = new Object[0][];
        for (int i = 0; i < 100; i++) {
            array = addObject(array,
                    random(0, windowWidth),
                    random(0, windowHeight),
                    circleRadius, 0, 0,
                    circleMass, circleColor);
        }

        physicsThread physics = new physicsThread(array);
        physics.start();

        GUI.createWindow();
        Graphics graphics = GUI.jframe.getGraphics();

        while (true) {
            array = physics.getArray();

            GUI.clearWindow(graphics);
            for (Object[] objects : array) {
                GUI.drawCircle(
                        (int) Math.round((Double) objects[0]),
                        (int) Math.round((Double) objects[1]),
                        (int) Math.round((Double) objects[2]),
                        (Color) objects[6], graphics);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static Object[] objectsPhysicsCalculation(Object[] object1, Object[] object2) {
        double alpha1 = alpha(object1, object2);
        double totalForce = force(object2, object1);
        double force1 = (totalForce * (Double) object2[5]) / ((Double) object1[5] + (Double) object2[5]);
        double[] sumVector = sumVector(alpha1, force1, (Double) object1[3], (Double) object2[4]);
        return new Object[]
                {object1[0], object1[1], object1[2], sumVector[0], sumVector[1], object1[5], object1[6]};
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

class physicsThread extends Thread {
    private Object[][] array;

    public physicsThread(Object[][] tempArray) {
        this.array = tempArray;
    }

    public void run() {
        while (true) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (i != j) {
                        array[i] = Main.objectsPhysicsCalculation(array[i], array[j]);
                    }
                }
            }

            for (int i = 0; i < array.length; i++) {
                array[i][0] = (Double) array[i][0] + Math.cos((Double) array[i][3]) * (Double) array[i][4];
                array[i][1] = (Double) array[i][1] + Math.sin((Double) array[i][3]) * (Double) array[i][4];
            }
        }
    }

    public Object[][] getArray() {
        return array;
    }
}
