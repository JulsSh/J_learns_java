package ru.stqa.pft.sandbox;

public class HelloWorld {

  public static void main(String[] args) {

    hello("world");
    hello("user");
    hello("Juls");
    double l =5;
    System.out.println("square with side " + l +" = " + area(l));
    double a =4;
    double b=6;
    System.out.println("square with side " + a + "and" +  b +" = " + area(l));
  }
  public static void hello(String someone) {
    System.out.println("Hello, " + someone + "!");
  }
  public static double area (double len){
    return len*len;
  }
  public static double area (double a, double b){
    return a*b;
  }
}
