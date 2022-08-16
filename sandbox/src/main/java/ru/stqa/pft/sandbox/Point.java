package ru.stqa.pft.sandbox;

class Point {
  int a;
  int b;
  public Point(int a, int b) {
    this.a = a;
    this.b = b;
  }
  public double distance(int a, int b) {

    int dx = this.a - a;

    int dy = this.b - b;

    return Math.sqrt(dx*dx + dy*dy);

  }

  double distance(Point p) {

    return distance(p.a, p.b);

  }
}

class PointDist {

  public static void main(String args[]) {
    Point p1 = new Point(-17, 8);

    Point p2 = new Point(-1, 0);

    System.out.println("the distance between 2 points is " + p1.distance(p2));

  }
}