package ru.pft40.bugHunter.mathematics;

/**
 * Created by BugHunter on 08.04.2017.
 */
public class Application {
    public static void main(String[] args) {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(-5, -5);
        double distance = p1.calculateDistanceTo(p2);
        String message = String.format("Расстояние между точками с координатами (%.2f; %.2f) и (%.2f; %.2f) = ",
                                        p1.x, p1.y, p2.x, p2.y);
        System.out.println(message + distance);
    }

}
