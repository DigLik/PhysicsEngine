package Main;

// {x, y, radius, angle(radian), a(force), mass(kg), color}

import java.awt.*;

public class ArrayFunctions {
    public static Object[][] addObject(Object[][] array, double x, double y, double radius, double angle, double a, double mass, Color color) {
        Object[][] newArray = new Object[array.length + 1][];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = new Object[]{x, y, radius, angle, a, mass, color};
        return newArray;
    }

    public static Object[][] removeObject(Object[][] array, int index) {
        if (index < 0 || index >= array.length) {
            return array;
        }
        Object[][] newArray = new Object[array.length - 1][];
        System.arraycopy(array, 0, newArray, 0, index);
        if (array.length - (index + 1) >= 0)
            System.arraycopy(array, index + 1, newArray, index + 1 - 1, array.length - (index + 1));
        return newArray;
    }
}
