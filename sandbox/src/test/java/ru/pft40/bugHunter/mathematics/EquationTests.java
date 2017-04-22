package ru.pft40.bugHunter.mathematics;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by BugHunter on 22.04.2017.
 */
public class EquationTests {

    @Test
    public void test0() throws Exception {
        Equation e = new Equation(1, 1, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public void test1() throws Exception {
        Equation e = new Equation(1, 2, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public void test2() throws Exception {
        Equation e = new Equation(1, 5, 6);
        Assert.assertEquals(e.rootNumber(), 2);
    }

    @Test
    public void testLinear() throws Exception {
        Equation e = new Equation(0, 1, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public void testConstant() throws Exception {
        Equation e = new Equation(0, 0, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public void testAllZero() throws Exception {
        Equation e = new Equation(0, 0, 0);
        Assert.assertEquals(e.rootNumber(), -1);
    }

}
