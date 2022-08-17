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

