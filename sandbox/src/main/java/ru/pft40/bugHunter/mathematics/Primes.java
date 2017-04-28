package ru.pft40.bugHunter.mathematics;


public class Primes {    


    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if(n % i == 0){
               return false;
            }
        }        
        return true;
    }

    static boolean isPrimeFaster(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < n/2; i++) {
            if(n % i == 0){
                return false;
            }
        }
        
        return true;
    }

    static boolean isPrimeFastest(int n) {
        int sqr = (int)Math.sqrt(n);
        if (n < 2) {
            return false;
        }
        for (int i = 2; i < sqr; i++) {
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    static boolean isPrimeWhile(int n) {
        if (n < 2) {
            return false;
        }
        int i = 2;
        while (i < n && n % 2 != 0) {
            i++;
        }
        return i == n;
    }
}
