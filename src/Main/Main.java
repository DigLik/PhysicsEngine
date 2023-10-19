package Main;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static Main.ArrayFunctions.*;
import static Main.Config.*;
import static Main.Functions.*;

public class Main {
    public static void main(String[] args) {
        int temp = 0;

        Object[][] array = new Object[0][];
        for (int i = 0; i < 100; i++) {
            array = addObject(array,
                    random(0, windowWidth),
                    random(0, windowHeight),
                    circleRadius, 0, 0,
                    circleMass, circleColor);
        }

        GUI.createWindow();
        Graphics graphics = GUI.jframe.getGraphics();

        /*while (true) {
            GUI.clearWindow(g);
            for (Object[] objects : array) {
                GUI.drawCircle(
                        (int) Math.round((Double) objects[0]),
                        (int) Math.round((Double) objects[1]),
                        (int) Math.round((Double) objects[2]),
                        circleColor, g
                );
            }
            try { Thread.sleep(1000 / 5);
            } catch (InterruptedException ignored) {}
        }*/

        while (true) {
            for (int q = 0; q < 1; q++) {
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array.length; j++) {
                        if (i != j) {
                            array[i] = objectsPhysicsCalculation(array[i], array[j]);
                        }
                    }
                }
            }

            if (temp == 100) {
                System.out.println("\n\n\n" + "Angle: " + array[0][3] + "\n" + "Force: " + array[0][4]);
                System.out.println("\n" + "X: " + array[0][0] + "\n" + "Y: " + array[0][1]);
                temp = 0;
            }

            for (int i = 0; i < array.length; i++) {
                array[i][0] = (Double) array[i][0] + Math.cos((Double) array[i][3]) * (Double) array[i][4];
                array[i][1] = (Double) array[i][1] + Math.sin((Double) array[i][3]) * (Double) array[i][4];
            }

            GUI.clearWindow(graphics);
            for (int i = 0; i < array.length; i++) {
                GUI.drawCircle(
                        (int) Math.round((Double) array[i][0]),
                        (int) Math.round((Double) array[i][1]),
                        (int) Math.round((Double) array[i][2]),
                        (Color) array[i][6], graphics);
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
