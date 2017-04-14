package ru.pft40.bugHunter.geometry;

/**
 * Created by BugHunter on 13.04.2017.
 */
public class Square {
    float a; //side length


    public Square(float a) {
        this.a = a;
    }

    public float calculateArea(){
        return this.a*this.a;
    }

}
