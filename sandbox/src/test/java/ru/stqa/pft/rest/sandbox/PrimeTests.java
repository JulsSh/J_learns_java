package ru.stqa.pft.rest.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PrimeTests {

  @Test
  public void testPrime(){
   Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }
  @Test
    public void testNonPrime(){
      Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));

    }
  @Test(enabled = false)
  public void testPrimeLong(){
    long n=Integer.MAX_VALUE;
    assertTrue(Primes.isPrime(n));
  }
}
