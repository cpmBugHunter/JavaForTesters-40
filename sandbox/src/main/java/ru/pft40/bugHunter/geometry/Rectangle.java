package ru.pft40.bugHunter.geometry;

/**
 * Created by BugHunter on 13.04.2017.
 */
public class Rectangle {
    public float a; //rectangle width
    public float b; //rectangle height

    public Rectangle(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public float calculateArea(){
        return this.a*this.b;
    }
}
