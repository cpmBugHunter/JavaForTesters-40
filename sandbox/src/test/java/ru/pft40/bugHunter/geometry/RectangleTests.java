package ru.pft40.bugHunter.geometry;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by BugHunter on 14.04.2017.
 */
public class RectangleTests {
    @Test
    public void testCalculateArea() {
        Rectangle rectangle = new Rectangle(3, 7);
        Assert.assertEquals(rectangle.calculateArea(), 21.0f);
    }
}
