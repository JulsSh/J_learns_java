package ru.stqa.pft.sandbox;

public class HelloWorld {

  public static void main(String[] args) {

    hello("world");
    hello("user");
    hello("Juls");

    Square s= new Square(5);
    System.out.println("square with side " + s.l +" = " + s.area());

    Rectangle r=new Rectangle(4,6);

    System.out.println("square with side " + r.a + "and" +  r.b +" = " + r.area());
  }
  public static void hello(String someone) {
    System.out.println("Hello, " + someone + "!");
  }


  public static double area (double a, double b){
    return a*b;
  }
}
