package ru.pft40.bugHunter.geometry;

/**
 * Created by BugHunter on 08.04.2017.
 */
public class Application {
    public static void main(String[] args) {
        Point p1 = new Point(7, 4);
        Point p2 = new Point(0, -4);
        double distance = calculateDistance(p1, p2);
        String message = String.format("Расстояние между точками с координатами (%.2f; %.2f) и (%.2f; %.2f) = ",
                                        p1.x, p1.y, p2.x, p2.y);
        System.out.println(message + distance);
    }

    public static double calculateDistance(Point p1, Point p2){
        double xSubstrDegree2 = substractionDegree2(p1.x, p2.x);
        double ySubstrDegree2 = substractionDegree2(p1.y, p2.y);
        return Math.sqrt(xSubstrDegree2 + ySubstrDegree2);
    }

    public static double substractionDegree2(float a, float b){
        return (a*a - 2*a*b + b*b);
    }
}
