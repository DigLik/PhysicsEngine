package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Main.Config.*;
import static Main.Functions.*;

public class Main {
    public static ArrayList<ArrayList<Double>> array = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < circleCount; i++) {
            array.add(new ArrayList<>(List.of(
                    random(windowWidth),
                    random(windowHeight),
                    circleDiameter, 0.0, 0.0,
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
        double v1f_x, v1f_y, v2f_x, v2f_y;

        if (distance(object1, object2) <= object1.get(2)/2 + object2.get(2)/2) {
            double v1i_x = object1.get(3), v1i_y = object1.get(4);
            double v2i_x = object2.get(3), v2i_y = object2.get(4);
            double m1 = object1.get(5), m2 = object2.get(5);
            double e = elasticityCoefficient;
            v1f_x = ((m2 - e * m1) * v1i_x + (1 + e) * m2 * v2i_x) / (m1 + m2);
            v1f_y = ((m2 - e * m1) * v1i_y + (1 + e) * m2 * v2i_y) / (m1 + m2);
            v2f_x = ((m1 - e * m2) * v2i_x + (1 + e) * m1 * v1i_x) / (m1 + m2);
            v2f_y = ((m1 - e * m2) * v2i_y + (1 + e) * m1 * v1i_y) / (m1 + m2);
            object2.set(3, v2f_x);
            object2.set(4, v2f_y);
            array.set(array.indexOf(object2), object2);
        } else {
            double angle = getAngle(object1, object2);
            double force = gravityForce(object1, object2) / object1.get(5) * deltaT;
            v1f_x = Math.cos(angle) * force + object1.get(3);
            v1f_y = Math.sin(angle) * force + object1.get(4);
        }

        if (distance(object1, object2) < circleDiameter) {
            object1.set(0, x1 - (circleDiameter - distance(object1, object2)) * Math.cos(getAngle(object1, object2)));
            object1.set(1, y1 - (circleDiameter - distance(object1, object2)) * Math.sin(getAngle(object1, object2)));
        }
        if (x1 <= 0) object1.set(0, Math.abs(x1));
        if (y1 <= 0) object1.set(1, Math.abs(y1));
        if (x1 >= windowWidth - circleDiameter) object1.set(0, windowWidth - circleDiameter - Math.abs(x1 - (windowWidth - circleDiameter)));
        if (y1 >= windowHeight - circleDiameter) object1.set(1, windowHeight - circleDiameter - Math.abs(y1 - (windowHeight - circleDiameter)));
        if (x1 <= 0 || x1 >= windowWidth - circleDiameter) v1f_x = -v1f_x;
        if (y1 <= 0 || y1 >= windowHeight - circleDiameter) v1f_y = -v1f_y;

        return new ArrayList<>(List.of(object1.get(0) + v1f_x, object1.get(1) + v1f_y, object1.get(2), v1f_x, v1f_y, object1.get(5)));
    }
}

class physicsThread extends Thread {
    private ArrayList<ArrayList<Double>> tempArray;
    public physicsThread(ArrayList<ArrayList<Double>> array) {
        this.tempArray = array;
    }
    public void run() {
        while (true) {
            if (tempArray != Main.array) tempArray = Main.array;
            for (int i = 0; i < tempArray.size(); i++)
                for (int j = 0; j < tempArray.size(); j++)
                    if (i != j) tempArray.set(i, Main.objectsPhysicsCalculation(tempArray.get(i), tempArray.get(j)));
            Main.array = tempArray;
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
