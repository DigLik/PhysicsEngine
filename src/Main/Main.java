package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Main.Config.*;
import static Main.Functions.*;
import static Main.vectorFunctions.*;

public class Main {
    public static ArrayList<ArrayList<Double>> array = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < circleCount; i++) {
            array.add(new ArrayList<>(List.of(
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
            GUI.clearWindow(buffer.getDrawGraphics());

            for (ArrayList<Double> objects : array) {
                GUI.drawCircle(
                        (int) Math.round(objects.get(0)),
                        (int) Math.round(objects.get(1)),
                        (int) Math.round(objects.get(2)),
                        buffer.getDrawGraphics());}
            buffer.show();

            try {
                TimeUnit.MILLISECONDS.sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<Double> objectsPhysicsCalculation(ArrayList<Double> object1, ArrayList<Double> object2) {
        double x1 = object1.get(0), y1 = object1.get(1);
        double angle = getAngle(object1, object2);
        double force = gravityForce(object1, object2) / object1.get(5);

        if (x1 < 0 || x1 > windowWidth - object1.get(2))
            object1.set(3, -object1.get(3));
        if (y1 < 0 || y1 > windowHeight - object1.get(2))
            object1.set(4, -object1.get(4));

        double x = Math.cos(angle) * force + object1.get(3);
        double y = Math.sin(angle) * force + object1.get(4);

        return new ArrayList<>(List.of(object1.get(0) + x, object1.get(1) + y, object1.get(2), x, y, object1.get(5)));
    }
}

class physicsThread extends Thread {
    private ArrayList<ArrayList<Double>> tempArray;

    public physicsThread(ArrayList<ArrayList<Double>> array) {
        this.tempArray = array;
    }
    public void run() {
        while (true) {
            for (int i = 0; i < tempArray.size(); i++) {
                for (int j = 0; j < tempArray.size(); j++) {
                    if (i != j) {
                        tempArray.set(i, Main.objectsPhysicsCalculation(tempArray.get(i), tempArray.get(j)));
                    }
                }
            }
            Main.array = tempArray;
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
