package ru.pft40.bugHunter.mathematics;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by BugHunter on 13.04.2017.
 */
public class SquareTests {

    @Test
    public static void testCalculateArea() {
        Square square = new Square(5);
        Assert.assertEquals(square.calculateArea(), 25.0f);
    }
}
