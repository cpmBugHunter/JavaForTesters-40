package ru.pft40.bugHunter.mathematics;


import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTests {

    @Test
    public void testIsPrime() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testIsPrimeWhile() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testIsPrimeFaster() {
        Assert.assertTrue(Primes.isPrimeFaster(Integer.MAX_VALUE));
    }

    @Test
    public void testIsPrimeFastest() {
        Assert.assertTrue(Primes.isPrimeFastest(Integer.MAX_VALUE));
    }
}
