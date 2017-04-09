package ru.pft40.bugHunter.geometry;

/**
 * Created by BugHunter on 08.04.2017.
 */
public class Point {
    float x; //coordinate X
    float y; //coordinate Y


    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public double calculateDistanceTo(Point p){
        double xSubstrDegree2 = substractionDegree2(p.x, this.x);
        double ySubstrDegree2 = substractionDegree2(p.y, this.y);
        return Math.sqrt(xSubstrDegree2 + ySubstrDegree2);
    }

    public static double substractionDegree2(float a, float b){
        return (a*a - 2*a*b + b*b);
    }
}
