package ru.pft40.bugHunter.geometry;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by BugHunter on 14.04.2017.
 */
public class PointTests {
    @Test
    public void testCreatedPointNotNull() throws Exception {
        Point point = new Point(1, -3);
        Assert.assertNotNull(point);
    }

    @Test
    public void testCtorWorksProperly() throws Exception {
        Point point = new Point(5,3);
        Assert.assertEquals(point.x, 5.0);
        Assert.assertEquals(point.y, 3.0);
    }

    @Test
    public void testCalculateDistanceTo_xCoordsEqualZero_ReturnsCorrectResult() throws Exception {
        Point p1 = new Point(0, -5);
        Point p2 = new Point(0, 7);
        double distance = p1.calculateDistanceTo(p2);
        Assert.assertEquals(distance, 12.0);
    }

    @Test
    public void testCalculateDistanceTo_yCoordsEqualZero_ReturnsCorrectResult() throws Exception {
        Point p1 = new Point(8, 0);
        Point p2 = new Point(-4, 0);
        double distance = p1.calculateDistanceTo(p2);
        Assert.assertEquals(distance, 12.0);
    }

    @Test
    public void testCalculateDistanceTo_TwoPointsAreEqual_ReturnsZero() throws Exception {
        Point p1 = new Point(0, -5);
        Point p2 = new Point(0, -5);
        double distance = p1.calculateDistanceTo(p2);
        Assert.assertEquals(distance, 0.0);
    }
}
