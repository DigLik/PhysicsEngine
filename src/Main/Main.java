package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static Main.Config.*;
import static Main.Functions.*;

public class Main {
    public static ArrayList<ArrayList<Double>> array = new ArrayList<>();
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            array.add(new ArrayList<>(Arrays.asList(
                    random(windowWidth),
                    random(windowHeight),
                    circleRadius, 0.0, 0.0,
                    circleMass)));
        }

        physicsThread physics = new physicsThread(array);
        physics.start();

        GUI.createWindow();
        Canvas canvas = new Canvas();
        GUI.jframe.add(canvas);
        canvas.createBufferStrategy(2);
        BufferStrategy buffer = canvas.getBufferStrategy();

        while (true) {
            array = physics.getArray();

            GUI.clearWindow(buffer.getDrawGraphics());
            for (ArrayList<Double> objects : array) {
                GUI.drawCircle(
                        (int) Math.round(objects.get(0)),
                        (int) Math.round(objects.get(1)),
                        (int) Math.round(objects.get(2)),
                        buffer.getDrawGraphics());
            }
            buffer.show();
            try {
                TimeUnit.MILLISECONDS.sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static ArrayList<Double> objectsPhysicsCalculation(ArrayList<Double> object1, ArrayList<Double> object2) {
        double alpha1 = alpha(object1, object2);
        double totalForce = force(object2, object1);
        double force1 = (totalForce * object2.get(5)) / (object1.get(5) + object2.get(5));
        double[] sumVector = sumVector(alpha1, force1, object1.get(3), object2.get(4));
        return new ArrayList<>(Arrays.asList(object1.get(0), object1.get(1), object1.get(2), sumVector[0], sumVector[1], object1.get(5)));
    }
    public static double distance(ArrayList<Double> object1, ArrayList<Double> object2) {
        return hypotenuse(legX(object1.get(0), object2.get(0)), legY(object1.get(1), object2.get(1)));
    }
    public static double alpha(ArrayList<Double> object1, ArrayList<Double> object2) {
        return angle(legX(object1.get(0), object2.get(0)), legY(object1.get(1), object2.get(1)));
    }
    public static double force(ArrayList<Double> object1, ArrayList<Double> object2) {
        return gravityForce(object1.get(5), object2.get(2), distance(object1, object2));
    }
}

class physicsThread extends Thread {
    private ArrayList<ArrayList<Double>> array;

    public physicsThread(ArrayList<ArrayList<Double>> tempArray) {
        this.array = tempArray;
    }

    public void run() {
        while (true) {
            for (int i = 0; i < array.size(); i++) {
                for (int j = 0; j < array.size(); j++) {
                    if (i != j) {
                        array.set(i, Main.objectsPhysicsCalculation(array.get(i), array.get(j)));
                    }
                }
            }

            for (int i = 0; i < array.size(); i++) {
                array.get(i).set(0, array.get(i).get(0) + Math.cos(array.get(i).get(3)) * array.get(i).get(4));
                array.get(i).set(1, array.get(i).get(1) + Math.sin(array.get(i).get(3)) * array.get(i).get(4));
            }
        }
    }
    public ArrayList<ArrayList<Double>> getArray() {
        return array;
    }
}
