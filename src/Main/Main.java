package Main;

import static Main.Array.*;
import static Main.Config.*;
import static Main.GUI.*;
import static Main.Functions.*;

public class Main {
    public static Object[][] array = new Object[0][];
    public static void main(String[] args) {
        createWindow();
        GUI gui = new GUI();
        for (int i = 0; i < 10; i++) {
            array = addObject(array,
                    random(circleRadius / 2, windowWidth - circleRadius / 2),
                    random(circleRadius / 2, windowHeight - circleRadius / 2),
                    circleRadius, 0, circleMass, 0, circleColor);
        }
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
        }
    }
}
